package com.example.testfx.kbot;

import com.example.testfx.KeyLogEvent;
import com.example.testfx.TableViewSample;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseListener;
import com.github.kwhat.jnativehook.mouse.NativeMouseMotionListener;
import javafx.application.Application;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InputTest implements
        NativeKeyListener,
        NativeMouseListener,
        NativeMouseMotionListener {

    public final InputManager inputManager;

    private final boolean uiActivated;
    private TableViewSample app;

    public int current_mouse_x;

    public int current_mouse_y;

    public static void main(String[] args) {
        new InputTest(null);
    }

    public InputTest(Application app) {
        if (app == null){
            this.uiActivated = false;
        }else{
            this.uiActivated = true;
            this.app = (TableViewSample) app;
        }

        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);
        logger.setUseParentHandlers(false);

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

        this.inputManager = new InputManager();

    }

    public void loadActions(){
        this.inputManager.resetActions();
        try{
            // create object mapper instance
            ObjectMapper mapper = new ObjectMapper();

            // convert JSON array to list of books
            List<InputAction> commands =
                    Arrays.asList(mapper.readValue(Paths.get("./data/currentInputs.json").toFile(), InputAction[].class));

            // Convert InputActionModel to what is used in the InputManager
            commands.forEach(command -> {
//                InputAction newInputAction = new InputAction(command);
                this.inputManager.currentInputs.add(command);
                if(this.uiActivated){
                    this.app.table.fireEvent(new KeyLogEvent(KeyLogEvent.KEY, command));
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void nativeKeyPressed(NativeKeyEvent e) {
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
            case 102:
                // 6 - numpad
                System.out.println("Playing/Looping recent recording...");
                this.inputManager.loopCurrentInputs();
                break;
            case 100:
                // 4 - numpad
                System.out.println("Reset macro");
                this.inputManager.resetActions();
                this.app.table.fireEvent(new KeyLogEvent(KeyLogEvent.RESET, null));
                break;
            case 101:
                // 5 - numpad
                System.out.println("Toggle Playing/Looping recent recording...");
                this.inputManager.toggleIsPaused();
                break;
            default:
                if(this.inputManager.isRecording()){
                    InputAction ia = this.inputManager.addKeyboardPress(System.currentTimeMillis(),e.getRawCode());
                    if(this.uiActivated){
                        this.app.table.fireEvent(new KeyLogEvent(KeyLogEvent.KEY, ia));
                    }
                }
        }
    }

    public void nativeKeyReleased(NativeKeyEvent e) {
        if(this.inputManager.isRecording()){
            InputAction ia = this.inputManager.addKeyboardRelease(System.currentTimeMillis(),e.getRawCode());
            if(this.uiActivated){
                this.app.table.fireEvent(new KeyLogEvent(KeyLogEvent.KEY, ia));
            }
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
        if(this.inputManager.isRecording()){
            InputAction ia = this.inputManager
                    .addMouseButtonPress(System.currentTimeMillis(),e.getButton(), current_mouse_x, current_mouse_y);
            if(this.uiActivated){
                this.app.table.fireEvent(new KeyLogEvent(KeyLogEvent.KEY, ia));
            }
        }
    }

    @Override
    public void nativeMouseReleased(NativeMouseEvent e) {
        if(this.inputManager.isRecording()){
            InputAction ia = this.inputManager.addMouseButtonRelease(System.currentTimeMillis(),e.getButton(), current_mouse_x, current_mouse_y);
            if(this.uiActivated){
                this.app.table.fireEvent(new KeyLogEvent(KeyLogEvent.KEY, ia));
            }
        }
    }

    @Override
    public void nativeMouseMoved(NativeMouseEvent e) {
        if(this.inputManager.isRecording()){
            InputAction ia = this.inputManager.addMouseMove(System.currentTimeMillis(), e.getX(), e.getY());
            if(this.uiActivated){
                this.app.table.fireEvent(new KeyLogEvent(KeyLogEvent.KEY, ia));
            }
        }
        this.current_mouse_x = e.getX();
        this.current_mouse_y = e.getY();
    }

    @Override
    public void nativeMouseDragged(NativeMouseEvent e) {
    }
}
    