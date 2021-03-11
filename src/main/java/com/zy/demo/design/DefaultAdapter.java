package com.zy.demo.design;

/**
 * 默认适配器模式
 * @author zy
 */
public class DefaultAdapter {
    public static void main(String[] args){
        new TxtFileMonitor().delete();
    }
}

/**
 * 文件操作监听器
 */
interface FileOperateListener{

    /**
     * 操作前触发
     */
    void opBefore();

    /**
     * 操作后触发
     */
    void opAfter();

    /**
     * 新增文件时触发
     */
    void add();

    /**
     * 修改文件时触发
     */
    void update();

    /**
     * 读取文件时触发
     */
    void read();

    /**
     * 删除文件时触发
     */
    void delete();
}

/**
 * 文件操作适配器
 */
class FileOperateAdapter implements FileOperateListener {

    @Override
    public void opBefore() {

    }

    @Override
    public void opAfter() {

    }

    @Override
    public void add() {

    }

    @Override
    public void update() {

    }

    @Override
    public void read() {

    }

    @Override
    public void delete() {

    }
}

/**
 * txt文件监控
 */
class TxtFileMonitor extends FileOperateAdapter {

    /**
     * 操作txt文件后触发
     */
    @Override
    public void opAfter() {
        System.out.println("op txt file after.");
    }

    /**
     * 修改txt文件时触发
     */
    @Override
    public void update() {
        System.out.println("update txt file.");
    }

    /**
     * 删除txt文件时触发
     */
    @Override
    public void delete() {
        System.out.println("delete txt file.");
    }
}