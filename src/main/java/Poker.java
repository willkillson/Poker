import com.sun.glass.events.KeyEvent;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.Set;


public class Poker extends Application {

    @Override
    public void start(Stage stage) {
        stage.setScene(menu(stage));
        stage.show();
    }


    Scene menu(Stage stage){
        ToolBar tb = new ToolBar();
        Button b1 = new Button("Cards");
        tb.getItems().add(b1);

        tb.setScaleX(5);
        tb.setScaleY(5);

        tb.autosize();
        b1.setOnAction(event -> {stage.setScene(gameScene());});
        return new Scene(tb);
    }

    Scene gameScene(){
        Group hand = new Group();
        Group delt = new Group();

        BorderPane borderPane = new BorderPane();

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
                break;
            }

            views.add(iv);
        }

        for(int i = 0;i< views.size();i++){
            delt.getChildren().add(views.get(i));
        }

        for(int i = 0;i< 2;i++){
            hand.getChildren().add(views.get(i));
        }


        //delt.setScaleX(0.4);
        //delt.setScaleY(0.4);



        borderPane.setTop(delt);
        borderPane.setCenter(hand);

        return new Scene(borderPane, 1280, 960, Color.BLACK);

    }

    public static void main(String[] args) {
        launch();
    }

}