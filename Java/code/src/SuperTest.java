public class SuperTest {
    public static void main(String[] args) {
        Child node = new Child();
        node.method();
    }
}

class Parent{
    int x = 10;

    void method() {
        System.out.println("super.x="+x);
    }
}

class Child extends Parent {
    int x = 20;

    void method() {
        System.out.println("x="+x);
        System.out.println("this.x="+this.x);
        super.method();
    }
}

