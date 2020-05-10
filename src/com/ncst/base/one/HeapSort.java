package com.ncst.base.one;

import java.util.Arrays;

/**
 * @author i
 * @create 2020/5/8 17:24
 * @Description
 *          思路:堆排序是比较重要的排序，主要是基于堆结构。而堆结构就是完全二叉排序树。因为二叉排序树的结构特点。
 *      我们可以用数组进行模拟二叉树的左右子节点， 关系就是 root节点的左子节点为 2*i + 1 root节点的右子节点为 2*i+2
 *      而左子节点或者右子节点的父节点为 (i-1)/2 ，通过上述关系就可以通过数组来实现堆结构。
 *      而堆结构中比较重要的两个操作就是 一个是节点的生成 也就是heapInsert 比如添加一个节点3 需要父节点比较大小。
 *      如果父节点比较小，交换，否则不交换。这样，每次添加一个节点都需要进行这个heapInsert操作。终止条件就是
 *      上升到根节点 (0-1)/2 == 0 终止。
 *      另一个堆结构的操作时 heapify()，堆排序就是基于大顶堆 拿到最大的元素，将最大元素和最后元素交换。这样依次拿最大的元素。
 *      但是交换上去的元素，要维护大顶堆结构的。依次将根节点的元素和左右子节点元素比较，选取出左右子节点最大值，进行交换、
 *      通俗一点 来说，就是每次拿最大值，将交换的值 再次和最大的值交换。但是大小要减一。
 *      总结 堆排序基于堆结构 堆结构基于完全二叉树来形成。掌握 堆的heaoInsert 和 heapify
 * <p>
 *      时间辅助度:O(nlogn) 空间复杂度:O(1)
 */
public class HeapSort {

    public static void heapSort(int[] arr) {
        if (arr == null && arr.length < 2) {
            return;
        }
        //先构建大顶堆
        for (int i = 0; i < arr.length; i++) {
            headInsert(arr, i);
        }

        int size = arr.length;
        //每次选大顶堆root节点 size不断减1
        swap(arr, 0, --size);
        while (size > 0) {
            headify(arr, 0, size);
            swap(arr, 0, --size);
        }
    }

    //上升
    public static void headInsert(int[] arr, int cur) {
        while (arr[cur] > arr[(cur - 1) / 2]) {
            swap(arr, cur, (cur - 1) / 2);
            cur = (cur - 1) / 2;
        }
    }

    //下降
    public static void headify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;//最大值下标
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index) {
                break;
            }
            swap(arr, largest, index);
            index = largest;
            left = index * 2 + 1;
        }
    }

    private static void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {1, 5, 6, 3, 7, 4, 0};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
