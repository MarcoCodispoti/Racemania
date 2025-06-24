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
        Statement stmt;
        Connection conn;

        int userId;
        String role;
        int trackId;

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
                throw new FailedLoginException("Invalid credentials");
            }
        } catch (SQLException e) {
            throw new SQLException("Errore nel database", e);
        }
        return account;
    }

    public boolean checkConnection() throws SQLException {
        Connection conn;

        try{
            conn = Connector.getInstance().getConnection();
            return conn != null;
        } catch (SQLException _) {
            // not handled
        }
        return false;
    }

}