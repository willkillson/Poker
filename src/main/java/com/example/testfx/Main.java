package com.example.testfx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Random;

public class Main extends Application {

    Canvas canvas;

    @Override
    public void start(Stage primaryStage) {
        try {
            BorderPane root = new BorderPane();
            Scene scene = new Scene(root,400,400);

            canvas = new Canvas();

            Pane pane = new Pane();
            pane.getChildren().add(canvas);
            canvas.widthProperty().bind(pane.widthProperty());
            canvas.heightProperty().bind(pane.heightProperty());
            root.setCenter(pane);


            Thread thread = new Thread(new Runnable() {
                InputStream stream = new FileInputStream("./data/images/currentInputs/needles/image_001.png");
                InputStream stream2 = new FileInputStream("./data/images/currentInputs/needles/image_002.png");
                InputStream stream3 = new FileInputStream("./data/images/currentInputs/needles/image_003.png");
                Image image = new Image(stream);
                Image image2 = new Image(stream2);
                Image image3 = new Image(stream3);

                private ObjectProperty<Image> imageToBeDrawn = new SimpleObjectProperty<Image>(image);
                private ObjectProperty<Image> imageToBeDrawn2 = new SimpleObjectProperty<Image>(image2);
                private ObjectProperty<Image> imageToBeDrawn3 = new SimpleObjectProperty<Image>(image3);

                private Robot robot = new Robot();
                private Rectangle rect = new Rectangle(2560,1440);

                @Override
                public void run() {



                    // Option 2: Listen to the width and height property change of the canvas and redraw the image
                    canvas.widthProperty().addListener(event -> addImageToCanvas(imageToBeDrawn.get()));
                    canvas.heightProperty().addListener(event -> addImageToCanvas(imageToBeDrawn.get()));

                    imageToBeDrawn.addListener(event -> addImageToCanvas(imageToBeDrawn.get()));

                    while(true){
                        BufferedImage bi = robot.createScreenCapture(rect);
                        WritableImage image = SwingFXUtils.toFXImage(bi, null);

                        Random rand = new Random();
                        imageToBeDrawn.set(image);

//                        try {
//                            Thread.sleep(1000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
                    }
                }
            });

            thread.setDaemon(true);
            thread.start();

            primaryStage.setScene(scene);

            // Option 2: start the Thread only when the stage is showing
//          primaryStage.showingProperty().addListener(new ChangeListener<Boolean>() {
//
//              @Override
//              public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
//                  if(newValue)
//                      thread.start();
//
//              }
//          });

            primaryStage.show();



        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    void addImageToCanvas(Image img){
        Platform.runLater(new Runnable(){

            @Override
            public void run() {

                GraphicsContext gc = canvas.getGraphicsContext2D();
                gc.drawImage(img, 0, 0, canvas.getWidth(), canvas.getHeight());
            }});
    }

    public static void main(String[] args) {
        launch(args);
    }
}