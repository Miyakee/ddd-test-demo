package com.ddd.parkingLot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLotCenter {
  private Map<String,ParkingLot> allParkingLots = new HashMap<>();
  private List<String> parkingIdsOrder = new ArrayList<>();

  public Map<String, ParkingLot> getAllParkingLots() {
    return allParkingLots;
  }

  List<String> getParkingIdsOrder() {
    return parkingIdsOrder;
  }

  public void create(List<String> parkingLotOrder,int size){
    parkingLotOrder.stream().forEach(
        parkingLotId->{
          ParkingLot parkingLot = new ParkingLot(parkingLotId,size);
          allParkingLots.put(parkingLotId,parkingLot);
          parkingIdsOrder.add(parkingLotId);
        }
    );
  }

  public void create(List<String> parkingLotOrder){
    parkingLotOrder.stream().forEach(
        parkingLotId->{
          ParkingLot parkingLot = new ParkingLot(parkingLotId);
          allParkingLots.put(parkingLotId,parkingLot);
          parkingIdsOrder.add(parkingLotId);
        }
    );

  }

//  ParkingLot findParkingLot(String parkingLotNumber){
//    if (!allParkingLots.containsKey(parkingLotNumber)){
//      throw new BusinessException("假票");
//    }
//   return allParkingLots.get(parkingLotNumber);
//  }
}
