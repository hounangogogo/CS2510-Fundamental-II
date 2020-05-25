import javalib.colors.Black;
import javalib.colors.White;

import javalib.worldimages.OvalImage;
import javalib.worldimages.OverlayImages;
import javalib.worldimages.Posn;
import javalib.worldimages.TextImage;
import javalib.worldimages.WorldImage;


/**
 * Class represent airplane agent.
 */
class Plane implements SkyConstant {
  Posn loc;
  int life;
  int time;

  /**
   * Constructor for plane.
   * @param loc the location for plane.
   */
  Plane(Posn loc) {
    this.loc = loc;
    life = 3;
    time = 0;
  }



  /**
   * Move certain distance base on key pressed.
   * @param ke
   */
  void movePlane(String ke) {
    if (ke.equals("up") && loc.y > 15) {
      loc.y = loc.y - 10;
    }
    else if (ke.equals("down") && loc.y < HEIGHT - 10) {
      loc.y = loc.y + 10;
    }
    else if (ke.equals("left") && loc.x > 10) {
      loc.x = loc.x - 10;
    }
    else if (ke.equals("right") && loc.x < WIDTH) {
      loc.x = loc.x + 10;
    }
  }

  Posn planeLoc() {
    return new Posn(loc.x, loc.y);
  }

  WorldImage planeImage() {
    return new OverlayImages(new OvalImage(
            this.planeLoc(),50, 20, new White()),
            new TextImage(this.planeLoc(),
                    "Boeing 787", new Black()));
  }

  void planeHit(ILoE es) {
    if (!es.isEmpty()) {
      ConsLoEnemies e = (ConsLoEnemies) es;
      int b_x = e.first.loc.x;
      int b_y = e.first.loc.y;
      int diff_x = loc.x - b_x;
      int diff_y = loc.y - b_y;
      double dis = Math.sqrt(diff_x * diff_x + diff_y * diff_y);
      if (dis < 25) {
        System.out.println("Hit!");
        life--;
      }
      else {
        planeHit(e.rest);
      }
    }
  }
}
