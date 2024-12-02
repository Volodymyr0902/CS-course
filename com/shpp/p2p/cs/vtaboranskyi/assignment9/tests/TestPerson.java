package com.shpp.p2p.cs.vtaboranskyi.assignment9.tests;

/**
 * This is custom class for testing collections behaviour with own objects.
 */
public class TestPerson {

    /**
     * Randomly picked fields.
     */
    private String name;
    private int age;

    /**
     * Constructs test object.
     *
     * @param name Object's "name".
     * @param age Object's "age".
     */
    public TestPerson(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Deeply compares instances of this class.
     *
     * @param object A TestPerson instance.
     * @return <code>True</code>, if compared objects are both
     *         this class instance and their fields are equal.
     */
    @Override
    public boolean equals(Object object) {
        return object instanceof TestPerson &&
                this.name.equals(((TestPerson) object).name) &&
                this.age == ((TestPerson) object).age;
    }

    /**
     * @return String representation of this object.
     */
    @Override
    public String toString() {
        return "TestObject{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
