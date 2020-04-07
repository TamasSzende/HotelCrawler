package hotelGenerator;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.List;

public class HotelsToJSON {
    public static void parseHotelData(List<HotelDataStolen> hotelDataStolenList) {
        File file = new File("Res/hotels.json");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            OutputStream outputStream = new FileOutputStream(file);
            JsonGenerator jsonGenerator = objectMapper.getFactory().createGenerator(outputStream);
            objectMapper.writeValue(jsonGenerator, hotelDataStolenList);
            jsonGenerator.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<HotelDataStolen> reparseHotelData(String fileName) {
        File file = new File(fileName);
        ObjectMapper objectMapper = new ObjectMapper();
        List<HotelDataStolen> hotels = null;
        try {
            hotels = objectMapper.readValue(file, new TypeReference<List<HotelDataStolen>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hotels;
    }
}
