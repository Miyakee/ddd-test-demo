package com.ddd.parkingLot;

public class Ticket {

  private String token;
  private String parkingLotNo;

  public String getToken() {
    return token;
  }

  public String getParkingLotNo() {
    return parkingLotNo;
  }

  public Ticket(String token, String parkingLotNo) {
    this.token = token;
    this.parkingLotNo = parkingLotNo;
  }
}
