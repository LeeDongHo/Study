package junkseok.ch14;

@FunctionalInterface
interface MyFunction {
    public abstract void run();
    //void run();
}

public class LamdaEx1 {
    // 매개변수 타입이 MyFunction인 메서드
    static void execute(MyFunction function) {
        function.run();
    }

    // 반환 타입이 MyFunction인 메서드
    static MyFunction getMyFunction() {
        MyFunction f = () -> System.out.println("[method] run()");
        return f;
    }

    public static void main(String[] args) {
        // 람다식으로 MyFunction의 run()을 구현
        MyFunction function = () -> System.out.println("[main] run()");
        MyFunction function1 = new MyFunction() {
            @Override
            public void run() {
                System.out.println("[Override] run()");
            }
        };

        MyFunction function2 = getMyFunction();

        function.run();
        function1.run();
        function2.run();

        execute(function);
        execute(()-> System.out.println("[execute] run()"));
    }
}
