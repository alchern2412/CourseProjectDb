package by.belstu.alchern.db.courseproject.model.impl;

import by.belstu.alchern.db.courseproject.model.Entity;

import java.util.Date;

public class Flight extends Entity {
    Airport from_airport;
    Airport to_airport;
    Plain plain;
    Date departure;
    Date arrival;
    double price;

    public Flight() {
    }

    public Flight(int id, Airport from_airport, Airport to_airport, Plain plain, Date departure, Date arrival, double price) {
        super(id);
        this.from_airport = from_airport;
        this.to_airport = to_airport;
        this.plain = plain;
        this.departure = departure;
        this.arrival = arrival;
        this.price = price;
    }

    public Airport getFrom_airport() {
        return from_airport;
    }

    public void setFrom_airport(Airport from_airport) {
        this.from_airport = from_airport;
    }

    public Airport getTo_airport() {
        return to_airport;
    }

    public void setTo_airport(Airport to_airport) {
        this.to_airport = to_airport;
    }

    public Plain getPlain() {
        return plain;
    }

    public void setPlain(Plain plain) {
        this.plain = plain;
    }

    public Date getDeparture() {
        return departure;
    }

    public void setDeparture(Date departure) {
        this.departure = departure;
    }

    public Date getArrival() {
        return arrival;
    }

    public void setArrival(Date arrival) {
        this.arrival = arrival;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Flight flight = (Flight) o;

        if (Double.compare(flight.price, price) != 0) return false;
        if (from_airport != null ? !from_airport.equals(flight.from_airport) : flight.from_airport != null)
            return false;
        if (to_airport != null ? !to_airport.equals(flight.to_airport) : flight.to_airport != null) return false;
        if (plain != null ? !plain.equals(flight.plain) : flight.plain != null) return false;
        if (departure != null ? !departure.equals(flight.departure) : flight.departure != null) return false;
        return arrival != null ? arrival.equals(flight.arrival) : flight.arrival == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        result = 31 * result + (from_airport != null ? from_airport.hashCode() : 0);
        result = 31 * result + (to_airport != null ? to_airport.hashCode() : 0);
        result = 31 * result + (plain != null ? plain.hashCode() : 0);
        result = 31 * result + (departure != null ? departure.hashCode() : 0);
        result = 31 * result + (arrival != null ? arrival.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
