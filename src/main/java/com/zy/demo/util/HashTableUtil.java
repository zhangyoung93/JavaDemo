package com.zy.demo.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 哈希表
 * @author zy
 */
public class HashTableUtil{

    /**
     * HashMap是数组+单向链表+红黑树的复合数据结构。
     *
     * HashMap扩容(即rehash过程)需要重建一整套数组+单向链表+红黑树，非常影响迭代性能。
     * 避免或减少rehash是维持HashMap高性能的关键因素。
     *
     * HashMap使用直接地址法定义哈希函数。
     * 哈希函数f(key) = (capacity-1) & h(key)。
     * capacity：数组容量。
     * h(key)：计算key哈希码的函数。h(key) = key.hashCode() ^ (key.hashCode() >>> 16)
     *
     * 基础数据通过哈希函数存储在数组对应位置，每个数组的元素是一个单向链表。
     * 当新添加的数据发生哈希冲突，则该数据会通过链地址法，存储在链表的下个结点。
     *
     * 当链表长度大于等于8时，单向链表结构会转化为红黑树结构；当红黑树按照扩容哈希算法(hash & capacity)计算的结点数小于等于6时，红黑树结构会转化为单向链表结构。
     *
     * HashMap增删查的时间复杂度：
     * 无哈希冲突：O(1)
     * 有哈希冲突：出现红黑树结构是O(logn)；未出现红黑树结构是O(1)
     * 数组的时间复杂度：O(1)
     * 链表的时间复杂度：O(1)，因为链表最大长度是8，是有限值，即O(1)。
     * 红黑树的时间复杂度：O(logn)，红黑树可以看作是平衡的二叉搜索树。
     *
     */
    private Map<Object,Object> map;

    public HashTableUtil(){
        this.map = new HashMap<>();
    }

    /**
     * 计算指定字符串被提交的次数
     * 时间复杂度：哈希冲突少的情况下是O(1)；哈希冲突多的情况下是O(logn)。
     * 空间复杂度：O(n) --新建HashMap对象
     * @return 次数
     */
    public int calcKeySubmit(String key){
        //入参校验
        if(key == null){
            return -1;
        }
        //获取指定字符串的提交次数
        Integer count = (Integer) map.get(key);
        //如果字符串第一次被提交，次数为1；否则次数累加。
        count = count == null ? 1 : count+1;
        //记录当前字符串新的提交次数
        map.put(key,count);
        return count;
    }

    /**
     * 给定一个已去重的正整数数组arr和一个目标值target，请在数组中找出相加结果等于目标值的两个整数，并返回它们在数组中的下标。
     * 假设答案只有一组且数据中的元素只能使用1次。
     * 时间复杂度：哈希冲突少的情况下是O(1)；哈希冲突多的情况下是O(logn)。
     * 空间复杂度：O(n) --新建HashMap对象
     */
    public static void findTwoSum(int[] arr,int target){
        //校验正整数数组
        if(arr == null || arr.length < 2){
            return;
        }
        //校验目标值
        if(target < 1){
            return;
        }
        //新建哈希表
        Map<Integer,Integer> map = new HashMap<>();
        //数组长度
        int arrLen = arr.length;
        //遍历数组
        for(int i = 0 ; i < arrLen ; i++){
            //计算另一个整数的期望值
            int otherNum = target - arr[i];
            //去重校验
            if(map.containsKey(arr[i])){
                System.out.println("Illegal array:the array has duplicate element!");
                return;
            }
            //如果当前期望值符合，则找到答案。
            if(map.containsKey(otherNum)){
                System.out.println("答案：value1="+arr[i]+",index1="+i+";value2="+otherNum+",index2="+map.get(otherNum));
            }
            //用哈希表记录数据元素与数组索引的关系
            map.put(arr[i],i);
        }
    }
}
