package me.domo.java8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.*;

public class GreetingTest {

    public static void main(String[] args) {
        //UnaryOperator<String> hi = s -> "hi"+s;
        UnaryOperator<String> hi = Greeting::hi;


        Greeting greeting = new Greeting();
        UnaryOperator<String> hello = greeting::hello;
        System.out.println(hello.apply("doho"));

        // Constructor은 Class를 반환
        // Greeting()
        Supplier<Greeting> newGreeting = Greeting::new;
        Greeting greeting1 = newGreeting.get(); // 이래야지 인스턴스가 생성 된 것.

        // Greeting(String str)
        Function<String, Greeting> dohoGreeting = Greeting::new;
        Greeting doho = dohoGreeting.apply("doho");
        System.out.println(doho.getName());

        String[] names = {"doho","kr","domo"};
        //Arrays.sort(names, (o1, o2) -> 0);
        Arrays.sort(names, String::compareToIgnoreCase  );
        System.out.println(Arrays.toString(names));
    }
}
