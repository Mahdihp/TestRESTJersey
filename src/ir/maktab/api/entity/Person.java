package ir.maktab.api.entity;

public abstract class Person {

    private int id;

    public Person() {
    }

    public Person(int id) {
        this.id = id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
