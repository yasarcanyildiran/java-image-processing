/**
 * Extraction de contours : un pixel devient noir s'il est très différent
 * du pixel à sa droite ou du pixel en dessous (dans l'image initiale),
 * sinon il devient blanc.
 */
public class Outline implements Transform {

  private static final double BLACK = 0.0;
  private static final double WHITE = 1.0;

  private final double threshold;

  public Outline(double threshold) {
    this.threshold = threshold;
  }

  public boolean isVeryDifferent(double luminosity1, double luminosity2) {
    return Math.abs(luminosity1 - luminosity2) > threshold;
  }

  private boolean isOnOutline(GrayImage image, int x, int y) {
    double luminosity = image.getPixelLuminosity(x, y);
    boolean rightDifferent = x + 1 < image.getWidth()
        && isVeryDifferent(luminosity, image.getPixelLuminosity(x + 1, y));
    boolean belowDifferent = y + 1 < image.getHeight()
        && isVeryDifferent(luminosity, image.getPixelLuminosity(x, y + 1));
    return rightDifferent || belowDifferent;
  }

  @Override
  public void applyTo(GrayImage image) {
    // On calcule d'abord tous les contours sur l'image initiale,
    // sinon les pixels déjà modifiés fausseraient les comparaisons.
    boolean[][] outline = new boolean[image.getWidth()][image.getHeight()];
    for (int x = 0; x < image.getWidth(); x++) {
      for (int y = 0; y < image.getHeight(); y++) {
        outline[x][y] = isOnOutline(image, x, y);
      }
    }
    for (int x = 0; x < image.getWidth(); x++) {
      for (int y = 0; y < image.getHeight(); y++) {
        image.setPixelLuminosity(outline[x][y] ? BLACK : WHITE, x, y);
      }
    }
  }
}
