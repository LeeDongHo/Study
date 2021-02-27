# JAVA 

* IDE : IntelliJ IDEA 2020.2.1

- [JAVA](#java)
  - [Java](#java-1)
  - [변수](#변수)
  - [배열](#배열)
  - [객체지향](#객체지향)
  - [예외처리](#예외처리)
  - [문자열](#문자열)
    - [String](#string)
    - [StringBuffer](#stringbuffer)
  - [참고](#참고)
  

**자바 실행 과정**   
![실행과정](../Java/img/compile.jpg)   
운영체제에 상관없이 바이트 코드 파일은 JVM을 통해서 실행 가능한 기계어로 번역된다.

**패키지**   
자바는 소스 파일 및 컴파일된 바이트 코드 파일들을 기능별로 쉽게 관리하기 위해 패키지를 사용한다. JDK 11 이후 버전부터는 직접 생성하는 프로젝트도 모듈로 활용할 수 있기 때문에 반드시 패키지를 요구한다. JDK 8 이전 버전에선 필수는 아니지만 JDK 버전에 상관없이 패키지 만드는 것이 좋다.

**JAVA Memory 구조?**
- class area, stack, heap

**IntelliJ 단축키**

psvm + TAB : public static void main() 함수 생성

## Java
* 자바의 장점
  * 운영체제에 독립적이다.
    * 자바 응용 프로그램은 JVM (Java Virtual Machine)하고만 통신을 하고 JVM이 운영체제가 이해할 수 있도록 응용 프로그램을 변환하여 전달한다. JVM은 운영체제에 종속적이다.
  * 객체지향언어
    * OOP(Object-Oriented Programming language)중 하나로 객제지향의 특징인 상속, 캡슐화, 다형성이 잘 적용된 순수 객체지향언어라는 평가를 받고있다.
  * 쉬운 난이도
    * OOP 관련 구문은 스몰톡(small talk)이라는 객체지향언어에서 가져왔고, 연산자와 기본구문은 C++에서 가져왔다고 한다. 장점은 취하고 복잡한 부분(메모리 관리)을 제거해 단순화 했다.
  * 자동 메모리 관리 (Garbage Collection)
    * GC가 자동적으로 메모리를 관리해주기 때문에 개발자는 메모리를 따로 관리 하지 않아도 된다.
  * 네트워크와 분산처리를 지원한다.
    * 다양한 네트워크 프로그래밍 라이브러리 (Java API)가 존재한다.
  * 멀티스레드를 지원한다.
    * 멀티 스레딩 프로그램은 OS마다 구현방법이 다르지만 JAVA에서는 시스템과 관계없이 구현 가능하다 (JVM), 여러 스레드에 대한 스케쥴링은 **자바 인터프리터**가 담당한다.
  * 동적 로딩(Dynamic Loading)을 지원
    * 실행시 모든 클래스가 로딩되지 않고 필요한 시점에 클래스를 로딩하여 사용할 수 있다. 그래서 일부 클래스가 변경되어도 응용 프로그램 전체를 다시 컴파일 하지 않아도 된다.
    * 단점으로는 속도 문제가 대표적인데 JVM과 동적 로딩 방법때문에 속도가 느린것으로 추정한다. 이에 대한 대안으로 **JIT컴파일러**와 **Hotspot**같은 신기술의 도입으로 JVM 기능을 향상시켜 속도 문제를 개선했다고 한다.

* Java 실행 과정
  ```
    1. 프로그램의 실행에 필요한 클래스(*.class)파일을 로드한다.
    2. 클래스 파일을 검사한다. (파일 형식, 악성 코드 확인)
    3. 지정된 클래스에서 main(String[] args)를 호출한다.
  ```

* Package
  * 관련된 클래스들을 묶는 물리적 디렉토리 단위이다. 
  * 같은 이름의 클래스라도 다른 패키지에 존재한다면 충돌을 피할 수 있다.
  * 점(.)을 구분자로 계층구조 구성이 가능하다.
  * 소스파일에 자신이 속할 패키지를 지정하지 않은 클래스는 자동적으로 '이름없는 패키지'에 속한다.
  * 클래스명과 쉽게 구분을 위해 소문자 명명을 원칙으로 한다.

* Import
  * 컴파일러에게 소스파일에 사용된 클래스의 패키지 정보를 제공하는 것. 
  * 기본적으로 package를 모두 작성해줘야 하지만 import로 미리 명시해주면 클래스 이름에서 package를 생략할 수 있다.
  * package -> import -> class 순서로 선언한다.
  * '*'을 쓸 경우 지정된 패키지에 속하는 모든 클래스를 패키지명 없이 사용할 수 있지만 하위 계층 클래스까지 포함해주지는 않는다.
    ```
      import java.util.*
      import java.text.*
      --------------------------
      import java.*

      위 두가지는 다르다.
    ```
  * import를 사용하면 컴파일 시간이 조금 더 걸리고 그 외엔 영향을 주지 않는다.


## 변수
  * 기본형 (Primitive type) : boolean, char, byte, short, int, long, float, double   
  
    |자료형|크기|저장 가능한 범위|종류|
    |--|--|:--:|:--:|
    |boolean|1byte| true, false| 논리형
    |byte|1byte|-128 ~ 127|정수형
    |short|2byte|-32.768 ~ 32,767|정수형
    |char|2byte|0 ~ 65536 |문자형
    |int|4byte|-2,147,483,648 ~ 2,147,483,647|정수형
    |float|4byte|1.4E-45 ~ 3.4028235E38|실수형
    |long|8byte|-9,223,372,036,854,775,808 ~ 9,223,372,036,854,775,807|정수형
    |double|8byte|4.9E-324 ~ 1.7976931348623157E308|실수형   
    * C/C++ 에서 char은 1byte (ASCII)였다. 하지만 JAVA의 char은 2byte (UNICODE)라는 차이가 있다. JAVA의 char의 범위는 C/C++의 unsigned short 와 같다.
    * JVM은 피연산자를 4byte단위로 저장하기 때문에 크기가 4byte보다 작은 자료형들 (byte, short)을 연산할 때는 int를 사용하는 것이 효율적이다.


  * 기본형 - **실수형**
    * float : 1 + 8 + 23 = 32bit = 4byte  
      |부호|지수|가수|
      |:--:|:--:|:--:|
      |1|8|23|
    * double : 1 + 11 + 52 = 64bit = 8byte  
      |부호|지수|가수|
      |:--:|:--:|:--:|
      |1|11|52|
  
  * 참조형 (Reference type) : Primitive type 8개를 제외한 나머지, 객체의 주소를 저장
  * 형 변환 (casting)
    * 자동 형변환 : 캐스트 연산자를 생략한 경우엔 JVM이 자동적으로 형변환 한다. 이때 큰 자료형에서 작은 자료형으로 변환은 값 손실의 우려가 있어 자동 형변환이 적용되지 않는다.
    ![casting](./img/casting.png)
    * 연산에서 형변환은 int보다 작은 경우 int로, int보다 큰 경우 큰 자료형에 맞춰진다.
  * JAVA에서 상수는 final이다. (C++은 const 이다)

  * 클래스 변수 (공유 변수) : 멤버 변수 중에 static이 붙은 것, 모든 인스턴스가 공통된 변수를 공유한다. 클래스가 로딩될때 생성되어 프로그램이 종료될 때 까지 유지되며, public을 앞에 붙이면 전역변수의 성격을 갖는다.
  * Initialization 
    * **초기화 순서** : Default -> Explicit -> Block -> (Constructor)
    * **Explicit Initialization** : 변수 선언과 동시에 초기화하는 것.
    * **Initialization Block**
      ```
      class BlockTest {
        // Class initialization block
        static {
          System.out.println("static{ }");
        }


        // Instance initialization block
        {
          System.out.println("{ }");
        }

        public BlockTest() {
          System.out.println("생성자");
        }

        public static void main(String args[]) {
          System.out.println("BlockTest bt = new BlockTest();");
          BlockTest bt = new BlockTest();

          System.out.println("BlockTest bt2 = new BlockTest();");
          BlockTest bt2 = new BlockTest();
        }
      }

      --------------------------------------[output]------------------------------------

      static{ }
      BlockTest bt = new BlockTest();
      { }
      생성자
      BlockTest bt2 = new BlockTesT();
      { }
      생성자
      ```
      * Class initialization block : instance initialization block + static
        * 클래스가 메모리에 처음 로드될 때 한번만 수행된다.
      * Instance initialization block : 클래스 내부 {}에 코드 작성
        * 생성자 같이 인스턴스 생성할 때 마다 수행되지만 생성자보다 먼저 호출된다.
        * 모든 생성자에서 공통적으로 수행되는 코드를 인스턴스 초기화 블록에 넣어둘 수 있다.

    * JVM의 종류에 따라 클래스의 로딩시기가 달라질 수 있다. 클래스가 필요할 때 바로 메모리에 로딩하도록 설꼐가 되어있는 것도 있고, 실행효율을 높이기 위해서 사용될 클래스들을 프로그램이 시작될 때 미리 로딩하도록 되어있는 것도 있다.



## 배열

```
    int[][] arrays;
    int arrays[][];
    intp[] arrays[];
```
* C++과 기본 개념은 같다. 
  
2차원 배열일 때 주소값은 어떻게 될까???

## 객체지향
* 기본 속성
* 제어자
  * 접근 제어자
    ```
      public > protected > default > private
    ```
    * default : 같은 패키지, 클래스 내에서만 접근 가능하다.
    * protected : 같은 패키지, 다른 패키지의 자식 클래스 내에서만 접근이 가능하다.
    * private : 같은 클래스 내에서만 접근이 가능하다.
      * 생성자를 private로 선언해서 인스턴스의 생성을 제한할 수 있다. 이런 경우 부모 클래스가 될 수 도 없기에 final 클래스로 선언해주는 것이 더 명시적이다.
      ```
      class Singleton {
        private static Singleton instance = new Singleton();

        private Singletone() {
          // 생성자
        }

        private static Singleton getInstance() {
          return instance;
        }
      }
      ``` 
      * private는 클래스 내부에서 호출이 가능하기 때문에 static에서 new Singleton()이 호출 성공한다.
  * static
    * static은 멤버변수, 메서드, 초기화 블럭에서 사용할 수 있다. 
    * 클래스가 메모리에 로드될 때 생성된다.
  * final
    * C++의 const와 같은 역할을 한다.
    * 변수에 사용하면 상수, 메서드에 사용하면 오버라이딩이 안되고 클래스에 사용되면 자식 클래스를 갖지 못한다.
    * 특이하게 생성자를 이용해 final 매개변수를 최초 1회 초기화 가능하다.
    ```
      final class FinalTest {         // 부모가 될 수 없는 클래스
        final int MAX_SIZE = 10;      // 상수
        final void getMaxSize() {     // 오버라이딩 할 수 없는 메서드
          final int LV = MAX_SIZE;    // 상수
          return MAX_SIZE;
        }
      }
    --------------------------------------------------------------------
    // 생성자 final 초기화
    class Card {
      final int NUMBER;
      final int KIND;

      Card(int kind, int num) {
        NUMBER = num;
        KIND = kind;
      }
    }
    ``` 
  * abstract
    * 메서드의 선언부만 작성하고 실제 수행내용은 구현하지 않은 **추상 메서드** 선언에 사용한다.
    * 추상 클래스는 클래스 내에 추상 메서드가 선언됨을 알리는 역할을 한다.
    ```
    abstract class AbstractTest {   // 추상 메서드를 포함한 클래스 (추상 클래스)
      abstract void move();         // 추상 메서드
    }
    ``` 
  1. 메서드에 static, abstract를 함께 사용할 수 없다. -> static은 body가 필요하다.
  2. abstract, final을 함께 사용할 수 없다.
  3. abstract의 접근자가 private일 수 없다.
  4. 메서드에 private, final을 같이 사용할 필요는 없다. 둘중 하나만 해도 오버라이딩이 불가능하기 때문이다.
* 다형성 : 부모 클래스의 참조변수로 자식 클래스의 인스턴스를 참조할 수 있도록 한 것.
  ```
  // 자식 타입으로 조상 인스턴스 참조 - 오류
    Child c = new Parent();   // 자식 클래스의 멤버를 갖고있지 않아서 오류발생
    Parent p = new Child();   // 가능
  ```
  * 참조변수
    * 서로 상속관계에 있는 참조변수만 **형변환**이 가능하다.
    ```
      class Car {
        String  color;
        int     doorCount;
        void drive() {
          Systsem.out.println("Drive");
        }
        void stop() {
          System.out.println("Stop");
        }
      }

      class FireEngine extends Car {
        void water() {
          System.out.println("Water");
        }
      }

      clss Ambulance extends Car {
        void siren() {
          System.out.println("Siren");
        }
      }

    ----------------------------------------------------------------

    class Test {
      pullic static void main(String[] args) {
        Car car = null;
        FireEngine fe = new FireEngine();
        FireEngine fe2 = null;

        car = fe;               // Up-Casting : 형변환 생략 가능
        fe2 = (FireEngine)car;  // Down-Casting : 형변환 생략 불가능
      }
    }

    ----------------------------------------------------------------
    // 상속을 이용한 참조변수의 다형성
    class Product {
      int price;
      int bonusPoint;
    }

    class Tv extends Product { }
    class Computer extends Product { }
    class Audio extends Product { }

    class Buyer {
      Vector item = new Vector();
      // Product[] items = new Product[10];

      void buy(Product p)
      {
        System.out.println(p);
      }
    }
    ``` 
    * 형변환은 참조변수의 타입만 변화시키는 것 이기 때문에 인스턴스에는 영향을 주지 않는다. 단지 참조변수의 형변환을 통해서 참조하고 있는 인스턴스에서 사용할 멤버의 범위를 조절하는 것 뿐이다.
    * 모든 클래스는 Object 클래스를 상속 받고 있다.
    * 참조변수의 출력이나 덧셈연산자를 이용한 문자열 결합에는 toString()이 자동적으로 호출된다.
  * instanceof
    * 참조변수가 참조하고 있는 인스턴스의 실제 타입을 알아보는 용도로 사용
    * return is boolean
    ```
      public class InstanceofTest {
        public static void main(String[] args) {
            FireEngine fe = new FireEngine();

            if(fe instanceof FireEngine) {
                System.out.println("This is a FireEngine instance.");
            }

            if(fe instanceof Car) {
                System.out.println("This is a Car instance");
            }

            if(fe instanceof Object) {
                System.out.println("This is a Object instance");
            }
        }
      }

      class Car {}
      class FireEngine() {}
      -------------------------------------------------------------------------
      This is a FireEngine instance.
      This is a Car instance
      This is a Object instance
    ``` 
* 추상클래스
  * 추상메서드를 포함하는 클래스, 추상 메서드를 포함하지 않은 클래스에 abstract 를 붙이면 인스턴스 생성을 할 수 없게된다. (추상 클래스는 인스턴스 생성을 할 수 없다.)
  * 이를 사용하는 이유는 자식 클래스에서 오버라이딩을 강제하기 위해서이다.
* 인터페이스
  * 추상 클래스보다 더 추상적인 개념이다. 
  * 오직 추상 메서드(public abstract)와 상수(public static final)만을 멤버로 가질 수 있다.
  * 인터페이스는 extends가 아니라 implements 키워드를 사용한다.
  * 인터페이스는 다중 구현 가능하며, 상속과 구현을 동시에 할 수도 있다.

## 예외처리
  프로그램 실행 중 발생할 수 있는 예외에 대한 처리를 함으로써 프로그램의 비정상 종료를 막고 정상적인 실행상태를 유지하는 것.
* 컴파일 에러 : 컴파일 할 때 발생하는 에러
* 런타임 에러 : 프로그램의 실행도중에 발생하는 에러
  * Error   : OutOfMemoryError, StackOverflowError - 복구 불가능
  * Exception : 복구 가능
* 에러의 종류
  * RuntimeException : 프로그래머의 실수로 발생하는 예외 - 컴파일러 오류 x
  * Exception : 사용자의 실수와 같은 외적인 요인에 발생하는 예외
* try-catch에서 생성된 예외 클래스의 인스턴스에 instanceof연사자를 이용해 일치하는지 순차적으로 검사 (C++ switch와 같은 로직)
* try-catch 마지막에 Exception 클래스 타입 참조변수를 선업합시다.

## 문자열
  ### String
    public final class String implements java.io.Serializable, Comparable 
    {
      /* *The value is used for character storage.*/
      private char[] value;
      ...
    }
  * String 클래스는 final 형식이다. 그래서 인스턴스가 갖고 있는 문자열(Value)는 변경할 수 없다. 사용할 때 + 연산자를 이용해 문자열 결합되는 상황은 기존 문자열에 추가되는 것이 아니라 새로운 문자열을 가진 인스턴스가 생성되는 것 이다.
  * String 생성자를 사용해 인스턴스 생성하는 것과 문자열 리터럴을 이용해 인스턴스 생성한 것은 인스턴스 주소 값에 차이를 갖는다.
    ```
      String s1 = "AAA"
      String s2 = new String("AAA");

      if(s1==s2)
        System.out.println("s1==s2");
      else
        System.out.println("s1!=s2");

      if(s1.equals(s2))
        System.out.println("s1.equals is true");
      else
        System.out.println("s1.equals is false");
    ``` 

  ### StringBuffer

## 참고
* 『혼자 공부하는 자바』신용권저, 한빛미디어 
* 『자바의 정석 2판』 남궁 성저, 도우 출판