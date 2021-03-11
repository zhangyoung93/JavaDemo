package com.zy.demo.algorithm;

import java.util.ArrayDeque;

/**
 * 递归
 * @author zy
 */
public class Recursion {

    /**
     * 汉诺塔问题：假设有x、y、z三根柱子，其中x上从小到大放置n个大小各不相同的圆盘(最小圆盘在柱子顶部，
     * 最大圆盘在柱子底部)，y和z暂时未放置圆盘。现要将x上的圆盘移动到z上，要求每次只能移动
     * 1个圆盘，且大圆盘不能放在小圆盘上边。求移动的步骤。
     *
     * 问题分析：
     * 柱子x移动圆盘只能从柱子顶部的圆盘开始移动，大圆盘不能放在小圆盘上边，必须借助柱子y暂存小圆盘，才能
     * 让柱子x底部最大的圆盘移动到柱子z的底部。而在最大圆盘移动到z底部之前，必须要借助x、z不断调整圆盘的位置，
     * 以满足大圆盘不能放在小圆盘上边的规则。
     *
     * 解题思路：
     * 1、将x上除底部的n-1个圆盘移动到y。
     * 2、将x底部最大的圆盘移动到z。
     * 3、将y上的n-1个圆盘移动到z。
     *
     * 数据结构：
     * 由于圆盘总是从顶部取走，符合后进先出原则，所以选用栈结构。
     *
     * @param x x柱子，大圆盘在栈底，小圆盘在栈顶，圆盘大小暂定由正整数值表示。
     * @param z z柱子，按照从小到大的顺序压栈。
     * @return z柱子
     */
    public static ArrayDeque<Integer> HanoiTower(ArrayDeque<Integer> x,ArrayDeque<Integer> z){
        //校验入参
        if(x == null || x.size() == 0){
            return null;
        }
        //圆盘数量n
        int n = x.size();
        //清空z
        if(z == null){
            z = new ArrayDeque<>(n);
        }else{
            z.clear();
        }
        //仅有1个圆盘直接返回
        if(n == 1){
            z.push(x.pop());
            return z;
        }
        //创建柱子y，作为介质
        ArrayDeque<Integer> y = new ArrayDeque<>(n);
        //递归将x上的圆盘放置到z上
        move(x.size(),x,y,z);
        //返回z
        return z;
    }

    /**
     * 递归移动
     * @param n 圆盘数量
     * @param x x柱子
     * @param y y柱子
     * @param z z柱子
     */
    private static void move(int n,ArrayDeque<Integer> x,ArrayDeque<Integer> y,ArrayDeque<Integer> z) {
        //移动x底部圆盘到z
        if(n == 1){
            z.push(x.pop());
            return;
        }
        //移动x到y
        move(n-1,x,z,y);
        //移动x到z
        z.push(x.pop());
        //移动y到z
        move(n-1,y,x,z);
    }

    /**
     * 输入x，输出斐波那契数列中第x位的元素。
     * 斐波那契数列：任意数等于其前面2个数的和。f(n)=f(n-2)+f(n-1),n>=3。f(0)=0,f(1)=1,f(2)=1，
     * 例如：0，1，1，2，3，5，8，13，21......
     *
     * 问题分析：
     * 递归体：f(n)=f(n-2)+f(n-1)，n>=3
     * 终止条件：f(0)=0,f(1)=1
     *
     * @param nx 输入x
     * @return 斐波那契数列第x位的元素
     */
    public static int fibonacci(int nx){
        //入参校验
        if(nx < 3){
            throw new IllegalArgumentException("Illegal input nx < 3");
        }
        //递归求和
        return sumResult(nx);
    }

    /**
     * 递归求和
     * @param nx 第nx位
     * @return 斐波那契数列第nx位元素
     */
    private static int sumResult(int nx){
        //f(0)=0
        if(nx == 0){
            return 0;
        }
        //f(1)=1
        if(nx == 1){
            return 1;
        }
        //求和
        return sumResult(nx-2) + sumResult(nx-1);
    }
}
