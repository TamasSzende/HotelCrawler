package hotelGenerator;

import hotelGenerator.typeEnums.RoomFeatureType;
import hotelGenerator.typeEnums.RoomType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RoomDataStolen {

    private String roomName;
    private RoomType roomType;
    private int numberOfBeds; // férőhely
    private int roomArea;
    private Double pricePerNight;
    private String roomImageUrl;
    private String description;
    private List<RoomFeatureType> roomFeatures;

    public RoomDataStolen(String roomName, int numberOfBeds, int roomArea, String roomImageURL, List<String> roomFeatures) {
        this.roomName = roomName;
        this.numberOfBeds = numberOfBeds;
        this.roomArea = roomArea;
        this.roomImageUrl = roomImageURL;
        this.roomFeatures = generateRoomFeatures(roomFeatures);
        generatePricePerNight();
        generateRoomType();
        generateDescription();
    }

    public RoomDataStolen() {
    }

    private void generateDescription() {
        switch (this.roomType) {
            case EGYAGYASSZOBA:
                this.description = "Az egyágyas szoba az egyedül utazó vendégeink részére biztosít elhelyezést.";
                break;
            case KETAGYASSZOBA:
                this.description = "A szoba keleti fekvésű és erkéllyel rendelkezik. Kényelmes elhelyezést biztosít két fő számára szép kilátással.";
                break;
            case HAROMAGYASSZOBA:
                this.description = "A szobában három teljes értékű ágy található. A szobához felár ellenében klíma is igényelhető!";
                break;
            case NEGYAGYASSZOBA:
                this.description = "A tágas szoba kényelmes elhelyezést biztosít az egész család számára.";
                break;
            case TOBBAGYASSZOBA:
                this.description = "Ebben a szobában elférnek sokan";
                break;
            case STUDIO:
                this.description = "A tetőtéri kialakítású szoba kellemes pihenést biztosít szép kilátással.";
                break;
            case APARTMAN:
                this.description = "Egy hálószoba és egy nappali szolgálja vendégeink kényelmes pihenését. A szoba erkéllyel rendelkezik.";
                break;
            case LAKOSZTALY:
                this.description = "A lakosztály eleganciát, tágas tereket és páratlan kényelmet kínál csodálatos kilátással.";
                break;
        }
    }

    private void generatePricePerNight() {
        Random random = new Random();
        double price = 3000d;
        price += (numberOfBeds + random.nextDouble()) * (2000 + random.nextInt(7000));
        price += roomArea * random.nextInt(500);
        pricePerNight = (Math.floor(price / 100)) * 100;
    }

    private List<RoomFeatureType> generateRoomFeatures(List<String> roomFeatures) {
        List<RoomFeatureType> RoomFeatureTypes = new ArrayList<>();
        for (String featureString : roomFeatures
        ) {
            RoomFeatureType roomFeatureType = RoomFeatureType.findByDisplayName(featureString);
            if (roomFeatureType == null) {
                System.out.println("\u001B[32m" + featureString + "\u001B[0m");
            } else {
                RoomFeatureTypes.add(roomFeatureType);
            }
        }
        return RoomFeatureTypes;
    }

    private void generateRoomType() {
        switch (numberOfBeds) {
            case 1:
                this.roomType = RoomType.EGYAGYASSZOBA;
                break;
            case 2:
                this.roomType = RoomType.KETAGYASSZOBA;
                break;
            case 3:
                this.roomType = RoomType.HAROMAGYASSZOBA;
                break;
            case 4:
                this.roomType = RoomType.NEGYAGYASSZOBA;
                break;
            default:
                this.roomType = RoomType.TOBBAGYASSZOBA;
        }
        if (this.pricePerNight > 30000) {
            this.roomType = RoomType.STUDIO;
        }
        if (this.pricePerNight > 40000) {
            this.roomType = RoomType.APARTMAN;
        }
        if (this.pricePerNight > 50000) {
            this.roomType = RoomType.LAKOSZTALY;
        }

        if (this.roomName.contains("apartman")) {
            this.roomType = RoomType.APARTMAN;
        }
        ;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public int getRoomArea() {
        return roomArea;
    }

    public void setRoomArea(int roomArea) {
        this.roomArea = roomArea;
    }

    public String getRoomImageUrl() {
        return roomImageUrl;
    }

    public void setRoomImageUrl(String roomImageUrl) {
        this.roomImageUrl = roomImageUrl;
    }

    public List<RoomFeatureType> getRoomFeatures() {
        return roomFeatures;
    }

    public void setRoomFeatures(List<RoomFeatureType> roomFeatures) {
        this.roomFeatures = roomFeatures;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public Double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(Double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "RoomData{" +
                "roomName='" + roomName + '\'' +
                ", numberOfBeds=" + numberOfBeds +
                ", roomArea=" + roomArea +
                ", roomImageURL='" + roomImageUrl + '\'' +
                ", features=" + roomFeatures +
                '}';
    }
}
