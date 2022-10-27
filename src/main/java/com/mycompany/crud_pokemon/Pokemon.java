/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crud_pokemon;



/**
 *
 * @author Jorge
 */
public class Pokemon {
    public int _id;
    private String name;
    private String tipo;
    private String habilidad;


    public Pokemon(int _id, String name, String type, String habilidad) {
        this._id = _id;
        this.name = name;
        this.tipo = type;
        this.habilidad = habilidad;
    }
    



    public String getName() {
        return name;
    }

    public void setName(String Name) {
        this.name = Name;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String Tipos) {
        this.tipo = Tipos;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getHabilidad() {
        return habilidad;
    }

    public void setHabilidad(String habilidad) {
        this.habilidad = habilidad;
    }
}
