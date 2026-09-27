import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class MatrixGrayImageTest {

@Test
void testGetWidth() {
assertThat(new MatrixGrayImage(0,0).getWidth()).isEqualTo(0);
assertThat(new MatrixGrayImage(10,20).getWidth()).isEqualTo(10);
assertThat(new MatrixGrayImage(400,300).getWidth()).isEqualTo(400);
}

@Test
void testGetHeight() {
assertThat(new MatrixGrayImage(0,0).getHeight()).isEqualTo(0);
assertThat(new MatrixGrayImage(10,20).getHeight()).isEqualTo(20);
assertThat(new MatrixGrayImage(400,300).getHeight()).isEqualTo(300);
}

@Test
void testGetPixel_whenPixelHasBeenSet() {
MatrixGrayImage image = new MatrixGrayImage(10, 10);
image.setPixelLuminosity(0.2, 1, 1);
assertThat(image.getPixelLuminosity(1,1)).isEqualTo(0.2);
image.setPixelLuminosity(0.8, 3, 9);
assertThat(image.getPixelLuminosity(3,9)).isEqualTo(0.8);
}

@Test
void testNewImageIsWhite() {
MatrixGrayImage image = new MatrixGrayImage(5, 3);
for (int x = 0; x < 5; x++) {
for (int y = 0; y < 3; y++) {
assertThat(image.getPixelLuminosity(x, y)).isEqualTo(1.0);
}
}
}

@Test
void testSetPixel_doesNotChangeOtherPixels() {
MatrixGrayImage image = new MatrixGrayImage(4, 4);
image.setPixelLuminosity(0.0, 2, 1);
assertThat(image.getPixelLuminosity(2, 1)).isEqualTo(0.0);
assertThat(image.getPixelLuminosity(1, 2)).isEqualTo(1.0);
}
}
