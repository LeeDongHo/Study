# JSP&Servlet
자바 웹을 다루는 기술 - 길벗  
시작일 : 2021.03.15   
**Version**   
* JDK 11   
* Tomcat 10
* Eclipse 
* Oracle database 18c EX
* SQL Developer 20.4.1
---
```
.
├── README.md
└── JAVA_WEB
      └WEB-INF
         ├── classes
         ├── lib
         └── web.xml

```
|구성요소|기능|
|:--:|--|
  WEB-INF  | Web Application에 관한 정보가 저장되는 곳. 외부에서 접근 불가능|
  classes  | Web Application이 수행하는 서블릿과 다른 일반 클래스들이 위치|
  lib      | Web Application에서 사용되는 라이브러리 압축 파일(jar)이 저장. lib의 jar은 classpath자동설정|
  web_xml |배치 지시자(Deployment descriptor)로 일종의 환경 설정 파일
---
## **목차**
1. [이론](#이론)


## **이론**
1. JSP 동작 방식의 특징
   * 프로세스 방식이 아닌 스레드 방식으로 실행
   * 클라이언트 요구 처리 기능은 최초 1회 메모리에 로드 -> 재사용
2. 초기 동적 웹 페이지들은 서블릿으로 구현되었고, 서블릿의 단점을 보완해서 나온 것이 JSP이다.
3. 서블릿은 자바로 작성되어있어 **자바의 특성을 모두 가지지만 독자적으로 실행되지 못해 톰캣과 같은 JSP/Servlet 컨테이너에서 실행**된다.   
---
## **Servlet**
![서블릿 동작 과정](\readme_image\서블릿_동작_과정.PNG)

### **서블릿의 특징**
* 스레드 방식으로 실행
* 자바의 특징(OOP)를 계승
* 컨테이너 종류에 상관없이 독립적으로 실행됨
* 보안 기능을 적용하기 쉽다.
* 브라우저 요청시 기능 수행

### **Servlet Life Cycle**
|단계|메서드|특징|
|:--:|:--:|--|
|초기화|init()| 서블릿 요청시 맨 처음 한번만 호출되며, 서블릿 생성시 초기화 작업 수행|
|작업|doGet()<br>doPost()|서블릿 요청시 매번 호출, 실제 요청된 작업을 수행|
|종료|destroy()|서블릿이 메모리에서 소멸될 때 호출, 마무리 작업 수행|
* init(), destroy()는 요구사항에 따라 생략할 수 있다.
* **doXX()** 는 서블릿의 핵심 기능을 처리하므로 **반드시 구현**해야한다.
* doGet(HttpServletRequest req, HttpServletResponse res) => req, res 객체 생성 후 서블릿의 메서드를 호출한다.

### **Servlet Mapping**
**1. web.xml**
```XML
<!--1번째-->
  <servlet>
  	<servlet-name>aaa</servlet-name>
  	<servlet-class>sec01.ex01.FirstServlet</servlet-class>
  </servlet>

  <servlet-mapping>
  	<servlet-name>aaa</servlet-name>
  	<url-pattern>/first</url-pattern>
  </servlet-mapping>

<!--2번째-->
  <servlet>
  	<servlet-name>bbb</servlet-name>
  	<servlet-class>sec01.ex01.SecondServlet</servlet-class>
  </servlet>

  <servlet-mapping>
  	<servlet-name>bbb</servlet-name>
  	<url-pattern>/second</url-pattern>
  </servlet-mapping>
```
* <Servlet-name\> 이 <servlet\>와 <servlet-mapping\>을 연결하는 기준점이다.
* 여러개의 서블릿을 맵핑할 땐 <servlet\>와 <servlet-mapping\>을 각각 분리해야한다.   

**2.Annotation**
```Java
import javax.servlet.annotation.WebServlet;

@WebServlet("/third")
public class ThirdServlet extends HttpServlet {
   ...
}
```
* Annotation으로 맵핑하는 클래스는 반드시 HttpServlet를 상속받아야한다.   

```
http://ip:port/project/package.class
-> http://localhost:8080/pro1/sec01.ex01.FirstServlet

http://ip:port/project/servlet-mapping
-> http://localhost:8080/pro1/aaa
```
---
## **변경점**
1. p.085 - **create user scott identified by tiger;**
   * ORA-65096 : 공통 사용자 또는 롤 이름이 부적합합니다.
      * oracle 12c 부터 계정앞에 C## 추가   
      [[common_user_prefix]](https://docs.oracle.com/database/121/REFRN/GUID-516ADCCF-3661-4B54-908A-7041854EA14F.htm#REFRN10354) / [[ErrorCode]](https://docs.oracle.com/en/database/oracle/oracle-database/18/errmg/ORA-60001.html#GUID-9B78A028-D760-4810-9CFC-9013FBD1FCC9)   
   * TODO : **create user C##scott identified by tiger;**
2.