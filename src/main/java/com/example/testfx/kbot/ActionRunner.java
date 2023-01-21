package com.example.testfx.kbot;

import java.awt.*;
import java.util.Queue;

public class ActionRunner extends Thread{

    long prevActionTime  = 0;
    public void run(Queue<InputAction> actions) {

        try{
            // Create an instance of Robot class
            Robot robot = new Robot();

            System.out.println("Starting ActionRunner with "+actions.size() +" actions.");
            Thread.sleep(1000);

            while(!actions.isEmpty()){

                InputAction inputAction = actions.remove();
                System.out.println(inputAction.toString());
                Thread.sleep(inputAction.time - prevActionTime);

                switch (inputAction.getType()) {
                    case InputAction.KEYBOARD_BUTTON_PRESS -> robot.keyPress(inputAction.getKeyboardCode());
                    case InputAction.KEYBOARD_BUTTON_RELEASE -> robot.keyRelease(inputAction.getKeyboardCode());
                    case InputAction.MOUSE_MOVEMENT -> robot.mouseMove(inputAction.xPos, inputAction.yPos);
                    case InputAction.MOUSE_BUTTON_PRESS -> robot.mousePress(inputAction.getMouseButtonCode());
                    case InputAction.MOUSE_BUTTON_RELEASE -> robot.mouseRelease(inputAction.getMouseButtonCode());
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
