import java.util.ArrayList;
import java.util.Comparator;

/**
 * to represent PriorityQueue which contains <code>ArrayList</code> and
 * <code>Comparator</code>
 */
public class PriorityQueue<T> {
  ArrayList<T> list;
  Comparator<T> comp;

  /**
   * The constructor for priorityQueue.
   *
   * @param list list of T
   * @param comp the comparator
   */
  PriorityQueue(ArrayList<T> list, Comparator<T> comp) {
    this.list = list;
    this.comp = comp;
  }

  /**
   * isLeaf that consumes a node label (an int) and returns true if the node has no children.
   *
   * @param i the index of a node.
   * @return whether this node is a leaf
   */
  boolean isLeaf(int i) {
    return 2 * i > list.size() - 1;
  }

  /**
   * produces the index of its child with the highest priority
   *
   * @param index index of given node
   * @return index of its child with the highest priority
   */
  int higherPriorityChild(int index) {
    if (index * 2 + 1 > list.size() - 1) {
      return index * 2;
    } else {
      if (comp.compare(list.get(index * 2), list.get(index * 2 + 1)) >= 0) {
        return index * 2;
      } else {
        return index * 2 + 1;
      }
    }
  }

  /**
   * Swap two elements in list.
   *
   * @param i the index for first element.
   * @param j the index for second element.
   */
  private void swap(int i, int j) {
    T temp = list.get(i);
    list.set(i, list.get(j));
    list.set(j, temp);
  }

  /**
   * insert a node to the heap.
   *
   * @param node the node need to be added to heap.
   */
  public void insert(T node) {
    list.add(node);
    int k = list.size() - 1;
    while (k > 1 && comp.compare(list.get(k), list.get(k / 2)) > 0) {
      swap(k, k / 2);
      k = k / 2;
    }
  }

  /**
   * removes the node with the highest priority from the heap.
   */
  void remove() {
    T temp = list.get(1);
    T last = list.get(list.size() - 1);
    list.remove(list.size() - 1);
    int k = 1;
    list.set(k, last);
    while (!isLeaf(k)) {
      int child = higherPriorityChild(k);
      if (comp.compare(last, list.get(child)) < 0) {
        swap(child, k);
        k = child;
      } else {
        break;
      }
    }
  }

}
