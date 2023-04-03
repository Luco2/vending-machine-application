package com.techelevator.application;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Inventory extends VendingMachine{

    private String INVENTORY_FILE = "catering.csv";
    private static List<Items> items; // list of items in machine

    public Inventory(){
        this.items = new ArrayList<>();
        readItemsFromFile();
    }

    private void readItemsFromFile(){
        try {
            File file = new File(INVENTORY_FILE);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()){
                String line = scanner.nextLine(); // reads a line from the file
                String[] parts = line.split(",");
                String slot = parts[0].trim();
                String name = parts[1].trim();
                BigDecimal price = new BigDecimal(parts[2].trim());
                String itemType = parts[3].trim();
                // create object
                Items item = new Items(slot, name, price, itemType);
                items.add(item);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }  public static List<Items> getItems(){

        return items;
    }
}