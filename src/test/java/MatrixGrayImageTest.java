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
}