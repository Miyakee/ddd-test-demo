package com.ddd.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.ddd.parkingLot.MaxSpaceFirstParkingStrategy;
import com.ddd.parkingLot.NaturalParkingStrategy;
import com.ddd.parkingLot.ParkingLot;
import com.ddd.parkingLot.ParkingLotCenter;
import com.ddd.parkingLot.ParkingManager;
import com.ddd.parkingLot.Car;
import com.ddd.parkingLot.ParkingBoy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;

public class ParkingCarTest {

  @Test
  public void find_natural_Space_when_junior_parkingBoy(){
    List<String> order = new ArrayList<>();
    order.add("1");
    order.add("2");
    ParkingLotCenter parkingLotCenter = new ParkingLotCenter();
    parkingLotCenter.create(order,3);
    Map<String, ParkingLot> allParkingLost= parkingLotCenter.getAllParkingLots();

    List<ParkingBoy> parkingBoys = new ArrayList<>();
    ParkingBoy parkingBoyJunior = new ParkingBoy(allParkingLost,"001",new NaturalParkingStrategy());
    ParkingBoy parkingBoySenior = new ParkingBoy(allParkingLost,"002",new MaxSpaceFirstParkingStrategy());
    parkingBoys.add(parkingBoyJunior);
    parkingBoys.add(parkingBoySenior);
    ParkingManager parkingManager= new ParkingManager(parkingBoys);
    ParkingLot  parkingLot =  parkingManager.findParkingLotByParkingBoyId("001");
    parkingLot.parking(new Car("0202"));
    parkingLot.parking(new Car("0203"));
    assertEquals("1",
    parkingManager.findParkingLotByParkingBoyId("001").getId());
    assertEquals("2",
        parkingManager.findParkingLotByParkingBoyId("002").getId());
  }



  @Test
  public void find_max_Space_when_senior_parkingBoy(){
    List<String> order = new ArrayList<>();
    order.add("1");
    order.add("2");
    order.add("3");
    ParkingLotCenter parkingLotCenter = new ParkingLotCenter();
    parkingLotCenter.create(order,3);
    Map<String, ParkingLot> allParkingLost= parkingLotCenter.getAllParkingLots();

    List<ParkingBoy> parkingBoys = new ArrayList<>();
    ParkingBoy parkingBoyJunior = new ParkingBoy(allParkingLost,"001",new NaturalParkingStrategy());
    ParkingBoy parkingBoySenior = new ParkingBoy(allParkingLost,"002",new MaxSpaceFirstParkingStrategy());
    parkingBoys.add(parkingBoyJunior);
    parkingBoys.add(parkingBoySenior);
    ParkingManager parkingManager= new ParkingManager(parkingBoys);
    ParkingLot  parkingLot =  parkingManager.findParkingLotByParkingBoyId("001");
    parkingLot.parking(new Car("0202"));
    parkingLot.parking(new Car("0203"));
    parkingLot.parking(new Car("0204"));
    assertEquals("2",
        parkingManager.findParkingLotByParkingBoyId("001").getId());
    ParkingLot parkingLotMaxxed =parkingManager.findParkingLotByParkingBoyId("002");
    parkingLotMaxxed.parking(new Car("0101"));
    assertEquals("3",
        parkingManager.findParkingLotByParkingBoyId("002").getId());
  }

  @Test
  public void find_max_Space_when_parkingBoy_has_different_parkingLot(){
    List<String> order = new ArrayList<>();
    order.add("1");
    order.add("2");
    order.add("3");
    ParkingLotCenter parkingLotCenter = new ParkingLotCenter();
    parkingLotCenter.create(order,3);
    Map<String, ParkingLot> allParkingLost= parkingLotCenter.getAllParkingLots();

    List<ParkingBoy> parkingBoys = new ArrayList<>();
    Map<String,ParkingLot> seniorParkingLots = new HashMap<>();
    seniorParkingLots.put("1",allParkingLost.get("1"));
    seniorParkingLots.put("2",allParkingLost.get("2"));
    Map<String,ParkingLot> juniorParkingLots = new HashMap<>();
    juniorParkingLots.put("3",allParkingLost.get("3"));
    ParkingBoy parkingBoyJunior = new ParkingBoy(juniorParkingLots,"001",new NaturalParkingStrategy());
    ParkingBoy parkingBoySenior = new ParkingBoy(seniorParkingLots,"002",new MaxSpaceFirstParkingStrategy());
    parkingBoys.add(parkingBoyJunior);
    parkingBoys.add(parkingBoySenior);
    ParkingManager parkingManager= new ParkingManager(parkingBoys);
    ParkingLot  parkingLot =  parkingManager.findParkingLotByParkingBoyId("001");
    parkingLot.parking(new Car("0202"));
    parkingLot.parking(new Car("0203"));
    assertEquals("3",
        parkingManager.findParkingLotByParkingBoyId("001").getId());
    ParkingLot parkingLotMaxxed =parkingManager.findParkingLotByParkingBoyId("002");
    parkingLotMaxxed.parking(new Car("0101"));
    assertEquals("2",
        parkingManager.findParkingLotByParkingBoyId("002").getId());
    parkingLotMaxxed = parkingManager.findParkingLotByParkingBoyId("002");
    parkingLotMaxxed.parking(new Car("0102"));
    parkingLotMaxxed.parking(new Car("0103"));
    assertEquals("1",
        parkingManager.findParkingLotByParkingBoyId("002").getId());

  }


//  @Test
//  public void parkingCarFailWhenParkingLotIsFull(){
//    List<String> order = new ArrayList<>();
//    order.add("1");
//    ParkingLotCenter parkingLotCenter = new ParkingLotCenter();
//    parkingLotCenter.create(order);
//
//    ParkingBoy parkingBoy = new ParkingBoy(parkingLotCenter);
//    Ticket ticket = parkingBoy.parkingCar(new Car("0909"));
//
//    String message =assertThrows(BusinessException.class, () ->
//        parkingBoy.parkingCar(new Car("0202"))).getMessage();
//    assertEquals("没有车位了",message);
//  }
//
//  @Test
//  public void parkingCarWhenFirstParkingLotIsFullButNextHasEmpty()  {
//    List<String> order = new ArrayList<>();
//    order.add("1");
//    order.add("2");
//    order.add("3");
//    ParkingLotCenter parkingLotCenter = new ParkingLotCenter();
//    parkingLotCenter.create(order, 1);
//
//    ParkingBoy parkingBoy = new ParkingBoy(parkingLotCenter);
//    Ticket ticket1 = parkingBoy.parkingCar(new Car("0909"));
//    Ticket ticket2 = parkingBoy.parkingCar(new Car("0202"));
//    assertEquals("2",ticket2.getParkingLotNo());
//
//  }
//
//  @Test
//  public void pickingCarFailWhenCarHasPicked()  {
//    List<String> order = new ArrayList<>();
//    order.add("1");
//    order.add("2");
//    ParkingLotCenter parkingLotCenter = new ParkingLotCenter();
//    parkingLotCenter.create(order, 2);
//
//    ParkingBoy parkingBoy = new ParkingBoy(parkingLotCenter);
//    Ticket ticket1 = parkingBoy.parkingCar(new Car("0909"));
//    Ticket ticket2 = parkingBoy.parkingCar(new Car("0202"));
//    Ticket ticket3 = parkingBoy.parkingCar(new Car("0201"));
//
//    parkingBoy.pickingCar(ticket3);
//    assertEquals("2",ticket3.getParkingLotNo());
//    String message = assertThrows(BusinessException.class, () ->
//        parkingBoy.pickingCar(ticket3)).getMessage();
//    assertEquals("假票",message);
//
//  }
//
//  @Test
//  public void pickingCarFailWhenTicketIsFake()  {
//    List<String> order = new ArrayList<>();
//    order.add("1");
//    ParkingLotCenter parkingLotCenter = new ParkingLotCenter();
//    parkingLotCenter.create(order, 1);
//
//    ParkingBoy parkingBoy = new ParkingBoy(parkingLotCenter);
//    Ticket ticket1 = parkingBoy.parkingCar(new Car("0909"));
//    String message = assertThrows(BusinessException.class, () ->
//        parkingBoy.pickingCar(new Ticket("123","2"))).getMessage();
//    assertEquals("假票",message);
//
//  }
//
//
//  @Test
//  public void test()  {
//    List<String> order = new ArrayList<>();
//    order.add("1");
//    ParkingLotCenter parkingLotCenter = new ParkingLotCenter();
//    parkingLotCenter.create(order, 1);
//
//    ParkingBoy parkingBoy = new ParkingBoy(parkingLotCenter);
//    System.out.println(parkingBoy.findParkingLotBySenior());
//
//  }
}
