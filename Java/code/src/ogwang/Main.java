import java.util.*;

public class Main {
    public static void main(String[] args) {
        JDBCTest jdbc = new JDBCTest();
        List<HashMap<String,Object>> memoList = jdbc.testSelect();
        System.out.println("==================================");
        System.out.println("no \t name \t msg \t writeday");
        System.out.println("==================================");
        if(memoList != null) {
            for(HashMap<String,Object> memo : memoList) {
                System.out.println(memo.get("NO")+"\t"+memo.get("NAME")+"\t"+memo.get("MSG")+"\t"+memo.get("WRITEDATE"));
            }
        }
        else
            System.out.println("== 입력된 데이터가 없습니다 ==");
    }
}



