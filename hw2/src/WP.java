/**
 * assignment 2 pair xxx Chao Fang bursty Haonan Zhao haonan
 */



/*
 *                       +----------------------+
 *                       |          WP          |
 *                       +----------------------+
 *                       | String       url     |
 *                       +----------------------+
 *                       |    String   title    |
 *                       +----------------------+
 *             +---------|    ILoIItem  Item     |
 *             |         +----------------------+
 *             |
 *             |
 *        +------------+
 *        |  ILoIItem   |
 *        +------------+
 *              |
 *             / \
 *            /   \
 *  +---------+   +------------+
 *  |MtLoIItem |  | ConsLoIItem|
 *  +---------+   +------------+
 *                |first  Item | ---------------+
 *                +------------+                |
 *                |rest ILoIItem|                |
 *                +------------+                |
 *                                              |
 *                                    +-------------------+
 *                                    |        IItem      |
 *                                    +-------------------+
 *                                             |
 *                             +---------------+--------------+
 *                             |               |              |
 *                             |               |              |
 *                +---------------+    +---------------+      |
 *                |    Text       |    |    Image      |   +------------+
 *                +---------------+    +---------------+   |   Link     |
 *                |String contents|    |String fileName|   +------------+
 *                +---------------+    +---------------+   |Stirng name |
 *                                     |    int size   |   +------------+
 *                                     +---------------+   |  WP  page  |
 *                                     |String fileType|   +------------+
 *                                     +---------------+
 *
 *
 *
 *
 */

import tester.Tester;

/**
 * Class WP describes the contents of a web site
 */
class WP {
  String url;
  String title;
  ILoItem items;

  /**
   * The constructor for a web page.
   *
   * @param url   the url of the web page.
   * @param title the title of the web page.
   * @param items the item in this web page.
   */
  WP(String url, String title, ILoItem items) {
    this.url = url;
    this.title = title;
    this.items = items;
  }

  /**
   * Computes the total size of all images in this web page and all web pages that are linked to
   * it.
   *
   * @return the total size of all images in this web page.
   */
  int totalImageSize() {
    return items.getTotalImageSize();
  }

  /**
   * computer the number of letters in all text that appears on the web site starting at this web
   * page. This includes the contents of the text, the names of the image files plus the file type
   * (but not the typical dot that is used in the full file name), the labels for links, and the
   * titles of the web pages.
   *
   * @return the text length of the web page.
   */
  int textLength() {
    return title.length() + items.getTotalTextLength();
  }

  /**
   * Produces one String that has in it all names of images on this web site, given with their file
   * types, and separated by comma and space.
   *
   * @return the String of images.
   */
  String images() {
    String s = items.getAllImages();
    return s.substring(0, s.length() - 1);
  }
}

/**
 * The interface of items.
 */
interface Item {

  /**
   * Get the size of an image.
   *
   * @return the size of an image.
   */
  int getImageSize();


  /**
   * Get the length of text.
   *
   * @return the length of text.
   */
  int getTextLength();

  /**
   * Get the image info.
   *
   * @return image info
   */
  String imageInfo();
}

/**
 * The text class.
 */
class Text implements Item {
  String contents;

  /**
   * The constructor of text class.
   *
   * @param contents the content in this text.
   */
  Text(String contents) {
    this.contents = contents;
  }

  /**
   * Get the size of an image.
   *
   * @return the size of an image.
   */
  @Override
  public int getImageSize() {
    return 0;
  }

  /**
   * Get the length of text.
   *
   * @return the length of text.
   */
  @Override
  public int getTextLength() {
    return contents.length();
  }

  /**
   * Get the image info.
   *
   * @return image info
   */
  @Override
  public String imageInfo() {
    return "";
  }
}

/**
 * The image class.
 */
class Image implements Item {
  String fileName;
  int size;
  String fileType;

  /**
   * The constructor of an image.
   */
  Image(String fileName, int size, String fileType) {
    this.fileName = fileName;
    this.size = size;
    this.fileType = fileType;
  }

  /**
   * Get the size of an image.
   *
   * @return the size of an image.
   */
  @Override
  public int getImageSize() {
    return size;
  }

  /**
   * Get the length of text.
   *
   * @return the length of text.
   */
  @Override
  public int getTextLength() {
    return fileName.length() + fileType.length();
  }

  /**
   * Get the image info.
   *
   * @return image info
   */
  @Override
  public String imageInfo() {
    String comma = ", ";
    String dot = ".";
    return fileName + dot + fileType + comma;
  }
}


/**
 * The link class.
 */
class Link implements Item {
  String name;
  WP page;

  /**
   * The constructor for an link.
   *
   * @param name the name of the link.
   * @param page the web page link to it.
   */
  Link(String name, WP page) {
    this.name = name;
    this.page = page;
  }

  /**
   * Get the size of an image.
   *
   * @return the size of an image.
   */
  @Override
  public int getImageSize() {
    return page.totalImageSize();
  }

  /**
   * Get the length of text.
   *
   * @return the length of text.
   */
  @Override
  public int getTextLength() {
    return name.length() + page.textLength();
  }

  /**
   * Get the image info.
   *
   * @return image info
   */
  @Override
  public String imageInfo() {
    return page.images();
  }
}

/**
 * The interface of list of items.
 */
interface ILoItem {
  /**
   * Function get the total image size.
   *
   * @return the size of all images.
   */
  int getTotalImageSize();

  /**
   * Function get the total text length.
   *
   * @return the text length.
   */
  int getTotalTextLength();

  /**
   * Get all images of a list.
   *
   * @return all images of a list.
   */
  String getAllImages();
}

/**
 * MtLoItem represent an list of empty item.
 */
class MtLoItem implements ILoItem {

  /**
   * Function get the total image size.
   *
   * @return the size of all images.
   */
  @Override
  public int getTotalImageSize() {
    return 0;
  }

  /**
   * Function get the total text length.
   *
   * @return the text length.
   */
  @Override
  public int getTotalTextLength() {
    return 0;
  }

  /**
   * Get all images of a list.
   *
   * @return all images of a list.
   */
  @Override
  public String getAllImages() {
    return "";
  }
}

/**
 * ConsLoItem implements
 */
class ConsLoItem implements ILoItem {
  Item first;
  ILoItem rest;

  /**
   * The constructor of ConsLoItem.
   *
   * @param first the first item in the list.
   * @param rest  the rest items.
   */
  ConsLoItem(Item first, ILoItem rest) {
    this.first = first;
    this.rest = rest;
  }

  /**
   * Function get the total image size.
   *
   * @return the size of all images.
   */
  @Override
  public int getTotalImageSize() {
    return first.getImageSize() +
            rest.getTotalImageSize();
  }

  /**
   * Function get the total text length.
   *
   * @return the text length.
   */
  @Override
  public int getTotalTextLength() {
    return first.getTextLength() + rest.getTotalTextLength();
  }

  /**
   * Get all images of a list.
   *
   * @return all images of a list.
   */
  @Override
  public String getAllImages() {
    return first.imageInfo() + rest.getAllImages();
  }
}

/**
 * The examples and tests for classes and methods.
 */
class ExamplesWP {

  private ILoItem empty = new MtLoItem();
  private Item text1 = new Text("This is Annie");
  private Item image1 = new Image("annie", 230, "jpeg");
  private Item text2 = new Text("This is Kevin");
  private Item image2 = new Image("kevin", 400, "png");
  private Item text3 = new Text("My friend Jackie");
  private Item image3 = new Image("jackie", 300, "png");

  private WP wp2 = new WP("bob-friends.org", "Bob's Friends",
          new ConsLoItem(this.text3, new ConsLoItem(
                  this.image3, this.empty)));
  private Item link1 = new Link("Here are Bob's friends", this.wp2);
  private WP myWP = new WP("myfriends.org", "My Friends",
          new ConsLoItem(this.text1, new ConsLoItem(
                  this.image1, new ConsLoItem(
                  this.text2, new ConsLoItem(
                  this.image2, new ConsLoItem(
                  this.link1, this.empty))))));

  boolean testTitleLength(Tester t) {
    return
            t.checkExpect(this.myWP.title.length(), 10);
  }

  boolean testTotalImageSize(Tester t) {
    return
            t.checkExpect(this.myWP.totalImageSize(), 930) &&
                    t.checkExpect(this.wp2.totalImageSize(), 300);
  }

  boolean testTextLength(Tester t) {
    return
            t.checkExpect(this.myWP.textLength(), 113);
  }

  boolean testImages(Tester t) {
    return
            t.checkExpect(this.myWP.images(),
                    "annie.jpeg, kevin.png, jackie.png");


  }


}