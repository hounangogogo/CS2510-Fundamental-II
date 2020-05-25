

import javalib.worldimages.*;

/** To represent a frog
 * 
 * @author Hong
 *
 */
public class Frog {
    /** constant: the size of the screen*/
    int width = 1100;
    int height = 583;

    /** the location of this Frog */
    CartPt loc;
    /** the name of the image file for displaying this frog*/
    String imgName = "frog.png";
    Floater floater;

    /** To change the location of this Frog to the given one
     * @param loc
     */
    Frog(CartPt loc) {
        super();
        this.loc = loc;
    }

    /** To set the location of this frog to the given location 
     * produces the new location    
     * @param CartPt new location 
     * @return CartPt return the frog to the new location 
     */
    public CartPt relocate(CartPt newLoc) {
        this.floater = null;
        this.loc = newLoc;
        return this.loc;
    }

    /** To get the location of this frog */
    public CartPt getLoc() {
        return this.loc;
    }

    /** To move this frog along coordinates by given x, y
     * @param dx
     * @param dy
     * @return CartPt the location of the frog
     */
    public CartPt moveBy(int dx, int dy) {
        // cannot move more if reaches the left, right, or bottom margin 
        if ((this.loc.x + dx >= 0) && (this.loc.x + dx <= this.width)
         && (this.loc.y + dy <= this.height)) {
            this.loc = this.loc.moveBy(dx, dy);
            this.floater = null;    // leave floater once move by
        }
        return this.loc;
    }
    
    // To determine if this Frog passed a game
    public boolean isPasssed() {
        return this.loc.y <= 0;
    }

    // To create the image of this frog
    public WorldImage makeImage() {
        return new FromFileImage(this.loc, this.imgName);
    }
    
    // To make this frog hop on the given floater
    // produces new frog
    public Frog hopOn(Floater f) {
        this.floater = f;
        return this;
    }
    
    // To move this frog if it is on floater 
    // produces the new loc after moving
    public CartPt moveOn() {
        if (!(this.floater == null) && !this.outOfScreen()) {
            this.loc.x = this.floater.getLoc().x;
            this.loc.y = this.floater.getLoc().y;
        }
        return this.loc;
    }
    
    // To determine if this frog is on the river and out of the screen
    public boolean outOfScreen() {
        return !(this.floater == null) 
                && ((this.loc.x + this.floater.velocityX <= 0) 
                        || (this.loc.x + this.floater.velocityX 
                                >= this.width));
    }
}
