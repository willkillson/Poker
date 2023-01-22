package com.example.testfx.model;

import com.example.testfx.kbot.InputAction;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class InputActionModel {
    private final SimpleStringProperty type;
    private final SimpleLongProperty time;  // current time
    private final SimpleIntegerProperty keyCode; // raw input
    private final SimpleIntegerProperty x;
    private final SimpleIntegerProperty y;
    private final SimpleStringProperty description;

    public InputActionModel(InputAction inputAction){
        this.type = new SimpleStringProperty(inputAction.getType());
        this.time = new SimpleLongProperty(inputAction.getTime());
        this.keyCode = new SimpleIntegerProperty(inputAction.getKeyCode());
        this.x = new SimpleIntegerProperty(inputAction.getX());
        this.y = new SimpleIntegerProperty(inputAction.getY());
        this.description = new SimpleStringProperty(inputAction.getDescription());
    }

    // You must use these setters and getters.
    public String getType() {
        return type.get();
    }

    public long getTime() {
        return time.get();
    }

    public int getKeyCode(){
        return keyCode.get();
    }

    public int getX(){
        return x.get();
    }

    public int getY(){
        return y.get();
    }

    public String getDescription(){
        return description.get();
    }

}
