package by.hayel.data.random.api.config.properties;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "cors")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CorsProperties {
  String mapping;
  String allowedOrigins;
  String allowedMethods;
  String allowedHeaders;
  boolean allowCredentials;
}
