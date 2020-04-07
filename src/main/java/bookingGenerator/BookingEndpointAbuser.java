package bookingGenerator;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.HttpPost;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class BookingEndpointAbuser {
    public void abuseEndpoint(List<BookingCreateItemWithStringDate> bookings) {
        for (BookingCreateItemWithStringDate booking : bookings) {
            try {
                sendPostRequest(booking);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendPostRequest(BookingCreateItemWithStringDate booking) throws IOException {
        URL url = new URL("http://localhost:8080/api/booking");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);

        ObjectMapper objectMapper = new ObjectMapper();
        String bookingJSON = objectMapper.writeValueAsString(booking);

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = bookingJSON.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
        }
    }
}
