package junkseok.ch12;

import java.util.ArrayList;

class Fruit2                  { public String toString() { return "Fruit";}}
class Apple2 extends Fruit2   { public String toString() { return "Apple";}}
class Grape2 extends Fruit2   { public String toString() { return "Grape";}}

class Juice {
    private String name;
    Juice(String name)      {this.name = name+"Juice";}
    public String toString(){return name;}
}

class Juicer {
    static Juice makeJuice(FruitBox2<? extends Fruit2> box) {
        String tmp = "";
        for(Fruit2 f : box.getList())
            tmp += f + " ";
        return new Juice(tmp);
    }
}

class FruitBox2<T extends Fruit2> extends Box2<T> {}

class Box2<T> {
    ArrayList<T> list = new ArrayList<>();
    void add(T item)        { list.add(item);}
    T get(int i)            { return list.get(i);}
    ArrayList<T> getList()  { return list;}
    int size()              { return list.size();}
    public String toString(){ return list.toString();}
}

public class FruitBoxEx3 {
    public static void main(String[] args) {
        FruitBox2<Fruit2> fruitBox2 = new FruitBox2<>();
        FruitBox2<Apple2> apple2Box2 = new FruitBox2<>();

        fruitBox2.add(new Apple2());
        fruitBox2.add(new Grape2());
        apple2Box2.add(new Apple2());
        apple2Box2.add(new Apple2());

        System.out.println(Juicer.makeJuice(fruitBox2));
        System.out.println(Juicer.makeJuice(apple2Box2));
    }
}


