package view;

import java.util.ArrayList;
import model.Member;

/**
 * MemberView class.
 */
public class SystemSummary {

  /**
   * Show system summary.
   *
   * @param memberCount         the member count
   * @param itemCount           the item count
   * @param contractCount       the contract count
   * @param activeContractCount the active contract count
   * @param members             the members
   */
  public void showSystemSummary(int memberCount, int itemCount, int contractCount, int activeContractCount,
      ArrayList<Member> members) {
    System.out.println("");
    System.out.println("System summary--------------------------");
    System.out.println("");
    System.out.println(memberCount + " members, " + itemCount + " items.");
    System.out.println(contractCount + " contracts, " + activeContractCount + " active contracts.");

    for (Member member : members) {
      System.out.println("");
      System.out.println(
          member.getName() + ", " + member.getEmail() + ", " + member.getPhoneNumber() + ", " + member.getUserId());
      System.out.println(member.getCredits() + " credits, " + member.getNumberOfItems() + " items, "
          + member.getNumberOfContracts() + " contracts.");
    }
    System.out.println("----------------------------------------");
  }
}
