package ru.vsu.sample.Menu;

import java.math.BigDecimal;

public class HotDish extends Dish {

    public HotDish(int id, String name, BigDecimal price, int TimeInMinutes, Singularity singularity) {
        super(id, name, price, TimeInMinutes, singularity);
        setCategory(Category.HOTDISH);
    }
}
