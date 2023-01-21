package com.example.testfx.model;

import com.example.testfx.kbot.InputAction;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class InputActionModel {
    public SimpleStringProperty type;
    public SimpleLongProperty time;  // current time
    public SimpleIntegerProperty keyCode; // raw input
    public SimpleIntegerProperty xPos;
    public SimpleIntegerProperty yPos;
    public SimpleStringProperty description;

    public InputActionModel(InputAction inputAction){
        this.type = new SimpleStringProperty(inputAction.type);
        this.time = new SimpleLongProperty(inputAction.time);
        this.keyCode = new SimpleIntegerProperty(inputAction.keyCode);
        this.xPos = new SimpleIntegerProperty(inputAction.xPos);
        this.yPos = new SimpleIntegerProperty(inputAction.yPos);
        this.description = new SimpleStringProperty(inputAction.description);
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

    public int getXPos(){
        return xPos.get();
    }

    public int getYPos(){
        return yPos.get();
    }

    public String getDescription(){
        return description.get();
    }

}