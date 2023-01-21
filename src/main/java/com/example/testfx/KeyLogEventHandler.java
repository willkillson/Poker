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
        System.out.println(event.getEventType().getName());
        if( event.getEventType().getName() == "KEY"){
            this.tableViewSample.data2.add(new InputActionModel(event.inputAction));
        }else if (event.getEventType().getName() == "RESET"){
            this.tableViewSample.data2.remove(0,tableViewSample.data2.size());
        }

    }
}