package pracaDomowa2a;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class MainPracaDomowa2a extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {

            Group root = new Group();
            Scene scene = new Scene(root, 1100, 780);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

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
                clippedImage.setFill(Color.WHITE);
                clippedImage.setStrokeWidth(3);

                clippedImages.add(clippedImage);
            }

            int y = 0;
            for (int i = 0; i<5;i++) {
                clippedImagesGrid.add(clippedImages.get(y), 0, i);
                clippedImagesGrid.add(clippedImages.get(1+y), 1, i);
                clippedImagesGrid.add(clippedImages.get(2+y), 2, i);
                clippedImagesGrid.add(clippedImages.get(3+y), 3, i);
                clippedImagesGrid.add(clippedImages.get(4+y), 4, i);
                y = y +5;
            }

            VBox buttonsLanelBox = new VBox();
            buttonsLanelBox.getChildren().addAll(buttonBox, text, clippedImagesGrid);

            Rectangle mainPicture = new Rectangle(50, 50, 500, 700);
            mainPicture.setStroke(Color.BLACK);
            mainPicture.setStrokeWidth(4);
            mainPicture.setFill(Color.WHITE);

            LinearGradient linearGradient = new LinearGradient(0.7, 0, 1, 0.7, true, CycleMethod.REFLECT,
                    new Stop(0, Color.BLUE),
                    new Stop(0.5, Color.RED),
                    new Stop(1, Color.GREEN)
            );
            mainPicture.setFill(linearGradient);

            HBox pictureAndInformation = new HBox(10);
            pictureAndInformation.getChildren().addAll(mainPicture, buttonsLanelBox);
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
