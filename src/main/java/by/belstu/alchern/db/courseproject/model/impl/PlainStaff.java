package by.belstu.alchern.db.courseproject.model.impl;

import by.belstu.alchern.db.courseproject.model.Entity;

public class PlainStaff extends Entity {
    private Plain plain;
    private Worker worker;

    public PlainStaff() {
    }

    public PlainStaff(int id, Plain plain, Worker worker) {
        super(id);
        this.plain = plain;
        this.worker = worker;
    }

    public Plain getPlain() {
        return plain;
    }

    public void setPlain(Plain plain) {
        this.plain = plain;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        PlainStaff that = (PlainStaff) o;

        if (!plain.equals(that.plain)) return false;
        return worker.equals(that.worker);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + plain.hashCode();
        result = 31 * result + worker.hashCode();
        return result;
    }
}
