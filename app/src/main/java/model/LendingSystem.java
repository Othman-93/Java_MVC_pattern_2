package model;

import java.util.ArrayList;
import java.util.Random;

/**
 * LendingSystem class.
 */
public class LendingSystem {
  private static final int STARTING_CREDITS = 100;
  private static final int CREDITS_ADDED_PER_ITEM = 100;
  private ArrayList<Contract> contracts;
  private ArrayList<Member> members;
  private DayCounter dayCounter;
  private Random random;
  private SystemValidationService valditionService;

  /**
   * Constructor.
   */
  public LendingSystem() {
    this.members = new ArrayList<>();
    this.contracts = new ArrayList<>();
    this.dayCounter = new DayCounter(0);
    this.random = new Random();
    this.valditionService = new SystemValidationService();
  }

  /**
   * Get total number of contracts in the system.
   *
   * @return The total number of contracts as int
   */
  public int getTotalNumberOfContracts() {
    ArrayList<Contract> contacts = new ArrayList<Contract>();

    for (Member member : members) {
      contacts.addAll(member.getContracts());
    }
    return contacts.size();
  }

  /**
   * Get total number of contracts in the system.
   *
   * @return The total number of active contracts as int
   */
  public int getTotalNumberOfActiveContracts() {
    ArrayList<Contract> contacts = new ArrayList<Contract>();

    for (Member member : members) {
      for (Contract contract : member.getContracts()) {
        if (contract.isActive(getCurrentDay())) {
          contacts.add(contract);
        }
      }
    }
    return contacts.size();
  }

  /**
   * Get total number of items in the system.
   *
   * @return - The total number of items as int.
   */
  public int getTotalNumberOfItems() {
    return getAllItems().size();
  }

  /**
   * Get total number of members in the system.
   *
   * @return The total number of members as int.
   */
  public int getTotalNumberOfMembers() {
    return members.size();
  }

  /**
   * Get current day.
   *
   * @return The current day as int.
   */
  public int getCurrentDay() {
    return dayCounter.getCurrentDay();
  }

  /**
   * Advance day.
   */
  public void advanceDay() {
    dayCounter.advanceDay();
    for (Contract contract : contracts) {
      if (contract.getStartDay() == dayCounter.getCurrentDay()) {
        contract.getItem().setAvailable(false);

      } else if (contract.getEndDay() < dayCounter.getCurrentDay()) {
        contract.getItem().setAvailable(true);
      }
    }
  }

  /**
   * Get all items in the system.
   *
   * @return All items as ArrayList
   */
  public ArrayList<Item> getAllItems() {
    ArrayList<Item> items = new ArrayList<Item>();

    for (Member member : members) {
      items.addAll(member.getItems());
    }
    return items;
  }

  /**
   * Load current day into the system.
   *
   * @param currentDay The current day
   */
  protected void loadCurrentDay(int currentDay) {
    dayCounter = new DayCounter(currentDay);
  }

  /**
   * Load members into the system.
   *
   * @param members The members to load
   */
  protected void loadMembers(ArrayList<Member> members) {
    this.members = new ArrayList<>(members);
  }

  /**
   * Add a new member.
   *
   * @param member The member to add
   * @throws IllegalArgumentException If email or phone number is not unique
   */
  public void addMember(Member member) {

    // Make sure that the creationDay is set correctly
    Member newMember = new Member(member.getName(), member.getEmail(), member.getPhoneNumber(), getCurrentDay());

    if (valditionService.validateMember(newMember, members)) {
      newMember.setUserId(generateId(members));
      newMember.addCredits(STARTING_CREDITS);
      this.members.add(newMember);
    } else {
      throw new IllegalArgumentException("Could not create member: Email and Phone must be Unique.");
    }
  }

  public void addItemToMember(Item item, Member member) {
    member.addItem(item);
    member.addCredits(CREDITS_ADDED_PER_ITEM);
  }

  private String generateId(ArrayList<Member> memberList) {
    final int idLength = 6;
    final char[] alphaNumric = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
    boolean isUnique = true;
    StringBuffer buf = new StringBuffer();
    String id = " ";
    do {

      for (int i = 0; i < idLength; i++) {
        int randomIndex = random.nextInt(alphaNumric.length);
        buf.append(alphaNumric[randomIndex]);
      }
      id = buf.toString();
      for (Member member : memberList) {
        if (member.getUserId().equals(id)) {
          isUnique = false;
          break;
        }
      }

    } while (isUnique == false);

    return id;
  }

  public ArrayList<Member> getMembers() {
    return new ArrayList<>(members);
  }

  public ArrayList<Item> getItemsForMember(Member member) {
    return new ArrayList<>(member.getItems());
  }

  public void deleteMember(Member member) {
    members.remove(member);
  }

  /**
   * Delete item.
   *
   * @param item The item to delete
   */
  public void deleteItem(Item item) {
    // Clear contracts from the borrower member
    for (Contract contract : item.getContracts()) {
      Member borrower = contract.getBorrower();
      borrower.deleteContract(contract);
    }

    // Clear contracts from the item itself
    item.clearContracts();

    // Remove item from the owner member
    Member owner = item.getOwner();
    owner.deleteItem(item);
  }

  /**
   * Make payment for contract.
   *
   * @param contract The contract to make payment for
   * @throws IllegalArgumentException If the item already has an active contract
   *                                  for the startday and enday
   * @throws IllegalArgumentException If the borrower does not have enough credits
   */
  public void fulfillContract(Contract contract) {

    if (contract.getItem().hasActiveContract(contract.getStartDay(), contract.getEndDay())) {
      throw new IllegalArgumentException("Item is already rented out during this period.");
    }

    Member borrower = contract.getBorrower();
    Member lender = contract.getLender();
    if (borrower.getCredits() >= contract.getCost()) {
      if (!borrower.getUserId().equals(lender.getUserId())) {
        borrower.removeCredits(contract.getCost());
        lender.addCredits(contract.getCost());
      }
      establishContract(contract);
    } else {
      throw new IllegalArgumentException("Could not create contract, not enough credits.");
    }
  }

  public ArrayList<Contract> getContracts(Item item) {
    ArrayList<Contract> contracts = new ArrayList<>(item.getContracts());
    return contracts;
  }

  /**
   * Update member name.
   *
   * @param member The member to update
   * @param name   The new name
   */
  public void updateMemberName(Member member, String name) {
    member.setName(name);
  }

  /**
   * Update member email.
   *
   * @param member The member to update
   * @param email  The new email
   */
  public void updateMemberEmail(Member member, String email) {
    if (valditionService.validateEmail(email, members)) {
      member.setEmail(email);
    } else {
      throw new IllegalArgumentException("Email must be Unique.");
    }
  }

  /**
   * Update member phone number.
   *
   * @param member      The member to update
   * @param phoneNumber The new phone number
   */
  public void updateMemberPhoneNumber(Member member, String phoneNumber) {
    if (valditionService.validatePhoneNumber(phoneNumber, members)) {
      member.setPhoneNumber(phoneNumber);
    } else {
      throw new IllegalArgumentException("Email must be Unique.");
    }
  }

  public void updateItemName(Item item, String input) {
    item.setName(input);
  }

  public void updateItemCategory(Item item, String input) {
    item.setCategory(input);
  }

  public void updateItemDescription(Item item, String input) {
    item.setShortDescription(input);
  }

  public void updateItemPrice(Item item, int input) {
    item.setPrice(input);
  }

  /**
   * Get currently active contract.
   *
   * @param item The item to get currently active contract for
   * @return The currently active contract
   * 
   */
  public Contract getActiveContract(Item item) {
    for (Contract contract : item.getContracts()) {
      if (contract.isActive(getCurrentDay())) {
        return contract;
      }
    }
    return null;
  }

  public Member getBorrower(Contract activeContract) {
    return activeContract.getBorrower();
  }

  /**
   * Update member.
   *
   * @param member      The member to update
   * @param tempMember  The temporary member to copy name, email and phone number from
   */
  public void updateMember(Member member, Member tempMember) {
    if (!tempMember.getName().equals("")) {
      member.setName(tempMember.getName());
    }
    if (!tempMember.getEmail().equals("")) {
      boolean isUnique = valditionService.validateEmail(tempMember.getEmail(), members);
      if (isUnique) {
        member.setEmail(tempMember.getEmail());
      } else {
        throw new IllegalArgumentException("Email must be Unique.");
      }   
    }
    if (!tempMember.getPhoneNumber().equals("")) {
      boolean isUnique = valditionService.validatePhoneNumber(tempMember.getPhoneNumber(), members);

      if (isUnique) {
        member.setPhoneNumber(tempMember.getPhoneNumber());
      } else {
        throw new IllegalArgumentException("Phone number must be Unique.");
      }
    }
  }

  /**
   * Update item.
   *
   * @param tempItem  The temporary item to copy name, category, short description and price from
   * @param item      The item to update
   */
  public void updateItem(Item tempItem, Item item) {
    if (!tempItem.getName().equals("")) {
      item.setName(tempItem.getName());
      ;
    }
    if (!tempItem.getCategory().equals("")) {
      item.setCategory(tempItem.getCategory());
    }
    if (!tempItem.getShortDescription().equals("")) {
      item.setShortDescription(tempItem.getShortDescription());
    }
    if (tempItem.getPrice() >= 0) {
      item.setPrice(tempItem.getPrice());
    }
  }

  /**
  * Establish contract.
  *
  * @param contract The contract to establish in the system.
  */
  private void establishContract(Contract contract) {
    contract.getBorrower().addContract(contract);
    contract.getItem().addContract(contract);
  }

  /**
   * Establish contract.
   *
   * @param selectedItem  The selected item
   * @param selectedMember The selected member
   * @param startDay      The start day      
   * @param endDay        The end day
   */
  public void establishContract(Item selectedItem, Member selectedMember, int startDay, int endDay) {
    for (Member member : members) {

      if (member.getItems().contains(selectedItem)) {

        for (Item item : member.getItems()) {

          if (item == selectedItem) {

            Contract contract = new Contract(selectedMember, startDay, endDay, item);
            item.getContractsInPackage().add(contract);
            selectedMember.getContracts().add(contract);
            contracts.add(contract);
            member.addCredits(contract.getCost());
            selectedMember.removeCredits(contract.getCost());
          }
          
        }
      }
    }
  }

  /**
   * Check if item has active contract.
   *
   * @param itemCopy  The item to check
   * @param startDay  The start day
   * @param endDay    The end day
   * @return          True if the item has an active contract, false otherwise
   */
  public boolean hasActiveContract(Item itemCopy, int startDay, int endDay) {
    for (Member member : members) {
      for (Item item : member.getItems()) {
        if (item == itemCopy) {
          if (item.getContracts().size() == 0) {

            return false;
          }
          for (Contract contract : item.getContracts()) {
            for (int day = startDay; day <= endDay; day++) {
              if (contract.isActive(day)) {
                return true;
              }
            } 
          }
          
        }
      }
    }
    return false;

  }

  /**
   * Check if member has enough credits.
   *
   * @param selectedMember The member to check
   * @param selectedItem    The item to check    
   * @param startDay        The start day
   * @param endDay          The end day
   * @return                True if the member has enough credits, false otherwise
   */
  public boolean hasEnoughCredits(Member selectedMember, Item selectedItem, int startDay, int endDay) {
    int price = selectedItem.getPrice() * ((endDay - startDay) + 1);
    if (selectedMember.getCredits() >= price) {
      return true;
    }
    return false;
  }
}
