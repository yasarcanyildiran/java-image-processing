/**
 * Réduit le nombre de niveaux de gris de l'image à nbGrayLevels.
 */
public class DecreaseGrayLevels implements Transform {

  private final int nbGrayLevels;

  public DecreaseGrayLevels(int nbGrayLevels) {
    this.nbGrayLevels = nbGrayLevels;
  }

  /**
   * Arrondit la luminosité à la valeur la plus proche dans
   * {0/(n-1), 1/(n-1), ..., (n-1)/(n-1)}.
   */
  public double roundLuminosity(double luminosity) {
    int intervals = nbGrayLevels - 1;
    return (double) Math.round(luminosity * intervals) / intervals;
  }

  @Override
  public void applyTo(GrayImage image) {
    for (int x = 0; x < image.getWidth(); x++) {
      for (int y = 0; y < image.getHeight(); y++) {
        double luminosity = image.getPixelLuminosity(x, y);
        image.setPixelLuminosity(roundLuminosity(luminosity), x, y);
      }
    }
  }
}
