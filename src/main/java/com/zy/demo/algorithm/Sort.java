package com.zy.demo.algorithm;

import java.util.Random;

/**
 * 排序
 * @author zy
 */
public class Sort {

    /**
     * 冒泡排序。
     *
     * 原理：
     * 从第一个数据开始，依次比较相邻两个数据的大小，如果前者与后者不符合指定顺序(升序要求前者小于后者；降序要求前者大于后者)，则交换它们的位置，直到没有交换操作可执行。最终每个数据的排序过程像冒泡一样在结尾浮起。
     *
     * 性能：
     * 时间复杂度：O(n²) --双层嵌套循环
     * 空间复杂度：O(1) --消耗有限的内存资源
     * 稳定性：稳定 --同一个数据在排序前后的相对位置不变。
     *
     * @param arr 排序的对象
     * @return 排序结果
     */
    public static int[] bubbleSort(int[] arr){
        //入参校验
        if(arr == null || arr.length == 0){
            return null;
        }
        //临时变量
        int tmp;
        //第一次循环表示排序的趟数
        for(int i = 1 ; i < arr.length ; i++){
            //第二次循环表示每趟排序将第1个数字浮起的步骤
            for(int j = 0 ; j < arr.length-i ; j++){
                //假设要求降序排列。
                if(arr[j] < arr[j+1]){
                    tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                }
            }
        }
        return arr;
    }

    /**
     * 插入排序。
     *
     * 原理：
     * 假设第一个数据arr[0]是已排序的序列，将其后未排序的元素按照排序规则逐个插入该序列中，直到未排序的数据为空。
     *
     * 性能：
     * 时间复杂度：O(n²) --双层嵌套循环
     * 空间复杂度：O(1) --消耗有限的内存资源
     * 稳定性：稳定 --同一个数据在排序前后的相对位置不变。
     *
     * @param arr 待排序数组
     * @return 排序后的数组
     */
    public static int[] insertionSort(int[] arr){
        //入参校验
        if(arr == null || arr.length == 0){
            return null;
        }
        //临时变量
        int tmp;
        //第一次循环遍历未排序的数据
        for(int i = 1 ; i < arr.length ; i++){
            //第二次循环将未排序的数据与已排序的数据比较
            for(int j = 0 ; j < i ; j++){
                //假设要求升序排列
                if(arr[i] < arr[j]){
                    tmp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = tmp;
                }
            }
        }
        return arr;
    }

    /**
     * 选择排序。
     *
     * 原理：
     * 每次从数组查找最值元素并移动到数组头部，直到循环结束。
     *
     * 性能：
     * 时间复杂度：O(n²) --双层嵌套循环
     * 空间复杂度：O(1) --消耗有限的内存资源
     * 稳定性：稳定 --同一个数据在排序前后的相对位置不变。
     *
     * @return 排序结果
     */
    public static int[] selectionSort(int[] arr){
        //入参校验
        if(arr == null || arr.length == 0){
            return arr;
        }
        //临时变量
        int tmp;
        //先遍历n-1次，最后1次无需遍历
        for(int i = 0 ; i < arr.length-1 ; i++){
            //声明最值索引
            int index = i;
            //每次遍历寻找最值索引
            for(int j = i+1 ; j < arr.length ; j++){
                //假设要求降序排列
                if(arr[index] < arr[j]){
                    index = j;
                }
            }
            //最值索引放到数组头部
            if(index != i){
                tmp = arr[index];
                arr[index] = arr[i];
                arr[i] = tmp;
            }
        }
        return arr;
    }

    /**
     * 归并排序。
     *
     * 原理：
     * 递归二分数组，直到每个子数组中只有1个元素，对其排序且合并结果，直到所有数组都已完成合并。
     *
     * 性能：
     * 时间复杂度：O(nlogn) --二分的时间复杂度是O(logn)，每次二分遍历子数组的时间复杂度是O(n)。
     * 空间复杂度：O(n) --新建数组用于存储中间排序结果
     * 稳定性：稳定 --同一个数据在排序前后的相对位置不变。
     *
     * @param arr 原数组
     * @return 排序后数组
     */
    public static int[] mergeSort(int[] arr){
        //入参校验
        if(arr == null || arr.length == 0){
            return arr;
        }
        //创建数组用于暂存排序结果
        int[] result = new int[arr.length];
        //递归二分
        doMergeSortBinary(arr,result,0,arr.length-1);
        //返回排序后数组
        return arr;
    }

    /**
     * 递归二分
     * @param arr 排序前数组
     * @param result 排序后数组
     * @param low 低位索引
     * @param high 高位索引
     */
    private static void doMergeSortBinary(int[] arr,int[] result,int low,int high){
        //低位索引大于等于高位索引，则退出递归。
        if(low >= high){
            return;
        }
        //计算二分索引
        int mid = (low+high)/2;
        //左子数组递归二分(奇数长度数组，中位索引处元素融合到左子数组)
        doMergeSortBinary(arr,result,low,mid);
        //右子数组递归二分(偶数长度数组，中位索引加1处元素融合到右子数组)
        doMergeSortBinary(arr,result,mid+1,high);
        //递归合并
        doMergeSortMerge(arr,result,low,mid,high);
    }

    /**
     * 递归合并
     * @param arr 排序前数组
     * @param result 排序后数组
     * @param low 低位索引
     * @param mid 中位索引
     * @param high 高位索引
     */
    private static void doMergeSortMerge(int[] arr,int[] result,int low,int mid,int high){
        //左子数组起始索引临时记录
        int leftLow = low;
        //右子数组起始索引临时记录
        int rightLow = mid+1;
        //结果数组起始索引临时记录
        int index = low;
        //左子数组与右子数组比较
        while(leftLow <= mid && rightLow <= high){
            //假设按照升序排列
            if(arr[leftLow] <= arr[rightLow]){
                result[index++] = arr[leftLow++];
            }else{
                result[index++] = arr[rightLow++];
            }
        }
        //左子数组剩余元素顺序添加到结果中
        while(leftLow <= mid){
            result[index++] = arr[leftLow++];
        }
        //右子数组剩余元素顺序添加到结果中
        while(rightLow <= high){
            result[index++] = arr[rightLow++];
        }
        //将当前二分数组的排序结果赋值到原数组，并返回给上一层循环
        for(int i = low ; i <= high ; i++){
            arr[i] = result[i];
        }
    }

    /**
     * 快速排序
     *
     * 原理：
     * 选取数组中任意一个元素作为分区点，将较小值放在分区点低位侧，将较大值放在分区点高位侧，递归分区，直到每个区间只有1个元素。
     *
     * 性能：
     * 时间复杂度：O(nlogn) --二分的时间复杂度是O(logn)，每次二分遍历子数组的时间复杂度是O(n)。
     * 空间复杂度：O(1) --交换元素
     * 稳定性：不稳定 --同一个数据在排序前后的相对位置改变
     *
     * @param arr 排序前数组
     * @return 排序后数组
     */
    public static int[] quickSort(int[] arr){
        //入参校验
        if(arr == null || arr.length == 0){
            return arr;
        }
        //随机选择分区点
        int partIndex = new Random().nextInt(arr.length);
        int temp = arr[0];
        arr[0] = arr[partIndex];
        arr[partIndex] = temp;
        //快排递归
        doQuickRecursion(arr,0,arr.length-1);
        return arr;
    }

    /**
     * 快排递归
     * @param arr 排序前数组
     * @param low 低位索引
     * @param high 高位索引
     */
    private static void doQuickRecursion(int[] arr,int low,int high){
        //低位索引大于等于高位索引，则退出递归。
        if(low >= high){
            return;
        }
        //暂存低位索引游标
        int i = low;
        //暂存高位索引游标
        int j = high;
        //暂存分区元素
        int part = arr[low];
        //声明中间变量
        int temp;
        //循环(假设按照降序排列)
        while(i < j){
            //先右分区冒泡
            while(arr[j] <= part && i < j){
                j--;
            }
            //再左分区冒泡
            while(arr[i] >= part && i < j){
                i++;
            }
            //交换左右分区冒泡元素
            temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        //当前数组左右分区完成冒泡后，把分区元素移动到分区点
        arr[low] = arr[i];
        arr[i] = part;
        //递归快排左区间
        doQuickRecursion(arr,low,j-1);
        //递归快排右区间
        doQuickRecursion(arr,j+1,high);
    }
}
