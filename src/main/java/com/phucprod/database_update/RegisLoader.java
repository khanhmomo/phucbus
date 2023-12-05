package com.phucprod.database_update;

import com.phucprod.database_query.SQLConnection;
import struct.regisform;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class RegisLoader {

    public boolean Regis(regisform data) throws SQLException, ClassNotFoundException {
        try (Connection con = SQLConnection.getConnection()) {
            PreparedStatement pst = con.prepareStatement("insert into users(user_name, user_pw, user_email, user_firstname, user_lastname, user_admin) values(?,?,?,?,?,?) ");
            pst.setString(1, data.user_name);
            pst.setString(2, data.user_pass);
            pst.setString(3, data.user_email);
            pst.setString(4, data.user_firstname);
            pst.setString(5, data.user_lastname);
            pst.setBoolean(6, data.user_admin);
            int rowCount = pst.executeUpdate();
            if (rowCount > 0) return true;
        }
        return false;
    }

    public boolean check_Exist (String email, String name) throws SQLException, ClassNotFoundException {
        try (Connection con = SQLConnection.getConnection()) {
            PreparedStatement pst_emailcheck = con.prepareStatement("select * from users where user_email = ? ");
            pst_emailcheck.setString(1, email);
            PreparedStatement pst_namecheck = con.prepareStatement("select * from users where user_name = ? ");
            pst_namecheck.setString(1, name);

            ResultSet rs_email = pst_emailcheck.executeQuery();
            ResultSet rs_name = pst_namecheck.executeQuery();

            if (rs_email.next() || rs_name.next()) return true;
        }
        return false;
    }

    public boolean check_Pass (String pass, String repass) throws SQLException, ClassNotFoundException {
        try (Connection con = SQLConnection.getConnection()) {
            if (pass.equals(repass)) return true;
        }
        return false;
    }

}
