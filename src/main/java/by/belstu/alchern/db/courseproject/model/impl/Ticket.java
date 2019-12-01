package by.belstu.alchern.db.courseproject.model.impl;

import by.belstu.alchern.db.courseproject.model.Entity;

public class Ticket extends Entity {
    Flight flight;
    User user;
    boolean confirmed;

    public Ticket() {
    }

    public Ticket(int id, Flight flight, User user, boolean confirmed) {
        super(id);
        this.flight = flight;
        this.user = user;
        this.confirmed = confirmed;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Ticket ticket = (Ticket) o;

        if (confirmed != ticket.confirmed) return false;
        if (!flight.equals(ticket.flight)) return false;
        return user.equals(ticket.user);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + flight.hashCode();
        result = 31 * result + user.hashCode();
        result = 31 * result + (confirmed ? 1 : 0);
        return result;
    }


}
