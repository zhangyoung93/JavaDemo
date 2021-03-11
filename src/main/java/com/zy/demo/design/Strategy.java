package com.zy.demo.design;

/**
 * 策略模式
 * @author zy
 */
public class Strategy {
    public static void main(String[] args){
        SelectOptimizeStrategy sos = new SelectOptimizeStrategy(new TimeOptimizeStrategy());
        sos.executeOptimize();
    }
}

/**
 * 调优策略
 */
interface OptimizeStrategy{
    /**
     * 调优方法
     */
    void optimize();
}

/**
 * 时间调优策略
 */
class TimeOptimizeStrategy implements OptimizeStrategy {

    /**
     * 时间调优方法
     */
    @Override
    public void optimize() {
        System.out.println("optimize by time");
    }
}

/**
 * 空间调优策略
 */
class SpaceOptimizeStrategy implements OptimizeStrategy {

    /**
     * 空间调优方法
     */
    @Override
    public void optimize() {
        System.out.println("optimize by space");
    }
}

/**
 * 选择调优策略
 */
class SelectOptimizeStrategy{

    //声明调优策略
    private OptimizeStrategy optimizeStrategy;

    /**
     * 选择指定调优策略
     * @param optimizeStrategy 调优策略
     */
    public SelectOptimizeStrategy(OptimizeStrategy optimizeStrategy){
        this.optimizeStrategy = optimizeStrategy;
    }

    /**
     * 执行策略
     */
    public void executeOptimize(){
        this.optimizeStrategy.optimize();
    }
}