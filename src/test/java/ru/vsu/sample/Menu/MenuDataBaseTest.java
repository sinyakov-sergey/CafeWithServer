package ru.vsu.sample.Menu;

import org.junit.Test;
import ru.vsu.sample.Menu.DataBase.DataBase.FoodDatabaseLocal;
import ru.vsu.sample.Menu.DataBase.DataBase.MenuDataBase;
import ru.vsu.sample.Menu.Drinks.AlcoholicDrink;
import ru.vsu.sample.Menu.Drinks.Drink;
import ru.vsu.sample.Menu.Drinks.HotDrink;
import ru.vsu.sample.Menu.Drinks.SweetDrink;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class MenuDataBaseTest {
    @Test
    public void TestSaveInTableSoup() throws SQLException {
        Soup borsh = new Soup(13,"Борщ", new BigDecimal("350.0"), 30, Singularity.SOUR);
        MenuDataBase menuDataBase = new MenuDataBase();
        menuDataBase.saveSoup(borsh);
        FoodDatabaseLocal foodDatabaseLocal = new FoodDatabaseLocal();
        List<Dish> list = foodDatabaseLocal.downloadBD();
        for (Dish dish : list){
            if (dish.getCategory() == Category.SOUP){
                assertEquals(dish.getName(), "Борщ");
            }
        }
    }
    @Test
    public void TestSaveInTableSweetDrink() throws SQLException {
        SweetDrink cola = new SweetDrink(-1,"Коко-кола", new BigDecimal("70"), 1, Singularity.SWEET);
        MenuDataBase menuDataBase = new MenuDataBase();
        menuDataBase.saveSweetDrink(cola);
        FoodDatabaseLocal foodDatabaseLocal = new FoodDatabaseLocal();
        List<Dish> list = foodDatabaseLocal.downloadBD();
        for (Dish dish : list){
            if (dish.getCategory() == Category.SWEETDRINK){
                assertEquals(dish.getName(), "Коко-кола");
            }
        }
    }
    @Test
    public void TestSaveInTableSauce() throws SQLException {
        Sauce cheese = new Sauce(-1,"Сырный", new BigDecimal("30"), 1, Singularity.NOSINGULARITY);
        MenuDataBase menuDataBase = new MenuDataBase();
        menuDataBase.saveSauce(cheese);
        FoodDatabaseLocal foodDatabaseLocal = new FoodDatabaseLocal();
        List<Dish> list = foodDatabaseLocal.downloadBD();
        for (Dish dish : list){
            if (dish.getCategory() == Category.SAUCE){
                assertEquals(dish.getName(), "Сырный");
            }
        }
    }
    @Test
    public void TestSaveInTableSalad() throws SQLException {
        Salad greece = new Salad(-1,"Греческий", new BigDecimal("200"), 12, Singularity.NOSINGULARITY);
        MenuDataBase menuDataBase = new MenuDataBase();
        menuDataBase.saveSalad(greece);
        FoodDatabaseLocal foodDatabaseLocal = new FoodDatabaseLocal();
        List<Dish> list = foodDatabaseLocal.downloadBD();
        for (Dish dish : list){
            if (dish.getCategory() == Category.SALAD){
                assertEquals(dish.getName(), "Греческий");
            }
        }
    }
    @Test
    public void TestSaveInTableHotDrink() throws SQLException {
        HotDrink tea = new HotDrink(9,"Чай", new BigDecimal("20"), 3, Singularity.NOSINGULARITY, 90);
        MenuDataBase menuDataBase = new MenuDataBase();
        menuDataBase.saveHotDrink(tea);
        FoodDatabaseLocal foodDatabaseLocal = new FoodDatabaseLocal();
        List<Dish> list = foodDatabaseLocal.downloadBD();
        for (Dish dish : list){
            if (dish.getCategory() == Category.HOTDRINK){
                assertEquals(dish.getName(), "Чай");
            }
        }
    }
    @Test
    public void TestSaveInTableHotDish() throws SQLException {
        HotDish steak = new HotDish(8,"Стейк говяжий", new BigDecimal("350"), 18, Singularity.SPICY);
        MenuDataBase menuDataBase = new MenuDataBase();
        menuDataBase.saveHotDish(steak);
        FoodDatabaseLocal foodDatabaseLocal = new FoodDatabaseLocal();
        List<Dish> list = foodDatabaseLocal.downloadBD();
        for (Dish dish : list){
            if (dish.getCategory() == Category.HOTDISH){
                assertEquals(dish.getName(), "Стейк говяжий");
            }
        }
    }
    @Test
    public void TestSaveInTableGarnish() throws SQLException {
        Garnish potato = new Garnish(7,"Картошка жаренная", new BigDecimal("100"), 10, Singularity.NOSINGULARITY);
        MenuDataBase menuDataBase = new MenuDataBase();
        menuDataBase.saveGarnish(potato);
        FoodDatabaseLocal foodDatabaseLocal = new FoodDatabaseLocal();
        List<Dish> list = foodDatabaseLocal.downloadBD();
        for (Dish dish : list){
            if (dish.getCategory() == Category.GARNISH){
                assertEquals(dish.getName(), "Картошка жаренная");
            }
        }
    }
    @Test
    public void TestSaveInTableDrink() throws SQLException {
        Drink water = new Drink(2,"Вода", new BigDecimal("30"), 1, Singularity.NOSINGULARITY);
        MenuDataBase menuDataBase = new MenuDataBase();
        menuDataBase.saveDrink(water);
        FoodDatabaseLocal foodDatabaseLocal = new FoodDatabaseLocal();
        List<Dish> list = foodDatabaseLocal.downloadBD();
        for (Dish dish : list){
            if (dish.getCategory() == Category.DRINK){
                assertEquals(dish.getName(), "Вода");
            }
        }
    }
    @Test
    public void TestSaveInTableAlcoholicDrink() throws SQLException {
        AlcoholicDrink beer = new AlcoholicDrink(4,"Пиво", new BigDecimal("100"), 3, Singularity.NOSINGULARITY, 7);
        MenuDataBase menuDataBase = new MenuDataBase();
        menuDataBase.saveAlcoholicDrink(beer);
        FoodDatabaseLocal foodDatabaseLocal = new FoodDatabaseLocal();
        List<Dish> list = foodDatabaseLocal.downloadBD();
        for (Dish dish : list){
            if (dish.getCategory() == Category.ALCOHOLICDRINK){
                assertEquals(dish.getName(), "Пиво");
            }
        }
    }
    /*@Test
    public void TestDeleteAll(){
        FoodDatabaseLocal foodDatabaseLocal = new FoodDatabaseLocal();
        List<Dish> list = foodDatabaseLocal.downloadBD();
        MenuDataBase menuDataBase = new MenuDataBase();
        for (Dish d: list){
            menuDataBase.deleteDish(d);
        }
        list = foodDatabaseLocal.downloadBD();
        assertEquals(0, list.size());
    }*/

}