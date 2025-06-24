package com.example.racemania.model.dao;

import com.example.racemania.model.service.Query;
import com.example.racemania.model.bean.UserBean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    public UserBean getUserFromDB(int userId) throws SQLException {
        UserBean userBean = null;

        try {
            ResultSet rs = Query.findUserInfo(userId);
            while(rs.next()) {
                userBean = extractUser(rs,userId);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException();
        }
        return userBean;
    }

    public UserBean extractUser(ResultSet rs,int userId) throws SQLException {
        UserBean userBean = new UserBean();
        userBean.setUserId(userId);
        userBean.setUserName(rs.getString("username"));
        userBean.setEmail(rs.getString("email"));

        return userBean;
    }
}
