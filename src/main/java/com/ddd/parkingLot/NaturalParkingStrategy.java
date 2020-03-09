package com.ddd.parkingLot;

import com.ddd.parkingLot.exception.BusinessException;
import java.util.Map;

public class NaturalParkingStrategy implements ParkingStrategy {

  @Override
  public ParkingLot findParkingLot(Map<String,ParkingLot> parkingLots) {
    for (ParkingLot parkingLot : parkingLots.values()) {
      if (parkingLot.hasEmpty()) {
        return parkingLot;
      }
    }
    throw new BusinessException("没有车位了");
  }
}

