package com.parkit.parkingsystem.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class InputReaderUtil {

    private static Scanner scan = new Scanner(System.in);
    private static final Logger LOGGER = LogManager.getLogger("InputReaderUtil");

    /**
     * 
     * @return
     */
    public int readSelection() {
        try {
            return Integer.parseInt(scan.nextLine());
        } catch (Exception e) {
            LOGGER.error("Error while reading user input from Shell", e);
            LOGGER.debug("Error reading input. Please enter valid number for proceeding further");
            return -1;
        }
    }

    /**
     * 
     * @return
     * @throws IllegalArgumentException
     */
    public String readVehicleRegistrationNumber() throws IllegalArgumentException {
        try {
            String vehicleRegNumber = scan.nextLine();
            if (vehicleRegNumber == null || vehicleRegNumber.trim().length() == 0) {
                throw new IllegalArgumentException("Invalid input provided");
            }
            return vehicleRegNumber;
        } catch (IllegalArgumentException e) {
            LOGGER.error("Error while reading user input from Shell", e);
            LOGGER.debug("Error reading input. Please enter a valid string for vehicle registration number");
            throw e;
        }
    }
}
