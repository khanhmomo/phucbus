package com.phucprod.database_update;

import com.phucprod.database_query.SQLConnection;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserSetting {
    public void updateFullName (String fname, String lname, HttpSession session) throws SQLException, ClassNotFoundException {
        try(Connection con = SQLConnection.getConnection()) {
            String query = "update users set user_firstname=?, user_lastname=? where user_name=?;";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1,fname);
            pst.setString(2,lname);
            pst.setString(3, (String) session.getAttribute("name"));
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateEmail (String email, HttpSession session) {
        try(Connection con = SQLConnection.getConnection()) {
            String query = "update users set user_email=? where user_name=?;";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1,email);
            pst.setString(2, (String) session.getAttribute("name"));
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updatePassword(String pass, HttpSession session) {
        try(Connection con = SQLConnection.getConnection()) {
            String query = "update users set user_pw=? where user_name=?;";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1,pass);
            pst.setString(2, (String) session.getAttribute("name"));
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
