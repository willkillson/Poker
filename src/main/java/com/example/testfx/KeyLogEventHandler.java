package com.example.testfx;

import com.example.testfx.model.InputActionModel;
import javafx.event.EventHandler;

public class KeyLogEventHandler implements EventHandler<KeyLogEvent> {

    private TableViewSample tableViewSample;

    public KeyLogEventHandler(TableViewSample tableViewSample) {
        this.tableViewSample = tableViewSample;
    }

    @Override
    public void handle(KeyLogEvent event) {
        this.tableViewSample.data2.add(new InputActionModel(event.inputAction));
    }
}