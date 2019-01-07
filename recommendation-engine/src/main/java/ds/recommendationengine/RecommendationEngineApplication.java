package ds.recommendationengine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;

@EnableBinding(Source.class)
@SpringBootApplication
public class RecommendationEngineApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecommendationEngineApplication.class, args);
    }
}
