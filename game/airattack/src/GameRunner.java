import javalib.worldimages.Posn;

public class GameRunner implements SkyConstant{

  public static void main(String[] args) {
    Plane plane = new Plane(new Posn(WIDTH / 2, HEIGHT - 10));
    ILoBullet bullets = new MtLoBullet();
    ILoE enemies = new MtLoE();
    PowerF p1 = new PowerF(new Posn(WIDTH /2, 0));
    Bullet b = new Bullet(new Posn(plane.loc.x, plane.loc.y));
    SkyWorld k = new SkyWorld(plane, bullets, enemies);
    k.bigBang(WIDTH, HEIGHT, 0.03);
  }
}
