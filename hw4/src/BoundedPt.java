import tester.Tester;

abstract class Posn {
  int x;
  int y;

  /**
   * Constructor for posn.
   *
   * @param x the x position.
   * @param y the y position.
   */
  Posn(int x, int y) {
    this.x = x;
    this.y = y;
  }
}

/**
 * Class define boundary.
 */
class BoundedPt extends Posn {

  int width = 600;   // the width of the canvas
  int height = 400;  // the height of the canvas

  /**
   * Constructor for BoundedPt.
   *
   * @param x the x position.
   * @param y the y position.
   */
  BoundedPt(int x, int y) {
    super(x, y);
    if (this.withIn(width, height)) {
      this.x = x;
      this.y = y;

    }

    if (x < 0) {
      throw new RuntimeException(
              "The given x coordinate was "
                      + (0 - x) + " beyond the left edge.");
    }

    if (x > width) {
      throw new RuntimeException(
              "The given x coordinate was "
                      + (x - width) + " beyond the right edge.");
    }

    if (y < 0) {
      throw new RuntimeException(
              "The given y coordinate was "
                      + (0 - y) + " beyond the top edge.");
    }

    if (y > height) {
      throw new RuntimeException(
              "The given y coordinate was "
                      + (y - height) + " beyond the bottom edge.");
    }
  }

  //to check whether the given point within the bounds
  boolean withIn(int width, int height) {
    return
            0 <= x && x <= width && 0 <= y && y <= height;
  }
}

/**
 * The example and test for BoundedPt.
 */
class ExamplesBoundedPt {
  //some Good Points
  BoundedPt p1 = new BoundedPt(4, 5);
  BoundedPt p2 = new BoundedPt(7, 7);
  BoundedPt p3 = new BoundedPt(60, 40);
  BoundedPt p4 = new BoundedPt(65, 50);
  BoundedPt p5 = new BoundedPt(70, 60);

  boolean testConstructor(Tester t) {
    return
            t.checkConstructorException(new RuntimeException(
                            "The given x coordinate was "
                                    + 100 + " beyond the right edge."),
                    "BoundedPt", 700, 100) &&
                    t.checkConstructorException(new RuntimeException(
                                    "The given x coordinate was "
                                            + 100 + " beyond the left edge."),
                            "BoundedPt", -100, 100) &&
                    t.checkConstructorException(new RuntimeException(
                                    "The given y coordinate was "
                                            + 100 + " beyond the top edge."),
                            "BoundedPt", 100, -100) &&
                    t.checkConstructorException(new RuntimeException(
                                    "The given y coordinate was "
                                            + 100 + " beyond the bottom edge."),
                            "BoundedPt", 300, 500);
  }
}
