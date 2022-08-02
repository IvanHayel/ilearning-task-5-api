package by.hayel.data.random.api.config;

import java.util.Random;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FakeDataConfiguration {
  @Bean
  public Random getRandom() {
    return new Random();
  }
}
