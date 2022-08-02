package by.hayel.data.random.api.service.impl;

import by.hayel.data.random.api.model.user.User;
import by.hayel.data.random.api.service.ErrorSimulationService;
import by.hayel.data.random.api.simulation.SimulationErrorField;
import by.hayel.data.random.api.simulation.SimulationErrorType;
import java.util.List;
import java.util.Random;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ErrorSimulationServiceImpl implements ErrorSimulationService {
  private static final double MIN_ERROR_RATE = 0;
  private static final double MAX_ERROR_RATE = 1000;

  Random generator;

  private Object getRandom(Object[] objects) {
    if(objects == null || objects.length == 0) return null;
    return objects[generator.nextInt(objects.length)];
  }

  @Override
  public List<User> simulateErrors(List<User> users, Double rate) {
    if(rate == null || rate < MIN_ERROR_RATE || rate > MAX_ERROR_RATE) return users;
    for (User user : users) {
      double tempRate = rate;
      while (tempRate > 0) {
        SimulationErrorField field = (SimulationErrorField) getRandom(SimulationErrorField.values());
        SimulationErrorType type = (SimulationErrorType) getRandom(SimulationErrorType.values());
        simulateError(user, field, type, rate);
        tempRate--;
      }
    }
    return users;
  }

  private void simulateError(User user, SimulationErrorField field, SimulationErrorType type,
      Double rate) {
    if(rate < 1 && generator.nextDouble() > rate) return;
    switch (type) {
      case ADD_SYMBOL -> addRandomSymbol(user, field);
      case REMOVE_SYMBOL -> removeRandomSymbol(user, field);
      case SWAP_SYMBOLS -> swapRandomSymbols(user, field);
    }
  }

  private void addRandomSymbol(User user, SimulationErrorField field) {
    switch (field) {
      case ID -> user.setId(addRandomDigit(user.getId()));
      case NAME -> user.setName(addRandomSymbol(user.getName()));
      case ADDRESS -> user.setAddress(addRandomSymbol(user.getAddress()));
      case PHONE -> user.setPhone(addRandomSymbol(user.getPhone()));
    }
  }

  private String addRandomSymbol(String string) {
    if(string == null || string.isEmpty())
      return String.valueOf(generator.nextInt(Character.MAX_VALUE));
    StringBuilder builder = new StringBuilder(string);
    int position = generator.nextInt(string.length());
    char symbol = (char) (generator.nextInt(5) + string.charAt(position));
    builder.insert(position, symbol);
    return builder.toString();
  }

  private String addRandomDigit(String string) {
    if(string == null || string.isEmpty())
      return String.valueOf(generator.nextInt(10));
    StringBuilder builder = new StringBuilder(string);
    int position = generator.nextInt(string.length());
    builder.insert(position, generator.nextInt(10));
    return builder.toString();
  }

  private void removeRandomSymbol(User user, SimulationErrorField field) {
    switch (field) {
      case ID -> user.setId(removeRandomSymbol(user.getId()));
      case NAME -> user.setName(removeRandomSymbol(user.getName()));
      case ADDRESS -> user.setAddress(removeRandomSymbol(user.getAddress()));
      case PHONE -> user.setPhone(removeRandomSymbol(user.getPhone()));
    }
  }

  private String removeRandomSymbol(String string) {
    if(string == null || string.isEmpty()) return string;
    int index = generator.nextInt(string.length());
    StringBuilder builder = new StringBuilder(string);
    builder.deleteCharAt(index);
    return builder.toString();
  }

  private void swapRandomSymbols(User user, SimulationErrorField field) {
    switch (field) {
      case ID -> user.setId(swapRandomSymbols(user.getId()));
      case NAME -> user.setName(swapRandomSymbols(user.getName()));
      case ADDRESS -> user.setAddress(swapRandomSymbols(user.getAddress()));
      case PHONE -> user.setPhone(swapRandomSymbols(user.getPhone()));
    }
  }

  private String swapRandomSymbols(String string) {
    if(string == null || string.length() < 2) return string;
    int firstIndex = generator.nextInt(string.length());
    int secondIndex = generator.nextInt(string.length());
    StringBuilder builder = new StringBuilder(string);
    builder.setCharAt(firstIndex, string.charAt(secondIndex));
    builder.setCharAt(secondIndex, string.charAt(firstIndex));
    return builder.toString();
  }
}
