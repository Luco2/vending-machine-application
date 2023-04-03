package com.techelevator.application;

import com.techelevator.ui.UserInput;
import com.techelevator.ui.UserOutput;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

public class VendingMachine{
    public static final String SELECT_ITEMS = "S";
    public static final String DISPLAY_ITEMS = "D";
    public static final String PURCHASE = "P";

    public static final String EXIT = "E";
    public static final String FEED_MONEY = "M";
    public static final String FINISH_TRANSACTION = "F";


    public void displayItems(List<Items> items) {
        for (Items item : items) {
            String itemName = item.getName();
            BigDecimal itemPrice = item.getPrice();
            String itemSlot = item.getSlot();
            int itemQuantity = item.getQuantity();
            String itemType = item.getItemType();
            System.out.println(itemSlot + " " + itemName + " $" + itemPrice + " (" + itemType + ") " + itemQuantity + " left");
        }
    }


    BigDecimal quarter = new BigDecimal(0.25);
    BigDecimal nickel = new BigDecimal(0.05);
    BigDecimal dollar = new BigDecimal(1.00);
    BigDecimal dime = new BigDecimal(0.10);

    private static final SimpleDateFormat DATE = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
    // audit log will be stored in audit.txt
    private static final String FILENAME = "Audit.txt";

    public void run() {
        Inventory inventory = new Inventory();
        BigDecimal moneyValue = BigDecimal.valueOf(0.0);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            UserOutput.displayHomeScreen();
            String choice = UserInput.getHomeScreenOption();

            if (choice.equals("display")) {
                // display the vending machine slots
                displayItems(Inventory.getItems());
            } else if (choice.equals("purchase")) {
                // make a purchase
                UserOutput.displayPurchaseScreen();
                String purchaseChoice = UserInput.getPurchaseOption();
                if (purchaseChoice.equals("money")) {
                    // add money
                    System.out.println("Current money provided: " + moneyValue);
                    BigDecimal moneyInput = scanner.nextBigDecimal();

                    // user input amount of money
                    // .scale() <= 2 to make sure there is only two decimal points otherwise amount provided is invalid
                    // TODO: bring them to purchase menu instead of back to main menu
                    if (moneyInput.compareTo(BigDecimal.valueOf(0.01)) >= 0 && moneyInput.scale() <= 2) {
                        moneyValue = moneyValue.add(moneyInput);
                        choice.equals("purchase");
                        purchaseChoice.equals("selections");
                        UserOutput.displayPurchaseScreen();
                        UserInput.getPurchaseOption();
                    } else {
                        System.out.println("Invalid number.");
                        UserOutput.displayPurchaseScreen();
                        UserInput.getPurchaseOption();
                        continue;
                    }
                } else if (purchaseChoice.equals("selections")) {
                    // selecting and paying
                    displayItems(Inventory.getItems());

                    boolean addAnotherItem = true;
                    Map<String, Integer> itemsPurchasedBySlot = new HashMap<>();
                    while (addAnotherItem) {
                        System.out.print("Enter the slot of the item you want to purchase: ");
                        String selectedSlot = scanner.nextLine();

                        Items selectedItem = null;
                        for (Items item : Inventory.getItems()) {
                            if (item.getSlot().equals(selectedSlot)) {
                                selectedItem = item;
                                break;
                            }
                        }

                        if (selectedItem == null) {
                            System.out.println("Invalid slot selection.");
                        } else if (selectedItem.getQuantity() == 0) {
                            System.out.println("Item out of stock.");
                        } else if (moneyValue.compareTo(selectedItem.getPrice()) < 0) {
                            System.out.println("Insufficient funds.");
                        } else {
                            BigDecimal itemPrice = selectedItem.getPrice();
                            int itemsPurchased = itemsPurchasedBySlot.getOrDefault(selectedSlot, 0);
                            if (itemsPurchased % 2 == 1) {
                                itemPrice = itemPrice.subtract(BigDecimal.ONE);
                                System.out.println("You received a $1 discount for buying two items!");
                                System.out.println("Price of " + selectedItem.getName() + " with discount applied: $" + itemPrice);
                                itemsPurchasedBySlot.put(selectedSlot, 0); // reset discount
                            } else {
                                itemsPurchasedBySlot.put(selectedSlot, itemsPurchased + 1); // increment number of items purchased
                            }
                            moneyValue = moneyValue.subtract(itemPrice);
                            selectedItem.decrementQuantity();

                            String auditEntry = DATE.format(new Date()) + " ";
                            auditEntry += selectedItem.getName() + " ";
                            auditEntry += selectedItem.getSlot() + " $";
                            auditEntry += itemPrice + " $";
                            auditEntry += moneyValue;

//                            auditEntry += selectedItem.getSlot() + " $";
//                            auditEntry += selectedItem.getPrice() + " ";
//                            auditEntry += "$" + moneyValue;


                            try (FileWriter writer = new FileWriter(FILENAME, true)) {
                                writer.write(auditEntry + "\n");

                                System.out.println("You have purchased " + selectedItem.getName() + ".");
                                System.out.println("Money remaining: $" + moneyValue);

                                System.out.print("Anything else? (Y/N): ");
                                String addAnotherItemChoice = scanner.nextLine().toUpperCase();
                                if (!addAnotherItemChoice.equals("Y")) {
                                    addAnotherItem = false;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

                else if (purchaseChoice.equals("finish")) {
                    // get change back
                    int changeInCents = moneyValue.multiply(new BigDecimal(100)).intValue();

                    int numDollars = changeInCents / 100;
                    changeInCents -= numDollars * 100;

                    int numQuarters = changeInCents / 25;
                    changeInCents -= numQuarters * 25;

                    int numDimes = changeInCents / 10;
                    changeInCents -= numDimes * 10;

                    int numNickels = changeInCents / 5;

                    System.out.println("Your change is " + numDollars + " dollar(s), " + numQuarters + " quarter(s), " + numDimes + " dime(s), and " + numNickels + " nickel(s).");

                    moneyValue = BigDecimal.valueOf(0.0);
                }
            }
        }

    }
}
