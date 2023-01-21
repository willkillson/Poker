package com.example.testfx.kbot;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class InputManager extends Thread {


    private Thread activeThread = null;
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
        Queue<InputAction> newQueue = new LinkedList<>();
        newQueue.addAll(this.currentInputs);
        actionRunner.run(newQueue);
        this.activeThread = actionRunner;
        this.isPaused = false;

    }

    public void loopCurrentInputs(){
        LoopingActionRunner actionRunner = new LoopingActionRunner();

        Queue<InputAction> newQueue = new LinkedList<>();
        newQueue.addAll(this.currentInputs);

        this.isLooping = true;
        this.isPaused = false;
        actionRunner.run(newQueue, this);
        this.activeThread = actionRunner;
    }

    private boolean isValidKey(int keyCode){
        return ! (keyCode >=100 && keyCode <=105);
    }

    public void addKeyboardPress(long currentTime, int keyCode){
        if(!this.isValidKey(keyCode)){
            return;
        }
        long time = currentTime - this.startTime;
        System.out.println("Adding action at time: "+time);
        InputAction ia = new InputAction(time,keyCode, InputAction.KEYBOARD_BUTTON_PRESS);
        this.currentInputs.add(ia);
    }

    public void addKeyboardRelease(long currentTime, int keyCode){
        if(!this.isValidKey(keyCode)){
            return;
        }
        long time = currentTime - this.startTime;
        System.out.println("Adding action at time: "+time);
        InputAction ia = new InputAction(time,keyCode, InputAction.KEYBOARD_BUTTON_RELEASE);
        this.currentInputs.add(ia);
    }

    public void addMouseButtonPress(long currentTime, int keyCode){
        long time = currentTime - this.startTime;
        System.out.println("Adding action at time: "+time);
        InputAction ia = new InputAction(time,keyCode, InputAction.MOUSE_BUTTON_PRESS);
        this.currentInputs.add(ia);
    }

    public void addMouseButtonRelease(long currentTime, int keyCode){
        long time = currentTime - this.startTime;
        System.out.println("Adding action at time: "+time);
        InputAction ia = new InputAction(time,keyCode, InputAction.MOUSE_BUTTON_RELEASE);
        this.currentInputs.add(ia);
    }

    public void addMouseMove(long currentTime, int xPos, int yPos){
        long time = currentTime - this.startTime;

        InputAction ia = new InputAction(time,xPos,yPos, InputAction.MOUSE_MOVEMENT);
        System.out.println(ia);
        this.currentInputs.add(ia);
        //InputAction{type='MOUSE_MOVEMENT', time=2148, keyCode=0, xPos=1124, yPos=504}
    }

    public boolean isRecording(){
        return this.isRecording;
    }

    public void setIsRecording(boolean isRecording){
        this.isRecording = isRecording;
        this.startTime = System.currentTimeMillis();
    }

    public void setIsLooping(boolean isLooping){
        this.isLooping = isLooping;
        this.isPaused = !this.isPaused;
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
