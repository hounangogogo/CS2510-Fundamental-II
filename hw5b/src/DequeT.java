// assignment 5
// Guo Hong
// guohong
// Zhao Haonan
// haonan

// to represent a book
class Book {
  String title;
  int price;

  /**
   * Constructor for book class.
   *
   * @param title the title of book
   * @param price the price of book
   */
  Book(String title, int price) {
    this.title = title;
    this.price = price;
  }
}

/**
 * the start point(sentinel) of a node list.
 */
class DequeT<T> {
  NodeT<T> sentinel;

  /**
   * The constructor for Deque.
   *
   * @param sentinel
   */
  DequeT(NodeT<T> sentinel) {
    this.sentinel = sentinel;
  }

  /**
   * Get the size of this deque.
   *
   * @return the size of deque.
   */
  int size() {
    if (sentinel.next.isSentinel()) {
      return 0;
    } else {
      return sentinel.next.getSize();
    }
  }


  /**
   * class Deque that consumes a String and inserts it at the front of the list.
   *
   * @param t the T for new node.
   */
  void addAtHead(T t) {
    NodeT<T> n = new NodeT<>(t);
    n.addNext(sentinel.next);
    sentinel.addNext(n);
  }

  /**
   * consumes a String and inserts it at the tail of this list.
   *
   * @param t T content for node.
   */
  void addAtTail(T t) {
    NodeT<T> n = new NodeT<>(t);
    sentinel.prev.addNext(n);
    n.addNext(sentinel);
  }

  /**
   * the class Deque that removes the first node from this Deque. Throw an exception, if an attempt
   * is made to remove from an empty list.
   */
  void removeFromHead() {
    if (sentinel.next.isSentinel()) {
      throw new RuntimeException("no node at the head of an empty list");
    } else {
      sentinel.addNext(sentinel.next.next);
    }
  }


  /**
   * the class Deque that removes the last node from this Deque. Throw an exception, if an attempt
   * is made to remove from an empty list.
   */
  void removeFromTail() {
    if (sentinel.prev.isSentinel()) {
      throw new RuntimeException("no node at the tail of an empty list");
    } else {
      sentinel.prev.prev.addNext(sentinel);
    }
  }


  /**
   * the class Deque that consumes a String and produces the node in this Deque that contains the
   * given String.
   *
   * @param t t target.
   * @return the string need to find.
   */
  NodeT<T> find(T t) {
    return sentinel.haveT(t);
  }


  /**
   * removes the given Node from this Deque. If the given node is the Sentinel, the method does
   * nothing.
   *
   * @param n node T
   */
  void removeNode(NodeT<T> n) {
    if (n.isSentinel()) {
      return;
    }
    n.prev.addNext(n.next);
  }
}

/**
 * Node has two links, one to the next item in the list and one to the previous node in the list.
 */
class NodeT<T> {
  T data;
  NodeT<T> prev;
  NodeT<T> next;

  /**
   * Constructor for node class.
   *
   * @param data the content stores in node.
   */
  NodeT(T data) {
    this.data = data;
    this.prev = null;
    this.next = null;
  }

  /**
   * Whether this node is a sentinel.
   *
   * @return boolean.
   */
  boolean isSentinel() {
    return false;
  }

  /**
   * get the size of the node list.
   *
   * @return the size of list of node.
   */
  int getSize() {
    if (this.isSentinel()) {
      return 0;
    }
    return 1 + next.getSize();
  }

  /**
   * Add a element for the next position.
   *
   * @param n the node need to be added.
   */
  void addNext(NodeT<T> n) {
    this.next = n;
    n.prev = this;
  }

  /**
   * Check whether the Deque contains the given string.
   *
   * @param t given string.
   * @return the node contains given string.
   */
  NodeT<T> haveT(T t) {
    if (t.equals(data)) {
      return this;
    } else if (next.isSentinel()) {
      return next;
    } else {
      return next.haveT(t);
    }
  }

}


/**
 * The Sentinel node is always present. It does not contain any useful data, i.e. the data field may
 * be either null, or for the list of Strings the empty String. Its role is to provide the link to
 * the head of the list and to the tail of the list.
 */
class SentinelT<T> extends NodeT<T> {

  /**
   * Constructor for node class.
   *
   * @param data the content stores in node.
   */
  SentinelT(T data) {
    super(data);
  }

  @Override
  boolean isSentinel() {
    return true;
  }
}
