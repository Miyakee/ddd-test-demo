package com.ddd.parkingLot;


import com.ddd.parkingLot.exception.BusinessException;
import java.util.Map;

public class MaxSpaceFirstParkingStrategy implements ParkingStrategy {

  @Override
  public ParkingLot findParkingLot(Map<String,ParkingLot> parkingLots) {
    ParkingLot maxSpaceParkingLot = null;
    for (ParkingLot parkingLot : parkingLots.values()) {
      if (parkingLot.moreThan(maxSpaceParkingLot)) {
        maxSpaceParkingLot = parkingLot;
      }
    }
    if (maxSpaceParkingLot==null || !maxSpaceParkingLot.hasEmpty()) {
      throw new BusinessException("没有车位了");
    }
    return maxSpaceParkingLot;
  }

}
