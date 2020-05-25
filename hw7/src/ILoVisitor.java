// A visitor for the ILo<T> classes that
// and produces the result of the type R
interface ILoVisitor<R, T> {
  // method for the empty list
  public R forMt();

  // method for the nonempty list
  public R forCons(T first, ILo<T> rest);
}

// A visitor that computes the total download time for all files 
// in the list of image files
class ILoImageDownloadTimeVisitor
        implements ILoVisitor<Integer, Image> {

  int speed;

  ILoImageDownloadTimeVisitor(int speed) {
    this.speed = speed;
  }

  // method for the empty list
  public Integer forMt() {
    return 0;
  }

  // method for the nonempty list
  public Integer forCons(Image first, ILo<Image> rest) {
    return
            first.fileSize / speed + rest.accept(this);
  }
}

/**
 * produces a list of titles of all songs in a list of songs.
 */
class ILoListOfSongTitleVisitor
        implements ILoVisitor<ILo<String>, Song> {

  @Override
  public ILo<String> forMt() {
    return new MtLo<>();
  }

  @Override
  public ILo<String> forCons(Song first, ILo<Song> rest) {
    return new ConsLo<>(first.title, rest.accept(this));
  }
}

/**
 * produces one long String that contains the titles of all books, separating the titles with the
 * new line String that is encoded as "\n".
 */
class ILoLongStringVisitor implements ILoVisitor<String, Book> {

  @Override
  public String forMt() {
    return "";
  }

  @Override
  public String forCons(Book first, ILo<Book> rest) {
    return first.title + "\n" + rest.accept(this);
  }
}
