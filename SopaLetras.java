package com.mycompany.sopaletras;

import java.util.Scanner;

/**
 *
 * @author jorda
 */
public class SopaLetras {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Por favor, introduce el nombre del archivo:");
        String nombreArchivo = scanner.nextLine();

        GeneradorAlfabetico generador = new GeneradorAlfabetico();

        // Para cargar desde un archivo
        generador.generarDesdeArchivo(nombreArchivo);

        // Para generar aleatoriamente
        generador.generarElemento();

        // Mostrar la cuadrícula
        generador.generarCuadricula();
        generador.mostrarCuadricula();

        // Guardar en un archivo
        generador.guardarEnArchivo("sopaGenerada.txt");
    }
}