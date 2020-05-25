import java.awt.*;
import java.util.Random;

import javalib.worldimages.Posn;
import javalib.worldimages.RectangleImage;
import javalib.worldimages.WorldImage;

interface SkyConstant {
  Random rand = new Random();
  int WIDTH = 400;
  int HEIGHT = 600;
  Color skyColor = Color.BLACK;
  WorldImage skyImage =
          new RectangleImage(new Posn(WIDTH / 2, HEIGHT / 2),
                  WIDTH, HEIGHT, skyColor);


}
