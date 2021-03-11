package com.zy.demo.util;

/**
 * 栈
 * 1、通过数组实现顺序栈
 * 2、通过链表实现链式栈
 * @param <E> 栈内元素类型
 * @author zhangyoung93
 */
public class StackUtil<E> {

    /*****  通过链表实现链式栈----->start    *****/

    //栈顶
    private Node<E> first;
    //栈底
    private Node<E> last;
    //栈内的元素个数
    private int size;

    /**
     * 无参构造函数
     */
    public StackUtil(){

    }

    /**
     * 初始化栈顶的构造函数
     * @param e 元素
     */
    public StackUtil(E e){
        this.first = this.last = new Node<>(e,null);
        this.size++;
    }

    /**
     * 获取栈内元素的个数
     * @return 元素个数
     */
    public int size(){
        return this.size;
    }

    /**
     * 按照从栈顶到栈底的顺序打印栈内元素
     * 时间复杂度：O(n) --遍历一次栈
     * 空间复杂度：O(n) --新建StringBuilder对象存储打印结果，结果与输入数据量线性相关。
     * @return 打印栈内元素的结果
     */
    @Override
    public String toString() {
        //打印结果缓存
        StringBuilder sb = new StringBuilder();
        //获取栈顶元素
        Node<E> node = this.first;
        //打印结果通过"{}"包裹
        sb.append("{");
        //从栈顶向栈底遍历栈
        while (node != null){
            sb.append(node.e).append(",");
            node = node.next;
        }
        //最后一个逗号处理
        if (sb.toString().contains(",")) {
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append("}");
        return sb.toString();
    }

    /**
     * 压栈
     * 时间复杂度：O(1) --链表头添加元素，只需修改一次指针。
     * 空间复杂度：O(1) --消耗有限的内存资源。
     * @param e 元素
     * @return 压栈成功返回true；否则返回false。
     */
    public boolean push(E e){
        //空栈时初始化栈顶和栈底。
        if(this.size == 0){
            //栈顶元素等于栈底元素
            this.first = this.last = new Node<>(e,null);
        }else{
            //栈内有元素时，把链表头作为栈顶，栈顶元素增删查的效率都很高。
            Node<E> firstNode = this.first;
            this.first = new Node<>(e,firstNode);
        }
        this.size++;
        return true;
    }

    /**
     * 出栈
     * 时间复杂度：O(1) --链表头查询与删除操作都是O(1)的复杂度
     * 空间复杂度：O(1) --消耗有限的内存资源。
     * @return 出栈的元素
     */
    public E pop(){
        //出栈元素
        E element;
        //空栈校验
        if(this.size == 0){
            return null;
        }else if(this.size == 1){
            //栈内只有1个元素时，出栈后将栈顶与栈底置为null。
            element = this.first.e;
            this.first = this.last = null;
        }else{
            //栈内有多个元素时，将栈顶元素出栈，并将下一个元素作为新栈顶。
            element = this.first.e;
            this.first = this.first.next;
        }
        this.size--;
        return element;
    }

    /**
     * 修改栈顶元素
     * 时间复杂度：O(1) --查询链表头结点的复杂度是O(1)
     * 空间复杂度：O(1) --消耗有限的内存资源
     * @param e 修改后的元素
     * @return 修改成功返回true；否则返回false。
     */
    public boolean modPop(E e){
        //空栈校验
        if(this.size == 0){
            return false;
        }
        //直接修改栈顶元素的数据域
        this.first.e = e;
        return true;
    }

    /**
     * 查找栈顶元素
     * 时间复杂度：O(1) --查询链表头结点的复杂度是O(1)
     * 空间复杂度：O(1) --消耗有限的内存资源。
     * @return 栈顶元素
     */
    public E getPop(){
        //空栈校验
        if(this.size == 0){
            return null;
        }
        //直接返回链表头结点数据域
        return this.first.e;
    }

    /**
     * 查找指定元素在栈中离栈顶最近的位置。规定栈顶的索引为0；
     * @param e 元素
     * @return 指定元素在栈中离栈顶最近的索引。如果指定元素不在栈中，则返回-1。
     */
    public int getIndex(E e){
        //空栈校验
        if(this.size() == 0){
            return -1;
        }
        //索引计数器
        int index = 0;
        //获取栈顶元素
        Node<E> node = this.first;
        //从栈顶向栈底遍历
        while(node != null){
            //判断元素是否存在
            if(e == node.e){
                return index;
            }
            index++;
            node = node.next;
        }
        return -1;
    }

    /**
     * 结点内部类
     */
    private static class Node<E>{
        //数据域
        private E e;
        //上个结点
        private Node<E> prev;
        //下个结点
        private Node<E> next;
        //结点构造函数
        Node(E e,Node<E> next){
            this.e = e;
            this.next = next;
        }
    }

    /*****  通过链表实现链式栈----->end    *****/

    /**
     * 括号匹配问题：给定一个只包括'{'、'}'、'('、')'、'['、']'的字符串，判断字符串中左括号与相同类型的右括号是否匹配，且左括号以正确的顺序匹配。
     * 时间复杂度：O(n) --遍历字符串中的字符。
     * 空间复杂度：O(n) --创建栈对象存储字符，存储数据量与输入数据量线性相关。
     * @param str 待处理包含括号的字符串。
     * @return 满足条件正确匹配则返回true；否则返回false。例如"{[()]}"满足条件。
     */
    public static boolean bracketsMatch(String str){
        //输入字符串校验
        if(str == null || str.isEmpty() || str.length() % 2 != 0){
            return false;
        }
        //创建栈对象
        StackUtil<Character> stackUtil = new StackUtil<>();
        //获取字符串长度
        int strLen = str.length();
        //遍历字符串
        for(int i = 0 ; i < strLen ; i++){
            char c = str.charAt(i);
            //因为左括号要以正确顺序匹配，所以把左括号压栈
            if(c == '{' || c == '[' || c == '('){
                stackUtil.push(c);
            }else{
                //获取栈顶的左括号
                Character top = stackUtil.pop();
                //栈内没有元素，说明左括号未按照正确顺序匹配
                if(top == null){
                    return false;
                }
                //左括号按照正确顺序匹配，说明可匹配的左括号与右括号在字符串中是位置对称的。
                if(top == '{' && c == '}' || top == '[' && c == ']' || top == '(' && c == ')'){
                    continue;
                }
                return false;
            }
        }
        //栈中左括号都被匹配，说明字符串满足括号匹配条件。
        return stackUtil.size() == 0;
    }


    /**
     * 浏览器前进后退问题。
     * 时间复杂度：O(1)  --指令order的值是常数，栈的压栈与出栈复杂度都是O(1)。
     * 空间复杂度：O(1)  --消耗有限的内存资源。
     * @param order 指令。规定：order是非0整数，正数表示前进几页，负数表示后退几页。例如order=2表示前进2页；order=-3表示后退3页。
     * @param back 后退栈，按照先后顺序存储可后退的网页，栈顶规定为当前浏览的网页ID。
     * @param forward 前进栈，按照先后顺序存储可前进的网页。
     * @return 当前浏览的网页ID，规定网页ID为正整数。如果处理失败则返回0。
     */
    public static int forwardOrBack(int order,StackUtil<Integer> back,StackUtil<Integer> forward){
        //校验指令
        if(order == 0){
            return 0;
        }
        //校验可后退的网页ID数组，至少存储当前浏览网页ID
        if(back == null || back.size() <= 1){
            return 0;
        }
        //校验前进栈
        if(forward == null){
            return 0;
        }
        //当前浏览的网页ID
        int currentId = 0;
        //如果输入指令是正整数，则是前进操作。
        if(order > 0){
            //前进栈为空，不允许操作
            if(forward.size() > 0){
                //逐个前进
                for(int i = 0 ; i < order ; i++){
                    //先从前进栈把可前进的网页出栈，再入栈到后退栈，该网页变为可后退。
                    Integer id = forward.pop();
                    if(id != null){
                        currentId = id;
                        back.push(currentId);
                    }else{
                        //栈顶元素是空，直接退出循环，不浪费时间。
                        break;
                    }
                }
            }
        }else{
            //如果输入指令是负数，则是后退操作，逐个后退
            for (int i = 0 ; i < Math.abs(order) ; i++){
                //先从后退栈把当前网页出栈，再入栈到前进栈，该网页变为可前进。
                Integer id = back.pop();
                if(id != null){
                    forward.push(id);
                    //注意后退栈要取最新的栈顶元素作为当前浏览网页
                    if(back.getPop() != null){
                        currentId = back.getPop();
                    }
                }else{
                    //栈顶元素是空，直接退出循环，不浪费时间。
                    break;
                }
            }
        }
        return currentId;
    }

//    /*****  通过数组实现顺序栈----->start    *****/
//
//    //声明对象数组
//    private Object[] objects;
//
//    //栈的默认初始化结果
//    private static final Object[] DEFAULT_OBJECTS = {};
//
//    //栈的默认容量
//    private static final int DEFAULT_CAPACITY = 10;
//
//    //栈的元素个数
//    private int size;
//
//    //无参构造函数
//    public StackUtil(){
//        this.objects = DEFAULT_OBJECTS;
//    }
//
//    /**
//     * 指定初始容量的构造函数
//     * @param capacity 初始容量
//     */
//    public StackUtil(int capacity){
//        if(capacity > 0){
//            this.objects = new Object[capacity];
//        }else if(capacity == 0){
//            this.objects = DEFAULT_OBJECTS;
//        }else{
//            throw new IllegalArgumentException("Illegal Capacity:" + capacity);
//        }
//    }
//
//    /**
//     * 获取栈内的元素个数
//     * @return 栈内的元素个数
//     */
//    public int size(){
//        return this.size;
//    }
//
//    /**
//     * 校验规则
//     * @param index 数组索引
//     */
//    private void valid(int index){
//        //数组越界校验
//        if(index < 0 || this.size > 0 && index > this.size - 1){
//            throw new IndexOutOfBoundsException("Index:" + index);
//        }
//    }
//
//    /**
//     * 打印栈内元素
//     */
//    @Override
//    public String toString() {
//        return Arrays.toString(this.objects);
//    }
//
//    /**
//     * 压栈
//     * 时间复杂度：可能是O(n)。数组扩容底层调用的是native修饰的System.arraycopy()方法，是在JVM本地方法栈中执行的，源码是非Java语言实现的。
//     * 空间复杂度：O(n) --新建数组对象，数组大小与输入数据量n线性相关。
//     * @param e 元素
//     * @return 添加元素成功返回true；否则返回false。
//     */
//    public boolean push(E e){
//        //原数组中无元素，则新建默认容量的数组
//        if(this.objects == DEFAULT_OBJECTS){
//            this.objects = new Object[DEFAULT_CAPACITY];
//        }else{
//            //添加元素后数组长度大于数组容量，则扩容。
//            if(this.size + 1 > this.objects.length){
//                //先校验容量是否超过Integer的最大值，控制JVM内存使用量。
//                if(this.size + 1 >= Integer.MAX_VALUE){
//                    throw new OutOfMemoryError("The size of stack is too large!");
//                }
//                //扩容值规定：如果原数组长度小于默认容量，则扩容到默认容量；否则扩容到原数组长度的1.5倍。
//                int newLength = this.objects.length < DEFAULT_CAPACITY ? DEFAULT_CAPACITY : (int) (1.5 * this.objects.length);
//                //重新实例化扩容后的数组
//                this.objects = Arrays.copyOf(this.objects, newLength);
//            }
//        }
//        //新添加的元素赋值到原数组尾部，不需要考虑索引的重排列。
//        this.objects[this.size++] = e;
//        return true;
//    }
//
//    /**
//     * 出栈
//     * 时间复杂度：O(1) --数组根据索引取值。
//     * 空间复杂度：O(1) --使用有限的变量资源。
//     * @return 出栈的元素
//     */
//    public E pop(){
//        //获取栈顶元素
//        E popElement = get(this.size-1);
//        //栈顶元素置为null
//        this.objects[--this.size] = null;
//        //size--可能会导致数组越界
//        return popElement;
//    }
//
//    /**
//     * 在栈中从栈底到栈顶查找指定索引处的元素
//     * 时间复杂度：O(1) --数组根据索引查找元素。
//     * 空间复杂度：O(1) --使用有限的变量资源。
//     * @param index 索引
//     * @return 元素
//     */
//    @SuppressWarnings("unchecked")
//    public E get(int index){
//        valid(index);
//        return (E)this.objects[index];
//    }
//
//    /**
//     * 修改栈顶元素
//     * 时间复杂度：O(1) --数组根据索引查找元素。
//     * 空间复杂度：O(1) --使用有限的变量资源。
//     * @param e 修改后的元素
//     * @return 修改成功返回true；否则返回false。
//     */
//    public boolean modPop(E e){
//        //注意不能对size做赋值。
//        this.objects[this.size-1] = e;
//        return true;
//    }
//
//    /*****  通过数组实现顺序栈----->end    *****/
}
