package junkseok.ch12;

import java.lang.annotation.*;

@Deprecated
@SuppressWarnings("1111")       // 유효하지 않은 어노테이션 무시
@TestInfo(testedBy = "aaa", testDate = @DateTime(yymmdd = "210303", hhmmss = "161101"))
public class AnnotationEx5 {
    public static void main(String[] args) {
        // AnnotationEx5의 Class 객체를 얻는다.
        Class<AnnotationEx5> cls = AnnotationEx5.class;

        TestInfo anno = (TestInfo)cls.getAnnotation(TestInfo.class);
        System.out.println("anno.testedBy() = " + anno.testedBy());
        System.out.println("anno.testDate().yymmdd = " + anno.testDate().yymmdd());
        System.out.println("anno.testDate().hhmmss = " + anno.testDate().hhmmss());

        for(String str : anno.testTools())
            System.out.println("testTools="+str);

        System.out.println();

        // AnnotationEx5에 적용된 모든 어노테이션들을 가져온다.
        Annotation[] annoArr = cls.getAnnotations();

        for(Annotation a : annoArr)
            System.out.println(a);
    }
}

@Retention(RetentionPolicy.RUNTIME)
@interface TestInfo {
    int         count()     default 1;
    String      testedBy();
    String[]    testTools() default "JUnit";
    TestType    testType()  default TestType.FIRST;
    DateTime    testDate();
}

@Retention(RetentionPolicy.RUNTIME)
@interface DateTime {
    String  yymmdd();
    String  hhmmss();
}

enum TestType { FIRST, FINAL }
