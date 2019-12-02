package by.belstu.alchern.db.courseproject.model.impl;

import by.belstu.alchern.db.courseproject.model.Entity;

public class PlaneStaff extends Entity {
    private Plane plane;
    private Worker worker;

    public PlaneStaff() {
    }

    public PlaneStaff(int id, Plane plane, Worker worker) {
        super(id);
        this.plane = plane;
        this.worker = worker;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
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

        PlaneStaff that = (PlaneStaff) o;

        if (!plane.equals(that.plane)) return false;
        return worker.equals(that.worker);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + plane.hashCode();
        result = 31 * result + worker.hashCode();
        return result;
    }
}
