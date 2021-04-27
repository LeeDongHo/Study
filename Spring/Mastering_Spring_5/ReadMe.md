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

#### **[스프링 빈 등록]**
1. **@Component**   
@Component를 이용하면 스프링빈 등록과 컴포넌트 스캔을 자동으로 설정할 수 있다. 스프링에서 흔히 사용하는 @Controller, @Repository, @Service 어노테이션들을 살펴보면 @Component를 가지고있다. <br> 

2. **@Configuration**   
