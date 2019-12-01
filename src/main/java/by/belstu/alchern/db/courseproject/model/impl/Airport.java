package by.belstu.alchern.db.courseproject.model.impl;

import by.belstu.alchern.db.courseproject.model.Entity;

public class Airport extends Entity {
    private String name;
    private Country country;

    public Airport() {
    }

    public Airport(int id, String name, Country country) {
        super(id);
        this.name = name;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Airport airport = (Airport) o;

        if (!name.equals(airport.name)) return false;
        return country.equals(airport.country);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + country.hashCode();
        return result;
    }
}
