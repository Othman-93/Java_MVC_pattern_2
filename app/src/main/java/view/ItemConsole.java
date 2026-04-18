package view;

import java.util.ArrayList;
import java.util.Scanner;
import model.Item;

/**
 * ItemConsole class.
 */
public class ItemConsole implements ManageInterface<Item> {

  @Override
  public void display(Item item) {
    System.out.println("");
    System.out.println("Name: " + item.getName());
    System.out.println("Category: " + item.getCategory());
    System.out.println("Short description: " + item.getShortDescription());
    System.out.println("Price: " + item.getPrice());
    System.out.println("Availability: " + (item.isAvailable() ? "Available" : "Not available"));
  }

  @Override
  public String getUserString(String message) {
    Scanner kb = new Scanner(System.in, "UTF-8");
    System.out.print(message);
    return kb.nextLine();
  }

  @Override
  public int getUserInt(String message) {
    /*
     * nextInt cannot detect when the user press return to skip entering a value. We
     * therefore need to ask for a string, and convert to int
     */
    Scanner kb = new Scanner(System.in, "UTF-8");
    int userInput = 0;
    final int skip = -1;
    boolean isValid = false;

    while (!isValid) {
      try {
        System.out.print(message);
        String inputStr = kb.nextLine(); // Read the whole line

        // Check for empty input and throw a custom exception if empty
        if (inputStr.trim().isEmpty()) {
          return skip;
        }

        // Try to convert the input string to an integer
        userInput = Integer.parseInt(inputStr);
        isValid = true;
      } catch (NumberFormatException e) {
        displayError("Invalid input. Please enter an integer.");
      }
    }
    return userInput;
  }

  @Override
  public Item update(Item item) {
    String name = getUserString("Enter a new name, or enter to skip: ");
    String category = getUserString("Enter a new category, or enter to skip: ");
    String shortDescription = getUserString("Enter a new short description, or enter to skip: ");
    int price = getUserInt("Enter a new price, or enter to skip: ");

    return new Item(name, shortDescription, category, price, 0);
  }

  @Override
  public Item create() {
    String name;
    do {
      name = getUserString("Enter a name: ");
    } while (name.length() == 0);

    String category;
    do {
      category = getUserString("Enter a category: ");
    } while (category.length() == 0);

    String shortDescription;
    do {
      shortDescription = getUserString("Enter a short description: ");
    } while (shortDescription.length() == 0);

    int price;
    do {
      price = getUserInt("Enter price per day: ");
    } while (price < 0);

    return new Item(name, shortDescription, category, price, 0);
  }

  @Override
  public int list(ArrayList<Item> items) {
    int counter = 1;
    System.out.println();

    System.out.println("| # | Name | Price | Availability |");
    System.out.println("-----------------------------------");

    if (items.size() == 0) {
      System.out.println("No items found");
    }

    for (Item item : items) {
      String available = item.isAvailable() ? "available" : "not available";

      System.out
          .println(counter + ") " + item.getName() + ", Costs " + item.getPrice() + " per day, Item is " + available);
      counter++;
    }
    System.out.println("0) Back");

    int intput = getUserInt("\nChoose an item: ");
    return intput;
  }

  @Override
  public void displayError(String message) {
    System.out.println("");
    System.out.println(message);
  }
}
