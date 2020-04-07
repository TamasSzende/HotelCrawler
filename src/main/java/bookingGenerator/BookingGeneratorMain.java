package bookingGenerator;

import java.time.LocalDate;
import java.util.stream.Collectors;

public class BookingGeneratorMain {
    // Mielőtt futtatjuk:
    //        1 kikommentelni az email küldést
    //        2 kikommentelni azt a booking validációt, hogy ne lehessen korábbra foglalni
    public static void main(String[] args) {
        BookingCreateItemGenerator bookingCreateItemGenerator = new BookingCreateItemGenerator();
        for (int i = 0; i < 600; i++) {
            bookingCreateItemGenerator.generateBooking(1, LocalDate.of(2019, 2, 1), LocalDate.of(2020, 7, 30));
        }
        BookingEndpointAbuser bookingEndpointAbuser = new BookingEndpointAbuser();
        bookingEndpointAbuser.abuseEndpoint(bookingCreateItemGenerator.getBookings().stream().map(BookingCreateItemWithStringDate::new).collect(Collectors.toList()));
        bookingCreateItemGenerator.saveBookingsToJSON();
    }
}
