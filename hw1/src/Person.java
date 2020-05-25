/**
 * assignment 1 pair xxx Chao Fang bursty Haonan Zhao haonan
 * <p>
 * 6 September 2013
 */


/**
 * Data collection for the US Census Bureau.
 */
class Person {
  private String name;
  private int yob;
  private String state;
  private boolean citizen;

  /**
   * Constructor for Person
   * @param name the name of person.
   * @param yob the year of birth given as a four digit number.
   * @param state state of residence - given as the standard two letter abbreviation.
   * @param citizen value, true, if the person is a US citizen.
   */
  public Person(String name, int yob, String state, boolean citizen) {
    this.name = name;
    this.yob = yob;
    this.state = state;
    this.citizen = citizen;
  }
}

/**
 * The examples of person.
 */
class PersonExample {
  Person alice = new Person("Alice", 1992, "MA", true);
  Person bob = new Person("Bob", 1993, "JS", false);
}