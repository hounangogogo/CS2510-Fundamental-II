/*
 * assignment 2
 * pair xxx
 * Chao Fang
 * bursty
 * Haonan Zhao
 * haonan
 *
 *  +-------------+--------------+
 *  |    +--------|  ExcelCells  |
 *  |    |        +--------------+
 *  |    |        | String row   |
 *  |    |        +--------------+
 *  |    |        |String column |
 *  |    |        +--------------+
 *  |    |        | IData  cell  |------+
 *  |    |        +--------------+      |
 *  |    |                              |
 *  |    |                              +
 *  |    |                      +--------------+
 *  |    |                      |    IData     |
 *  |    |                      +--------------+
 *  |    |                          /       \
 *  |    |
 *  |    |               +---------+     +--------------+
 *  |    |               |  Number |     | Formula      |
 *  |    |               +---------+     +--------------+
 *  |    |               |int  num |     |IFunctions f  |-----+
 *  |    |               |int  acc |     +--------------+     |
 *  |    |               +---------+     |ExcelCells c1 |     |
 *  |    +-------------------------------+--------------+     |
 *  +---- -------------------------------|ExcelCells c2 |     |
 *                                       +--------------+     |
 *                                                            |
 *                                             +--------------------------+
 *                                             |       IFunctions         |
 *                                             +--------------------------+
 *                                                         |
 *                                                     /   |   \
 *                                                    /    |    \
 *                                                   /     |     \
 *                                                  /      |      \
 *
 *                                 +------------+     +---------+   +-------+
 *                                 |  Addition  |     | minimum |   |Product|
 *                                 +------------+     +---------+   +-------+
 *
 */

import java.net.IDN;
import java.util.function.IntFunction;

import tester.Tester;

/**
 * Class represents data in the cells of a spreadsheet.
 */
public class ExcelCells {
  int row;
  String column;
  IData cell;

  /**
   * Constructor of a cell.
   *
   * @param row    the row of the cell.
   * @param column the column of the cell.
   * @param cell
   */
  ExcelCells(int row, String column, IData cell) {
    this.row = row;
    this.column = column;
    this.cell = cell;
  }

  int value() {
    return cell.calculateValue();
  }

  /**
   * Computes the number of cells that contain numbers (not formulas) involved in computing the
   * value of this cell.
   *
   * @return the number of cells that contains numbers.
   */
  int countArgs() {
    return cell.getNumOfArgs();
  }
}

/**
 * The interface of data in cell.
 */
interface IData {

  /**
   * Get the value of a cell.
   *
   * @return value of a cell.
   */
  int calculateValue();

  /**
   * Get the number of numbers in cell.
   *
   * @return the number of numbers.
   */
  int getNumOfArgs();
}

/**
 * Number represents the number element in data interface.
 */
class Num implements IData {
  int num;
  boolean visited;

  /**
   * Constructor of a number.
   *
   * @param num the number object.
   */
  Num(int num) {
    this.num = num;
    this.visited = false;
  }

  /**
   * Get the value of a cell.
   *
   * @return value of a cell.
   */
  @Override
  public int calculateValue() {
    return num;
  }

  /**
   * Computes the number of cells that contain numbers (not formulas) involved in computing the
   * value of this cell.
   *
   * @return the number of cells that contains numbers.
   */
  @Override
  public int getNumOfArgs() {
    if (!visited) {
      visited = true;
      return 1;
    } else {
      return 0;
    }
  }
}

/**
 * Formula represent the formula object in cell.
 */
class Formula implements IData {
  IFunctions f;
  ExcelCells c1;
  ExcelCells c2;

  /**
   * The constructor for a function.
   *
   * @param f  the function operator.
   * @param c1 the first argument.
   * @param c2 the second argument.
   */
  Formula(IFunctions f, ExcelCells c1, ExcelCells c2) {
    this.f = f;
    this.c1 = c1;
    this.c2 = c2;
  }

  /**
   * Get the value of a cell.
   *
   * @return value of a cell.
   */
  @Override
  public int calculateValue() {
    return f.compute(c1.value(), c2.value());
  }

  /**
   * Get the number of numbers in cell.
   *
   * @return the number of numbers.
   */
  @Override
  public int getNumOfArgs() {
    return c1.countArgs() + c2.countArgs();
  }
}

/**
 * The interface of functions.
 */
interface IFunctions {

  /**
   * compute the function base on formula.
   *
   * @param a the first arg.
   * @param b the second arg.
   * @return the result after calculation.
   */
  int compute(int a, int b);
}

/**
 * the addition to two given integer.
 */
class Addition implements IFunctions {

  /**
   * compute the function base on formula.
   *
   * @param a the first arg.
   * @param b the second arg.
   * @return the result after calculation.
   */
  @Override
  public int compute(int a, int b) {
    return a + b;
  }
}


/**
 * Minimum the minimum of two given integer
 */
class Minimum implements IFunctions {

  /**
   * to do the Minimum to two given integer
   *
   * @param a the first arg.
   * @param b the second arg.
   * @return the result of Minimum.
   */
  public int compute(int a, int b) {
    return Math.min(a, b);
  }
}

/**
 * Product the minimum of two given integer
 */
class Product implements IFunctions {

  /**
   * to do the Product to two given integer
   *
   * @param a the first arg.
   * @param b the second arg.
   * @return the result of multiply.
   */
  public int compute(int a, int b) {
    return a * b;
  }
}


/**
 * Example class and test class for cells.
 */
class ExamplesCells {

  ExcelCells a1 = new ExcelCells(1, "A", new Num(8));
  ExcelCells b1 = new ExcelCells(1, "B", new Num(3));
  ExcelCells c1 = new ExcelCells(1, "C", new Num(4));
  ExcelCells d1 = new ExcelCells(1, "D", new Num(6));
  ExcelCells e1 = new ExcelCells(1, "E", new Num(2));

  ExcelCells a2 = new ExcelCells(2, "A",
          new Formula(new Minimum(), this.b1, this.e1));
  ExcelCells b2 = new ExcelCells(2, "B",
          new Formula(new Addition(), this.a1, this.c1));
  ExcelCells e2 = new ExcelCells(2, "E",
          new Formula(new Product(), this.b2, this.d1));
  ExcelCells a3 = new ExcelCells(3, "A",
          new Formula(new Product(), this.a1, this.a2));
  ExcelCells b3 = new ExcelCells(3, "B",
          new Formula(new Addition(), this.b2, this.e1));
  ExcelCells e3 = new ExcelCells(3, "E",
          new Formula(new Minimum(), this.a3, this.d1));
  ExcelCells b4 = new ExcelCells(4, "B",
          new Formula(new Addition(), this.b3, this.b2));
  ExcelCells e4 = new ExcelCells(4, "E",
          new Formula(new Minimum(), this.b4, this.e3));
  ExcelCells b5 = new ExcelCells(5, "B",
          new Formula(new Product(), this.b4, this.b3));
  ExcelCells e5 = new ExcelCells(5, "E",
          new Formula(new Addition(), this.b5, this.e4));

  boolean testValue(Tester t) {
    return
            t.checkExpect(this.a1.value(), 8) &&
                    t.checkExpect(this.a2.value(), 2) &&
                    t.checkExpect(this.b2.value(), 12) &&
                    t.checkExpect(this.e2.value(), 72) &&
                    t.checkExpect(this.a3.value(), 16) &&
                    t.checkExpect(this.b3.value(), 14) &&
                    t.checkExpect(this.e3.value(), 6) &&
                    t.checkExpect(this.b4.value(), 26) &&
                    t.checkExpect(this.e4.value(), 6) &&
                    t.checkExpect(this.b5.value(), 364) &&
                    t.checkExpect(this.e5.value(), 370);
  }

  boolean testCountArgs(Tester t) {
    return
            t.checkExpect(this.b4.countArgs(), 3);
  }
}
