package controller;

import java.util.ArrayList;
import model.LendingSystem;
import model.Member;
import view.ManageInterface;
import view.MemberConsole;
import view.MemberVerboseConsole;
import view.MenuInterface;
import view.MenuView;
import view.SystemSummary;
import view.UserAction;

/**
 * Admin Class.
 */
public class Admin {
  private MenuInterface view;
  private ManageInterface<Member> console;
  private MemberController mc;

  /**
   * Constructor.
   */
  public Admin() {
    this.view = new MenuView(0);
    this.console = new MemberConsole();
    this.mc = new MemberController();
  }

  /**
   * Start the application.
   */
  public void start(LendingSystem ls) {
    UserAction action;

    do {
      displaySystemInfo(ls);

      console = new MemberConsole();
      view = new MenuView(ls.getCurrentDay());
      view.display();
      action = view.getAction();

      switch (action) {
        case Create:
          creatMember(ls);
          break;
        case Display:
          listMembers(ls);
          break;
        case DisplayVerbose:
          console = new MemberVerboseConsole();
          console.list(ls.getMembers());
          break;
        case AdvanceDay:
          ls.advanceDay();
          break;
        case Exit:
          return;
        default:
          break;
      }
    } while (action != UserAction.Exit);
  }

  private void creatMember(LendingSystem ls) {
    Member member = console.create();
    try {
      ls.addMember(member);
    } catch (Exception e) {
      console.displayError(e.getMessage());
    }
  }

  private void listMembers(LendingSystem ls) {
    final int back = 0;
    int userInput;

    do {
      ArrayList<Member> members = ls.getMembers();

      do {
        userInput = console.list(members);
      } while (userInput < 0 || userInput > members.size());

      if (userInput != back) {
        Member selectedMember = members.get(userInput - 1);
        mc.displayMember(selectedMember, ls);
      }
    } while (userInput != back);
  }


  private void displaySystemInfo(LendingSystem ls) {
    int memberCount = ls.getTotalNumberOfMembers();
    int itemCount = ls.getTotalNumberOfItems();
    int contractCount = ls.getTotalNumberOfContracts();
    int activeContractCount = ls.getTotalNumberOfActiveContracts();

    SystemSummary view = new SystemSummary();
    view.showSystemSummary(memberCount, itemCount, contractCount, activeContractCount, ls.getMembers());
  }
}
