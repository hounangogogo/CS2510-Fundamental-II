import java.util.ArrayList;

// represents a list of Person's buddies
interface ILoBuddy {

  /**
   * Check whether the list contains the given person.
   *
   * @param p the given person.
   * @return true if contains person, else false.
   */
  boolean containsBuddy(Person p);

  /**
   * Counter how many buddies in common.
   *
   * @param buddies list of buddies.
   * @return the number of buddie in common.
   */
  int countCommon(ILoBuddy buddies);

  /**
   * Check the list is empty or not.
   *
   * @return the list is empty or not.
   */
  boolean isEmpty();

  /**
   * Check whether the person will be invited.
   *
   * @param p the given person.
   * @return whether the person will be invited.
   */
  boolean distantBuddy(Person p, ArrayList<Person> checked);

  /**
   * Add all person to list.
   *
   * @return the list of guest.
   */
  ArrayList<Person> addGuestToList(ArrayList<Person> list);

}
