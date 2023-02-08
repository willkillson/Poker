
module com.example.testfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.datatransfer;
    requires java.desktop;
    requires java.logging;
    requires com.github.kwhat.jnativehook;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires javafx.swing;

    requires static lombok;
    requires org.bytedeco.opencv;


    exports com.example.testfx.kbot;
    exports com.example.testfx;
    exports com.example.testfx.model;
    exports com.example.testfx.kbot.vision;



    opens com.example.testfx to javafx.fxml;
    opens com.example.testfx.kbot to javafx.fxml;
    opens com.example.testfx.model to javafx.fxml;
    opens com.example.testfx.kbot.vision to javafx.fxml;


}