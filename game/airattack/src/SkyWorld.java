import javalib.colors.White;
import javalib.impworld.World;
import javalib.worldimages.OverlayImages;
import javalib.worldimages.Posn;
import javalib.worldimages.RectangleImage;
import javalib.worldimages.TextImage;
import javalib.worldimages.WorldEnd;
import javalib.worldimages.WorldImage;

class SkyWorld extends World implements SkyConstant {
  Plane plane;
  ILoBullet bullets;
  ILoE enemies;
  int i = -1;
  SkyWorld(Plane plane, ILoBullet bullets, ILoE enemies) {
    this.plane = plane;
    this.bullets = bullets;
    this.enemies = enemies;
  }

  public void onKeyEvent(String ke) {
    plane.movePlane(ke);
  }


  //to represent the world on next tick
  public void onTick() {

    bullets.bulletFly();
    bullets = new ConsLoBullet(new Bullet(new Posn(plane.loc.x, plane.loc.y)),
            this.bullets).removeBullet();
    enemies.enemiesFly();
    enemies = new ConsLoEnemies(new Enemy(new Posn(rand.nextInt(WIDTH), 0), 20), this.enemies).removeEnemy(bullets);
    plane.planeHit(enemies);
  }


  public WorldEnd worldEnds() {
    if (plane.life == 0) {
      return new WorldEnd(true,
                      new OverlayImages(this.makeImage(),
                              new TextImage(
                                      new Posn(200,200),
                                      "Game Over",
                                      30, new White())));
    }
    else {
      return new WorldEnd(false, this.makeImage());
    }
  }

  /**
   * <P>User defined method to draw the <code>{@link World World}</code>.</P>
   * <P>Override this method in the game world class</P>
   *
   * @return the image that represents this world at this moment
   */
  @Override
  public WorldImage makeImage() {
    return new RectangleImage(new Posn(WIDTH / 2, HEIGHT / 2), WIDTH,
            HEIGHT, skyColor)
            .overlayImages(plane.planeImage(), bullets.bulletsImage(), enemies.enemiesImage())
            .overlayImages(new TextImage(new Posn(30, 50),
            "Life " + plane.life, 16, new White()));

  }
}