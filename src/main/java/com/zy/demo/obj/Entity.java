package com.zy.demo.obj;

import java.io.Serializable;

/**
 * 实体类
 * @author zy
 */
public class Entity implements Serializable {

    private int id;

    private String name;

    public Entity(){

    }

    public Entity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
