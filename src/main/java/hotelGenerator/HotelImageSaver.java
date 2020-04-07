package hotelGenerator;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;


import java.io.IOException;
import java.util.Map;

public class HotelImageSaver {
    private static Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "doaywchwk",
            "api_key", "115312357113411",
            "api_secret", "rvKG8MW45AZWCh66Xs4oaxt4Bpk"
    ));

    public static void uploadHotelImages(HotelDataStolen hotelDataStolen) {
        for (int i = 0; i < hotelDataStolen.getHotelImageUrls().size(); i++) {
            String imageURL = hotelDataStolen.getHotelImageUrls().get(i);
            String publicID = "HotelImages/" + hotelDataStolen.getName() + "/" + i;
            String newURL = uploadImage(imageURL, publicID);
            if (newURL != null) {
                hotelDataStolen.getHotelImageUrls().set(i, newURL);
            } else {
                System.out.println("Couldn't upload image: " + imageURL);
            }
        }
        for (RoomDataStolen room : hotelDataStolen.getRooms()) {
            String imageURL = room.getRoomImageUrl();
            String publicID = "HotelImages/" + hotelDataStolen.getName() + "/rooms/" + room.getRoomName();
            String newURL = uploadImage(imageURL, publicID);
            if (newURL != null) {
                room.setRoomImageUrl(newURL);
            } else {
                System.out.println("Couldn't upload image: " + imageURL);
            }
        }
    }

    public static String uploadImage(String imageURL, String publicID) {
        String url = null;
        try {
            Map uploadResult = cloudinary.uploader().upload(imageURL, ObjectUtils.emptyMap());
            url = uploadResult.get("url").toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return url;
    }
}
