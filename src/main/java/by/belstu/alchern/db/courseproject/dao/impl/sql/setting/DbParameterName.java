package by.belstu.alchern.db.courseproject.dao.impl.sql.setting;

public class DbParameterName {
    public static final String REQ_ID = "id";

    public static final String REQ_USER_ROLE_ID = "role_id";
    public static final String REQ_USER_PASSWORD = "password";
    public static final String REQ_USER_LOGIN = "login";
    public static final String REQ_USER_FIRST_NAME = "first_name";
    public static final String REQ_USER_LAST_NAME = "last_name";
    public static final String REQ_USER_DOCUMENT_NUMBER = "document_number";
    public static final String REQ_USER_TEL = "tel";
    public static final String REQ_USER_ADDRESS = "address";

    // ДОБАВИТЬ ДАТУ ПРИЛЕТА-ОТЛЕТА
    public static final String REQ_FLIGHT_FROM_AIRPORT_ID = "from_airport_id";
    public static final String REQ_FLIGHT_TO_AIRPORT_ID = "to_airport_id";
    public static final String REQ_FLIGHT_PLAIN_ID = "plane_id";
    public static final String REQ_FLIGHT_DEPARTURE_DATE = "departure";
    public static final String REQ_FLIGHT_ARRIVAL_DATE = "arrival";
    public static final String REQ_FLIGHT_PASSENGERS_ALL = "passengers_all";
    public static final String REQ_FLIGHT_PASSENGERS = "passengers";
    public static final String REQ_FLIGHT_PRICE = "price";

    public static final String REQ_AIRPORT_NAME = "name";
    public static final String REQ_AIRPORT_COUNTRY_ID = "country_id";

    public static final String REQ_COUNTRY_NAME = "name";

    public static final String REQ_ROLE_ROLE = "role";

    public static final int ROLE_USER = 1;
    public static final int ROLE_ADMIN = 2;

    // ДОБАВИТЬ СТОИМОСТЬ БИЛЕТА!!!
    public static final String REQ_TICKET_FLIGHT_ID = "flight_id";
    public static final String REQ_TICKET_USER_ID = "user_id";
    public static final String REQ_TICKET_CONFIRMED = "confirmed";

    public static final String REQ_POSITION_POSITION = "position";

    public static final String REQ_PLAIN_NUMBER = "number";
    public static final String REQ_PLAIN_NAME = "name";
    public static final String REQ_PLAIN_CAPACITY = "capacity";

    public static final String REQ_PLAIN_STAFF_PLAIN_ID = "plane_id";
    public static final String REQ_PLAIN_STAFF_WORKER_ID = "worker_id";

    public static final String REQ_WORKER_FIRST_NAME = "first_name";
    public static final String REQ_WORKER_LAST_NAME = "last_name";
    public static final String REQ_WORKER_POSITION_ID = "position_id";

    // -----------


    public static final String REQ_UTIL_AUTO_INCREMENT = "auto_increment";

    public static final String TABLE_USERS = "users";
    public static final String TABLE_USERDATA = "userdata";
    public static final String TABLE_TICKETS = "tickets";
    public static final String TABLE_FLIGHTS = "flights";

}
