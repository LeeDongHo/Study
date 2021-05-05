package junkseok.ch14;

@FunctionalInterface
interface MyFunction1 {
    void myMethod();    // == public abstract void myMethod()
}

public class LambdaEx2 {
    public static void main(String[] args) {
        // == MyFunction1 function = (MyFunction1)(()->{});
        MyFunction1 function = () -> {};
        // Object 타입으로 형변환 생략됨
        // 형 변환 순서 : 익명 객체 -> 함수형 인터페이스 -> 오브젝트
        Object obj = (MyFunction1)(()->{});
        String str = ((Object)(MyFunction1) (()->{})).toString();

        System.out.println(function);
        System.out.println(obj);
        System.out.println(str);

        //System.out.println(()->{});   에러 : 람다식은 함수형 인터페이스로만 형변환 가능해서 Object 타입 형변환은 안된다.
        System.out.println((MyFunction1)(()->{}));
        //System.out.println((MyFunction1)(()->{}).toString());
        System.out.println(((Object)(MyFunction1)(()->{})).toString());
    }
}


/*
[output]
    객체 : 외부클래스이름$번호
    람다 : 외부클래스이름$$Lambda$번호
*/