package com.example.testfx.kbot;

import java.util.LinkedList;
import java.util.Queue;

public class InputManager {
    private Queue<InputAction> currentInputs;
    private long startTime;
    private boolean isRecording = false;
    private boolean isPaused = false;
    private boolean isLooping = false;


    public InputManager(){
        this.resetActions();
    }

    public void resetActions(){
        this.currentInputs = new LinkedList<>();
        this.startTime = System.currentTimeMillis();
        this.isRecording = false;
    }

    public void playCurrentInputs(){
        ActionRunner actionRunner = new ActionRunner();
        Queue<InputAction> newQueue = new LinkedList<>(this.currentInputs);
        actionRunner.run(newQueue);
        this.isPaused = false;
    }

    public void loopCurrentInputs(){
        this.isLooping = true;
        this.isPaused = false;
        Thread thread = new Thread(
                new LoopingActionRunner(this,
                        new LinkedList<>(this.currentInputs)));
        thread.start();
    }

    private boolean isValidKey(int keyCode){
        return keyCode >= 100 && keyCode <= 105;
    }

    public InputAction addKeyboardPress(long currentTime, int keyCode){
        long time = currentTime - this.startTime;
        if(this.isValidKey(keyCode)){
            return new InputAction(time,keyCode, InputAction.KEYBOARD_BUTTON_RELEASE, "Invalid key detected.");
        }
        System.out.println("Adding action at time: "+time);
        InputAction ia = new InputAction(time,keyCode, InputAction.KEYBOARD_BUTTON_PRESS);
        this.currentInputs.add(ia);
        return ia;
    }

    public InputAction addKeyboardRelease(long currentTime, int keyCode){
        long time = currentTime - this.startTime;
        if(this.isValidKey(keyCode)){
            return new InputAction(time,keyCode, InputAction.KEYBOARD_BUTTON_RELEASE, "Invalid key detected.");
        }
        System.out.println("Adding action at time: "+time);
        InputAction ia = new InputAction(time,keyCode, InputAction.KEYBOARD_BUTTON_RELEASE);
        this.currentInputs.add(ia);
        return ia;
    }

    public InputAction addMouseButtonPress(long currentTime, int keyCode){
        long time = currentTime - this.startTime;
        System.out.println("Adding action at time: "+time);
        InputAction ia = new InputAction(time,keyCode, InputAction.MOUSE_BUTTON_PRESS);
        this.currentInputs.add(ia);
        return ia;
    }

    public InputAction addMouseButtonRelease(long currentTime, int keyCode){
        long time = currentTime - this.startTime;
        System.out.println("Adding action at time: "+time);
        InputAction ia = new InputAction(time,keyCode, InputAction.MOUSE_BUTTON_RELEASE);
        this.currentInputs.add(ia);
        return ia;
    }

    public InputAction addMouseMove(long currentTime, int xPos, int yPos){
        long time = currentTime - this.startTime;

        InputAction ia = new InputAction(time,xPos,yPos, InputAction.MOUSE_MOVEMENT);
        System.out.println(ia);
        this.currentInputs.add(ia);
        //InputAction{type='MOUSE_MOVEMENT', time=2148, keyCode=0, xPos=1124, yPos=504}
        return ia;
    }

    public boolean isRecording(){
        return this.isRecording;
    }

    public void setIsRecording(boolean isRecording){
        this.isRecording = isRecording;
        this.startTime = System.currentTimeMillis();
    }

    public boolean isLooping(){
        return this.isLooping;
    }

    public void setIsPaused(){
        this.isPaused = !this.isPaused;
    }

    public boolean isPaused(){
        return this.isPaused;
    }


}
