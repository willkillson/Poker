package com.example.testfx.kbot;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseListener;
import com.github.kwhat.jnativehook.mouse.NativeMouseMotionListener;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InputTest implements
        NativeKeyListener,
        NativeMouseListener,
        NativeMouseMotionListener {//<-- Remember to add the jnativehook library

    private InputManager inputManager;
    private Robot robot = new Robot();
    private int xpos = 0;
    private int ypos = 0;

    private boolean isPrintKey = false;

    public static void main(String args[]) throws AWTException {
        new InputTest();
    }

    public InputTest() throws AWTException {
        //        InputTest.startTime = System.currentTimeMillis();
        //(From here)
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);
        logger.setUseParentHandlers(false);
        //(To there-^)
        try {
            GlobalScreen.registerNativeHook();
        }
        catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());
            System.exit(1);
        }

        GlobalScreen.addNativeKeyListener(this);
        GlobalScreen.addNativeMouseListener(this);
        GlobalScreen.addNativeMouseMotionListener(this);
        //Remember to include this^                     ^- Your class

        this.inputManager = new InputManager();

    }

    public void nativeKeyPressed(NativeKeyEvent e) {
        System.out.println(e.getRawCode());
//        int scale = 10;
//        if(e.getRawCode() == 104){
//            ypos -= 1*scale;
//        }
//        if(e.getRawCode() == 102){
//            xpos += 1*scale;
//        }
//        if(e.getRawCode() == 98){
//            ypos += 1*scale;
//        }
//        if(e.getRawCode() == 100){
//            xpos -= 1*scale;
//        }
//        if(e.getRawCode() == 101){
//            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
//            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
//        }
//
//        if(e.getRawCode() == 104 || e.getRawCode() == 102 || e.getRawCode() == 98 || e.getRawCode() == 100)
//            robot.mouseMove(xpos,ypos);

        switch(e.getRawCode()){
            case 103:
                // 7 - numpad
                System.out.println("Recording...");
                this.inputManager.setIsRecording(true);
                break;
            case 104:
                // 8 - numpad
                System.out.println("Ending Recording...");
                this.inputManager.setIsRecording(false);
                break;
            case 105:
                // 9 - numpad
                System.out.println("Playing recent recording...");
                //x: 1468 y: 571847
                this.inputManager.playCurrentInputs();
                break;
            case 102:
                // 6 - numpad
                System.out.println("Playing/Looping recent recording...");
                this.inputManager.loopCurrentInputs();
                break;
            case 100:
                // 4 - numpad
                System.out.println("Reseting macro");
                this.inputManager.resetActions();
                break;
            case 101:
                // 5 - numpad
                System.out.println("Stopping Playing/Looping recent recording...");
                this.inputManager.setIsPaused();
                break;
            case 99:
                System.out.println("PrintKey");
                this.isPrintKey = !this.isPrintKey;
            default:
                if(this.inputManager.isRecording()==true){
                    this.inputManager.addKeyboardPress(System.currentTimeMillis(),e.getRawCode());
                }
        }
    }

    public void nativeKeyReleased(NativeKeyEvent e) {
        if(this.inputManager.isRecording()==true){
            this.inputManager.addKeyboardRelease(System.currentTimeMillis(),e.getRawCode());
        }
    }

    public void nativeKeyTyped(NativeKeyEvent e) {
//        System.out.println("Key Typed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
    }

    //Mouse
    @Override
    public void nativeMouseClicked(NativeMouseEvent e) {
//        System.out.println("nativeMouseClicked: " + NativeKeyEvent.getKeyText(e.getButton()));
    }

    @Override
    public void nativeMousePressed(NativeMouseEvent e) {
//        System.out.println("nativeMousePressed: " + e.getButton());
        if(this.inputManager.isRecording()==true){
            this.inputManager.addMouseButtonPress(System.currentTimeMillis(),e.getButton());
        }
    }

    @Override
    public void nativeMouseReleased(NativeMouseEvent e) {
//        System.out.println("nativeMouseReleased: " + e.getButton());
        if(this.inputManager.isRecording()==true){
            this.inputManager.addMouseButtonRelease(System.currentTimeMillis(),e.getButton());
        }
    }

    @Override
    public void nativeMouseMoved(NativeMouseEvent e) {
        if(this.isPrintKey){
            System.out.println("x: " + e.getX() + " y: "+e.getY());
        }

        if(this.inputManager.isRecording()==true){
            this.inputManager.addMouseMove(System.currentTimeMillis(), e.getX(), e.getY());
        }
    }

    @Override
    public void nativeMouseDragged(NativeMouseEvent e) {
//        System.out.println("nativeMouseDragged: " + e.getX() + " "+ e.getY());

        if(this.inputManager.isRecording()==true){
            this.inputManager.addMouseMove(System.currentTimeMillis(), e.getX(), e.getY());
        }
    }
}
    