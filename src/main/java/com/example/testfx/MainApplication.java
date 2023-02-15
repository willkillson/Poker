package com.example.testfx;

import com.example.testfx.kbot.InputTest;
import com.example.testfx.kbot.vision.Vision;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class MainApplication extends Application {
    public CurrentCommandTable table;
    public Canvas canvas;

    public Vision vision;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws FileNotFoundException, AWTException {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root,1010,945);
        stage.setTitle("kbot");
        stage.setResizable(false);

        try{
            this.vision = new Vision();
        }catch (Exception e){
            e.printStackTrace();
        }


        InputTest inputTest = new InputTest(this);
        this.table = new CurrentCommandTable(inputTest);



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

            private ObjectProperty<Image> imageToBeDrawn = new SimpleObjectProperty<Image>();

            private Robot robot = new Robot();
            private Rectangle rect = new Rectangle(2560,1440);

            @Override
            public void run() {
                imageToBeDrawn.addListener(event -> addImageToCanvas(imageToBeDrawn.get()));
                while(true){
                    BufferedImage bi = robot.createScreenCapture(rect);

//                    try {
//                        BufferedImage bufferedImage = vision.executeMatch(bi);
////                        Image imageIconImage = imageIcon.getImage();
////                        BufferedImage bufferedImage = (BufferedImage) imageIconImage;
//                        WritableImage writeableImage = SwingFXUtils.toFXImage(bufferedImage, null);
//                        imageToBeDrawn.set(writeableImage);
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }

                    WritableImage image = SwingFXUtils.toFXImage(bi, null);
                    imageToBeDrawn.set(image);
                }
            }
        });

        thread.setDaemon(true);
        thread.start();

        root.setTop(this.table.hBox);

        stage.setScene(scene);
        stage.show();
    }

    void addImageToCanvas(Image img){
        Platform.runLater(new Runnable(){

            @Override
            public void run() {
                int baseSize = 500;
                GraphicsContext gc = canvas.getGraphicsContext2D();
                double ratio = 2560 /1440;
                gc.drawImage(img, 0, 0, (baseSize + (int)(baseSize*ratio)), baseSize);
            }});
    }

} 