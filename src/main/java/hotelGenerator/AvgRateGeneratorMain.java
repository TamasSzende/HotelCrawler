package hotelGenerator;

import java.io.File;

public class AvgRateGeneratorMain {
    public static void main(String[] args) {
        AverageRateGenerator averageRateGenerator = new AverageRateGenerator();
        averageRateGenerator.addAverageRateToJSON(new File("src/main/resources/hotels2.json"));
    }
}
