import java.util.ArrayList;

// represents an empty list of Person's bussies
class MTLoBuddy implements ILoBuddy {
  MTLoBuddy() {
  }


  /**
   * Check whether the list contains the given person.
   *
   * @param p the given person.
   * @return true if contains person, else false.
   */
  @Override
  public boolean containsBuddy(Person p) {
    return false;
  }

  /**
   * Counter how many buddies in common.
   *
   * @param buddies list of buddies.
   * @return the number of buddie in common.
   */
  @Override
  public int countCommon(ILoBuddy buddies) {
    return 0;
  }

  /**
   * Check the list is empty or not.
   *
   * @return the list is empty or not.
   */
  @Override
  public boolean isEmpty() {
    return true;
  }

  /**
   * Check whether the person will be invited.
   *
   * @param p the given person.
   * @return whether the person will be invited.
   */
  @Override
  public boolean distantBuddy(Person p, ArrayList<Person> checked) {
    return false;
  }

  /**
   * Add all person to list.
   *
   * @param list list of guest.
   * @return the list of guest.
   */
  @Override
  public ArrayList<Person> addGuestToList(ArrayList<Person> list) {
    return list;
  }

}
