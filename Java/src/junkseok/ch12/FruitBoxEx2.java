package junkseok.ch12;

import java.util.ArrayList;

class Fruit1 implements Eatable {
    public String toString() { return "Fruit";}
}

class Apple1 extends Fruit1 { public String toString() {return "Apple";}}
class Grape1 extends Fruit1 { public String toString() { return "Grape";}}
class Toy1                  { public String toString() { return "Toy";}}

interface Eatable {}
public class FruitBoxEx2 {
    public static void main(String[] args) {
        FruitBox<Fruit1> fruit1FruitBox = new FruitBox<Fruit1>();
        FruitBox<Apple1> apple1FruitBox = new FruitBox<>();
        FruitBox<Grape1> grape1FruitBox = new FruitBox<>();
        // FruitBox<Toy>    toyFruitBox    = new FruitBox<Toy>();   에러 : Toy가 Fruit1을 상속받지 않았음.

        fruit1FruitBox.add(new Fruit1());
        fruit1FruitBox.add(new Apple1());
        fruit1FruitBox.add(new Grape1());
        apple1FruitBox.add(new Apple1());
        // apple1FruitBox.add(new Grape1());    에러 : Graph는 Apple과 상속관계가 아님.
        grape1FruitBox.add(new Grape1());

        System.out.println("fruit-box-"+fruit1FruitBox);
        System.out.println("apple-box-"+apple1FruitBox);
        System.out.println("grape-box-"+grape1FruitBox);

    }
}


class FruitBox<T extends Fruit1 & Eatable> extends Box1<T> { }

class Box1<T> {
    ArrayList<T> list = new ArrayList<T>();
    void    add(T item)     { list.add(item);}
    T       get(int i)      { return list.get(i);}
    int     size()          { return list.size();}
    public String toString(){ return list.toString();}
}