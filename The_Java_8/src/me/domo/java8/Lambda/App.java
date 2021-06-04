package me.domo.java8.Lambda;

import java.util.function.*;

public class App {

    public static void main(String[] args) {
        // 익명 내부 클래스 - anonymous inner class
        RunSomething runSomething = new RunSomething() {
            @Override
            public void doIt() {
                System.out.println("Hello");
                System.out.println("Lambda");
            }
        };
        runSomething.doIt();

        // to lambda
        RunSomething runSomething_lambda = () -> System.out.println("Hello");
        runSomething_lambda.doIt();

        RunSomething runSomething_lambda2 = () -> {
            System.out.println("Hello");
            System.out.println("Lambda");
        };
        runSomething_lambda2.doIt();

        //------------------------------------------------------------------------
        Plus10 plus10 = new Plus10();
        System.out.println(plus10.apply(1));    // output 11

        //-------------Plut10 to lambda-------------------------------------------
        Function<Integer, Integer> plus10_1 = (i) -> i+10;
        UnaryOperator<Integer> uPlus10 = (i) -> i+10;

        System.out.println(plus10_1.apply(2));      // output 12
        System.out.println(uPlus10.apply(3));       // output 13

        //------------Function<T,R> compse(), andThen()---------------------------
        Function<Integer, Integer> multiply2 = (i) -> i*2;

        // Higher-Order
        // 입력으로 들어온 Function apply 실행 후 compose를 호출한 Obj의 apply 실행
        // (i*2) + 10
        Function<Integer, Integer> multiply2AndPlus10 = plus10.compose(multiply2);
        System.out.println(multiply2AndPlus10.apply(2));    // output 14

        // (i+10) * 2
        System.out.println(plus10.andThen(multiply2).apply(2)); // output 24

        //------------Consumer<T>---------------------------
        Consumer<Integer> printT = (i) -> System.out.println(i);
        printT.accept(10);      // output 10

        //------------Supplier<T>---------------------------
        Supplier<Integer> get10 = () -> 10;
        System.out.println(get10.get());   // output 10

        //------------Predicate<T>---------------------------
        Predicate<String> startWithDoho = (s) -> s.startsWith("doho");
        Predicate<Integer> isEven = (i) -> i%2 ==0;

        //------------BinaryOperator<T>---------------------------
        BinaryOperator<Integer> sum = (a, b) -> a+b;
    }
}
