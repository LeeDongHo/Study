package junkseok.ch10;

import java.util.*;

public class CalendarEx6 {
    public static void main(String[] args) {
        if(args.length != 2) {
            System.out.println("Usage : java CalendarEx6 2015 9");
            return;
        }
        int year = Integer.parseInt(args[0]);
        int month = Integer.valueOf(args[1]);
        int START_DAY_OF_WEEK = 0;
        int END_DAY = 0;

        Calendar sDay = Calendar.getInstance();
        Calendar eDay = Calendar.getInstance();

        // 월의 경우 0 부터 11까지의 값을 가진다.
        sDay.set(year, month-1, 1);
        eDay.set(year,month,1);

        // 다음달의 첫 날에서 하루를 빼면 현재달의 마지막 날이 된다.
        //eDay.getActualMaximum(Calendar.DATE) 도 같다.
        eDay.add(Calendar.DATE,-1);
        START_DAY_OF_WEEK = sDay.get(Calendar.DAY_OF_WEEK);
        END_DAY = eDay.get(Calendar.DATE);

        System.out.println("\t"+year+"년 "+month+"월");
        System.out.println("  일 월 화 수 목 금 토");

        // 해당 월의 1일이 어느 요일인지 에 따라 빈칸으로 내비둔다
        for(int i=1;i<START_DAY_OF_WEEK; i++) {
            System.out.print("   ");
        }

        for(int i=1, n = START_DAY_OF_WEEK ; i<END_DAY ; i++, n++) {
            System.out.print((i<10)?"  "+i : " "+i);
            if(n%7 == 0) System.out.println();
        }
    }
}
