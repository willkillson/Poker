package com.example.testfx.kbot;

import com.example.testfx.model.InputActionModel;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

import java.io.File;
import java.util.*;

public class InputManager {
    public Queue<InputAction> currentInputs;
    private long startTime;
    private boolean isRecording = false;
    private volatile boolean isPaused = false;
    private Thread runThread = null;

    public InputManager(){
        this.resetActions();
    }

    public void saveActions(){
        try{
            ObjectMapper mapper = JsonMapper.builder()
                    .configure(MapperFeature.CAN_OVERRIDE_ACCESS_MODIFIERS, true)
                    .configure(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS,true)
                    .build();
            ArrayList<InputActionModel> list = new ArrayList<>();
//            List<InputAction> list2 = new ArrayList<>();
            this.currentInputs.forEach((inputAction -> {
                InputActionModel ia = new InputActionModel(inputAction);
                list.add(ia);
//                list2.add(new InputAction(inputAction));
            }));
            mapper.writeValue(new File("./data/currentInputs.json"), list);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void resetActions(){
        this.currentInputs = new LinkedList<>();
        this.startTime = System.currentTimeMillis();
        this.isRecording = false;
        if(runThread != null)
            runThread.stop();
    }

    public void loopCurrentInputs(){
        this.isPaused = false;

        if(this.runThread == null || !this.runThread.isAlive()){
            this.runThread = new Thread(
                    new LoopingActionRunner(this));
            this.runThread.start();
        }
    }

    private boolean isValidKey(int keyCode){
        return keyCode >= 100 && keyCode <= 105;
    }

    public InputAction addKeyboardPress(long currentTime, int keyCode){
        long time = currentTime - this.startTime;
        if(this.isValidKey(keyCode)){
            return new InputAction(InputAction.KEYBOARD_BUTTON_PRESS,time,keyCode, -999,-999 ,"Invalid key detected.");
        }
        System.out.println("Adding action at time: "+time);
        InputAction ia = new InputAction(InputAction.KEYBOARD_BUTTON_PRESS,time,keyCode,-999,-999, "");
        this.currentInputs.add(ia);
        return ia;
    }

    public InputAction addKeyboardRelease(long currentTime, int keyCode){
        long time = currentTime - this.startTime;
        if(this.isValidKey(keyCode)){
            return new InputAction(InputAction.KEYBOARD_BUTTON_RELEASE,time,keyCode, -999,-999 ,"Invalid key detected.");
        }
        System.out.println("Adding action at time: "+time);
        InputAction ia = new InputAction(InputAction.KEYBOARD_BUTTON_RELEASE,time,keyCode,-999,-999, "");
        this.currentInputs.add(ia);
        return ia;
    }

    public InputAction addMouseButtonPress(long currentTime, int keyCode, int xPos, int yPos){
        long time = currentTime - this.startTime;
        System.out.println("Adding action at time: "+time);
        InputAction ia = new InputAction(InputAction.MOUSE_BUTTON_PRESS, time,keyCode, xPos,yPos, "");
        this.currentInputs.add(ia);
        return ia;
    }

    public InputAction addMouseButtonRelease(long currentTime, int keyCode, int xPos, int yPos){
        long time = currentTime - this.startTime;
        System.out.println("Adding action at time: "+time);
        InputAction ia = new InputAction(InputAction.MOUSE_BUTTON_RELEASE, time,keyCode,xPos,yPos , "");
        this.currentInputs.add(ia);
        return ia;
    }

    public InputAction addMouseMove(long currentTime, int xPos, int yPos){
        long time = currentTime - this.startTime;
        InputAction ia = new InputAction(InputAction.MOUSE_MOVEMENT, time,-999,xPos,yPos,"" );
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

    public void toggleIsPaused(){
        this.isPaused = !this.isPaused;
    }

    public boolean isPaused(){
        return this.isPaused;
    }


}
