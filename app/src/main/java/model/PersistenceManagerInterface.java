package model;

/**
* PersistenceManagerInterface interface.
*/
public interface PersistenceManagerInterface {
  LendingSystem load();

  void save(LendingSystem ls);
}