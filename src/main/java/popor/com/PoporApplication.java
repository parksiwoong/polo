package popor.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PoporApplication {

    public static final String dataPath = "C:\\Users\\parksiwoong\\Desktop\\popor\\20201126\\popor\\_data";

    public static void main(String[] args) {
        SpringApplication.run(PoporApplication.class, args);
    }

}
