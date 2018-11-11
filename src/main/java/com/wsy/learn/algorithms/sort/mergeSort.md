#归并排序学习笔记

归并排序采用了分治法的算法思想，所谓*分治法（Divide and conquer algorithm）*：将原问题分解为几个规模较小但类似于原问题的子问题，递归的求解这些子问题
然后再合并这些子问题的解来建立原问题的解。时间复杂度O(nlgn),空间复杂度O(n)，n指问题的规模。


分为三个步骤：

&nbsp;&nbsp;&nbsp;&nbsp;分解(Divide):将原问题分解为若干的子问题，这些子问题是原问题的规模较小的实例。

&nbsp;&nbsp;&nbsp;&nbsp;解决(Conquer):解决这些子问题，递归的求解各个子问题。

&nbsp;&nbsp;&nbsp;&nbsp;合并(Combine):合并这些子问题的解为原问题的解。