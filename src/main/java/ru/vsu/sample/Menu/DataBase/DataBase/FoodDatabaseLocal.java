package ru.vsu.sample.Menu.DataBase.DataBase;

import ru.vsu.sample.Menu.*;
import ru.vsu.sample.Menu.Drinks.AlcoholicDrink;
import ru.vsu.sample.Menu.Drinks.Drink;
import ru.vsu.sample.Menu.Drinks.HotDrink;
import ru.vsu.sample.Menu.Drinks.SweetDrink;


import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class FoodDatabaseLocal {

    private final List<Dish> Menu = new LinkedList<>();

    public FoodDatabaseLocal() {
        downloadBD();
    }

    public List<Dish> downloadBD(){
        Menu.clear();
        String[] allCategories = { "DRINK", "GARNISH", "HOTDISH", "SALAD", "SAUCE", "SOUP",
                "HOTDRINK", "ALCOHOLICDRINK", "SWEETDRINK", "COMBO"};
        MenuDataBase menuDataBase = new MenuDataBase();
        for (String s : allCategories) {
            try {
                Connection c = DriverManager.getConnection("jdbc:h2:D:/my work/OOP/Cafe/DataBase", "sa", "");
                Statement statement = c.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from " + s);
                while (resultSet.next()){
                    switch (s){
                        case ("DRINK"):
                            Drink drink = new Drink(resultSet.getInt(1),
                                    resultSet.getString(2),
                                    resultSet.getBigDecimal(3),
                                    resultSet.getInt(4),
                                    Singularity.valueOf(resultSet.getString(5)));
                            Menu.add(drink);
                            break;
                        case ("GARNISH"):
                            Garnish garnish = new Garnish(resultSet.getInt(1),
                                    resultSet.getString(2),
                                    resultSet.getBigDecimal(3),
                                    resultSet.getInt(4),
                                    Singularity.valueOf(resultSet.getString(5)));
                            Menu.add(garnish);
                            break;
                        case ("HOTDISH"):
                            HotDish hotDish = new HotDish(resultSet.getInt(1),
                                    resultSet.getString(2),
                                    resultSet.getBigDecimal(3),
                                    resultSet.getInt(4),
                                    Singularity.valueOf(resultSet.getString(5)));
                            Menu.add(hotDish);
                            break;
                        case ("SALAD"):
                            Salad salad = new Salad(resultSet.getInt(1),
                                    resultSet.getString(2),
                                    resultSet.getBigDecimal(3),
                                    resultSet.getInt(4),
                                    Singularity.valueOf(resultSet.getString(5)));
                            Menu.add(salad);
                            break;
                        case ("SAUCE"):
                            Sauce sauce = new Sauce(resultSet.getInt(1),
                                    resultSet.getString(2),
                                    resultSet.getBigDecimal(3),
                                    resultSet.getInt(4),
                                    Singularity.valueOf(resultSet.getString(5)));
                            Menu.add(sauce);
                            break;
                        case ("SOUP"):
                            Soup soup = new Soup(resultSet.getInt(1),
                                    resultSet.getString(2),
                                    resultSet.getBigDecimal(3),
                                    resultSet.getInt(4),
                                    Singularity.valueOf(resultSet.getString(5)));
                            Menu.add(soup);
                            break;
                        case ("HOTDRINK"):
                            HotDrink hotDrink = new HotDrink(resultSet.getInt(1),
                                    resultSet.getString(2),
                                    resultSet.getBigDecimal(3),
                                    resultSet.getInt(4),
                                    Singularity.valueOf(resultSet.getString(5)),
                                    resultSet.getInt(6));
                            Menu.add(hotDrink);
                            break;
                        case ("ALCOHOLICDRINK"):
                            AlcoholicDrink alcoholicDrink = new AlcoholicDrink(
                                    resultSet.getInt(1),
                                    resultSet.getString(2),
                                    resultSet.getBigDecimal(3),
                                    resultSet.getInt(4),
                                    Singularity.valueOf(resultSet.getString(5)),
                                    resultSet.getInt(6));
                            Menu.add(alcoholicDrink);
                            break;
                        case ("SWEETDRINK"):
                            SweetDrink sweetDrink = new SweetDrink(resultSet.getInt(1),
                                    resultSet.getString(2),
                                    resultSet.getBigDecimal(3),
                                    resultSet.getInt(4),
                                    Singularity.valueOf(resultSet.getString(5)));
                            Menu.add(sweetDrink);
                            break;
                        case ("COMBO"):
                            Combo combo = new Combo(resultSet.getInt(1),
                                    resultSet.getString(2),
                                    resultSet.getBigDecimal(3),
                                    resultSet.getInt(4),
                                    Singularity.valueOf(resultSet.getString(5)));
                            Connection c1 = DriverManager.getConnection("jdbc:h2:D:/my work/OOP/Cafe/DataBase", "sa", "");
                            Statement statement1 = c1.createStatement();
                            ResultSet components1 = statement1.executeQuery
                                    ("select * from COMPONENTSOFCOMBO where COMBOID=" + combo.getId());
                            while (components1.next()){
                                int id = components1.getInt(1);
                                for (Dish dish : Menu){
                                    if (dish.getId() == id){
                                        combo.putToList(dish);
                                    }
                                }
                            }
                            Menu.add(combo);
                            break;
                    }
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return getMenu();
    }

    public List<Dish> getMenu() {
        return Menu;
    }


    public Dish getToIndex(int index){
        return Menu.get(index);
    }
}
