package com.mycompany.sopaletras;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.HashSet;
import java.util.Set;

public abstract class GeneradorSopas {
    protected String[][] cuadricula = new String[15][15];
    protected List<Elemento> elementos = new ArrayList<>();
    private Random rand = new Random();
    private String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private Set<String> coordenadasUsadas = new HashSet<>();
    
    public abstract void generarElemento();

    public void esconderElemento(Elemento elemento) {
    int x = elemento.getCoordenadasX();
    int y = elemento.getCoordenadasY();
    String valor = elemento.getValor();
    String direccion = elemento.getDireccion();

    boolean colocado = false;
    while (!colocado) {
        if (direccion.equals("vertical")) {
            if (verificarEspacio(x, y, valor.length(), direccion, valor)) {
                for (int i = 0; i < valor.length(); i++) {
                    cuadricula[x + i][y] = String.valueOf(valor.charAt(i));
                }
                colocado = true;
            }
        } else if (direccion.equals("horizontal")) {
            // Invierte la palabra si la dirección es horizontal
            valor = new StringBuilder(valor).reverse().toString();
            if (verificarEspacio(x, y, valor.length(), direccion, valor)) {
                for (int i = 0; i < valor.length(); i++) {
                    cuadricula[x][y + i] = String.valueOf(valor.charAt(i));
                }
                colocado = true;
            }
        } else if (direccion.equals("diagonal")) {
            // Invierte la palabra si la dirección es diagonal
            valor = new StringBuilder(valor).reverse().toString();
            if (verificarEspacio(x, y, valor.length(), direccion, valor)) {
                for (int i = 0; i < valor.length(); i++) {
                    cuadricula[x + i][y + i] = String.valueOf(valor.charAt(i));
                }
                colocado = true;
            }
        }

        // Si la palabra no se pudo colocar, cambia las coordenadas y vuelve a intentarlo
        if (!colocado) {
            x = rand.nextInt(cuadricula.length);
            y = rand.nextInt(cuadricula[0].length);
        }
    }
}



private boolean verificarEspacio(int x, int y, int longitud, String direccion, String valor) {
    if (direccion.equals("vertical")) {
        for (int i = 0; i < longitud; i++) {
            if (x + i >= cuadricula.length || (cuadricula[x + i][y] != null && !cuadricula[x + i][y].equals(String.valueOf(valor.charAt(i))))) {
                return false;
            }
        }
    } else if (direccion.equals("horizontal")) {
        for (int i = 0; i < longitud; i++) {
            if (y + i >= cuadricula[0].length || (cuadricula[x][y + i] != null && !cuadricula[x][y + i].equals(String.valueOf(valor.charAt(i))))) {
                return false;
            }
        }
    } else if (direccion.equals("diagonal")) {
        for (int i = 0; i < longitud; i++) {
            if (x + i >= cuadricula.length || y + i >= cuadricula[0].length || (cuadricula[x + i][y + i] != null && !cuadricula[x + i][y + i].equals(String.valueOf(valor.charAt(i))))) {
                return false;
            }
        }
    }
    return true;
}


public void generarCuadricula() {
    // Inicializa la cuadrícula
    cuadricula = new String[15][15];

    // Coloca las palabras en la cuadrícula
    for (Elemento elemento : elementos) {
        boolean colocado = false;
        while (!colocado) {
            elemento.setCoordenadasX(rand.nextInt(cuadricula.length));
            elemento.setCoordenadasY(rand.nextInt(cuadricula[0].length));
            if (verificarEspacio(elemento.getCoordenadasX(), elemento.getCoordenadasY(), elemento.getValor().length(), elemento.getDireccion(), elemento.getValor())) {
                esconderElemento(elemento);
                colocado = true;
            }
        }
    }

    // Llena el espacio vacío con letras o números aleatorios
    for (int i = 0; i < cuadricula.length; i++) {
        for (int j = 0; j < cuadricula[i].length; j++) {
            if (cuadricula[i][j] == null) {
                if (elementos.get(0).getValor().matches("[0-9]+")) {
                    cuadricula[i][j] = String.valueOf(rand.nextInt(10));
                } else {
                    cuadricula[i][j] = String.valueOf(letras.charAt(rand.nextInt(letras.length())));
                }
            }
        }
    }
}



    

        public void mostrarCuadricula() {
    System.out.println("Sopa de letras:");
    for (int i = 0; i < cuadricula.length; i++) {
        for (int j = 0; j < cuadricula[i].length; j++) {
            System.out.print(cuadricula[i][j] + " ");
        }
        System.out.println();
    }

    System.out.println("\nPalabras del archivo:");
    for (Elemento elemento : elementos) {
        System.out.println(elemento.getValor());
    }
}


    public void guardarEnArchivo(String nombreArchivo) {
        try (FileWriter writer = new FileWriter(nombreArchivo)) {
            for (int i = 0; i < cuadricula.length; i++) {
                for (int j = 0; j < cuadricula[i].length; j++) {
                    writer.write(cuadricula[i][j] + " ");
                }
                writer.write("\n");
            }

            // Escribir elementos en el archivo
            for (Elemento elemento : elementos) {
                writer.write(
                    elemento.getValor() + " " +
                    elemento.getCoordenadasX() + " " +
                    elemento.getCoordenadasY() + " " +
                    elemento.getDireccion() + "\n"
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
