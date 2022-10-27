module com.mycompany.crud_pokemon {
    requires javafx.controls;
    requires javafx.fxml;
    requires mongo.java.driver;
    requires com.google.gson;
    opens com.mycompany.crud_pokemon to javafx.fxml;
    exports com.mycompany.crud_pokemon;
    //exports Modelo;
}
