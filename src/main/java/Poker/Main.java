package Poker;

import ConsoleGUI.JConsole;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;

import javafx.embed.swing.SwingNode;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Set;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;


public class Main extends Application {

    private static PokerGame game;
    private static Cards cards;
    public static JConsole console;

    TilePane tilePane;

    Set<String> keys;
    ArrayList<ImageView> views;


    Group delt;
    BorderPane borderPane;

    @Override
    public void start(Stage stage) {

        tilePane = new TilePane();

        this.keys = cards.cardMap.keySet();

        this.delt = new Group();
        this.borderPane = new BorderPane();
        this.views =  new ArrayList<>();

        int posX = 0;
        int posY = 0;
        for(String ck: game.deltCards){
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
        tilePane.getChildren().addAll(views);
        borderPane.setCenter(tilePane);




        /*MyConsole console = new MyConsole();*/

        int MAXCURSORPOS_R = 40;
        int MAXCURSORPOS_C = 100;

        console = new JConsole(MAXCURSORPOS_C,MAXCURSORPOS_R);
        console.setCursorVisible(true);
        console.setCursorBlink(true);


        //console.captureStdOut();
        System.out.println("Captured output");


        SwingNode sn = new SwingNode();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                sn.setContent(console);
            }
        });

        borderPane.setBottom(sn);
        stage.setWidth(1116);
        stage.setHeight(800);
        stage.setResizable(false);
        stage.setScene(new Scene(borderPane, 1280, 960, Color.BLACK));
        stage.show();


       Timeline fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(0.2), new EventHandler<ActionEvent>() {

            @Override
            synchronized public void handle(ActionEvent event) {

                tilePane.getChildren().clear();
                views.clear();
                try{

                    for(String ck: game.deltCards){
                        ImageView iv = new ImageView(cards.cardMap.get(ck));
                        iv.setPreserveRatio(true);
                        iv.setFitHeight(150);
                        iv.setFitWidth(150);
                        tilePane.getChildren().add(iv);
                    }
                    console.write("hello", java.awt.Color.GREEN,java.awt.Color.BLACK );
                    console.repaint();


                }catch(Exception ecp){
                    ecp.printStackTrace();
                }

            }
        }));


        fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
        fiveSecondsWonder.play();

    }

/*    private void createAndSetSwingContent(SwingNode swingNode){
        sw
    }*/


/*    Scene menu(Stage stage){
        ToolBar tb = new ToolBar();
        Button b1 = new Button("Cards");
        tb.getItems().add(b1);

        tb.autosize();
        b1.setOnAction(event -> {stage.setScene(gameScene());});
        return new Scene(tb);
    }*/


    public static void main(String[] args) {
        cards = new Cards();
        game = new PokerGame(cards);
        Thread thread = new Thread(game);
        thread.start();

        launch();
    }

}