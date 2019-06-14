/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cuatroenlineaj.modelo;

import java.io.Serializable;

public class Partida implements Serializable{
    private String id_partida;
    private int numerojugadores=4;
    private Tablero id_tablero;
    private Double tiempo_jugador1;
    private Double tiempo_jugador2;

    public Partida(String id_partida, Tablero id_tablero, Double tiempo_jugador2) {
        this.id_partida = id_partida;
        this.id_tablero = id_tablero;
        this.tiempo_jugador2 = tiempo_jugador2;
    }

    public String getId_partida() {
        return id_partida;
    }

    public void setId_partida(String id_partida) {
        this.id_partida = id_partida;
    }

    public int getNumerojugadores() {
        return numerojugadores;
    }

    public void setNumerojugadores(int numerojugadores) {
        this.numerojugadores = numerojugadores;
    }

    public Tablero getId_tablero() {
        return id_tablero;
    }

    public void setId_tablero(Tablero id_tablero) {
        this.id_tablero = id_tablero;
    }

    public Double getTiempo_jugador1() {
        return tiempo_jugador1;
    }

    public void setTiempo_jugador1(Double tiempo_jugador1) {
        this.tiempo_jugador1 = tiempo_jugador1;
    }

    public Double getTiempo_jugador2() {
        return tiempo_jugador2;
    }

    public void setTiempo_jugador2(Double tiempo_jugador2) {
        this.tiempo_jugador2 = tiempo_jugador2;
    }

    @Override
    public String toString() {
        return "Partida{" + "id_partida=" + id_partida + ", numerojugadores=" + numerojugadores + ", id_tablero=" + id_tablero + ", tiempo_jugador1=" + tiempo_jugador1 + ", tiempo_jugador2=" + tiempo_jugador2 + '}';
    }
}
