package com.ncst.base.one;

import java.util.Arrays;

/**
 * @author i
 * @create 2020/5/8 16:27
 * @Description
 *
 *   快速排序 time O(NlogN) space : O(logN)
 *   实现思路:
 *       经典快排，分区是按照数组的最后一个元素进行分区，而在分区的过程中，每次只能确定一个元素的位置。因此，当出现 极端情况
 *       [1,2,3,4,5,6] or [9,8,5,3,1] 这种已经排序好的情况，time 为O(N^2)
 *       而荷兰国旗问题的出现解决了，分区每次只能确定一个数的情况。荷兰问题 每次将数据分为3部分 小于目标值 等于目标值 大于目标值
 *       当目标值出现多个的时候，可以将等于目标值的数 全部确定位置，而经典快排无法做到这一点。因此 对于有重复元素的快排 荷兰国旗优化版的快排
 *       要比经典快排有一定的优势。
 *       虽然 荷兰国旗问题了重复元素的问题，但是，由于 选取分区的target是无法确定的。时间复杂度也是无法去评估的。
 *       因此 加入随机选取分区的target值，在概率上解决了 将时间复杂度稳定在O(NlogN) space : O(logN)
 *       而随机快排的space 是由数据的规模决定的，也就是数据的logn次定位。
 *       总结  经典快排 》 荷兰国旗问题优化 》 随机快排、
 */
public class QuickSort {

    public static void sortQuick(int [] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        sortQuick(arr,0,arr.length-1);
    }


    private static void sortQuick(int[] arr, int l, int r) {
        if(l < r){
            //随机快排
            swap(arr,l+(int)(Math.random()*(r-l+1)),r);
            int[] partition = partition(arr, l, r);
            sortQuick(arr,l,partition[0]-1);
            sortQuick(arr,partition[1]+1,r);
        }
    }

    /***
     *  分区操作
     * @param arr
     * @param l
     * @param r
     * @return
     */
    public static int [] partition(int [] arr,int l,int r){
        int less = l-1;
        int more = r;
        while (l<more){
            if(arr[l]<arr[r]){
                swap(arr,++less,l++);
            }else if(arr[l] > arr[r]){
                swap(arr,--more,l);
            }else {
                l++;
            }
        }
        swap(arr,more,r);// note 每次交换最后数组一个元素和重复元素。
        return new int [] {less+1,more};
    }


    private static void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    public static void main(String[] args) {
        int [] arr = {1,5,6,3,7,4,0};
        sortQuick(arr);
        System.out.println(Arrays.toString(arr));
    }

}
