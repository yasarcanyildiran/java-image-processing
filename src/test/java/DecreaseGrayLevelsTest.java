import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

class DecreaseGrayLevelsTest {

  @Test
  void testRoundLuminosity_withTwoLevels() {
    DecreaseGrayLevels transform = new DecreaseGrayLevels(2);
    assertThat(transform.roundLuminosity(0.1)).isEqualTo(0.0);
    assertThat(transform.roundLuminosity(0.49)).isEqualTo(0.0);
    assertThat(transform.roundLuminosity(0.51)).isEqualTo(1.0);
    assertThat(transform.roundLuminosity(0.9)).isEqualTo(1.0);
  }

  @Test
  void testRoundLuminosity_withFourLevels() {
    DecreaseGrayLevels transform = new DecreaseGrayLevels(4);
    assertThat(transform.roundLuminosity(0.0)).isCloseTo(0.0, within(1e-9));
    assertThat(transform.roundLuminosity(0.3)).isCloseTo(1.0 / 3, within(1e-9));
    assertThat(transform.roundLuminosity(0.6)).isCloseTo(2.0 / 3, within(1e-9));
    assertThat(transform.roundLuminosity(0.95)).isCloseTo(1.0, within(1e-9));
  }

  @Test
  void testApplyTo_leavesAtMostNbGrayLevelsColors() {
    MatrixGrayImage image = new MatrixGrayImage(256, 1);
    for (int x = 0; x < 256; x++) {
      image.setPixelLuminosity(x / 255.0, x, 0);
    }
    new DecreaseGrayLevels(4).applyTo(image);
    Set<Double> colors = new HashSet<>();
    for (int x = 0; x < 256; x++) {
      colors.add(image.getPixelLuminosity(x, 0));
    }
    assertThat(colors.size()).isEqualTo(4);
  }
}
