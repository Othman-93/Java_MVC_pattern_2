package view;

import java.util.Scanner;

/**
 * MemberView class.
 */
public class MemberView implements MenuInterface {

  @Override
  public void display() {
    System.out.println("");
    System.out.println("1) Update member");
    System.out.println("2) Delete member");
    System.out.println("3) Add owned items");
    System.out.println("4) List owned items");
    System.out.println("5) Borrow an item");
    System.out.println("0) Back");
    System.out.println("");
  }

  private String getUserString(String message) {
    Scanner kb = new Scanner(System.in, "UTF-8");
    System.err.print(message);
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
      case '3':
        return UserAction.AddItem;
      case '4':
        return UserAction.ListItems;
      case '5':
        return UserAction.BorrowItem;
      default:
        return UserAction.None;
    }
  } 
}
