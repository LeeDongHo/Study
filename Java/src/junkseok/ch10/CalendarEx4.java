package junkseok.ch10;

import java.util.*;

public class CalendarEx4 {
    public static void main(String[] args) {
        Calendar date = Calendar.getInstance();
        date.set(2015,7,31);    // 2015년 8월 31일

        System.out.println(toString(date));
        System.out.println("= 1일 후 =");
        date.add(Calendar.DATE,1);
        System.out.println(toString(date));

        System.out.println("= 6달 전 =");
        date.add(Calendar.MONTH,-6);
        System.out.println(toString(date));

        // roll은 다른 필드 (연, 월, 일)에 영향을 주지 않는 연산이다.
        // 일자를 더해서 월이 넘어가야하지만 넘기지 않는다.
        // 말일에 월 필드를 변경하면 일 필드가 바뀔 수 있다.
        System.out.println("= 31일 후 (roll) =");
        date.roll(Calendar.DATE,31);
        System.out.println(toString(date));

        System.out.println("= 31일 후 (add)=");
        date.add(Calendar.DATE,31);
        System.out.println(toString(date));

        System.out.println("= 1달 1일 전 (roll)=");
        date.add(Calendar.DATE,-1);
        date.roll(Calendar.MONTH,-1);
        System.out.println(toString(date));
    }

    public static  String toString(Calendar date) {
        return date.get(Calendar.YEAR) +"년 "+(date.get(Calendar.MONTH)+1)+"월 "+date.get(Calendar.DATE)+"일";
    }
}
