package com.example.racemania.model;

import com.example.racemania.model.service.Query;
import com.example.racemania.model.bean.UserBean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    public UserBean getUserFromDB(int userId) throws SQLException {
        UserBean userBean = null;
        // Statement stmt = null;
        // Connection conn = null;

        try {
            // conn = Connector.getInstance().getConnection();
            // stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
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
