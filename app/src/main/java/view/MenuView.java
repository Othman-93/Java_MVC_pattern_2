package view;

import java.util.Scanner;

/**
 * MenuView class.
 */
public class MenuView implements MenuInterface {

  private int currentDay;

  /**
   * Constructor.
   *
   * @param currentDay current day
   */
  public MenuView(int currentDay) {
    this.currentDay = currentDay;
  }

  /**
   * Display the main menu.
   */
  @Override
  public void display() {
    System.out.println("");
    System.out.println("Menu (Day " + currentDay + ")");
    System.out.println("1) Create Member");
    System.out.println("2) List Members");
    System.out.println("3) List Members Verbose");
    System.out.println("4) Advance Day");
    System.out.println("0) Exit");
  }

  private String getUserString(String message) {
    Scanner kb = new Scanner(System.in, "UTF-8");
    System.err.print(message);
    return kb.nextLine();
  }

  @Override
  public UserAction getAction() {
    String input = getUserString("\nMake a choice: ");
    UserAction action = toAction(input);
    return action;
  }

  private UserAction toAction(String input) {
    if (input.length() == 0) {
      return UserAction.None;
    }

    switch (input.charAt(0)) {
      case '0':
        return UserAction.Exit;
      case '1':
        return UserAction.Create;
      case '2':
        return UserAction.Display;
      case '3':
        return UserAction.DisplayVerbose;
      case '4':
        return UserAction.AdvanceDay;
      default:
        return UserAction.None;
    }
  }
}