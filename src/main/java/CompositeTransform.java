import java.util.List;

/**
 * Applique successivement une liste de transformations à l'image.
 */
public class CompositeTransform implements Transform {

  private final List<Transform> transforms;

  public CompositeTransform(List<Transform> transforms) {
    this.transforms = transforms;
  }

  @Override
  public void applyTo(GrayImage image) {
    for (Transform transform : transforms) {
      transform.applyTo(image);
    }
  }
}
