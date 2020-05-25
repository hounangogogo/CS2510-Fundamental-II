import javalib.worldimages.Posn;
import javalib.worldimages.WorldImage;

public abstract class Power implements SkyConstant {
  Posn loc;

  Power(Posn loc) {
    this.loc = loc;
  }

  void powerMove() {
    if (loc.y < HEIGHT) {
      loc.y = loc.y + 5;
      loc.x = loc.x + rand.nextInt(20);
      loc.x = loc.x - rand.nextInt(20);
    }
  }

  //to represent the image of my plane
  abstract WorldImage enemyImage();
}
