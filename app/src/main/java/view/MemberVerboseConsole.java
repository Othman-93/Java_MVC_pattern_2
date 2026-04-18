package view;

import java.util.ArrayList;
import java.util.Scanner;
import model.Contract;
import model.Item;
import model.Member;

/**
 * MemberVerboseConsole class.
 */
public class MemberVerboseConsole extends MemberConsole {
  
  @Override
  public int list(ArrayList<Member> members) {
    for (Member member : members) {
      String template = "%-13s %-13s";
      System.out.println("");
      System.out.println(String.format(template, "Name:",  member.getName()));
      System.out.println(String.format(template, "Email:",  member.getEmail()));
      System.out.println(String.format("Has th following items: "));
      int counter = 1;
      for (Item item : member.getItems()) {
        System.out.println(counter + ". " + item.getName());
        if (!item.getIsAvailable()) {
          Contract contract = item.getContracts().get(item.getContracts().size() - 1);  //get the last contract added
          System.out.println("This item is currently lent to : "
              + contract.getBorrower().getName() + " from day " + contract.getStartDay()
                + " util day " + contract.getEndDay());
        }
      }
    }
    int intput;
    do {
      intput = getUserInt("\n0) Back: ");
    } while (intput != 0);
    return intput;
  }

  @Override
  public int getUserInt(String message) {
    Scanner kb = new Scanner(System.in, "UTF-8");
    System.out.print(message);
    return kb.nextInt();
  }
}
