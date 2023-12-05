package com.phucprod.database_query;

import struct.loginauth;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginAuth {

    public loginauth Query(String name, String pass) throws SQLException, ClassNotFoundException {

        loginauth check;
        try (Connection con = SQLConnection.getConnection()) {
            PreparedStatement pst = con.prepareStatement("select * from users where user_email = ? and user_pw = ?");
            pst.setString(1, name);
            pst.setString(2, pass);

            PreparedStatement pst_uname = con.prepareStatement("select * from users where user_name = ? and user_pw = ?");
            pst_uname.setString(1, name);
            pst_uname.setString(2, pass);

            ResultSet rs = pst.executeQuery();
            ResultSet rs_uname = pst_uname.executeQuery();
            check = new loginauth();
            if (rs.next()) {
                check.CheckAuth = 1;
                check.CheckAdmin = rs.getInt("user_admin");
                check.UserName = rs.getString("user_name");

            }
            if (rs_uname.next()) {
                check.CheckAuth = 1;
                check.CheckAdmin = rs_uname.getInt("user_admin");
                check.UserName = rs_uname.getString("user_name");
            }
        }
        return check;
    }


}
