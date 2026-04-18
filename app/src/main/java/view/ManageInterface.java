package view;

import java.util.ArrayList;

/**
 * ConsoleUi interface.
 */
public interface ManageInterface<T> {

  void display(T objet);

  T create();

  int list(ArrayList<T> object);

  T update(T object);

  String getUserString(String message);
  
  void displayError(String message);

  int getUserInt(String message);

  default void listVarbose(ArrayList<T> members){
    
  }
}
