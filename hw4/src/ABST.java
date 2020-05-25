import tester.Tester;

/**
 * Class book represents a book object.
 */
class Book {
  String title;
  String author;
  int price;

  /**
   * The constructor of a book.
   *
   * @param title  the title of a book
   * @param author the author of a book
   * @param price  the price of a book
   */
  Book(String title, String author, int price) {
    this.title = title;
    this.author = author;
    this.price = price;
  }

  /**
   * Function determine whether the given book is the same book as the current one.
   *
   * @param b the given book
   * @return whether the two book are the same book.
   */
  boolean sameBook(Book b) {
    return b.price == price &&
            b.author.equals(author) &&
            b.title.equals(title);
  }
}

/**
 * interface book comparator compare book base on different way.
 */
interface IBookComparator {

  /**
   * Method compare two books.
   *
   * @param b1 the first book
   * @param b2 the second book
   * @return order of the book
   */
  int compare(Book b1, Book b2);
}

/**
 * Compare two books by title.
 */
class BooksByTitle implements IBookComparator {

  /**
   * Method compare two books.
   *
   * @param b1 the first book
   * @param b2 the second book
   * @return order of the book
   */
  @Override
  public int compare(Book b1, Book b2) {
    return b1.title.compareTo(b2.title);
  }
}

class BooksByAuthor implements IBookComparator {

  /**
   * Method compare two books.
   *
   * @param b1 the first book
   * @param b2 the second book
   * @return order of the book
   */
  @Override
  public int compare(Book b1, Book b2) {
    return b1.author.compareTo(b2.author);
  }
}

class BooksByPrice implements IBookComparator {

  /**
   * Method compare two books.
   *
   * @param b1 the first book
   * @param b2 the second book
   * @return order of the book
   */
  @Override
  public int compare(Book b1, Book b2) {
    return b1.price - b2.price;
  }
}

abstract class ABST {
  IBookComparator comp;

  /**
   * The constructor of ABST.
   *
   * @param comp the comparator.
   */
  ABST(IBookComparator comp) {
    this.comp = comp;
  }

  /**
   * Produces a new binary search tree with the given item inserted in the correct place.
   *
   * @param b new book need to be inserted
   * @return a new binary search tree.
   */
  public abstract ABST insert(Book b);

  /**
   * get the first book in tree.
   *
   * @return the first book in tree.
   */
  public abstract Book getFirst();

  /**
   * Determine the given node is leaf or not.
   *
   * @return if leaf or not.
   */
  public abstract boolean isLeaf();


  /**
   * Produces a new binary search tree with the first Book removed.
   *
   * @return a new binary search tree with the first Book removed.
   */
  public abstract ABST getRest();

  /**
   * Function determine whether the given tree is the same tree as current one.
   *
   * @param t given tree.
   * @return whether the given tree is the same tree as the given one.
   */
  public abstract boolean sameTree(ABST t);

  /**
   * Get book from tree.
   */
  public abstract Book getBook();

  /**
   * Get left tree.
   *
   * @return left tree.
   */
  public abstract ABST getLeft();


  /**
   * Get right tree.
   *
   * @return left tree.
   */
  public abstract ABST getRight();

  /**
   * whether two tree have same data.
   *
   * @param t another tree.
   * @return whether two tree have same data.
   */
  public abstract boolean sameData(ABST t);

  /**
   * Function check whether current list contains given book b.
   *
   * @param b book
   * @return whether this tree contains book b.
   */
  public abstract boolean contains(Book b);

  /**
   * Determine the tree is same as list or not.
   *
   * @param lob list of book.
   * @return whether the tree is the same as list of book.
   */
  public abstract boolean sameAsList(ILoBook lob);

  /**
   * Build a list base on the given ABST.
   *
   * @return
   */
  public abstract ILoBook buildList1();

  /**
   * Consumes a list of books and adds to it one at a time all items from this tree in the sorted
   * order.
   *
   * @return list of book.
   */
  public abstract ILoBook buildList(ILoBook lob);
}

/**
 * Leaf represents empty node.
 */
class Leaf extends ABST {

  /**
   * The constructor of ABST.
   *
   * @param comp the comparator.
   */
  Leaf(IBookComparator comp) {
    super(comp);
  }

  /**
   * Produces a new binary search tree with the given item inserted in the correct place.
   *
   * @param b new book need to be inserted
   * @return a new binary search tree.
   */
  @Override
  public ABST insert(Book b) {
    return new Node(comp, b, this, this);
  }

  /**
   * get the first book in tree.
   *
   * @return the first book in tree.
   */
  @Override
  public Book getFirst() {
    throw new RuntimeException("No first book in an empty tree");
  }

  /**
   * Determine the given node is leaf or not.
   *
   * @return if leaf or not.
   */
  @Override
  public boolean isLeaf() {
    return true;
  }

  /**
   * Produces a new binary search tree with the first Book removed.
   *
   * @return a new binary search tree with the first Book removed.
   */
  @Override
  public ABST getRest() {
    throw new RuntimeException("No rest of an empty tree");
  }

  /**
   * Function determine whether the given tree is the same tree as current one.
   *
   * @param t given tree.
   * @return whether the given tree is the same tree as the given one.
   */
  @Override
  public boolean sameTree(ABST t) {
    if (t.isLeaf()) {
      return comp.equals(t.comp);
    } else {
      return false;
    }
  }

  /**
   * Get book from tree.
   */
  @Override
  public Book getBook() {
    throw new RuntimeException("No data of an empty tree");
  }

  /**
   * Get left tree.
   *
   * @return left tree.
   */
  @Override
  public ABST getLeft() {
    throw new RuntimeException("No node of an empty tree");
  }

  /**
   * Get right tree.
   *
   * @return left tree.
   */
  @Override
  public ABST getRight() {
    throw new RuntimeException("No node of an empty tree");
  }

  /**
   * whether two tree have same data.
   *
   * @param t another tree.
   * @return whether two tree have same data.
   */
  @Override
  public boolean sameData(ABST t) {
    return true;
  }

  /**
   * Function check whether current list contains given book b.
   *
   * @param b book
   * @return whether this tree contains book b.
   */
  @Override
  public boolean contains(Book b) {
    return false;
  }

  /**
   * Determine the tree is same as list or not.
   *
   * @param lob list of book.
   * @return whether the tree is the same as list of book.
   */
  @Override
  public boolean sameAsList(ILoBook lob) {
    return lob.isEmpty();
  }

  /**
   * Build a list base on the given ABST.
   *
   * @return
   */
  @Override
  public ILoBook buildList1() {
    return new MtLoBook();
  }

  /**
   * Consumes a list of books and adds to it one at a time all items from this tree in the sorted
   * order.
   *
   * @param lob list
   * @return list of book.
   */
  @Override
  public ILoBook buildList(ILoBook lob) {
    return lob;
  }
}

/**
 * Represents a node with value in it.
 */
class Node extends ABST {
  Book book;
  ABST left;
  ABST right;

  Node(IBookComparator comp, Book book, ABST left, ABST right) {
    super(comp);
    this.book = book;
    this.left = left;
    this.right = right;
  }

  /**
   * The constructor of ABST.
   *
   * @param comp the comparator.
   */
  Node(IBookComparator comp) {
    super(comp);
  }

  /**
   * Produces a new binary search tree with the given item inserted in the correct place.
   *
   * @param b new book need to be inserted
   * @return a new binary search tree.
   */
  @Override
  public ABST insert(Book b) {
    if (comp.compare(book, b) >= 0) {
      return new Node(comp, book, left.insert(b), right);
    } else {
      return new Node(comp, book, left, right.insert(b));
    }
  }

  /**
   * get the first book in tree.
   *
   * @return the first book in tree.
   */
  @Override
  public Book getFirst() {
    if (left.isLeaf()) {
      return book;
    } else {
      return left.getFirst();
    }
  }

  /**
   * Determine the given node is leaf or not.
   *
   * @return if leaf or not.
   */
  @Override
  public boolean isLeaf() {
    return false;
  }

  /**
   * Produces a new binary search tree with the first Book removed.
   *
   * @return a new binary search tree with the first Book removed.
   */
  @Override
  public ABST getRest() {
    if (book.sameBook(this.getFirst())) {
      return this.right;
    } else {
      return new Node(comp, book, left.getRest(), right);
    }
  }

  /**
   * Function determine whether the given tree is the same tree as current one.
   *
   * @param t given tree.
   * @return whether the given tree is the same tree as the given one.
   */
  @Override
  public boolean sameTree(ABST t) {
    if (t.isLeaf()) {
      return false;
    } else {
      return book.sameBook(t.getBook()) &&
              this.left.sameTree(t.getLeft()) &&
              this.right.sameTree(t.getRight());

    }
  }

  /**
   * Get book from tree.
   */
  @Override
  public Book getBook() {
    return book;
  }

  /**
   * Get left tree.
   *
   * @return left tree.
   */
  @Override
  public ABST getLeft() {
    return left;
  }

  /**
   * Get right tree.
   *
   * @return left tree.
   */
  @Override
  public ABST getRight() {
    return right;
  }


  /**
   * whether two tree have same data.
   *
   * @param t another tree.
   * @return whether two tree have same data.
   */
  @Override
  public boolean sameData(ABST t) {
    if (t.contains(book)) {
      return this.left.sameData(t) &&
              this.right.sameData(t);
    } else {
      return false;
    }
  }


  /**
   * Function check whether current list contains given book b.
   *
   * @param b book
   * @return whether this tree contains book b.
   */
  @Override
  public boolean contains(Book b) {
    if (book.sameBook(b)) {
      return true;
    } else {
      return left.contains(b) || right.contains(b);
    }
  }

  /**
   * Determine the tree is same as list or not.
   *
   * @param lob list of book.
   * @return whether the tree is the same as list of book.
   */
  @Override
  public boolean sameAsList(ILoBook lob) {
    return this.buildList1().sameList(lob);
  }

  /**
   * Build a list base on the given ABST.
   *
   * @return list of book.
   */
  @Override
  public ILoBook buildList1() {
    return new ConsLoBook(this.getFirst(),
            this.getRest().buildList1());
  }

  /**
   * Consumes a list of books and adds to it one at a time all items from this tree in the sorted
   * order.
   *
   * @param lob list of book
   * @return list of book.
   */
  @Override
  public ILoBook buildList(ILoBook lob) {
    return this.getRest().buildList(new ConsLoBook(this.getFirst(), lob));
  }
}


/**
 * interface of list of book.
 */
interface ILoBook {

  /**
   * Check whether the given list is empty.
   *
   * @return list is empty or not.
   */
  boolean isEmpty();

  /**
   * Get the first book from list.
   *
   * @return the first book from list.
   */
  Book getFirst();

  /**
   * Get the rest books from list.
   *
   * @return the rest book from list.
   */
  ILoBook getRest();


  /**
   * Determine whether two list are the same.
   *
   * @param bl the other list.
   * @return two lists are same or not.
   */
  boolean sameList(ILoBook bl);

  /**
   * Build a tree from given list.
   *
   * @param tree given tree.
   * @return a binary search tree.
   */
  ABST buildTree(ABST tree);
}

/**
 * Class represents an empty list of book
 */
class MtLoBook implements ILoBook {

  /**
   * Check whether the given list is empty.
   *
   * @return list is empty or not.
   */
  @Override
  public boolean isEmpty() {
    return true;
  }

  /**
   * Get the first book from list.
   *
   * @return the first book from list.
   */
  @Override
  public Book getFirst() {
    throw new RuntimeException("No book in an empty list");
  }

  /**
   * Get the rest books from list.
   *
   * @return the rest book from list.
   */
  @Override
  public ILoBook getRest() {
    throw new RuntimeException("No books in an empty list");
  }

  /**
   * Determine whether two list are the same.
   *
   * @param bl the other list.
   * @return two lists are same or not.
   */
  @Override
  public boolean sameList(ILoBook bl) {
    return bl.isEmpty();
  }

  /**
   * Build a tree from given list.
   *
   * @param tree given tree.
   * @return a binary search tree.
   */
  @Override
  public ABST buildTree(ABST tree) {
    return tree;
  }
}

/**
 * Class represent list of book.
 */
class ConsLoBook implements ILoBook {
  Book first;
  ILoBook rest;

  /**
   * Constructor of ConsLoBook.
   *
   * @param first the first element in list.
   * @param rest  the rest book in list.
   */
  ConsLoBook(Book first, ILoBook rest) {
    this.first = first;
    this.rest = rest;
  }

  /**
   * Check whether the given list is empty.
   *
   * @return list is empty or not.
   */
  @Override
  public boolean isEmpty() {
    return false;
  }

  /**
   * Get the first book from list.
   *
   * @return the first book from list.
   */
  @Override
  public Book getFirst() {
    return first;
  }

  /**
   * Get the rest books from list.
   *
   * @return the rest book from list.
   */
  @Override
  public ILoBook getRest() {
    return rest;
  }

  /**
   * Determine whether two list are the same.
   *
   * @param bl the other list.
   * @return two lists are same or not.
   */
  @Override
  public boolean sameList(ILoBook bl) {
    return this.first.sameBook(bl.getFirst()) &&
            this.rest.sameList(bl.getRest());
  }

  /**
   * Build a tree from given list.
   *
   * @param tree given tree.
   * @return a binary search tree.
   */
  @Override
  public ABST buildTree(ABST tree) {
    return rest.buildTree(tree.insert(first));
  }


}


// to show the examples
class ExamplesABST {

  ABST leaf1 = new Leaf(new BooksByTitle());

  ABST leaf2 = new Leaf(new BooksByAuthor());

  ABST leaf3 = new Leaf(new BooksByPrice());

  ILoBook empty = new MtLoBook();

  Book b1 = new Book("A", "Tom", 150);
  Book b2 = new Book("B", "Clark", 70);
  Book b3 = new Book("C", "Bob", 100);
  Book b4 = new Book("D", "Tom", 80);
  Book b5 = new Book("E", "Sam", 200);
  Book b6 = new Book("", "Sam", 200);
  IBookComparator byTitle = new BooksByTitle();
  IBookComparator byAuthor = new BooksByAuthor();
  IBookComparator byPrice = new BooksByPrice();


  ILoBook lob1 = new ConsLoBook(this.b2, this.empty);
  ILoBook lob2 = new ConsLoBook(this.b4, this.empty);
  ILoBook lob3 = new ConsLoBook(this.b1, this.lob1);
  ILoBook lob4 = new ConsLoBook(this.b1, new ConsLoBook(this.b2,
          new ConsLoBook(this.b3, new ConsLoBook(this.b4, this.empty))));
  ILoBook lob5 = new ConsLoBook(this.b1, new ConsLoBook(this.b3,
          new ConsLoBook(this.b4, new ConsLoBook(this.b2, this.empty))));
  ILoBook lob6 = new ConsLoBook(this.b5, this.empty);
  ABST node12 = new Node(this.byTitle, this.b5, this.leaf1, this.leaf1);

  ABST nodeauthor1 = new Node(this.byAuthor, this.b1, this.leaf2, this.leaf2);
  ABST nodeprice1 = new Node(this.byPrice, this.b5, this.leaf3, this.leaf3);

  ABST node1 = new Node(this.byTitle, this.b2, this.leaf1, this.leaf1);
  ABST node2 = new Node(this.byTitle, this.b4, this.leaf1, this.leaf1);
  ABST node2plus5 = new Node(this.byTitle, this.b4, this.leaf1, this.node12);
  ABST node3 = new Node(this.byTitle, this.b1, this.leaf1, node1);
  ABST node4 = new Node(this.byTitle, this.b3, this.node3, this.node2);
  ABST node4plus5 = new Node(this.byTitle, this.b3, this.node3,
          this.node2plus5);

  ABST node5 = new Node(this.byTitle, this.b3, this.node1, this.node2);
  ABST node6 = new Node(this.byTitle, this.b3, this.node3, this.node2);
  ABST node7 = new Node(this.byPrice, this.b3, this.node2, this.node3);

  ABST node11 = new Node(this.byTitle, this.b4, this.leaf1, this.leaf1);
  ABST node10 = new Node(this.byTitle, this.b2, this.leaf1, this.leaf1);
  ABST node9 = new Node(this.byTitle, this.b3, this.node10, this.node11);
  ABST node8 = new Node(this.byTitle, this.b1, this.leaf1, this.node9);


  ABST nodet = new Node(this.byTitle, this.b1, this.leaf1,
          new Node(this.byTitle, this.b2, this.leaf1,
                  new Node(this.byTitle, this.b5, this.leaf1, this.leaf1)));
  ABST nodet2 = new Node(this.byTitle, this.b1,
          new Node(this.byTitle, this.b6, this.leaf1, this.leaf1),
          this.node1);

  boolean testInsert(Tester t) {
    return
            t.checkExpect(this.node3.insert(this.b5),
                    this.nodet) &&
                    t.checkExpect(this.node3.insert(this.b6),
                            this.nodet2) &&
                    t.checkExpect(this.nodeauthor1.insert(this.b2),
                            new Node(this.byAuthor, this.b1,
                                    new Node(this.byAuthor,
                                            this.b2, this.leaf2, this.leaf2),
                                    this.leaf2)) &&
                    t.checkExpect(this.nodeprice1.insert(this.b1),
                            new Node(this.byPrice, this.b5,
                                    new Node(this.byPrice,
                                            this.b1, this.leaf3, this.leaf3),
                                    this.leaf3));
  }

  boolean testGetFirst(Tester t) {
    return
            t.checkExpect(this.node4.getFirst(), this.b1);

  }

  boolean testGetRest(Tester t) {
    return
            t.checkExpect(this.node4.getRest(),
                    this.node5) &&
                    t.checkExpect(this.node1.getRest(), leaf1);

  }

  boolean testException(Tester t) {
    return
            t.checkException("Test for invalid getrest",
                    new RuntimeException("No rest of an empty tree"),
                    this.leaf1,
                    "getRest") &&
                    t.checkException("Test for invalid getFirst",
                            new RuntimeException("No first book in an empty tree"),
                            this.leaf1,
                            "getFirst") &&
                    t.checkException("Test for invalid getleft",
                            new RuntimeException("No node of an empty tree"),
                            this.leaf1,
                            "getLeft") &&
                    t.checkException("Test for invalid getright",
                            new RuntimeException("No node of an empty tree"),
                            this.leaf1,
                            "getRight");

  }

  boolean testSameTree(Tester t) {
    return
            t.checkExpect(this.node4.sameTree(this.node6), true) &&
                    t.checkExpect(this.node4.sameTree(this.node5), false) &&
                    t.checkExpect(this.leaf1.sameTree(this.leaf2), false) &&
                    t.checkExpect(this.node4.sameTree(this.leaf1), false) &&
                    t.checkExpect(leaf1.sameTree(leaf1), true);

  }

  boolean testContains(Tester t) {
    return
            t.checkExpect(this.node4.contains(b4), true) &&
                    t.checkExpect(this.node4.contains(b6), false);
  }

  boolean testSameData(Tester t) {
    return
            t.checkExpect(this.node4.sameData(this.node6), true) &&
                    t.checkExpect(this.node4.sameData(this.node5), false) &&
                    t.checkExpect(this.node4.sameData(this.node8), true);
  }


  boolean testBuildList1(Tester t) {
    return
            t.checkExpect(this.node4.buildList1(), this.lob4) &&
                    t.checkExpect(this.node3.buildList1(), this.lob3) &&
                    t.checkExpect(this.node8.buildList1(), this.lob4) &&
                    t.checkExpect(this.leaf1.buildList1(), this.empty);
  }

  boolean testSameAsList(Tester t) {
    return
            t.checkExpect(this.node4.sameAsList(this.lob4), true) &&
                    t.checkExpect(this.node3.sameAsList(this.lob3), true) &&
                    t.checkExpect(this.node8.sameAsList(this.lob4), true) &&
                    t.checkExpect(this.leaf1.sameAsList(this.lob4), false) &&
                    t.checkExpect(this.leaf1.sameAsList(this.empty), true) &&
                    t.checkExpect(this.node1.sameAsList(this.lob1), true);
  }

  boolean testBuildTree(Tester t) {
    return
            t.checkExpect(this.lob5.buildTree(this.leaf1), this.node8) &&
                    t.checkExpect(this.lob6.buildTree(node4), this.node4plus5);
  }

  boolean testBuildList(Tester t) {
    return
            t.checkExpect(this.node4.buildList(this.empty),
                    new ConsLoBook(this.b4, new ConsLoBook(this.b3,
                            new ConsLoBook(
                                    this.b2, new ConsLoBook(
                                    this.b1,
                                    this.empty)))));
  }
}
