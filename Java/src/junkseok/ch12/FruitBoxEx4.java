package junkseok.ch12;

import java.util.*;

class Fruit3 {
    private final String  name;
    private final int     weight;

    Fruit3(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public String toString()    { return name+"("+weight+"}";}

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }
}

class Apple3 extends Fruit3 {
    Apple3(String name, int weight) {
        super(name, weight);
    }
}

class Grape3 extends Fruit3 {
    public Grape3(String name, int weight) {
        super(name, weight);
    }
}

class AppleCmp implements Comparator<Apple3> {
    @Override
    public int compare(Apple3 o1, Apple3 o2) {
        return o2.getWeight() - o1.getWeight();
    }
}

class GrapeCmp implements Comparator<Grape3> {
    @Override
    public int compare(Grape3 o1, Grape3 o2) {
        return o2.getWeight() - o1.getWeight();
    }
}

class FruitCmp implements Comparator<Fruit3> {
    @Override
    public int compare(Fruit3 o1, Fruit3 o2) {
        return o1.getWeight() - o2.getWeight();
    }
}

class FruitBox3<T extends Fruit3> extends Box3<T> {}

class Box3<T> {
    ArrayList<T> list = new ArrayList<>();

    void add(T item)        { list.add(item);}
    T get(int i)            { return list.get(i);}
    ArrayList<T> getList()  { return list;}
    int size()              { return list.size();}

    @Override
    public String toString() {
        return list.toString();
    }
}

public class FruitBoxEx4 {
    public static void main(String[] args) {
        FruitBox3<Apple3> appleBox3 = new FruitBox3<>();
        FruitBox3<Grape3> grapeBox3 = new FruitBox3<>();

        appleBox3.add(new Apple3("GreenApple",300));
        appleBox3.add(new Apple3("GreenApple",100));
        appleBox3.add(new Apple3("GreenApple",200));

        grapeBox3.add(new Grape3("GreenGrape",400));
        grapeBox3.add(new Grape3("GreenGrape",300));
        grapeBox3.add(new Grape3("GreenGrape",200));

        Collections.sort(appleBox3.getList(), new AppleCmp());
        Collections.sort(grapeBox3.getList(), new GrapeCmp());
        System.out.println(appleBox3);
        System.out.println(grapeBox3);
        System.out.println();
        Collections.sort(appleBox3.getList(), new FruitCmp());
        Collections.sort(grapeBox3.getList(), new FruitCmp());
        System.out.println(appleBox3);
        System.out.println(grapeBox3);
    }
}
