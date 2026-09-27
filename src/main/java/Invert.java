/**
 * Inverse la luminosité de chaque pixel : l devient 1 - l.
 */
public class Invert implements Transform {

  @Override
  public void applyTo(GrayImage image) {
    for (int x = 0; x < image.getWidth(); x++) {
      for (int y = 0; y < image.getHeight(); y++) {
        double luminosity = image.getPixelLuminosity(x, y);
        image.setPixelLuminosity(1 - luminosity, x, y);
      }
    }
  }
}
