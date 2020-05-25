import javalib.colors.Yellow;
import javalib.worldimages.OvalImage;
import javalib.worldimages.Posn;
import javalib.worldimages.WorldImage;

/**
 * Class of Bullet.
 */
class Bullet {
  Posn loc;

  Bullet(Posn loc) {
    this.loc = loc;
  }

  void bulletMove() {
    if (loc.y > 0) {
      loc.y = loc.y - 20;
    }
  }

  //to represent the image of my plane
  WorldImage bulletImage() {
    return new OvalImage(loc, 20, 5, new Yellow());
  }

}
