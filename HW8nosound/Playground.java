// assignment 8
// Guo Hong
// guohong
// Zhao Haonan
// haonan

import java.awt.Color;
import java.util.ArrayList;

import javalib.*;
import javalib.impworld.World;
import javalib.worldimages.DiskImage;
import javalib.worldimages.Posn;
import javalib.worldimages.RectangleImage;


import javalib.worldimages.TextImage;
import javalib.worldimages.WorldEnd;
import javalib.worldimages.WorldImage;

/**
 * To represent the playground
 * 
 * @author Hong
 */
public class Playground extends World implements FrogConstant {


    Frog frog;
    Integer score;
    Integer lives;
    ArrayList<FrogKiller> frogKillers = new ArrayList<FrogKiller>();
    ArrayList<Floater> floaters = new ArrayList<Floater>();

    Playground(Frog frog, Integer score, Integer lives) {
        this.frog = frog;
        this.score = score;
        this.lives = lives;
        // adding Frogkiller to playground
        initFrogKiller();
        // adding Floaters to playground
        initFolaters();
    }

    /** To initialize FrogKillers */
    private void initFrogKiller() {
        int currentX; // the current X location
        int interval; // the interval between FrogKillers
        int velocityX; // the velocity at X direction
        int locY; // the current Y location
        boolean isCar = true;

        // first lane with tank ;
        currentX = 0;
        interval = 400;
        locY = height - 80;
        velocityX = 8;
        while (currentX < width) {
            // adding Tank
            this.frogKillers
                    .add(new Tank(new CartPt(currentX, locY), velocityX));
            currentX = currentX + interval;
        }

        // second lane with FireTruck
        currentX = 0;
        interval = 200;
        locY = locY - 55;
        velocityX = 10;
        // adding one FireTruck
        this.frogKillers.add(new FireTruck(new CartPt(currentX, locY),
                velocityX));

        // third lane with Zombies;
        currentX = 0;
        interval = 400;
        locY = locY - 55;
        velocityX = -10;
        while (currentX < width) {
            // adding Zombies
            this.frogKillers.add(new Zombie(new CartPt(currentX, locY),
                    velocityX));
            currentX = currentX + interval;
        }

        // fourth lane to produce alternative SchoolBus and zombie motorcycles;
        currentX = 0;
        interval = 300;
        locY = locY - 55;
        velocityX = -10;
        while (currentX < width) {
            // adding SchoolBus and zombie motorcycles
            if (isCar)
                this.frogKillers.add(new SchoolBus(new CartPt(currentX, locY),
                        velocityX));
            else
                this.frogKillers.add(new Tank(new CartPt(currentX, locY),
                        velocityX));
            currentX = currentX + interval;
            isCar = !isCar;
        }

    }

    /** To initialize Floaters */
    private void initFolaters() {
        int currentX; // Current X location
        int interval; // the interval between floaters
        int velocityX; // the velocity at x direction
        int locY; // current Y position
        boolean isLeaf = true;

        // first lane of the river;
        currentX = 0;
        interval = 300;
        locY = 53 + 53 / 2;
        velocityX = 10;

        while (currentX < width) {
            // adding LilyPad as Floater
            this.floaters
                    .add(new LilyPad(new CartPt(currentX, locY), velocityX));
            currentX = currentX + interval;
        }

        // second lane of the river;
        currentX = 0;
        interval = 100;
        locY = locY + 53;
        velocityX = 15;
        // to produce alternative logs and shark
        while (currentX < width) {
            if (isLeaf)
                this.frogKillers.add(new Shark(new CartPt(currentX, locY),
                        velocityX));
            else
                this.floaters
                        .add(new Log(new CartPt(currentX, locY), velocityX));
            isLeaf = !isLeaf;
            currentX = currentX + interval;
        }

        // third lane;
        currentX = 0;
        interval = 500;
        locY = locY + 53;
        velocityX = -10;

        while (currentX < width) {
            this.floaters.add(new Log(new CartPt(currentX, locY), velocityX));
            currentX = currentX + interval;
        }
    }

    /**
     * To generate the background of this playground
     * 
     * @return <code>WorldImage</code> the image of the background
     */
    private WorldImage background() {

        return // background color
        new RectangleImage(
                new Posn(width / 2, height / 2), width, height,
                Color.getHSBColor(90, 170, 100))

                // score and lives
                .overlayImages(
                        new TextImage(new Posn(width - 125, 20), "Score: "
                                + Integer.toString(this.score) + " | Lifes: "
                                + Integer.toString(this.lives), 20, 3,
                                Color.black))
                // road
                .overlayImages(
                        new RectangleImage(new Posn(width / 2, 
                                height - 50 * 3
                                - 5 * 2), width, 
                                50 * 4 + 5 * 3, Color.darkGray))
                .overlayImages(
                        new RectangleImage(new Posn(width / 2, 
                                height - 50 * 3
                                - 5 * 2 - 2), 
                                width, 2, Color.orange))
                .overlayImages(
                        new RectangleImage(new Posn(
                                width / 2, height - 50 * 3
                                - 5 * 2 + 2), width, 2, Color.orange))
                .overlayImages(
                        this.makeDashedLines(height - 50 * 2 - 5 - 3, 20))
                .overlayImages(
                        this.makeDashedLines(height - 50 * 4 - 15 + 3, 20)
                                .overlayImages(
                                        new RectangleImage(
                                                new Posn(width / 2,
                                                height - 50 - 3), width, 2,
                                                Color.white)
                                                .overlayImages(
                                                        new RectangleImage(
                                                        new Posn(width / 2,
                                                                height - 50 * 5
                                                                        - 5 * 4
                                                                        + 3),
                                                        width, 2, 
                                                        Color.white))))
                // river
                .overlayImages(
                        new RectangleImage(new Posn(width / 2,
                                53 + (3 * 53) / 2), width, 3 * 53, Color.blue));
    }

    /**
     * To draw dashed line for the road at the given y
     * 
     * @param y
     *            the y coordinate of the dashed line
     * @param dashLength
     * @return <code>WorldImage</code> the image of the dashed line for the road
     */
    private WorldImage makeDashedLines(int y, int dashLength) {
        int interval = 50; // the interval between dashes
        WorldImage currentImage = new DiskImage(new Posn(0, 0), 0,
                Color.darkGray);
        int currentX = 0;
        while (currentX < width) {
            currentImage = currentImage.overlayImages(new RectangleImage(
                    new Posn(currentX, y), dashLength, 2, Color.white));
            currentX = currentX + interval;
        }
        return currentImage;
    }

    /** To draw this playground, with frog, floaters, FrogKillers */
    public WorldImage makeImage() {
        WorldImage currentImage = this.background();
        for (Floater f : this.floaters) {
            currentImage = currentImage.overlayImages(f.makeImage());
        }

        currentImage = currentImage.overlayImages(this.frog.makeImage());

        for (FrogKiller k : this.frogKillers) {
            currentImage = currentImage.overlayImages(k.makeImage());
        }
        return currentImage;
    }

    /**
     * To deal with the KeyEvent, move the frog with corresponding key if the
     * frog is killed, passed the game or land on the floater, there is a sound
     * effect for each evant
     * */
    public void onKeyEvent(String ke) {
        if (ke.equals("up"))
            this.frog.moveBy(0, -53);
        else if (ke.equals("down"))
            this.frog.moveBy(0, 53);
        else if (ke.equals("left"))
            this.frog.moveBy(-50, 0);
        else if (ke.equals("right"))
            this.frog.moveBy(50, 0);

    }

    /**
     * To move the world on tick, including: move the FrogKiller(Tank,
     * ShcoolBus, Zombie etc) move the frog Add background music move the
     * Floater (log, LilyPad) If the frog killed, drowned, out of screen, its
     * lives will be decreased
     */

    public void onTick() {

        if (this.lives <= 0)
            return; // stop moving after the lives used up
        for (FrogKiller k : this.frogKillers) {
            k.moveOn();
        }

        for (Floater f : this.floaters) {
            f.moveOn();
        }

        this.frog.moveOn(); // frog moves if it is on a floater

        if (this.frogKilled() || 
                this.frogDrowned() || 
                this.frog.outOfScreen()) {
            // decreases the lives if frog is killed or drowned
            this.lives--;
            // returns the frog to the start point if there is at least one life
            if (this.lives > 0)
                this.frog.relocate(new CartPt(width / 2, height - 25));
        }

    }

    /**
     * After each tick, check if there is no life if yes, end the game
     * 
     */
    public WorldEnd worldEnds() {
        if (this.lives <= 0) {
            return new WorldEnd(true, this.makeImage().overlayImages(
                    new TextImage(new Posn(width / 2, height / 2), "GAME OVER",
                            50, 3, Color.red)));
        } 
        else {
            if (this.frog.isPasssed()) {
                this.score++;
                this.frog.relocate(new CartPt(width / 2, height - 25));
            }
            return new WorldEnd(false, this.makeImage());
        }

    }

    /** To check if any FrogKiller kill the frog */
    public boolean frogKilled() {

        for (FrogKiller k : this.frogKillers) {
            if (k.crush(this.frog.getLoc()))

                return true;
        }

        return false;
    }

    /** To check if any floater get the frog */
    public boolean frogDrowned() {
        // It is not drowned if frog is not on the river
        if ((this.frog.loc.y <= 53 + 50 / 2)
                || (this.frog.loc.y >= 4 * 53 + 50 / 2))
            return false;
        // It is not drowned if frog on the river and on a floater
        for (Floater f : this.floaters) {
            if (f.catches(this.frog.getLoc())) {
                this.frog.hopOn(f);
                return false;
            }
        }
        // it is drowned if is on the river but not on a floater
        return true;
    }

}
