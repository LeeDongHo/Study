package junkseok.ch14;

@FunctionalInterface
interface MyFunction2 {
    void myMethod(int j);
}

// 예제 7-35 -> Lambda
class Outer {
    // Outer.this.val
    int val = 10;

    class Inner {
        // this.val
        int val = 20;

        void method(int i) {
            int val = 30;

            MyFunction2 function = (j) -> {
                System.out.println("             i : " + i);
                System.out.println("             j : " + j);
                System.out.println("           val : " + val);
                System.out.println("      this.val : " + this.val);
                System.out.println("Outer.this.val : " + Outer.this.val);
            };

            function.myMethod(i/2);
        }
    }
}

public class LambdaEx3 {
    public static void main(String[] args) {
        Outer outer = new Outer();
        Outer.Inner inner = outer.new Inner();
        inner.method(101);
    }
}
