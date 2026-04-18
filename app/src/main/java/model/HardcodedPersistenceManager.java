package model;

import java.util.ArrayList;

/**
 * HardcodedPersistenceManager class.
 */
public class HardcodedPersistenceManager implements PersistenceManagerInterface {

  @Override
  public LendingSystem load() {
    return hardCodedLendingSystem();
  }

  @Override
  public void save(LendingSystem ls) {
    // Saved into imaginary database
  }

  private LendingSystem hardCodedLendingSystem() {

    Member member1 = new Member("Othman", "Othman@email.com", "123", 0);
    member1.addCredits(500);
    member1.setUserId("E5MV4W");
    Member member2 = new Member("Tobias", "Tobias@email.com", "456", 0);
    member2.addCredits(100);
    member2.setUserId("8TREQF");
    Member member3 = new Member("Harry", "Harry@email.com", "789", 0);
    member3.addCredits(100);
    member3.setUserId("DFXJCL");

    Item item1 = new Item("Electric bike", "Easy workout Bike", "cycleling", 50, 0);
    Item item2 = new Item("PS5", "The best gaming console", "Gaming", 10, 0);

    Contract contract1 = new Contract(member3, 5, 7, item2);

    item2.addContract(contract1);

    member1.addItem(item1);
    member1.addItem(item2);
    member3.addContract(contract1);

    ArrayList<Member> members = new ArrayList<>();
    members.add(member1);
    members.add(member2);
    members.add(member3);

    LendingSystem ls = new LendingSystem();
    ls.loadMembers(members);
    ls.loadCurrentDay(0);

    return ls;
  }
}
