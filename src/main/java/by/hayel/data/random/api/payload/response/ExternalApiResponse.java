package by.hayel.data.random.api.payload.response;

import by.hayel.data.random.api.model.user.FakeUser;
import by.hayel.data.random.api.payload.ExternalResponse;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExternalApiResponse implements ExternalResponse {
  List<FakeUser> results;
}
