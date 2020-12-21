import ru.vsu.sample.Menu.DataBase.DataBase.ChequeDataBase;
import ru.vsu.sample.Menu.Dish;
import ru.vsu.sample.Menu.Singularity;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import static java.util.UUID.randomUUID;


public class Client {
    
    private final int id;

    private final String name;

    private final int TimeInMinutes;

    private final BigDecimal money;

    private final Singularity precedency;//предпочтения в еде

    private final List<Dish> order = new LinkedList<>();

    private final List<Integer> numbers = new LinkedList<>();

    public Client(String name, int TimeInMinutes, BigDecimal money, Singularity precedency) {
        UUID uuid = randomUUID();
        this.id = uuid.hashCode();
        this.name = name;
        this.TimeInMinutes = TimeInMinutes;
        this.money = money;
        this.precedency = precedency;
    }

    public String getName() {
        return name;
    }

    public int getTimeInMinutes() {
        return TimeInMinutes;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public Singularity getPrecedency() {
        return precedency;
    }

    public List<Dish> getOrder() {
        return order;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public boolean putInOrder(Dish dish, int number) {
        if (dish == null)return false;
        if (dish.getTimeInMinutes() > getTimeInMinutes() ||
                dish.getPrice().multiply(new BigDecimal(number)).compareTo(getMoney()) > 0)return false;
        order.add(dish);
        numbers.add(number);
        ChequeDataBase chequeDataBase = new ChequeDataBase();
        try {
            chequeDataBase.putInDataBaseCheck(dish.getName(), number, dish.getPrice(), getId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public void deleteFromOrder(Dish dish, int number){
        for (int i = 0; i < order.size(); i++){
            Dish d = order.get(i);
            if (d.getName().equals(dish.getName())){
                if (numbers.get(i) <= number){
                    order.remove(i);
                    numbers.remove(i);
                }
                else {
                    numbers.set(i, numbers.get(i) - number);
                }
                break;
            }
        }
        ChequeDataBase chequeDataBase = new ChequeDataBase();
        try {
            chequeDataBase.deleteFromCheck(dish.getName(), number, dish.getPrice(), getId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteAllFromOrder(){
        order.clear();
        numbers.clear();
        ChequeDataBase chequeDataBase = new ChequeDataBase();
        try {
            chequeDataBase.deleteAllFromCheck();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }
}
