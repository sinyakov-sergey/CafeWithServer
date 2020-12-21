package ru.vsu.sample.Menu.DataBase.DataBase;

import java.math.BigDecimal;
import java.sql.*;

public class ChequeDataBase {

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:D:/my work/OOP/Cafe/DataBase", "sa", "");
    }

    public void putInDataBaseCheck(String name, int number, BigDecimal price, int clientID) throws SQLException{
        try (Connection c = getConnection()) {
            BigDecimal number1 = new BigDecimal(number);
            if (searchRepeat(name, clientID)){
                PreparedStatement statement = c.prepareStatement("UPDATE CHEQUE SET NUMBER = NUMBER + "+ number
                        +" WHERE NAME = '"+ name + "' and CLIENTID='"+ clientID +"'");
                statement.executeUpdate( );
                Statement statement1 = c.createStatement();
                statement1.executeUpdate("UPDATE CHEQUE SET PRICE = PRICE + "+ price.multiply(number1)
                        +" WHERE NAME ='"+ name + "' and CLIENTID='"+ clientID +"'");
            }
            else {
                PreparedStatement statement = c.prepareStatement
                        ("insert into CHEQUE (name, number, price , clientid) values (?, ?, ?, ?)");
                statement.setString(1, name);
                statement.setInt(2, number);
                statement.setBigDecimal(3, price.multiply(number1));
                statement.setInt(4, clientID);
                statement.executeUpdate();
            }
        }
    }

    private boolean searchRepeat(String name, int clientID) throws SQLException{
        try (Connection c = getConnection()) {
            Statement statement = c.createStatement();
            ResultSet resultSet = statement.
                    executeQuery("select * from CHEQUE where NAME='"+ name +"' and CLIENTID='"+ clientID +"'");
            return resultSet.next();
        }
    }

    public void deleteFromCheck(String name, int number, BigDecimal price, int clientID) throws SQLException {
        try (Connection c = getConnection()) {
            if (searchRepeat(name, clientID)){
                Statement statement = c.createStatement();
                ResultSet resultSet = statement.executeQuery ("select * from CHEQUE where NAME='"+ name
                                + "' and CLIENTID='"+ clientID + "'");
                resultSet.next();
                int numberFromBD = resultSet.getInt(2);
                PreparedStatement statement1 = c.prepareStatement("DELETE FROM CHEQUE WHERE NAME='" + name
                        + "' and CLIENTID='"+ clientID + "'");
                statement1.executeUpdate();
                if (numberFromBD > number){
                    BigDecimal number1 = new BigDecimal(numberFromBD - number);
                    putInDataBaseCheck(name, numberFromBD - number, price, clientID);
                }
            }
        }
    }

    public void deleteAllFromCheck() throws SQLException {
        try (Connection c = getConnection()) {
            PreparedStatement statement = c.prepareStatement("delete from CHEQUE");
            statement.executeUpdate();
        }
    }
}