package com.zy.demo.util;

import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 树
 * @author zhangyang7
 */
public class TreeUtil<E> {

    /*  二叉树(二叉搜索树)-----start    */

    //根结点
    private TreeNode<E> root;

    //树的结点个数
    private int size;

    /**
     * 无参构造函数
     */
    public TreeUtil(){

    }

    /**
     * 指定根结点数据的构造函数
     * @param e 根结点数据
     */
    public TreeUtil(E e){
        this.root = new TreeNode<>(e,null,null);
        this.size++;
    }

    /**
     * 获取树的结点个数
     * @return 树的结点个数
     */
    public int size(){
        return this.size;
    }

    /**
     * 按照层序打印树
     * @return 打印结果
     */
    @Override
    public String toString() {
        if(this.size == 0){
            return "";
        }
        return Arrays.toString(layerOrderTraversal());
    }

    /**
     * 前序遍历
     * 时间复杂度：O(n) --全结点访问
     * 空间复杂度：O(n) --新建队列存储遍历结果
     * @return 遍历结果
     */
    public Queue<Object> preOrderTraversal(){
        //空树返回null
        if(this.size == 0){
            return null;
        }
        //队列存储结果
        Queue<Object> queue = new ArrayBlockingQueue<>(this.size);
        //前序遍历子树
        preOrderQry(queue,this.root);
        return queue;
    }

    /**
     * 前序遍历递归查找
     * @param queue 队列
     * @param treeNode 当前结点
     */
    private void preOrderQry(Queue<Object> queue,TreeNode<E> treeNode){
        if(treeNode != null){
            //前序遍历的顺序：父结点——>左子结点——>右子结点
            queue.offer(treeNode.e);
            preOrderQry(queue,treeNode.left);
            preOrderQry(queue,treeNode.right);
        }
    }

    /**
     * 中序遍历
     * 时间复杂度：O(n) --全结点访问
     * 空间复杂度：O(n) --新建队列存储遍历结果
     * @return 遍历结果
     */
    public Queue<Object> inOrderTraversal(){
        if(this.size == 0){
            return null;
        }
        Queue<Object> queue = new ArrayBlockingQueue<>(this.size);
        inOrderQry(queue,this.root);
        return queue;
    }

    /**
     * 中序遍历递归查找
     * @param queue 队列
     * @param treeNode 父结点
     */
    private void inOrderQry(Queue<Object> queue,TreeNode<E> treeNode){
        if(treeNode != null){
            //中序遍历顺序：左子结点——>父结点——>右子结点
            inOrderQry(queue,treeNode.left);
            queue.offer(treeNode.e);
            inOrderQry(queue,treeNode.right);
        }
    }

    /**
     * 后序遍历
     * 时间复杂度：O(n) --全结点访问
     * 空间复杂度：O(n) --新建队列存储遍历结果
     * @return 遍历结果
     */
    public Queue<Object> postOrderTraversal(){
        if(this.size == 0){
            return null;
        }
        Queue<Object> queue = new ArrayBlockingQueue<>(this.size);
        postOrderQry(queue,this.root);
        return queue;
    }

    /**
     * 后序遍历递归查找
     * @param queue 队列
     * @param treeNode 父结点
     */
    private void postOrderQry(Queue<Object> queue,TreeNode<E> treeNode){
        if(treeNode != null){
            //后序遍历顺序：左子结点——>右子结点——>父结点
            postOrderQry(queue,treeNode.left);
            postOrderQry(queue,treeNode.right);
            queue.offer(treeNode.e);
        }
    }

    /**
     * 层序遍历
     * 时间复杂度：O(n) --全结点访问
     * 空间复杂度：O(n)+O(n)=O(n) --新建队列用于层序访问，新建数组用于存储结果。
     * @return 遍历结果
     */
    public Object[] layerOrderTraversal(){
        //空树校验
        if(this.size == 0){
            return null;
        }
        //新建数组用于存储结果
        Object[] objects = new Object[this.size];
        //数组索引变量
        int index = 0;
        //新建队列用于层序遍历
        Queue<TreeNode<E>> queue = new ArrayBlockingQueue<>(this.size);
        //先输出根结点
        queue.offer(this.root);
        //根结点值添加到数组中。
        objects[index++] = this.root.e;
        //当前结点
        TreeNode<E> treeNode;
        //队列不为空，则继续递归
        while(!queue.isEmpty()){
            //取出先进的树结点
            treeNode = queue.poll();
            if(treeNode != null){
                if(treeNode.left != null){
                    //输出当前结点的左子结点
                    queue.offer(treeNode.left);
                    objects[index++] = treeNode.left.e;
                }
                if(treeNode.right != null){
                    //输出当前结点的右子结点
                    queue.offer(treeNode.right);
                    objects[index++] = treeNode.right.e;
                }
            }
        }
        return objects;
    }

    /**
     * 二叉搜索树添加结点。
     * @param e 添加结点数据
     * @return 添加成功则返回true；否则返回false。
     */
    public boolean add(E e){
        //空树则添加根结点
        if(this.size == 0){
            this.root = new TreeNode<>(e,null,null);
            this.size++;
            return true;
        }
        //空结点校验
        if(e == null){
            return false;
        }
        //递归添加
        return add(e,this.root);
    }

    /**
     * 二叉搜索树递归添加节点(去重)
     * 时间复杂度：O(logn) --树的每一层判断1次。结点的层次与结点的个数对数相关。
     * 空间复杂度：O(1) --使用有限的内存资源
     * @param e 节点值
     * @param treeNode 父结点
     * @return 插入成功则返回true；否则返回false。
     */
    private boolean add(E e,TreeNode<E> treeNode){
        if(treeNode != null){
            //暂定根据数据的哈希码来判断大小
            //如果新结点值大于当前结点值
            if(e.hashCode() > treeNode.e.hashCode()){
                //当前结点的右子结点为空
                if(treeNode.right == null){
                    //新结点添加到当前结点的右子结点
                    treeNode.right = new TreeNode<>(e,null,null);
                    this.size++;
                    return true;
                }else{
                    //递归添加
                    add(e,treeNode.right);
                }
            }
            //如果新结点值小于当前结点值
            if(e.hashCode() < treeNode.e.hashCode()){
                //当前结点的左子结点为空
                if(treeNode.left == null){
                    //新结点添加到当前结点的左子结点
                    treeNode.left = new TreeNode<>(e,null,null);
                    this.size++;
                    return true;
                }else{
                    //递归添加
                    add(e,treeNode.left);
                }
            }
        }
        return false;
    }

    /**
     * 二叉搜索树查找指定结点是否存在
     * @param e 指定结点值
     * @return 存在则返回true；否则返回false。
     */
    @SuppressWarnings("CatchMayIgnoreException")
    public boolean search(E e){
        //空树校验
        if(this.size == 0){
            return false;
        }
        //指定结点校验
        if(e == null){
            return false;
        }
        try {
            //递归查找
            search(e,this.root);
        }catch (Exception ex){
            if("true".equals(ex.getMessage())){
                return true;
            }
        }
        return false;
    }

    /**
     * 递归查找指定节点值
     * 时间复杂度：O(logn) --树的每一层判断1次。结点的层次与结点的个数对数相关。
     * 空间复杂度：O(1) --使用有限的内存资源。
     * @param e 指定结点
     * @param treeNode 当前结点
     */
    private void search(E e,TreeNode<E> treeNode) throws Exception {
        if(treeNode != null){
            int eVal = e.hashCode();
            int thisVal = treeNode.e.hashCode();
            if(eVal < thisVal){
                //指定结点小于当前结点，则递归其左子树
                search(e,treeNode.left);
            }else if(eVal > thisVal){
                //指定结点小于当前结点，则递归其右子树
                search(e,treeNode.right);
            }else{
                //指定结点存在，抛出异常退出递归循环
                throw new Exception("true");
            }
        }
    }

    /**
     * 二叉搜索树删除指定结点。
     * @param e 指定结点值
     * @return 删除成功返回true；否则返回false。
     */
    @SuppressWarnings("CatchMayIgnoreException")
    public boolean remove(E e){
        //空树校验
        if(this.size == 0){
            return false;
        }
        //指定结点校验
        if(e == null){
            return false;
        }
        try {
            //递归删除
            remove(e,this.root,"root",this.root);
        }catch (Exception ex){
            if("true".equals(ex.getMessage())){
                return true;
            }
        }
        return false;
    }

    /**
     * 递归删除
     * 时间复杂度：O(logn) --树的每一层判断1次。结点的层次与结点的个数对数相关。
     * 空间复杂度：O(n) --新建队列用于存储树的遍历结果。
     * @param e 要删除的结点
     * @param parent 当前结点的父结点
     * @param location 父结点与子结点的关系。规定："left"指子结点是父结点的左子结点；"right"指子结点是父结点的右子结点；"root"指父结点是根结点。
     * @param treeNode 当前结点
     * @throws Exception 结束递归循环的异常
     */
    @SuppressWarnings("unchecked")
    private void remove(E e,TreeNode<E> parent,String location,TreeNode<E> treeNode) throws Exception {
        if(treeNode != null){
            //暂定根据数据的哈希码来判断大小
            int eVal = e.hashCode();
            int thisVal = treeNode.e.hashCode();
            if(eVal < thisVal){
                //指定结点小于当前结点，则递归其左子树
                remove(e,treeNode,"left",treeNode.left);
            }else if(eVal > thisVal){
                //指定结点小于当前结点，则递归其右子树
                remove(e,treeNode,"right",treeNode.right);
            }else{
                //找到要删除的结点，分为三种情况：

                //1、要删除的结点是叶子结点，将该结点的父结点对应的指针指向null。
                if(treeNode.left == null && treeNode.right == null){
                    if("left".equals(location)){
                        parent.left = null;
                    }
                    if("right".equals(location)){
                        parent.right = null;
                    }
                    if("root".equals(location)){
                        this.root = null;
                    }
                }

                //2、要删除的结点有1个子结点，将该结点的父结点对应的指针指向该结点的子结点。
                //(1)左子结点不为空，右子结点为空
                if(treeNode.left != null && treeNode.right == null){
                    if("left".equals(location)){
                        parent.left = treeNode.left;
                    }
                    if("right".equals(location)){
                        parent.right = treeNode.left;
                    }
                    if("root".equals(location)){
                        this.root = treeNode.left;
                    }
                }
                //(2)左子结点为空，右子结点不为空
                if(treeNode.left == null && treeNode.right != null){
                    if("left".equals(location)){
                        parent.left = treeNode.right;
                    }
                    if("right".equals(location)){
                        parent.right = treeNode.right;
                    }
                    if("root".equals(location)){
                        this.root = treeNode.right;
                    }
                }

                //3、要删除的结点有2个子结点，两种替换删除方案任选其一。
                if(treeNode.left != null && treeNode.right != null){
                    //新建队列用于获取最值
                    Queue<Object> queue = new ArrayBlockingQueue<>(this.size);
                    //临时变量
                    Object obj = null;

                    //(1)替换删除法：用左子树的最大值结点替换要删除的结点，再删除左子树的最大值结点。
                    //中序遍历左子树
                    inOrderQry(queue,treeNode.left);
                    //队列最后1个元素即左子树的最大值
                    while (!queue.isEmpty()){
                        obj = queue.poll();
                    }
                    //替换
                    treeNode.e = (E)obj;
                    //删除
                    remove((E)obj,treeNode,"left",treeNode.left);

//                    //(2)替换删除法：用右子树的最小值结点替换要删除的结点，再删除右子树的最小值结点。
//                    //中序遍历右子树
//                    inOrderQry(queue,treeNode.right);
//                    //队列第一个元素即右子树的最小值
//                    if(!queue.isEmpty()){
//                        obj = queue.peek();
//                    }
//                    //替换
//                    treeNode.e = (E)obj;
//                    //删除
//                    remove((E)obj,treeNode,"right",treeNode.right);
                }
                //删除结点后重新计算树的结点个数
                this.size--;
                //抛出异常退出递归循环
                throw new Exception("true");
            }
        }
    }

    /**
     * 树的结点内部类
     * @param <E> 数据域
     */
    private static class TreeNode<E>{

        //当前结点的父结点
        TreeNode<E> parent;

        //当前结点的数据域
        E e;

        //当前结点的左子结点
        TreeNode<E> left;

        //当前结点的右子结点
        TreeNode<E> right;

        //不指定父结点的构造函数
        TreeNode(E e,TreeNode<E> left,TreeNode<E> right){
            this.e = e;
            this.left = left;
            this.right =right;
        }

        //指定父结点的构造函数
        TreeNode(TreeNode<E> parent,E e,TreeNode<E> left,TreeNode<E> right){
            this.parent = parent;
            this.e = e;
            this.left = left;
            this.right = right;
        }
    }

    /*  二叉树(二叉搜索树)-----end    */
}
