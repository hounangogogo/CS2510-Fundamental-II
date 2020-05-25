import java.util.ArrayList;

import tester.Tester;

/**
 * the interface of comparator function object.
 *
 * @param <T> takes in type T.
 */
interface IComparator<T> {

  /**
   * Compare two elements.
   *
   * @param t1 the first element.
   * @param t2 the second element.
   * @return whether the first is smaller than the second.
   */
  boolean compare(T t1, T t2);
}

class IntegerComparator implements IComparator<Integer> {

  /**
   * Compare two elements.
   *
   * @param t1 the first element.
   * @param t2 the second element.
   * @return whether the first is smaller than the second.
   */
  @Override
  public boolean compare(Integer t1, Integer t2) {
    return t1 -t2 <= 0;
  }
}


/**
 * Comparator for String.
 */
class StringComparator implements IComparator<String> {

  /**
   * Compare two elements.
   *
   * @param t1 the first element.
   * @param t2 the second element.
   * @return whether the first is smaller than the second.
   */
  @Override
  public boolean compare(String t1, String t2) {
    return t1.compareTo(t2) <= 0;
  }
}

/**
 * Insertion sort was that each new item has been inserted into the already sorted list.
 */
public class ExamplesInsertSort {

  IComparator<Integer> compInt;
  IComparator<String> compStr;

  ArrayList<Integer> numList;
  ArrayList<String> strList;

  void setup() {
    compInt = new IntegerComparator();
    compStr = new StringComparator();
    numList = new ArrayList<>();
    strList = new ArrayList<>();
    numList.add(1);
    numList.add(4);
    numList.add(8);

    strList.add("a");
    strList.add("g");
    strList.add("x");
  }

  /**
   * Insert a element to a sorted list.
   *
   * @param item       element need to be inserted to list.
   * @param sortedList the sorted list.
   * @param c          the comparator for compare.
   * @param <T>        type T.
   */
  <T> void sortedInsert(T item, ArrayList<T> sortedList, IComparator<T> c) {
    int size = sortedList.size();
    for (int i = 0; i < size; i++) {
      if (c.compare(item, sortedList.get(i))) {
        sortedList.add(i, item);
        break;
      }
    }
    if (sortedList.size() == size) {
      sortedList.add(item);
    }
  }


  /**
   * InsertSort produce a new array.
   *
   * @param unsorted takes in a unsorted array.
   * @param c        comparator.
   * @param <T>      type T
   * @return the order list.
   */
  <T> ArrayList<T> insertSort(ArrayList<T> unsorted, IComparator<T> c) {
    ArrayList<T> result = new ArrayList<>();
    for (T element : unsorted) {
      if (result.isEmpty()) {
        result.add(element);
      } else {
        for (int j = 0; j < result.size(); j++) {
          T current = result.get(j);
          if (c.compare(result.get(result.size() - 1), element)) {
            result.add(element);
            break;
          }

          if (c.compare(element, current)) {
            result.add(j, element);
            break;
          }
        }
      }
    }
    return result;
  }


  /**
   * function perform an insertionSort to the list.
   *
   * @param list the input list.
   * @param c    comparator.
   * @param <T>  type T for array list element.
   */
  <T> void insertionSort(ArrayList<T> list, IComparator<T> c) {
    for (int i = 0; i < list.size(); i++) {
      T element = list.get(i);

      for (int j = 0; j <= i; j++) {
        if (c.compare(element, list.get(j))) {
          list.remove(element);
          list.add(j, element);
          break;
        }
      }
    }
  }

  /**
   * test Sorted Insert.
   *
   * @param t tester t.
   * @return sorted list.
   */
  boolean testSortedInsert(Tester t) {
    setup();
    ArrayList<Integer> result = new ArrayList<>();
    result.add(1);
    result.add(2);
    result.add(4);
    result.add(8);
    sortedInsert(2, numList, compInt);


    ArrayList<String> s = new ArrayList<>();
    s.add("a");
    s.add("b");
    s.add("g");
    s.add("x");
    sortedInsert("b", strList, compStr);
    return t.checkExpect(s, strList) && t.checkExpect(result, numList);
  }

  boolean testInsertSort(Tester t) {
    setup();
    ArrayList<Integer> unordered = new ArrayList<>();
    unordered.add(4);
    unordered.add(2);
    unordered.add(3);
    unordered.add(1);

    ArrayList<Integer> result = insertSort(unordered, compInt);
    ArrayList<Integer> expect = new ArrayList<>();
    expect.add(1);
    expect.add(2);
    expect.add(3);
    expect.add(4);
    return t.checkExpect(expect, result);
  }

  boolean testInsertionSort(Tester t) {
    setup();
    ArrayList<Integer> unordered = new ArrayList<>();
    unordered.add(4);
    unordered.add(2);
    unordered.add(3);
    unordered.add(1);
    insertionSort(unordered, compInt);
    ArrayList<Integer> expect = new ArrayList<>();
    expect.add(1);
    expect.add(2);
    expect.add(3);
    expect.add(4);
    return t.checkExpect(expect, unordered);
  }

}
