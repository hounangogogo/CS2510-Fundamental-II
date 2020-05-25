// assignment 8
// Guo Hong
// guohong
// Zhao Haonan
// haonan

/**
 * To represent a Zombie
 * 
 * @author Hong
 * 
 */
public class Zombie extends FrogKiller {
    Zombie(CartPt loc, int velocityX) {
        super(loc, velocityX, 62);
        this.imageName = "zombie_left.png";
    }

}
