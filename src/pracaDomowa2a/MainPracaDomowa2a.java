package pracaDomowa2a;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;



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
            buttonBox.getChildren().addAll(loadPictureButton,clearButton);

            Label text = new Label("Wycinki w kolejności malejącej średniej wartości składowej czerwonej.");
      
           Rectangle clippedImage = new Rectangle(100,200,41,41);
           clippedImage.setStroke(Color.BLACK);
           clippedImage.setFill(Color.WHITE);
           clippedImage.setStrokeWidth(3);

    


            VBox buttonsLanelBox = new VBox();
            buttonsLanelBox.getChildren().addAll(buttonBox,text, clippedImage);
            root.getChildren().add(buttonsLanelBox);


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
