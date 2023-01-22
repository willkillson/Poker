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
