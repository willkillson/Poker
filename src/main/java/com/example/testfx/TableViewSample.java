package com.example.testfx;

import com.example.testfx.kbot.InputTest;
import com.example.testfx.model.InputActionModel;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class TableViewSample extends Application {

    public MyTableView table;
    final ObservableList<InputActionModel> data2 =
            FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        this.table = new MyTableView(this);
        InputTest inputTest = new InputTest(this);

        Scene scene = new Scene(new Group());
        stage.setTitle("kbot");
        stage.setWidth(1010);
        stage.setHeight(550);

        final Label label = new Label("Current Commands");
        label.setFont(new Font("Arial", 20));

        table.setEditable(true);

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

        TableColumn<InputActionModel, String> xPos = new TableColumn<>("Mouse X Position");
        xPos.setMinWidth(150);
        xPos.setCellValueFactory(
                new PropertyValueFactory<>("xPos"));

        TableColumn<InputActionModel, String> yPos = new TableColumn<>("Mouse Y Position");
        yPos.setMinWidth(150);
        yPos.setCellValueFactory(
                new PropertyValueFactory<>("yPos"));

        TableColumn<InputActionModel, String> description = new TableColumn<>("Description");
        description.setMinWidth(150);
        description.setCellValueFactory(
                new PropertyValueFactory<>("description"));

        table.setItems(data2);

        table.getColumns().addAll(type, time, keyCode, xPos, yPos, description);

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
        playButton.setOnAction(event -> {
            inputTest.inputManager.saveActions();
        });

        //Creating the stop button
        Button stopButton = new Button("Load Commands");

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
        vboxCommands.setMargin(stopButton, new Insets(1, 2, 2, 2));
        vboxCommands.setMargin(resetButton, new Insets(1, 2, 2, 2));

        //retrieving the observable list of the HBox
        ObservableList list = vboxCommands.getChildren();

        //Adding all the nodes to the observable list (HBox)
        list.addAll(textArea, playButton, stopButton, resetButton);

        //TABLE
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);

        HBox finalHBox = new HBox();

        finalHBox.getChildren().addAll(vbox);
        finalHBox.getChildren().addAll(vboxCommands);

        ((Group) scene.getRoot()).getChildren().addAll(finalHBox);

        stage.setScene(scene);
        stage.show();
    }

} 