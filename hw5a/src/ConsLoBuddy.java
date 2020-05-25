import java.util.ArrayList;

// represents a list of Person's buddies
class ConsLoBuddy implements ILoBuddy {

  Person first;
  ILoBuddy rest;

  /**
   * Constructor of the ConsLoBuddy.
   *
   * @param first the first person.
   * @param rest  the rest person.
   */
  ConsLoBuddy(Person first, ILoBuddy rest) {
    this.first = first;
    this.rest = rest;
  }

  /**
   * Check whether the list contains the given person.
   *
   * @param p the given person.
   * @return true if contains person, else false.
   */
  @Override
  public boolean containsBuddy(Person p) {
    if (p.username.equals(first.username)) {
      return true;
    } else {
      return rest.containsBuddy(p);
    }
  }

  /**
   * Counter how many buddies in common.
   *
   * @param buddies list of buddies.
   * @return the number of buddie in common.
   */
  @Override
  public int countCommon(ILoBuddy buddies) {
    if (buddies.isEmpty()) {
      return 0;
    } else if (buddies.containsBuddy(first)) {
      return 1 + buddies.countCommon(rest);
    } else {
      return buddies.countCommon(rest);
    }
  }

  /**
   * Check the list is empty or not.
   *
   * @return the list is empty or not.
   */
  @Override
  public boolean isEmpty() {
    return false;
  }

  /**
   * Check whether the person will be invited.
   *
   * @param p the given person.
   * @return whether the person will be invited.
   */
  @Override
  public boolean distantBuddy(Person p, ArrayList<Person> checked) {
    if (containsBuddy(p)) {
      return true;
    } else if (checked.contains(first)) {
      return rest.distantBuddy(p, checked);
    } else {
      checked.add(first);
      return first.buddies.distantBuddy(p, checked) ||
              rest.distantBuddy(p, checked);
    }
  }

  /**
   * Add all person to list.
   *
   * @param list list of guest.
   * @return the list of guest.
   */
  @Override
  public ArrayList<Person> addGuestToList(ArrayList<Person> list) {
    if (list.contains(first)) {
      return rest.addGuestToList(list);
    } else {
      list.add(first);
      first.buddies.addGuestToList(list).addAll(rest.addGuestToList(list));
      return list;
    }
  }
}
