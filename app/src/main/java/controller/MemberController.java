package controller;

import model.Item;
import model.LendingSystem;
import model.Member;
import view.ManageInterface;
import view.MemberConsole;
import view.MemberView;
import view.MenuInterface;
import view.UserAction;


/**
 * MemberController class.
 */
public class MemberController {

  private MenuInterface view;
  private ManageInterface<Member> console;
  private ItemController ic;

  /**
   * Constructor.
   */
  public MemberController() {
    view = new MemberView();
    console = new MemberConsole();
    ic = new ItemController();
  }
     
  /**
   * Display the member.
   *
   * @param selectedMember the selected member
   * @param ls             the ls
   */
  public void displayMember(Member selectedMember, LendingSystem ls) {
    UserAction action;

    do {
      console.display(selectedMember);
      view.display();
      action = view.getAction();
      
      switch (action) {
        case Update:
          updateMember(ls, selectedMember);
          break;
        case AddItem:
          addItem(ic, selectedMember, ls);
          break;
        case ListItems:
          displayItems(ls, selectedMember);
          break;
        case BorrowItem:
          ic.borrowItem(ls, selectedMember);
          break;
        case Delete:
          deleteMember(ls, selectedMember);
          return;
        case Back:
          return;
        default:
          break;
      }
    } while (action != UserAction.Back || action != UserAction.Delete);
  }

  private void displayItems(LendingSystem ls, Member selectedMember) {
    ic.listItems(selectedMember, ls);
  }

  private void deleteMember(LendingSystem ls, Member selectedMember) {
    ls.deleteMember(selectedMember);
  } 

  private void addItem(ItemController ic, Member member, LendingSystem ls) {
    Item item = ic.createItem();
    ls.addItemToMember(item, member);
  }

  private void updateMember(LendingSystem ls, Member member) {
    while (true) {
      Member copyMember = console.update(member);

      try {
        ls.updateMember(member, copyMember);
        return;
      } catch (Exception e) {
        console.displayError(e.getMessage());
      }
    }
  }
}
