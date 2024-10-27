package com.shpp.cs.a.lectures.OOP;

import java.util.Objects;

public class Man {
    private String eyesColor;
    private String hairColor;
    private String name;
    private int age;
    private int dna;

    public Man(String eyesColor, String hairColor, String name, int age, int dna) {
        this.eyesColor = eyesColor;
        this.hairColor = hairColor;
        this.name = name;
        this.age = age;
        this.dna = dna;
    }

    public int getDna() {
        return dna;
    }

    public void setDna(int dna) {
        this.dna = dna;
    }

    //    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Man man = (Man) o;
//        return
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Man man = (Man) o;
        return age == man.age && dna == man.dna && Objects.equals(eyesColor, man.eyesColor) && Objects.equals(hairColor, man.hairColor) && Objects.equals(name, man.name);
    }

    @Override
    public int hashCode() {
        return dna;
    }
}
