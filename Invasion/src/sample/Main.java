package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.awt.Rectangle;

public class Main extends Application {

    private int n=0;

    public void run(javafx.scene.shape.Rectangle rect){
        rect.setTranslateX(rect.getTranslateX()+10);
    }
    public javafx.scene.shape.Rectangle getRectangle(){
        javafx.scene.shape.Rectangle rectangle=new javafx.scene.shape.Rectangle(200,100);

        return rectangle;
    }
    public void buttonPressed(KeyEvent e)
    {
        if(e.getCode().toString().equals("ENTER"))
        {
            //do something
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Button btn=new Button("Start");
        //btn.setTranslateY(750);
        //btn.setTranslateX(-930);
        StackPane stpn= new StackPane();
        stpn.getChildren().add(btn);
        stpn.getStylesheets().add("style.css");
        Scene scene=new Scene(stpn);
        Image img=new Image("file:himmel.jpg");
        javafx.scene.shape.Rectangle rect=new javafx.scene.shape.Rectangle(200,100);

        rect.setTranslateX(-900);
        stpn.getChildren().add(rect);
        stpn.addEventHandler(KeyEvent.ANY, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                Timeline timeline=new Timeline(new KeyFrame(Duration.millis(50),ae-> run(rect)));
                timeline.setCycleCount(91);
                timeline.play();
                timeline.stop();
            }
        });


        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Timeline timeline=new Timeline(new KeyFrame(Duration.millis(50),ae-> run(rect)));
                timeline.setCycleCount(91);
                timeline.play();
            }
        });


        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
