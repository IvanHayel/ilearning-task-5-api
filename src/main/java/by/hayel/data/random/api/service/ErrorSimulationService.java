package by.hayel.data.random.api.service;

import by.hayel.data.random.api.model.user.FakeUser;
import java.util.List;

public interface ErrorSimulationService {
  List<FakeUser> simulateErrors(List<FakeUser> users, double rate);
}
