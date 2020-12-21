package ru.vsu.sample.Menu.DataBase.DataBase;

import ru.vsu.sample.Menu.*;
import ru.vsu.sample.Menu.Drinks.AlcoholicDrink;
import ru.vsu.sample.Menu.Drinks.Drink;
import ru.vsu.sample.Menu.Drinks.HotDrink;
import ru.vsu.sample.Menu.Drinks.SweetDrink;

import java.sql.*;
import java.util.List;
import java.util.UUID;


public class MenuDataBase {

    private Connection getConnection() throws SQLException{
        return DriverManager.getConnection("jdbc:h2:D:/my work/OOP/Cafe/DataBase", "sa", "");
    }

    /*public void saveDish(Dish dish) throws SQLException {
        try (Connection c = getConnection()) {
            String s = String.valueOf(dish.getCategory());
            PreparedStatement statement;
            if (s.equals("ALCOHOLICDRINK") || s.equals("HOTDRINK")){
                statement = c.prepareStatement("insert into " + s +
                        "(id, name, price, time, singularity, degree) values (?, ?, ?, ?, ?, ?)");
                if (dish.getCategory() == Category.ALCOHOLICDRINK){
                    AlcoholicDrink alcoholicDrink = (AlcoholicDrink) dish;
                    statement.setInt(6, alcoholicDrink.getDegree());
                }
                else {
                    HotDrink hotDrink = (HotDrink) dish;
                    statement.setInt(6, hotDrink.getDegree());
                }
            }
            else {
                statement = c.prepareStatement("insert into " + s +
                        "(id, name, price, time, singularity) values (?, ?, ?, ?, ?)");
                if (s.equals("COMBO")){
                    Combo combo = (Combo) dish;
                    List<Dish> list = combo.getList();
                    for (Dish d : list) {
                        PreparedStatement statement1 = c.prepareStatement
                                ("insert into COMPONENTSOFCOMBO (DISHID, COMBOID) values (?, ?)");
                        statement1.setInt(1, d.getId());
                        statement1.setInt(2, id);
                        statement1.executeUpdate();
                    }
                }
            }
            statement.setInt(1, id);
            statement.setString(2, dish.getName());
            statement.setBigDecimal(3, dish.getPrice());
            statement.setInt(4, dish.getTimeInMinutes());
            statement.setString(5, String.valueOf(dish.getSingularity()));
            statement.executeUpdate();
        }
    }*/

    public AlcoholicDrink saveAlcoholicDrink(Dish dish) throws SQLException {
        int id = createID(dish);
        try (Connection c = getConnection()) {
            String s = String.valueOf(dish.getCategory());
            PreparedStatement statement = c.prepareStatement("insert into " + s +
                    "(id, name, price, time, singularity, degree) values (?, ?, ?, ?, ?, ?)");
            AlcoholicDrink alcoholicDrink = (AlcoholicDrink) dish;
            statement.setInt(6, alcoholicDrink.getDegree());
            statement.setInt(1, id);
            statement.setString(2, dish.getName());
            statement.setBigDecimal(3, dish.getPrice());
            statement.setInt(4, dish.getTimeInMinutes());
            statement.setString(5, String.valueOf(dish.getSingularity()));
            statement.executeUpdate();
            return (AlcoholicDrink) dish;
        }
    }

    public HotDrink saveHotDrink(Dish dish) throws SQLException {
        int id = createID(dish);
        try (Connection c = getConnection()) {
            String s = String.valueOf(dish.getCategory());
            PreparedStatement statement = c.prepareStatement("insert into " + s +
                    "(id, name, price, time, singularity, degree) values (?, ?, ?, ?, ?, ?)");
            statement.setInt(1, id);
            HotDrink hotDrink = (HotDrink) dish;
            statement.setInt(6, hotDrink.getDegree());
            statement.setString(2, dish.getName());
            statement.setBigDecimal(3, dish.getPrice());
            statement.setInt(4, dish.getTimeInMinutes());
            statement.setString(5, String.valueOf(dish.getSingularity()));
            statement.executeUpdate();
            return (HotDrink) dish;
        }
    }

    public Combo saveCombo(Dish dish) throws SQLException {
        int id = createID(dish);
        try (Connection c = getConnection()) {
            String s = String.valueOf(dish.getCategory());
            PreparedStatement statement = c.prepareStatement("insert into " + s +
                    "(id, name, price, time, singularity) values (?, ?, ?, ?, ?)");
            statement.setInt(1, id);
            statement.setString(2, dish.getName());
            statement.setBigDecimal(3, dish.getPrice());
            statement.setInt(4, dish.getTimeInMinutes());
            statement.setString(5, String.valueOf(dish.getSingularity()));
            statement.executeUpdate();
            Combo combo = (Combo) dish;
            List<Dish> list = combo.getList();
            for (Dish d : list) {
                PreparedStatement statement1 = c.prepareStatement
                        ("insert into COMPONENTSOFCOMBO (DISHID, COMBOID) values (?, ?)");
                statement1.setInt(1, d.getId());
                statement1.setInt(2, id);
                statement1.executeUpdate();
            }
            return (Combo) dish;
        }
    }

    public Drink saveDrink(Dish dish) throws SQLException {
        int id = createID(dish);
        try (Connection c = getConnection()) {
            String s = String.valueOf(dish.getCategory());
            PreparedStatement statement = c.prepareStatement("insert into " + s +
                    "(id, name, price, time, singularity) values (?, ?, ?, ?, ?)");
            statement.setInt(1, id);
            statement.setString(2, dish.getName());
            statement.setBigDecimal(3, dish.getPrice());
            statement.setInt(4, dish.getTimeInMinutes());
            statement.setString(5, String.valueOf(dish.getSingularity()));
            statement.executeUpdate();
            return (Drink) dish;
        }
    }
    public Garnish saveGarnish(Dish dish) throws SQLException {
        int id = createID(dish);
        try (Connection c = getConnection()) {
            String s = String.valueOf(dish.getCategory());
            PreparedStatement statement = c.prepareStatement("insert into " + s +
                    "(id, name, price, time, singularity) values (?, ?, ?, ?, ?)");
            statement.setInt(1, id);
            statement.setString(2, dish.getName());
            statement.setBigDecimal(3, dish.getPrice());
            statement.setInt(4, dish.getTimeInMinutes());
            statement.setString(5, String.valueOf(dish.getSingularity()));
            statement.executeUpdate();
            return (Garnish) dish;
        }
    }
    public HotDish saveHotDish(Dish dish) throws SQLException {
        int id = createID(dish);
        try (Connection c = getConnection()) {
            String s = String.valueOf(dish.getCategory());
            PreparedStatement statement = c.prepareStatement("insert into " + s +
                    "(id, name, price, time, singularity) values (?, ?, ?, ?, ?)");
            statement.setInt(1, id);
            statement.setString(2, dish.getName());
            statement.setBigDecimal(3, dish.getPrice());
            statement.setInt(4, dish.getTimeInMinutes());
            statement.setString(5, String.valueOf(dish.getSingularity()));
            statement.executeUpdate();
            return (HotDish) dish;
        }
    }

    public Salad saveSalad(Dish dish) throws SQLException {
        int id = createID(dish);
        try (Connection c = getConnection()) {
            String s = String.valueOf(dish.getCategory());
            PreparedStatement statement = c.prepareStatement("insert into " + s +
                    "(id, name, price, time, singularity) values (?, ?, ?, ?, ?)");
            statement.setInt(1, id);
            statement.setString(2, dish.getName());
            statement.setBigDecimal(3, dish.getPrice());
            statement.setInt(4, dish.getTimeInMinutes());
            statement.setString(5, String.valueOf(dish.getSingularity()));
            statement.executeUpdate();
            return (Salad) dish;
        }
    }

    public Sauce saveSauce(Dish dish) throws SQLException {
        int id = createID(dish);
        try (Connection c = getConnection()) {
            String s = String.valueOf(dish.getCategory());
            PreparedStatement statement = c.prepareStatement("insert into " + s +
                    "(id, name, price, time, singularity) values (?, ?, ?, ?, ?)");
            statement.setInt(1, id);
            statement.setString(2, dish.getName());
            statement.setBigDecimal(3, dish.getPrice());
            statement.setInt(4, dish.getTimeInMinutes());
            statement.setString(5, String.valueOf(dish.getSingularity()));
            statement.executeUpdate();
            return (Sauce) dish;
        }
    }

    public Soup saveSoup(Dish dish) throws SQLException {
        int id = createID(dish);
        try (Connection c = getConnection()) {
            String s = String.valueOf(dish.getCategory());
            PreparedStatement statement = c.prepareStatement("insert into " + s +
                    "(id, name, price, time, singularity) values (?, ?, ?, ?, ?)");
            statement.setInt(1, id);
            statement.setString(2, dish.getName());
            statement.setBigDecimal(3, dish.getPrice());
            statement.setInt(4, dish.getTimeInMinutes());
            statement.setString(5, String.valueOf(dish.getSingularity()));
            statement.executeUpdate();
            return (Soup) dish;
        }
    }

    public SweetDrink saveSweetDrink(Dish dish) throws SQLException {
        int id = createID(dish);
        try (Connection c = getConnection()) {
            String s = String.valueOf(dish.getCategory());
            PreparedStatement statement = c.prepareStatement("insert into " + s +
                    "(id, name, price, time, singularity) values (?, ?, ?, ?, ?)");
            statement.setInt(1, id);
            statement.setString(2, dish.getName());
            statement.setBigDecimal(3, dish.getPrice());
            statement.setInt(4, dish.getTimeInMinutes());
            statement.setString(5, String.valueOf(dish.getSingularity()));
            statement.executeUpdate();
            return (SweetDrink) dish;
        }
    }

    private int createID(Dish dish){
        int id;
        if (dish.getId() == -1){
            UUID uuid = UUID.randomUUID();
            id = Math.abs(uuid.hashCode());
        }
        else id = dish.getId();
        return id;
    }


    public void deleteDish(Dish dish){
        String s = String.valueOf(dish.getCategory());
        try (Connection c = getConnection()) {
            PreparedStatement statement = c.prepareStatement("DELETE FROM "+ s +" WHERE ID="+ dish.getId() +";");
            statement.executeUpdate();
            if (s.equals("COMBO")){
                PreparedStatement statement1 = c.prepareStatement("DELETE FROM COMPONENTSOFCOMBO WHERE COMBOID="
                        + dish.getId() +";");
                statement1.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
