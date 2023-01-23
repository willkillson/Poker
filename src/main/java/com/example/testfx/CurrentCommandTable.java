package com.example.testfx;
import com.example.testfx.kbot.InputTest;
import com.example.testfx.kbot.LoopingActionRunner;
import com.example.testfx.model.InputActionModel;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.awt.Robot;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Random;

public class CurrentCommandTable extends TableView {

    public final HBox hBox;

    public final ObservableList<InputActionModel> data2 =
            FXCollections.observableArrayList();

    public CurrentCommandTable(InputTest inputTest) throws FileNotFoundException, AWTException {

        addEventHandler(
                NativeKeyEvent.RESET,
                new NativeKeyEventHandler(this));
        addEventHandler(
                NativeKeyEvent.KEY,
                new NativeKeyEventHandler(this));
        addEventHandler(
                NativeKeyEvent.RUN_COMMAND,
                new NativeKeyEventHandler(this));

        final Label label = new Label("Current Commands");
        label.setFont(new Font("Arial", 20));

        this.setEditable(true);

        TableColumn<InputActionModel, String> type = new TableColumn<>("Type");
        type.setMinWidth(150);
        type.setCellValueFactory(
                new PropertyValueFactory<>("type"));

        TableColumn<InputActionModel, String> time = new TableColumn<>("Time");
        time.setMinWidth(50);
        time.setCellValueFactory(
                new PropertyValueFactory<>("time"));

        TableColumn<InputActionModel, String> keyCode = new TableColumn<>("Key Code");
        keyCode.setMinWidth(50);
        keyCode.setCellValueFactory(
                new PropertyValueFactory<>("keyCode"));

        TableColumn<InputActionModel, String> x = new TableColumn<>("Mouse X Position");
        x.setMinWidth(150);
        x.setCellValueFactory(
                new PropertyValueFactory<>("x"));

        TableColumn<InputActionModel, String> y = new TableColumn<>("Mouse Y Position");
        y.setMinWidth(150);
        y.setCellValueFactory(
                new PropertyValueFactory<>("y"));

        TableColumn<InputActionModel, String> description = new TableColumn<>("Description");
        description.setMinWidth(150);
        description.setCellValueFactory(
                new PropertyValueFactory<>("description"));

        this.setItems(data2);

        this.getColumns().addAll(type, time, keyCode, x, y, description);

        //TABLE
        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, this);


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
            data2.remove(0,data2.size());
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

        //retrieving the observable list of the VBox
        ObservableList list = vboxCommands.getChildren();

        //Adding all the nodes to the observable list (VBox)
        list.addAll(textArea, playButton, loadCommandsButton, resetButton);

        this.hBox = new HBox();
        this.hBox.getChildren().addAll(vbox);
        this.hBox.getChildren().addAll(vboxCommands);


    }

}
