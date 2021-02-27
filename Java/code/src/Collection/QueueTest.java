package Collection;
import java.util.*;

public class QueueTest {
    static Queue q = new LinkedList();
    static final int MAX_SIZE = 5;

    public static void main(String[] args) {
        System.out.println("help를 입력하면 도움말ㅇ르 볼 수 있습니다.");

        while(true)
        {
            System.out.println(">>");
            try {
                Scanner s = new Scanner(System.in);
                String input = s.nextLine().trim();

                if("".equals(input)) continue;
                if(input.equalsIgnoreCase("q")) {
                    System.exit(0);
                } else if(input.equalsIgnoreCase("help")) {
                    System.out.println(" help -  도움말을 보여줍니다.");
                    System.out.println(" q/Q -  프로그램을 종료합니다.");
                    System.out.println(" history -  최근에 입력한 명령어를 "
                                        +MAX_SIZE+"개 보여줍니다.");
                } else if(input.equalsIgnoreCase("history")) {
                    int i = 0;
                    save(input);

                    LinkedList tmp = (LinkedList)q;
                    ListIterator it = tmp.listIterator();

                    while(it.hasNext()) {
                        System.out.println(++i+"."+it.next());
                    }
                } else {
                    save(input);
                    System.out.println(input);
                }
            } catch(Exception e) {
                System.out.println("입력오류 입니다.");
            }
        }
    }

    public static void save(String input)
    {
        if(!"".equals(input))
            q.offer(input);

        if(((LinkedList)q).size() > MAX_SIZE)
            q.remove();
    }
}
