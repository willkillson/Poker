package com.example.testfx.kbot;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

public class LoopingActionRunner implements Runnable{

    private InputManager inputManager;
    private Queue<InputAction> actions;
    long prevActionTime  = 0;

    LoopingActionRunner(InputManager inputManager, Queue<InputAction> actions){
        this.inputManager = inputManager;
        this.actions = actions;
    }

    @Override
    public void run() {
        try{

            // Create an instance of Robot class
            Robot robot = new Robot();

            System.out.println("Starting LoopingActionRunner with "+actions.size() +" actions.");

            Queue<InputAction> actionsShallowClone = new LinkedList<>(actions);

            while(true){

                if(inputManager.isPaused()){
//                    System.out.println(inputManager.isPaused());
                    continue;
                }
                if(actionsShallowClone.isEmpty()){
                    // Loop again.
                    actionsShallowClone = new LinkedList<>(actions);
                    this.prevActionTime = 0;
                }
                InputAction inputAction = actionsShallowClone.remove();
                Thread.sleep(inputAction.getTime() - prevActionTime);
                switch (inputAction.getType()) {
                    case InputAction.KEYBOARD_BUTTON_PRESS -> robot.keyPress(inputAction.getKeyboardCode());
                    case InputAction.KEYBOARD_BUTTON_RELEASE -> robot.keyRelease(inputAction.getKeyboardCode());
                    case InputAction.MOUSE_MOVEMENT -> robot.mouseMove(inputAction.getXPos(), inputAction.getYPos());
                    case InputAction.MOUSE_BUTTON_PRESS -> robot.mousePress(inputAction.getMouseButtonCode());
                    case InputAction.MOUSE_BUTTON_RELEASE -> robot.mouseRelease(inputAction.getMouseButtonCode());
                }

                prevActionTime = inputAction.getTime();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (AWTException e) {
            System.out.println("Robot issue on: "+e.getMessage());
        }

    }
}
