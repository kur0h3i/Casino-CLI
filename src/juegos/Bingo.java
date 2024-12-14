// Bingo.java
package juegos;

import ascii.ASCIIGeneral;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import excep.ExcepcionJugadorSinFichas;
import personas.Jugador;
import ascii.ASCIIBingo;

public class Bingo extends Juego {

    private final int FILAS = 4;
    private final int COLUMNAS = 6;
    private String[][][] cartones;
    private HashSet<Integer> numerosDisponibles;
    private Jugador[] jugadores;
    private int bote;
    private int apuesta;
    private Jugador jugador;
    private ASCIIBingo interfaz;

    public Bingo(Jugador jugador) {
        super(jugador);
        this.jugador = jugador;
        numerosDisponibles = new HashSet<>();
        interfaz = new ASCIIBingo();
    }

    @Override
    public void iniciarPartida() throws ExcepcionJugadorSinFichas {
        Scanner input = new Scanner(System.in);

        ASCIIGeneral.limpiarPantalla();
        interfaz.titulo();

        System.out.println("Bienvenido al Bingo!");
        System.out.print("¿Cuántos jugadores participarán? (Incluyéndote): ");
        int numJugadores = input.nextInt();
        input.nextLine();

        System.out.print("¿Cuántas fichas deseas apostar? (Cada jugador aportará la misma cantidad): ");
        apuesta = input.nextInt();
        input.nextLine();

        if (apuesta > jugador.getFichas()) {
            throw new ExcepcionJugadorSinFichas("No tienes suficientes fichas para la apuesta inicial.");
        }

        bote = apuesta * numJugadores;
        jugadores = new Jugador[numJugadores];
        cartones = new String[numJugadores][FILAS][COLUMNAS];

        jugadores[0] = jugador;
        jugador.restarFichas(apuesta);

        for (int i = 1; i < numJugadores; i++) {
            jugadores[i] = new Jugador("Jugador " + i, 18, 0);
        }

        System.out.println("¡Apuesta aceptada! El bote es de " + bote + " fichas.");
        ASCIIGeneral.esperarTecla();

        generarCartones(numJugadores);
        jugarBingo(numJugadores);
    }

    private void generarCartones(int numJugadores) {
        Random random = new Random();

        // Inicializar números disponibles (1-75)
        for (int i = 1; i <= 75; i++) {
            numerosDisponibles.add(i);
        }

        for (int k = 0; k < numJugadores; k++) {
            for (int i = 0; i < FILAS; i++) {
                for (int j = 0; j < COLUMNAS; j++) {
                    if (random.nextInt(100) < 50) { 
                        cartones[k][i][j] = "X";
                    } else {
                        int numero;
                        do {
                            numero = random.nextInt(75) + 1;
                        } while (!numerosDisponibles.contains(numero));
                        cartones[k][i][j] = String.valueOf(numero);
                    }
                }
            }
        }

        ASCIIGeneral.limpiarPantalla();
        System.out.println("Tu cartón:");
        imprimirCarton(cartones[0]); // Solo muestra el cartón del jugador principal
    }

    private void imprimirCarton(String[][] carton) {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                System.out.print(carton[i][j] + "\t");
            }
            System.out.println();
        }
    }

    private void jugarBingo(int numJugadores) {
        Random random = new Random();
    
        System.out.println("Comienza el Bingo! Bote total: " + bote + " fichas.");
    
        while (true) {
            long startTime = System.currentTimeMillis(); // Tiempo de inicio de la iteración
    
            if (numerosDisponibles.isEmpty()) {
                for (int i = 1; i <= 75; i++) {
                    numerosDisponibles.add(i); // Rellenar si están vacíos
                }
            }
    
            int numero = random.nextInt(75) + 1;
    
            if (!numerosDisponibles.contains(numero)) {
                continue; // Evitar números repetidos
            }
    
            numerosDisponibles.remove(numero);
            ASCIIGeneral.limpiarPantalla();
            System.out.println("Número extraído: " + numero);
    
            tacharNumero(cartones[0], numero);
            System.out.println("Tu cartón actualizado:");
            imprimirCarton(cartones[0]);
    
            if (esBingo(cartones[0])) {
                System.out.println("¡Bingo! Has ganado el bote de " + bote + " fichas.");
                jugador.agregarFichas(bote);
                ASCIIGeneral.esperarTecla();
                return;
            }
    
            for (int k = 1; k < numJugadores; k++) {
                tacharNumero(cartones[k], numero);
                if (esBingo(cartones[k])) {
                    System.out.println("¡Bingo! El ganador es " + jugadores[k].getName() + " y se lleva el bote de " + bote + " fichas.");
                    jugadores[k].agregarFichas(bote);
                    ASCIIGeneral.esperarTecla();
                    return;
                }
            }
    
            // Calcular el tiempo total de la iteración
            long elapsedTime = System.currentTimeMillis() - startTime;
    
            // Ajustar la pausa para mantener un intervalo constante de 2 segundos
            long sleepTime = 1000 - elapsedTime;
            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    System.out.println("Error en la pausa: " + e.getMessage());
                }
            }
        }
    }
    

    private void tacharNumero(String[][] carton, int numero) {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                if (carton[i][j].equals(String.valueOf(numero))) {
                    carton[i][j] = "X";
                }
            }
        }
    }

    private boolean esBingo(String[][] carton) {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                if (!carton[i][j].equals("X")) {
                    return false;
                }
            }
        }
        return true;
    }
}
