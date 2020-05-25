// assignment 8
// Guo Hong
// guohong
// Zhao Haonan
// haonan

import javalib.worldimages.FromFileImage;
import javalib.worldimages.WorldImage;

/**
 * To represent an abstract Floater
 * 
 * @author Hong
 * 
 */
public abstract class Floater implements FrogConstant {

    /**
     * the location of this Floater
     */
    CartPt loc;
    /**
     * the name of the image file for displaying this Floater
     */
    String imgName;
    /**
     * the velocity of this Floater in x
     */
    int velocityX;
    /**
     * the length of this Floater
     */
    int length;

    Floater(CartPt loc, String imgName, int velocityX, int length) {
        super();
        this.loc = loc;
        this.imgName = imgName;
        this.velocityX = velocityX;
        this.length = length;
    }

    /**
     * To move this Floater
     * 
     * @return CartPt the location of the floater
     */
    public CartPt moveOn() {
        this.loc.x = (width + this.loc.x + this.velocityX) % width;
        return this.loc;
    }

    /**
     * To create the image of this Floater
     * 
     * @return WordImage
     */
    public WorldImage makeImage() {
        return new FromFileImage(this.loc, this.imgName);
    }

    /**
     * To determine if this floater catch the frog at the given position
     * 
     * @param CartPt
     *            the location of the frog
     * @return boolean if the floater catch the frog
     */
    public boolean catches(CartPt that) {
        return (Math.abs(this.loc.y - that.y) <= 5 && Math.abs(this.loc.x
                - that.x) < (this.length / 2 + 15));
    }

    /**
     * To get the location of this frog
     * 
     * @return CartPt
     */
    public CartPt getLoc() {
        return this.loc;
    }
}
