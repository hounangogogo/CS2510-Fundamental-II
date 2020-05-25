import javalib.worldimages.OvalImage;
import javalib.worldimages.OverlayImages;
import javalib.worldimages.Posn;
import javalib.worldimages.WorldImage;

/**
 * The list of Bullets.
 */
interface ILoBullet extends SkyConstant {
  boolean isEmpty();

  WorldImage bulletsImage();

  void bulletFly();

  ILoBullet removeBullet();


}


class MtLoBullet implements ILoBullet {

  @Override
  public boolean isEmpty() {
    return true;
  }

  @Override
  public WorldImage bulletsImage() {
    return new OvalImage(new Posn(0, 0), 0, 0, skyColor);
  }

  @Override
  public void bulletFly() {

  }

  @Override
  public ILoBullet removeBullet() {
    return this;
  }


}

class ConsLoBullet implements ILoBullet {
  Bullet first;
  ILoBullet rest;

  ConsLoBullet(Bullet first, ILoBullet rest) {
    this.first = first;
    this.rest = rest;
  }

  @Override
  public boolean isEmpty() {
    return false;
  }

  @Override
  public WorldImage bulletsImage() {
    return new OverlayImages(this.first.bulletImage(),
            rest.bulletsImage());
  }

  @Override
  public void bulletFly() {
    this.first.bulletMove();
    this.rest.bulletFly();
  }

  @Override
  public ILoBullet removeBullet() {
    if (first.loc.y < 10) {
      return rest.removeBullet();
    }
    else {
      return new ConsLoBullet(first, rest.removeBullet());
    }
  }
}