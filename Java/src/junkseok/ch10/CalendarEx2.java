package junkseok.ch10;

import java.util.*;

public class CalendarEx2 {
    public static void main(String[] args) {
        // 요일은 1부터 시작하기 때문에, DAY_OF_WEEK[0]은 비워두었ㄷ.
        final String[] DAY_OF_WEEK = {"","일","월","화","수","목","금","토"};

        Calendar date1 = Calendar.getInstance();
        Calendar date2 = Calendar.getInstance();

        // month의 경우 0부터 시작하기 때문에 8월인 경우, 7로 지정해야한다.
        // date1.set(2015, Calendar.AUGUST, 15)
        date1.set(2015,7,15);   // 2015년 8월 15일
        System.out.println("date1은 "+toString(date1) + DAY_OF_WEEK[date1.get(Calendar.DAY_OF_WEEK)]+ "요일이고, ");
        System.out.println("오늘(date2)은 " + toString(date2) + DAY_OF_WEEK[date2.get(Calendar.DAY_OF_WEEK)]+"요일입니다.");

        // 두 날짜간 차이를 얻으려면, 

    }

    public static String toString(Calendar date)
    {
        return date.get(Calendar.YEAR) + "년 " + (date.get(Calendar.MONTH)+1) + "월 " + date.get(Calendar.DATE) + "일";
    }
}
