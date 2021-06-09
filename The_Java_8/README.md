# The java 8
![sign](https://cdn.inflearn.com/public/course-325598-cover/493b8680-7652-4ea8-8e57-a53e58b1622f)   
**인프런 - 더 자바, Java 8 『백기선』**   
출처 : https://www.inflearn.com/course/the-java-java8/dashboard

:ballot_box_with_check: : 완료   
:clock2: : 진행중

|Chpater|Subject|Progress|Date|
|:--:|:--|:--:|:--:|
|2| [함수형 인터페이스와 람다 표현식](#함수형-인터페이스와-람다-표현식)|:ballot_box_with_check:|2021.05.27.
|3| [인터페이스의 변화](#인터페이스의-변화)|:ballot_box_with_check:|2021.06.02.
|4| [Stream](#stream)|:clock2:|2021.06.05.
<br>
---
<br> 

## **함수형 인터페이스와 람다 표현식**
수강일 : 2021.05.27.

### **[함수형 인터페이스]**
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
    // 람다로 표현할 수 없다.
    public class Foo {
        public static void main(String[] args) {
            (final) int baseNumber = 10;    // 함수 바깥 - 변경 불가능

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

결론 : 함수 내부나, 함수가 전달받은 파라미터만 가지고 사용해야한다.   

### **[자바 기본 제공 함수형 인터페이스]**
[java.lang.function](https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html) package   -> 테이블에 작성되지 않은 인터페이스는 링크 참고
<table>
<thead bgcolor="Ivory">
    <tr>
        <th > Interface Name </th>
        <th> Modifier and Type </th>
        <th  align=center> Method
        <th> Description </th>
    </tr>
</thead>
<tbody>
    <tr>
        <td rowspan=3>Function &ltT,R&gt </td>
        <td> R </td>
        <td> apply(T t)</td>
        <td>T 타입을 받아 R타입 리턴</td>
    </tr>
    <tr>
        <td> default &ltV&gt Function&ltT, V&gt </td>
        <td> andThen(Function&lt ? super R, ? extends V&gt after)</td>
        <td rowspan=2 align=center> 함수 조합에 이용</td> 
    </tr>
    <tr>
        <td>default &ltV&gt Function&ltV,R&gt</td>
        <td>compose()</td>
    </tr>
    <tr>
        <td> BiFunction&ltT,U,R&gt</td>
        <td> R </td>
        <td> apply(T t, U u)</td>
        <td> 입력값을 T,U 두개를 받아서 R을 리턴</td>
    </tr>
    <tr>
        <td> Consumer&ltT&gt </td>
        <td> void </td>
        <td> accept(T t) </td>
        <td> T타입을 받고 아무 값도 리턴하지 않음</td>
    </tr>
    <tr>
        <td> Supplier&ltT&gt </td>
        <td> T </td>
        <td> get() </td>
        <td> T 타입의 값을 제공</td>
    </tr>
    <tr>
        <td rowspan=4> Predicate&ltT&gt </td>
        <td> boolean </td>
        <td> test(T t) </td>
        <td> T타입을 받아 boolean을 리턴</td>
    </tr>
    <tr>
        <td rowspan=3> default Predicate&ltT&gt </td>
        <td> negate() </td>
        <td rowspan=3 align=center> 합수 조합용 메소드 </td>
    </tr>
    <tr>
        <td> or(Predicate&lt? super T&gt other) </td>
    </tr>
    <tr>
    </tr>
    <tr>
        <td> UnaryOperator&ltT&gt </td>
        <td colspan=2 align=center> Function&ltT, R&gt 을 상속 </td>
        <td> T타입 값 하나를 받아 동일한 타입을 리턴 </td>
    </tr>
        <tr>
        <td> BinaryOperator&ltT&gt </td>
        <td colspan=2 align=center> BiFunction&ltT, U, R&gt 을 상속 </td>
        <td> T타입 값 하나를 받아 동일한 타입을 리턴 </td>
    </tr>
</tbody>
</table>

### **[Lambda]**
**[(인자 리스트) -> {바디}](https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html)**

[인자 리스트]   
* 인자 없을 때 : ()
* 인자 한 개일 때 : (one) / one
* 인자 두 개이상 일 때 : (onw, two ~)
* 인자 타입 생략 가능 -> 컴파일러가 추론(infer)

[바디]
* 화살표 오른쪽에 함수 본문 정의
* 여러 줄인 경우 { } 사용
* 한 줄인 경우 { }, return 생략 가능

[변수 캡처 (Variable Capture)]
* Local variable capture
  * final, effective final인 경우에만 참조 가능 -> 아닌 경우 concurrency 문제 발생 가능
* effective final (JAVA 8 지원)
    ```Java
        (final) int baseNumber = 10;    // final이 없지만 이 변수는 어디서도 변경하지 않는다.
    ```
  * "사실상" final인 변수
  * final 사용하지 않은 변수를 익명 클래스 구현체 또는 람다에서 참조할 수 있다.
* 익명 클래스 구현체와 달리 "Shadowing" 않는다. [참조](https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html#shadowing)
  * 익명 클래스는 새로운 Scope를 만들지만 람다는 람다를 감싸고 있는 Scope와 같다.

[Shadowing]   

[옳은 예]
```Java
public class ShadowTest {

    public int x = 0;

    class FirstLevel {

        public int x = 1;

        void methodInFirstLevel(int x) {
            System.out.println("x = " + x);
            System.out.println("this.x = " + this.x);
            System.out.println("ShadowTest.this.x = " + ShadowTest.this.x);
        }
    }

    public static void main(String... args) {
        ShadowTest st = new ShadowTest();
        ShadowTest.FirstLevel fl = st.new FirstLevel();
        fl.methodInFirstLevel(23);
    }
}

// ---------------------- Output -------------------------------------------
    x = 23
    this.x = 1
    ShadowTest.this.x = 0
```

[틀린 예]
```Java
public class Foo {

    public static void main(String[] args) {
        Foo foo = new Foo();
        foo.run();
    }

    private void run() {
        int baseNumber = 10;

        // Lambda Error : run()과 같은 scope 공유
        IntConsumer printInt = (baseNumber) -> {
            System.out.println(i + baseNumber)
        }

        // Lambda 내부 Sout부분 오류발생 : 람다는 effective final, final만 사용 가능
        baseNumber++;
    }
}
```

### **[메소드 레퍼런스]**
``` Java
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
    Arrays.sort(names, String::compareToIgnoreCase);
    System.out.println(Arrays.toString(names));
```
---

## **인터페이스의 변화**
### **[Interface method - Default method]**
```Java
// Collection.java
// Collectino interface의 모든 하위 클래스들은 removeIf를 가진다.
default boolean removeIf(Predicate<? super E> filter) {
    Objects.requireNonNull(filter);
    boolean removed = false;
    final Iterator<E> each = iterator();
    while(each.hasNext()) {
        if(filter.test(each.next())) {
            each.remove();
            removed = true;
        }
    }
    return removed;
}
```
* 인터페이스에 메소드 선언이 아니라 구현체를 제공하는 방법
* 해당 인터페이스를 구현한 클래스를 깨트리지 않고 새 기능을 추가할 수 있다.
* 기본 메소드는 구현체가 모르게 추가된 기능이기에 그만큼 리스크가 있다.
    ```Java
    public interface Foo {
        void printName();

        default void printNameUpperCase() {
            System.out.println(getName().toUpperCase());
        }

        String getName();   // Name 값이 항상 있다고 보장할 수 없다.
                            // name = null -> NullPointException
    }
    ```
  * **컴파일 에러는 아니지만 구현체에 따라 런타임 에러 발생 가능**
  * **반드시 문서화** (@implSpec 자바독 태그 사용)
  * 구현 클래스에서 Overriding을 이용해 재정의 할 수 있다.
* **Object가 제공하는 기능** default 메소드로 제공할 수 없다.
  * 구현체가 재정의 해야한다.
  * 인터페이스에서 추상 메서드로 선언하는 것은 괜찮다. 
    * 모두 공통으로 제공하기 때문에 추상 메서드로 분류하지 않는다.
* 본인이 수정할 수 있는 인터페이스만 수정 가능하다
* 인터페이스를 상속받는 인터페이스에서 다시 추상 메소드로 변경할 수 있다.
  ```Java
  public interface Foo {
      default void getNameUpperCase() {
          ...
      }
  }

  public interface Bar extends Foo {
      void getNameUpperCase();  // 추상 메서드로 선언한 경우 구현 클래스에서 재정의 필수

      // 추상 메서드로 선언 하지 않은 경우 상위 인터페이스 그대로 사용 가능
  }
  ```
  [Bonus]
  ```Java
  public interface Foo {
      default void getNameUpperCase() {
          System.out.println("Foo");
      }
  }

  public interface Bar {
      default void getNameUpperCase() {
          System.out.println("Bar");
      }
  }

  public class Test implements Foo, Bar {
      ...
        // @Override
        // public void printNameUpperCase() {
        //    ...
        //}
  }
  ```
    * Bar, Foo 중 무엇을 사용할지 모르기 때문에 **컴파일 에러** 발생
    * 충돌하는 default method 직접 override 해야한다.

### **[Interface method - Static method]**
* 해당 타입 관련 헬퍼 또는 유틸리티 메소드를 제공할 때 인터페이스에 static method 제공 가능
  
### **[Interface method - JAVA API]**
JAVA8 에서 추가된 기본 메소드로 인해 API에 변화가 발생했다. 그 중 일부 API를 살펴보도록 한다.
* [Iterable](https://docs.oracle.com/javase/8/docs/api/java/lang/Iterable.html)
  * forEach()
  * [spliterator()](https://docs.oracle.com/javase/8/docs/api/java/util/Spliterator.html)
* [Collection](https://docs.oracle.com/javase/8/docs/api/java/util/Collection.html)
  * stream() / parallelStream()
    ```Java
    // Collection.java -> Stream 원형
    default Stream<E> stream() {
        return StreamSupport.stream(spliterator(), false);
    }
    ----------------------------------------------------------
    name.stream();
    ```
  * removeIf(Predicate)
  * spliterator()
* [Comparator](https://docs.oracle.com/javase/8/docs/api/java/util/Comparator.html)
  * reversed()
  * thenComparing()
  * static reverseOrder() / naturalOrder()
  * static nullsFirst) / nullsLast()
  * static comparing()

---
## **[Stream]**
### **[Stream]**
sequence of elements supporting sequential and parallel aggregate operations
* 데이터를 저장하고 있는 Collection이 아니다.
* Funtional in nature, 스트림이 처리하는 데이터 소스를 변경하지 않는다.
* 스트림으로 처리하는 데이터는 오직 한번만 처리한다.
* 무제한일 수도 있다. (Short Circuit 메소드를 사용해서 제한 가능)
* 중개 오퍼레이션은 근본적으로 Lazy 하다.
* 손쉽게 병렬 처리 할 수 있다.
### **[Stream pipeline]**
* 0 또는 다수의 intermediate operation (중개 오퍼레이션)과 한 개의 종료 오퍼레이션으로 구성한다.
* 스트림 데이터 소스는 오직 터미널 오퍼레이션을 실행할 때 에만 처리한다.
### **[intermediate operation]**
* stream을 리턴한다.
* stateless / stateful operation으로 더 상세하게 구분 가능하다. 
    (distinct, sorted 처럼 이전 소스 데이터 참조해야하는건 stateful opreation)
* filter, map, limit, skip, sorted, ...
### **[terminal operation]]**
* stream을 리턴하지 않는다.
* collect, allMatch, count, forEach, min, max, ...