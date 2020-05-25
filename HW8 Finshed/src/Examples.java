
import tester.Tester;

import javalib.worldimages.FromFileImage;

// Examples
public class Examples {
    // Examples of frogs
    Frog frog1 = new Frog(new CartPt(1100 / 2, 583 - 25));
    Frog frog2 = new Frog(new CartPt(1100 / 2, 0));
    Frog frog3 = new Frog(new CartPt(1100 / 2, 583 - 53 * 2));
    
    // Examples of Floaters
    Floater lily1 = new LilyPad(new CartPt(1100 / 2, 583 - 53 * 2), 1);
    Floater log1 = new Log(new CartPt(1100 / 2, 583 - 53 * 2), 1);
    
    // Examples of FrogKillers
    FrogKiller zombie1 = new Zombie(new CartPt(1100 / 2, 583 - 53 * 2), 1);
    FrogKiller schoolbus1 = new SchoolBus(new CartPt(1100 / 2, 583 - 53 * 2), 1);
    FrogKiller firetruck1 = new FireTruck(new CartPt(1100 / 2, 583 - 53 * 2), 1);
    FrogKiller tank1 = new Tank(new CartPt(1100 / 2, 583 - 25), 1);
    FrogKiller tank2 = new Tank(new CartPt(1100 / 2, 583 - 25), -1);

    // Examples of world
    Playground world1 = new Playground(frog1, 0, 3);

    void testFrog(Tester t) {
        Frog frog3a = new Frog(new CartPt(1100 / 2, 583 - 53 * 2));
        Frog frog4 = new Frog(new CartPt(1100 / 2, 50));
        frog3a.hopOn(lily1);

        t.checkExpect(frog1.getLoc(), new CartPt(1100 / 2, 583 - 25));
        t.checkExpect(frog1.isPasssed(), false);
        
        // no change of location because frog1 is not on floater
        t.checkExpect(frog1.moveOn(), new CartPt(1100 / 2, 583 - 25));

        t.checkExpect(frog2.isPasssed(), true);
        t.checkExpect(frog2.getLoc(), frog2.loc);
        t.checkExpect(frog2.moveOn(), frog2.getLoc());

        t.checkExpect(frog3a.moveOn(), lily1.getLoc());
        t.checkExpect(frog4.moveBy(-5000, 0), new CartPt(1100 / 2, 50));
        t.checkExpect(frog3a.moveBy(-5, 0), new CartPt(1100 / 2 - 5, 
                583 - 53 * 2));
        
    }

    void testFoaters(Tester t) {
        Frog frog3a = new Frog(new CartPt(1100 / 2, 53 * 2));
        Floater lily2 = new LilyPad(new CartPt(1100 / 2, 53 * 2), 1);
        Playground world1a = new Playground(frog3a, 0, 3);
        world1a.floaters.add(lily2);

        t.checkExpect(lily2.catches(frog3a.getLoc()), true);
        t.checkExpect(frog3a.floater, null);
        t.checkExpect(world1a.frogDrowned(), false);
        world1a.onTick();
        t.checkExpect(frog3a.floater, lily2);
        t.checkExpect(lily2.moveOn(), new CartPt(552, 106));

        
    }
    void testVehicle(Tester t) {
        FrogKiller tank3 = new Tank(new CartPt(1100 / 2, 583 - 25), 
                -1);
        t.checkExpect(tank3.moveOn(), 
                new CartPt(1100 / 2 - 1, 583 - 25));
    }
    void testMakeImages(Tester t) {
        t.checkExpect(frog1.makeImage(), new FromFileImage(frog1.getLoc(), 
                "frog.png"));
        t.checkExpect(lily1.makeImage(), new FromFileImage(lily1.getLoc(), 
                "lilypad.png"));
        t.checkExpect(log1.makeImage(), new FromFileImage(log1.getLoc(), 
                "log.png"));
        t.checkExpect(zombie1.makeImage(), 
                new FromFileImage(zombie1.loc,
                "zombie_left.png"));
        t.checkExpect(schoolbus1.makeImage(), new FromFileImage(schoolbus1.loc, 
                "schoolbus_left.png"));
        t.checkExpect(firetruck1.makeImage(), 
                new FromFileImage(firetruck1.loc,
                "firetruck_right.png"));
        t.checkExpect(tank1.makeImage(), 
                new FromFileImage(tank1.loc,
                "Tank_right.png"));
        t.checkExpect(tank2.makeImage(), 
                new FromFileImage(tank2.loc,
                "Tank_left.png"));
        t.checkExpect(world1.makeImage(), world1.makeImage());
    }
    void testWorlds(Tester t) {
        // internal examples of frogs
        Frog frog2a = new Frog(new CartPt(1100 / 2, 0));        
        Frog frog4a = new Frog(new CartPt(0, 583 - 50 - 25 - 5));
        Frog frog4b = new Frog(new CartPt(1100 / 2, 53 * 2));
        
        // internal examples
        Playground world1a = new Playground(frog2a, 0, 3);
        Playground world1b = new Playground(frog2a, 0, 0);
        Playground world4a = new Playground(frog4a, 0, 1);
        Playground world4b = new Playground(frog4b, 0, 2);
                
        t.checkExpect(world1a.worldEnds(), world1a.worldEnds());
        world1a.onKeyEvent("up");
        t.checkExpect(frog2a.getLoc(), new CartPt(1100 / 2, 505));
        world1a.onKeyEvent("left");
        t.checkExpect(frog2a.getLoc(), new CartPt(1100 / 2 - 50, 505));
        world1a.onKeyEvent("right");
        t.checkExpect(frog2a.getLoc(), new CartPt(1100 / 2, 505));
        world1a.onKeyEvent("down");
        t.checkExpect(frog2a.getLoc(), new CartPt(1100 / 2, 505 + 53));
        world1a.onKeyEvent("something else");
        t.checkExpect(frog2a.getLoc(), new CartPt(1100 / 2, 505 + 53));
        
        // test the lives changes of Frog in world4a  
        t.checkExpect(world4a.lives, 1);
        t.checkExpect(world4a.frogKilled(), true);
        world4a.onTick();   // after a tick
        t.checkExpect(world4a.lives, 0);    // frog died
        
       // test the lives changes of Frog in world4b   
        t.checkExpect(world4b.lives, 2);
        t.checkExpect(world4b.frogDrowned(), true);
        world4b.onTick();   // after a tick
        t.checkExpect(world4b.lives, 1);
        t.checkExpect(world4b.frogDrowned(), false); // frog returns
        
        t.checkExpect(frog2a.getLoc(), new CartPt(1100 / 2, 505 + 53));
        world1b.onTick();
        t.checkExpect(world1b.worldEnds(), world1b.worldEnds());
        t.checkExpect(world4a.worldEnds().getClass().getName(), 
                "javalib.worldimages.WorldEnd");
    }

}
