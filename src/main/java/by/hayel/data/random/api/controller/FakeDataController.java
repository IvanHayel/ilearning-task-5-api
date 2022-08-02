package by.hayel.data.random.api.controller;

import by.hayel.data.random.api.exception.NegativeDataCountException;
import by.hayel.data.random.api.model.user.User;
import by.hayel.data.random.api.payload.request.FakeUsersRequest;
import by.hayel.data.random.api.service.FakeGenerationService;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fake-data")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FakeDataController {
  FakeGenerationService fakeGenerationService;
  Random generator;

  @PostMapping
  public ResponseEntity<List<User>> getRandomFakeUsers(@RequestBody FakeUsersRequest request) {
    Long count = Long.parseLong(request.getCount());
    if (count < 0) throw new NegativeDataCountException();
    Locale locale = parseLocale(request.getLocale());
    Long seed = parseSeed(request.getSeed(), request.getPage());
    Double errorRate = parseErrorRate(request.getErrorRate());
    return ResponseEntity.ok(fakeGenerationService.generateUsers(count, locale, seed, errorRate));
  }

  private Double parseErrorRate(String errorRate) {
    return errorRate == null ? 0.0 : Double.parseDouble(errorRate);
  }

  private Locale parseLocale(String locale) {
    return locale == null ? Locale.ENGLISH : Locale.forLanguageTag(locale);
  }

  private Long parseSeed(String seed, String page) {
    return (seed == null || seed.isBlank() ? generator.nextLong() : Long.parseLong(seed))
        + (page == null || page.isBlank() ? 0 : Long.parseLong(page));
  }
}
