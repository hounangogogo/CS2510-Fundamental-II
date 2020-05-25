// assignment 8
// Guo Hong
// guohong
// Zhao Haonan
// haonan

import javalib.worldimages.Posn;

/**
 * <code>CartPt</code> represent CartPt with move methods
 */
public class CartPt extends Posn {
    CartPt(int x, int y) {
        super(x, y);
    }

    /**
     * produce a point moved by the given distance from this point
     * 
     * @param dx
     *            the x coordinate
     * @param dy
     *            the y coordinate
     * @return CartPt
     */
    CartPt moveBy(int dx, int dy) {
        return new CartPt(this.x + dx, this.y + dy);
    }
}