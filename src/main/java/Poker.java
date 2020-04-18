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

        Image image = null;
        try{

            image = new Image(new FileInputStream("resources/cards/2C.png"));

        }catch(Exception ecp){
            ecp.printStackTrace();
        }

        ImageView iv = new ImageView(image);
        iv.setPreserveRatio(true);
        iv.setFitHeight(100);
        iv.setFitWidth(100);


        //gc.drawImage(image,0,0);



/*        gc.setFill(Color.BLUE);
        gc.fillRect(75,75,100,100);*/

        root.getChildren().add(iv);



        stage.setScene(s);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}