# MASTERING SPRING 5
<p align="center">
<img src="image/Headline.jfif" alt="표지" width = 300>   
</p>

[스프링 5 마스터 2/e](http://www.yes24.com/Product/Goods/92339993?OzSrank=2)   
2021.04.21. ~   

---
* IntelliJ
* JDK 11
---   
:ballot_box_with_check: : 완료   
:clock2: : 진행중
|번호|주제|진행|
|:--:|:--:|:--:|
|1|[스프링 환경](#1-스프링-환경)|:ballot_box_with_check:|
|2|[의존 관계 주입 및 단위테스트](#2의존-관계-주입-및-단위-테스트-하기) |:clock2:|
---
## 1. 스프링 환경
<details>
<summary> 다루는 내용 </summary>
&nbsp;&nbsp;&nbsp;&nbsp;1. Spring framework가 인기있는 이유<br>
&nbsp;&nbsp;&nbsp;&nbsp;2. Spring framework는 어플리케이션 아키텍처의 진화에 어떻게 적응했을까?<br>
&nbsp;&nbsp;&nbsp;&nbsp;3. Spring framework에서 중요한 모듈은?<br>
&nbsp;&nbsp;&nbsp;&nbsp;4. Spring framework는 스프링 프로젝트 중 어디에 적합한가?<br>
&nbsp;&nbsp;&nbsp;&nbsp;5. Spring framework 5.0 & 5.1    <br>
</details>

---
스프링 프레임워크는 20개 이상의 서로 다른 모듈로 구성된다. 스프링 모듈은 스프링 프레임워크의 핵심 기능(DI, MVC, AOP 등)을 제공해 스프링 모듈에서 필요한 모듈을 선택할 수 있고 아키텍처 부분에서는 **MSA**가 도입되고 있다.

스프링 프로젝트에서 중요한 부분은 스프링 부트, 스프링 클라우드, 스프링 데이터, 스프링 배치 및 시큐리티다. 

### **스프링**
스프링 프레임워크 첫 번째 버전은 '테스트 가능한 코드 작성'과 '느슨하게 연결된 웹 어플리케이션 개발'에서 겪는 어려움을 해결하는게 주요 과제였다. 시간이 흘러 SOAL(Simple Ojbect Access Protocol)에서 REST 까지 훌륭한 웹 서비스 개발하는 것으로 과제가 바뀌었다.

> 스프링 프레임 워크   
> 스프링 프레임워크는 최신 자바 기반 엔터프라이즈 어플리케이션을 위한 포괄적인 프로그래밍과 구성 모델을 제공한다.    
> [출처](https://projects.pring.io/spring=framework/)

자바 클래스가 종속 인스턴스를 직접 작성하면 클래스 간에 밀접한 결합이 형성된다 (C++ 게임서버 만들 때 멤버 변수로 다른 클래스 인스턴스 보유한 것과 같은 상황). 스프링에서는 객체를 만들고 연결하는 책임을 **IoC(Inversion of Control) 컨테이너**에 넘긴다.

스프링 프레임워크가 인기 있던 이유는 아래와 같다.
#### **[느슨한 결합 및 테스트 가능성]**
클래스 사이의 결합이 느슨하면 장기적으로 어플리케이션을 유지하고 관리하는데 도움이 되며 ***테스트가 가능하다는 이점***이 있다. 

이전 버전의 자바 EE 어플리케이션에서 코드 테스트를 하기 위해선 컨테이너에 코드를 배포하는 것이 유일한 방법이었다.   
하지만 DI는 의존 관계를 Mock로 쉽게 대체해 단위 테스트를 가능하게 해준다.
#### **[단위 테스트 단순화로 얻을 수 있는 이득]**
* 프로그래머의 생산성 향상
* 결함의 조기 발견으로 비용 절감
* 어플리케이션에는 CI(Continous Integration) 빌드에서 실행할 수 있는 자동화 단위 테스트가 있어 향후 결함 방지 가능.
  
>**CI (Continuous Integration)** 는 코드가 버전 제어에 전념하자마자 모든 자동화된 테스트와 배포를 실행한다. 깨진 테스트나 기능을 발견하는 즉시 보고된다.

#### **[아키텍처 유연성]**
스프링은 어플리케이션의 서로 다른 부분들 간의 결합을 줄이고, 테스트할 수 있게 만드는 핵심 작업에 중점을 두면서 사용자가 선택한 프레임워크와 훌륭한 통합 기능을 제공한다. 또 특정 프레임워크를 사용하고 싶지 않으면 다른 프레임워크로 쉽게 대체가 가능하다.
* 스프링 빈은 비즈니스 로직의 간단한 구현을 제공하며, EJB와도 통합 된다.
* JDBC, JPA, 하이버네이트, iBatis등 선호하는 데이터 레이어 프레임워크를 완벽하게 지원한다.
* AOP(Aspect Oriented Framework)를 제공해 크로스 컷팅 (로깅, 트랜잭션 관리, 시큐리티 등)을 구현할 수 있다. AspectJ도 통합할 수 있다.
* 스프링은 MVC 뿐만 아니라 Struts, Vaadin, JSF등 선택한 모든 웹 프레임워크를 지원한다.

#### **[복잡한 코드 감소]**
이전의 J2EE 어플리케이션엔 데이터베이스 연결, 예외처리, 트랜잭션 관리, 로깅 등 복잡한 코드가 많이 표현됐다.
```java
PreparedStatement st = null;
try {
    st = conn.prepareStatement(INSERT_TODO_QUERY);
    // 비즈니스 로직
} catch (SQLException e) {
    logger.error("Failed : " + INSERT_TODO_QUERY, e);
} finally {
    if(!st) {
        try {
            st.close();
        } catch (SQLException e) {
            // 무시...
        }
    }
}
```
위 코드를 스프링 프레임워크는 아래와 같이 개선해준다.
```java
jdbcTemplate.update(INSERT_TODO_QUERY, bean.getDesciption(), bean.isDone());
```

> 스프링은 예외 처리 로직을 중앙 집중화하고 한 곳에서 처리하는 방법을 제공한다. 또 모든 복잡한 코드는 래퍼 클래스에서 구현처리함으로 개발자들은 간결하게 코드를 작성할 수 있게 됐다.
---
## 2.의존 관계 주입 및 단위 테스트 하기
이번 장에서는 의존 관계가 무엇인지 이해하고 DI의 필요성을 탐구한다. 
<details>
<summary> 다루는 내용 </summary>
&nbsp;&nbsp;&nbsp;&nbsp;1. 의존 관계란 무엇인가?<br>
&nbsp;&nbsp;&nbsp;&nbsp;2. 의존 관계 주입(DI)이란 무엇일까?<br>
&nbsp;&nbsp;&nbsp;&nbsp;3. DI를 올바르게 사용하면 어플리케이션을 테스트 할 수 있을까?<br>
&nbsp;&nbsp;&nbsp;&nbsp;4. Spring은 빈 팩토리와 ApplicationContext로 DI를 어떻게 구현할까?<br>
&nbsp;&nbsp;&nbsp;&nbsp;5. 컴포넌트 스캔이란?    <br>
&nbsp;&nbsp;&nbsp;&nbsp;6. 자바와 XML 어플리케이션 컨텍스트의 차이점은?    <br>
&nbsp;&nbsp;&nbsp;&nbsp;7. 스프링 컨텍스트의 단위 테스트는 어떻게 작성할까?    <br>
&nbsp;&nbsp;&nbsp;&nbsp;8. 모킹은 단위 테스트를 어떻게 쉽게 처리할까?    <br>
&nbsp;&nbsp;&nbsp;&nbsp;9. 다른 빈 스코프는 무엇일까?    <br>
&nbsp;&nbsp;&nbsp;&nbsp;10. CDI란 무엇이며 스프링은 CDI를 어떻게 지원할까?    <br>
</details>
---

### **의존 관계**
객체지향 어플리케이션은 객체끼리의 상호 작용을 중심으로 구축된다. 일반적인 어플리케이션은 서로 상호작용하는 수천개의 개체를 포함한다. (SOLID 원리 중 SRP 원칙을 준수한 결과)
```java
// DatServiceImpl은 BusinessServiceImpl의 의존관계
public class BusinessServiceImpl {
    public long calculateSum(User user) {
        DataServiceImpl dataService = new DataServiceImpl();    // 인스턴스 생성
        long sum = 0;
        for(Data data : dataService.retrieveData(user)) {
            sum += data.getValue();
        }
        return sum;
    }
}
```

#### **[단단한 결합]**
```java
DataServiceImpl dataService = new DataServiceImpl(); // DataServiceImpl2();
```
위 코드가 단단한 결합인 이유는 DataServiceImpl 클래스가 상위 클래스에 종속되기 때문이다. 만약 생성할 클래스가 바뀌면 코드도 수정해줘야한다.

#### **[느슨한 결합]**
```java
public interface DataService {
    ...
}

public class BusinessServiceImpl {
    private final DataService dataService;

    public BusinessServiceImpl BusinessServiceImpl(DataService dataService) {
        this.dataService = dataService;
    }

    public long CalculateSum(User user) {
        long sum = 0;
        for (Data data : dataService.retrieveData(user)) {
            sum += data.getValue();
        }
        return sum;
    }
}
```
위 코드와 같이 인터페이스, 생성자를 이용하면 느슨한 결합을 사용할 수 있다. BusinessServiceImpl 클래스도 인터페이스로 분리하면 더 느슨하게 만들 수 있다.

#### **[용어 이해]**
* **Bean** : 인스턴스. 위 예제에서는 businessService, datService가 해당.
* **와이어링** : dataService를 생성해 businessService의 생성자에 인수 전달한 것을 말함
* **DI** : 빈 식별, 생성 및 와이어링 의존 관계 프로세스
* **IoC** : 의존 관계 책임을 프레임워크로 넘기는 것.

#### **[스프링 빈 활용]**
[@Component vs @Configuration - Notion](https://www.notion.so/Component-Configuration-36b96fb4abb94d50bc63b2d890981304)
1. **@Component**   
@Component를 이용하면 스프링빈 등록과 컴포넌트 스캔을 자동으로 설정할 수 있다. 스프링에서 흔히 사용하는 @Controller, @Repository, @Service 어노테이션들을 살펴보면 @Component를 가지고있다. <br> 
가능하다면 @Component보다 @Service 같은 특수 어노테이션을 사용하는 것이 좋다. **특수 어노테이션은 추가 기능을 제공**하기 때문이다.

2. **@Autowired**   
@Autowired 어노테이션을 지정해 인스턴스 변수에 빈을 주입할 수 있다.
    ```java
        @Service
        public class BusinessServiceImpl {
            @Autowired
            private DataService dataService;
        }

        @Repository
        public class DataServiceImpl implements DataService {
            public List<Data> retrieveData(User user) {
                return Arrays.asList(new Data(10), new Data(20));
            }
        }
    ```

3. **어플리케이션 컨텍스트 자바 구성**
    ```java
        @Configuration
        @ComponentScan(basePackages = {"com.mastering.spring"})
        class SpringContext {

        }
    ```
    * kr.mastering.spring 패키지를 스캔하고 BusinessServiceImpl과 DataServiceImpl 빈을 찾는다.
    * DataServiceImpl은 의존 관계가 없어서 이를 위한 빈이 생성된다.
    * BusinessServiceImpl은 DataServiceImpl에 의존하고, DataServiceImpl은 DataService의 구현체다. 따라서 오토 와이어링에 기준을 충족해 BusinessServiceImpl이 생성되고 기존에 생성된 DataServiceImpl빈이 setter를 통해 자동으로 연결된다.
    ```java
        public class LaunchJavaContext {
            private static final User DUMMY_USER = new User("dummy");

            public static Logger logger = Logger.getLogger(LaunchJavaContext.class);

            public static void main(String[] args) {
                
                ApplicationContext context = new AnnotationConfigApplicationContext(SpringContext.class);

                // 컨텍스트 시작되면 비즈니스 서비스 빈을 가져와야한다. 
                // getBean() : 빈 유형을 전달
                BusinessService service = context.getBean(BusinessService.class);

                logger.debug(service.calculateSum(DUMMY_USER));
            }
        }
    ```
    * 빈과 의존 관계 식별을 위해 컴포넌트 스캔
    * 빈이 생성되고 필요에 따라 의존 관계 연결
4. **어플리케이션 컨텍스트 XML 구성**   
   **[XML 스프링 구성 정의]**   
    ```XML
    <?xml version="1.0" encoding="UTF-8" standalone="no"?>
    <beans> <!-- Namespace definitions removed-->
    <context:component-scan base-package = "kr.mastring.spring"/>
    </beans>
    ```    
    구성요소 스캔은 **context : component-scane**을 사용해 정의   

    **[XML 구성으로 어플리케이션 시작]**
    ```java
    public class LaunchXmlContext {
        private static final User DUMMY_USER = new User("dummy");
        public static Logger logger = Logger.getLogger(LaunchJavaContext.class);
        public static void main(String[] args) {
            ApplicationContext context = new ClassPathXmlApplicationContext(
                "BusinessApplicationContext.xml");
            BusinessService service = context.getBean(BusinessService.class);
            logger.debug(service.calculateSum(DUMMY_USER));
            )
        }
    }
    ```
5. **@Autowired**
    
    의존 관계에서 이 어노테이션을 사용하면 어플리케이션 컨텍스트는 일치하는 의존 관계를 검색한다. 기본적으로 오토와이어링된 모든 의존 관계가 필요하다.    
    * **일치 항목 1개** : 찾고 있는 의존 관계
    * **일치 항목 2개 이상 / 없음** : 오토 와이어링 실패   
    ****
    **[@Primary]**   
    특정 의존 관계를 오토 와이어링 할 수 있는 후보가 2개 이상일때 @Primary 어노테이션을 사용한 빈이 호출된다.
    ```java
    interface SortingAlogrithm {}

    @Component
    class MergeSort implements SortingAlgorithm {
        ...
    }

    @Component
    @Primary
    class QuickSort implements SortingAlgorithm {
        ...
    }
    ```
    ****
    **[@Qualifier]**   
    스프링 빈에 참조를 주기 위해 사용된다. 참조는 오토와이어링 할 필요가 있는 의존 관계를 규정하는데 사용된다.
    ```java
    @Component
    @Qualifier("mergesort")
    class MergeSort implements SortringAlgorithm {
        ...
    }

    @Component
    class QuickSort implements SortingAlgorithm {
        ...
    }

    @Component
    class SomeService {
        @Autowired
        @Qualifier("mergesort")
        SortingAlgorithm algorithm;
    }
    ```
    @Qualifier("mergesort")를 사용했기 때문에 MergeSort 한정자가 정의돼 있는 MergeSort가 선택된다.
    
6. **의존 관계 주입 방법**   
    **[Setter]**   
    ```java
    public class BusinessServiceImpl {
        private DataService dataService;
        @Autowired
        public void setDataService(DataService dataService) {
            this.dataService = dataService;
        }
    }
    ```
    변수에 @Autowired를 선언하면 스프링은 자동으로 setter 주입을 사용하기 때문에 setter를 선언해줄 필요는 없다.
    ```java
    public class BusinessServiceImpl {
        @Autowired
        private DataService dataService;
    }
    ```
    ****
    **[Constructor]**   
    ```java
    public class BusinessServiceImpl {
        private DataService dataService;
        
        @Autowired
        public BussinessServiceImpl(DataService dataService) {
            super();
            this.dataService = dataService;
        }
    }
    //--------------------------------------------------------------------
    Autowiring by type from bean name 'businessServiceImpl' via
    constructor to bean named 'dataServiceImpl'
    ```   

    setter 주입을 사용하면 생성 중에 오브젝트가 변경될 수 있다. 스프링에서 주입한 빈이 변경되는 경우는 드물기 때문에 생성자를 사용하는 것이 옳다고 한다.   
    setter를 사용하면 생성자의 크기가 감소하고 의존 관계가 있음을 숨길 수 있긴 하다.

7. **[스프링 빈 스코프 (Scope)]**   
    스프링 빈의 기본 스코프는 SingleTon이다. (JVM당 하나의 인스턴스만 있는 자바 클래스, 스프링에서 싱글톤은 **스프링 어플리케이션 컨텍스트 당 하나의 인스턴스를 가진 클래스**이다.)
    
    싱글톤 빈의 인스턴스는 하나뿐이므로 Request와 관련된 데이터를 포함할 수 없으며 스코프는 @Scope 어노테이션과 함께 제공된다.
    ```java
    @Service
    @Scope("singleton")
    public class BusinessServiceImpl implements BusinessService {
        ...
    }
    ```
    ****
    |스코프|사용|
    |:--:|:--|
    |**싱글톤**| 기본 스코프. 빈의 인스턴스는 여러 참조가 있어도 IoC 컨테이너 인스턴스당 하나만 사용/생성된다. <br> 이 인스턴스는 캐싱되 빈을 사용하는 모든 후속 요청에 사용된다. 단일 JVM에 여러 스프링 컨테이너가 있다면 같은 빈의 인스턴스는 여러개 있을 수 있다.|
    |**프로토타입**| 스프링 컨테이너에서 빈을 요청할 때 마다 새 인스턴스 생성. 빈에 상태가 포함된 경우 이 스코프를 사용하는 것이 좋다.|
    |**리퀘스트**|스프링 웹 컨텍스트에서만 사용할 수 있고, 모든 HTTP 요청마다 빈의 새 인스턴스가 생성된다. <BR> 빈의 요청이 완료되는 즉시 폐기되며, 단일 요청과 관련된 데이터를 보유하고 있는 빈에 이상적이다.|
    |**세션**|스프링 웹 컨텍스트에서만 사용할 수 있으며, 모든 HTTP Session 마다 빈의 새로운 인스턴스가 생성된다. 웹 어플리케이션 사용자 권한과 같이 단일 사용자 고유의 데이터에 적합하다.|
    |**어플리케이션**|스프링 웹 컨텍스트에서만 사용할 수 있으며, 웹 어플리케이션당 하나의 빈 인스턴스로 특정 환경의 어플리케이션 구성에 적합하다.|


8. **[스프링 어노테이션 (일부)]**

    |어노테이션|사용|
    |:--:|:--|
    |**@ScopedProxy**|Request, Session bean을 sigleton bean에 주입해야때 사용.<br> singleton scope bean에 주입되는 스마트 프록시 제공|
    |**@Component, @Service,<br>@Controller, @Repository**|@Component : 스프링 빈 정의하는 가장 일반적인 방법 <br> @Service : 비즈니스 서비스 레이어에서 사용 <br> @Repository : DAO에서 사용 <BR> @Controller : 프레젠테이션 구성으로 사용|
    |**@PostConstruct**|모든 스프링 빈에서 이 어노테이션을 이용해 post construct 메소드를 제공<br> post construct 메소드는 빈이 의존 관계로 완전이 초기화된 후에 호출, 빈 생명 주기 동안 한 번만 호출됨|
    |**@PreDestory**|컨테이너에서 빈이 제거되기 전에 호출되며, 빈이 보유한 모든 리소스를 해제하는데 사용. -> 리소스 누수 방지|

#### **[CDI 탐색]**
**CID (Context and Dependency Injection)** 의 목표는 DI 수행 방법의 기본을 표준화 하는 것이다.
> 스프링은 JSR-330에 정의된 표준 어노테이션을 지원한다.
> [Link](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-standard-annotations)

<br>

---

### **단위 테스트**
---
#### **[JUnit]**