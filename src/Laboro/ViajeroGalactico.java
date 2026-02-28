package Laboro2;

import java.util.Scanner;

public class ViajeroGalactico {

    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        int posicion = 1;
        int tirdasAnterior = 0;

        System.out.println("¡Bienvenido a LA GUÍA DEL VIAJERO INTERGALÁCTICO!");
        System.out.println("Estás en la casilla 1 (galaxia 000)");

        posicion = jugar(posicion, tirdasAnterior, leer);

        if (posicion == 42) {
            System.out.println(" ¡FELICIDADES! Has llegado a la casilla 42. ¡HAS GANADO!");
        } else if (posicion == 33) {
            System.out.println(" Has sido absorbido por un agujero negro en la casilla 33. Fin del viaje...");
        }

        leer.close();
    }

    public static int jugar(int posicionInicial, int tirdasAnterior, Scanner leer) {
        int posicion = posicionInicial;

        while (posicion != 33 && posicion != 42) {
            System.out.println("Estás en la casilla " + posicion);
            System.out.println("Pulsa Enter para lanzar los 3 dados galácticos...");
            leer.nextLine();

            int d1 = lanzarDado();
            int d2 = lanzarDado();
            int d3 = lanzarDado();

            System.out.println("Han salido: " + d1 + ", " + d2 + ", " + d3);

            int galaxia = d1 * 100 + d2 * 10 + d3;
            int suma = d1 + d2 + d3;
            int tirdasActual = calcularTirdas(d1, d2, d3);

            System.out.println("Galaxia destino: " + galaxia + " → suma = " + suma + " → tirdas = " + tirdasActual);

            int diferencia = Math.abs(tirdasActual - tirdasAnterior);

            if (diferencia > 4) {
                System.out.println("¡La galaxia está demasiado lejos! (" + diferencia + " años luz). No puedes avanzar.");
                System.out.println("¡Vuelves a intentarlo desde la misma casilla!");
            } else {
                System.out.println("Distancia: " + diferencia + " años luz → avanzas " + diferencia + " casillas");
                posicion += diferencia;

                if (posicion > 42) {
                    System.out.println("¡Te has pasado de la casilla 42! Vuelves al principio.");
                    posicion = 1;
                }

                if (posicion == 31) {
                    System.out.println("¡Cuidado! Extraterrestres hostiles te capturan...");
                    System.out.println("¡Te envían de vuelta a la casilla 13!");
                    posicion = 13;
                }
            }

            if (diferencia <= 4) {
                tirdasAnterior = tirdasActual;
            }

            System.out.println();
        }

        return posicion;
    }

    public static int lanzarDado() {
        return (int) (Math.random() * 9) + 1;
    }

    public static int calcularTirdas(int a, int b, int c) {
        int suma = a + b + c;
        while (suma >= 10) {
            int nuevaSuma = 0;
            while (suma > 0) {
                nuevaSuma += suma % 10;
                suma /= 10;
            }
            suma = nuevaSuma;
        }
        return suma;
    }
}