module com.mycompany.crud_pokemon {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.crud_pokemon to javafx.fxml;
    exports com.mycompany.crud_pokemon;
}
