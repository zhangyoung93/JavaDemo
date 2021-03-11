package com.zy.demo.util;

import java.util.Arrays;

/**
 * 链表
 * @author zy
 */
public class LinkedListUtil<E> {

    //链表头结点
    private Node<E> first;

    //链表尾结点
    private Node<E> last;

    //链表中结点个数
    private int size;

    /**
     * 链表的无参构造函数
     */
    public LinkedListUtil(){

    }

    /**
     * 链表初始化头结点的构造函数
     * @param e 元素
     */
    public LinkedListUtil(E e){
        //链表中只有1个结点，头尾结点相同
        this.first = this.last = new Node<>(e,null);
        size++;
    }

    /**
     * 获取链表长度
     * @return 链表中结点的个数
     */
    public int size(){
        return this.size;
    }

    /**
     * 按照从头到尾的顺序打印链表元素
     * @return 返回结果用"{}"包裹，多个元素之间","分隔
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<E> node = this.first;
        while (node != null){
            sb.append(node.e).append(",");
            node = node.next;
        }
        return sb.length() > 0 ? "{"+sb.deleteCharAt(sb.length()-1).toString()+"}" : "{}";
    }

    /**
     * 判断链表中指定索引处的结点是否存在。规定：链表索引取值从0到链表长度减1。
     * 复杂度：O(1)
     * @param index 索引
     * @return 存在则返回true；否则返回false。
     */
    public boolean existIndex(int index){
        return this.size > 0 && index >= 0 && index < this.size;
    }

    /**
     * 判断链表中指定元素的结点是否存在
     * 时间复杂度：O(n) --遍历1次链表查找指定元素。
     * 空间复杂度：O(n) --新建StringBuilder对象存储结果，结果与输入数据量线性相关。
     * @param e 元素
     * @return 存在则返回true；否则返回false。
     */
    public boolean existElement(E e){
        return this.size > 0 && getIndex(e) != null;
    }

    /**
     * 获取链表中指定索引处的元素
     * 时间复杂度：O(n) --遍历1次链表。
     * 空间复杂度：O(1) --使用有限的变量资源。
     * @param index 索引
     * @return 元素
     */
    public E getElement(int index){
        if(!existIndex(index)){
            return null;
        }
        //计数器
        int count = 0;
        Node<E> node = this.first;
        while (node != null){
            if(count == index){
                return node.e;
            }
            node = node.next;
            count++;
        }
        return null;
    }

    /**
     * 获取链表中指定元素所存在的索引列表
     * 时间复杂度：O(n) --遍历1次链表。
     * 空间复杂度：O(n) --新建StringBuilder对象存储结果，结果与输入数据量线性相关。
     * @param e 元素
     * @return 索引列表
     */
    public int[] getIndex(E e){
        //索引
        int index = 0;
        //临时变量用于收集未知个数的索引值
        StringBuilder sb = new StringBuilder();
        //获取头结点
        Node<E> node = this.first;
        //头结点不为空时循环获取下个结点
        while (node != null){
            //在链表中找到指定元素时，记录索引值。
            if(node.e == e){
                sb.append(index).append(",");
            }
            node = node.next;
            index++;
        }
        //String[]转换int[]
        if(sb.length() > 0){
            String indexStr = sb.deleteCharAt(sb.length()-1).toString();
            return Arrays.stream(indexStr.split(",")).mapToInt(Integer::parseInt).toArray();
        }
        return null;
    }

    /**
     * 在指定索引处添加指定结点
     * 时间复杂度：O(n) --遍历1次链表。
     * 空间复杂度：O(1) --使用有限的变量资源。
     * @param index 索引
     * @param e 元素
     * @return 添加成功则返回true；否则返回false。
     */
    public boolean add(int index,E e){
        //如果是空链表，允许在第一个位置添加结点
        if(this.size == 0 && index == 0){
            this.first = this.last = new Node<>(e,null);
            this.size++;
            return true;
        }
        //校验输入的索引
        if(!existIndex(index)){
            return false;
        }
        //计数器
        int count = 0;
        //缓存上一个结点
        Node<E> prevNode = null;
        //从头结点开始遍历
        Node<E> node = this.first;
        //这里头结点其实肯定不为null
        while (node != null){
            //按照计数器查找指定索引处的结点
            if(count == index){
                //在链表头结点插入时，新插入的结点为链表的头结点，并指向原索引处的结点。。
                if(index == 0){
                    //头结点更改
                    this.first = new Node<>(e,node);
                    this.size++;
                    return true;
                }else{
                    //在链表非头结点插入时，上个结点要指向新插入的结点，新插入的结点要指向原索引处的结点。
                    prevNode.next = new Node<>(e, node);
                    this.size++;
                    return true;
                }
            }
            prevNode = node;
            node = node.next;
            count++;
        }
        return false;
    }

    /**
     * 在链表尾添加元素
     * 时间复杂度：O(1) --只需修改一次链表尾结点的指针。
     * 空间复杂度：O(1) --使用有限的变量资源。
     * @param e 元素
     * @return 添加成功则返回true；否则返回false。
     */
    public boolean addLast(E e){
        //如果链表是空的，则新插入的结点是头结点也是尾结点。
        if(this.size == 0){
            this.first = this.last = new Node<>(e,null);
        }else{
            //取出链表尾结点
            Node<E> node = this.last;
            //原尾结点指针指向新添加的结点，新添加的结点作为链表的新尾结点。
            this.last = node.next = new Node<>(e,null);
        }
        this.size++;
        return true;
    }

    /**
     * 修改链表中指定索引处的元素
     * 时间复杂度：O(n) --遍历1次链表查找指定元素。
     * 空间复杂度：O(1) --使用有限的变量资源。
     * @param index 索引
     * @param e 元素
     * @return 修改成功则返回true；否则返回false。
     */
    public boolean mod(int index,E e){
        //判断链表中是否存在索引对应的结点
        if(!existIndex(index)){
            return false;
        }
        //计数器
        int count = 0;
        //获取链表头结点
        Node<E> node = this.first;
        //从头遍历链表
        while (node != null){
            if(index == count){
                node.e = e;
                return true;
            }
            node = node.next;
            count++;
        }
        return false;
    }

    /**
     * 替换链表中的指定元素
     * 时间复杂度：O(n) --遍历1次链表查找指定元素。
     * 空间复杂度：O(1) --使用有限的变量资源。
     * @param oldElement 链表中原元素
     * @param newElement 链表中新元素
     * @return 替换成功则返回true；否则返回false。
     */
    public boolean replace(E oldElement,E newElement){
        if(this.size == 0){
            return false;
        }
        //替换操作计数器
        int count = 0;
        Node<E> node = this.first;
        while(node != null){
            if(node.e == oldElement){
                node.e = newElement;
                count++;
            }
            node = node.next;
        }
        return count > 0;
    }

    /**
     * 删除指定索引处的元素
     * 时间复杂度：O(n) --遍历1次链表查找指定元素。
     * 空间复杂度：O(1) --使用有限的变量资源。
     * @param index 索引
     * @return 删除成功则返回true；否则返回false。
     */
    public boolean remove(int index){
        //校验索引规则
        if(!existIndex(index)){
            return false;
        }
        //索引计数器
        int count = 0;
        //缓存上一个结点
        Node<E> prevNode = null;
        //获取头结点
        Node<E> node = this.first;
        //从头开始遍历链表
        while(node != null){
            //索引为头结点时
            if(index == 0){
                //如果头结点存在下一个结点，则更换头结点
                if(node.next != null){
                    this.first = node.next;
                }else{
                    //链表只有1个结点
                    this.first = this.last = null;
                }
                size--;
                return true;
            }else{
                //索引不为头结点时，匹配链表的索引
                if(count == index){
                    //上个结点指向删除结点的下个结点
                    prevNode.next = node.next;
                    size--;
                    return true;
                }
            }
            prevNode = node;
            node = node.next;
            count++;
        }
        return false;
    }

    /**
     * 删除链表中指定元素对应的结点
     * 时间复杂度：O(n) --遍历1次链表。
     * 空间复杂度：O(1) --使用有限的变量。
     * @param e 指定元素
     * @return 删除结点的个数
     */
    public int removeAll(E e){
        if(this.size == 0){
            return 0;
        }
        //删除计数器
        int count = 0;
        //缓存上一个结点
        Node<E> prevNode = null;
        //从头开始遍历链表
        Node<E> node = this.first;
        while(node != null){
            if(node.e == e){
                //链表只有一个结点
                if(this.size == 1){
                    this.first = this.last = null;
                }else{
                    //删除头结点
                    if(node == this.first){
                        this.first = node.next;
                    }else if(node == this.last){
                        //删除尾结点
                        prevNode.next = null;
                        this.last = prevNode;
                    }else{
                        //删除中间结点，注意保留上个结点的缓存变量
                        prevNode.next = node.next;
                        node = prevNode;
                    }
                }
                size--;
                count++;
            }
            prevNode = node;
            node = node.next;
        }
        return count;
    }

    /**
     * 翻转指定链表
     * 时间复杂度：O(2n)=O(n) --顺序执行遍历2次链表；链表添加结点时间复杂度是O(1)，不计算在复杂度内。
     * 空间复杂度：O(n) --新建单链表对象。
     * @param linkList 指定链表
     * @return 翻转后的链表
     */
    public LinkedListUtil<E> reverse(LinkedListUtil<E> linkList){
        //校验指定链表
        if(linkList == null || linkList.size() == 0){
            return null;
        }
        //获取指定链表头结点
        Node<E> node = linkList.first;
        //缓存上一个结点
        Node<E> prev = null;
        //从头遍历链表，利用双链表实现翻转
        while(node != null){
            node.prev = prev;
            prev = node;
            node = node.next;
        }
        //新建单链表对象
        LinkedListUtil<E> newLinkedListUtil = new LinkedListUtil<>();
        //获取指定链表尾结点，从尾到头向新的单链表添加结点
        node = linkList.last;
        while(node != null){
            newLinkedListUtil.addLast(node.e);
            node = node.prev;
        }
        return newLinkedListUtil;
    }

    /**
     * 翻转指定链表，且指定参数k个结点翻转一次(n能被链表长度整除)。
     * 时间复杂度：O(n) --虽然嵌套循环遍历2次链表，但是内层循环次数为有限值k，并且链表在尾结点后添加元素的时间复杂度是O(1)，不计算在复杂度内。
     * 空间复杂度：O(n) --新建单链表对象。
     * @param linkList 指定链表
     * @param k 翻转一次链表需要的结点数，正整数。
     * @return 翻转后的链表
     */
    public LinkedListUtil<E> reverse(LinkedListUtil<E> linkList,int k){
        //校验指定链表
        if(linkList == null || linkList.size() == 0){
            return null;
        }
        //翻转结点个数小于1不处理
        if(k < 1){
            return null;
        }
        //判断翻转结点个数是否能被链表长度整除
        if(linkList.size() % k > 0){
            return null;
        }
        //计数器
        int count = 0;
        //新建单链表对象
        LinkedListUtil<E> newLinkedListUtil = new LinkedListUtil<>();
        //获取指定链表头结点
        Node<E> node = linkList.first;
        //缓存上一个结点
        Node<E> prev = null;
        //从头遍历链表，利用双链表实现翻转
        while(node != null){
            //考虑第一次循环是头结点不为空的情况，计数器先加1
            count++;
            node.prev = prev;
            //如果循环到指定结点数时，从尾到头翻转子链表
            if(count % k == 0){
                //子链表计数器，用于每次只向新链表添加k个结点。
                int tmp = 0;
                //缓存当前node，避免影响第一层while循环的条件
                Node<E> nodeTmp = node;
                while(nodeTmp != null && tmp != k){
                    newLinkedListUtil.addLast(nodeTmp.e);
                    nodeTmp = nodeTmp.prev;
                    tmp++;
                }
            }
            prev = node;
            node = node.next;
        }
        return newLinkedListUtil;
    }

    /**
     * 如果链表长度为奇数，利用快慢指针获取链表中间结点的元素。
     * 时间复杂度：O(n/2)=O(n) --遍历1/2次链表
     * 空间复杂度：O(1) --使用有限的变量资源。
     * @param linkList 指定链表
     * @return 元素
     */
    public E getMiddleElement(LinkedListUtil<E> linkList){
        if(linkList == null){
            return null;
        }
        //判断链表长度是否为奇数
        if(linkList.size() % 2 == 0){
            return null;
        }
        //慢指针结点
        Node<E> slowNode;
        //快慢指针初始化为链表头结点
        Node<E> fastNode = slowNode = linkList.first;
        //从头开始按照快指针遍历链表，当快指针的下个结点为空时，证明链表遍历完成。
        while(fastNode != null && fastNode.next != null){
            //慢指针一次跳转1个结点
            slowNode = slowNode.next;
            //块指针一次跳转2个结点
            fastNode = fastNode.next.next;

        }
        //慢指针即中间结点
        return slowNode.e;
    }

    /**
     * 判断指定链表是否有环
     * 时间复杂度：O(n) --遍历1次链表。
     * 空间复杂度：O(1) --使用有限的变量资源。
     * @param linkList 指定链表
     * @return 如果有环则返回true；否则返回false。
     */
    public boolean existLoop(LinkedListUtil<E> linkList){
        if(linkList == null){
            return false;
        }
        if(linkList.size() == 0){
            return false;
        }
        //快慢指针初始结点
        Node<E> fastNode;
        //慢指针结点
        Node<E> slowNode;
        //快慢指针初始结点
        fastNode = slowNode = linkList.first;
        //以慢指针遍历链表
        while(slowNode != null){
            //如果快指针的下个结点为空，则说明链表无环。
            if(fastNode.next == null){
                break;
            }
            //不考虑头结点相遇的情况
            fastNode = fastNode.next.next;
            slowNode = slowNode.next;
            //如果快指针与慢指针相遇，则说明链表中有环。
            if(fastNode == slowNode){
                return true;
            }
        }
        return false;
    }

    /**
     * 结点内部类
     * @param <E> 元素
     */
    private static class Node<E>{
        //数据域(元素)
        E e;
        //上个结点
        Node<E> prev;
        //下个结点
        Node<E> next;

        //单向链表结点构造函数
        Node(E e,Node<E> next){
            this.e = e;
            this.next = next;
        }

        //双向链表结点构造函数
        Node(Node<E> prev,E e,Node<E> next){
            this.prev = prev;
            this.e = e;
            this.next = next;
        }
    }
}
