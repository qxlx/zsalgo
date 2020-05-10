package com.ncst.base.one;

import java.util.Arrays;

/**
 * @author i
 * @create 2020/5/8 12:42
 * @Description 荷兰国旗问题
 *      给定一个数组arr,和一个数num,请把小于num的数放在数组的左边，等于num的数放在数组的中间，
 *      大于num的数放在数组的右边。
 */
public class NetherlandsFlag {

    /***
     *  荷兰国旗问题的解决
     *  1.设定less 为-1 位置 more 为arr.length位置 cur为当前移动的 在0位置
     *  a.终止条件 cur < more 一直执行
     *  b. 1.如果 arr[cur] < p 将less+1的位置和当前cur指向的元素置换 less区逼迫等于区向右移动
     *     2.如果 arr[cur] > p 将more-1的位置和当前cur指向的元素置换 more区域逼迫等于区向左移动 因为刚置换为最后more-1的元素
     *       所以 cur不需要移动 需要再一次判断。
     *     3.就是arr[cur] == p cur 直接跳过cur++;
     *  以上就是解决荷兰问题的基本思路、问题的解决有点类似于双指针，不过这里是三指针(自己的想法) 左 中 右 符合哪个指针 就进行什么样的操作、
     * @param arr 数组
     * @param p  选取的target目标值
     * @return
     */
    public static int[] partiation(int[] arr, int p) {
        int less = -1, more = arr.length, cur = 0;
        while (cur < more) {
            if (arr[cur] < p) {
                swap(arr, ++less, cur++);
            } else if (arr[cur] > p) {
                swap(arr, --more, cur);
            } else {
                cur++;
            }
        }
        return arr;
    }

    private static void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {1, 4, 5, 3, 2, 9};
        System.out.println(Arrays.toString(partiation(arr, 4)));
    }


}
