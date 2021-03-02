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
        date1.set(2025,7,15);   // 2015년 8월 15일
        System.out.println("date1은 "+toString(date1) + DAY_OF_WEEK[date1.get(Calendar.DAY_OF_WEEK)]+ "요일이고, ");
        System.out.println("오늘(date2)은 " + toString(date2) + DAY_OF_WEEK[date2.get(Calendar.DAY_OF_WEEK)]+"요일입니다.");

        // 두 날짜간 차이를 얻으려면, getTimeInMillis() 천분의 일초 단위로 변환해야한다.
        long difference = (date2.getTimeInMillis() - date1.getTimeInMillis()) / 1000;
        System.out.println("그 날(date1)부터 지금(date2) 까지" + difference + "초가 지났습니다.");
        System.out.println("일(Day)로 계산하면 " +difference/(24*60*60)+"일 입니다.");

        // 시간상 전 후를 알고싶은 경우 after사용
        System.out.println("그 날(date1)부터 지금(date2)은 " + ((date2.after(date1)) ? "과거" : "미래") + "입니다.");
    }

    public static String toString(Calendar date)
    {
        return date.get(Calendar.YEAR) + "년 " + (date.get(Calendar.MONTH)+1) + "월 " + date.get(Calendar.DATE) + "일";
    }
}
