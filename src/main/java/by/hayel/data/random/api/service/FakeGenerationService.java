package by.hayel.data.random.api.service;

import by.hayel.data.random.api.model.user.User;
import java.util.List;
import java.util.Locale;

public interface FakeGenerationService {
  List<User> generateUsers(Long count, Locale locale, Long seed, Double errorRate);
}
