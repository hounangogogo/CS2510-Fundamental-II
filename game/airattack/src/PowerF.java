import java.awt.*;

import javalib.colors.Red;
import javalib.worldimages.OvalImage;
import javalib.worldimages.Posn;
import javalib.worldimages.TextImage;
import javalib.worldimages.WorldImage;

class PowerF extends Power {
  PowerF(Posn loc) {
    super(loc);
  }

  @Override
  WorldImage enemyImage() {
    return new OvalImage(loc, 30, 20, Color.CYAN).
            overlayImages(
                    new TextImage(
                            new Posn(15,10),"F", 10, Color.BLACK));
  }
}
