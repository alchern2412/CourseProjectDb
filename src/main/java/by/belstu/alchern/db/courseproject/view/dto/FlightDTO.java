package by.belstu.alchern.db.courseproject.view.dto;

public class FlightDTO {
    String fromAirport;
    String toAirport;
    String plain;
    String departure;
    String arrival;
    String price;

    public FlightDTO(String fromAirport, String toAirport, String plain, String departure, String arrival, String price) {
        this.fromAirport = fromAirport;
        this.toAirport = toAirport;
        this.plain = plain;
        this.departure = departure;
        this.arrival = arrival;
        this.price = price;
    }

    public FlightDTO() {
    }

    public String getFromAirport() {
        return fromAirport;
    }

    public void setFromAirport(String fromAirport) {
        this.fromAirport = fromAirport;
    }

    public String getToAirport() {
        return toAirport;
    }

    public void setToAirport(String toAirport) {
        this.toAirport = toAirport;
    }

    public String getPlain() {
        return plain;
    }

    public void setPlain(String plain) {
        this.plain = plain;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
