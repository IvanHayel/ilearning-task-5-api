package by.hayel.data.random.api.service.impl;

import by.hayel.data.random.api.config.properties.ExternalApiProperties;
import by.hayel.data.random.api.payload.request.FakeUsersRequest;
import by.hayel.data.random.api.service.ExternalApiService;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ExternalApiServiceImpl implements ExternalApiService {
  private static final String URL_SEPARATOR = "?";
  private static final String PARAMETER_EQUAL = "=";
  private static final String PARAMETER_SEPARATOR = "&";
  private static final String SEED_PARAMETER = "seed";
  private static final String PAGE_PARAMETER = "page";
  private static final String RESULTS_PARAMETER = "results";
  private static final String NATIONALITY_PARAMETER = "nat";

  ExternalApiProperties externalApi;

  @Override
  public URI buildUri() {
    return URI.create(externalApi.getUrl());
  }

  @Override
  public URI buildUri(Map<String, String> requestParameters) {
    StringJoiner params = new StringJoiner(PARAMETER_SEPARATOR);
    if (requestParameters.isEmpty()) return buildUri();
    requestParameters.forEach((key, value) -> params.add(key + PARAMETER_EQUAL + value));
    return URI.create(externalApi.getUrl() + URL_SEPARATOR + params);
  }

  @Override
  public URI buildUri(FakeUsersRequest request) {
    if (request == null) return buildUri();
    Map<String, String> params = new HashMap<>();
    if (Objects.nonNull(request.getSeed())) params.put(SEED_PARAMETER, request.getSeed());
    if (Objects.nonNull(request.getPage())) params.put(PAGE_PARAMETER, request.getPage());
    if (Objects.nonNull(request.getCount())) params.put(RESULTS_PARAMETER, request.getCount());
    if (Objects.nonNull(request.getNationality()))
      params.put(NATIONALITY_PARAMETER, request.getNationality());
    return buildUri(params);
  }
}
