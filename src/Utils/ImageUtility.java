package Utils;

import NetworkCore.WeightVector;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

public class ImageUtility {

    public static Image convertFileToJavaFxImages(File file) {
        if (file != null) {
            javafx.scene.image.Image img;
            img = new javafx.scene.image.Image(file.toURI().toString());
            return img;
        }
        return null;

    }

    private static Image convertBufferedImageToFxImage(BufferedImage image) {
        if (image != null) {
            return SwingFXUtils.toFXImage(image, null);
        }
        return null;
    }

    public static double[][] generatePixelArray(File file, int type) {
        if (file != null) {
            try {
                BufferedImage image = ImageIO.read(file);
                Raster raster = image.getData();
                int w = raster.getWidth();
                int h = raster.getHeight();
                double[][] pixels = new double[w][h];
                for (int x = 0; x < w; x++) {
                    for (int y = 0; y < h; y++) {
                        if (type == 12) {
                            pixels[x][y] = raster.getSample(x, y, 0);
                        }
                        if (type == 6) {
                            pixels[x][y] = image.getRGB(x, y);
                        }
                    }
                }
                return pixels;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }


        return null;
    }

    private static int getColor(int pixelValue) {
        int[] color = new int[3];
        color[0] = ((pixelValue >> 16) & 0xff); // red;
        color[1] = ((pixelValue >> 8) & 0xff); // green;
        color[2] = (pixelValue & 0xff); // blue;
        int colorValue = (int) (color[0] * (Math.pow(2, 16)) + color[1] * (Math.pow(2, 8)) + color[2]);
        return colorValue;
    }


    public boolean compare(int[][] pixelArray, int[][] pixelArray1) {
        int count = 0;
        int height = pixelArray.length;
        int width = pixelArray1.length;

        if (width != height) {
            return false;
        } else {
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    if (pixelArray[x][y] == pixelArray1[x][y]) {
                        count++;
                    }
                }
            }
            return count == width * height;
        }
    }

    private BufferedImage generateBinaryImageFromPixels(int[][] pixels) {
        if (pixels.length > 0) {
            BufferedImage image = new BufferedImage(28, 28, BufferedImage.TYPE_BYTE_BINARY);
            WritableRaster raster = image.getRaster();
            for (int x = 0; x < 28; x++) {
                for (int y = 0; y < 28; y++) {
                    raster.setSample(x, y, 0, pixels[x][y]);
                }
            }
            image.setData(raster);
            return image;
        }
        return null;
    }

    private BufferedImage generateColoredImageFromPixels(int[][] pixels) {
        if (pixels.length > 0) {
            BufferedImage image = new BufferedImage(28, 28, BufferedImage.TYPE_INT_ARGB);
            for (int x = 0; x < 28; x++) {
                for (int y = 0; y < 28; y++) {
                    image.setRGB(x, y, pixels[x][y]);
                }
            }
            return image;
        }
        return null;
    }

    private int[][] generate2DArrayFromVector(WeightVector vector) {
        int size = (int) Math.sqrt(vector.size());
        int[][] pixels = new int[size][size];
        int k = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (k == vector.size()) {
                    break;
                } else {
                    pixels[i][j] = (int) Math.round(vector.elementAt(k));
                    k++;
                }
            }
        }
        return pixels;
    }

    public Image returnPixelGeneratedImage(WeightVector vector, int i) {
        if (vector != null) {
            if (i == 12) {
                return (convertBufferedImageToFxImage(generateBinaryImageFromPixels((generate2DArrayFromVector(vector)))));
            }
            if (i == 6) {
                return (convertBufferedImageToFxImage(generateColoredImageFromPixels((generate2DArrayFromVector(vector)))));
            }
        }
        return null;
    }
}
