package hotelGenerator;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HotelImagDownloader {

    public static void saveHotelImagesToFile(HotelDataStolen hotelDataStolen) {

        try {
            int counter = 0;
            Path hotelDirectory = Paths.get("Res/HotelImages/" + hotelDataStolen.getName());
            Files.createDirectories(hotelDirectory);
            for (String imageURL : hotelDataStolen.getHotelImageUrls()) {
                counter++;
                saveImage(imageURL, hotelDirectory, "" + counter);
                Thread.sleep(100);
            }
            Path roomsDirectory = hotelDirectory.resolve("Rooms");
            Files.createDirectories(roomsDirectory);
            for (RoomDataStolen room : hotelDataStolen.getRooms()) {
                saveImage(room.getRoomImageUrl(), roomsDirectory, room.getRoomName());
                Thread.sleep(100);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void saveImage(String imageURL, Path path, String filename) {
        try (InputStream in = new URL(imageURL).openStream()) {
            Path filePath = path.resolve(filename + ".jpg");
            Files.copy(in, filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
