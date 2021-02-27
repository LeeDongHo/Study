final class Singleton {

    private static Singleton instance = new Singleton();

    private Singleton() {

    }

    public static Singleton getInstance() {
        return instance;
    }
}

public class PrivateConstructor {
    public static void main(String[] args) {
        //Singleton s = new Singleton();
        Singleton s1 = Singleton.getInstance();
    }
}