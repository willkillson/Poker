package com.example.testfx;
import javafx.scene.control.TableView;

public class MyTableView extends TableView {

    public MyTableView(TableViewSample tableViewSample){
        addEventHandler(
                KeyLogEvent.KEY,
                new KeyLogEventHandler(tableViewSample));
    }
}
