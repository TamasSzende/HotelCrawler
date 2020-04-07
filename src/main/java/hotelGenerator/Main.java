package hotelGenerator;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        getAboutFiftyHotelsData();
    }

    private static void getAboutFiftyHotelsData() {
        List<HotelDataStolen> hotelDataStolenList = new ArrayList<>();
        HotelFinder hotelFinder = new HotelFinder();
        String hotelsPage = (hotelFinder.getHotelsPage("https://szallas.hu/budapest?checkin=2020-03-12&checkout=2020-03-13&search=Budapest(Budapest%3B6)"));
        List<URI> hotelURIs = hotelFinder.findHotelURIs(hotelsPage);
        for (URI uri : hotelURIs) {
            HotelDataParser hotelDataParser = new HotelDataParser(uri);
            HotelDataStolen hotelDataStolen = hotelDataParser.getHotelData();
            HotelImageSaver.uploadHotelImages(hotelDataStolen);
            System.out.println(hotelDataStolen);
            hotelDataStolenList.add(hotelDataStolen);
        }
        HotelsToJSON.parseHotelData(hotelDataStolenList);
    }

    private static void getOneHotelsData(int hotelNum) {
        List<HotelDataStolen> hotelDataStolenList = new ArrayList<>();
        HotelFinder hotelFinder = new HotelFinder();
        String hotelsPage = (hotelFinder.getHotelsPage("https://szallas.hu/budapest?checkin=2020-03-12&checkout=2020-03-13&search=Budapest(Budapest%3B6)"));
        List<URI> hotelURIs = hotelFinder.findHotelURIs(hotelsPage);
        URI uri = hotelURIs.get(hotelNum);
        HotelDataParser hotelDataParser = new HotelDataParser(uri);
        HotelDataStolen hotelDataStolen = hotelDataParser.getHotelData();
        HotelImageSaver.uploadHotelImages(hotelDataStolen);
        //System.out.println(hotelData);
        //hotelGenerator.HotelImagDownloader.saveHotelImagesToFile(hotelData);
        hotelDataStolenList.add(hotelDataStolen);

        HotelsToJSON.parseHotelData(hotelDataStolenList);
    }
}
