module com.example.racemania {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires java.sql;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.desktop;
    requires jdk.compiler;


    exports com.example.racemania.view1;
    exports com.example.racemania.view2;
    opens com.example.racemania.view1 to javafx.fxml;
    opens com.example.racemania.view2 to javafx.fxml;
}