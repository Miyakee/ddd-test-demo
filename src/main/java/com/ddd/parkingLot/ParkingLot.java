package com.ddd.parkingLot;

import static com.google.common.hash.Hashing.*;

import com.ddd.parkingLot.exception.BusinessException;
import com.google.common.base.Charsets;
import java.util.HashMap;

public class ParkingLot {

  private HashMap<String, Car> currentLots = new HashMap<>();
  private String id;
  private int limit = 1;
  private final static String SALT_PASSWORD = "parkingLot";

  public String getId() {
    return id;
  }

  ParkingLot(String id, int limit) {
    this.id = id;
    this.limit = limit;
  }

  ParkingLot(String id) {
    this.id = id;
  }

  public Ticket parking(Car car) {
    if (!hasEmpty()) {
      throw new BusinessException("该停车场车已经停满");
    }
    String token = generateTicketToken(car.getCarNumber());
    currentLots.put(token, car);
    return new Ticket(token, id);
  }

  Car picking(Ticket ticket) {
    if (validTicket(ticket)) {
      Car car = currentLots.get(ticket.getToken());
      currentLots.remove(ticket.getToken());
      return car;
    }
    throw new BusinessException("假票");
  }

  boolean hasEmpty() {
    return limit > currentLots.size();
  }


  private boolean validTicket(Ticket ticket) {
    return currentLots.containsKey(ticket.getToken()) && generateTicketToken(
        currentLots.get(ticket.getToken()).getCarNumber()).equals(ticket.getToken());
  }

  private String generateTicketToken(String carNumber) {
    carNumber = carNumber + SALT_PASSWORD;
    return sha256().newHasher().putString(carNumber, Charsets.UTF_8).hash().toString();

  }

  Boolean moreThan(ParkingLot parkingLot){
    if(parkingLot == null){
      return true;
    }
    return this.currentLots.size()<parkingLot.currentLots.size();
  }

}
