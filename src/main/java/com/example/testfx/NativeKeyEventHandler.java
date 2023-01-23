package com.example.testfx;

import com.example.testfx.model.InputActionModel;
import javafx.application.Platform;
import javafx.event.EventHandler;

import java.util.Optional;

public class NativeKeyEventHandler implements EventHandler<NativeKeyEvent> {

    private final CurrentCommandTable table;

    public NativeKeyEventHandler(CurrentCommandTable table) {
        this.table = table;
    }

    @Override
    public void handle(NativeKeyEvent event) {
        System.out.println(event.getEventType().getName());

        switch(event.getEventType().getName()){
            case "KEY" -> {
                table.data2.add(new InputActionModel(event.inputAction));
                // Scroll to the item.
                Platform.runLater( () -> table.scrollTo(table.getItems().size()-1) );
                // Select the item.
                Platform.runLater( () -> table.getSelectionModel().select(table.getItems().size()-1) );

            }
            case "RESET" -> {
                table.data2.remove(0,table.data2.size());
            }
            case "RUN_COMMAND" -> {
                //TODO:
                // Find command,
                // Scroll to the item
                // Select the item
                long time = event.inputAction.getTime();
                Optional<InputActionModel> optional =  table.data2
                        .stream()
                        .filter((e)-> e.getTime() == time)
                        .findFirst();
                if (optional.isPresent()){
                    InputActionModel inputActionModel = optional.get();
                    // Scroll to the item.
                    Platform.runLater( () -> table.scrollTo(inputActionModel));
                    // Select the item.
                    Platform.runLater( () -> table.getSelectionModel().select(inputActionModel));
                }
            }

        }
    }
}