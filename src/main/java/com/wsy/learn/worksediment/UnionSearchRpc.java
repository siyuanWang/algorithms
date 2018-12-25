package com.wsy.learn.worksediment;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.MINUTES;


public class UnionSearchRpc {

    private ExecutorService executor = new ThreadPoolExecutor(50,
            100,
            1L,
            MINUTES,
            new LinkedBlockingQueue<>(100),
            Executors.defaultThreadFactory(),
            (r, executor) -> {
                System.out.println("线程池已满");
                throw new RejectedExecutionException("Task " + r.toString() + " rejected from " + executor.toString());
            }
    );

    public List<String> query() {
        ExecutorCompletionService<String> completion = new ExecutorCompletionService<>(executor);
        int loop = 0;
        completion.submit(() -> "1");
        loop++;
        completion.submit(() -> "2");
        loop++;
        completion.submit(() -> "3");
        loop++;
        completion.submit(() -> {
            Thread.sleep(5000);
            return "4";
        });
        loop++;
        List<String> list = new ArrayList<>();
        try {
            for (int i = 0; i < loop; i++) {
                Future<String> future = completion.poll(500, TimeUnit.MILLISECONDS);
                if(future != null) {
                    list.add(future.get());
                }

            }
            System.out.println("挑出循环");
        } catch (Exception e) {
            e.printStackTrace();
        }
        kill();
        return list;
    }

    void kill() {
        executor.shutdown();
    }

    public static void main(String[] args) {
        UnionSearchRpc rpc = new UnionSearchRpc();
        System.out.println(rpc.query());
    }
}
