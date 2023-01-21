module com.example.testfx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.datatransfer;
    requires java.desktop;
    requires java.logging;
    requires com.github.kwhat.jnativehook;

    opens com.example.testfx to javafx.fxml;
    exports com.example.testfx;
}