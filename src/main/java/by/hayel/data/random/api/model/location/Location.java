package by.hayel.data.random.api.model.location;

import java.io.Serial;
import java.io.Serializable;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Location implements Serializable {
  @Serial private static final long serialVersionUID = 1L;

  String country;
  String state;
  String city;
  Street street;
  String postcode;
}
