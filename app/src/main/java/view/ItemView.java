package view;

import java.util.Scanner;

/**
 * ItemView class.
 */
public class ItemView implements MenuInterface {
    
  @Override
  public void display() {
    System.out.println("");
    System.out.println("1) Update item");
    System.out.println("2) Delete item");
    System.out.println("0) Back");
    System.out.println("");
  }

  private String getUserString(String message) {
    Scanner kb = new Scanner(System.in, "UTF-8");
    System.out.print(message);
    return kb.nextLine();
  }

  @Override
  public UserAction getAction() {
    String input = getUserString("Make a choice: ");
    UserAction action = toAction(input);
    return action;
  }

  private UserAction toAction(String input) {
    if (input.length() == 0) {
      return UserAction.None;
    }

    switch (input.charAt(0)) {
      case '0':
        return UserAction.Back;
      case '1':
        return UserAction.Update;
      case '2':
        return UserAction.Delete;
      default:
        return UserAction.None;
    }
  }
}
