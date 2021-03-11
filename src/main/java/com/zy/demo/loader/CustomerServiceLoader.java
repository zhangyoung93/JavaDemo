package com.zy.demo.loader;

import com.zy.demo.intf.TestInterface;

import java.util.ServiceLoader;

/**
 * 自定义SPI
 * @author zy
 */
public class CustomerServiceLoader {
    public static void main(String[] args){
        //加载指定接口的ServiceLoader
        ServiceLoader<TestInterface> serviceLoader = ServiceLoader.load(TestInterface.class);
        //遍历接口实现类
        for(TestInterface testService : serviceLoader){
            //获取接口实现类名称
            System.out.println(testService.getClass().getName());
            //执行接口方法
            testService.doTest();
        }
    }
}
