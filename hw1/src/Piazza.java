/*
 *  assignment 1
 *  pair xxx
 *  Chao Fang
 *  bursty
 *  Haonan Zhao
 *  haonan
 *
 *  6 September 2013



               +---------------------+
               |                     |
               v                     |
           +-------+                 |
           |IPizza |                 |
           +-------+                 |
             / \                     |
             ---                     |
              |                      |
      -----------------              |
      |               |              |
+-------------+   +---------------+  |
|    Plain    |   |    Fancy      |  |
+-------------+   +---------------+  |
|String crust |   | IPizza base   |--+
|String cheese|   | String topping|
|             |   +---------------+
+-------------+

*/

/**
 * The interface of a piazza.
 */
interface IPiazza {
}

/**
 * A piazza only has crust and cheese.
 */
class Plain implements IPiazza {
  private String crust;
  private String cheese;

  /**
   * The constructor of a plain pizza.
   *
   * @param crust  the crust of a plain pizza.
   * @param cheese the cheese on a plain pizza.
   */
  Plain(String crust, String cheese) {
    this.crust = crust;
    this.cheese = cheese;
  }
}


/**
 * The class of fancy pizza.
 */
class Fancy implements IPiazza {
  private IPiazza base;
  private String topping;

  /**
   * The constructor for Fancy pizza.
   *
   * @param base    the base of fancy pizza.
   * @param topping the topping of fancy pizza.
   */
  Fancy(IPiazza base, String topping) {
    this.base = base;
    this.topping = topping;
  }
}


/**
 * The example of piazza class.
 */
class ExamplesPizza {

  IPiazza order1 = new Fancy(
          new Plain("thin crust", "mozarella"), "mushrooms and olives");

  IPiazza order2 = new Fancy(
          new Plain("deep dish", "mixed"), "pepperoni and onions");
}