package com.mycompany.sopaletras;

public class Elemento {
    private String valor;
    private int coordenadasX;
    private int coordenadasY;
    private String direccion;

    public Elemento(String valor, int coordenadasX, int coordenadasY, String direccion) {
        this.valor = valor;
        this.coordenadasX = coordenadasX;
        this.coordenadasY = coordenadasY;
        this.direccion = direccion;
    }

    public void setCoordenadasX(int coordenadasX) {
        this.coordenadasX = coordenadasX;
    }

    public void setCoordenadasY(int coordenadasY) {
        this.coordenadasY = coordenadasY;
    }
    
    
    public String getValor() {
        return valor;
    }

    public int getCoordenadasX() {
        return coordenadasX;
    }

    public int getCoordenadasY() {
        return coordenadasY;
    }

    public String getDireccion() {
        return direccion;
    }
}