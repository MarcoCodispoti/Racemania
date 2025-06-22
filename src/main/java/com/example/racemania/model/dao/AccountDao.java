package com.example.racemania.model.dao;

import com.example.racemania.exceptions.FailedLoginException;
import com.example.racemania.model.Account;
import com.example.racemania.model.service.Connector;
import com.example.racemania.model.service.Query;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountDao {
    public Account checkAccount(String email, String password) throws FailedLoginException, SQLException{
        Account account = new Account();
        Statement stmt; // = null;
        Connection conn; // = null;

        int userId; // = -1;
        String role; //  = null;
        int trackId; // = -1;

        try{
            conn = Connector.getInstance().getConnection();
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = Query.checkCredentials(stmt,email,password);

            if(rs.next()){ // se il result set non è vuoto
                userId = rs.getInt("user_id");      // mi salvo i valori che mi interessano
                account.setUserId(userId);
                role = rs.getString("user_role");
                account.setRole(role);
                trackId = rs.getInt("track_id");

                if(trackId == 0){
                    trackId = -1;
                }
                account.setTrackId(trackId);

            } else {
                System.out.println("Credenziali errate");
                throw new FailedLoginException("Invalid credentials");
            }
        } catch (SQLException e) {
            System.out.println("Errore nel database");
            throw new SQLException("Errore nel database");
        }
        return account;
    }

    public boolean checkConnection() throws SQLException {
        // Statement stmt = null;
        Connection conn; // = null;

        try{
            conn = Connector.getInstance().getConnection();
            if (conn != null) {
                System.out.println("Connection established");
                return true;
            }
            System.out.println("Connection not established");
            return false;
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        }
        return false;
    }

}