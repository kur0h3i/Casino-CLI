package estructuraCasino;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


import accionesCasino.Mesa;
import accionesCasino.PuertaSalida;
import excep.ExcepcionJugadorSinDinero;
import excep.ExcepcionJugadorSinFichas;
import juegos.Bingo;
import juegos.Ruleta;
import personas.Jugador;
import ascii.ASCIIGeneral;
import accionesCasino.Cajero;

public class SalaPrincipal {

    char[][] mapa = MapaCasino.mapa;
    int posX = MapaCasino.posX;
    int posY = MapaCasino.posY;
    Jugador jugador;
    ASCIIGeneral interzaf;

    public SalaPrincipal(Jugador jugador) throws ExcepcionJugadorSinFichas, ExcepcionJugadorSinDinero {
        Scanner scanner = new Scanner(System.in);
        this.jugador = jugador;

        //jugador.setFichas(100); // Fichas iniciales del jugador

        // Mesas disponibles (agregar las mesas a la lista)
        ArrayList<Mesa> mesas = new ArrayList<>();
        mesas.add(new Mesa(new Ruleta(jugador), "Ruleta", 1, new int[][]{{9, 4}})); 
        mesas.add(new Mesa(new Bingo(jugador), "Bingo", 1, new int[][]{{14, 11}}));

        boolean running = true;
        while (running) {
            ASCIIGeneral.limpiarPantalla();
            interfazPrincipal(jugador, mesas);
            salirPuerta();
            cajero();
            entradaTerminal(scanner, jugador, mesas);
        }

        scanner.close();
    }

    // Limpiar pantalla (función para terminal)

    public void interfazPrincipal(Jugador jugador, ArrayList<Mesa> mesas){
        // Interfaz del jugador
        System.out.println("----------------------------");
        System.out.println("Nombre :  " + jugador.getName());
        System.out.println("Dinero : " + jugador.getDinero());
        System.out.println("Fichas : " + jugador.getFichas());
        System.out.println("POSX : " + posX);
        System.out.println("POSY : " + posY);
        System.out.println("----------------------------");

        // Mostrar el mapa del casino
        mostrarMapa();

        // Instrucciones de control
        System.out.println("Usa WASD para moverte, E para unirte a la mesa, Q para salir:");

        // Interacción con mesas
        for (Mesa mesa : mesas) {
            if (mesa.getPosicionInteractuar()[0][0] == posX && mesa.getPosicionInteractuar()[0][1] == posY) {
                System.out.println("Usa E para unirte a la mesa de " + mesa.getNombreMesa());
            }
        }
    }

    // Mostrar mapa del casino
    public void mostrarMapa() {
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                if (i == posY && j == posX) {
                    System.out.print("P ");  // Representar jugador con "P"
                } else {
                    System.out.print(mapa[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    // Mover jugador en el mapa
    public void moverJugador(int dx, int dy) {
        int nuevaPosX = posX + dx;
        int nuevaPosY = posY + dy;

        // Comprobar que la nueva posición esté dentro de los límites y no sea un muro (#)
        if (nuevaPosX >= 0 && nuevaPosX < mapa[0].length && nuevaPosY >= 0 && nuevaPosY < mapa.length
                && mapa[nuevaPosY][nuevaPosX] != '#') {
            posX = nuevaPosX;
            posY = nuevaPosY;
        }
    }

    public void salirPuerta(){
        // Puerta de salida
        if (posX == 4 && posY == 0) {
            System.out.println("Usa E para salir/guardar/cargar "); 
        }
    }

    public void cajero(){
        if (posX == 2 && posY == 7){
            System.out.println("Usa E para entrar en el cajero");
        }
    }

    public void entradaTerminal(Scanner scanner, Jugador jugador, ArrayList<Mesa> mesas) throws ExcepcionJugadorSinFichas, ExcepcionJugadorSinDinero {
        boolean validInput = false;
        
        while (!validInput) {
            try {
                // Leer entrada del teclado
                String input = scanner.nextLine().toLowerCase();
                validInput = true;  // Asumir entrada válida hasta comprobar

                switch (input) {
                    case "w":
                        moverJugador(0, -1);
                        break;
                    case "s":
                        moverJugador(0, 1);
                        break;
                    case "a":
                        moverJugador(-1, 0);
                        break;
                    case "d":
                        moverJugador(1, 0);
                        break;
                    case "e":
                        // Verificar si estamos en la posición de la mesa y unirse
                        for (Mesa mesa : mesas) {
                            if (mesa.getPosicionInteractuar()[0][0] == posX && mesa.getPosicionInteractuar()[0][1] == posY) {
                                mesa.jugar();
                            }
                        }
                        if (posX == 2 && posY == 7){
                            new Cajero(jugador);
                        }
                        if (posX == 4 && posY == 0){
                            new PuertaSalida(jugador);
                        }
                        break;
                    default:
                        validInput = false;  // Entrada no válida
                        System.out.println("Comando no válido. Intenta de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor, ingresa un comando válido.");
                scanner.nextLine(); // Limpiar el buffer
                validInput = false;  // Repetir el bucle para pedir entrada nuevamente
            }
        }
    }
}
