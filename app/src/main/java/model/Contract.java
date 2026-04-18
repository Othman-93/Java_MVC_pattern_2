package model;

/**
 * Contract class.
 */
public class Contract {
  private Member borrower;
  private int startDay;
  private int endDay;
  private Item item;

  /**
   * Constructor.
   *
   * @param borrower  The borrower
   * @param startDay  The start day
   * @param endDay    The end day
   * @param item      The item
   */
  public Contract(Member borrower, int startDay, int endDay, Item item) {
    setBorrower(borrower);
    this.startDay = startDay;
    this.endDay = endDay;
    setItem(item);
  }

  /**
   * Check if the contract is active.
   *
   * @param day The day to check
   * @return    True if the contract is active, false otherwise
   */
  public boolean isActive(int day) {
    if (day >= startDay && day <= endDay) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Calculate and return the cost.
   *
   * @return  The cost as int
   */
  public int getCost() {
    int cost;

    if (borrower.getItems().contains(item)) {
      cost = 0;
      return cost;
    } else {
      cost = item.getPrice() * (endDay - startDay);
      return cost;
    }
  }

  public int getStartDay() {
    return this.startDay;
  }

  public int getEndDay() {
    return this.endDay;
  }

  public Member getBorrower() {
    return new Member(borrower);
  }

  protected Item getItem() {
    return this.item;
  }

  protected Member getLender() {
    return this.item.getOwner();
  }

  protected void setBorrower(Member borrower) {
    this.borrower = borrower;
  }
  
  protected void setItem(Item item) {
    this.item = item;
  }
}
