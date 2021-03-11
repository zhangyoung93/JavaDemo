package com.zy.demo.intf.impl;

import com.zy.demo.intf.TestInterface;

public class TestInterfaceImpl implements TestInterface {
    @Override
    public void doTest() {
        System.out.println("doTest");
    }
}
