package com.example.testfx;

import com.example.testfx.kbot.InputAction;
import javafx.event.Event;
import javafx.event.EventType;

public class KeyLogEvent extends Event {
    public InputAction inputAction;

    public static final EventType<KeyLogEvent> RESET =
            new EventType<>(Event.ANY, "RESET");
    public static final EventType<KeyLogEvent> KEY =
            new EventType<>(Event.ANY, "KEY");

    public static final EventType<KeyLogEvent> RUN_COMMAND =
            new EventType<>(Event.ANY, "RUN_COMMAND");


    public KeyLogEvent(EventType<? extends Event> eventType, InputAction inputAction) {
        super(eventType);
        this.inputAction = inputAction;
    }
}