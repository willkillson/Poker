package com.example.testfx.kbot;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

public class LoopingActionRunner extends Thread{

    long prevActionTime  = 0;


    public void run(Queue<InputAction> actions, InputManager inputManager) {

        try{

            // Create an instance of Robot class
            Robot robot = new Robot();

            System.out.println("Starting LoopingActionRunner with "+actions.size() +" actions.");
            Thread.sleep(1000);

            Queue<InputAction> actionsShallowClone = new LinkedList<>(actions);

            while(inputManager.isLooping()){

                if(inputManager.isPaused()){
                    continue;
                }

                if(actionsShallowClone.isEmpty()){
                    // Loop again.
                    actionsShallowClone = new LinkedList<>(actions);
                    this.prevActionTime = 0;
                }

                InputAction inputAction = actionsShallowClone.remove();
                System.out.println(inputAction.toString());
                Thread.sleep(inputAction.time - prevActionTime);

                switch(inputAction.getType()){
                    case InputAction.KEYBOARD_BUTTON_PRESS:
                        robot.keyPress(inputAction.getKeyboardCode());
                        break;
                    case InputAction.KEYBOARD_BUTTON_RELEASE:
                        robot.keyRelease(inputAction.getKeyboardCode());
                        break;
                    case InputAction.MOUSE_MOVEMENT:
                        robot.mouseMove(inputAction.xPos,inputAction.yPos);
                        break;
                    case InputAction.MOUSE_BUTTON_PRESS:
                        robot.mousePress(inputAction.getMouseButtonCode());
                        break;
                    case InputAction.MOUSE_BUTTON_RELEASE:
                        robot.mouseRelease(inputAction.getMouseButtonCode());
                        break;
                }

                prevActionTime = inputAction.time;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (AWTException e) {
            System.out.println("Robot issue on: "+e.getMessage());
        }

    }
}
