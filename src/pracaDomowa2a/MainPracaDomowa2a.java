package pracaDomowa2a;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class MainPracaDomowa2a extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {

            Group root = new Group();
            Scene scene = new Scene(root, 1100, 780);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

            Canvas canvas = new Canvas(500, 700);
            GraphicsContext gc = canvas.getGraphicsContext2D();

            HBox buttonBox = new HBox(10);
            buttonBox.setPadding(new Insets(55));
            Button loadPictureButton = new Button("Wczytaj");
            Button clearButton = new Button("Czyść");
            buttonBox.getChildren().addAll(loadPictureButton, clearButton);

            Label text = new Label("Wycinki w kolejności malejącej średniej wartości składowej czerwonej.");

            GridPane clippedImagesGrid = new GridPane();
            clippedImagesGrid.addColumn(5);
            clippedImagesGrid.addRow(5);
            clippedImagesGrid.setHgap(20);
            clippedImagesGrid.setVgap(20);

            List<Rectangle> clippedImages = new ArrayList<>();

            for (int i = 0; i <= 25; i++) {

                Rectangle clippedImage = new Rectangle(41, 41);
                clippedImage.setStroke(Color.BLACK);
                clippedImage.setFill(Color.TRANSPARENT);
                clippedImage.setStrokeWidth(3);

                clippedImages.add(clippedImage);
            }

            int y = 0;
            for (int i = 0; i < 5; i++) {
                clippedImagesGrid.add(clippedImages.get(y), 0, i);
                clippedImagesGrid.add(clippedImages.get(1 + y), 1, i);
                clippedImagesGrid.add(clippedImages.get(2 + y), 2, i);
                clippedImagesGrid.add(clippedImages.get(3 + y), 3, i);
                clippedImagesGrid.add(clippedImages.get(4 + y), 4, i);
                y = y + 5;
            }

            VBox buttonsLanelBox = new VBox();
            buttonsLanelBox.getChildren().addAll(buttonBox, text, clippedImagesGrid);

            Image image = new Image(
                    getClass().getResourceAsStream("test2.png"));
            // for tests
            //getClass().getResourceAsStream("test.png"));

            gc.drawImage(image, 30, 30);
            List<WritableImage> cutedImages= new ArrayList<>();

            canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    PixelReader reader = image.getPixelReader();
                    WritableImage newImage = new WritableImage(reader, (int) event.getX() - 41, (int) event.getY() - 41, 41, 41);

                    cutedImages.add(newImage);

                   // int averageValueOfRed = calculateAvetageValueRed(newImage);

                    List<WritableImage> cutedImagesSorter = cutedImages.sortByAverageValueOfRed();

                    for(int i= 0 ; i<clippedImages.size(); i++){
                        clippedImages.get(i).setFill(cutedImagesSorter.get(i));
                    }


                    //TODO

                }

                private List<WritableImage> sortByAverageValueOfRed(){

                    return
                }

                private int calculateAvetageValueRed(WritableImage newImage) {

                    int pixel ;
                    int redValue = 0;
                    int width = (int) newImage.getWidth();
                    int height = (int) newImage.getHeight();

                    for (int x = 0; x < width; x++) {
                        for (int y = 0; y < height; y++) {
                            pixel = newImage.getPixelReader().getArgb(x,y);
                            redValue += (pixel >> 16) & 0xff;
                        }
                    }

                    return redValue / (width * height);
                }
            });

            HBox pictureAndInformation = new HBox(10);
            pictureAndInformation.getChildren().addAll(canvas, buttonsLanelBox);
            pictureAndInformation.setLayoutX(30);
            pictureAndInformation.setLayoutY(30);

            root.getChildren().add(pictureAndInformation);

            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
