import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

// represents a Person with a user name and a list of buddies
class Person {

  String username;
  ILoBuddy buddies;

  Person(String username) {
    this.username = username;
    this.buddies = new MTLoBuddy();
  }


  // Change this person's buddy list so that it includes the given person
  void addBuddy(Person buddy) {
    buddies = new ConsLoBuddy(buddy, buddies);
  }

  // returns true if this Person has that as a direct buddy
  boolean hasDirectBuddy(Person that) {
    return buddies.containsBuddy(that);
  }

  ArrayList<Person> guestList() {
    ArrayList<Person> list = new ArrayList<>();
    list.add(this);
    return buddies.addGuestToList(list);
  }

  // returns the number of people who will show up at the party
  // given by this person
  int partyCount() {
    ArrayList<Person> p = this.guestList();
    Set<Person> set = new HashSet<>(p);
    p.clear();
    p.addAll(set);
    return p.size();
  }

  // returns the number of people that are direct buddies
  // of both this and that person
  int countCommonBuddies(Person that) {
    return buddies.countCommon(that.buddies);
  }

  // will the given person be invited to a party
  // organized by this person?
  boolean hasDistantBuddy(Person that) {
    ArrayList<Person> checked = new ArrayList<>();
    checked.add(this);
    return buddies.distantBuddy(that, checked);
  }
}
