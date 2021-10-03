package com.parkit.parkingsystem.service;

import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.dao.TicketDAO;
import com.parkit.parkingsystem.model.Ticket;

public class FareCalculatorService {

    public void calculateFare(Ticket ticket) {
        if( (ticket.getOutTime() == null) || 
        		(ticket.getOutTime().before(ticket.getInTime())) ) {
            throw new IllegalArgumentException("Out time provided is incorrect:" 
        		+ ticket.getOutTime());
        }

        final long HOUR_TO_MILLISEC = 60 * 60 * 1000;
        long inHour = ticket.getInTime().getTime(); 
        long outHour = ticket.getOutTime().getTime(); 
        
        // Some tests are failing here. Need to check if this logic is correct
        double duration = (outHour - inHour) / (double)HOUR_TO_MILLISEC;
        
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

	public double roundParkingFare(double price) {
		return ((int)(price * 100)) / 100d;
	} 
}