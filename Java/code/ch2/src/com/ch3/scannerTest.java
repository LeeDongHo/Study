package com.ch3;
import java.util.Scanner;

public class scannerTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // next
        System.out.print("문자열 입력 : ");
        String nextString = sc.next();
        System.out.println("nextString = " + nextString);

        // nextInt
        System.out.print("숫자 입력 : ");
        int nextInt = sc.nextInt();
        System.out.println("nextInt : " + nextInt);

        // random test
        double randResult = (int)(Math.random());
    }
}
