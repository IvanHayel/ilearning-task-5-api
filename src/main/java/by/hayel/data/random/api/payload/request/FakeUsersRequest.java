package by.hayel.data.random.api.payload.request;

import by.hayel.data.random.api.payload.ClientRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FakeUsersRequest implements ClientRequest {
  String seed;
  String page;
  String nationality;
  String count;
  String errorRate;
}
