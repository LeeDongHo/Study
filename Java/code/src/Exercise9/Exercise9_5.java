package Exercise9;

public class Exercise9_5 {
    public static void main(String[] args) {
        System.out.println(Count("12345AB12AB345AB","AB"));
        System.out.println(Count("12345","AB"));
    }

    public static int Count(String src, String target)
    {
        int count = 0;
        int startPosition = 0;
        int findPosition = 0;

        while(true) {
            findPosition = src.indexOf(target,startPosition);
            if (findPosition == -1) {
                return count;
            }
            count++;
            startPosition = findPosition + target.length();
        }


    }
}
