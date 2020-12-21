import ru.vsu.sample.Menu.Dish;

import java.math.BigDecimal;
import java.util.List;

public class CalculateCheck {

    public static BigDecimal calculateOrder(Client client) {
        List<Dish> order = client.getOrder();
        List<Integer> numbers = client.getNumbers();
        BigDecimal check = new BigDecimal("0.0");
        for (int i = 0; i < order.size(); i++) {
            check = check.add(order.get(i).getPrice().multiply(new BigDecimal(numbers.get(i))));
        }
        if (client.getMoney().compareTo(check) < 0) return new BigDecimal("-1");
        return check;
    }
}
