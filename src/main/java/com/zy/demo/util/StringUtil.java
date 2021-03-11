package com.zy.demo.util;

import java.util.Arrays;

/**
 * 字符串
 * @author zy
 */
public class StringUtil {

    //字符数组
    private char[] chars;

    //字符串长度
    private int size;

    //字符数组初始化默认值
    private static final char[] DEFAULT_CHARS = {};

    //字符数组默认长度
    private static final int DEFAULT_SIZE = 10;

    /**
     * 无参构造方法
     */
    public StringUtil(){
        this.chars = DEFAULT_CHARS;
    }

    /**
     * 指定初始容量的构造方法
     * @param capacity 初始容量
     */
    public StringUtil(int capacity){
        if(capacity == 0){
            this.chars = DEFAULT_CHARS;
        }else if(capacity > 0){
            this.chars = new char[capacity];
        }else{
            throw new IllegalArgumentException("Illegal capacity!");
        }
    }

    /**
     * 指定初始字符串的构造方法
     * @param chars 初始字符串
     */
    public StringUtil(char[] chars){
        this.chars = DEFAULT_CHARS;
        this.addLast(chars);
    }

    /**
     * 获取字符串的长度
     * @return 字符数组的元素个数
     */
    public int size(){
        return this.size;
    }

    /**
     * 打印字符串
     */
    public String toString() {
        if(this.size == 0){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < this.size ; i++){
            sb.append(chars[i]);
        }
        return sb.toString();
    }

    /**
     * 字符数组扩容
     * 时间复杂度：可能是O(n)  --底层是JVM本地方法实现的。
     * 空间复杂度：O(n)
     */
    private void expand(int newLen){
        if(newLen <= DEFAULT_SIZE){
            this.chars = Arrays.copyOf(this.chars,DEFAULT_SIZE);
        }else{
            newLen = newLen * 3/2;
            this.chars = Arrays.copyOf(this.chars, newLen);
        }
    }

    /**
     * 字符串连接
     * 时间复杂度：如果不考虑扩容的情况，则是O(n)。
     * 空间复杂度：如果不需要扩容，则是O(1)；否则是O(n)。
     * @param chars 要连接的字符数组
     * @return 连接后的新字符串
     */
    public StringUtil addLast(char[] chars){
        //校验入参字符串
        if(chars == null || chars.length == 0){
            return this;
        }
        //连接后新字符串长度
        int newLen = chars.length + this.size;
        //如果连接后的字符串长度大于字符数组长度，则扩容
        if(newLen > this.chars.length){
            this.expand(newLen);
        }
        //连接字符串
        for(int i = 0 ; i < chars.length ; i++){
            this.chars[this.size++] = chars[i];
        }
        //添加完成后重新初始化数组
        this.chars = Arrays.copyOf(this.chars,this.size);
        return this;
    }

    /**
     * 在指定索引处添加指定字符串
     * 时间复杂度：如不考虑数组扩容则是O(n)  --数组中间添加元素需要移动其它元素的位置。
     * 空间复杂度：如不考虑扩容则是O(1)
     * @param index 索引。规定起始索引为0。
     * @param chars 要添加的字符数组
     * @return 添加成功则返回true；否则返回false。
     */
    public StringUtil add(int index,char[] chars){
        //校验入参索引
        if(index < 0 || index >= this.size){
            return this;
        }
        //校验入参字符串
        if(chars == null){
            return this;
        }
        //空字符串处理
        if(chars.length == 0){
            return this;
        }
        //获取添加的子字符串长度
        int addLen = chars.length;
        //获取添加后的主字符串长度
        int newLen = this.size + addLen;
        //判断是否扩容
        if(newLen > this.chars.length){
            this.expand(newLen);
        }
        //提前把原数组指定索引处之后的元素向后移动，为添加的字符串腾出空间。
        for(int i = this.size-1 ; i >= index ; i--){
            this.chars[i+addLen] = this.chars[i];
        }
        //添加指定字符串
        for(int i = 0 ; i < addLen ; i++){
            this.chars[index+i] = chars[i];
            this.size++;
        }
        //添加完成后重新初始化数组
        this.chars = Arrays.copyOf(this.chars,this.size);
        return this;
    }

    /**
     * 判断原字符串中是否包含指定字符串(字符串查找)
     * 时间复杂度：O(mn) --两层嵌套for循环，其中第二层for循环遍历指定字符串的长度m。
     * 空间复杂度：O(n)  --创建Integer数组存储结果，如果原字符串中的字符不是完全一样的，则此复杂度很小，可以看作O(1)。
     * @param chars 指定字符串
     * @return 如果存在返回true；否则返回false。
     */
    public boolean contains(char[] chars){
        if(chars == null || chars.length == 0){
            return false;
        }
        if(chars.length > this.size){
            return false;
        }
        int[] ints = this.getIndexes(chars);
        if(ints != null && ints.length > 0){
            return true;
        }
        return false;
    }

    /**
     * 判断原字符串中是否包含指定字符串，并返回出现的索引列表。
     * 时间复杂度：O(mn) --两层嵌套for循环，其中第二层for循环遍历指定字符串的长度m。
     * 空间复杂度：O(n)  --创建Integer数组存储结果，如果原字符串中的字符不是完全一样的，则此复杂度很小，可以看作O(1)。
     * @param chars 指定字符串
     * @return 如果存在返回指定字符串首字符在原字符串出现的位置；否则返回空数组。
     */
    private int[] getIndexes(char[] chars){
        //初始化数组记录结果
        int[] ints = {};
        //结果数组索引
        int index = 0;
        //结果数组长度
        int len = 0 ;
        //遍历原字符串
        for(int i = 0 ; i < this.size ; i++){
            //如果在原字符串中查找到指定字符串的首字符
            if(this.chars[i] == chars[0]){
                //首字符后的元素个数不足时，直接退出。
                if(this.size - i < chars.length){
                    break;
                }
                //计数器
                int count = 0;
                //遍历指定字符串
                for(int j = 0 ; j < chars.length ; j++){
                    //逐个比较两个字符串对应位置的字符
                    if(this.chars[i+j] == chars[j]){
                        count++;
                    }
                }
                //如果字符相等个数等于指定字符串的长度，则证明原字符串包含指定字符串。
                if(count == chars.length){
                    //索引大于数组长度，则扩容
                    if(index >= ints.length){
                        int newLen = ints.length == 0 ? 10 : ints.length * 3/2;
                        ints = Arrays.copyOf(ints,newLen);
                    }
                    ints[index++] = i;
                    len++;
                }
            }
        }
        //重新初始化数组
        ints = Arrays.copyOf(ints,len);
        return ints;
    }

    /**
     * 将原字符串中指定字符串替换为新的字符串(字符串删除)
     * 时间复杂度：如果不考虑扩容是O(mn)
     * 空间复杂度：O(n)  --创建数组存储结果
     * @param newChars 要替换的新字符串
     * @param oldChars 要替换的旧字符串
     * @return 替换成功返回替换后的新字符串；否则返回原字符串。
     */
    public StringUtil replace(char[] oldChars,char[] newChars){
        //原字符串为空不处理
        if(this.size == 0){
            return this;
        }
        //要替换的旧字符串为空或者长度大于原字符串不处理
        if(oldChars == null || oldChars.length == 0 || oldChars.length > this.size){
            return this;
        }
        //要替换的新字符串为空不处理
        if(newChars == null){
            return this;
        }
        //查询要替换的旧字符串是否存在，并获取首字符索引列表。
        int[] ints = this.getIndexes(oldChars);
        //未匹配到指定字符串直接返回原字符串。
        if(ints == null || ints.length == 0){
            return this;
        }
        //如果要替换的新字符串是空数组，则为删除操作
        if(newChars.length == 0){
            //遍历存储首字符索引的列表
            for(int i = 0 ; i < ints.length ; i++){
                //删除处后元素索引
                int afterIndex = 0;
                //先把删除处后的元素向数组头移动
                for(int j = ints[i] ; j < this.size ; j++){
                    //数组越界校验
                    afterIndex = j + oldChars.length;
                    if(afterIndex >= this.size){
                        break;
                    }
                    //删除操作索引向前移动
                    this.chars[j] = this.chars[afterIndex];
                }
                //数组长度变更(匹配1次删除oldChars个元素)
                this.size = this.size - oldChars.length;
                //重新初始化数组
                this.chars = Arrays.copyOf(this.chars,this.size);
                //删除成功后同时移动索引列表剩余索引
                for(int k = i+1 ; k < ints.length ; k++){
                    ints[k] = ints[k] - oldChars.length;
                }
            }
        }else{
            //否则是字符串替换操作，有三种情况：
            //1、替换+删除
            if(oldChars.length > newChars.length){
                //删除元素个数
                int delNum = oldChars.length - newChars.length;
                //遍历首字符索引列表
                for(int i = 0 ; i < ints.length ; i++){
                    //起始索引
                    int index = ints[i];
                    //遍历要替换的字符串
                    for(int j = 0 ; j < newChars.length ; j++){
                        //替换
                        this.chars[index++] = newChars[j];
                    }
                    //删除元素前先移动其它元素索引
                    for(int k = index ; k < this.size ; k++){
                        //数组越界校验
                        if(k+delNum >= this.size){
                            break;
                        }
                        this.chars[k] = this.chars[k+delNum];
                    }
                    //数组长度变更
                    this.size = this.size - delNum;
                    //删除后重新初始化数组
                    this.chars = Arrays.copyOf(this.chars,this.size);
                    //删除成功后同时移动索引列表剩余索引
                    for(int m = i+1 ; m < ints.length ; m++){
                        ints[m] = ints[m] - delNum;
                    }
                }
            }else if(oldChars.length < newChars.length){
                //2、替换+添加
                //添加元素个数
                int addNum = newChars.length - oldChars.length;
                //遍历要替换的索引列表
                for(int i = 0 ; i < ints.length ; i++){
                    //起始索引
                    int index = ints[i];
                    //添加的起始索引
                    int jIndex = 0;
                    //替换
                    for(int j = 0 ; j < oldChars.length ; j++){
                        this.chars[index++] = newChars[j];
                        jIndex = j;
                    }
                    //替换完成后的下一个索引为添加的起始索引
                    jIndex++;
                    //判断是否需要扩容
                    if(this.size + addNum > this.chars.length){
                        this.expand(this.size+addNum);
                    }
                    //添加前移动其它元素位置
                    for(int k = this.size-1 ; k >= index ; k--){
                        this.chars[k+addNum] = this.chars[k];
                    }
                    //添加
                    for(int j = jIndex ; j < newChars.length ; j++){
                        this.chars[index++] = newChars[j];
                    }
                    //数组长度变更
                    this.size = this.size + addNum;
                    //添加成功后重新初始化数组
                    this.chars = Arrays.copyOf(this.chars,this.size);
                    //索引列表位置移动
                    for(int m = i+1 ; m < ints.length ; m++){
                        ints[m] = ints[m] + addNum;
                    }
                }
            }else{
                //3、完全替换
                //遍历存储首字符索引的列表
                for(int i = 0 ; i < ints.length ; i++){
                    //起始索引
                    int index = ints[i];
                    //逐个替换字符
                    for(int j = 0 ; j < newChars.length ; j++){
                        this.chars[index++] = newChars[j];
                    }
                }
            }
        }
        return this;
    }

    /**
     * 指定两个字符串，查找它们的最大公共字符串。
     * 时间复杂度：O(m²n)  --三层嵌套for循环，原字符串的长度是n，指定字符串的长度是m
     * 空间复杂度：O(n)  --新建数组对象存储结果
     * @return 最大公共字符串
     */
    public StringUtil findMaxCommon(char[] charsA,char[] charsB){
        if(charsA == null || charsA.length == 0 || charsB == null || charsB.length == 0){
            return null;
        }
        //最大公共字符串
        this.chars = null;
        //最大公共字符串元素个数
        int maxNum = 0;
        //遍历字符串A
        for(int i = 0 ; i < charsA.length ; i++){
            //遍历字符串B
            for(int j = 0 ; j < charsB.length ; j++){
                //如果字符串A当前字符等于字符串B的首个字符
                if(charsA[i] == charsB[j]){
                    //获取字符串A的当前索引
                    int index = i;
                    //计数器
                    int count = 0;
                    //缓存
                    char[] tmp = new char[Math.min(charsA.length,charsB.length)];
                    //遍历字符串B
                    for(int k = j ; k < charsB.length ; k++){
                        if(index >= charsA.length){
                            break;
                        }
                        //逐个比较两个字符串对应位置的字符
                        if(charsA[index++] == charsB[k]){
                            //记录结果
                            tmp[count++] = charsB[k];
                        }else{
                            break;
                        }
                    }
                    //赋值
                    if(count > maxNum){
                        maxNum = count;
                        this.chars = tmp;
                    }
                }
            }
        }
        //重新初始化结果数组
        if(maxNum > 0){
            this.size = maxNum;
            this.chars = Arrays.copyOf(this.chars,this.size);
        }
        return this;
    }

    /**
     * 字符串翻转
     * 时间复杂度：O(n/2)=O(n)  --遍历字符串
     * 空间复杂度：O(1)  --使用有限的内存资源
     * @return 翻转后的字符串
     */
    public StringUtil reverse(){
        if(this.size == 0){
            return this;
        }
        //遍历字符串
        for(int i = 0 ; i < this.size/2 ; i++){
            //缓存当前字符
            char tmp = this.chars[i];
            //首尾交换字符
            this.chars[i] = this.chars[this.size-i-1];
            this.chars[this.size-i-1] = tmp;
        }
        return this;
    }
}
