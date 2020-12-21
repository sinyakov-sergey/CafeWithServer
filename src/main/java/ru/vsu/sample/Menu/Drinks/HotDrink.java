package ru.vsu.sample.Menu.Drinks;

import ru.vsu.sample.Menu.Category;
import ru.vsu.sample.Menu.Singularity;

import java.math.BigDecimal;

public class HotDrink extends Drink {

    private final int degree;

    public HotDrink(int id, String name, BigDecimal price, int TimeInMinutes, Singularity singularity, int degree) {
        super(id, name, price, TimeInMinutes, singularity);
        setCategory(Category.HOTDRINK);
        this.degree = degree;
    }

    public int getDegree() {
        return degree;
    }
}
