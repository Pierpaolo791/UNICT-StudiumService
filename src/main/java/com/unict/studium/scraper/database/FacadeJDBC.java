package com.unict.studium.scraper.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

import java.util.Properties;

public class FacadeJDBC {

    private static final String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/studium";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    private Connection conn;
    private Properties properties;

    public FacadeJDBC() {
        openConnection();
    }

    private Properties getProperties() {

        if (properties == null) {
            properties = new Properties();
            properties.setProperty("user", USERNAME);
            properties.setProperty("password", PASSWORD);
            properties.setProperty("serverTimezone", "UTC");
        }
        return properties;
    }

    public Connection openConnection() {
        try {
            return DriverManager.getConnection(DATABASE_URL, getProperties());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean executeUpdate(String update) {
        Connection conn = openConnection();
        if (conn == null) {
            return false;
        }
        Statement stmt = null;
        boolean res = true;
        try {

            stmt = conn.createStatement();
            stmt.executeUpdate(update);
            stmt.close();
            conn.close();

        } catch (SQLIntegrityConstraintViolationException e) {
            //System.out.println(".");
        } catch (SQLException ex) {
            ex.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
            res = false;
        } finally {
            try {
                stmt.close();
                conn.close();
            } catch (SQLException ex) {
                System.out.println("Non si è chiusa la connessione al DB");
            }

        }
        return res;
    }

    public ResultSet executeQuery(String query) {
        Connection conn = openConnection();
        Statement stmt = null;
        ResultSet res = null;
        try {
            stmt = conn.createStatement();
            res = stmt.executeQuery(query);

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                res.close();
                stmt.close();
                conn.close();
            } catch (SQLException ex) {
                System.out.println("Non si è chiusa la connessione al DB");
            }
        }
        return res;
    }

    public Statement openStatement() {
        Connection conn = openConnection();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println("Non si è chiusa la connessione al DB");
            }
        }

        return stmt;
    }
}
