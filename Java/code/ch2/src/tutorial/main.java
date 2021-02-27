package tutorial;

import java.awt.desktop.SystemSleepEvent;

public class main {
    public static void main(String[] args) {

        // java array
        //  type[][] name
        //  type name[][]
        //  type[] name[]
        int[][] scores = { {100,100,100},
            {20,20,20},
            {30,30,30},
            {40,40,40},
            {50,50,50} };

        int koreanTotal = 0;
        int englishTotal = 0;
        int mathTotal = 0;

        System.out.println("번호 국어 영어 수학 총점 평균 ");
        System.out.println("=========================");

        for(int i=0;i<scores.length;i++)
        {
            int sum=0;
            koreanTotal += scores[i][0];
            englishTotal += scores[i][1];
            mathTotal += scores[i][2];
            System.out.print(" " + (i+1) + " ");
            for(int j=0;j<scores[i].length;j++)
            {
                sum+=scores[i][j];
                System.out.print(scores[i][j]+" ");
            }
            System.out.println(sum + " " + sum/(float)scores[i].length);;
        }
        final int a = 1;

        System.out.println("=========================");
        System.out.println("총점:" + koreanTotal+" " + englishTotal + " " + mathTotal);

        System.out.println("2차원 배열 테스트");
        for(int i=0;i<scores.length;i++)
            System.out.println("Scores["+i+"] : "+scores[i]);

        // 커맨드 라인 통해 입력받기
        // java main asd 123
        System.out.println("매개변수 개수 : " + args.length);
        for(int i=0;i<args.length;i++)
            System.out.println("args["+i+"] = \"" + args[i]+"\"");
    }
}
