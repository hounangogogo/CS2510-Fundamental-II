
interface ISelect<T> {
  // a predicate that determines the properties of the given item
  public boolean select(T t);
}

// select only the books that are cheaper than the given limit
class CheapBook implements ISelect<Book> {
  int limit;

  CheapBook(int limit) {
    this.limit = limit;
  }

  public boolean select(Book b) {
    return b.price < this.limit;
  }
}
