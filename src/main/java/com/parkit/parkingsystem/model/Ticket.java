package com.parkit.parkingsystem.model;

import java.util.Date;

public class Ticket {
    private int id;
    private ParkingSpot parkingSpot;
    private String vehicleRegNumber;
    private double price;
    private Date inTime;
    private Date outTime;
    private boolean registeredVehicle;
    
    /**
     * 
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * 
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 
     * @return
     */
    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    /**
     * 
     * @param parkingSpot
     */
    public void setParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpot = parkingSpot;
    }

    /**
     * 
     * @return
     */
    public String getVehicleRegNumber() {
        return vehicleRegNumber;
    }

    /**
     * 
     * @param vehicleRegNumber
     */
    public void setVehicleRegNumber(String vehicleRegNumber) {
        this.vehicleRegNumber = vehicleRegNumber;
    }

    /**
     * 
     * @return
     */
    public double getPrice() {
        return price;
    }

    /**
     * 
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * 
     * @return
     */
    public Date getInTime() {
        return (Date)inTime.clone();
    }

    /**
     * 
     * @param inTime
     */
    public void setInTime(Date inTime) {
        this.inTime = (Date)inTime.clone();
    }

    /**
     * 
     * @return
     */
    public Date getOutTime() {
        return outTime != null ? (Date)outTime.clone() : null;
    }

    /**
     * 
     * @param outTime
     */
    public void setOutTime(Date outTime) {
        this.outTime = outTime != null ? (Date)outTime.clone() : null;
    }

    /**
     * 
     * @return
     */
	public boolean isRegisteredVehicle() {
		return this.registeredVehicle;
	}

	/**
	 * 
	 * @param registeredVehicle
	 */
	public void setRegisteredVehicle(boolean registeredVehicle) {
		this.registeredVehicle = registeredVehicle;
	}
}
