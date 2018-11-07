package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Scanner;


public class Main extends Application {

    int frameCounter = 0;
    String movement;
    Scanner input = new Scanner(System.in);
    int layout = 0;
    @Override
    public void start(Stage primaryStage) throws Exception{

        movement = input.next();
        Pane pane = new Pane();
        final ImageView imv = new ImageView();
        final Image img = new Image("male/Walk (1).png");
        imv.setImage(img);
        imv.setFitHeight(200);
        imv.setFitWidth(160);

        final HBox pictureRegion = new HBox();
        pictureRegion.getChildren().add(imv);
        pane.getChildren().add(pictureRegion);

        EventHandler<ActionEvent> eventHandler = event ->{
            int spriteNumber = ((frameCounter++) % 10) + 1;
            String sprite = "male/" + movement + " (" + spriteNumber + ").png";
            final Image image2 = new Image(sprite);
            imv.setImage(image2);
            pictureRegion.setLayoutX(layout++);
        };

        Timeline animation = new Timeline(
                new KeyFrame(Duration.millis(100), eventHandler)
        );

        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();




        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(pane, 300, 275));
        primaryStage.show();

    }




    public static void main(String[] args) {
        launch(args);
    }
}
