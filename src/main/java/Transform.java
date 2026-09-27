/**
 * Une transformation qui modifie une image en niveaux de gris.
 */
public interface Transform {
  void applyTo(GrayImage image);
}
