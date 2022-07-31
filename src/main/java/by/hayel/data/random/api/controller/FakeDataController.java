package by.hayel.data.random.api.controller;

import by.hayel.data.random.api.model.user.FakeUser;
import by.hayel.data.random.api.payload.request.FakeUsersRequest;
import by.hayel.data.random.api.payload.response.ExternalApiResponse;
import by.hayel.data.random.api.service.ErrorSimulationService;
import by.hayel.data.random.api.service.ExternalApiService;
import java.net.URI;
import java.util.List;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/fake-data")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FakeDataController {
  RestTemplate restTemplate;
  ExternalApiService externalApiService;
  ErrorSimulationService errorSimulationService;

  @PostMapping
  public ResponseEntity<List<FakeUser>> getRandomFakeUsers(@RequestBody FakeUsersRequest request) {
    URI uri = externalApiService.buildUri(request);
    try {
      var response = restTemplate.getForEntity(uri, ExternalApiResponse.class);
      var data = Objects.requireNonNull(response.getBody()).getResults();
      String errorRate = request.getErrorRate();
      if (Objects.nonNull(errorRate)) {
        data = errorSimulationService.simulateErrors(data, Double.parseDouble(errorRate));
      }
      return response.getStatusCode().is2xxSuccessful()
          ? ResponseEntity.ok(data)
          : ResponseEntity.badRequest().build();
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.badRequest().build();
    }
  }
}
