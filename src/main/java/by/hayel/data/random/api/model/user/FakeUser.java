package by.hayel.data.random.api.model.user;

import by.hayel.data.random.api.model.location.Location;
import java.io.Serial;
import java.io.Serializable;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FakeUser implements Serializable {
  @Serial private static final long serialVersionUID = 1L;

  Id id;
  Name name;
  Location location;
  String email;
  String phone;
  String page;
}
