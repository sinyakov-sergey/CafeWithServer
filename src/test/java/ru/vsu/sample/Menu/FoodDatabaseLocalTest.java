package ru.vsu.sample.Menu;


import org.junit.jupiter.api.Test;
import ru.vsu.sample.Menu.DataBase.DataBase.FoodDatabaseLocal;
import ru.vsu.sample.Menu.DataBase.DataBase.MenuDataBase;
import ru.vsu.sample.Menu.Drinks.Drink;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FoodDatabaseLocalTest {

    @Test
    public void downloadBD() throws SQLException {
        MenuDataBase menuDataBase = new MenuDataBase();
        Drink water = new Drink(0,"Вода", new BigDecimal("30"), 1, Singularity.NOSINGULARITY);
        menuDataBase.saveDrink(water);
        FoodDatabaseLocal foodDatabaseLocal = new FoodDatabaseLocal();
        foodDatabaseLocal.downloadBD();
        List<Dish> list = foodDatabaseLocal.getMenu();
        assertEquals(list.get(0).getName(), "Вода");
    }
}