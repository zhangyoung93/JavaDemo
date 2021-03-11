package com.zy.demo.loader;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 自定义类加载器
 * @author zy
 */
public class CustomerClassLoader extends ClassLoader{

    //class文件目录
    private static final String CLASS_FILE_PATH = "demoPath";

    /**
     * 将自定义类加载器委派父类加载器
     */
    public CustomerClassLoader(){
        super();
    }

    /**
     * 以自定义类加载方式加载字节码文件
     * @param name 字节码文件名称
     * @return 类对象
     * @throws ClassNotFoundException 未找到类异常
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        //字节码文件输入
        FileInputStream fis = null;
        //字节数组输出
        ByteArrayOutputStream baos = null;
        //字节数组
        byte[] bytes = null;
        //字节数组长度
        int len = 0;
        try {
            //从指定文件读取数据
            fis = new FileInputStream(CLASS_FILE_PATH+name);
            baos = new ByteArrayOutputStream();
            //创建临时字节数组用于存储中间数据
            byte[] tmp = new byte[1024];
            int readSize = 0;
            //输入
            while((readSize = fis.read(tmp)) != -1){
                //输出
                baos.write(tmp,0,readSize);
            }
            bytes = baos.toByteArray();
            len = bytes.length;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(fis != null){
                    fis.close();
                }
                if(baos != null){
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        //将字节数组转化为Class对象
        return super.defineClass(name,bytes,0,len);
    }

    public static void main(String[] args){
        CustomerClassLoader ccl = new CustomerClassLoader();
        try {
            System.out.println(ccl.findClass("demo.class"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
