import javafx.scene.paint.Color;

import java.io.FileWriter;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;

/**
* Created by Arnaud Labourel on 02/10/2018.
*/
public class MatrixGrayImage implements GrayImage {

private final byte[][] pixels;
private final int width;
private final int height;

@Override
public void setPixelLuminosity(double luminosity, int x, int y) {
pixels[x][y] = ByteGrayColor.ofLuminosity(luminosity);
}

@Override
public double getPixelLuminosity(int x, int y) {
return ByteGrayColor.toLuminosity(pixels[x][y]);
}

@Override
public Color getPixelColor(int x, int y) {
return ByteGrayColor.toColor(pixels[x][y]);
}

@Override
public int getWidth() {
return width;
}

@Override
public int getHeight() {
return height;
}

public MatrixGrayImage(int width, int height){
this.width = width;
this.height = height;
this.pixels = new byte[width][height];
for (int x = 0; x < width; x++) {
for (int y = 0; y < height; y++) {
pixels[x][y] = ByteGrayColor.WHITE;
}
}
}

/*
Les méthodes suivantes assurent la lecture et l'écriture des fichiers images. Elles sont
correctes, vous ne devriez pas les modifier.
*/

public static MatrixGrayImage createImageFromPGMFile(String fileName) {
// NE PAS MODIFIER !
InputStream file = ClassLoader.getSystemResourceAsStream(fileName);
Scanner scan = new Scanner(file);
scan.nextLine();
scan.nextLine();

int width = scan.nextInt();
int height = scan.nextInt();

MatrixGrayImage result = new MatrixGrayImage(width, height);

scan.nextInt();

for(int y = 0; y < height; y++){
for(int x = 0; x < width; x++) {
double luminosity =
ByteGrayColor.toLuminosity((byte) (scan.nextInt() + ByteGrayColor.BLACK));
result.setPixelLuminosity(luminosity, x, y);
}
}

return result;
}

public void writeIntoPGMFormat(String fileName){
// NE PAS MODIFIER !
try {
FileWriter fileWriter = new FileWriter(fileName);
PrintWriter printWriter = new PrintWriter(fileWriter);
printWriter.println("P2");
printWriter.println("# CREATOR: TP3 Version 2.0");
printWriter.printf("%d %d\n",this.width, this.height);

printWriter.println(pgmCodeOfGrayColor(pixels[0][0]));

for(int y = 0; y < height; y++){
for(int x = 0; x < width; x++) {
printWriter.println(pgmCodeOfGrayColor(getPixelLuminosity(x,y)));
}
}
printWriter.close();
}
catch (Exception e){
e.printStackTrace();
}
}

private static final int PGM_MAXIMUM_CODE = 255;

private int pgmCodeOfGrayColor(double luminosity) {
return (int) (luminosity * (double) PGM_MAXIMUM_CODE);
}
}
