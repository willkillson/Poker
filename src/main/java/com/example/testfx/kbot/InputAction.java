package com.example.testfx.kbot;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InputAction {

    /**
     * MOUSE_MOVEMENT
     * MOUSE_BUTTON_PRESS
     * Mouse Button Release
     * Keyboard Button Press
     * Keyboard Button Release
     */
    public static final String MOUSE_MOVEMENT = "MOUSE_MOVEMENT";
    public static final String MOUSE_BUTTON_PRESS = "MOUSE_BUTTON_PRESS";
    public static final String MOUSE_BUTTON_RELEASE = "MOUSE_BUTTON_RELEASE";
    public static final String KEYBOARD_BUTTON_PRESS = "KEYBOARD_BUTTON_PRESS";
    public static final String KEYBOARD_BUTTON_RELEASE = "KEYBOARD_BUTTON_RELEASE";

    private String type = "";
    private long time = 0;  // current time
    private int keyCode = -999; // raw input
    private int x = -999;
    private int y = -999;
    private String description = "";

//    public InputAction(final long time,final int keyCode, final String type){
//        this.keyCode = keyCode;
//        this.time = time;
//        this.type = type;
//    }
//
//    public InputAction(final long time,final int xPos, int yPos,final String type){
//        this.time = time;
//        this.type = type;
//        this.x = xPos;
//        this.y = yPos;
//    }

//    public InputAction(final long time,final int keyCode, final String type, final String description){
//        this.keyCode = keyCode;
//        this.time = time;
//        this.type = type;
//        this.description = description;
//    }

//    public InputAction(InputActionModel inputActionModel){
//        this.x = inputActionModel.getX();
//        this.y = inputActionModel.getY();
//        this.description = inputActionModel.getDescription();
//        this.time = inputActionModel.getTime();
//        this.keyCode = inputActionModel.getKeyCode();
//    }

//    public InputAction(InputAction inputAction){
//        this.type = inputAction.getType();
//        this.keyCode = inputAction.getKeyCode();
//        this.x = inputAction.getX();
//        this.y = inputAction.getY();
//        this.description = inputAction.getDescription();
//        this.time = inputAction.getTime();
//    }

//    public InputAction(InputActionPojo command) {
//        this.type = command.getType();
//        this.keyCode = command.getKeyCode();
//        this.x = command.getX();
//        this.y = command.getY();
//        this.description = command.getDescription();
//        this.time = command.getTime();
//    }

    @Override
    public String toString() {
        return "InputAction{" +
                "type='" + type + '\'' +
                ", time=" + time +
                ", keyCode=" + keyCode +
                ", xPos=" + x +
                ", yPos=" + y +
                '}';
    }

    int getKeyboardCode(){
        if(this.keyCode==160){
            return KeyEvent.VK_SHIFT;
        }else if(this.keyCode==164){
            return KeyEvent.VK_ALT;
        }else if (this.keyCode==162){
            return KeyEvent.VK_CONTROL;
        }
        else{
            return this.keyCode;
        }
    }

    int getMouseButtonCode(){
        if(this.keyCode==1){
            //left button
            return InputEvent.BUTTON1_DOWN_MASK;

        }else if(this.keyCode==2){
            //right button
            return InputEvent.BUTTON3_DOWN_MASK;
        }else{
            return 0;
        }
    }
}
