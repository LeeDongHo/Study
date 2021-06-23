package me.domo.java8.Steram;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("doho");
        names.add("foo");
        names.add("fighting");
        names.add("grape");

        // Functional in nature
        Stream<String> stringStream = names.stream().map(String::toUpperCase);
        names.forEach(System.out::println);
        System.out.println("-------------------------------");
        stringStream.forEach(System.out::println);

        System.out.println("Intermediate operation]===========");

        // Intermediate operation (중개 오퍼레이션)
        // terminal operation 이 오기 전까지 실행되지 않는다.
        List<String> collect = names.stream().map(s -> {
            System.out.println(s);
            return s.toUpperCase();
        }).collect(Collectors.toList());
        System.out.println("print Collect]====");
        collect.forEach(System.out::println);

        // 활용 예제
        for (String name:names) {
            if (name.startsWith("d")) {
                System.out.println(name.toUpperCase());
            }
        }

        System.out.println("ParallelSteram====================");
        names.parallelStream().map((s) -> {
            System.out.println(s + " " + Thread.currentThread().getName());
            return s.toUpperCase();
        }).collect(Collectors.toList());
        //collect.forEach(System.out::println);

        System.out.println("\nStream====================");
        names.stream().map((s) -> {
            System.out.println(s + " " + Thread.currentThread().getName());
            return s.toUpperCase();
        }).collect(Collectors.toList());
        //collect.forEach(System.out::println);

        // Stream API
        System.out.println("========[Stream API]===========");
        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1,"spring boot",true));
        springClasses.add(new OnlineClass(2,"spring data jpa",true));
        springClasses.add(new OnlineClass(3,"spring mvc",false));
        springClasses.add(new OnlineClass(4,"spring core",false));
        springClasses.add(new OnlineClass(5,"rest api development",false));

        System.out.println("Spring 으로 시작하는 수업");
        // TODO
        springClasses.stream()
                .filter(s -> s.getTitle().startsWith("spring"))
                .forEach(s -> System.out.println(s.getTitle()));

        System.out.println();
        System.out.println("Close 되지 않은 수업");
        // TODO
//        springClasses.stream()
//                .filter(oc -> !oc.isClosed())
//                .forEach(oc -> System.out.println(oc.getTitle()));
        // after
        springClasses.stream()
                .filter(Predicate.not(OnlineClass::isClosed))
                .forEach(oc -> System.out.println(oc.getId()));

        System.out.println("수업 이름만 모아서 스트림 만들기");
        // TODO
        springClasses.stream()
                .map(OnlineClass::getTitle)
                .forEach(System.out::println);


        List<OnlineClass> javaClasses = new ArrayList<>();
        javaClasses.add(new OnlineClass(6,"The java, Test",true));
        javaClasses.add(new OnlineClass(7,"The java, Code manipulation",true));
        javaClasses.add(new OnlineClass(8,"The java, 8 to 11",false));

        List<List<OnlineClass>> dohoEvents = new ArrayList<>();
        dohoEvents.add(springClasses);
        dohoEvents.add(javaClasses);

        System.out.println("두 수업 목록에 들어있는 모든 수업 아이디 출력");
        // TODO
        dohoEvents.stream()
                .flatMap(Collection::stream)
                .forEach(oc -> System.out.print(oc.getId() + ","));
        System.out.println();
        System.out.println("10부터 1씩 증가하는 무제한 스트림 중에서 앞에 10개 빼고 최대 10개 까지만");
        // TODO
        Stream.iterate(10, i->i+1)
                .skip(10)
                .limit(10)
                .forEach(System.out::println);

        System.out.println("자바 수업 중에 Test가 들어있는 수업이 있는지 확인");
        // TODO
        boolean test = javaClasses.stream()
                .anyMatch(oc -> oc.getTitle().contains("Test"));
        System.out.println(test);

        System.out.println("스프링 수업 중에 제목에 spring이 들어간 제목만 모아서 List로 만들기");
        // TODO
        List<String> spring = springClasses.stream()
                .filter(oc -> oc.getTitle().contains("spring"))
                .map(OnlineClass::getTitle)
                .collect(Collectors.toList());
        spring.forEach(System.out::println);
        System.out.println("// map 위치 차이");
        List<String> spring1 = springClasses.stream()
                .map(OnlineClass::getTitle)
                .filter(t -> t.contains("spring"))
                .collect(Collectors.toList());
        spring1.forEach(System.out::println);
    }
}

