package controller;

import java.util.ArrayList;
import model.Item;
import model.LendingSystem;
import model.Member;
import view.ItemConsole;
import view.ItemView;
import view.ManageInterface;
import view.MenuInterface;
import view.UserAction;

/**
 * ItemController class.
 */
public class ItemController {

  private MenuInterface itemView;
  private ManageInterface<Item> console;

  public ItemController() {
    this.itemView = new ItemView();
    this.console = new ItemConsole();
  }

  /**
   * Display items.
   *
   * @param selectedMember  the selected member
   * @param ls              the ls
   * @param console         the console
   */

  private void display(Item selectedItem, Member member, LendingSystem ls) {
    UserAction action;

    do {
      console.display(selectedItem);
      itemView.display();
      action = itemView.getAction();

      switch (action) {
        case Update:
          update(ls, selectedItem);
          break;
        case Delete:
          deleteItem(ls, selectedItem);
          return;
        case BorrowItem:
          borrowItem(ls, member);
          break;
        case Back:
          return;
        default:
          break;
      } 
    } while (action != UserAction.Back || action != UserAction.Delete);
  }

  private void update(LendingSystem ls, Item item) {
    Item copyItem = console.update(item);
    ls.updateItem(copyItem, item);
  }

  public Item createItem() {
    return console.create();
  }

  /**
   * Borrow item.
   *
   * @param ls            the ls
   * @param selectedMember the selected member
   */
  public void borrowItem(LendingSystem ls, Member selectedMember) {
    ArrayList<Item> items = ls.getAllItems();
    int index;
    final int back = 0;

    do {
      index = console.list(items);
    } while (index < 0 || index > items.size());

    if (index == back) {
      return;
    }

    Item selectedItem = items.get(index - 1);
    int startDay = console.getUserInt("Enter start day: ");
    int endDay = console.getUserInt("Enter end day: ");

    while (!(startDay >= ls.getCurrentDay() && endDay >= startDay)) {
      if (startDay < ls.getCurrentDay()) {
        console.displayError("Cant enter start day in the past. Today is day " + ls.getCurrentDay() + ". ");
      } else if (endDay < startDay) {
        console.displayError("Invalid input. End day must be after start day. ");
      }
      startDay = console.getUserInt("Enter start day: ");
      endDay = console.getUserInt("Enter end day: ");
    }

    if (!ls.hasActiveContract(selectedItem, startDay, endDay)) {
      if (ls.hasEnoughCredits(selectedMember, selectedItem, startDay, endDay)) {
        ls.establishContract(selectedItem, selectedMember, startDay, endDay);
      } else {
        console.displayError("No enough Credits! ");
      }

    } else {
      console.displayError("The item is not available during the choosen period! ");
    }
  }

  public void deleteItem(LendingSystem ls, Item item) {
    ls.deleteItem(item);
  }

  /**
   * List items.
   *
   * @param selectedMember the selected member
   * @param ls              the ls
   */
  public void listItems(Member selectedMember, LendingSystem ls) {
    final int back = 0;
    int userInput;

    do {
      ArrayList<Item> items = selectedMember.getItems();
      do {
        userInput = console.list(items);
      } while (userInput < 0 || userInput > items.size());

      if (userInput != back) {
        Item selectedItem = items.get(userInput - 1);
        itemView = new ItemView();
        display(selectedItem, selectedMember, ls);
      }
    } while (userInput != back);
  }
}
