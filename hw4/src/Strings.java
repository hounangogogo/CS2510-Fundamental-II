// CS 2510 Fall 2013
// Assignment 3

import tester.*;

/**
 * interface string compare has a compare method.
 */
interface StringsCompare {

  /**
   * Compare two string base on different method.
   *
   * @param s1 the first string.
   * @param s2 the second string.
   * @return the result whether the first one comes before.
   */
  boolean comesBefore(String s1, String s2);
}

/**
 * Comparing the Strings lexicographically.
 */
class StringLexComp implements StringsCompare {

  /**
   * Compare two string base on different method.
   *
   * @param s1 the first string.
   * @param s2 the second string.
   * @return the result whether the first one comes before.
   */
  @Override
  public boolean comesBefore(String s1, String s2) {
    return s1.compareTo(s2) <= 0;
  }
}

/**
 * Comparing the Strings by their length from the shortest to the longest.
 */
class StringLengthComp implements StringsCompare {

  /**
   * Compare two string base on different method.
   *
   * @param s1 the first string.
   * @param s2 the second string.
   * @return the result whether the first one comes before.
   */
  @Override
  public boolean comesBefore(String s1, String s2) {
    return s1.length() < s2.length();
  }
}

// to represent a list of Strings
interface ILoS {

  // combine all Strings in this list into one
  String combine();

  /**
   * determines whether the list is sorted in alphabetical order.
   *
   * @return boolean
   */
  boolean isSorted(StringsCompare comp);

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
  ILoS merge(ILoS ls, StringsCompare comp);

  /**
   * Produces a sorted list, in the order given by the StringsCompare function object.
   *
   * @return a sorted list.
   */
  ILoS sort(StringsCompare comp);


  /**
   * Find the smallest string from a list.
   *
   * @param s    the smallest string.
   * @param comp the comparator.
   * @return the smallest string in this list.
   */
  String findSmallest(String s, StringsCompare comp);

  /**
   * Remove the smallest string from list.
   *
   * @param s the given string.
   * @return the list after remove the smallest string.
   */
  ILoS removeSmallest(String s);
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
  public boolean isSorted(StringsCompare comp) {
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
  public ILoS merge(ILoS ls, StringsCompare comp) {
    return ls;
  }

  /**
   * Produces a sorted list, in the order given by the StringsCompare function object.
   *
   * @param comp comparator.
   * @return a sorted list.
   */
  @Override
  public ILoS sort(StringsCompare comp) {
    return this;
  }

  /**
   * Find the smallest string from a list.
   *
   * @param s    the smallest string.
   * @param comp the comparator.
   * @return the smallest string in this list.
   */
  @Override
  public String findSmallest(String s, StringsCompare comp) {
    return s;
  }


  /**
   * Remove the smallest string from list.
   *
   * @param s the given string.
   * @return the list after remove the smallest string.
   */
  @Override
  public ILoS removeSmallest(String s) {
    return this;
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
  public boolean isSorted(StringsCompare comp) {
    if (rest.isEmpty()) {
      return true;
    }
    if (comp.comesBefore(first, rest.getFirst())) {
      return rest.isSorted(comp);
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
  public ILoS merge(ILoS ls, StringsCompare comp) {
    if (this.isEmpty()) {
      return ls;
    }
    if (ls.isEmpty()) {
      return this;
    }
    if (comp.comesBefore(first, ls.getFirst())) {
      return new ConsLoS(first, rest.merge(ls, comp));
    } else {
      return new ConsLoS(ls.getFirst(),
              this.merge(ls.getRest(), comp));
    }
  }

  /**
   * Produces a sorted list, in the order given by the StringsCompare function object.
   *
   * @param comp
   * @return a sorted list.
   */
  @Override
  public ILoS sort(StringsCompare comp) {
    String smallest = findSmallest(first, comp);
    ILoS rest = removeSmallest(smallest);
    return new ConsLoS(smallest, rest.sort(comp));
  }

  /**
   * Find the smallest string from a list.
   *
   * @param s    the smallest string.
   * @param comp the comparator.
   * @return the smallest string in this list.
   */
  @Override
  public String findSmallest(String s, StringsCompare comp) {
    if (comp.comesBefore(first, s)) {
      return rest.findSmallest(first, comp);
    } else {
      return rest.findSmallest(s, comp);
    }
  }

  /**
   * Remove the smallest string from list.
   *
   * @param s the given string.
   * @return the list after remove the smallest string.
   */
  @Override
  public ILoS removeSmallest(String s) {
    if (first.equals(s)) {
      return rest;
    } else {
      return new ConsLoS(first, rest.removeSmallest(s));
    }
  }
}

/**
 * Example and test for list of strings.
 */
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
  ILoS unsorted = new ConsLoS("b",
          new ConsLoS("a", new ConsLoS("d",
                  new ConsLoS("c", empty))));
  ILoS l1 = new ConsLoS("a",
          new ConsLoS("abc",
                  new ConsLoS("abcde", this.empty)));
  ILoS l2 = new ConsLoS("ab",
          new ConsLoS("abcd", this.empty));
  ILoS l3 = new ConsLoS("123",
          new ConsLoS("12", new ConsLoS("12345",
                  new ConsLoS("1234", empty))));


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
            t.checkExpect(this.alph.isSorted(new StringLexComp()), true) &&
                    t.checkExpect(this.mary.isSorted(new StringLexComp()), false) &&
                    t.checkExpect(this.empty.isSorted(new StringLexComp()), true);

  }

  boolean testMerge(Tester t) {
    return
            t.checkExpect(
                    this.alph.merge(this.sorted, new StringLexComp()),
                    new ConsLoS("A",
                            new ConsLoS("B",
                                    new ConsLoS("C",
                                            new ConsLoS("S",
                                                    new ConsLoS("S",
                                                            empty)))))) &&

                    t.checkExpect(
                            this.l1.merge(this.l2, new StringLengthComp()),
                            new ConsLoS("a",
                                    new ConsLoS("ab",
                                            new ConsLoS("abc",
                                                    new ConsLoS("abcd",
                                                            new ConsLoS("abcde",
                                                                    empty)))))) &&
                    t.checkExpect(empty.merge(this.alph, new StringLexComp()),
                            this.alph) &&
                    t.checkExpect(this.alph.merge(empty, new StringLexComp()),
                            this.alph);

  }

  boolean testFindMost(Tester t) {
    return
            t.checkExpect(this.unsorted.findSmallest(this.unsorted.getFirst(),
                    new StringLexComp()),
                    "a") &&
                    t.checkExpect(this.empty.findSmallest("string",
                            new StringLexComp()), "string");


  }

  boolean testStringLeft(Tester t) {
    return
            t.checkExpect(this.l2.removeSmallest("abcd"),
                    new ConsLoS("ab", empty)) &&
                    t.checkExpect(this.empty.removeSmallest("abcd"),
                            this.empty);
  }

  boolean testSort(Tester t) {
    return
            t.checkExpect(this.unsorted.sort(new StringLexComp()),
                    new ConsLoS("a", new ConsLoS("b", new ConsLoS("c",
                            new ConsLoS("d", empty))))) &&
                    t.checkExpect(
                            this.l3.sort(new StringLengthComp()),
                            new ConsLoS(
                                    "12",
                                    new ConsLoS("123",
                                            new ConsLoS("1234",
                                                    new ConsLoS("12345",
                                                            empty)))));
  }

}

