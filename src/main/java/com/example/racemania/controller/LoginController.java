package com.example.racemania.controller;

import com.example.racemania.exceptions.FailedFileAccessException;
import com.example.racemania.model.*;
import com.example.racemania.model.dao.AccountFSDao;
import com.example.racemania.model.dao.AccountDao;
import com.example.racemania.model.factories.UserFactory;
import com.example.racemania.model.bean.AccountBean;
import com.example.racemania.model.bean.LoginBean;
import com.example.racemania.view1.FxmlLoader;
import com.example.racemania.view2.FxmlLoader2;

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
            AccountFSDao accountFSDao = new AccountFSDao();
            try {
                account = accountFSDao.checkAccount(loginBean.getEmail(), loginBean.getPassword());
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

    public AccountBean authenticate(String email, String password) throws FailedLoginException, SQLException {
        LoginBean loginBean = new LoginBean();
        loginBean.setEmail(email);
        loginBean.setPassword(password);

        return login(loginBean); // o altra logica già esistente
    }

    public void redirectToHomePage(int uiVersion){
        if (LoggedUser.getInstance().getCustomer() != null) {
            pageSelector(uiVersion, "Home");
        } else if(LoggedUser.getInstance().getTrackOwner() != null) {
            pageSelector(uiVersion, "OwnerHome");
        }
    }

    public void pageSelector(int uiVersion,String page){
        if(uiVersion == 1){
            FxmlLoader.setPage(page + "Page");
        } else if (uiVersion == 2){
            FxmlLoader2.setPage(page + "Page2");
        }
    }

}

