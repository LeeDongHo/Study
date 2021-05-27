# The java 8
![sign](https://cdn.inflearn.com/public/course-325598-cover/493b8680-7652-4ea8-8e57-a53e58b1622f)   
**인프런 - 더 자바, Java 8 『백기선』**   
출처 : https://www.inflearn.com/course/the-java-java8/dashboard

:ballot_box_with_check: : 완료   
:clock2: : 진행중

|Chpater|Subject|Progress|Date|
|:--:|:--|:--:|:--:|
|2| [함수형 인터페이스와 람다 표현식](#2-함수형-인터페이스와-람다-표현식clock220210527)|:clock2:|2021.05.27
---
## 함수형 인터페이스와 람다 표현식
수강일 : 2021.05.27.

### **함수형 인터페이스**
* interface에 **추상 메소드가 1개만** 존재하는 것.
* **@FunctionalInterface** 어노테이션 추가 -> 추상 메서드 2개 이상일 경우 어노테이션에 에러 발생


```Java
@FunctionalInterface
public interface RunSomething {
    void doIt();        // Abstract method

    // 아래는 추상 메서드가 아니기 때문에 RunSomething Interface는 Functional Interface 다.
    static void printName() {
        System.out.println("DOHO");
    }

    default void printAge() {
        System.out.println("30");
    }
}
```

아래는 추상 메서드가 2개 이상인 경우 코드이다. @FunctionalInterface 어노테이션에서 오류가 발생한다.
```Java
@FunctionalInterface 
public interface RunSomething {
    void doIt();

    void doAgain(); // @FunctinalInterface 오류 발생
}
```

아래는 인터페이스를 구현한 코드이다.
```Java
public class Foo {

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

    }
}
```
* 자바 함수형 프로그래밍에선 함수를 **First class object**로 사용할 수 있다.   
-> First class object라는 것은 **"() -> System.out.prinln("~")"** 같은 **람다 식을 리턴하거나 함수의 매개변수로 사용할 수 있음**을 말한다.
* 고차 함수(Higher-Order Function) : 함수가 함수를 매개변수로 사용할 수 있다.   
-> 당연한 이유는 자바는 함수 또한 Object로 관리하기 때문이다.
* 순수 함수 : 항상 같은 결과를 보장   
    ```Java
    @FunctionalInterface
    public interface RunSomething {
        int doIt(int number);
    }
    ```
  [옳은 예]

    ```Java
    public class Foo {
        public static void main(String[] args) {
            RunSomething runSomething = (number) -> {
                return number+10;
            };

            // 항상 같은 결과 보장
            System.out.println(runSomething.doIt(1));
            System.out.println(runSomething.doIt(1));
            System.out.println(runSomething.doIt(1));
        }
    }
    ```
    [틀린 예]
    ```Java
    public class Foo {
        public static void main(String[] args) {
            int baseNumber = 10;    // 함수 바깥 - 변경 불가능

            RunSomething runSomething = new RunSomething() {
                int innerNumber = 10; // 함수 바깥 - 변경 가능
                @Override
                public int doIt(int number) {
                    // baseNumber++ : 불가능
                    innerNumber++;  // 가능
                    return number + baseNumber; 
                }
            }
        }
    }
    ```
    위와 같은 경우는 함수 바깥의 값을 참조하므로 **상태 값에 의존한다**라고 말 한다.   
    함수 내부에서 외부 변수 변경은 문법적으로 막힌게 있고 가능한게 있지만 순수 함수라고 볼 수 없다.