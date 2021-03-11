package com.zy.demo.design;

/**
 * 工厂模式
 * @author zy
 */
public class Factory {
    //测试
    public static void main(String[] args){
        CarFactory carFactory = new ChineseCarFactory();
        System.out.println(carFactory);
        Car car = carFactory.produce("HongQi");
        System.out.println(car);
        if(car != null){
            car.run();
        }
    }
}

/**
 * 汽车工厂基类
 */
interface CarFactory{
    /**
     * 汽车生产行为
     */
    Car produce(String name);
}

/**
 * 中国汽车工厂派生类
 */
class ChineseCarFactory implements CarFactory {

    /**
     * 中国汽车生产行为
     */
    @Override
    public Car produce(String name) {
        if ("HongQi".equals(name)) {
            return new HongQi();
        }
        return null;
    }
}

/**
 * 美国汽车工厂派生类
 */
class AmericanCarFactory implements CarFactory {

    /**
     * 美国汽车生产行为
     */
    @Override
    public Car produce(String name) {
        if ("Benz".equals(name)) {
            return new Benz();
        }
        return null;
    }
}

/**
 * 汽车基类
 */
interface Car{
    /**
     * 汽车运行
     */
    void run();
}

/**
 * 奔驰汽车派生类
 */
class Benz implements Car {

    @Override
    public void run() {
        System.out.println("Benz car is running.");
    }
}

/**
 * 红旗汽车派生类
 */
class HongQi implements Car {

    @Override
    public void run() {
        System.out.println("HongQi car is running.");
    }
}