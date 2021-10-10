/**
 * Package Parking System
 */
package com.parkit.parkingsystem;

import com.parkit.parkingsystem.service.InteractiveShell;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class provides the main entry of the Parking System application.
 * @author eayichath KELANI
 */
public class App {
	/**
	 * constant that declares a logger.
	 */
    private static final Logger LOGGER = LogManager.getLogger("App");

    /**
     * Method main : entry point of the app.
     * @param args : argument to provide to method main
     */
    public static void main(String... args) {
    	LOGGER.info("Initializing Parking System");
    	InteractiveShell.loadInterface();
    }
}
