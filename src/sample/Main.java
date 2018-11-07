package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.w3c.dom.events.Event;

import java.util.Scanner;


public class Main extends Application {

    int frameCounter = 0;
    String movement;
    Scanner input = new Scanner(System.in);
    int layout = 0;
    @Override
    public void start(Stage primaryStage) throws Exception{

        movement = "Idle";
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
            int modulus;
            if(movement.equals("Walk"))
                modulus = 10;
            else if(movement.equals("Idle"))
                modulus = 15;
            else if (movement.equals("Attack"))
                modulus = 8;
            else
                modulus = 12;
            int spriteNumber = ((frameCounter++) % modulus) + 1;
            String sprite = "male/" + movement + " (" + spriteNumber + ").png";
            final Image image2 = new Image(sprite);
            imv.setImage(image2);
            pictureRegion.setLayoutX(layout);
            if(movement.equals("Walk"))
                layout++;
            if(layout == 270)
                layout = - 125;
        };

        Timeline animation = new Timeline(
                new KeyFrame(Duration.millis(100), eventHandler)
        );

        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();




        primaryStage.setTitle("Zombie");
        primaryStage.setScene(new Scene(pane, 300, 275));
        primaryStage.show();

        GridPane pane2 = new GridPane();
        Button walk = new Button("Walk");
        WalkHandler walkHandler = new WalkHandler();
        walk.setOnAction(walkHandler);
        walk.setPrefSize(100, 100);

        Button idle = new Button("Idle");
        IdleHandler idleHandler = new IdleHandler();
        idle.setOnAction(idleHandler);
        idle.setPrefSize(100, 100);

        Button attack = new Button("Attack");
        AttackHandler attackHandler = new AttackHandler();
        attack.setOnAction(attackHandler);
        attack.setPrefSize(100, 100);

        Button dead = new Button();
        DeadHandler deadHandler = new DeadHandler();
        dead.setOnAction(deadHandler);
        dead.setPrefSize(100, 100);
        Image skull = new Image("skull.jpg");
        dead.setStyle("-fx-background-image: url('/skull.jpg')");


        pane2.add(walk, 0, 0);
        pane2.add(idle, 0, 1);
        pane2.add(attack, 1, 0);
        pane2.add(dead, 1, 1);
        Stage secondaryStage = new Stage();
        secondaryStage.setScene(new Scene(pane2, 200, 200));
        secondaryStage.setX(400);
        secondaryStage.setY(250);
        secondaryStage.show();


    }

    class WalkHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e){
            movement = "Walk";
        }
    }

    class IdleHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e){
            movement = "Idle";
        }
    }

    class AttackHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e){
            movement = "Attack";
        }
    }

    class DeadHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e){
            movement = "Dead";
        }
    }





    public static void main(String[] args) {
        launch(args);
    }
}
