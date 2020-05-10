package com.ncst.base.one;

/**
 * @author i
 * @create 2020/5/9 19:29
 * @Description
 *
 *  问题：
 *      给定一个数组，求如果排序之后，相邻两数的最大差值，要求时间复杂度O(N)，且要求不能用非基于比较的排序。
 *  思路:
 *      我们可以基于桶排序的思想来解决这个问题。data[1,2,3,4,5,7,8] 那么最大值就为2 5->7之间的最大间隙为2
 *      a.首先创建一个n+1 大小的桶，遍历一遍数组 找出minValue 和 maxValue。如果最大值和最小值相等 说明数据一样 直接返回
 *      将minValue放在下标为0的桶的位置   maxValue放在下标为n的桶位置。
 *      b.创建三个数组 分别记录 存放的是hasNum->是否有数据  minNum ->最小值  maxNum ->最大值
 *      遍历将数据放在每个桶的位置上，桶只存储三个属性，是否有数据 最小值 最大值。
 *      c.如此 如果想找出间隙最大值，试想是不可能出现在同一个桶类，只会在相邻桶之间 也就是 左桶的最大值 和 油桶的 最小值
 *
 *  复杂度分析
 *     time : O(n)
 *     space : O(1)
 */
public class MaxGap {


    public static int maxGap(int[] arr) {
        //1.参数判断
        if (arr == null || arr.length <= 2) {
            return 0;//表示没有
        }
        int minValue = Integer.MIN_VALUE;
        int maxValue = Integer.MAX_VALUE;

        //2.遍历元素 找出minValue 和 maxValue
        for (int value : arr) {
            minValue = Math.min(minValue, value);
            maxValue = Math.max(maxValue, value);
        }

        if (maxValue == minValue) {
            return 0;
        }

        //3.比较 找出间隙最大值
        int n = arr.length;
        boolean[] hasNum = new boolean[n + 1];
        int[] maxNum = new int[n + 1];
        int[] minNum = new int[n + 1];
        //3.1 将每个元素存储到所属的桶内
        //记录桶的下标
        int bid = 0;

        for (int i = 0; i < n; i++) {
            bid = bucket(arr[i], n, minValue, maxValue);
            //找到最小值
            minNum[bid] = hasNum[bid] ? Math.min(arr[i], minNum[bid]) : arr[i];
            //找到最大值
            maxNum[bid] = hasNum[bid] ? Math.max(arr[i], maxNum[bid]) : arr[i];
            hasNum[bid] = true;
        }
        //3.2 找到间隙最大值
        int res = 0;
        int lastIndex = maxNum[0];
        for (int i = 1; i <= n; i++) {
            if (hasNum[i]) {
                res = Math.max(res, minNum[i] - lastIndex);
                lastIndex = maxNum[i];
            }
        }
        return res;
    }

    /***
     *
     * @param num
     * @param len
     * @param min
     * @param max
     * @return
     */
    public static int bucket(long num, long len, long min, long max) {
        return (int) ((num - min) * len / (max - min));
    }

}
