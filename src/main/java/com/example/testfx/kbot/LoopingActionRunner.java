package com.example.testfx.kbot;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

public class LoopingActionRunner implements Runnable{

    private final InputManager inputManager;
    long prevActionTime  = 0;

    LoopingActionRunner(InputManager inputManager){
        this.inputManager = inputManager;
    }

    @Override
    public void run() {
        try{

            // Create an instance of Robot class
            Robot robot = new Robot();

            System.out.println("Starting LoopingActionRunner with "+inputManager.currentInputs.size() +" actions.");

            Queue<InputAction> actionsShallowClone = new LinkedList<>(inputManager.currentInputs);

            while(!inputManager.isPaused()){

                if(actionsShallowClone.isEmpty()){
                    // Loop again.
                    actionsShallowClone = new LinkedList<>(inputManager.currentInputs);
                    this.prevActionTime = 0;
                }

                InputAction inputAction = actionsShallowClone.remove();
                long wait = inputAction.getTime() - prevActionTime;

                if( wait >= 0){
                    Thread.sleep(wait);
                }

                switch (inputAction.getType()) {
                    case InputAction.KEYBOARD_BUTTON_PRESS ->
                            robot.keyPress(inputAction.getKeyboardCode());
                    case InputAction.KEYBOARD_BUTTON_RELEASE ->
                            robot.keyRelease(inputAction.getKeyboardCode());
                    case InputAction.MOUSE_MOVEMENT ->
                            robot.mouseMove(inputAction.getX(), inputAction.getY());
                    case InputAction.MOUSE_BUTTON_PRESS ->
                            //robot.mouseMove(inputAction.getX(), inputAction.getY());
                            robot.mousePress(inputAction.getMouseButtonCode());
                    case InputAction.MOUSE_BUTTON_RELEASE ->
                            //robot.mouseMove(inputAction.getX(), inputAction.getY());
                            robot.mouseRelease(inputAction.getMouseButtonCode());
                }

                prevActionTime = inputAction.getTime();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
