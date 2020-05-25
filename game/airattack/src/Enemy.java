import javalib.colors.Red;
import javalib.worldimages.OvalImage;
import javalib.worldimages.Posn;
import javalib.worldimages.WorldImage;

class Enemy implements SkyConstant {
  Posn loc;
  int speed;

  Enemy(Posn loc, int speed) {
    this.loc = loc;
    this.speed = speed;
  }

  void enemyMove() {
    if (loc.y < HEIGHT) {
      loc.y = loc.y + speed;
      loc.x = loc.x + rand.nextInt(5);
      loc.x = loc.x - rand.nextInt(5);
    }
  }

  boolean readyToRemove(ILoBullet bs) {
    if (bs.isEmpty()) {
      return false;
    }
    ConsLoBullet b = (ConsLoBullet) bs;
    int b_x = b.first.loc.x;
    int b_y = b.first.loc.y;
    int diff_x = loc.x - b_x;
    int diff_y = loc.y - b_y;
    double dis = Math.sqrt(diff_x * diff_x + diff_y * diff_y);
    if (dis < 20) {
      return true;
    }
    else {
      return readyToRemove(b.rest);
    }
  }


  //to represent the image of my plane
  WorldImage enemyImage() {
    return new OvalImage(loc, 10, 10, new Red());
  }
}
