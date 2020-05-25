// assignment 5
// Guo Hong
// guohong
// Zhao Haonan
// haonan

import tester.Tester;

// to represent all examples
class ExamplesDequeT<T> {
  // make some examples
  SentinelT<String> sentinel = new SentinelT<String>("");
  NodeT<String> abcnode = new NodeT<String>("abc");
  NodeT<String> bcdnode = new NodeT<String>("bcd");
  NodeT<String> cdenode = new NodeT<String>("cde");
  NodeT<String> defnode = new NodeT<String>("def");

  DequeT<String> deque1 = new DequeT<String>(sentinel);

  Book b1 = new Book("Buffalo", 10);
  Book b2 = new Book("Boston", 12);
  Book b3 = new Book("New york", 14);
  Book b4 = new Book("Toronto", 15);
  Book b5 = new Book("Albany", 20);
  Book b6 = new Book("Changsha", 16);

  NodeT<Book> n1 = new NodeT<Book>(b1);
  NodeT<Book> n2 = new NodeT<Book>(b2);
  NodeT<Book> n3 = new NodeT<Book>(b3);
  NodeT<Book> n4 = new NodeT<Book>(b4);
  SentinelT<Book> sentinelb = new SentinelT<Book>(null);

  NodeT<Book> n5 = new NodeT<Book>(b5);

  DequeT<Book> deque2 = new DequeT<Book>(sentinelb);

  // make an empty list for string deque
  void emptyList() {
    sentinel.addNext(sentinel);
  }

  // make an example list for string deque
  void exampleList() {
    sentinel.addNext(abcnode);
    abcnode.addNext(bcdnode);
    bcdnode.addNext(cdenode);
    cdenode.addNext(defnode);
    defnode.addNext(sentinel);
  }

  // make an empty list for book deque
  void emptyListBook() {
    sentinelb.addNext(sentinelb);
  }


  // make an example list for book deque
  void exampleListBook() {
    sentinelb.addNext(n1);
    n1.addNext(n2);
    n2.addNext(n3);
    n3.addNext(n4);
    n4.addNext(sentinelb);
  }

  // test size method
  void testSize(Tester t) {
    emptyList();
    t.checkExpect(this.deque1.size(), 0);
    exampleList();
    t.checkExpect(this.deque1.size(), 4);
    emptyListBook();
    t.checkExpect(this.deque2.size(), 0);
    exampleListBook();
    t.checkExpect(this.deque2.size(), 4);
  }

  // test addAtHead method
  void testAddAtHead(Tester t) {
    emptyList();
    this.deque1.addAtHead("tuhao");
    t.checkExpect(this.deque1.size(), 1);
    t.checkExpect(sentinel.next.data, "tuhao");
    t.checkExpect(sentinel.next.next.data, "");
    emptyListBook();
    this.deque2.addAtHead(b5);
    t.checkExpect(this.deque2.size(), 1);
    t.checkExpect(sentinelb.next.data, b5);
    t.checkExpect(sentinelb.next.next.data, null);
  }

  // test addAddTail method
  void testAddAtTail(Tester t) {
    exampleList();
    this.deque1.addAtTail("niubi");
    t.checkExpect(this.deque1.size(), 5);
    t.checkExpect(sentinel.prev.data, "niubi");
    t.checkExpect(sentinel.prev.prev.data, "def");
    exampleListBook();
    this.deque2.addAtTail(b6);
    t.checkExpect(this.deque2.size(), 5);
    t.checkExpect(sentinelb.prev.data.price, 16);
    t.checkExpect(sentinelb.prev.data, b6);

  }

  // test removeFromHead method
  void testRemoveFromHead(Tester t) {
    exampleList();
    this.deque1.removeFromHead();
    t.checkExpect(this.deque1.size(), 3);
    t.checkExpect(sentinel.next.data, "bcd");

    emptyList();
    t.checkException("testing remove From Head",
            new RuntimeException("no node at the head of an empty list"),
            this.deque1, "removeFromHead");

    exampleListBook();
    this.deque2.removeFromHead();
    t.checkExpect(this.deque2.size(), 3);
    t.checkExpect(this.sentinelb.next.data, b2);
    t.checkExpect(this.sentinelb.next.data.title, "Boston");

  }

  // test removeFromTail method
  void testRemoveFromTail(Tester t) {
    exampleList();
    this.deque1.removeFromTail();
    t.checkExpect(this.deque1.size(), 3);
    t.checkExpect(sentinel.prev.data, "cde");

    emptyList();
    t.checkException("testing remove from tail",
            new RuntimeException("no node at the tail of an empty list"),
            this.deque1, "removeFromTail");

    exampleListBook();
    this.deque2.removeFromTail();
    t.checkExpect(this.deque2.size(), 3);
    t.checkExpect(sentinelb.prev.data, b3);

    emptyListBook();
    t.checkException("testing remove from tail",
            new RuntimeException("no node at the tail of an empty list"),
            this.deque2, "removeFromTail");
  }

  // test haveT method in node
  void testHaveT(Tester t) {
    exampleListBook();
    t.checkExpect(this.n1.haveT(b1), n1);
    t.checkExpect(this.n1.haveT(b3), n3);
    t.checkExpect(this.n1.haveT(b6), sentinelb);
    emptyListBook();
    t.checkExpect(this.sentinelb.haveT(b1), this.sentinelb);
  }

  // test find method in Deque
  void testFind(Tester t) {
    exampleList();
    t.checkExpect(this.deque1.find("bcd"), bcdnode);
    t.checkExpect(this.deque1.find("cde"), sentinel.next.next.next);
    t.checkExpect(this.deque1.find("da"), sentinel);

    exampleListBook();
    t.checkExpect(this.deque2.find(b1), n1);
    t.checkExpect(this.deque2.find(b6), sentinelb);
    t.checkExpect(this.deque2.find(b2), sentinelb.next.next);

  }

  // test removeNode method
  void testRemoveNode(Tester t) {
    exampleList();
    this.deque1.removeNode(sentinel.next.next);
    t.checkExpect(this.deque1.size(), 3);
    t.checkExpect(this.sentinel.next.next.data, "cde");
    exampleListBook();
    this.deque2.removeNode(n2);
    t.checkExpect(this.deque2.size(), 3);
    t.checkExpect(this.sentinelb.next.next, n3);
    this.deque2.removeNode(sentinelb);
    t.checkExpect(this.deque2.find(b4).next, sentinelb);
  }

  void testEqual(Tester t) {
    t.checkExpect(n1.data.equals(b1), true);
    t.checkExpect(b4.equals(b3), false);
  }
}