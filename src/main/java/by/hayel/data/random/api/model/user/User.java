package by.hayel.data.random.api.model.user;

import java.io.Serial;
import java.io.Serializable;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User implements Serializable {
  @Serial private static final long serialVersionUID = 1L;

  String id;
  String name;
  String address;
  String phone;
}
