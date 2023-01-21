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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.*;

public class TableViewSample extends Application {

    public MyTableView table;
    final ObservableList<InputActionModel> data2 =
            FXCollections.observableArrayList();

    public static void main(String[] args) throws AWTException {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws AWTException {
        this.table = new MyTableView(this);

        //    final HBox hb = new HBox();
        InputTest inputTest = new InputTest(this);

        Scene scene = new Scene(new Group());
        stage.setTitle("Table View Sample");
        stage.setWidth(950);
        stage.setHeight(550);

        final Label label = new Label("Address Book");
        label.setFont(new Font("Consolas", 20));

        table.setEditable(true);

        TableColumn<InputActionModel, String> type = new TableColumn<>("Type");
        type.setMinWidth(150);
        type.setCellValueFactory(
                new PropertyValueFactory<>("type"));

        TableColumn<InputActionModel, String> time = new TableColumn<>("Time");
        time.setMinWidth(50);
        time.setCellValueFactory(
                new PropertyValueFactory<>("time"));

        TableColumn<InputActionModel, String> keyCode = new TableColumn<InputActionModel, String>("Key Code");
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


        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();
    }

} 