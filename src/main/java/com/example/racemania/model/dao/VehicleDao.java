package com.example.racemania.model.dao;

import com.example.racemania.exceptions.FailedInsertException;
import com.example.racemania.model.service.Connector;
import com.example.racemania.model.service.Query;
import com.example.racemania.model.bean.VehicleBean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VehicleDao {

    public VehicleDao() {}

    public void insertVehicle(VehicleBean vehicleBean) throws FailedInsertException {
        Statement stmt = null;
        Connection conn;

        try {
            conn = Connector.getInstance().getConnection();
            stmt = conn.createStatement();
            Query.insertVehicle(stmt, vehicleBean);
        } catch (Exception e) {
            throw new FailedInsertException("An error during lesson insertion occurred.");
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                //not handled
            }
        }
    }


    private VehicleBean extractVehicle(ResultSet rs) throws SQLException {
        VehicleBean vehicleBean = new VehicleBean();
        vehicleBean.setPlate(rs.getString("plate"));
        vehicleBean.setOwnerId(rs.getInt("owner_id"));
        vehicleBean.setBrand(rs.getString("brand"));
        vehicleBean.setModel(rs.getString("model"));
        vehicleBean.setImmatriculationYear(rs.getInt("immatriculation_year"));
        vehicleBean.setPower(rs.getInt("power"));
        vehicleBean.setLastcheckYear(rs.getInt("lastcheck_year"));

        return vehicleBean;
    }


    public VehicleBean getVehicleFromDB(String plate) throws SQLException {
        VehicleBean vehicleBean = null;

        try {
            ResultSet rs = Query.findVehicleInfo(plate);
            while(rs.next()) {
                vehicleBean = extractVehicle(rs);
            }
        }
        catch (SQLException e) {
            // e.printStackTrace();
            throw new SQLException();
        }
        return vehicleBean;
    }

}
