package com.techelevator.application;

import java.math.BigDecimal;

public class Items {

    private String name;
    private BigDecimal price;
    private String slot;
    private int quantity;
    // TODO: add bogodo (discount) item quantity
    private String itemType;

    public Items(String slot, String name, BigDecimal price, String itemType){
        this.slot = slot;
        this.name = name;
        this.price = price;
        this.itemType = itemType;
        this.quantity = 6;
    }


    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getSlot() {
        return slot;
    }

    public int getQuantity() {
        return quantity;
    }


    public String getItemType() {
        return itemType;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean decrementQuantity() {
        if (quantity > 0) {
            quantity--;
            return true;
        } else {
            return false;
        }
    }
}
