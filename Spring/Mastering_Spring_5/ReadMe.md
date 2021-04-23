# MASTERING SPRING 5
<p align="center">
<img src="image/Headline.jfif" alt="표지" width = 300>   
</p>

[스프링 5 마스터 2/e](http://www.yes24.com/Product/Goods/92339993?OzSrank=2)   
2021.04.21. ~

:ballot_box_with_check: : 완료   
:clock2: : 진행중
|번호|주제|소주제|진행|
|:--:|:--:|:--:|:--:|
|1|스프링 환경|[프레임워크, 모듈 및 프로젝트](#1-스프링과-스프링-부트-시작하기)|:clock2:|
---
## 1. 스프링과 스프링 부트 시작하기
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