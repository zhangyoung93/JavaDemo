package com.zy.demo.design;

/**
 * 建造者模式
 * @author zy
 */
public class Builder {

    public static void main(String[] args){
        User user = new UserBuilder().id(20L).name("zy").age(27).sex('男').build();
        System.out.println(user);
    }
}

/**
 * 用户类
 */
class User{

    //编号
    private long id;
    //姓名
    private String name;
    //年龄
    private int age;
    //性别
    private char sex;

    /**
     * 有参构造方法私有化
     * @param id 编号
     * @param name 姓名
     * @param age 年龄
     * @param sex 性别
     */
    public User(long id, String name, int age, char sex){
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }
}

/**
 * 用户建造者
 */
class UserBuilder{
    //编号
    private long id;
    //姓名
    private String name;
    //年龄
    private int age;
    //性别
    private char sex;

    /**
     * 编号赋值建造者
     * @param id 编号
     * @return 建造者
     */
    public UserBuilder id(long id){
        this.id = id;
        return this;
    }

    /**
     * 姓名赋值建造者
     * @param name 姓名
     * @return 建造者
     */
    public UserBuilder name(String name){
        this.name = name;
        return this;
    }

    /**
     * 年龄赋值建造者
     * @param age 年龄
     * @return 建造者
     */
    public UserBuilder age(int age){
        this.age = age;
        return this;
    }

    /**
     * 性别赋值建造者
     * @param sex 性别
     * @return 建造者
     */
    public UserBuilder sex(char sex){
        this.sex = sex;
        return this;
    }

    /**
     * 创建对象并增加校验规则
     * @return 对象实例
     */
    public User build(){
        if(this.id > 10000L){
            throw new IllegalArgumentException("属性id不能大于10000");
        }
        if(this.name == null){
            throw new IllegalArgumentException("属性name不能为空");
        }
        if(this.age < 20 || this.age > 60){
            throw new IllegalArgumentException("属性age取值范围为20到60");
        }
        return new User(this.id,this.name,this.age,this.sex);
    }
}
