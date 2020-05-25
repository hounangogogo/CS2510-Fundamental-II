/**


 +-------+
 | Deque |
 +-------+
 | node  |------+
 +-------+      |
         +------+
         |
         v
     +----------+
     | Sentinel |
     +----------+
     | ""       |
 +---| next     |
 |   | prev     |-----------------------------+
 |   +----------+                             |
 |                                            |
 v                                            v
 +----------+   +----------+   +----------+   +----------+
 | Node     |   | Node     |   | Node     |   | Node     |
 +----------+   +----------+   +----------+   +----------+
 | "abc"    |   | "bcd"    |   | "cde"    |   | "def"    |
 | bcdnode  |-->| cdenode  |-->| defnode  |-->| sentinel |
 | sentinel |<--| abcnode  |<--| bcdnode  |<--| cdenode  |
 +----------+   +----------+   +----------+   +----------+

 */



/**
 * the start point(sentinel) of a node list.
 */
class Deque {
  Node sentinel;

  /**
   * The constructor for Deque.
   *
   * @param sentinel
   */
  Deque(Node sentinel) {
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
   * @param s the string for new node.
   */
  void addAtHead(String s) {
    Node n = new Node(s);
    n.addNext(sentinel.next);
    sentinel.addNext(n);
  }

  /**
   * consumes a String and inserts it at the tail of this list.
   *
   * @param s string content for node.
   */
  void addAtTail(String s) {
    Node n = new Node(s);
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
   * @param s string target.
   * @return the string need to find.
   */
  Node find(String s) {
    return sentinel.haveString(s);
  }


  /**
   * removes the given Node from this Deque.
   * If the given node is the Sentinel, the method does nothing.
   * @param n
   */
  void removeNode(Node n) {
    if (n.isSentinel()) {
      return;
    }
    n.prev.addNext(n.next);
  }
}

  /**
 * Node has two links, one to the next item in the list and one to the previous node in the list.
 */
class Node {
  String data;
  Node prev;
  Node next;

  /**
   * Constructor for node class.
   * @param data the content stores in node.
   */
  Node(String data) {
    this.data = data;
    this.prev = null;
    this.next = null;
  }

  /**
   * Whether this node is a sentinel.
   * @return boolean.
   */
  boolean isSentinel() {
    return false;
  }

  /**
   * get the size of the node list.
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
   * @param n the node need to be added.
   */
  void addNext(Node n) {
    this.next = n;
    n.prev = this;
  }

    /**
     * Check whether the Deque contains the given string.
     * @param s given string.
     * @return the node contains given string.
     */
  Node haveString(String s) {
    if (s.equals(data)) {
      return this;
    }
    else if (next.isSentinel()) {
      return next;
    }
    else {
      return next.haveString(s);
    }
  }

}


/**
 * The Sentinel node is always present. It does not contain any useful data,
 * i.e. the data field may be either null, or for the list of Strings the empty String.
 * Its role is to provide the link to the head of the list and to the tail of the list.
 */
class Sentinel extends Node {

  /**
   * Constructor for node class.
   *
   * @param data the content stores in node.
   */
  Sentinel(String data) {
    super(data);
  }

  @Override
  boolean isSentinel() {
    return true;
  }
}
