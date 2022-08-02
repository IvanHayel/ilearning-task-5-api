package by.hayel.data.random.api.service.impl;

import by.hayel.data.random.api.model.user.User;
import by.hayel.data.random.api.service.ErrorSimulationService;
import by.hayel.data.random.api.service.FakeGenerationService;
import com.github.javafaker.Faker;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FakeGenerationServiceImpl implements FakeGenerationService {
  private static final String FAKER_NOISE = "#";
  private static final String EMPTY = "";

  ErrorSimulationService errorSimulationService;

  @Override
  public List<User> generateUsers(Long count, Locale locale, Long seed, Double errorRate) {
    if (count < 0) return Collections.emptyList();
    List<User> fakeUsers = new ArrayList<>();
    while (count > 0) {
      Random currentSeed = seed == null ? new Random() : new Random(seed + count);
      Faker faker = new Faker(locale, currentSeed);
      User fakeUser = new User();
      fakeUser.setId(faker.idNumber().ssnValid());
      fakeUser.setName(faker.name().nameWithMiddle());
      fakeUser.setAddress(faker.address().fullAddress().replace(FAKER_NOISE, EMPTY).strip());
      fakeUser.setPhone(faker.phoneNumber().phoneNumber());
      fakeUsers.add(fakeUser);
      count--;
    }
    return errorSimulationService.simulateErrors(fakeUsers, errorRate);
  }
}
