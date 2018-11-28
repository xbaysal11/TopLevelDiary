package jav;

import java.sql.*;

public class DataBaseHandler extends Configs {


    Connection dbConnection;

    public Connection getDbConnection() {

        try {
            String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName
                    + "?autoReconnect=true&useSSL=false ";
//            Class.forName("com.mysql.cj.jdbc.Driver");

            dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return dbConnection;

    }

    public void signUpUser(User user) {
        String insert = "INSERT INTO " + Constants.USERS_TABLE + "(" +
                Constants.USER_FIRSTNAME + "," + Constants.USER_LASTNAME + "," +
                Constants.USER_LOGIN + "," + Constants.USER_PASSWORD + ")" +
                "VALUE(?,?,?,?)";
        try {
            if (!user.getFirstName().equals("") && !user.getLastName().equals("")
                    && !user.getLogin().equals("") && !user.getPassword().equals("")) {

                PreparedStatement prSt = getDbConnection().prepareStatement(insert);
                prSt.setString(1, user.getFirstName());
                prSt.setString(2, user.getLastName());
                prSt.setString(3, user.getLogin());
                prSt.setString(4, user.getPassword());

                prSt.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public ResultSet getUser(User user) {
        ResultSet resultSet = null;

        String select = "SELECT * FROM " + Constants.USERS_TABLE + " WHERE " + Constants.USER_LOGIN + "=? AND "
                + Constants.USER_PASSWORD + "=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, user.getLogin());
            prSt.setString(2, user.getPassword());


            resultSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }


    public ResultSet getLogin(User user) {
        ResultSet resultSet = null;

        String select = "SELECT * FROM " + Constants.USERS_TABLE + " WHERE " + Constants.USER_LOGIN + "=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, user.getLogin());

            resultSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public void insertTextFile(Text text) {
        String insert = "INSERT INTO " + Constants.TEXTS_TABLE + "(" +
                Constants.USER_LOGIN + ","  + Constants.TEXT_FILES + "," +
                Constants.TEXT_DATE + ")" +
                "VALUE(?,?,?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, text.getLogin());
            prSt.setString(2, text.getTextFiles());
            prSt.setString(3, text.getDate());

            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public ResultSet searchFileFromDataBase(Text text) throws SQLException {
        ResultSet resultSet;

        String verify = "SELECT * FROM " + Constants.TEXTS_TABLE+ " WHERE " + Constants.USER_LOGIN + "=? AND "
                + Constants.TEXT_DATE + "=?";


        PreparedStatement prSt = getDbConnection().prepareStatement(verify);

        prSt.setString(1, text.getLogin());
        prSt.setString(2, text.getDate());

        resultSet = prSt.executeQuery();

        return resultSet;
    }




}