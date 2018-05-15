package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.w3c.dom.css.Rect;

import java.security.Key;
import java.sql.Time;
import java.util.concurrent.RecursiveAction;

public class Main extends Application {
    static int changepic = 0; //zum Bildwechsel und damit man den Start Button nur ein Mal clicken kann
    static int jump=0;

    public void run(Rectangle rect, Image img1, Image img2) {
        if (changepic % 2 == 0)
            rect.setFill(new ImagePattern(img2));
        else
            rect.setFill(new ImagePattern(img1));
        if(rect.getTranslateX()!=0)
            rect.setTranslateX(rect.getTranslateX() + 20);
        changepic++;
        if(changepic%4==0) {
            jumpDown(rect, img1);
        }
    }

    public void jumpUp(Rectangle rect, Image img) {
        rect.setTranslateY(rect.getTranslateY() - 150);
        rect.setFill(new ImagePattern(img));
    }

    public void jumpDown(Rectangle rect, Image img) {
        rect.setTranslateY(120);
        rect.setFill(new ImagePattern(img));
    }

    public void change_character_grosch(Rectangle rect, Image img, Image img2, Image img3){
        ImagePattern imgp1=new ImagePattern(img);
        ImagePattern imgp2= new ImagePattern(img2);
        ImagePattern imgp3= new ImagePattern(img3);

        rect.setFill(imgp1);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Invasion");

        Button btn = new Button("Start");
        btn.setTranslateX(300);
        btn.setTranslateY(-500);

        Button restart = new Button("Restart");
        restart.setTranslateX(300);
        restart.setTranslateY(0);

        Button grosch=new Button("Wechsle zu Grosch");
        grosch.setTranslateY(-500);
        grosch.setTranslateX(-500);


        Image img1 = new Image("file:Cosic1.png");    //rennend 1
        Image img2 = new Image("file:Cosic4.png");    //rennend 2
        Image img3= new Image("file:Cosic3.png");     //springen

        Rectangle obstacle1=new Rectangle(100, 30);
        Rectangle obstacle2=new Rectangle(70, 50);
        Rectangle obstacle3=new Rectangle(800, 45);
        Rectangle obstacle4=new Rectangle(110, 60);
        Rectangle obstacle5=new Rectangle(60, 90);



        Rectangle charakter = new Rectangle(300, 400);
        charakter.setTranslateX(-700);
        charakter.setTranslateY(120);
        charakter.setFill(new ImagePattern(img1));

        final Timeline timeline = new Timeline(new KeyFrame(Duration.millis(300), ae -> run(charakter, img1, img2)));


        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (changepic == 0) {  //der Button darf nur einmal geclickt werden!
                    timeline.setCycleCount(100000000);  //TODO: infinte einbauen!
                    timeline.play();
                }
            }
        });

        restart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //alles reseten
                charakter.setTranslateX(-700);
                charakter.setTranslateY(120);
                changepic = 0;
                timeline.stop();
            }
        });

        grosch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                change_character_grosch(charakter, img1, img2, img3);
            }
        });




        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(btn, restart, grosch, charakter);    //alle Elemente hinzufügen
        Scene scene = new Scene(stackPane);


        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                if(jump==0) {
                    System.out.println("A key was pressed");
                    jumpUp(charakter, img3);
                    jump++;
                }
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode()==KeyCode.ENTER){
                    System.out.println("A was released");
                    jumpDown(charakter, img1);
                    jump--;
                }
            }
        });

        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
