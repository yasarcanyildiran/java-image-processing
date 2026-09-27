import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

class InvertTest {

  @Test
  void testApplyTo_invertsLuminosity() {
    MatrixGrayImage image = new MatrixGrayImage(3, 2);
    image.setPixelLuminosity(0.0, 0, 0);
    image.setPixelLuminosity(0.2, 1, 0);
    image.setPixelLuminosity(1.0, 2, 1);
    new Invert().applyTo(image);
    assertThat(image.getPixelLuminosity(0, 0)).isCloseTo(1.0, within(0.01));
    assertThat(image.getPixelLuminosity(1, 0)).isCloseTo(0.8, within(0.01));
    assertThat(image.getPixelLuminosity(2, 1)).isCloseTo(0.0, within(0.01));
  }

  @Test
  void testApplyTo_twiceGivesOriginalImage() {
    MatrixGrayImage image = new MatrixGrayImage(2, 2);
    image.setPixelLuminosity(0.4, 1, 1);
    Invert invert = new Invert();
    invert.applyTo(image);
    invert.applyTo(image);
    assertThat(image.getPixelLuminosity(1, 1)).isCloseTo(0.4, within(0.01));
  }
}
