package com.example.racemania.controller;

import com.example.racemania.exceptions.FailedFileAccessException;
import com.example.racemania.model.*;
import com.example.racemania.model.dao.AccountFSDao;
import com.example.racemania.model.dao.AccountDao;
import com.example.racemania.model.factories.UserFactory;
import com.example.racemania.model.bean.AccountBean;
import com.example.racemania.model.bean.LoginBean;

import javax.security.auth.login.FailedLoginException;
import java.sql.SQLException;

public class LoginController {
    public AccountBean login(LoginBean loginBean) throws FailedLoginException, SQLException, FailedFileAccessException {
        // LoginBean actualloginBean = loginBean;
        AccountBean actualaccountBean = new AccountBean();
        Account account;

        Customer customer;
        TrackOwner trackOwner;

        AccountDao connectionDao = new AccountDao();
        boolean conditionDB;

        try {
            conditionDB = connectionDao.checkConnection();
        } catch (SQLException e) {
            System.out.println("Eseguo login in modalità FileSystem");
            throw new SQLException();
        }

        if (conditionDB) {
            // Sono nel database
            // chiamo il Dao del database
            System.out.println("Sono riuscito a comunicare col database");
            AccountDao accountDao = new AccountDao();
            // Errore capire perché in loginBean.getEmail() e loginBean.getPassword() restituiscono null
            try {
                account = accountDao.checkAccount(loginBean.getEmail(), loginBean.getPassword());   // Funziona

                System.out.println("Ruolo account:" + account.getRole());
                System.out.println("UserID account: " + account.getUserId());
                System.out.println("TracKID account: " + account.getTrackId());
                actualaccountBean.setRole(account.getRole());
                actualaccountBean.setUserId(account.getUserId());
                actualaccountBean.setTrackId(account.getTrackId());
            }
            catch (com.example.racemania.exceptions.FailedLoginException e){
                throw new FailedLoginException("Credenziali errate");
            }
            catch (SQLException e){
                throw new SQLException("Database error");
            }


        } else {
            // sono nel FileSystem
            // chiamo il Dao del file system
            System.out.println("Non sono riuscito a comunicare col database");
            AccountFSDao accountFSDao = new AccountFSDao();
            try {
                account = accountFSDao.checkAccount(loginBean.getEmail(), loginBean.getPassword()); //Funziona
                actualaccountBean.setRole(account.getRole());
                actualaccountBean.setUserId(account.getUserId());
                actualaccountBean.setTrackId(account.getTrackId());
            }
            catch (com.example.racemania.exceptions.FailedLoginException e){
                throw new FailedLoginException("Credenziali errate");

            }
        }

        System.out.println("Valori ottenuti dal Dao: ");
        System.out.println("Role: " + account.getRole());
        System.out.println("UserID: " + account.getUserId());
        System.out.println("TrackId: " + account.getTrackId());
        // da qui devo aver estratto: Ruolo, user_id e track_id (=-1 se l'user è customer)
        // Fino a qui la parte del database funziona
        // per ora prendo solo Ruolo, user_id e track_id, dovrebbe funzionare bene

        // UserFactory myFactory;

        LoggedUser.logout();

        if(account.getRole().equals("TrackOwner")){
            trackOwner = (TrackOwner) UserFactory.createUser(account.getUserId(),loginBean.getEmail(),account.getRole(),account.getTrackId());
            LoggedUser.getInstance().setTrackOwner(trackOwner);
            LoggedUser.getInstance().setRole(account.getRole());
        } else {
            customer = (Customer) UserFactory.createUser(account.getUserId(),loginBean.getEmail(),account.getRole(),account.getTrackId());
            LoggedUser.getInstance().setCustomer(customer);
            LoggedUser.getInstance().setRole(account.getRole());
        }


        return actualaccountBean;
    }
}

