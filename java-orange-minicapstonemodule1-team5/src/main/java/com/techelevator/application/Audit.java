package com.techelevator.application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Audit {

    // format date into string
    private static final SimpleDateFormat DATE = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
    // audit log will be stored in audit.txt
    private static final String FILENAME = "Audit.txt";
    // vending machine balance
    private BigDecimal balance;

    public Audit(BigDecimal initialBalance){
        this.balance = initialBalance;
    }
// use message in purchase
void auditLog(String message, BigDecimal amount){
        try {
            File file = new File(FILENAME);
            FileWriter writer = new FileWriter(file);

            balance = balance.add(amount); // Update balance

           // 2f because we need the number to be formatted with 2 decimal places
            String entry = DATE.format(new Date()) + " " + message + String.format(" $%.2f", amount) + String.format(" $%.2f", balance);
            writer.write(entry + "\n");

            writer.close();
        } catch (IOException e) {
            System.out.println("Error.");
        }
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
