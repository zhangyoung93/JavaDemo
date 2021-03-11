package com.zy.demo.algorithm;

/**
 * 分治
 * @author zy
 */
public class Partition {

    /**
     * 在有序数组arr中查找目标值target是否存在。
     *
     * 解题思路：
     * 1、遍历数组，逐个判断arr[i]与target是否相等。时间复杂度是O(n)。
     * 2、分治。使用二分查找算法，将arr分解，逐个查找子数组，直到找到target或子问题不可分解。时间复杂度是O(logn)。
     *
     * @param arr 有序数组
     * @param target 查找的目标元素
     * @return target存在返回true；否则返回false。
     */
    public static boolean ifExist(int[] arr,int target){
        //入参校验
        if(arr == null || arr.length == 0){
            return false;
        }
        //数组低位索引
        int low = 0;
        //数组中位索引
        int middle;
        //数组高位索引
        int high = arr.length-1;
        //二分查找，如果低位索引大于高位索引时，退出循环
        while (low <= high){
            //低位索引与高位索引先判断一次
            if(arr[low] == target || arr[high] == target){
                return true;
            }
            //获取中位索引
            middle = (low+high)/2;
            //目标值匹配中位索引值，则目标值存在
            if(target == arr[middle]){
                return true;
            }else if(target < arr[middle]){
                //目标值在低位与中位之间，高位索引收缩到中位索引-1
                high = middle - 1;
            }else{
                //目标值在中位与高位之间，低位索引扩展到中位索引+1
                low = middle + 1;
            }
        }
        return false;
    }

    /**
     * 在指定有序数组arr中，查找数组中第一个大于target的数值。
     * @param arr 有序数组
     * @param target 指定数值
     * @return 数组arr中第一个大于num的数值；如果不存在，则返回-1。
     */
    public static int findFirstNum(int[] arr,int target){
        //入参校验
        if(arr == null || arr.length == 0){
            return -1;
        }
        //低位索引
        int low = 0;
        //中位索引
        int middle;
        //高位索引
        int high = arr.length-1;
        //二分查找
        while(low <= high){
            //获取中位索引
            middle = (low+high)/2;
            //如果目标值小于中位数字，且中位元素前1位数字大于等于目标值，剔除中位索引到0的情况
            if(target < arr[middle] && (middle == 0 || target <= arr[middle-1])){
                //中位数字即数字中大于target的第一个数字
                return arr[middle];
            }else if(target > arr[middle]){
                low = middle+1;
            }else{
                high = middle-1;
            }
        }
        return -1;
    }
}
