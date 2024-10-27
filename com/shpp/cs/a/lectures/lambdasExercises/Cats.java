package com.shpp.cs.a.lectures.lambdasExercises;

public class Cats {
    public static void main(String[] args) {
        // создаем кота и выводим на экран чтоб убедиться, что он "пустой"
        Cat myCat = new Cat();
        System.out.println(myCat);

        // создаем лямбду
        Settable<Cat> s = (obj, name, age) -> {
            obj.setName(name);
            obj.setAge(age);
        };

        // вызываем метод, в который передаем кота и лямбду
        changeEntity(myCat, s);
        // выводим на экран и видим, что состояние кота изменилось (имеет имя и возраст)
        System.out.println(myCat);
    }

    private static <T extends WithNameAndAge>  void changeEntity(T entity, Settable<T> s) {
        s.set(entity, "Мурзик", 3);
    }
}

interface WithNameAndAge {
    void setName(String name);
    void setAge(int age);
}

interface Settable<C extends WithNameAndAge> {
    void set(C entity, String name, int age);
}

class Cat implements WithNameAndAge {
    private String name;
    private int age;

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}