## Module 1 Capstone - Taste Elevator Vending Machine Application

You've been asked to develop an application for the newest vending machine distributor,
Taste Elevator.  TE Vending Customers purchase food items from the vending machine and are charged accordingly.


### Application Requirements

1. The TE Vending machine dispenses munchies, candy, drinks, and gum.
   - Each item has a Name and a Price.
2. A main menu must display when the software runs, presenting the following options:
    > ```
    > (D) Display Vending Items
    > (P) Purchase
    > (E) Exit
    > ```
3. The inventory is stocked via an input file when the application starts.  **2 different files are provided, make sure your program will work with either one.**
4. Each time the application runs, the inventory is restocked.  The only time the inventory is stocked is when it is started.
5. When the customer selects "(D) Display Vending Items", they're presented
   with a list of all items in the machine with its quantity remaining:
   
    - Each product has a slot identifier and a purchase price.
    - Each slot has enough room for 6 of that product.
    - Every product is initially stocked to the maximum amount.
    - A product that has run out must indicate that it is NO LONGER AVAILABLE.
6. When the customer selects "(P) Purchase", they are guided through the purchasing
   process menu:
   
    >```
    >(M) Feed Money
    >(S) Select Item
    >(F) Finish Transaction
    >
    > Current Money Provided: $2.00
    >```
7. The purchase process flow is as follows:
    1. Selecting "(M) Feed Money" allows the customer to repeatedly feed money into the
       machine in valid, whole dollar amounts—for example, $1, $5, $10, or $20.
        - The "Current Money Provided" indicates how much money the customer
          has fed into the machine.
    2. Selecting "(S) Select Item" allows the customer to select an item to
       purchase.
        - Show the list of items available and allow the customer to enter
          a slot identifier to select an item.
        - If the item (slot identifier) does not exist, the customer is informed and returned
          to the Purchase menu.
        - If a product is NO LONGER AVAILABLE, the customer is informed and returned to the
          Purchase menu.
        - If a valid item is selected, it is dispensed to the customer.
        - Dispensing an item prints the item name, cost, and the money
          remaining. Dispensing also returns a message:
            - All munchy items print "Munchy, Munchy, so Good!"
            - All candy items print  "Sugar, Sugar, so Sweet!"
            - All drink items print  "Drinky, Drinky, Slurp Slurp!"
            - All gum items print    "Chewy, Chewy, Lots O Bubbles!"
        - After the item is dispensed, the machine must update its balance
          accordingly and return the customer to the Purchase menu.
        - In honor of Spring, this machine is running a BOGODO sale, buy one 
          item, get one dollar off any second item.
          - After an item is purchased with the discount, the discount resets. For example, if a third item is purchased a discount IS NOT applied. If a fourth item is purchased the discount DOES apply, and so on.
    3. Selecting "(F) Finish Transaction" allows the customer to complete the
       transaction and receive any remaining change.
        - The customer's money is returned using nickels, dimes, quarters, and dollars (single dollars)
          using the smallest amount of coins possible.
        - The machine's current balance must be updated to $0 remaining.
    4. After completing their purchase, the user is returned to the "Main" menu to
       continue using the Vending machine.
8. All purchases must be audited to prevent theft by creating an Audit file:
   - Each purchase must generate a line in a file called `Audit.txt`.
   - The audit entry must be in the format:
    >```
    >01/01/2022 12:00:00 PM MONEY FED:          $5.00   $5.00
    >01/01/2022 12:00:15 PM MONEY FED:          $5.00  $10.00
    >01/01/2022 12:00:20 PM 7Down           B4 $10.00   $6.75
    >01/01/2022 12:01:25 PM SingleMint Gum  C2  $6.75   $5.40
    >01/01/2022 12:01:35 PM CHANGE GIVEN:       $5.40   $0.00
    >```
9. Create as many of your classes as possible to be "testable" classes. **Limit console**
   **input and output to as few classes as possible**.
10. Optional - Sales Report
    - Provide a "Hidden" menu option on the main menu ("S") that writes to a sales
      report that shows the total sales since the machine was started. The name of the
      file must include the date and time so each sales report is uniquely named.
    - An example of the output format is provided below.
11. Provide unit tests demonstrating that your code works correctly.
___
### Vending Data File
The input file that stocks the products is a comma separated values file (csv). Each line is a separate item in the file and follows the below format:

 Column Name   | Description
----------------|-------------
 Slot Location | The slot location in the machine where the item is set.
 Item Name | The display name of the item.
 Price         | The purchase price for the item.
 Type          | The item type for this row.

For example:

```
A1,Preengles,2.35,Munchy
A1,Chippos,2.55,Munchy
B2,Papsi,3.45,Drink
D1,Moonpie,2.95,Candy
```

An input file has been provided for testing with your repository: `catering.csv`.
There is an alternate input file `catering1.csv` provided for further testing.

---
### Sales Report
The output sales report file is a pipe-delimited file. Each line is a separate item with the number of sales for the applicable item. At the end of the report is a blank line followed by the **TOTAL SALES** dollar amount indicating the gross sales from the vending machine.

Items purchased at a discount through the BOGODO sale are the last number in the row for each item in the following format:

```
Taste Eelvator Sales Report
item|amount sold full price|amount sold with BOGODO discount
```


**Example Output**

>```
>Taste Elevator Sales Report
>Chiclets|3|0
>Popcorn|3|2
>U-Chews|0|0
>Mountain Melter|5|0
>7Down|3|0
>Snykkers|2|0
>Stackers|2|0
>SingleMint Gum|3|0
>Ginger Ayle|4|0
>Wonka Bar|2|0
>Papsi|6|0
>Teaberry|1|0
>Chippos|5|0
>Caramel Bar|6|0
>Moonpie|4|0
>Preengles|2|1
>TOTAL SALES 146.20 
>```
