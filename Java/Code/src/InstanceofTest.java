class Car {
    String  color;
    int     doorCount;

    void drive() {
        System.out.println("Drive");
    }

    void stop() {
        System.out.println("Stop");
    }
}

class FireEngine extends Car {
    void water() {
        System.out.println("Water");
    }
}

public class InstanceofTest {
    public static void main(String[] args) {
        FireEngine fe = new FireEngine();

        if(fe instanceof FireEngine) {
            System.out.println("This is a FireEngine instance.");
        }

        if(fe instanceof Car) {
            System.out.println("This is a Car instance");
        }

        if(fe instanceof Object) {
            System.out.println("This is a Object instance");
        }
    }
}

