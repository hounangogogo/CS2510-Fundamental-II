// CS 2510 Fall 2013
// Assignment 3

import tester.*;

// to represent a list of Strings
interface ILoS {

  // combine all Strings in this list into one
  String combine();

  /**
   * determines whether the list is sorted in alphabetical order.
   *
   * @return boolean
   */
  boolean isSorted();

  /**
   * Get the first element of in a list.
   *
   * @return the first element of a list.
   */
  String getFirst();

  /**
   * Get the rest of the string.
   *
   * @return the rest of the string.
   */
  ILoS getRest();

  /**
   * Check whether the list is empty or not.
   *
   * @return list is empty or not.
   */
  boolean isEmpty();

  /**
   * merge two sorted list of strings together.
   *
   * @param ls string list.
   * @return sorted list of string.
   */
  ILoS merge(ILoS ls);
}

// to represent an empty list of Strings
class MtLoS implements ILoS {
  MtLoS() {
  }

  // combine all Strings in this list into one
  public String combine() {
    return "";
  }

  /**
   * determines whether the list is sorted in alphabetical order.
   *
   * @return boolean
   */
  @Override
  public boolean isSorted() {
    return true;
  }

  /**
   * Get the first element of in a list.
   *
   * @return the first element.
   */
  @Override
  public String getFirst() {
    return "";
  }

  /**
   * Get the rest of the string.
   *
   * @return the rest of the string.
   */
  @Override
  public ILoS getRest() {
    return this;
  }

  /**
   * Check whether the list is empty or not.
   *
   * @return list is empty or not.
   */
  @Override
  public boolean isEmpty() {
    return true;
  }

  /**
   * merge two sorted list of strings together.
   *
   * @param ls string list.
   * @return sorted list of string.
   */
  @Override
  public ILoS merge(ILoS ls) {
    return ls;
  }
}

// to represent a nonempty list of Strings
class ConsLoS implements ILoS {
  String first;
  ILoS rest;

  /**
   * The constructor of ConsLos.
   *
   * @param first the first string.
   * @param rest  the rest of the list.
   */
  ConsLoS(String first, ILoS rest) {
    this.first = first;
    this.rest = rest;
  }
    
    /*
     TEMPLATE
     FIELDS:
     ... this.first ...         -- String
     ... this.rest ...          -- ILoS
     
     METHODS
     ... this.combine() ...     -- String
     
     METHODS FOR FIELDS
     ... this.first.concat(String) ...        -- String
     ... this.first.compareTo(String) ...     -- int
     ... this.rest.combine() ...              -- String
     
     */

  // combine all Strings in this list into one
  public String combine() {
    return this.first.concat(this.rest.combine());
  }

  /**
   * determines whether the list is sorted in alphabetical order.
   *
   * @return boolean
   */
  @Override
  public boolean isSorted() {
    if (rest.isEmpty()) {
      return true;
    }
    if (first.compareTo(rest.getFirst()) < 0) {
      return rest.isSorted();
    } else {
      return false;
    }
  }

  /**
   * Get the first element of in a list.
   *
   * @return the first element of a list.
   */
  @Override
  public String getFirst() {
    return first;
  }

  /**
   * Get the rest of the string.
   *
   * @return the rest of the string.
   */
  @Override
  public ILoS getRest() {
    return rest;
  }

  /**
   * Check whether the list is empty or not.
   *
   * @return list is empty or not.
   */
  @Override
  public boolean isEmpty() {
    return false;
  }

  /**
   * merge two sorted list of strings together.
   *
   * @param ls string list.
   * @return sorted list of string.
   */
  @Override
  public ILoS merge(ILoS ls) {
    if (this.isEmpty()) {
      return ls;
    }
    if (ls.isEmpty()) {
      return this;
    }
    if (first.compareTo(ls.getFirst()) <= 0) {
      return new ConsLoS(first, rest.merge(ls));
    } else {
      return new ConsLoS(ls.getFirst(), this.merge(ls.getRest()));
    }
  }
}

// to represent examples for lists of strings
class ExamplesStrings {

  ILoS empty = new MtLoS();
  ILoS mary = new ConsLoS("Mary ",
          new ConsLoS("had ",
                  new ConsLoS("a ",
                          new ConsLoS("little ",
                                  new ConsLoS("lamb.", this.empty)))));
  ILoS alph = new ConsLoS("A",
          new ConsLoS("C",
                  new ConsLoS("S", this.empty)));
  ILoS sorted = new ConsLoS("B",
          new ConsLoS("S", this.empty));

  boolean testGetFirst(Tester t) {
    return
            t.checkExpect(this.alph.getFirst(), "A") &&
                    t.checkExpect(empty.getFirst(), "");
  }

  boolean testGetRest(Tester t) {
    return
            t.checkExpect(this.alph.getRest(),
                    new ConsLoS("C",
                            new ConsLoS("S", empty))) &&
                    t.checkExpect(empty.getRest(), empty);


  }

  boolean testIsSorted(Tester t) {
    return
            t.checkExpect(this.alph.isSorted(), true) &&
                    t.checkExpect(this.mary.isSorted(), false) &&
                    t.checkExpect(this.empty.isSorted(), true);

  }

  boolean testMerge(Tester t) {
    return
            t.checkExpect(
                    this.alph.merge(this.sorted),
                    new ConsLoS("A",
                            new ConsLoS("B",
                                    new ConsLoS("C",
                                            new ConsLoS("S",
                                                    new ConsLoS("S",
                                                            empty)))))) &&
                    t.checkExpect(empty.merge(this.alph), this.alph) &&
                    t.checkExpect(this.alph.merge(empty), this.alph);

  }
}
