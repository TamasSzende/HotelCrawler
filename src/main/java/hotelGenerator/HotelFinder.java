package hotelGenerator;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

public class HotelFinder {


    public String getHotelsPage(String url) {
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET().build();
        HttpResponse<String> httpResponse = null;
        try {
            httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        if (httpResponse == null) {
            return null;
        } else {
            return httpResponse.body();
        }
    }

    public List<URI> findHotelURIs(String hotelsPage) {
        List<URI> uriList = new ArrayList<>();
        Scanner scanner = new Scanner(hotelsPage);
        String stringToFindStart = "<a class=\"hotel-name\" href=\"/";
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            int index = line.indexOf(stringToFindStart);
            if (index != -1) {
                int startIndex = index + stringToFindStart.length();
                String stringToFindEnd = " data-urlparams=";
                int endIndex = line.indexOf(stringToFindEnd, startIndex) - 1;
                String url = line.substring(startIndex, endIndex);
                uriList.add(URI.create("http://www.szallas.hu/" + url));
            }
        }
        return uriList;
    }


}
