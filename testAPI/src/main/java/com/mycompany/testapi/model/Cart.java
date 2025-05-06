package com.mycompany.testapi.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private int id;
    private int customerId;
    private List<Cartitem> items = new ArrayList<>();

    public Cart() {
    }

    public Cart(int id, int customerId) {
        this.id = id;
        this.customerId = customerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public List<Cartitem> getItems() {
        return items;
    }

    public void setItems(List<Cartitem> items) {
        this.items = items;
    }
}
