package view;

import java.util.ArrayList;
import java.util.Scanner;
import model.Member;

/**
 * MemberConsole class.
 */
public class MemberConsole implements ManageInterface<Member> {

  @Override
  public void display(Member member) {
    String template = "%-13s %-13s";
    System.out.println("");
    System.out.println(String.format(template, "Name:",  member.getName()));
    System.out.println(String.format(template, "Email:",  member.getEmail()));
    System.out.println(String.format(template, "Phone number:",  member.getPhoneNumber()));
    System.out.println(String.format(template, "Credits:",  member.getCredits()));
    System.out.println(String.format(template, "Owned items: ", member.getNumberOfItems()));
  }

  public void listVarbose() {
    System.out.println("sdasd");
  }
  
  @Override
  public int list(ArrayList<Member> members) {
    int counter = 1;
    System.out.println();

    for (Member member : members) {
      System.out.println(counter + ") " + member.getName());
      counter++;
    }
    System.out.println("0) Back");

    int intput = getUserInt("\nChoose a member: ");
    return intput;
  }


  @Override
  public String getUserString(String message) {
    Scanner kb = new Scanner(System.in, "UTF-8");
    System.out.print(message);
    return kb.nextLine();
  }

  @Override
  public int getUserInt(String message) {
    Scanner kb = new Scanner(System.in, "UTF-8");
    System.out.print(message);
    return kb.nextInt();
  }

  @Override
  public Member update(Member member) {
    String name = getUserString("Enter a new name, or enter to skip: ");
    String email = getUserString("Enter a new Email, or enter to skip: ");
    String phoneNumber = getUserString("Enter a new phone number, or enter to skip: ");

    return new Member(name, email, phoneNumber, 0);
  }

  @Override
  public Member create() {
    String name;
    do {
      name = getUserString("Enter a name: ");
    } while (name.length() == 0);

    String email;
    do {
      email = getUserString("Enter an Email: ");
    } while (email.length() == 0);

    String phoneNumber;
    do {
      phoneNumber = getUserString("Enter a phone number: ");
    } while (phoneNumber.length() == 0);

    return new Member(name, email, phoneNumber, 0);
  }

  @Override
  public void displayError(String message) {
    System.out.println("");
    System.out.println("Error! " + message);
  }
}
