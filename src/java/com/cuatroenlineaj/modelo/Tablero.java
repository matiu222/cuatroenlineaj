/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cuatroenlineaj.modelo;

import java.io.Serializable;

public class Tablero implements Serializable{
    private String id_tablero;
    private Partida id_partida;
    private Usuario ficha;

    public Tablero(String id_tablero, Partida id_partida, Usuario ficha) {
        this.id_tablero = id_tablero;
        this.id_partida = id_partida;
        this.ficha = ficha;
    }

    public String getId_tablero() {
        return id_tablero;
    }

    public void setId_tablero(String id_tablero) {
        this.id_tablero = id_tablero;
    }

    public Partida getId_partida() {
        return id_partida;
    }

    public void setId_partida(Partida id_partida) {
        this.id_partida = id_partida;
    }

    public Usuario getFicha() {
        return ficha;
    }

    public void setFicha(Usuario ficha) {
        this.ficha = ficha;
    }

    @Override
    public String toString() {
        return "Tablero{" + "id_tablero=" + id_tablero + ", id_partida=" + id_partida + ", ficha=" + ficha + '}';
    }
}