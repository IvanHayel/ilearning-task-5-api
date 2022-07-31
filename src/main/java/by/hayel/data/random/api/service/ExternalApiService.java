package by.hayel.data.random.api.service;

import by.hayel.data.random.api.payload.request.FakeUsersRequest;
import java.net.URI;
import java.util.Map;

public interface ExternalApiService {
  URI buildUri();

  URI buildUri(Map<String, String> parameters);

  URI buildUri(FakeUsersRequest request);
}
