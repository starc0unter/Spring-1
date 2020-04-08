package com.chentsov.persist;


@SuppressWarnings("WeakerAccess")
public class Product {

    private long id;
    private String title;
    private long price;

    public Product() {
    }

    public Product(long id, String title, long cost) {
        this.id = id;
        this.title = title;
        this.price = cost;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
