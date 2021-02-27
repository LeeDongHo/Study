package com.ch5;

public class subClass {

    // instance variable
    // class area에 정의되며 객체 생성시 자동 초기화 된다.
    int number;

    // static variable
    // 클래스를 로드할 때 class area의 static영역에 바로 초기화된다.
    // 해당 클래스 타입으로 생성된 모든 객체가 값을 공유한다.
    static int st_number = 3;

    subClass(int n) {
        n = number;
    }
    void local_function(int local_int) {
        int x = local_int;
        System.out.println("local_function x : " + x);
        System.out.println("static variable : " + st_number);
        System.out.println("instance variable : " + number);

    }

    void set_instance(int value)
    {
        number = value;
    }
}
