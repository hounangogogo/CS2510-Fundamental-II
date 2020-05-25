import java.util.*;

/**
 * <code>Word</code> represents a word and its number of occurrences
 *
 * @author CS2510
 * @version 11-09-2013
 */
public class Word {
  String s;
  int counter;

  /**
   * @param s the word s.
   */
  Word(String s) {
    this.s = s;
    counter = 1;
  }


  /**
   * Is this Word equal to the given Object
   *
   * @param obj the given object
   * @return .. FILL IN ...
   */
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (obj instanceof Word) {
      Word w = (Word) obj;
      return w.s.equals(this.s);
    } else {
      return false;
    }
  }

  /**
   * Produce this Word's hashCode
   *
   * @return .. FILL IN ...
   */
  public int hashCode() {
    /* ... FILL IN ... */
    return s.hashCode();
  }

  /**
   * Produce a String representation of this Word
   *
   * @return a String representation of this Word
   */
  public String toString() {
    return (this.s + ", Counter: " + this.counter + "; ");
  }

  /**
   * increase the frequency of the Word by 1
   */
  public void increment() {
    counter++;
  }
}

/**
 * Class words by freq is a comparator for two words.
 */
class WordsByFreq implements Comparator<Word> {

  @Override
  public int compare(Word w1, Word w2) {
    return w2.counter - w1.counter;
  }
}