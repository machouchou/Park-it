package com.parkit.parkingsystem.dao;

import com.parkit.parkingsystem.config.DataBaseConfig;
import com.parkit.parkingsystem.constants.DBConstants;
import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.model.ParkingSpot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ParkingSpotDAO {
    private static final Logger LOGGER = LogManager.getLogger("ParkingSpotDAO");

    private DataBaseConfig dataBaseConfig;
    
    /** 
     * @param 
     */
    public ParkingSpotDAO() {
    	dataBaseConfig = new DataBaseConfig();
    }
    
    /**
     * 
     * @return
     */
    public DataBaseConfig getDataBaseConfig() {
    	return this.dataBaseConfig;
    }
    
    /**
     * 
     * @param dataBaseConfig
     */
    public void setDataBaseConfig(DataBaseConfig dataBaseConfig) {
    	this.dataBaseConfig = dataBaseConfig;
    }
    /** getNextAvailableSlot method gets the first slot of parking which is available.
     * @param parkingType
     * @return
     */
    public int getNextAvailableSlot(ParkingType parkingType) {
        Connection con = null;
        PreparedStatement ps = null;
        int result = -1;
        try {
            con = dataBaseConfig.getConnection();
            ps = con.prepareStatement(DBConstants.GET_NEXT_PARKING_SPOT);
            ps.setString(1, parkingType.toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getInt(1);
            }
            dataBaseConfig.closeResultSet(rs);
            dataBaseConfig.closePreparedStatement(ps);
        } catch (Exception ex) {
            LOGGER.error("Error fetching next available slot", ex);
        } finally {
            dataBaseConfig.closeConnection(con);
            dataBaseConfig.closePreparedStatement(ps);
        }
        
        return result;
    }

    /** this method updates the parking spot.
     * @param parkingSpot
     * @return that one row is updated;
     */
    public boolean updateParking(ParkingSpot parkingSpot) {
        //update the availability fo that parking slot
        Connection con = null;
        int updateRowCount = 0;
        PreparedStatement ps = null;

        try {
            con = dataBaseConfig.getConnection();
            ps = con.prepareStatement(DBConstants.UPDATE_PARKING_SPOT);
            ps.setBoolean(1, parkingSpot.isAvailable());
            ps.setInt(2, parkingSpot.getId());
            updateRowCount = ps.executeUpdate();
            dataBaseConfig.closePreparedStatement(ps);
            
        } catch (Exception ex) {
            LOGGER.error("Error updating parking info", ex);
        } finally {
            dataBaseConfig.closeConnection(con);
            dataBaseConfig.closePreparedStatement(ps);
        }
        
        return (updateRowCount == 1);
    }
}
