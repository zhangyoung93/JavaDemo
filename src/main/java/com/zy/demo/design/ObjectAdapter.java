package com.zy.demo.design;

/**
 * 对象适配器
 *
 * @author zy
 */
public class ObjectAdapter {
    public static void main(String[] args){
        ThreeHoleSocket threeHoleSocket = new Adapter(new TwoHolePlug());
        threeHoleSocket.charge();
    }
}

/**
 * 三孔插座——目标接口
 */
interface ThreeHoleSocket {
    /**
     * 充电
     */
    void charge();
}

/**
 * 两孔插头——源接口
 */
class TwoHolePlug {
    /**
     * 两孔插头的手机充电
     */
    void chargePhone(){

    }
}

/**
 * 适配器——源接口转换为目标接口
 */
class Adapter implements ThreeHoleSocket {

    //声明源接口
    private TwoHolePlug twoHolePlug;

    /**
     * 初始化源接口
     * @param twoHolePlug 源接口
     */
    public Adapter(TwoHolePlug twoHolePlug) {
        this.twoHolePlug = twoHolePlug;
    }

    /**
     * 源接口适配目标接口
     */
    @Override
    public void charge() {
        this.twoHolePlug.chargePhone();
    }
}

