package by.hayel.data.random.api.config.properties;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "external-api")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExternalApiProperties {
  String url;
}
