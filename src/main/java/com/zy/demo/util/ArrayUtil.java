package com.zy.demo.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 数组
 */
public class ArrayUtil {

    /**
     * 假设整型数组存储了n(n>=5)个评委对运动员的打分(分值)，且每个评委的打分都不一样，要求用数组按照顺序保存去掉最高分和最低分之后的结果，并计算结果中的平均分数。程序的空间复杂度为O(1)。
     * 时间复杂度：O(n²)  --两层嵌套循环判断评委打分是否重复。
     * 空间复杂度：O(1)  --使用有限的内存资源
     * @param arr 评委的打分数组
     */
    public static void score(int[] arr){
        //入参校验
        if(arr == null || arr.length < 5){
            return;
        }
        int maxIndex = 0;
        int minIndex = 0;
        int maxVal = 0;
        int minVal = 0;
        //数组长度
        int arrLen = arr.length;
        //校验是否有重复分数
        for(int i = 0 ; i < arrLen ; i++){
            for(int j = i+1 ; j < arrLen ; j++){
                if(arr[i] == arr[j]){
                    System.out.println("每位评委的打分不允许相同，请重新打分！");
                    return;
                }
            }
        }
        //遍历分数，获取最高分与最低分的索引
        for(int i = 0 ; i < arrLen ; i++){
            //第一次循环把最大值与最小值赋值
            if(i == 0){
                maxVal = minVal = arr[i];
            }
            //校验分数
            if(arr[i] < 0 || arr[i] > 100){
                System.out.println("打出的分数不能低于0分或高于100分，请重新打分！");
                return;
            }
            //筛选最大值索引
            if(arr[i] > maxVal){
                maxIndex = i;
                maxVal = arr[i];
            }
            //筛选最小值索引
            if(arr[i] < minVal){
                minIndex = i;
                minVal = arr[i];
            }
        }
        //最值索引处理，防止一次遍历同时改变2个索引，造成第二次遍历的误差
        int before = maxIndex;
        int after = minIndex;
        if(maxIndex < minIndex){
            before = minIndex;
            after = maxIndex;
        }
        //遍历分数，删除最高分
        for(int i = before ; i < arrLen-1 ; i++){
            arr[i] = arr[i+1];
        }
        //遍历分数，删除最低分
        for(int i = after ; i < arrLen-1 ; i++){
            arr[i] = arr[i+1];
        }
        arr[arrLen-1] = arr[arrLen-2] = 0;
        //求和
        float sum = 0;
        for(int i = 0 ; i < arrLen-2 ; i++){
            sum+=arr[i];
        }
        //求平均数
        float ave = sum/(arrLen-2);
        System.out.println("原数组去掉最高分"+maxVal+"和最低分"+minVal+"后的平均分为"+ave);
    }

    /**
     * 习题：一个整型数组有n个元素，输出该数组翻转后的结果。
     * 时间复杂度：O(n/2)=O(n) --数组元素共交换n/2次。
     * 空间复杂度：O(1) --使用有限的int变量。
     */
    public static void overturn(int[] arr){
        //缓存变量
        int tmp;
        //数组长度
        int arrLen = arr.length;
        //首尾元素位置逐个交换，直到数组中间位置停止
        for(int i = 0 ; i < arrLen/2 ; i++){
            tmp = arr[i];
            arr[i] = arr[arrLen-i-1];
            arr[arrLen-i-1] = tmp;
        }
        System.out.println("数组翻转后的结果为"+ Arrays.toString(arr));
    }

    /**
     * 习题：查找指定整型数组中出现次数最多的数字。
     * 时间复杂度：O(n)+O(n)+O(n)=O(3n)=O(n) --3块顺序执行的for循环。
     * 空间复杂度：O(n) --新建Map、StringBuilder、String变量，与输入数据量n线性相关。
     */
    public static void calcTimes(int[] arr){
        //定义HashMap存储数字与出现次数的关系
        Map<Integer,Integer> map = new HashMap<>();
        //数组长度
        int arrLen = arr.length;
        //查找数组中出现次数最多的数字
        for(int i = 0 ; i < arrLen ; i++){
            if(map.containsKey(arr[i])){
                map.put(arr[i],map.get(arr[i])+1);
            }else{
                map.put(arr[i],1);
            }
        }
        //最大次数
        int timesMax = 0;
        //遍历Map获取最多次数
        for(Integer value : map.values()){
            if(value > timesMax){
                timesMax = value;
            }
        }
        //缓存变量
        StringBuilder sb = new StringBuilder();
        //筛选最多次数的数字
        for(Map.Entry<Integer,Integer> entry : map.entrySet()){
            if(entry.getValue() == timesMax){
                sb.append(entry.getKey()).append(",");
            }
        }
        //删除最后一个逗号
        String str = sb.deleteCharAt(sb.length()-1).toString();
        System.out.println("数组"+Arrays.toString(arr)+"中出现次数最多的数字是"+str+",共出现"+timesMax+"次！");
    }

    /**
     * 指定已排序的数组，删除数组中的重复元素后输出新数组内容和删除元素的个数。要求空间复杂度为O(1)。
     * 时间复杂度：O(n)  --遍历一次数组
     * 空间复杂度：O(1)  --使用有限的内存资源
     * @param arr
     */
    public static void removeDup(int[] arr){
        //入参校验
        if(arr == null || arr.length == 0){
            return;
        }
        //删除元素计数器
        int count = 0;
        //临时变量
        int tmp = 0;
        //遍历数组，数组末尾元素已在前一次循环中比较
        for(int i = 0 ; i < arr.length-1 ; i++){
            //如果当前元素与下个索引的元素相同，则删除并移动索引。
            if(arr[i] == arr[i+1]){
                arr[i] = arr[i+1];
                count++;
            }else{
                //如果当前元素与上个索引的元素相同，则删除并移动索引。
                if(i > 0 && tmp == arr[i]){
                    arr[i] = arr[i+1];
                }
            }
            //保存上一个索引的元素
            tmp = arr[i];
        }
        System.out.println("新数组="+Arrays.toString(arr)+",删除数组尾部"+count+"个元素。");
    }
}
