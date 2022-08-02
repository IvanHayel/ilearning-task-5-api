package by.hayel.data.random.api.service;

import by.hayel.data.random.api.model.user.User;
import java.util.List;

public interface ErrorSimulationService {
  List<User> simulateErrors(List<User> users, Double rate);
}
