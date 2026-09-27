import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OutlineTest {

  @Test
  void testIsVeryDifferent() {
    Outline outline = new Outline(0.1);
    assertThat(outline.isVeryDifferent(0.5, 0.55)).isFalse();
    assertThat(outline.isVeryDifferent(0.5, 0.7)).isTrue();
    assertThat(outline.isVeryDifferent(0.7, 0.5)).isTrue();
  }

  @Test
  void testApplyTo_uniformImageBecomesWhite() {
    MatrixGrayImage image = new MatrixGrayImage(3, 3);
    new Outline(0.1).applyTo(image);
    for (int x = 0; x < 3; x++) {
      for (int y = 0; y < 3; y++) {
        assertThat(image.getPixelLuminosity(x, y)).isEqualTo(1.0);
      }
    }
  }

  @Test
  void testApplyTo_detectsVerticalEdge() {
    // colonne 0 noire, colonnes 1 et 2 blanches
    MatrixGrayImage image = new MatrixGrayImage(3, 2);
    image.setPixelLuminosity(0.0, 0, 0);
    image.setPixelLuminosity(0.0, 0, 1);
    new Outline(0.1).applyTo(image);
    assertThat(image.getPixelLuminosity(0, 0)).isEqualTo(0.0);
    assertThat(image.getPixelLuminosity(0, 1)).isEqualTo(0.0);
    assertThat(image.getPixelLuminosity(1, 0)).isEqualTo(1.0);
    assertThat(image.getPixelLuminosity(2, 1)).isEqualTo(1.0);
  }
}
