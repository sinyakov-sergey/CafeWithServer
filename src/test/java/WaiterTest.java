import org.junit.jupiter.api.Test;
import ru.vsu.sample.Menu.*;
import ru.vsu.sample.Menu.DataBase.DataBase.FoodDatabaseLocal;
import ru.vsu.sample.Menu.DataBase.DataBase.MenuDataBase;
import ru.vsu.sample.Menu.Drinks.HotDrink;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WaiterTest {
    @Test
    public void creatMyMenu() throws SQLException {
        Soup borsh = new Soup(12,"Борщ", new BigDecimal("350.0"), 30, Singularity.SOUR);
        HotDrink tea = new HotDrink(13,"Чай", new BigDecimal("40"), 5, Singularity.SWEET, 100);
        Garnish grechka = new Garnish(14,"Гречка", new BigDecimal("100.0"), 10, Singularity.NOSINGULARITY);
        HotDish shashlik = new HotDish(15,"Шашлык", new BigDecimal("500.0"), 25, Singularity.NOSINGULARITY);
        Salad olive = new Salad(16,"Оливье", new BigDecimal("300.0"), 15, Singularity.NOSINGULARITY);
        MenuDataBase menuDataBase = new MenuDataBase();
        menuDataBase.saveSoup(borsh);
        menuDataBase.saveHotDrink(tea);
        menuDataBase.saveGarnish(grechka);
        menuDataBase.saveHotDish(shashlik);
        menuDataBase.saveSalad(olive);
    }
    @Test
    public void TestTime() {
        Client client = new Client("Иван", 12, new BigDecimal("10000.0"), Singularity.NOSINGULARITY);
        FoodDatabaseLocal menu = new FoodDatabaseLocal();
        menu.downloadBD();
        List<Dish> list = Waiter.SuggestOrder(client, menu);
        assertEquals(list.get(1).getName(), "Чай");
        assertEquals(list.get(0).getName(), "Гречка");
    }

    @Test
    public void TestPrice() {
        Client client = new Client("Иван",60, new BigDecimal("310.0"), Singularity.NOSINGULARITY);
        FoodDatabaseLocal menu = new FoodDatabaseLocal();
        menu.downloadBD();
        List<Dish> list = Waiter.SuggestOrder(client, menu);
        assertEquals(list.get(0).getName(), "Гречка");
        assertEquals(list.get(1).getName(), "Оливье");
        assertEquals(list.get(2).getName(), "Чай");
    }

    @Test
    public void TestSingularity() {
        Client client = new Client("Иван",60, new BigDecimal("10000.0"), Singularity.SWEET);
        FoodDatabaseLocal menu = new FoodDatabaseLocal();
        menu.downloadBD();
        List<Dish> list = Waiter.SuggestOrder(client, menu);
        assertEquals(list.get(0).getName(), "Чай");
    }

    @Test
    public void TestCalculateCheck() {
        Client client = new Client("Иван",60, new BigDecimal("10000.0"), Singularity.NOSINGULARITY);
        FoodDatabaseLocal menu = new FoodDatabaseLocal();
        menu.downloadBD();
        client.putInOrder(menu.getToIndex(0), 1);
        client.putInOrder(menu.getToIndex(1), 1);
        client.putInOrder(menu.getToIndex(2), 1);
        BigDecimal check = CalculateCheck.calculateOrder(client);
        assertEquals(check, new BigDecimal("900.0"));
    }
}