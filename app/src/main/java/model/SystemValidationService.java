package model;

import java.util.ArrayList;

/**
 * SystemValidationService class.
 */
public class SystemValidationService {

  /**
   * Validate member.
   *
   * @param newMember   The member to validate if email and phone are unique
   * @param memberList  The member list to compare against
   * @return            Boolean to indicate if the user passed validtion successfully
   */
  protected boolean validateMember(Member newMember, ArrayList<Member> memberList) {
    for (Member member : memberList) {
      if (member.getPhoneNumber().equalsIgnoreCase(newMember.getPhoneNumber())) {
        return false;
      }
      if (member.getEmail().equalsIgnoreCase(newMember.getEmail())) {
        return false;
      }
    }
    return true;
  }

  /**
   * Validate item.
   *
   * @param email       The email to validate
   * @param memberList  The member list to compare against
   * @return            Boolean to indicate if the user passed validtion successfully
   */
  protected boolean validateEmail(String email, ArrayList<Member> memberList) {
    for (Member member : memberList) {
      if (member.getEmail().equalsIgnoreCase(email)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Validate phone number.
   *
   * @param phoneNumber The phone number to validate
   * @param memberList  The member list to compare against
   * @return            Boolean to indicate if the user passed validtion successfully
   */
  protected boolean validatePhoneNumber(String phoneNumber, ArrayList<Member> memberList) {
    for (Member member : memberList) {
      if (member.getPhoneNumber().equalsIgnoreCase(phoneNumber)) {
        return false;
      }
    }
    return true;
  }
}
