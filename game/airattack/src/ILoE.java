import javalib.worldimages.OvalImage;
import javalib.worldimages.OverlayImages;
import javalib.worldimages.Posn;
import javalib.worldimages.WorldImage;

public interface ILoE extends SkyConstant {

  WorldImage enemiesImage();

  void enemiesFly();

  ILoE removeEnemy(ILoBullet bs);

  boolean isEmpty();


}

class MtLoE implements ILoE {

  @Override
  public WorldImage enemiesImage() {

    return new OvalImage(new Posn(0, 0), 0, 0, skyColor);
  }

  @Override
  public void enemiesFly() {
  }

  @Override
  public ILoE removeEnemy(ILoBullet bs) {
    return this;
  }

  @Override
  public boolean isEmpty() {
    return true;
  }
}

class ConsLoEnemies implements ILoE {
  Enemy first;
  ILoE rest;

  ConsLoEnemies(Enemy first, ILoE rest) {
    this.first = first;
    this.rest = rest;
  }

  @Override
  public WorldImage enemiesImage() {
    return new OverlayImages(this.first.enemyImage(),
            rest.enemiesImage());
  }

  @Override
  public void enemiesFly() {
    first.enemyMove();
    rest.enemiesFly();
  }

  @Override
  public ILoE removeEnemy(ILoBullet bs) {

    if (first.loc.y > 590 || first.readyToRemove(bs)) {
      return rest.removeEnemy(bs);
    }
    else {
      return new ConsLoEnemies(first, rest.removeEnemy(bs));
    }
  }

  @Override
  public boolean isEmpty() {
    return false;
  }
}