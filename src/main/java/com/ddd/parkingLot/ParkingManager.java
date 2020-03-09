package com.ddd.parkingLot;

import com.ddd.parkingLot.exception.BusinessException;
import java.util.List;

public class ParkingManager {
  private List<ParkingBoy> parkingBoys;

  public ParkingManager(List<ParkingBoy> parkingBoys) {
    this.parkingBoys = parkingBoys;
  }

  public ParkingLot findParkingLotByParkingBoy(ParkingBoy parkingBoy){
   return parkingBoy.findParkingLot();
  }

 public ParkingLot findParkingLotByParkingBoyId(String parkingBoyId){
    ParkingBoy parkingBoy=parkingBoys.stream().filter(eachBoy -> eachBoy.getId() == parkingBoyId).findAny().orElseThrow(()->new BusinessException("boy不存在"));
    return findParkingLotByParkingBoy(parkingBoy);
  }

}
