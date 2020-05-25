import java.util.ArrayList;

/**
 * Comparator interface for comparing two object.
 *
 * @param <T> object type T.
 */
interface Comparator<T> {

  /**
   * Compare two object.
   *
   * @param t1 the first object.
   * @param t2 the second object.
   * @return the compare result.
   */
  int compare(T t1, T t2);
}

/**
 * Compare Strings.
 */
class CompareString implements Comparator<String> {

  /**
   * Compare two object.
   *
   * @param t1 the first object.
   * @param t2 the second object.
   * @return the compare result.
   */
  @Override
  public int compare(String t1, String t2) {
    return t1.compareTo(t2);
  }
}

/**
 * Compare two integers.
 */
class CompareInteger implements Comparator<Integer> {

  /**
   * Compare two object.
   *
   * @param t1 the first object.
   * @param t2 the second object.
   * @return the compare result.
   */
  @Override
  public int compare(Integer t1, Integer t2) {
    return t1 - t2;
  }
}

/**
 * Algorithms class has method binarySearch.
 */
class Algorithms {

  /**
   * Binary search function for search a element.
   *
   * @param lower the lower bound for searching.
   * @param upper the upper bound for searching.
   * @param list  the input list.
   * @param comp  comparator.
   * @param obj   target need to find.
   * @param <T>   type T
   * @return the index of the target.
   */
  <T> int binarySearch(int lower, int upper, ArrayList<T> list, Comparator<T> comp, T obj) {
    if (lower > upper) {
      return -1;
    }
    int currentIndex = (lower + upper) / 2;
    T currentValue = list.get(currentIndex);
    int diff = comp.compare(currentValue, obj);
    if (diff == 0) {
      return currentIndex;
    } else if (diff < 0) {
      return binarySearch(currentIndex + 1, upper, list, comp, obj);
    } else {
      return binarySearch(lower, currentIndex - 1, list, comp, obj);
    }
  }
}