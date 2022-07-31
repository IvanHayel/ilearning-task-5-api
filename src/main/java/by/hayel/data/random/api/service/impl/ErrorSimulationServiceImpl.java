package by.hayel.data.random.api.service.impl;

import by.hayel.data.random.api.model.location.Location;
import by.hayel.data.random.api.model.location.Street;
import by.hayel.data.random.api.model.user.FakeUser;
import by.hayel.data.random.api.model.user.Id;
import by.hayel.data.random.api.model.user.Name;
import by.hayel.data.random.api.service.ErrorSimulationService;
import by.hayel.data.random.api.simulation.SimulationErrorField;
import by.hayel.data.random.api.simulation.SimulationErrorType;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class ErrorSimulationServiceImpl implements ErrorSimulationService {
  private static final double MIN_ERROR_RATE = 0;
  private static final double MAX_ERROR_RATE = 1000;
  private static final Random GENERATOR = new Random();

  @Override
  public synchronized List<FakeUser> simulateErrors(List<FakeUser> users, double rate) {
    if (rate <= MIN_ERROR_RATE || rate > MAX_ERROR_RATE) return users;
    Set<FakeUser> usersWithErrors = new HashSet<>();
    for(FakeUser user : users) {
      double counter = rate;
      while (counter > 0) {
        SimulationErrorType errorType = getRandomErrorType();
        SimulationErrorField errorField = getRandomErrorField();
        if(counter >= 1) {
          simulateError(user, errorType, errorField);
        } else if(counter > 0 && GENERATOR.nextDouble() < counter) {
          simulateError(user, errorType, errorField);
        }
        counter -= 1;
      }
      usersWithErrors.add(user);
    }
    return new ArrayList<>(usersWithErrors);
  }

  private SimulationErrorType getRandomErrorType() {
    int random = GENERATOR.nextInt(SimulationErrorType.values().length);
    return SimulationErrorType.values()[random];
  }

  private SimulationErrorField getRandomErrorField() {
    int random = GENERATOR.nextInt(SimulationErrorField.values().length);
    return SimulationErrorField.values()[random];
  }

  private void simulateError(
      FakeUser user, SimulationErrorType errorType, SimulationErrorField errorField) {
    switch (errorType) {
      case REMOVE_SYMBOL -> removeRandomSymbol(user, errorField);
      case ADD_SYMBOL -> addRandomSymbol(user, errorField);
      case SWAP_SYMBOLS -> swapRandomSymbols(user, errorField);
    }
  }

  private void removeRandomSymbol(FakeUser user, SimulationErrorField errorField) {
    switch (errorField) {
      case ID_NAME -> {
        Id id = user.getId();
        id.setName(removeRandomSymbol(id.getName()));
        user.setId(id);
      }
      case ID_VALUE -> {
        Id id = user.getId();
        id.setValue(removeRandomSymbol(id.getValue()));
        user.setId(id);
      }
      case NAME_FIRST -> {
        Name name = user.getName();
        name.setFirst(removeRandomSymbol(name.getFirst()));
        user.setName(name);
      }
      case NAME_LAST -> {
        Name name = user.getName();
        name.setLast(removeRandomSymbol(name.getLast()));
        user.setName(name);
      }
      case NAME_TITLE -> {
        Name name = user.getName();
        name.setTitle(removeRandomSymbol(name.getTitle()));
        user.setName(name);
      }
      case LOCATION_COUNTRY -> {
        Location location = user.getLocation();
        location.setCountry(removeRandomSymbol(location.getCountry()));
        user.setLocation(location);
      }
      case LOCATION_CITY -> {
        Location location = user.getLocation();
        location.setCity(removeRandomSymbol(location.getCity()));
        user.setLocation(location);
      }
      case LOCATION_STREET_NAME -> {
        Location location = user.getLocation();
        Street street = location.getStreet();
        street.setName(removeRandomSymbol(street.getName()));
        location.setStreet(street);
        user.setLocation(location);
      }
      case LOCATION_STREET_NUMBER -> {
        Location location = user.getLocation();
        Street street = location.getStreet();
        street.setNumber(removeRandomSymbol(street.getNumber()));
        location.setStreet(street);
        user.setLocation(location);
      }
      case LOCATION_POSTAL_CODE -> {
        Location location = user.getLocation();
        location.setPostcode(removeRandomSymbol(location.getPostcode()));
        user.setLocation(location);
      }
      case PHONE -> {
        user.setPhone(removeRandomSymbol(user.getPhone()));
      }
    }
  }

  private String removeRandomSymbol(String string) {
    if(string == null || string.isEmpty()) return string;
    StringBuilder builder = new StringBuilder(string);
    int random = GENERATOR.nextInt(builder.length());
    builder.deleteCharAt(random);
    return builder.toString();
  }

  private void addRandomSymbol(FakeUser user, SimulationErrorField errorField) {
    switch (errorField) {
      case ID_NAME -> {
        Id id = user.getId();
        id.setName(addRandomSymbol(id.getName()));
        user.setId(id);
      }
      case ID_VALUE -> {
        Id id = user.getId();
        id.setValue(addRandomDigit(id.getValue()));
        user.setId(id);
      }
      case NAME_FIRST -> {
        Name name = user.getName();
        name.setFirst(addRandomSymbol(name.getFirst()));
        user.setName(name);
      }
      case NAME_LAST -> {
        Name name = user.getName();
        name.setLast(addRandomSymbol(name.getLast()));
        user.setName(name);
      }
      case NAME_TITLE -> {
        Name name = user.getName();
        name.setTitle(addRandomSymbol(name.getTitle()));
        user.setName(name);
      }
      case LOCATION_COUNTRY -> {
        Location location = user.getLocation();
        location.setCountry(addRandomSymbol(location.getCountry()));
        user.setLocation(location);
      }
      case LOCATION_CITY -> {
        Location location = user.getLocation();
        location.setCity(addRandomSymbol(location.getCity()));
        user.setLocation(location);
      }
      case LOCATION_STREET_NAME -> {
        Location location = user.getLocation();
        Street street = location.getStreet();
        street.setName(addRandomSymbol(street.getName()));
        location.setStreet(street);
        user.setLocation(location);
      }
      case LOCATION_STREET_NUMBER -> {
        Location location = user.getLocation();
        Street street = location.getStreet();
        street.setNumber(addRandomDigit(street.getNumber()));
        location.setStreet(street);
        user.setLocation(location);
      }
      case LOCATION_POSTAL_CODE -> {
        Location location = user.getLocation();
        location.setPostcode(addRandomDigit(location.getPostcode()));
        user.setLocation(location);
      }
    }
  }

  private String addRandomSymbol(String string) {
    if(string == null || string.isEmpty()) return string;
    StringBuilder builder = new StringBuilder(string);
    int random = GENERATOR.nextInt(builder.length());
    builder.insert(random, getRandomSymbol(string));
    return builder.toString();
  }

  private char getRandomSymbol(String seed) {
    if(seed == null || seed.isEmpty()) return (char) (GENERATOR.nextInt() + 'a');
    return (char) (1 + GENERATOR.nextInt(10) + seed.charAt(GENERATOR.nextInt(seed.length())));
  }

  private String addRandomDigit(String string) {
    if(string == null || string.isEmpty()) return string;
    StringBuilder builder = new StringBuilder(string);
    int random = GENERATOR.nextInt(builder.length());
    builder.insert(random, getRandomDigit());
    return builder.toString();
  }

  private char getRandomDigit() {
    return (char) (GENERATOR.nextInt(10) + '0');
  }

  private void swapRandomSymbols(FakeUser user, SimulationErrorField errorField) {
    switch (errorField) {
      case ID_NAME -> {
        Id id = user.getId();
        id.setName(swapRandomSymbols(id.getName()));
        user.setId(id);
      }
      case ID_VALUE -> {
        Id id = user.getId();
        id.setValue(swapRandomSymbols(id.getValue()));
        user.setId(id);
      }
      case NAME_FIRST -> {
        Name name = user.getName();
        name.setFirst(swapRandomSymbols(name.getFirst()));
        user.setName(name);
      }
      case NAME_LAST -> {
        Name name = user.getName();
        name.setLast(swapRandomSymbols(name.getLast()));
        user.setName(name);
      }
      case NAME_TITLE -> {
        Name name = user.getName();
        name.setTitle(swapRandomSymbols(name.getTitle()));
        user.setName(name);
      }
      case LOCATION_COUNTRY -> {
        Location location = user.getLocation();
        location.setCountry(swapRandomSymbols(location.getCountry()));
        user.setLocation(location);
      }
      case LOCATION_CITY -> {
        Location location = user.getLocation();
        location.setCity(swapRandomSymbols(location.getCity()));
        user.setLocation(location);
      }
      case LOCATION_STREET_NAME -> {
        Location location = user.getLocation();
        Street street = location.getStreet();
        street.setName(swapRandomSymbols(street.getName()));
        location.setStreet(street);
        user.setLocation(location);
      }
      case LOCATION_STREET_NUMBER -> {
        Location location = user.getLocation();
        Street street = location.getStreet();
        street.setNumber(swapRandomSymbols(street.getNumber()));
        location.setStreet(street);
        user.setLocation(location);
      }
      case LOCATION_POSTAL_CODE -> {
        Location location = user.getLocation();
        location.setPostcode(swapRandomSymbols(location.getPostcode()));
        user.setLocation(location);
      }
      case PHONE -> {
        user.setPhone(swapRandomSymbols(user.getPhone()));
      }
    }
  }

  private String swapRandomSymbols(String string) {
    if(string == null || string.isEmpty()) return string;
    StringBuilder builder = new StringBuilder(string);
    int firstIndex = GENERATOR.nextInt(builder.length());
    int secondIndex = GENERATOR.nextInt(builder.length());
    char firstChar = builder.charAt(firstIndex);
    char secondChar = builder.charAt(secondIndex);
    builder.setCharAt(firstIndex, secondChar);
    builder.setCharAt(secondIndex, firstChar);
    return builder.toString();
  }
}
