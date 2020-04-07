package hotelGenerator;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.List;
import java.util.Random;

public class AverageRateGenerator {
    private Random random = new Random();

    public void addAverageRateToJSON(File file) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<HotelDataStolen> hotelDataStolenList = objectMapper.readValue(file, new TypeReference<>() {
            });
            for (HotelDataStolen hotelDataStolen : hotelDataStolenList) {
                hotelDataStolen.setAvgRate(generateAvgRate());
                System.out.println(hotelDataStolen.getRooms());
            }
            OutputStream outputStream = new FileOutputStream(new File("Res/hotelsAvg.json"));
            JsonGenerator jsonGenerator = objectMapper.getFactory().createGenerator(outputStream);
            objectMapper.writeValue(jsonGenerator, hotelDataStolenList);
            jsonGenerator.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Double generateAvgRate() {
        Double avgRate = 6.5;
        avgRate += (double) random.nextInt(35) / 10;
        return avgRate;
    }
}
