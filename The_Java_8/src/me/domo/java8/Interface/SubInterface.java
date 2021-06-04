package me.domo.java8.Interface;

import me.domo.java8.Interface.InterfaceMethod;

public interface SubInterface extends InterfaceMethod {
    // InterfaceMethod가 제공하는 default method printNameUpperCase()를 제공하지 않는다.
    // 이럴 경우 추상 메서드로 선언해주면 된다.

    // 추상 메서드를 선언하지 않으면 모든 클래스에 기본으로 제공된다.
    // 추상 메서드로 선언한 경우 : SubInterface를 구현하는 모든 클래스에서 재정의 해야한다.
    //void printNameUpperCase();

    default void printNameUpperCase() {
        
    }





}
