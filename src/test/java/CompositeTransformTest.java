import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

class CompositeTransformTest {

  @Test
  void testApplyTo_emptyListDoesNothing() {
    MatrixGrayImage image = new MatrixGrayImage(2, 2);
    image.setPixelLuminosity(0.3, 0, 1);
    new CompositeTransform(List.of()).applyTo(image);
    assertThat(image.getPixelLuminosity(0, 1)).isCloseTo(0.3, within(0.01));
  }

  @Test
  void testApplyTo_appliesTransformsInOrder() {
    MatrixGrayImage image = new MatrixGrayImage(1, 1);
    image.setPixelLuminosity(0.2, 0, 0);
    // DecreaseGrayLevels(2) : 0.2 -> 0.0, puis Invert : 0.0 -> 1.0
    new CompositeTransform(List.of(new DecreaseGrayLevels(2), new Invert())).applyTo(image);
    assertThat(image.getPixelLuminosity(0, 0)).isEqualTo(1.0);

    MatrixGrayImage other = new MatrixGrayImage(1, 1);
    other.setPixelLuminosity(0.2, 0, 0);
    // Invert : 0.2 -> 0.8, puis DecreaseGrayLevels(2) : 0.8 -> 1.0
    new CompositeTransform(List.of(new Invert(), new DecreaseGrayLevels(2))).applyTo(other);
    assertThat(other.getPixelLuminosity(0, 0)).isEqualTo(1.0);
  }

  @Test
  void testApplyTo_twoInvertsCancelOut() {
    MatrixGrayImage image = new MatrixGrayImage(1, 1);
    image.setPixelLuminosity(0.4, 0, 0);
    new CompositeTransform(List.of(new Invert(), new Invert())).applyTo(image);
    assertThat(image.getPixelLuminosity(0, 0)).isCloseTo(0.4, within(0.01));
  }
}
