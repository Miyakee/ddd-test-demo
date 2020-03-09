package com.ddd.parkingLot;


import java.util.Map;

public interface ParkingStrategy {
  ParkingLot findParkingLot(Map<String,ParkingLot> parkingLots);
}
