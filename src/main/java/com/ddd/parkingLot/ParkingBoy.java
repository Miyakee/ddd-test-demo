package com.ddd.parkingLot;

import java.util.List;
import java.util.Map;

public class ParkingBoy {

  //  private ParkingLotCenter parkingLotFactory ;
  private String id;
  private Map<String,ParkingLot> parkingLots ;
  private ParkingStrategy parkingStrategy;

  public String getId() {
    return id;
  }

  //生成boy的时候 需要交给他一个停车场
  public ParkingBoy(Map<String,ParkingLot> parkingLots, String id, ParkingStrategy parkingStrategy) {
    this.id = id;
    this.parkingLots = parkingLots;
    this.parkingStrategy = parkingStrategy;
  }


  ParkingLot findParkingLot() {
    return parkingStrategy.findParkingLot(parkingLots);
  }

//  public Ticket parkingCar(Car car) {
//    Ticket ticket;
//    List<String> parkingOrder = parkingLotFactory.getParkingIdsOrder();
//    for (String parkingLotNumber:parkingOrder){
//      ParkingLot parkingLot = parkingLotFactory.findParkingLot(parkingLotNumber);
//      try {
//        ticket = parkingLot.parking(car);
//        return ticket;
//      }catch (BusinessException e){
//          if (!e.getMessage().equals("该停车场车已经停满")){
//            throw e;
//          }
//      }
//    }
//    throw  new BusinessException("没有车位了");
//  }
//
//  public Car pickingCar(Ticket ticket) {
//    ParkingLot parkingLot  = parkingLotFactory.findParkingLot(ticket.getParkingLotNo());
//    return parkingLot.picking(ticket);
//  }

}
