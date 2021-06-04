package me.domo.java8.Lambda;

import java.util.function.*;

public class LambdaVariable {

    public static void main(String[] args) {
        LambdaVariable lam = new LambdaVariable();
        lam.run();
    }

    private void run() {
        final int baseNumber = 10;

        class LocalClass {
            void printBaseNumber() {
                System.out.println(baseNumber);
            }
        }

        // 익명 클래스
        Consumer<Integer> integerConsumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(baseNumber);
            }
        };
        
        IntConsumer printInt = (i) -> System.out.println(i + baseNumber);

        printInt.accept(10);
    }
}
