package by.belstu.alchern.db.courseproject.model.impl;

import by.belstu.alchern.db.courseproject.model.Entity;

public class Plain extends Entity {
    private String number;
    private String name;
    private int capacity;

    public Plain() {
    }

    public Plain(int id, String number, String name, int capacity) {
        super(id);
        this.number = number;
        this.name = name;
        this.capacity = capacity;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Plain plain = (Plain) o;

        if (capacity != plain.capacity) return false;
        if (!number.equals(plain.number)) return false;
        return name.equals(plain.name);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + number.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + capacity;
        return result;
    }
}
