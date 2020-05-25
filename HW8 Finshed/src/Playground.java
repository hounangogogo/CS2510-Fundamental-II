
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

import javalib.tunes.Note;
import javalib.tunes.SoundConstants;
import javalib.worldimages.*;
import javalib.soundworld.World;




import java.util.*;
import java.awt.Color;

/**To represent the playground
 * @author Hong
 */
public class Playground extends World {
    // the size of the screen
    int width = 1100;
    int height = 583;
    
    Frog frog;
    Integer score; 
    Integer lives;
    ArrayList<FrogKiller> frogKillers = new ArrayList<FrogKiller>();
    ArrayList<Floater> floaters = new ArrayList<Floater>();    
    
    Iterator<Note> tuneToPlay = (new Song()).iterator();


    
    
    
    Playground(Frog frog, Integer score, Integer lives) {
        this.frog = frog;
        this.score = score;
        this.lives = lives;
        // adding Frogkiller to playground 
        initFrogKiller();
        // adding Floaters to playground 
        initFolaters();
    }


    /** To initialize FrogKillers*/
    private void initFrogKiller() {
        int currentX;   // the current X location
        int interval;   // the interval between FrogKillers
        int velocityX;  // the velocity at X direction
        int locY;       // the current Y location
        boolean isCar = true;
        
        // first lane with tank ;
        currentX = 0;
        interval = 400;
        locY = this.height - 80; 
        velocityX = 8;
        while (currentX < this.width) {
            // adding  Tank
            this.frogKillers.add(new Tank(new CartPt(currentX, locY), 
                    velocityX));
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
        while (currentX < this.width) {
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
        while (currentX < this.width) {
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
    
    /** To initialize Floaters*/
    private void initFolaters() {
        int currentX;   // Current X location
        int interval;   // the interval between floaters 
        int velocityX;  // the velocity at x direction
        int locY;       // current Y position
        boolean isLeaf = true;
        
        // first lane of the river;
        currentX = 0;
        interval = 300;
        locY = 53 + 53 / 2;
        velocityX = 10;
        
        while (currentX < this.width) {
            // adding LilyPad as Floater
            this.floaters.add(new LilyPad(new CartPt(currentX, locY), 
                    velocityX));
            currentX = currentX + interval;
        }
        
        
        // second lane of the river;
        currentX = 0;
        interval = 100;
        locY = locY + 53;
        velocityX = 15;
        // to produce alternative logs and shark 
        while (currentX < this.width) {
            if (isLeaf) 
                this.frogKillers.add(new Shark(new CartPt(currentX, locY), 
                    velocityX));
            else
                this.floaters.add(new Log(new CartPt(currentX, locY), 
                        velocityX));      
            isLeaf = !isLeaf;
            currentX = currentX + interval;
        }
        
        
        // third lane;
        currentX = 0;
        interval = 500;
        locY = locY + 53;
        velocityX = -10;
        
        while (currentX < this.width) {
            this.floaters.add(new Log(new CartPt(currentX, locY), 
                    velocityX));
            currentX = currentX + interval;
        }        
    }
    
    /** To generate the background of this playground
     * @return <code>WorldImage</code> the image of the background
     */
    private WorldImage background() {
        
        return // background color
        new RectangleImage(new Posn(width / 2, height / 2), width, height,
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
                                height - 50 * 3 - 5 * 2),
                                width, 50 * 4 + 5 * 3, Color.darkGray))
                .overlayImages(
                        new RectangleImage(new Posn(width / 2, 
                                height - 50 * 3 - 5 * 2
                                - 2), width, 2, Color.orange))
                .overlayImages(
                        new RectangleImage(new Posn(width / 2, 
                                height - 50 * 3 - 5 * 2
                                + 2), width, 2, Color.orange))
                .overlayImages(this.makeDashedLines(height - 50 * 2 - 5 - 3, 
                        20))
                .overlayImages(
                        this.makeDashedLines(height - 50 * 4 - 15 + 3, 20)
                                .overlayImages(
                                        new RectangleImage(new Posn(width / 2,
                                                height - 50 - 3), 
                                                width, 2, Color.white)
                                                .overlayImages(
                                                        new RectangleImage(
                                                        new Posn(width / 2, 
                                                                height - 50
                                                                * 5 - 5 * 4 + 3
                                                                ), width,
                                                        2, Color.white))))
                // river
                .overlayImages(
                        new RectangleImage(
                                new Posn(width / 2, 53 + (3 * 53) / 2), width,
                                3 * 53, Color.blue));
    }

    /** To draw dashed line for the road at the given y 
     * @param y the y coordinate of the dashed line 
     * @param dashLength
     * @return <code>WorldImage</code> the image of the dashed 
     * line for the road
     */
    private WorldImage makeDashedLines(int y, int dashLength) {
        int interval = 50; // the interval between dashes
        WorldImage currentImage = new DiskImage(new Posn(0, 0), 0,
                Color.darkGray);
        int currentX = 0;
        while (currentX < this.width) {
            currentImage = currentImage.overlayImages(new RectangleImage(
                    new Posn(
                    currentX, y), dashLength, 2, Color.white));
            currentX = currentX + interval;
        }
        return currentImage;
    }

    /** To draw this playground, with frog, floaters, FrogKillers*/
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

    /** To deal with the KeyEvent, move the frog with corresponding key
     * if the frog is killed, passed the game or land on the floater, 
     * there is a sound effect for each evant 
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
        if (this.frogKilled()==true) {
            this.keyTunes.addNote(14, new Note("D4n2"));
        } 
        else if (this.frog.isPasssed()) {
            this.keyTunes.addNote(BIRD_TWEET, new Note("G2n4"));
        }
        else {
            for (Floater f : this.floaters) {
                if (f.catches(this.frog.getLoc())) {
                    this.keyTunes.addNote(BAGPIPE, new Note("E2f4"));
                    }
            }
        } 

        this.keyTunes.addNote(CLARINET, new Note("B4n4"));
    }
    
    /** To move the world on tick, including:
     * move the FrogKiller(Tank, ShcoolBus, Zombie etc)
     * move the frog 
     * Add background music 
     * move the Floater (log, LilyPad)
     * If the frog killed, drowned, out of screen, 
     * its lives will be decreased 
     */
    
    public void onTick() {
        
        if (this.lives <= 0)
            return; // stop moving after the lives used up
        for (FrogKiller k : this.frogKillers) { 
            k.moveOn();
        }
        this.tickTunes.addNote(TUBA, tuneToPlay.next());
        
        for (Floater f : this.floaters) { 
            f.moveOn();
        }
        
        this.frog.moveOn(); //frog moves if it is on a floater
        
        if (this.frogKilled() || this.frogDrowned() || this.frog.outOfScreen()) {
            // decreases the lives if frog is killed or drowned
            this.lives--;
            // returns the frog to the start point if there is at least one life
            if (this.lives > 0) 
                this.frog.relocate(new CartPt(this.width / 2, height - 25));
        }
        
        
        
    }

    /** After each tick, check if there is no life
     * if yes, end the game
     * 
     */
    public WorldEnd worldEnds() {
        if (this.lives <= 0) {
            return new WorldEnd(true, this.makeImage().overlayImages(
                    new TextImage(new Posn(width / 2, height / 2),
                            "GAME OVER", 
                            50, 3,
                            Color.red)));
        } else {
            if (this.frog.isPasssed()) {
                this.score++;
                this.frog.relocate(new CartPt(this.width / 2, height - 25));
            }
            return new WorldEnd(false, this.makeImage());
        }

    }

    /** To check if any FrogKiller kill the frog*/
    public boolean frogKilled() {
        
        for (FrogKiller k : this.frogKillers) {
            if (k.crush(this.frog.getLoc()))
                
                return true;
        }

        return false;
    }
    
    /** To check if any floater get the frog*/
    public boolean frogDrowned() {
        // It is not drowned if frog is not on the river 
        if ((this.frog.loc.y <= 53 + 50 / 2) ||
                (this.frog.loc.y >= 4 * 53 + 50 / 2 ))
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

/** The background music*/
class Song implements SoundConstants, Iterable<Note> {

    Note a = new Note("D4n2");
    Note c2 = new Note("B4n1");
    Note d2 = new Note("B4n2");
    Note e2 = new Note("E5n2");
    Note e = new Note("D4n1");
    Note f2 = new Note("F4n2");
    Note f = new Note("F4n1");
    Note g2 = new Note("D4n2");
    Note g = new Note("G4n1");
    Note cup2 = new Note("D5n2");
    Note cup = new Note("C3n1");
    Note dup = new Note("D3n1");
    Note eup = new Note("E4n1");
    Note silent = new Note(0, 0);


    Note[] backgroundmusic = new Note[] {
            this.g, this.a, this.e2, this.silent, this.c2, this.silent,     
            this.g, this.a, this.e2, this.silent, this.c2, this.silent,    
            this.g, this.g, this.cup, this.silent, this.cup, this.silent,
            this.cup, this.dup, this.eup, this.dup, this.cup2, this.silent,
            this.cup, this.a, this.g2, this.silent, this.g2, this.silent,
            this.g, this.a, this.f2, this.silent, this.f2, this.silent,
            this.f, this.g, this.e2, this.silent, this.e2, this.silent, 
            this.e, this.f, this.d2, this.silent, this.silent, this.silent   
    };

    public Iterator<Note> iterator() {
        return new SongIterator(this.backgroundmusic);
    }
}

/**a circular iterator for the given Array of notes
 * must play a silent note when pausing
 */
class SongIterator implements Iterator<Note> {

    Note[] song;
    int i;
    int len;

    SongIterator(Note[] song) {
        this.song = song;
        this.len = song.length;
        this.i = this.len - 1;
    }


    /** circular iterator - never runs out of notes for playing*/
    public boolean hasNext() {
        return true;
    }

    /** produce the next note to play*/
    public Note next() {
        this.i = (i + 1) % 48;
        return this.song[i];
    }

    /** do nothing on remove*/
    public void remove() { }
}