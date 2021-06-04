package me.domo.java8.Interface;

import java.util.*;
import java.util.stream.Collectors;

public class InterfaceTest {
    public static void main(String[] args) {

        // Interface default method
        DefaultInterfaceMethod method = new DefaultInterfaceMethod("doho");
        method.printName();
        method.printNameUpperCase();

        List<String> name = new ArrayList<>();
        name.add("tonberry");
        name.add("mogle");
        name.add("Foo");
        name.add("Bar");

        name.forEach(System.out::println);

        System.out.println("==========Spliterator===========");
        // iterator와 비슷한데 split할 수 있음
        //name.spliterator();

        Spliterator<String> spliterator = name.spliterator();
        Spliterator<String> spliterator1 = spliterator.trySplit();// 쪼개진다
        while(spliterator.tryAdvance(System.out::println));
        System.out.println("=====================");
        while(spliterator1.tryAdvance(System.out::println));

        System.out.println("==========Collection===========");
        
        long l = name.stream().map(String::toUpperCase)
                .filter(s -> s.startsWith("F"))
                .count();
        System.out.println("Start F count is " + l);

        Set<String> set = name.stream().map(String::toUpperCase)
                .filter(s -> s.startsWith("F"))
                .collect(Collectors.toSet());
        System.out.println(set);

        System.out.println("[RemoveIf]");
        name.removeIf(s->s.startsWith("m"));
        name.forEach(System.out::println);

        System.out.println("==========Comparator===========");
        Comparator<String> compareToIgnoreCase = String::compareToIgnoreCase;
        name.sort(compareToIgnoreCase.reversed());  // DESC
        //name.sort(compareToIgnoreCase.reversed().thenComparing());    // 추가 조건
        System.out.println(name);

    }
}
