package com.techelevator.application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class SalesReport {
    // format date into string
    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyyMMdd_HHmmss");
    private static final String FILE_NAME = "SalesReport.txt";

    // use map to lookup items
    public static void getSalesReport(Map<String, Items> inventory){
        Audit audit = new Audit(BigDecimal.ZERO); // initialize audit value to zero
        BigDecimal totalSales = audit.getBalance(); // get total sales

        try{
            File file = new File(FORMAT.format(new Date()) + "_" + FILE_NAME);
            FileWriter writer = new FileWriter(file);

            writer.write("Sales Report"); //title
            // loop through inventory map
            for(Map.Entry<String, Items> entry : inventory.entrySet()){
                String itemName = entry.getKey(); // get item name
                Items item = entry.getValue(); // get item value
                // TODO: add line to get amount sold when items class is done (starting quanity - quanity) ^^
               // int discount =  // get amount sold through BOGODO sale
                // TODO: add line for bogodo count when items class is finished
                // TODO: add writer.write to write a string with item name, amount sold at full price minus items sold by discount, and the amount of items sold through discount (use %s for string format, %d for integer format)
            }

            writer.write(String.format("TOTAL SALES %.2f\n", totalSales)); // write total sales

            writer.close();
        } catch (IOException e) {
            System.out.println("Error.");
        }
    }
}
