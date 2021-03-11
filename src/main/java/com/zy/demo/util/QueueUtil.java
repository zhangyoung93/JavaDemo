package com.zy.demo.util;

import java.util.Arrays;

/**
 * 队列
 * 1、顺序队列
 * 2、链式队列
 * 3、循环队列
 * @param <E> 元素
 */
public class QueueUtil<E> {

    /* **** 循环队列----->start **** */

    //队列元素
    private Object[] objects;

    //队列元素个数
    private int size;

    //队列初始化默认值
    private static final Object[] DEFAULT_OBJECTS = {};

    //队列初始化默认容量
    private static final int DEFAULT_CAPACITY = 10;

    //队头指针
    private int head;

    //队尾指针
    private int tail;

    //队列无参构造函数
    public QueueUtil(){
        this.objects = DEFAULT_OBJECTS;
    }

    /**
     * 队列初始化容量构造函数
     * @param capacity 容量
     */
    public QueueUtil(int capacity){
        //校验输入容量
        if(capacity > 0){
            this.objects = new Object[capacity];
        }else if(capacity == 0){
            this.objects = DEFAULT_OBJECTS;
        }else{
            throw new IllegalArgumentException("Illegal capacity=" + capacity);
        }
    }

    /**
     * 获取队列元素个数
     */
    public int size(){
        return this.size;
    }

    /**
     * 打印队列
     */
    @Override
    public String toString() {
        return Arrays.toString(this.objects);
    }

    /**
     * 数组扩容
     * 时间复杂度：O(n) --数组扩容后要为元素重新分配索引
     * 空间复杂度：O(n) --扩容会创建新数组对象，数组大小与输入数据线性相关。
     */
    private void expand(){
        //满队列再添加元素需要扩容
        if(this.size + 1 > this.objects.length){
            //获取旧数组长度
            int oldLen = this.objects.length;
            //扩容后新数组长度
            int newLen = oldLen >= DEFAULT_CAPACITY ? oldLen * 3/2 : DEFAULT_CAPACITY;
            //校验新数组长度
            if(newLen >= Integer.MAX_VALUE){
                throw new IllegalArgumentException("the length of array is too large:"+newLen);
            }
            //扩容
            this.objects = Arrays.copyOf(this.objects,newLen);
            //旧数组索引转换到新队列
            for(int i = 1 ; i <= this.size ; i++){
                this.objects[i] = this.objects[(this.head+i) % oldLen];
            }
            //重置新的队头与队尾
            this.head = 0;
            this.tail = this.size;
            this.objects[head] = null;
        }
    }

    /**
     * 入队
     * 时间复杂度：如果需要扩容是O(n)；不需要扩容是O(1)。
     * 空间复杂度：如果需要扩容是O(n)；不需要扩容是O(1)。
     * @param e 元素
     * @return 入队成功返回true；否则返回false。
     */
    public boolean add(E e){
        //是否扩容
        expand();
        //计算队尾指针
        this.tail = (this.tail+1) % this.objects.length;
        //队尾添加元素
        this.objects[this.tail] = e;
        //计数
        this.size++;
        return true;
    }

    /**
     * 出队
     * 时间复杂度：O(1) --根据数组索引获取出队元素，是对顺序队列的O(n)复杂度的优化。
     * 空间复杂度：O(1) --使用有限的内存资源。
     * @return 出队元素
     */
    @SuppressWarnings("unchecked")
    public E poll(){
        //空队列校验
        if(this.size == 0){
            return null;
        }
        //计数队头指针
        this.head = (this.head+1) % this.objects.length;
        //获取队头元素
        E e = (E)this.objects[this.head];
        //队头元素置空
        this.objects[this.head] = null;
        //计数
        this.size--;
        return e;
    }

    /**
     * 获取队头元素
     * 时间复杂度：O(1) --根据数组索引获取。
     * 空间复杂度：O(n) --使用有限的内存资源。
     * @return
     */
    @SuppressWarnings("unchecked")
    public E peek(){
        //空队列校验
        if(this.size == 0){
            return null;
        }
        //队头元素
        return (E)this.objects[(this.head+1) % this.objects.length];
    }

    /**
     * 约瑟夫环：n个人围成一圈(编号从1到n)，从编号为start的人开始报数1，数到out的人出列，下一个人又从1开始报数，数到out的人又出列，直到n个人都出列。求解n个人出列的顺序。
     * 时间复杂度：O(n²)  -- 嵌套while循环，不过遍历次数是越来越少的。
     * 空间复杂度：O(n)  --创建循环队列、数组、数组缩容的空间复杂度都是O(n)
     * @param n  --输入的人数
     * @param start  --开始报数的人的编号
     * @param out  --出列标记值，数到此标记值的人要出列。
     * @return 出队顺序结果
     */
    public String josephus(int n,int start,int out){
        //校验人数、标记值
        if(n <= 0 || out <= 0){
            return null;
        }
        //默认从第一个人开始报数
        if(start <= 0){
            start = 1;
        }
        //创建容量为n的循环队列
        QueueUtil<Integer> queueUtil = new QueueUtil<>(n);
        //将n个人按照编号顺序入队
        for(int i = 0 ; i < n ; i++){
            queueUtil.add(i+1);
        }
        //设置出队指针起始位置，循环队列队头指针是在索引=0处的，所以要-1。
        queueUtil.head = start - 1;
        //数组索引计数器
        int index = 0;
        //创建结果数组
        int[] result = new int[n];
        //循环出队，直到队列中无人
        while(queueUtil.size > 0){
            //每隔out-1人出队，即更改队头指针
            queueUtil.head = (queueUtil.head + out) % queueUtil.objects.length;
            //获取出队的人
            Integer tmp = (Integer)queueUtil.objects[queueUtil.head];
            //不为空则添加到结果数组中
            if(tmp != null){
                result[index] = tmp;
                //数组索引计数
                index++;
            }
            //出队后此位置置空
            queueUtil.objects[queueUtil.head] = null;
            //队列元素自减
            queueUtil.size--;
            //出队后重新整理队列，
            for(int i = queueUtil.head ; i < queueUtil.size ; i++){
                queueUtil.objects[i] = queueUtil.objects[i+1];
            }
            //剔除已出队的人，重新初始化新数组
            queueUtil.objects = Arrays.copyOf(queueUtil.objects,queueUtil.size);
            //剔除后，队头指针自减
            queueUtil.head--;
        }
        //所有人都已出队后，队头指针归零。
        queueUtil.head = 0;
        //输出结果数组
        return Arrays.toString(result);
    }

    /* **** 循环队列----->end **** */




//    /* **** 链式队列----->start **** */
//
//    //头结点
//    private Node<E> first;
//
//    //尾结点
//    private Node<E> last;
//
//    //队列元素个数
//    private int size;
//
//    /**
//     * 无参构造方法
//     */
//    public QueueUtil(){
//
//    }
//
//    /**
//     * 单向链表初始化头结点构造方法
//     */
//    public QueueUtil(E e){
//        this.first = this.last = new Node<>(e,null);
//        this.size++;
//    }
//
//    /**
//     * 获取队列元素个数
//     * @return 队列元素个数
//     */
//    public int size(){
//        return this.size;
//    }
//
//    /**
//     * 按照从队头到队尾的顺序打印队列，结果通过"{}"包裹
//     */
//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder("{");
//        Node<E> node = this.first;
//        while (node != null){
//            sb.append(node.e).append(",");
//            node = node.next;
//        }
//        if(sb.toString().endsWith(",")){
//            sb.deleteCharAt(sb.length()-1);
//        }
//        sb.append("}");
//        return sb.toString();
//    }
//
//    /**
//     * 入队
//     * 时间复杂度：O(1) --链表添加结点
//     * 空间复杂度：O(1) --使用有限的内存资源
//     * @param e 入队元素
//     * @return 入队成功返回true；否则返回false。
//     */
//    public boolean add(E e){
//        //空队列
//        if(this.size == 0){
//            this.first = this.last = new Node<>(e,null);
//        }else{
//            //队尾添加
//            this.last = this.last.next = new Node<>(e,null);
//        }
//        //队列元素计数
//        this.size++;
//        return true;
//    }
//
//    /**
//     * 出队
//     * 时间复杂度：O(1) --链表头即队头
//     * 空间复杂度：O(1) --使用有限的内存资源
//     * @return 出队元素
//     */
//    public E poll(){
//        //空队列校验
//        if(this.size == 0){
//            return null;
//        }
//        //链表头结点
//        Node<E> firstNode = this.first;
//        //更改链表头结点
//        this.first = firstNode.next;
//        //队列元素计数
//        this.size--;
//        return firstNode.e;
//    }
//
//    /**
//     * 获取出队元素
//     * 时间复杂度：O(1) --出队元素即链表头元素
//     * 空间复杂度：O(1) --使用有限的内存资源
//     * @return 出队元素
//     */
//    public E peek(){
//        //空队列校验
//        if(this.size == 0){
//            return null;
//        }
//        return this.first.e;
//    }
//
//    //结点内部类
//    private static class Node<E>{
//        //上个结点
//        Node<E> prev;
//        //结点数据域
//        E e;
//        //下个结点
//        Node<E> next;
//
//        //单向链表构造函数
//        Node(E e,Node<E> next){
//            this.e = e;
//            this.next = next;
//        }
//
//        //双向链表构造函数
//        Node(Node<E> prev,E e,Node<E> next){
//            this.prev = prev;
//            this.e = e;
//            this.next = next;
//        }
//    }
//
//    /* **** 链式队列----->end **** */

//    /* **** 顺序队列----->start **** */
//
//    //队列元素
//    private Object[] objects;
//
//    //队列元素个数
//    private int size;
//
//    //队列初始化默认值
//    private static final Object[] DEFAULT_OBJECTS = {};
//
//    //队列初始化默认容量
//    private static int DEFAULT_CAPACITY = 10;
//
//    /**
//     * 队列的无参构造方法
//     */
//    public QueueUtil(){
//        this.objects = DEFAULT_OBJECTS;
//    }
//
//    /**
//     * 队列指定初始容量的构造方法
//     * @param capacity 容量
//     */
//    public QueueUtil(int capacity){
//        //校验输入容量
//        if(capacity > 0){
//            this.objects = new Object[capacity];
//        }else if(capacity == 0){
//            this.objects = DEFAULT_OBJECTS;
//        }else{
//            throw new IllegalArgumentException("Illegal capacity=" + capacity);
//        }
//    }
//
//    /**
//     * 获取队列元素个数
//     * @return 队列元素个数
//     */
//    public int size(){
//        return this.size;
//    }
//
//    /**
//     * 按照从队头到队尾的顺序打印队列
//     * @return 打印结果字符串，结果用"{}"包裹。
//     */
//    @Override
//    public String toString() {
//        return Arrays.toString(objects);
//    }
//
//    /**
//     * 数组扩容
//     * 时间复杂度：可能是O(n)。数组扩容底层调用的是native修饰的System.arraycopy()方法，是在JVM本地方法栈中执行的，源码是非Java语言实现的。
//     * 空间复杂度：O(n)  --扩容操作会创建新数组对象，数组长度与输入数据线性相关。
//     */
//    private void expand(){
//        //如果队列元素个数大于数组容量，则对数组扩容
//        if(this.size + 1 > this.objects.length){
//            //新数组长度的计算方法
//            int newLen = this.objects.length >= DEFAULT_CAPACITY ? this.objects.length * 3/2 : DEFAULT_CAPACITY;
//            //校验新数组长度
//            if(newLen >= Integer.MAX_VALUE){
//                throw new IllegalArgumentException("the length of array is too large:"+newLen);
//            }
//            //扩容
//            this.objects = Arrays.copyOf(this.objects,newLen);
//        }
//    }
//
//    /**
//     * 入队
//     * 时间复杂度：可能是O(n)。数组扩容底层调用的是native修饰的System.arraycopy()方法，是在JVM本地方法栈中执行的，源码是非Java语言实现的。
//     * 空间复杂度：O(n)  --扩容操作会创建新数组对象，数组长度与输入数据线性相关。
//     * @param e 元素
//     * @return 入队成功则返回true；否则返回false。
//     */
//    public boolean add(E e){
//        expand();
//        //在最后一个队列元素后添加新元素，并计数。
//        this.objects[this.size++] = e;
//        return true;
//    }
//
//    /**
//     * 出队
//     * 时间复杂度：O(n) --数组起始索引的元素出队后，整体要重新排列各元素在数组中的位置。
//     * 空间复杂度：O(1) --使用有限的内存资源。
//     * @return 队头元素
//     */
//    @SuppressWarnings("unchecked")
//    public E poll(){
//        //空队列校验
//        if(this.size == 0){
//            return null;
//        }
//        //出队元素
//        E e = null;
//        //获取数组长度
//        int arrLen = this.size;
//        //遍历队列元素数组
//        for(int i = 0 ; i < arrLen ; i++){
//            //删除队头元素
//            if(i == 0){
//                e = (E)this.objects[i];
//                this.objects[i] = null;
//                //队列只有一个元素直接初始化数组
//                if(this.size == 1){
//                    this.objects = DEFAULT_OBJECTS;
//                }
//            }else{
//                //将其它元素向队头重新分配索引
//                this.objects[i-1] = this.objects[i];
//                //队尾元素置空
//                if(i == arrLen-1) {
//                    this.objects[i] = null;
//                }
//            }
//        }
//        //遍历数组结束后再重新计数队列元素个数。
//        this.size--;
//        return e;
//    }
//
//    /**
//     * 获取队头元素
//     * 时间复杂度：O(1)  --数组根据索引查询的复杂度是O(1)
//     * 空间复杂度：O(1) --使用有限的内存资源。
//     * @return 队头元素
//     */
//    @SuppressWarnings("unchecked")
//    public E peek(){
//        //校验空队列
//        if(this.size == 0){
//            return null;
//        }
//        //数组第一个元素即队头元素
//        return (E)this.objects[0];
//    }
//
//    /* **** 顺序队列----->end **** */
}
