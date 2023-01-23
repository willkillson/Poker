package com.example.testfx;

import com.example.testfx.kbot.InputAction;
import javafx.event.Event;
import javafx.event.EventType;

public class NativeKeyEvent extends Event {
    public InputAction inputAction;

    public static final EventType<NativeKeyEvent> RESET =
            new EventType<>(Event.ANY, "RESET");
    public static final EventType<NativeKeyEvent> KEY =
            new EventType<>(Event.ANY, "KEY");

    public static final EventType<NativeKeyEvent> RUN_COMMAND =
            new EventType<>(Event.ANY, "RUN_COMMAND");


    public NativeKeyEvent(EventType<? extends Event> eventType, InputAction inputAction) {
        super(eventType);
        this.inputAction = inputAction;
    }
}