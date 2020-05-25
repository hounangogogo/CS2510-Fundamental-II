/**
 * Represents a Book
 */
class Book {
  String title;
  String author;
  int price;     // in dollars

  /**
   * Constructor for book.
   *
   * @param title  the title of a book.
   * @param author the author of a book.
   * @param price  the price of a book.
   */
  Book(String title, String author, int price) {
    this.title = title;
    this.author = author;
    this.price = price;
  }


  /**
   * Compute the value of this Book, i.e., its price
   *
   * @return the price of the book
   */
  int value() {
    return this.price;
  }
}