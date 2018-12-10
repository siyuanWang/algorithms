package com.wsy.learn.guava;

import com.alibaba.fastjson.JSON;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 1、refreshAfterWrite when key isn't expired, refresh the cache
 */
@Component
@EnableScheduling
public class CacheStudy {
    private final ListeningExecutorService backgroundRefreshPools = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(2));

    private final LoadingCache<String, byte[]> cache = CacheBuilder.newBuilder()
            //设置cache的初始大小为100,最大容量是各个场景最大托底数量集合.cache在接近max的时候，会随着缓存大小增长接近最大值，缓存逐出不太可能再次使用的条目
            .initialCapacity(100).maximumSize(200)
            //设置并发数为5，即同一时间最多只能有5个线程往cache执行写入操作
            .concurrencyLevel(5)
            //设置cache中的数据过期间隔，避免key始终没有被查询但是依然在缓存池中的情况
            .expireAfterWrite(300, TimeUnit.SECONDS)
            .refreshAfterWrite(10, TimeUnit.SECONDS)
            //统计缓存命中率
            .recordStats()
            .weakKeys()
            .weakValues()
            //设置缓存移除通知
            .removalListener((notification) -> System.out.println(notification.getKey() + " was removed, cause is " + notification.getCause()))
            //构建cache实例
            .build(new CacheLoader<String, byte[]>() {
                public byte[] load(String key) {
                    System.out.println("start load");
                    try {
                        System.out.println("开始load");
                        //Thread.sleep(1000);
                        //System.out.println("结束load");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    byte[] bytes = new byte[30*1024];
                    return bytes;
                }

                @Override
                public ListenableFuture<byte[]> reload(String key, byte[] oldValue) throws Exception {
                    System.out.println("开始刷新");
                    return backgroundRefreshPools.submit(() -> {
                        try  {
                            Thread.sleep(3000);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        byte[] bytes = new byte[30*1024];
                        return bytes;
                    });
                }
            });


    @PostConstruct
    private void init() {
        byte[] bytes = new byte[1*1024*1024];
        cache.put("abc", bytes);
    }


    private static AtomicInteger i = new AtomicInteger(0);

    @Scheduled(fixedDelay = 1000 * 1)
    private void intervalRefresh() {
        try {
            byte[] cacheValue = cache.get("abc" + i.getAndIncrement());
            System.out.println(cacheValue);
            System.out.println(cache.asMap().keySet());
            //System.gc();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

    }
}
