package ru.vsu.sample.Menu;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class Combo extends Dish{

    private final List<Dish> list = new LinkedList<>();

    public Combo(int id, String name, BigDecimal price, int TimeInMinutes, Singularity singularity) {
        super(id, name, price, TimeInMinutes, singularity);
        setCategory(Category.COMBO);
    }

    public List<Dish> getList() {
        return list;
    }

    public void putToList(Dish dish) {
        list.add(dish);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("в комбо входят : ");
        for (Dish dish : list){
            str.append(dish.getName());
        }
        return str.toString();
    }
}
