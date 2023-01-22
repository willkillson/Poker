package com.example.testfx;

import com.example.testfx.model.InputActionModel;
import javafx.application.Platform;
import javafx.event.EventHandler;

public class KeyLogEventHandler implements EventHandler<KeyLogEvent> {

    private final TableViewSample tableViewSample;

    public KeyLogEventHandler(TableViewSample tableViewSample) {
        this.tableViewSample = tableViewSample;
    }

    @Override
    public void handle(KeyLogEvent event) {
        System.out.println(event.getEventType().getName());
        if( event.getEventType().getName().equals("KEY")){

            this.tableViewSample.data2.add(new InputActionModel(event.inputAction));
            Platform.runLater( () -> this.tableViewSample.table.scrollTo(this.tableViewSample.table.getItems().size()-1) );

        }else if (event.getEventType().getName().equals("RESET")){
            this.tableViewSample.data2.remove(0,tableViewSample.data2.size());
        }

    }
}