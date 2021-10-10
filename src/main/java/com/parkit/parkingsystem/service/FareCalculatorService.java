package com.parkit.parkingsystem.service;

import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.model.Ticket;

public class FareCalculatorService {

	/**
	 * this method calculates the parking fare for an hour if type of vehicle.
	 * is car or bike, else, it throws an exception.
	 * @param ticket
	 */
    public void calculateFare(final Ticket ticket) {
        if ((ticket.getOutTime() == null) 
        		|| (ticket.getOutTime().before(ticket.getInTime()))) {
            throw new IllegalArgumentException("Out time provided is incorrect:"
        		+ ticket.getOutTime());
        }

        final long hourToMilliSec = 60 * 60 * 1000;
        long inHour = ticket.getInTime().getTime();
        long outHour = ticket.getOutTime().getTime();

        double duration = (outHour - inHour) / (double) hourToMilliSec;
        
        switch (ticket.getParkingSpot().getParkingType()) {
            case CAR: {
                calculateParkingPrice(ticket, duration, Fare.CAR_RATE_PER_HOUR);
                break;
            }
            case BIKE: {
            	calculateParkingPrice(ticket, duration, Fare.BIKE_RATE_PER_HOUR);
                break;
            }
            default: throw new IllegalArgumentException("Unkown Parking Type");
        }
    }

    /** this method calculates parking price with 5% of reduce for recurrent users.
     * when they park more than 30 minutes, else the price is 0.00.
     * @param ticket
     * @param duration
     * @param ratePerHour
     */
	private void calculateParkingPrice(Ticket ticket, double duration, double ratePerHour) {
		if (duration > 0.5) {
			double price = (duration * ratePerHour);
			
			if (ticket.isRegisteredVehicle()) {
				price *= 0.95;
			}
			
			price = roundParkingFare(price);
			ticket.setPrice(price);
		} else {
			ticket.setPrice(0.0);
		}
	}

	/**
	 * This method to round the fare calculated.
	 *
	 * @param price
	 * @return
	 */
	public double roundParkingFare(final double price) {
		return ((int)(price * 100)) / 100d;
	}
}