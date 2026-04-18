package model;

/**
* DayCounter class.
*/
public class DayCounter {
  private int currentDay;

  protected DayCounter(int currentDay) {
    this.currentDay = currentDay;
  }

  protected int getCurrentDay() {
    return currentDay;
  }

  protected void advanceDay() {
    currentDay += 1;
  }
}
