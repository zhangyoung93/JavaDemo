package com.zy.demo.design;

/**
 * 单例模式
 * @author zy
 */
public class Singleton {

    /**
     * 一、内部类模式
     */

    //声明单例对象
    private Singleton singleton;

    /**
     * 构造函数私有化，禁止通过new创建对象
     */
    private Singleton(){

    }

    /**
     * 定义静态内部类
     */
    private static class SingletonInstance{
        private static Singleton singleton = new Singleton();
    }

    /**
     * 获取单例对象，调用方法时才会创建对象。
     * @return 单例对象
     */
    public static Singleton getInstance(){
        return SingletonInstance.singleton;
    }

//    /**
//     * 二、饿汉模式
//     */
//
//    //静态初始化对象实例
//    private static Singleton singleton = new Singleton();
//
//    /**
//     * 构造函数私有化，禁止通过new创建对象
//     */
//    private Singleton(){
//
//    }
//
//    /**
//     * 获取单例对象
//     * @return 单例对象
//     */
//    public static Singleton getInstance(){
//        return singleton;
//    }

//    /**
//     * 三、双重检查模式
//     */
//
//    //声明可见静态对象实例，volatile修饰符作用是防止指令重排序
//    private volatile static Singleton singleton;
//
//    /**
//     * 构造函数私有化，禁止通过new创建对象
//     */
//    private Singleton(){
//
//    }
//
//    /**
//     * 获取单例对象
//     * @return 单例对象
//     */
//    public static Singleton getInstance(){
//        //先判断对象是否为空
//        if(singleton == null){
//            //如果对象为空，则添加类锁
//            synchronized (Singleton.class){
//                //再判断一次对象是否为空，防止多线程同时绕过第一次检查，导致对象产生多例
//                //另外，指令重排序可能会发生先读后写情况，即一个线程在读singleton实例时，另一个线程还未将创建好的实例写到主内存中，导致又创建一次对象。
//                if(singleton == null){
//                    //创建对象
//                    singleton = new Singleton();
//                }
//            }
//        }
//        return singleton;
//    }
}
