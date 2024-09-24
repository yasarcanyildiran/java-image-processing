import javafx.scene.paint.Color;

/**
 * Created by Arnaud Labourel on 02/10/2018.
 */

public class ByteGrayColor {

  public static final byte WHITE =  Byte.MAX_VALUE;
  public static final byte BLACK =  Byte.MIN_VALUE;


  public static byte ofLuminosity(double luminosity) {
    int range = WHITE - BLACK;
    int level = ((int) Math.round(luminosity * range + 0.49999)) + BLACK;
    return (byte) (Math.max(BLACK,Math.min(WHITE,level)));
  }

  public static double toLuminosity(byte grayLevel) {
    return ((int) grayLevel - BLACK) / 255.0;
  }


  private static final double OPACITY = 1;

  public static Color toColor(byte grayLevel) {
    double component = toLuminosity(grayLevel);
    return new Color(component, component, component, OPACITY);
  }


  public static int compare(byte color1, byte color2) {
    return Byte.compare(color1, color2);
  }

}
