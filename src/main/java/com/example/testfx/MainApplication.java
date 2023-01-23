package com.example.testfx;

import com.example.testfx.kbot.InputTest;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApplication extends Application {
    public CurrentCommandTable table;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setTitle("kbot");
        stage.setWidth(1010);
        stage.setHeight(550);

        InputTest inputTest = new InputTest(this);
        this.table = new CurrentCommandTable(inputTest);

        ((Group) scene.getRoot()).getChildren().addAll(this.table.hBox);

        stage.setScene(scene);
        stage.show();
    }

} 