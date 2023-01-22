package com.example.testfx.kbot;



import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

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

    public String type;
    public long time;  // current time
    public int keyCode; // raw input
    public int xPos;
    public int yPos;

    public String description = "";

    public InputAction(final long time,final int keyCode, final String type){
        this.keyCode = keyCode;
        this.time = time;
        this.type = type;
    }

    public InputAction(final long time,final int xPos, int yPos,final String type){
        this.time = time;
        this.type = type;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public InputAction(final long time,final int keyCode, final String type, final String description){
        this.keyCode = keyCode;
        this.time = time;
        this.type = type;
        this.description = description;
    }

    public String getType(){
        return this.type;
    }


    @Override
    public String toString() {
        return "InputAction{" +
                "type='" + type + '\'' +
                ", time=" + time +
                ", keyCode=" + keyCode +
                ", xPos=" + xPos +
                ", yPos=" + yPos +
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
            return InputEvent.BUTTON2_DOWN_MASK;
        }else{
            return 0;
        }
    }
}
