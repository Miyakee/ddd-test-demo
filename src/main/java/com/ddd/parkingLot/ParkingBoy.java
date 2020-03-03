package com.ddd.parkingLot;

import com.ddd.parkingLot.exception.BusinessException;
import com.ddd.parkingLot.vo.Car;
import com.ddd.parkingLot.vo.Ticket;
import java.util.List;

public class ParkingBoy {

  private ParkingLotFactory parkingLotFactory ;

  //生成boy的时候 需要交给他一个停车场
  public ParkingBoy(ParkingLotFactory parkingLotFactory) {
    this.parkingLotFactory = parkingLotFactory;
  }

  public Ticket parkingCar(Car car) {
    Ticket ticket;
    List<String> parkingOrder = parkingLotFactory.getParkingIdsOrder();
    for (String parkingLotNumber:parkingOrder){
      ParkingLot parkingLot = parkingLotFactory.findParkingLot(parkingLotNumber);
      try {
        ticket = parkingLot.parking(car);
        return ticket;
      }catch (BusinessException e){
          if (!e.getMessage().equals("该停车场车已经停满")){
            throw e;
          }
      }
    }
    throw  new BusinessException("没有车位了");
  }

  public Car pickingCar(Ticket ticket) {
    ParkingLot parkingLot  = parkingLotFactory.findParkingLot(ticket.getParkingLotNo());
    return parkingLot.picking(ticket);
  }

}
