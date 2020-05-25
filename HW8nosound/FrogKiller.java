// assignment 8
// Guo Hong
// guohong
// Zhao Haonan
// haonan

import javalib.worldimages.FromFileImage;
import javalib.worldimages.WorldImage;

/**
 * represent a abstract <code>FrogKiller</code>
 * 
 * @author Hong
 * 
 */
public abstract class FrogKiller implements FrogConstant {

    // the location of this FrogKiller
    CartPt loc;
    // the name of the image file for displaying this FrogKiller
    String imageName;
    // the velocity of this FrogKiller in x
    int velocityInX;
    // the length of this FrogKiller
    int length;

    FrogKiller(CartPt loc, int velocityInX, int length) {
        this.loc = loc;
        this.velocityInX = velocityInX;
        this.length = length;
    }

    /**
     * To move this FrogKiller produces the new loc
     * 
     * @return <code>CartPt</code>
     */
    public CartPt moveOn() {
        this.loc.x = (width + this.loc.x + this.velocityInX) % width;
        return this.loc;
    }

    /**
     * create the image of this FrogKiller
     * 
     * @return <code>WorldImage</code> the image of this frogkiller
     */
    public WorldImage makeImage() {
        return new FromFileImage(this.loc, this.imageName);
    }

    /**
     * To determine if this FrogKiller collides the frog and kill the frog at
     * the given position
     * 
     * @param that
     *            the location of the frog
     * @return boolean if this FrogKiller kill the frog
     */
    public boolean crush(CartPt that) {

        return (Math.abs(this.loc.y - that.y) <= 10 && Math.abs(this.loc.x
                - that.x) < (this.length / 2 + 15));
    }
}
