package me.domo.java8.Optional;

import me.domo.java8.Steram.OnlineClass;

import java.time.Duration;
import java.util.*;

public class App {

    public static void main(String[] args) {
        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1,"spring boot",true));
        springClasses.add(new OnlineClass(2,"spring data jpa",true));
        springClasses.add(new OnlineClass(3,"spring mvc",false));
        springClasses.add(new OnlineClass(4,"spring core",false));
        springClasses.add(new OnlineClass(5,"rest api development",false));

        OnlineClass spring_boot = new OnlineClass(1,"spring boot",true);
        // (1) : null 체크 부재
        // Duration studyDuration = spring_boot.getProgress().getStudyDuration(); (1)
        // System.out.println(studyDuration);

        // (2) : null check를 잊을 수 있는 위험 존재
        // Progress progress = spring_boot.getProgress();
        // if(progress != null) {
        //      System.out.println(progress);
        // }



    }
}
