package com.example.testfx;

import com.example.testfx.model.InputActionModel;
import javafx.application.Platform;
import javafx.event.EventHandler;

import java.util.Optional;

public class KeyLogEventHandler implements EventHandler<KeyLogEvent> {

    private final TableViewSample tableViewSample;

    public KeyLogEventHandler(TableViewSample tableViewSample) {
        this.tableViewSample = tableViewSample;
    }

    @Override
    public void handle(KeyLogEvent event) {
        System.out.println(event.getEventType().getName());

        switch(event.getEventType().getName()){
            case "KEY" -> {
                this.tableViewSample.data2.add(new InputActionModel(event.inputAction));
                // Scroll to the item.
                Platform.runLater( () -> this.tableViewSample.table.scrollTo(this.tableViewSample.table.getItems().size()-1) );
                // Select the item.
                Platform.runLater( () -> this.tableViewSample.table.getSelectionModel().select(this.tableViewSample.table.getItems().size()-1) );
            }
            case "RESET" -> {
                this.tableViewSample.data2.remove(0,tableViewSample.data2.size());
            }
            case "RUN_COMMAND" -> {
                //TODO:
                // Find command,
                // Scroll to the item
                // Select the item


                long time = event.inputAction.getTime();
                Optional<InputActionModel> optional =  this.tableViewSample.data2
                        .stream()
                        .filter((e)-> e.getTime() == time)
                        .findFirst();
                if (optional.isPresent()){
                    InputActionModel inputActionModel = optional.get();
                    // Scroll to the item.
                    Platform.runLater( () -> this.tableViewSample.table.scrollTo(inputActionModel));
                    // Select the item.
                    Platform.runLater( () -> this.tableViewSample.table.getSelectionModel().select(inputActionModel));
                }
            }

        }
    }
}