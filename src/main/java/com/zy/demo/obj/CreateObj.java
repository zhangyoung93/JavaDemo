package com.zy.demo.obj;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 创建对象
 * @author zy
 */
public class CreateObj {

    //对象序列化输出文件
    private static final String FILE_PATH = "src/main/resources/obj.txt";

    /**
     * 创建对象的方式
     * @param obj 对象
     * @param method 方式
     */
    public static void createObj(Object obj,int method){
        System.out.println("old obj="+obj);
        try {
            switch (method){
                //1、通过关键字new创建对象
                case 1:
                    obj = new Object();
                    break;
                //2、通过类对象的newInstance()方法创建对象
                case 2:
                    obj = obj.getClass().newInstance();
                    break;
                //3、通过构造器对象的newInstance()方法创建对象
                case 3:
                    obj = obj.getClass().getConstructor().newInstance();
                    break;
                //4、通过对象的序列化与反序列化创建对象
                case 4:
                    //序列化
                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH));
                    oos.writeObject(obj);
                    oos.close();
                    //反序列化
                    ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH));
                    obj = ois.readObject();
                    ois.close();
                    break;
                //5、通过clone()方法创建对象
                case 5:
                    if(obj instanceof Entity){
                        obj = ((Entity)obj).clone();
                    }
                    break;
                default:
                    System.out.println("no method!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("new obj="+obj);
    }
}
