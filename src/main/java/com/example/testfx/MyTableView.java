package com.example.testfx;
import javafx.collections.ListChangeListener;
import javafx.scene.control.TableView;

public class MyTableView extends TableView {

    public MyTableView(TableViewSample tableViewSample){
        addEventHandler(
                KeyLogEvent.KEY,
                new KeyLogEventHandler(tableViewSample));
        addEventHandler(
                KeyLogEvent.RESET,
                new KeyLogEventHandler(tableViewSample));


    }
}
