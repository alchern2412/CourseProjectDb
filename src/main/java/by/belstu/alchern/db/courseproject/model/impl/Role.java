package by.belstu.alchern.db.courseproject.model.impl;

import by.belstu.alchern.db.courseproject.model.Entity;

public class Role extends Entity {
    private String role;

    public Role() {
    }

    public Role(String role) {
        this.role = role;
    }

    public Role(int id, String role) {
        super(id);
        this.role = role;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
