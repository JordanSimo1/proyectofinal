
package com.mycompany.sopaletras;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class GeneradorAlfabetico extends GeneradorSopas {
    
  @Override
public void generarElemento() {
    Random rand = new Random();
    String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    for (Elemento palabra : elementos) {
        String valor = palabra.getValor();
        int coordenadasX = palabra.getCoordenadasX();
        int coordenadasY = palabra.getCoordenadasY();
        String direccion = palabra.getDireccion();

        if ("horizontal".equals(direccion)) {
            for (int i = 0; i < valor.length(); i++) {
                int index = coordenadasY + i;
                cuadricula[coordenadasX][index] = String.valueOf(valor.charAt(i));
            }
        } else if ("vertical".equals(direccion)) {
            for (int i = 0; i < valor.length(); i++) {
                cuadricula[coordenadasX + i][coordenadasY] = String.valueOf(valor.charAt(i));
            }
        }
    }

    // Llenar el espacio vacío con letras aleatorias
    for (int i = 0; i < cuadricula.length; i++) {
        for (int j = 0; j < cuadricula[i].length; j++) {
            if (cuadricula[i][j] == null) {
                cuadricula[i][j] = String.valueOf(letras.charAt(rand.nextInt(letras.length())));
            }
        }
    }
}


public String obtenerSopaComoString() {
    StringBuilder sopaComoString = new StringBuilder();
    for (int i = 0; i < cuadricula.length; i++) {
        for (int j = 0; j < cuadricula[i].length; j++) {
            sopaComoString.append(cuadricula[i][j]);
            sopaComoString.append(' ');
        }
        sopaComoString.append('\n');
    }
    return sopaComoString.toString();
}






    public void generarDesdeArchivo(String nombreArchivo) {
    Random rand = new Random(); 
    try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
        String linea;
        while ((linea = br.readLine()) != null) {
            String[] elementosArchivo = linea.split(" ");
            for (String elemento : elementosArchivo) {
                // Generar coordenadas y dirección aleatorias
                int coordenadasX = rand.nextInt(15 - elemento.length() + 1);
                int coordenadasY = rand.nextInt(15 - elemento.length() + 1);
                String[] direcciones = {"vertical", "horizontal", "diagonal"};
                String direccion = direcciones[rand.nextInt(direcciones.length)];
                
                // Verificar que todo el espacio que ocupará la palabra esté vacío
                boolean espacioVacio = true;
                for (int i = 0; i < elemento.length(); i++) {
                    if (direccion.equals("vertical") && cuadricula[coordenadasX + i][coordenadasY] != null) {
                        espacioVacio = false;
                        break;
                    } else if (direccion.equals("horizontal") && cuadricula[coordenadasX][coordenadasY + i] != null) {
                        espacioVacio = false;
                        break;
                    } else if (direccion.equals("diagonal") && cuadricula[coordenadasX + i][coordenadasY + i] != null) {
                        espacioVacio = false;
                        break;
                    }
                }
                
                // Si todo el espacio está vacío, añade el elemento
                if (espacioVacio) {
                    Elemento nuevoElemento = new Elemento(elemento, coordenadasX, coordenadasY, direccion);
                    this.elementos.add(nuevoElemento);
                }
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}


}