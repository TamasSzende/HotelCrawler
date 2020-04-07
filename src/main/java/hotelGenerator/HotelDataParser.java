package hotelGenerator;

import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HotelDataParser {
    private Document document;

    public HotelDataParser(URI hotelPageURI) {
        document = null;
        try {
            document = Jsoup.connect(hotelPageURI.toString()).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HotelDataStolen getHotelData() {
        HotelDataStolen hotelDataStolen = null;
        if (document != null) {
            String hotelName = document.select("span.name").get(0).ownText();
            //String country = document.select("a.hoteladdress-info").get(0).ownText();
            String postalCode = document.select("a.hoteladdress-info").get(0).child(1).ownText();
            String city = document.select("a.hoteladdress-info").get(0).child(2).ownText();
            String streetAddress = document.select("a.hoteladdress-info").get(0).child(3).ownText();
            String latitude = document.select("a.show-hotel-map").attr("data-latitude");
            String longitude = document.select("a.show-hotel-map").attr("data-longitude");
            StringBuilder descriptionBuilder = new StringBuilder();
            document.select("div.hotel-text-description").get(0).children().forEach((element) -> {
                descriptionBuilder.append(element.ownText());
                descriptionBuilder.append("\n");
            });
            String description = descriptionBuilder.toString();
            List<String> hotelFeatures = new ArrayList<>();
            document.select("div.hotel-page-section-content-xs ul.list-iconic li").select("span").forEach((element) -> {
                if (element.children().size() == 0 && !element.className().equals("no-wrap")) {
                    String feature = element.ownText();
                    if (!feature.isBlank() && !hotelFeatures.contains(feature)) {
                        hotelFeatures.add(feature);
                    }
                }
            });
            List<String> imageUrls = new ArrayList<>();
            Arrays.stream(document.select(".hotel-photos-container script")
                    .get(0)
                    .html()
                    .split("\"original\":\""))
                    .forEach((str) -> {
                        int endIndex = str.indexOf("\"");
                        str = str.substring(0, endIndex);
                        if (str.contains("hotel") && !str.startsWith("init") && imageUrls.size() < 10) {
                            str = str.replaceAll("\\\\/", "/");
                            imageUrls.add(str);
                        }
                    });
            hotelDataStolen = new HotelDataStolen(hotelName, description, postalCode, city, streetAddress, longitude, latitude, hotelFeatures, imageUrls);
            getRoomData(hotelDataStolen);
        }
        return hotelDataStolen;
    }

    private void getRoomData(HotelDataStolen hotelDataStolen) {
        List<RoomDataStolen> roomDataStolens = new ArrayList<>();
        Elements roomsDocument = document.select(".table-room-list tr ");
        int numOfRooms = roomsDocument.select(".room-name").size();
        List<String> imageURLs = new ArrayList<>();
        roomsDocument.select(".img-responsive").forEach((url) -> imageURLs.add(url.attr("src").replace("150x150", "original")));
        List<String> roomNames = new ArrayList<>();
        roomsDocument.select("span.room-name").forEach((roomName) -> roomNames.add(roomName.ownText()));
        List<Integer> numOfBeds = new ArrayList<>();
        roomsDocument.select("div.traveller-details").forEach((numOfBedString) -> {
            char[] chars = numOfBedString.ownText().toCharArray();
            StringBuilder digits = new StringBuilder();
            for (char c : chars
            ) {
                if (Character.isDigit(c)) {
                    digits.append(c);
                    break;
                }
            }
            numOfBeds.add(Integer.parseInt(digits.toString()));
        });

        List<Integer> roomAreas = new ArrayList<>();
        List<List<String>> roomFeatures = new ArrayList<>();
        roomsDocument.select(".list-unstyled").forEach((list) -> {
            StringBuilder stringArea = new StringBuilder();
            char[] chars = list.child(0).ownText().toCharArray();
            for (char c : chars) {
                if (Character.isDigit(c)) {
                    stringArea.append(c);
                }
            }
            List<String> features = new ArrayList<>();
            if (!stringArea.toString().isBlank()) {
                roomAreas.add(Integer.parseInt(stringArea.toString()));
            } else {
                roomAreas.add(-1);
            }
            list.children().forEach((li) -> {
                if (li.children().size() < 2) {
                    features.add(li.ownText());
                }
            });
            roomFeatures.add(features);
        });
        for (int i = 0; i < numOfRooms; i++) {
            RoomDataStolen roomDataStolen = new RoomDataStolen(roomNames.get(i), numOfBeds.get(i), roomAreas.get(i), imageURLs.get(i), roomFeatures.get(i));
            roomDataStolens.add(roomDataStolen);
        }
        hotelDataStolen.setRooms(roomDataStolens);
    }
}
