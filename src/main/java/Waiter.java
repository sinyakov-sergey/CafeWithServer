import ru.vsu.sample.Menu.Dish;
import ru.vsu.sample.Menu.DataBase.DataBase.FoodDatabaseLocal;
import ru.vsu.sample.Menu.Singularity;


import java.util.List;

public class Waiter {
    public static List<Dish> SuggestOrder(Client client, FoodDatabaseLocal foodDatabaseLocal){
        List<Dish> AllFood = foodDatabaseLocal.getMenu();
        //отбрасываем варианты по времени и предпочтениям
        for (int i = 0; i < AllFood.size(); i++){
            Dish dish = AllFood.get(i);
            if (dish.getTimeInMinutes() > client.getTimeInMinutes() || dish.getPrice().compareTo(client.getMoney()) > 0){
                AllFood.remove(dish);
                i--;
            }
            else {
                if (!Singularity.NOSINGULARITY.equals(client.getPrecedency())){
                    if (!dish.getSingularity().equals(client.getPrecedency())){
                        AllFood.remove(dish);
                        i--;
                    }
                }
            }
        }
        return AllFood;
    }
}

