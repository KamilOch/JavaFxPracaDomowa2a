package pracaDomowa2a;

import javafx.scene.image.WritableImage;

public class CuttedElement {

    private WritableImage image;
    private int averageValueOfRed;

    public CuttedElement(WritableImage newImage) {
        this.image = newImage;
        this.averageValueOfRed = calculateAverageValueRed(newImage);
    }

    private int calculateAverageValueRed(WritableImage newImage) {

        int pixel;
        int redValue = 0;
        int width = (int) newImage.getWidth();
        int height = (int) newImage.getHeight();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                pixel = newImage.getPixelReader().getArgb(x, y);
                redValue += (pixel >> 16) & 0xff;
            }
        }

        return redValue / (width * height);
    }

    public int getAverageValueOfRed() {
        return averageValueOfRed;
    }

    public WritableImage getImage() {
        return image;
    }
}
