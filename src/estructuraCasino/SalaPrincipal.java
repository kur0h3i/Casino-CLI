package estructuraCasino;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

// Suponiendo que estos imports y clases existen
import accionesCasino.Mesa;
import juegos.Ruleta;
import personas.Jugador;

public class SalaPrincipal {

    char[][] mapa = MapaCasino.mapa;
    int posX = MapaCasino.posX;
    int posY = MapaCasino.posY;

    public SalaPrincipal(Jugador jugador) {
        Scanner scanner = new Scanner(System.in);

        jugador.setFichas(100); // Fichas iniciales del jugador

        // Mesas disponibles (agregar las mesas a la lista)
        ArrayList<Mesa> mesas = new ArrayList<>();
        mesas.add(new Mesa(new Ruleta(jugador), "Ruleta", 1, new int[][]{{9, 4}})); 
        mesas.add(new Mesa(new Ruleta(jugador), "Poker", 1, new int[][]{{11, 3}}));

        boolean running = true;
        while (running) {
            limpiarPantalla();
            interfazPrincipal(jugador, mesas);
            salirPuerta();
            entradaTerminal(scanner, jugador, mesas);
        }

        scanner.close();
    }

    // Limpiar pantalla (función para terminal)
    public static void limpiarPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

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
            System.out.println("Usa E para guardar partida ");
            System.out.println("Usa C para cargar partida ");
            System.out.println("Usa Q para Salir");
        }
    }

    public void entradaTerminal(Scanner scanner, Jugador jugador, ArrayList<Mesa> mesas) {
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
                        break;
                    case "q":
                        System.exit(0);
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
