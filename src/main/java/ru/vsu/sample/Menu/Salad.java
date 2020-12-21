package ru.vsu.sample.Menu;

import java.math.BigDecimal;

public class Salad extends Dish {

    public Salad(int id, String name, BigDecimal price, int TimeInMinutes, Singularity singularity) {
        super(id, name, price, TimeInMinutes, singularity);
        setCategory(Category.SALAD);
    }

}
