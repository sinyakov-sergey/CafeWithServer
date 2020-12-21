package ru.vsu.sample.Menu.Drinks;

import ru.vsu.sample.Menu.Category;
import ru.vsu.sample.Menu.Dish;
import ru.vsu.sample.Menu.Singularity;

import java.math.BigDecimal;

public class Drink extends Dish {

    public Drink(int id, String name, BigDecimal price, int TimeInMinutes, Singularity singularity) {
        super(id, name, price, TimeInMinutes, singularity);
        setCategory(Category.DRINK);
    }

}
