package by.hayel.data.random.api.model.location;

import java.io.Serial;
import java.io.Serializable;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Street implements Serializable {
  @Serial private static final long serialVersionUID = 1L;

  String number;
  String name;
}
