package model;

import java.util.ArrayList;

/**
 * Item class.
 */
public class Item {
  private Member owner;
  private String name;
  private String shortDescription;
  private int price;
  private String category;
  private int creationDay;
  private boolean isAvailable;
  private ArrayList<Contract> contracts;

  /**
   * Constructor.
   *
   * @param name            The name of the item
   * @param shortDecription The short description of the item
   * @param category        The category of the item
   * @param price           The price of the item
   * @param creationDay     The current day, will be set as the creationDay of the item
   */
  public Item(String name, String shortDecription, String category, int price, int creationDay) {
    this.name = name;
    this.shortDescription = shortDecription;
    this.category = category;
    this.price = price;
    this.creationDay = creationDay;
    this.contracts = new ArrayList<Contract>();
    this.isAvailable = true;
  }
  
  public String getName() {
    return name;
  }

  protected void setName(String name) {
    this.name = name;
  }

  public String getShortDescription() {
    return shortDescription;
  }

  protected void setShortDescription(String shortDescription) {
    this.shortDescription = shortDescription;
  }

  public int getPrice() {
    return price;
  }

  protected void setPrice(int price) {
    this.price = price;
  }

  public String getCategory() {
    return category;
  }

  protected void setCategory(String category) {
    this.category = category;
  }

  public int getCreationDay() {
    return creationDay;
  }

  protected void setCreationDay(int creationDay) {
    this.creationDay = creationDay;
  }

  public boolean getIsAvailable() {
    return isAvailable;
  }
  
  /**
   * Check if the item is available.
   *
   * @return  The availability as String
   */
  public Boolean isAvailable() {
    if (this.isAvailable) {
      return true;
    } 
    return false;
  }

  protected void setAvailable(boolean isAvailable) {
    this.isAvailable = isAvailable;
  }

  public ArrayList<Contract> getContracts() {
    return new ArrayList<Contract>(this.contracts);
  } 

  protected ArrayList<Contract> getContractsInPackage() {
    return this.contracts;
  } 

  protected void addContract(Contract contract) {
    this.contracts.add(contract);
  }

  protected Member getOwner() {
    return owner;
  }
  

  /**
   * Clear the contracts.
   */
  protected void clearContracts() {
    this.contracts.clear();
  }

  protected void setOwner(Member member) {
    this.owner = member;
  }

  /**
   * Check if ther is an active contract.
   *
   * @param day The day to check
   * @return    True if there is an active contract, false otherwise
   */
  public boolean hasActiveContract(int day) {
    for (Contract contract : contracts) {
      if (contract.isActive(day)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Check if ther is an active contract between two days.
   *
   * @param startDay The start day to check
   * @param endDay   The end day to check
   * @return    True if there is an active contract, false otherwise
   */
  protected boolean hasActiveContract(int startDay, int endDay) {
    for (Contract contract : contracts) {
      for (int day = startDay; day <= endDay; day++) {
        if (contract.isActive(day)) {
          return true;
        }
      }
    }
    return false;
  }
}
