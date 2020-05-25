// assignment 5
// Guo Hong
// guohong
// Zhao Haonan
// haonan

import tester.Tester;

// to represent all examples
class ExamplesDeque {
  // make some examples
  Sentinel sentinel = new Sentinel("");
  Node abcnode = new Node("abc");
  Node bcdnode = new Node("bcd");
  Node cdenode = new Node("cde");
  Node defnode = new Node("def");

  Deque deque1 = new Deque(sentinel);

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

  // test size method
  void testSize(Tester t) {
    emptyList();
    t.checkExpect(this.deque1.size(), 0);
    exampleList();
    t.checkExpect(this.deque1.size(), 4);
  }

  // test addAtHead method
  void testAddAtHead(Tester t) {
    emptyList();
    this.deque1.addAtHead("tuhao");
    t.checkExpect(this.deque1.size(), 1);
    t.checkExpect(sentinel.next.data, "tuhao");
    t.checkExpect(sentinel.next.next.data, "");
  }

  // test addAddTail method
  void testAddAtTail(Tester t) {
    exampleList();
    this.deque1.addAtTail("niubi");
    t.checkExpect(this.deque1.size(), 5);
    t.checkExpect(sentinel.prev.data, "niubi");
    t.checkExpect(sentinel.prev.prev.data, "def");
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
  }

  // test find method in Deque
  void testFind(Tester t) {
    exampleList();
    t.checkExpect(this.deque1.find("bcd"), sentinel.next.next);
    t.checkExpect(this.deque1.find("da"), sentinel);
  }

  // test removeNode method
  void testRemoveNode(Tester t) {
    exampleList();
    this.deque1.removeNode(sentinel.next.next);
    t.checkExpect(this.deque1.size(), 3);
    t.checkExpect(this.sentinel.next.next.data, "cde");
  }
}