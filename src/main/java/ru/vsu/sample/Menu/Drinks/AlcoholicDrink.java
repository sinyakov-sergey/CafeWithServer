package ru.vsu.sample.Menu.Drinks;

import ru.vsu.sample.Menu.Category;
import ru.vsu.sample.Menu.Singularity;

import java.math.BigDecimal;

public class AlcoholicDrink extends Drink {

    private final int degree;

    public AlcoholicDrink(int id, String name, BigDecimal price, int TimeInMinutes, Singularity singularity, int degree) {
        super(id, name, price, TimeInMinutes, singularity);
        this.degree = degree;
        setCategory(Category.ALCOHOLICDRINK);
    }

    public int getDegree() {
        return degree;
    }
}
