
/** To represent a tank
 * @author Hong
 *
 */
public class Tank extends FrogKiller{
    Tank(CartPt loc, int velocityX) {
        super(loc, velocityX, 52);
        /** according to the velocity's direction, this has corresponding icon
         * 
         */
            if (velocityX < 0) {
                this.imageName = "Tank_left.png";
            }
            else {    
            this.imageName = "Tank_right.png";
            }
    }
    
}
