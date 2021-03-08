package junkseok.ch12;

import java.util.ArrayList;

class Fruit                 {public String toString() { return "Fruit";}}
class Apple extends Fruit   {public String toString() { return "Apple";}}
class Grape extends Fruit   {public String toString() { return "Grape";}}
class Toy                   {public String toString() { return "Toy";}}

public class FruitBoxEx1 {
    public static void main(String[] args) {
        Box<Fruit>  fruitBox = new Box<>();
        Box<Apple>  appleBox = new Box<>();
        Box<Toy>    toyBox = new Box<Toy>();
        //Box<Grape>  grapeBox = new Box<Fruit>();  에러 : 타입 불일치

        fruitBox.add(new Fruit());
        fruitBox.add(new Apple());  // OK : void add (Fruit item)

        appleBox.add(new Apple());
        appleBox.add(new Apple());
        //appleBox.add(new Toy());  에러 : Apple 타입에 Toy 타입 담을 수 없음

        toyBox.add(new Toy());

        System.out.println(fruitBox);
        System.out.println(appleBox);
        System.out.println(toyBox);
    }
}

class Box<T> {
    ArrayList<T> list = new ArrayList<>();  // JDK 1.7부터 가능
    void add(T item)    {list.add(item);}
    T get(int i)        {return list.get(i);}
    int size()          {return list.size();}
    public String toString()    {return list.toString();}
}