package tutorial;

public class Exercise6_19 {

    public static void change(String str)
    {
        str += "456";
    }

    public static void main(String[] args) {
        String str = "ABC123";
        String str2 = new String("AAA123");
        System.out.println(str);
        System.out.println(str2);
        change(str);
        change(str2);
        System.out.println("After change :"+str);
        System.out.println("After change :"+str2);
    }
}
