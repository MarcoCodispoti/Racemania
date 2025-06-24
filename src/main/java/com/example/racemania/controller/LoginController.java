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
        AccountBean actualaccountBean = new AccountBean();
        Account account;

        Customer customer;
        TrackOwner trackOwner;

        AccountDao connectionDao = new AccountDao();
        boolean conditionDB;

        try {
            conditionDB = connectionDao.checkConnection();
        } catch (SQLException e) {
            throw new SQLException(e);
        }

        if (conditionDB) {
            AccountDao accountDao = new AccountDao();
            try {
                account = accountDao.checkAccount(loginBean.getEmail(), loginBean.getPassword());   // Funziona

                actualaccountBean.setRole(account.getRole());
                actualaccountBean.setUserId(account.getUserId());
                actualaccountBean.setTrackId(account.getTrackId());
            }
            catch (com.example.racemania.exceptions.FailedLoginException _){
                throw new FailedLoginException("Credenziali errate");
            }
            catch (SQLException _){
                throw new SQLException("Database error");
            }


        } else {
            System.out.println("Non sono riuscito a comunicare col database");
            AccountFSDao accountFSDao = new AccountFSDao();
            try {
                account = accountFSDao.checkAccount(loginBean.getEmail(), loginBean.getPassword()); //Funziona
                actualaccountBean.setRole(account.getRole());
                actualaccountBean.setUserId(account.getUserId());
                actualaccountBean.setTrackId(account.getTrackId());
            }
            catch (com.example.racemania.exceptions.FailedLoginException _){
                throw new FailedLoginException("Credenziali errate");

            }
        }

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

