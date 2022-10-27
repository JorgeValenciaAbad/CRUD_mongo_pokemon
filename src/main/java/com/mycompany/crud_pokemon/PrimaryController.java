package com.mycompany.crud_pokemon;

import DataBase.ConnectionDB;
import com.mongodb.MongoClient;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import com.google.gson.Gson;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.bson.Document;

public class PrimaryController {

    @FXML
    private TextField num;
    @FXML
    private TextField nombre;
    @FXML
    private TextField skill;
    @FXML
    public ChoiceBox tipo;
    @FXML
    private Button btModi;
    @FXML
    private Button btNew;
    @FXML
    private Button btDel;
    @FXML
    public Button btSave;
    @FXML
    public Button btCan;
    @FXML
    private TableView<Pokemon> table;
    @FXML
    private TableColumn<Pokemon, Integer> tcnum;
    @FXML
    private TableColumn<Pokemon, String> tcnombre;
    @FXML
    private TableColumn<Pokemon, String> tcskill;
    @FXML
    private TableColumn<Pokemon, String> tctipo;

    ArrayList<Pokemon> list = new ArrayList<Pokemon>();

    private MongoClient con = ConnectionDB.conectar();
    private MongoDatabase database = con.getDatabase("Pokemon");
    private MongoCollection col = database.getCollection("Pokedex");
    ObservableList<String> tipos = FXCollections.observableArrayList("Selecciona tipo", "Normal", "Lucha", "Veneno", "Hada", "Agua", "Fuego", "Planta", "Psquico", "Siniestro", "Acero", "Dragrón", "Roca", "Tierra", "Hielo", "Fantasma", "Electrico", "Volador", "Bicho");

    @FXML
    private void initialize() throws IOException {
        tipo.setItems(tipos);
        getPokemons();
        cancelar();

    }

    @FXML
    private void mosrtrarDatos() throws IOException {
        if (this.table != null) {
            List<Pokemon> tablaPokemon = this.table.getSelectionModel().getSelectedItems();
            final Pokemon poke = tablaPokemon.get(0);
            String id = " " + poke.get_id() + " ";
            this.num.setText(id.trim());
            this.nombre.setText(poke.getName());
            this.skill.setText(poke.getHabilidad());
            this.tipo.setValue(poke.getTipo());
            
            this.nombre.setDisable(false);
            this.skill.setDisable(false);
            this.tipo.setDisable(false);
            this.btDel.setDisable(false);
            this.btModi.setDisable(false);
        }

    }

    @FXML
    private void insertData() throws IOException {

        Document data = new Document();
        data.append("_id", Integer.parseInt(num.getText()));
        data.append("name", nombre.getText().toString());
        data.append("tipo", tipo.getValue().toString());
        data.append("habilidad", skill.getText().toString());

        col.insertOne(data);
        limpiar();
        getPokemons();
        cancelar();

    }

    @FXML
    private void nuevo() {
        this.btCan.setVisible(true);
        this.btSave.setVisible(true);
        this.num.setDisable(false);
        this.btNew.setVisible(false);
        this.nombre.setDisable(false);
        this.skill.setDisable(false);
        this.tipo.setDisable(false);
        this.btDel.setDisable(true);
        this.btModi.setDisable(true);
        this.tipo.setValue("Selecciona tipo");
    }

    @FXML
    private void cancelar() {
        this.btCan.setVisible(false);
        this.btSave.setVisible(false);
        this.num.setDisable(true);
        this.nombre.setDisable(true);
        this.skill.setDisable(true);
        this.tipo.setDisable(true);
        this.btDel.setDisable(true);
        this.btModi.setDisable(true);
        this.btNew.setVisible(true);
    }

    private void limpiar() {

        this.num.setText("");
        this.nombre.setText("");
        this.skill.setText("");
        this.tipo.setValue("Selecciona tipo");
    }

    @FXML
    private void modifyData() throws IOException {
        Document data = new Document();
        data.append("name", nombre.getText().toString());
        data.append("tipo", tipo.getValue().toString());
        data.append("habilidad", skill.getText().toString());

        col.updateOne(new Document ("_id", Integer.valueOf(num.getText())), new Document("$set",data));
        getPokemons();
        limpiar();
        cancelar();

    }

    @FXML
    private void deleteData() throws IOException {

        Document data = new Document();
        data.append("_id", Integer.parseInt(num.getText()));
        data.append("name", nombre.getText().toString());
        data.append("tipo", tipo.getValue().toString());
        data.append("habilidad", skill.getText().toString());

        col.deleteOne(data);
        limpiar();
        cancelar();
        getPokemons();

    }

    @FXML
    private void getPokemons() throws IOException {
        ObservableList<Pokemon> obs = FXCollections.observableArrayList();
        MongoClient con = null;
        MongoDatabase database = null;
        MongoCollection col = null;
        try {
            con = ConnectionDB.conectar();
            database = con.getDatabase("Pokemon");
            col = database.getCollection("Pokedex");
        } catch (Exception e) {
            e.printStackTrace();
        }

        MongoCursor<Document> data = col.find().iterator();
        while (data.hasNext()) {

            Document poke = data.next();

            obs.add(new Pokemon(poke.getInteger("_id"), poke.getString("name"), poke.getString("tipo"), poke.getString("habilidad")));
            this.table.setItems(obs);

            this.tcnum.setCellValueFactory(new PropertyValueFactory("_id"));
            this.tcnombre.setCellValueFactory(new PropertyValueFactory("name"));
            this.tctipo.setCellValueFactory(new PropertyValueFactory("tipo"));
            this.tcskill.setCellValueFactory(new PropertyValueFactory("habilidad"));
        }
    }

}
