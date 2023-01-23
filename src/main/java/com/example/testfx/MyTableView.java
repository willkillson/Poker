package com.example.testfx;
import com.example.testfx.model.InputActionModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class MyTableView extends TableView {

    public final VBox vbox;

    public final ObservableList<InputActionModel> data2 =
            FXCollections.observableArrayList();

    public MyTableView(){

        addEventHandler(
                KeyLogEvent.KEY,
                new KeyLogEventHandler(this));
        addEventHandler(
                KeyLogEvent.RESET,
                new KeyLogEventHandler(this));
        addEventHandler(
                KeyLogEvent.RUN_COMMAND,
                new KeyLogEventHandler(this));

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
        this.vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, this);

    }
}
