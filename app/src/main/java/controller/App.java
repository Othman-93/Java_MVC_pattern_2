package controller;

import model.HardcodedPersistenceManager;
import model.LendingSystem;
import model.PersistenceManagerInterface;

/**
 * Responsible for staring the application.
 */
public class App {
  /**
   * Application starting point.
   *
   * @param args command line arguments.
   */
  public static void main(String[] args) {

    PersistenceManagerInterface persistenceManager = new HardcodedPersistenceManager();

    LendingSystem ls = persistenceManager.load();

    Admin c = new Admin();
    c.start(ls);

    persistenceManager.save(ls);
  }
}
