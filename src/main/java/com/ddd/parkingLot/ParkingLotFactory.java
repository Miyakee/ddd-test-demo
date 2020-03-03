package com.ddd.parkingLot;

import com.ddd.parkingLot.exception.BusinessException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLotFactory {
  private Map<String,ParkingLot> parkingManagement = new HashMap<>();
  private List<String> parkingIdsOrder = new ArrayList<>();

  List<String> getParkingIdsOrder() {
    return parkingIdsOrder;
  }

  public void create(List<String> parkingLotOrder,int size){
    parkingLotOrder.stream().forEach(
        parkingLotId->{
          ParkingLot parkingLot = new ParkingLot(parkingLotId,size);
          parkingManagement.put(parkingLotId,parkingLot);
          parkingIdsOrder.add(parkingLotId);
        }
    );
  }

  public void create(List<String> parkingLotOrder){
    parkingLotOrder.stream().forEach(
        parkingLotId->{
          ParkingLot parkingLot = new ParkingLot(parkingLotId);
          parkingManagement.put(parkingLotId,parkingLot);
          parkingIdsOrder.add(parkingLotId);
        }
    );

  }

  ParkingLot findParkingLot(String parkingLotNumber){
    if (!parkingManagement.containsKey(parkingLotNumber)){
      throw new BusinessException("假票");
    }
   return parkingManagement.get(parkingLotNumber);
  }
}
