package com.zy.demo.design;

/**
 * 类适配器
 * @author zy
 */
public class ClassAdapter {
    public static void main(String[] args){
        Target target = new AdapterClass();
        target.targetMethod();
    }
}

/**
 * 目标类
 */
interface Target{
    /**
     * 目标方法
     */
    void targetMethod();
}

/**
 * 源类
 */
class Source{
    /**
     * 源方法
     */
    protected void sourceMethod(){

    }
}

/**
 * 适配器
 */
class AdapterClass extends Source implements Target {

    /**
     * 适配目标方法
     */
    @Override
    public void targetMethod() {
        super.sourceMethod();
    }
}