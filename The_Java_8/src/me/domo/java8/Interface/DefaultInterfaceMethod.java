package me.domo.java8.Interface;

import me.domo.java8.Interface.InterfaceMethod;

public class DefaultInterfaceMethod implements InterfaceMethod, SubInterface {
    String name;

    public DefaultInterfaceMethod(String name) {
        this.name = name;
    }

    @Override
    public void printName() {
        System.out.println(this.name);
    }

    @Override
    public String getName() {
        return this.name;
    }
}
