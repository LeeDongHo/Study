package me.domo.java8;

@FunctionalInterface
public interface RunSomething {
    void doIt();

    static void printName() {
        System.out.println("DOHO");
    }

    default void printAge() {
        System.out.println("30");
    }
}
