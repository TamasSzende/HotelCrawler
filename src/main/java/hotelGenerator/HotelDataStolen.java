package hotelGenerator;

import hotelGenerator.typeEnums.HotelFeatureType;
import hotelGenerator.typeEnums.HotelType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HotelDataStolen {
    private String name;
    private String postalCode;
    private String city;
    private String streetAddress;
    private String longitude;
    private String latitude;
    private HotelType hotelType;
    private List<RoomDataStolen> rooms = new ArrayList<>();
    private List<String> hotelImageUrls;
    private String description;
    private List<HotelFeatureType> hotelFeatures;
    private Double avgRate;


    public HotelDataStolen(String name, String description, String postalCode, String city, String streetAddress, String longitude, String latitude, List<String> hotelFeatures, List<String> hotelImageUrls) {
        this.name = name;
        this.description = description;
        this.longitude = longitude;
        this.latitude = latitude;
        this.hotelFeatures = generateHotelFeatures(hotelFeatures);
        this.hotelImageUrls = hotelImageUrls;
        this.postalCode = postalCode;
        this.city = city;
        this.streetAddress = streetAddress;
        hotelType = generateHotelType();
    }

    public HotelDataStolen() {
    }

    private List<HotelFeatureType> generateHotelFeatures(List<String> hotelFeatures) {
        List<HotelFeatureType> hotelFeatureTypes = new ArrayList<>();
        for (String featureString : hotelFeatures
        ) {
            HotelFeatureType hotelFeatureType = HotelFeatureType.findByDisplayName(featureString);
            if (hotelFeatureType == null) {
                System.out.println("\u001B[31m" + featureString + "\u001B[0m");
            } else {
                hotelFeatureTypes.add(hotelFeatureType);
            }
        }
        return hotelFeatureTypes;
    }

    private HotelType generateHotelType() {
        Random random = new Random();
        return HotelType.values()[random.nextInt(HotelType.values().length)];
    }

    @Override
    public String toString() {
        return "HotelData{ \n" +
                "hotelName='" + name + '\n' +
                "description= \n'" + description + '\n' +
                "hotelFeatures=" + hotelFeatures + "\n" +
                "imageURLs=" + hotelImageUrls + "\n" +
                "rooms=" + rooms +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<HotelFeatureType> getHotelFeatures() {
        return hotelFeatures;
    }

    public void setHotelFeatures(List<HotelFeatureType> hotelFeatures) {
        this.hotelFeatures = hotelFeatures;
    }

    public HotelType getHotelType() {
        return hotelType;
    }

    public void setHotelType(HotelType hotelType) {
        this.hotelType = hotelType;
    }

    public List<String> getHotelImageUrls() {
        return hotelImageUrls;
    }

    public void setHotelImageUrls(List<String> hotelImageUrls) {
        this.hotelImageUrls = hotelImageUrls;
    }

    public List<RoomDataStolen> getRooms() {
        return rooms;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setRooms(List<RoomDataStolen> roomDataStolens) {
        this.rooms.addAll(roomDataStolens);
    }

    public Double getAvgRate() {
        return avgRate;
    }

    public void setAvgRate(Double avgRate) {
        this.avgRate = avgRate;
    }
}
