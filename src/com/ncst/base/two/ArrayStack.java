package com.ncst.base.two;

/**
 * @author i
 * @create 2020/5/10 17:18
 * @Description
 *      数组实现栈
 *    思路：栈是一个先进后出结构。比如 1,2,3  pop()第一个数为3
 *    我们用size表示数组中已有数的大小。
 *    push():> 先判断数组是否超过了initSize的大小 添加数据 size++
 *    pop():> 先判断数组是否为空 然后弹出数据 size--
 *    peek():>查看栈顶元素 直接返回arr[size-1]; 为什么需要-1 因为在添加数据的时候
 *          size++ 指向的是当前没有数据的栈顶
 *
 */
public class ArrayStack <T>{
    private T [] arr;
    private int size;

    public ArrayStack(int initSize){
        arr = (T [] )new Object [initSize];
        size = 0;
    }

    //入栈
    public void push(T data){
        if(size >= arr.length){
            throw new IllegalArgumentException("stack is full!");
        }
        arr[size++] = data;
    }

    //出栈
    public T pop(){
        if (size == 0){
            throw  new IllegalArgumentException("stack is empty");
        }
        return arr[--size]; //Note 应该先--size 因为添加数据后 size=3 arr[3] 是越界的
    }

    //查看栈顶元素
    public T peek(){
        return arr[size-1];
    }

    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(3);
        arrayStack.push(1);
        arrayStack.push(2);
        arrayStack.push(3);

        System.out.println(arrayStack.peek());

        System.out.println(arrayStack.pop());
        System.out.println(arrayStack.pop());
        System.out.println(arrayStack.pop());
    }

}
