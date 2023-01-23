package com.example.testfx;

import com.example.testfx.kbot.InputTest;
import com.example.testfx.model.InputActionModel;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TableViewSample extends Application {

    public MyTableView table;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        this.table = new MyTableView();
        InputTest inputTest = new InputTest(this);

        Scene scene = new Scene(new Group());
        stage.setTitle("kbot");
        stage.setWidth(1010);
        stage.setHeight(550);


        //creating a text field
        TextArea textArea = new TextArea("""
                Num-7: Start Recording
                Num-8: Stop Recording
                Num-9: Loop commands once
                Num-6: Loop commands continuously
                Num-5: Stop Execution""");
        textArea.setEditable(false);
        textArea.setPrefWidth(215);

        //Creating the play button
        Button playButton = new Button("Save Commands");
        playButton.setOnAction(event -> inputTest.inputManager.saveActions());

        //Creating the stop button
        Button loadCommandsButton = new Button("Load Commands");
        loadCommandsButton.setOnAction(event -> inputTest.loadActions());


        //Creating the resetButton button
        Button resetButton = new Button("Reset Commands");

        resetButton.setOnAction(event -> {
            inputTest.inputManager.resetActions();
            table.data2.remove(0,table.data2.size());
        });

        //Instantiating the HBox class
        VBox vboxCommands = new VBox();

        //Setting the space between the nodes of a HBox pane
        vboxCommands.setSpacing(10);

        //Setting the margin to the nodes
        vboxCommands.setMargin(textArea, new Insets(40, 2, 2, 2));
        vboxCommands.setMargin(playButton, new Insets(1, 2, 2, 2));
        vboxCommands.setMargin(loadCommandsButton, new Insets(1, 2, 2, 2));
        vboxCommands.setMargin(resetButton, new Insets(1, 2, 2, 2));

        //retrieving the observable list of the HBox
        ObservableList list = vboxCommands.getChildren();

        //Adding all the nodes to the observable list (HBox)
        list.addAll(textArea, playButton, loadCommandsButton, resetButton);

        HBox finalHBox = new HBox();

        finalHBox.getChildren().addAll(table.vbox);
        finalHBox.getChildren().addAll(vboxCommands);

        ((Group) scene.getRoot()).getChildren().addAll(finalHBox);

        stage.setScene(scene);
        stage.show();
    }

} 