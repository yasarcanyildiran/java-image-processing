import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

class PixelateTest {

  @Test
  void testApplyTo_squareTakesAverageLuminosity() {
    MatrixGrayImage image = new MatrixGrayImage(2, 2);
    image.setPixelLuminosity(0.0, 0, 0);
    image.setPixelLuminosity(0.0, 1, 1);
    // moyenne = (0 + 1 + 1 + 0) / 4 = 0.5
    new Pixelate(2).applyTo(image);
    for (int x = 0; x < 2; x++) {
      for (int y = 0; y < 2; y++) {
        assertThat(image.getPixelLuminosity(x, y)).isCloseTo(0.5, within(0.01));
      }
    }
  }

  @Test
  void testApplyTo_squaresAreIndependent() {
    MatrixGrayImage image = new MatrixGrayImage(4, 2);
    image.setPixelLuminosity(0.0, 0, 0);
    image.setPixelLuminosity(0.0, 1, 0);
    image.setPixelLuminosity(0.0, 0, 1);
    image.setPixelLuminosity(0.0, 1, 1);
    new Pixelate(2).applyTo(image);
    assertThat(image.getPixelLuminosity(1, 1)).isEqualTo(0.0);
    assertThat(image.getPixelLuminosity(2, 0)).isEqualTo(1.0);
  }

  @Test
  void testApplyTo_worksWhenSizeDoesNotDivideImage() {
    MatrixGrayImage image = new MatrixGrayImage(3, 3);
    image.setPixelLuminosity(0.0, 2, 2);
    new Pixelate(2).applyTo(image);
    assertThat(image.getPixelLuminosity(2, 2)).isEqualTo(0.0);
    assertThat(image.getPixelLuminosity(0, 0)).isEqualTo(1.0);
  }
}
