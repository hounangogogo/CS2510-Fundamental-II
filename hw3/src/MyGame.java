/*
assignment 3
pair xxx
Chao Fang
bursty
Haonan Zhao
haonan
*/

import tester.*;
import javalib.impworld.*;
import javalib.colors.*;
import javalib.worldimages.*;

import java.util.*;
import java.awt.Color;

/* Game Introduction
 * The game we designed is about an airplane dodges birds.
 * Birds and airplane are represented as ovals.
 * Players should press (up down right left) buttons
 * to control the airplane fly through the gaps between birds.
 * Once the airplane hits one of the birds will lead to airplane
 * crash and game over. At the end of game,
 * it will show player how many seconds the player
 * survived until the game over.
 */

interface SkyWorldConstants {
  // a random number generator
  public Random rand = new Random();

  // the width of the world
  public int WIDTH = 800;

  // the height of the world
  public int HEIGHT = 400;

  // the color of the sky
  public Color skyColor = Color.cyan;

  // the background image of the sky
  public WorldImage skyImage =
          new RectangleImage(new Posn(WIDTH / 2, HEIGHT / 2),
                  WIDTH, HEIGHT, skyColor);
}


//To represent a location (x,y) in graphics coordinates
class CartPt extends Posn implements SkyWorldConstants {

  // the standard constructor - invokes the one in the super class
  CartPt(int x, int y) {
    super(x, y);
  }


  //Move this CartPt left i units -- or back to the right, if out of bounds
  // also move randomly up or down -2 to 2 pixel
  void moveLeftRandom(int i) {
    if (this.x - i < 0) {
      this.x = WIDTH;
      //this.y = rand.nextInt(400);  just for fun here.
    } else {
      this.x = this.x - i;
      this.y = this.y + rand.nextInt(5) - 2;
    }
  }

  // Compute the distance from this point to the given one
  double distTo(CartPt that) {
    return Math.sqrt((this.x - that.x) * (this.x - that.x) +
            (this.y - that.y) * (this.y - that.y));
  }
}

class MyPlane implements SkyWorldConstants {
  int x;
  int y;
  double seconds;

  MyPlane(int x, int y, double seconds) {
    this.x = x;
    this.y = y;
    this.seconds = seconds;
  }

  //to represent the original location of my plane
  CartPt planeLoc() {
    return new CartPt(this.x, this.y);
  }

  //to move the plane by press the given key
  void move(String ke) {
    if (ke.equals("up") && this.y > 10) {
      this.y = this.y - 25;
    } else if (ke.equals("down") && this.y < HEIGHT) {
      this.y = this.y + 25;
    } else if (ke.equals("left") && this.x > 10) {
      this.x = this.x - 30;
    } else if (ke.equals("right") && this.x < WIDTH) {
      this.x = this.x + 15;
    }
  }

  //to compute time the plane survived
  void survive() {
    this.seconds = this.seconds + 0.05;
  }

  //to represent the image of my plane
  WorldImage planeImage() {
    return new OverlayImages(new OvalImage(
            this.planeLoc(), 100, 40, new White()),
            new TextImage(this.planeLoc(),
                    "Boeing 787", new Black()));
  }
}

class Bird implements SkyWorldConstants {
  CartPt loc;

  Bird(CartPt loc) {
    this.loc = loc;
  }


  //the bird flies from right to left.
  //when it out of bounds, the bird restarts at an new
  //location on the right
  void flies() {
    this.loc.moveLeftRandom(5);
  }

  //to represent the image of this bird
  WorldImage birdImage() {
    return new OvalImage(this.loc, 50, 30, new Yellow());
  }

  //Is this bird getting closed to my plane?
  boolean closeTo(MyPlane p) {
    return this.loc.distTo(p.planeLoc()) < 31;
  }

}

interface ILoBird extends SkyWorldConstants {
  // produce the image of all birds in this list
  public WorldImage birdsImage();

  // let all birds in this list fly ahead on tick
  public void fly();

  // Is any bird in this list close to the given plane?
  public boolean crash(MyPlane that);

}

class MtLoBird implements ILoBird, SkyWorldConstants {

  //to represent a empty bird
  public WorldImage birdsImage() {
    return new OvalImage(new CartPt(0, 0), 0, 0, skyColor);
  }

  //let all birds in this list fly
  public void fly() {
    //empty void
  }

  ;

  //Is any bird in this list close to the given plane?
  public boolean crash(MyPlane that) {
    return false;
  }
}

class ConsILoBird implements ILoBird, SkyWorldConstants {
  Bird first;
  ILoBird rest;

  ConsILoBird(Bird first, ILoBird rest) {
    this.first = first;
    this.rest = rest;
  }

  //produce the image of all birds in this list
  public WorldImage birdsImage() {
    return new OverlayImages(this.first.birdImage(),
            this.rest.birdsImage());

  }

  //let all birds in this list fly ahead on tick
  public void fly() {
    this.first.flies();
    this.rest.fly();

  }

  //Is any bird in this list close to the given plane?
  public boolean crash(MyPlane that) {
    return this.first.closeTo(that) ||
            this.rest.crash(that);
  }
}

class SkyWorld extends World implements SkyWorldConstants {
  MyPlane plane;
  ILoBird birds;

  SkyWorld(MyPlane plane, ILoBird birds) {
    this.plane = plane;
    this.birds = birds;
  }

  //to represent the world on next tick
  public void onTick() {
    this.birds.fly();
    this.plane.survive();
  }

  //to represent the image of sky with plane and birds flying
  public WorldImage makeImage() {
    return new RectangleImage(new Posn(WIDTH / 2, HEIGHT / 2),
            WIDTH, HEIGHT, Color.cyan).overlayImages(
            this.plane.planeImage(), this.birds.birdsImage());
  }

  //the plane flies by the given key
  public void onKeyEvent(String ke) {
    this.plane.move(ke);
  }

  //the game ends when the plane crashes birds
  public WorldEnd worldEnds() {
    if (this.birds.crash(this.plane)) {
      return new WorldEnd(true,
              new OverlayImages(this.makeImage(),
                      new TextImage(new Posn(200, 150),
                              "Air Crash! You survived " +
                                      Double.toString(this.plane.seconds) +
                                      "seconds", new Red())));
    } else {
      return new WorldEnd(false, this.makeImage());
    }
  }
}


//Examples for game
class ExamplesSkyWorld implements SkyWorldConstants {

  CartPt p1 = new CartPt(WIDTH / 3, 35);
  CartPt p2 = new CartPt(WIDTH / 3, 70);
  CartPt p3 = new CartPt(WIDTH / 3, 105);
  CartPt p4 = new CartPt(WIDTH / 3, 140);
  CartPt p5 = new CartPt(WIDTH / 3, 175);
  CartPt p6 = new CartPt(WIDTH / 3, 210);
  CartPt p7 = new CartPt(WIDTH / 3, 245);
  CartPt p8 = new CartPt(WIDTH / 3, 280);
  CartPt p9 = new CartPt(WIDTH / 3, 315);
  CartPt p10 = new CartPt(WIDTH / 3, 350);
  CartPt p11 = new CartPt(WIDTH / 3, 385);
  Bird b1 = new Bird(p1);
  Bird b2 = new Bird(p2);
  Bird b3 = new Bird(p3);
  Bird b4 = new Bird(p4);
  Bird b5 = new Bird(p5);
  Bird b6 = new Bird(p6);
  Bird b7 = new Bird(p7);
  Bird b8 = new Bird(p8);
  Bird b9 = new Bird(p9);
  Bird b10 = new Bird(p10);
  Bird b11 = new Bird(p11);


  ILoBird nobird = new MtLoBird();
  ILoBird onebird = new ConsILoBird(this.b2, this.nobird);
  ILoBird allbird =
          new ConsILoBird(this.b1,
                  new ConsILoBird(this.b2,
                          new ConsILoBird(this.b3,
                                  new ConsILoBird(this.b4,
                                          new ConsILoBird(this.b5,
                                                  this.nobird)))));
            /*new ConsILoBird(this.b6,
            new ConsILoBird(this.b7,
            new ConsILoBird(this.b8,
            new ConsILoBird(this.b9,
            new ConsILoBird(this.b10,
            new ConsILoBird(this.b11 ,this.nobird)))))))))));

            <=  Commented because more than 80 chars.
            release them out before play.
*/

  WorldImage background = skyImage;
  MyPlane plane1 = new MyPlane(WIDTH / 2, 200, 0);
  MyPlane plane2 = new MyPlane(WIDTH / 3, 200, 0);

  SkyWorld sky = new SkyWorld(this.plane1, this.allbird);
  SkyWorld sky1 = new SkyWorld(this.plane1, this.onebird);
  SkyWorld oldsky = new SkyWorld(new MyPlane(200, 200, 0), this.allbird);

  public void testRun(Tester t) {
    // run the game
    this.sky.bigBang(WIDTH, HEIGHT, 0.05);
  }

  public static void main(String[] argv) {
    ExamplesSkyWorld e = new ExamplesSkyWorld();
    Tester.runReport(e, false, false);
  }

  void resetPlane() {
    this.plane1 = new MyPlane(WIDTH / 2, 200, 0);
  }

  void testMyPlaneMethod(Tester t) {
    t.checkExpect(this.plane1.planeLoc(), new CartPt(WIDTH / 2,
            HEIGHT / 2));

    this.plane1.move("up");
    t.checkExpect(this.plane1, new MyPlane(WIDTH / 2, HEIGHT / 2 - 25, 0));


    this.plane1.move("down");
    t.checkExpect(this.plane1, new MyPlane(WIDTH / 2, HEIGHT / 2, 0));

    this.plane1.move("left");
    t.checkExpect(this.plane1, new MyPlane(WIDTH / 2 - 30, HEIGHT / 2, 0));

    this.plane1.move("right");
    t.checkExpect(this.plane1, new MyPlane(WIDTH / 2 - 15, HEIGHT / 2, 0));

    this.plane1.survive();
    t.checkExpect(this.plane1, new MyPlane(WIDTH / 2 - 15,
            HEIGHT / 2, 0.05));

    resetPlane();
  }

  void testMoveLeftRandomMethods(Tester t) {
    CartPt pt;
    Bird bird;
    for (int i = 0; i < 100; i++) {
      pt = new CartPt(10, 20);
      pt.moveLeftRandom(5);
      t.checkRange(pt.y, 18, 23);
    }
    for (int i = 0; i < 100; i++) {
      bird = new Bird(new CartPt(10, 20));
      bird.flies();
      t.checkOneOf(bird,
              new Bird(new CartPt(5, 18)),
              new Bird(new CartPt(5, 19)),
              new Bird(new CartPt(5, 20)),
              new Bird(new CartPt(5, 21)),
              new Bird(new CartPt(5, 22)));
    }

  }

  void testBirdMethod(Tester t) {
    t.checkExpect(this.b1.closeTo(plane1), false);
  }

  void testILoBirdMethod(Tester t) {
    t.checkExpect(this.nobird.crash(this.plane1), false);
    t.checkExpect(this.allbird.crash(this.plane1), false);
    t.checkExpect(this.allbird.crash(this.plane2), true);
  }


  void testOnKeyEvent(Tester t) {

    this.sky = new SkyWorld(this.plane1, this.allbird);
    this.sky.onKeyEvent("up");
    t.checkExpect(this.sky,
            new SkyWorld(new MyPlane(WIDTH / 2, 175, 0),
                    this.allbird));


    this.sky.onKeyEvent("down");
    t.checkExpect(this.sky,
            new SkyWorld(new MyPlane(WIDTH / 2, 200, 0),
                    this.allbird));
    resetPlane();
    this.sky.onKeyEvent("left");
    t.checkExpect(this.sky,
            new SkyWorld(new MyPlane(WIDTH / 2 - 30, 200, 0),
                    this.allbird));


    this.sky.onKeyEvent("right");
    t.checkExpect(this.sky,
            new SkyWorld(new MyPlane(WIDTH / 2 - 15, 200, 0),
                    this.allbird));
  }

  void testSkyWorldEnd(Tester t) {
    t.checkExpect(this.sky.worldEnds(),
            new WorldEnd(false, this.sky.makeImage()));
    t.checkExpect(new SkyWorld(new MyPlane(WIDTH / 3, 35, 0),
            this.allbird).worldEnds(), new WorldEnd(true,
            new OverlayImages(new SkyWorld(
                    new MyPlane(WIDTH / 3, 35, 0),
                    this.allbird).makeImage(),
                    new TextImage(new Posn(200, 150),
                            "Air Crash! You survived " +
                                    Double.toString(
                                            new MyPlane(WIDTH / 3, 35, 0).seconds) +
                                    "seconds", new Red()))));

  }


}
