/**
 * Pixelisation : l'image est découpée en carrés de côté superPixelSize,
 * et chaque carré prend la luminosité moyenne de ses pixels.
 */
public class Pixelate implements Transform {

  private final int superPixelSize;

  public Pixelate(int superPixelSize) {
    this.superPixelSize = superPixelSize;
  }

  @Override
  public void applyTo(GrayImage image) {
    for (int x = 0; x < image.getWidth(); x += superPixelSize) {
      for (int y = 0; y < image.getHeight(); y += superPixelSize) {
        int maxX = Math.min(x + superPixelSize, image.getWidth());
        int maxY = Math.min(y + superPixelSize, image.getHeight());
        double average = averageLuminosity(image, x, y, maxX, maxY);
        fillSquare(image, x, y, maxX, maxY, average);
      }
    }
  }

  private double averageLuminosity(GrayImage image, int minX, int minY, int maxX, int maxY) {
    double sum = 0;
    for (int x = minX; x < maxX; x++) {
      for (int y = minY; y < maxY; y++) {
        sum += image.getPixelLuminosity(x, y);
      }
    }
    return sum / ((maxX - minX) * (maxY - minY));
  }

  private void fillSquare(GrayImage image, int minX, int minY, int maxX, int maxY, double luminosity) {
    for (int x = minX; x < maxX; x++) {
      for (int y = minY; y < maxY; y++) {
        image.setPixelLuminosity(luminosity, x, y);
      }
    }
  }
}
