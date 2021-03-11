package com.zy.demo.design;

import java.util.ArrayList;
import java.util.List;

/**
 * 观察者模式
 * @author zy
 */
public class Observer {
    public static void main(String[] args){
        Topic topic = new Topic();
        new MusicSubscriber(topic);
        new MovieSubscriber(topic);
        topic.notifySub("notify msg");
    }
}

/**
 * 主题类
 */
class Topic{
    //存储订阅者列表
    private List<Subscriber> subscriberList = new ArrayList<>();

    /**
     * 注册订阅者
     * @param subscriber 订阅者
     */
    public void registerSub(Subscriber subscriber){
        this.subscriberList.add(subscriber);
    }

    /**
     * 通知订阅者
     */
    public void notifySub(String msg){
        //循环订阅者订阅消息
        for(Subscriber subscriber : this.subscriberList){
            subscriber.subscribe(msg);
        }
    }
}

/**
 * 订阅者接口
 */
interface Subscriber{
    /**
     * 订阅主题
     * @param msg 消息
     */
    void subscribe(String msg);
}

/**
 * 音乐订阅者
 */
class MusicSubscriber implements Subscriber {

    /**
     * 在主题上注册音乐订阅者
     * @param topic 主题
     */
    public MusicSubscriber(Topic topic){
        topic.registerSub(this);
    }

    /**
     * 订阅
     * @param msg 消息
     */
    @Override
    public void subscribe(String msg) {
        System.out.println("subscribe music msg");
    }
}

/**
 * 电影订阅者
 */
class MovieSubscriber implements Subscriber {

    /**
     * 在主题上注册电影订阅者
     * @param topic 主题
     */
    public MovieSubscriber(Topic topic){
        topic.registerSub(this);
    }

    /**
     * 订阅
     * @param msg 消息
     */
    @Override
    public void subscribe(String msg) {
        System.out.println("subscribe movie msg");
    }
}