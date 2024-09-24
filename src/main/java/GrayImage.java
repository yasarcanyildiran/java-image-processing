/**
 * Created by Arnaud Labourel on 04/10/2018.
 */

public interface GrayImage extends Image {
    void setPixelLuminosity(double luminosity, int x, int y);
    double getPixelLuminosity(int x, int y);
}
