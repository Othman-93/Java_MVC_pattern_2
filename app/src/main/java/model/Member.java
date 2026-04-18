package model;

import java.util.ArrayList;

/**
 * Member class.
 */
public class Member {
  private String userId;
  private String name;
  private String email;
  private String phonNumber;
  private int credits;
  private int creationDay; 
  private ArrayList<Item> items;
  private ArrayList<Contract> contracts;

  /**
  * Constructor.
  *
  * @param name        The name of the member
  * @param email       The email of the member
  * @param phoneNumber The phone number of the member
  * @param currentDay  The current day, will be set as the creationDay of the member
  */
  public Member(String name, String email, String phoneNumber, int currentDay) {
    this.name = name;
    this.email = email;
    this.phonNumber = phoneNumber;
    this.creationDay = currentDay;
    this.items = new ArrayList<>();
    this.contracts = new ArrayList<>();
  }

  /**
   * Constructor.
   *
   * @param member The member to copy
   */
  public Member(Member member) {
    this.name = member.getName();
    this.email = member.getEmail();
    this.phonNumber = member.getPhoneNumber();
    this.items = new ArrayList<>();
    this.contracts = new ArrayList<>();
  }

  /**
   * Add item to member.
   *
   * @param title       The item title
   * @param description The item description
   * @param category    The item category
   * @param costPerDay  The item cost per day
   * @param creationDay The item the day was added to the system
   */
  protected void addItem(Item item) {
    item.setOwner(this);
    this.items.add(item);
  }

  public String getName() {
    return this.name;
  }

  public String getEmail() {
    return this.email;
  }

  public String getPhoneNumber() {
    return this.phonNumber;
  }

  public int getCredits() {
    return this.credits;
  }

  public int getCreationDay() {
    return this.creationDay;
  }

  public String getUserId() {
    return this.userId;
  }

  protected void setUserId(String idString) {
    this.userId = idString;
  }

  protected void setName(String name) {
    this.name = name;
  }

  protected void setEmail(String eamil) {
    this.email = eamil;
  }

  protected void setCredits(int credits) {
    this.credits = credits;
  }


  protected void setPhoneNumber(String phoneNumber) {
    this.phonNumber = phoneNumber;
  }

  protected void deleteItem(Item item) {
    this.items.remove(item);
  }

  public ArrayList<Item> getItems() {
    return new ArrayList<>(this.items);
  }

  protected ArrayList<Contract> getContracts() {
    return this.contracts;
  }

  /**
   * Get the number of contracts.
   *
   * @return The number of contracts of the member (both as lender and borrower)
   */
  public int getNumberOfContracts() {
    int numberOfContracts = this.getContracts().size();
    for (Item item : this.items) {
      numberOfContracts += item.getContracts().size();
    } 
    return numberOfContracts;
  }

  protected void addContract(Contract contract) {
    this.contracts.add(contract);
  }

  public int getNumberOfItems() {
    return this.items.size();
  }

  /**
   * Remove credits from member.
   *
   * @param cost The cost
   */
  protected void removeCredits(int cost) {
    this.credits -= cost;
  }

  /**
   * Add credits to member.
   *
   * @param payment The payment
   */
  protected void addCredits(int payment) {
    this.credits += payment;
  }

  protected void deleteContract(Contract contract) {
    this.contracts.remove(contract);
  }
}
