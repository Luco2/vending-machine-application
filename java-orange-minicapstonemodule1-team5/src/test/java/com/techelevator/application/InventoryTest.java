package com.techelevator.application;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class InventoryTest {


    private Inventory inventory;

    @Before
    public void setUp() {
        inventory = new Inventory();
    }

    @Test
    public void getItems_returns_correct_number_of_items() {
        List<Items> items = inventory.getItems();
        int expectedSize = 16;
        int actualSize = items.size();
        Assert.assertEquals("getItems should return a list with 16 items", expectedSize, actualSize);
    }

    @Test
    public void readItemsFromFile_reads_items_correctly() {
        List<Items> items = inventory.getItems();
        Items item1 = items.get(0);
        Items item2 = items.get(1);
        Items item3 = items.get(2);

        Assert.assertEquals("A1", item1.getSlot());
        Assert.assertEquals("U-Chews", item1.getName());
        Assert.assertEquals(new BigDecimal("1.65"), item1.getPrice());
        Assert.assertEquals("Gum", item1.getItemType());

        Assert.assertEquals("A2", item2.getSlot());
        Assert.assertEquals("Ginger Ayle", item2.getName());
        Assert.assertEquals(new BigDecimal("1.85"), item2.getPrice());
        Assert.assertEquals("Drink", item2.getItemType());

        Assert.assertEquals("A3", item3.getSlot());
        Assert.assertEquals("Snykkers", item3.getName());
        Assert.assertEquals(new BigDecimal("4.25"), item3.getPrice());
        Assert.assertEquals("Candy", item3.getItemType());
    }
}