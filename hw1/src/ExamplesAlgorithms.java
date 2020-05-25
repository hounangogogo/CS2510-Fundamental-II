/*
 *  assignment 1
 *  pair xxx
 *  Chao Fang
 *  bursty
 *  Haonan Zhao
 *  haonan
 *
 *  6 September 2013
 */

import tester.Tester;

/**
 * a class for defining simple mathematical functions (methods)
 */
class Algorithms {

  /**
   * Compute the location of the car.
   *
   * @param startLoc    location at the start
   * @param speed       given in pixels on the screen the speed at which the car is traveling
   * @param timeElapsed given in pixels per clock tick, and the elapsed time, given in clock ticks
   * @return the final location of the car.
   */
  static int carMove(int startLoc, int speed, int timeElapsed) {
    return startLoc + speed * timeElapsed;
  }


  /**
   * Compute the height of the rocket after the next tick
   *
   * @param startLoc current height
   * @param speed    seed in pixels per clock tick
   * @return
   */
  static int nextRocket(int startLoc, int speed) {
    if (speed - startLoc >= 0) {
      return 0;
    } else {
      return startLoc - speed;
    }
  }


  /**
   * Compute the area of a circle.
   *
   * @param rad given its radius
   * @return the area of circle.
   */
  static double circleArea(int rad) {
    return Math.PI * rad * rad;
  }


  /**
   * compute the perimeter of a circle.
   *
   * @param rad given its radius
   * @return the perimeter of a circle
   */
  static double circlePerimeter(int rad) {
    return 2 * Math.PI * rad;
  }
}

/**
 * Test the algorithms.
 */
class ExamplesAlgorithms {

  // test the method carMove:
  boolean testCarLoc(Tester t) {
    return
            t.checkExpect(Algorithms.carMove(20, 5, 50), 270,
                    "starting at 20, at a speed 5, after 50 ticks:") &&
                    t.checkExpect(Algorithms.carMove(10, 3, 60), 190,
                            "starting at 10, at a speed 3, after 60 ticks:");
  }

  // test the method nextRocket:
  boolean testNextRocket(Tester t) {
    return
            t.checkExpect(Algorithms.nextRocket(100, 5), 95,
                    "starts at 100, speed 5, the rocket descends to 95:") &&
                    t.checkExpect(Algorithms.nextRocket(0, 5), 0,
                            "starts at 0, speed 5, the rocket remains at 0:") &&
                    t.checkExpect(Algorithms.nextRocket(20, 20), 0,
                            "starts at 20, speed 20, the rocket lands at 0:") &&
                    t.checkExpect(Algorithms.nextRocket(15, 20), 0,
                            "starts at 15, speed 20, the rocket lands at 0:");
  }

  //test whether two times area is nearly the same
  //as radius times the perimeter
  boolean testCircles(Tester t) {
    return
            t.checkExpect(2 * Algorithms.circleArea(5),
                    5 * Algorithms.circlePerimeter(5),
                    "the radius of circle 5, two times area is "
                            + "nearly the same as radius times the perimeter:") &&
                    t.checkExpect(2 * Algorithms.circleArea(4),
                            4 * Algorithms.circlePerimeter(4),
                            "the radius of circle 4, two times area is"
                                    + "nearly the same as radius times the perimeter:");

  }
}
