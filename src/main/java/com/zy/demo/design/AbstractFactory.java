package com.zy.demo.design;

/**
 * 抽象工厂模式
 * @author zy
 */
public class AbstractFactory {
    public static void main(String[] args){
        AbstractComputerFactory acf = new AmdComputerFactory();
        System.out.println("ComputerFactory="+acf);
        HardDisk hardDisk = acf.makeHardDisk();
        System.out.println("HardDisk="+hardDisk);
        if(hardDisk != null){
            hardDisk.save();
        }
        Screen screen = acf.makeScreen();
        System.out.println("Screen="+screen);
        if(screen != null){
            screen.show();
        }
    }
}

/**
 * 硬盘抽象产品
 */
interface HardDisk{
    /**
     * 存储
     */
    void save();
}

/**
 * 显示器抽象产品
 */
interface Screen{
    /**
     * 显示
     */
    void show();
}

/**
 * 电脑抽象工厂
 */
interface AbstractComputerFactory{
    /**
     * 生产硬盘
     * @return 抽象产品硬盘
     */
    HardDisk makeHardDisk();

    /**
     * 生产显示器
     * @return 抽象产品显示器
     */
    Screen makeScreen();
}

/**
 * AMD硬盘
 */
class AmdHardDisk implements HardDisk {

    @Override
    public void save() {
        System.out.println("AMD HardDisk save.");
    }
}

/**
 * AMD显示器
 */
class AmdScreen implements Screen {

    @Override
    public void show() {
        System.out.println("AMD Screen show.");
    }
}

/**
 * Intel硬盘
 */
class IntelHardDisk implements HardDisk {

    @Override
    public void save() {
        System.out.println("Intel HardDisk save.");
    }
}

/**
 * Intel显示器
 */
class IntelScreen implements Screen {

    @Override
    public void show() {
        System.out.println("Intel Screen show.");
    }
}

/**
 * AMD电脑工厂
 */
class AmdComputerFactory implements AbstractComputerFactory {

    @Override
    public HardDisk makeHardDisk() {
        return new AmdHardDisk();
    }

    @Override
    public Screen makeScreen() {
        return new AmdScreen();
    }
}

/**
 * Intel电脑工厂
 */
class IntelComputerFactory implements AbstractComputerFactory {

    @Override
    public HardDisk makeHardDisk() {
        return new IntelHardDisk();
    }

    @Override
    public Screen makeScreen() {
        return new IntelScreen();
    }
}