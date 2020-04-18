import com.sun.glass.events.KeyEvent;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javafx.scene.image.Image ;

import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.security.Key;
import java.util.ArrayList;
import java.util.Set;


public class Poker extends Application {

    @Override
    public void start(Stage stage) {
/*        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        Label l = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        Scene scene = new Scene(new StackPane(l), 640, 480);
        stage.setScene(scene);
        stage.show();*/

        Group root = new Group();
        Scene s = new Scene(root, 1280, 960, Color.BLACK);

        final Canvas canvas = new Canvas(1280,960);
        GraphicsContext gc = canvas.getGraphicsContext2D();



        Cards cards = new Cards();


        Set<String> keys = cards.cardMap.keySet();
        ArrayList<ImageView> views = new ArrayList<>();

        int posX = 0;
        int posY = 0;
        for(String ck: keys){
            ImageView iv = new ImageView(cards.cardMap.get(ck));
            iv.setPreserveRatio(true);
            iv.setFitHeight(150);
            iv.setFitWidth(150);
            iv.setX(100*posX);
            iv.setY(150*posY);

            posX++;
            if(posX >=10){
                posX=0;
                posY++;
            }

            views.add(iv);
        }

        for(int i = 0;i< views.size();i++){
            root.getChildren().add(views.get(i));
        }

        stage.setScene(s);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}