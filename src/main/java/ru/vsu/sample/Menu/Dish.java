package ru.vsu.sample.Menu;



import java.math.BigDecimal;

public abstract class Dish {

    private int id;

    private String name;

    private BigDecimal price;

    private int TimeInMinutes;

    private Category category;//раздел блюда в меню(салат, суп,...)

    private Singularity singularity;//особенность блюда

    public Dish() {
    }

    public Dish(int id, String name, BigDecimal price, int TimeInMinutes, Singularity singularity){
        this.name = name;
        this.price = price;
        this.TimeInMinutes = TimeInMinutes;
        if (singularity == null){
            this.singularity = Singularity.NOSINGULARITY;
        }
        else {
            this.singularity = singularity;
        }
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getTimeInMinutes() {
        return TimeInMinutes;
    }

    public void setTimeInMinutes(int TimeInMinutes) {
        this.TimeInMinutes = TimeInMinutes;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Singularity getSingularity() {
        return singularity;
    }

    public void setSingularity(Singularity singularity) {
        this.singularity = singularity;
    }

    public int getId() {
        return id;
    }

}
