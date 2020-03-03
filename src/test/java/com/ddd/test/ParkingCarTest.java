package com.ddd.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.ddd.parkingLot.exception.BusinessException;
import com.ddd.parkingLot.vo.Car;
import com.ddd.parkingLot.ParkingBoy;
import com.ddd.parkingLot.ParkingLotFactory;
import com.ddd.parkingLot.vo.Ticket;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class ParkingCarTest {

  @Test
  public void parkingCarWhenParkingLotIsEmpty(){
    List<String> order = new ArrayList<>();
    order.add("1");
    ParkingLotFactory parkingLotFactory = new ParkingLotFactory();
    parkingLotFactory.create(order,3);

    ParkingBoy parkingBoy = new ParkingBoy(parkingLotFactory);
    Ticket ticket = parkingBoy.parkingCar(new Car("0909"));


    Car car = parkingBoy.pickingCar(ticket);
    assertEquals("0909",car.getCarNumber());


  }

  @Test
  public void parkingCarFailWhenParkingLotIsFull(){
    List<String> order = new ArrayList<>();
    order.add("1");
    ParkingLotFactory parkingLotFactory = new ParkingLotFactory();
    parkingLotFactory.create(order);

    ParkingBoy parkingBoy = new ParkingBoy(parkingLotFactory);
    Ticket ticket = parkingBoy.parkingCar(new Car("0909"));

    String message =assertThrows(BusinessException.class, () ->
        parkingBoy.parkingCar(new Car("0202"))).getMessage();
    assertEquals("没有车位了",message);
  }

  @Test
  public void parkingCarWhenFirstParkingLotIsFullButNextHasEmpty()  {
    List<String> order = new ArrayList<>();
    order.add("1");
    order.add("2");
    order.add("3");
    ParkingLotFactory parkingLotFactory = new ParkingLotFactory();
    parkingLotFactory.create(order, 1);

    ParkingBoy parkingBoy = new ParkingBoy(parkingLotFactory);
    Ticket ticket1 = parkingBoy.parkingCar(new Car("0909"));
    Ticket ticket2 = parkingBoy.parkingCar(new Car("0202"));
    assertEquals("2",ticket2.getParkingLotNo());

  }

  @Test
  public void pickingCarFailWhenCarHasPicked()  {
    List<String> order = new ArrayList<>();
    order.add("1");
    order.add("2");
    ParkingLotFactory parkingLotFactory = new ParkingLotFactory();
    parkingLotFactory.create(order, 2);

    ParkingBoy parkingBoy = new ParkingBoy(parkingLotFactory);
    Ticket ticket1 = parkingBoy.parkingCar(new Car("0909"));
    Ticket ticket2 = parkingBoy.parkingCar(new Car("0202"));
    Ticket ticket3 = parkingBoy.parkingCar(new Car("0201"));

    parkingBoy.pickingCar(ticket3);
    assertEquals("2",ticket3.getParkingLotNo());
    String message = assertThrows(BusinessException.class, () ->
        parkingBoy.pickingCar(ticket3)).getMessage();
    assertEquals("假票",message);

  }

  @Test
  public void pickingCarFailWhenTicketIsFake()  {
    List<String> order = new ArrayList<>();
    order.add("1");
    ParkingLotFactory parkingLotFactory = new ParkingLotFactory();
    parkingLotFactory.create(order, 1);

    ParkingBoy parkingBoy = new ParkingBoy(parkingLotFactory);
    Ticket ticket1 = parkingBoy.parkingCar(new Car("0909"));
    String message = assertThrows(BusinessException.class, () ->
        parkingBoy.pickingCar(new Ticket("123","2"))).getMessage();
    assertEquals("假票",message);

  }
}
