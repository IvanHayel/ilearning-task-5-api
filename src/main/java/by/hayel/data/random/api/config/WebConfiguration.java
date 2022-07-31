package by.hayel.data.random.api.config;

import by.hayel.data.random.api.config.properties.CorsProperties;
import by.hayel.data.random.api.config.properties.ExternalApiProperties;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableConfigurationProperties({CorsProperties.class, ExternalApiProperties.class})
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class WebConfiguration implements WebMvcConfigurer {
  CorsProperties corsProperties;

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry
        .addMapping(corsProperties.getMapping())
        .allowedOrigins(corsProperties.getAllowedOrigins())
        .allowedMethods(corsProperties.getAllowedMethods())
        .allowedHeaders(corsProperties.getAllowedHeaders())
        .allowCredentials(corsProperties.isAllowCredentials());
  }

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
}