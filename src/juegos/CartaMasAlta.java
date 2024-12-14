// CartaMasAlta.java
package juegos;

import ascii.ASCIICartaMasAlta;
import java.util.Scanner;
import recursos.Baraja;
import recursos.Carta;
import personas.Jugador;
import excep.ExcepcionJugadorSinFichas;

public class CartaMasAlta extends Juego {

    private Baraja baraja;
    private ASCIICartaMasAlta interfaz;
    Jugador jugador;
    private int apuesta;

    public CartaMasAlta(Jugador jugador) {
        super(jugador);
        this.jugador = jugador;
        this.baraja = new Baraja();
        this.interfaz = new ASCIICartaMasAlta();
    }

    @Override
    public void iniciarPartida() throws ExcepcionJugadorSinFichas {
        Scanner input = new Scanner(System.in);

        interfaz.titulo();
        comprobarfichas();

        boolean continuar = true;
        while (continuar) {
            interfaz.opciones();

            try {
                int opcion = input.nextInt();
                input.nextLine(); // Limpiar buffer

                switch (opcion) {
                    case 1:
                        realizarApuesta(input);
                        break;
                    case 2:
                        interfaz.cheatsheet();
                        break;
                    case 3:
                        continuar = false;
                        System.out.println("Gracias por jugar a La Carta Más Alta. ¡Hasta la próxima!");
                        break;
                    default:
                        System.out.println("Opción no válida. Intenta de nuevo.");
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida. Intenta de nuevo.");
                input.nextLine();
            }
        }
    }

    private void realizarApuesta(Scanner input) {
        System.out.println("¿Cuántas fichas deseas apostar?");
        System.out.println("Tienes " + jugador.getFichas() + " fichas disponibles.");

        try {
            apuesta = input.nextInt();
            input.nextLine(); // Limpiar buffer

            if (apuesta <= 0 || apuesta > jugador.getFichas()) {
                System.out.println("Apuesta no válida. Intenta de nuevo.");
                return;
            }

            jugador.restarFichas(apuesta);
            jugarRonda();
        } catch (Exception e) {
            System.out.println("Entrada inválida. Intenta de nuevo.");
            input.nextLine();
        }
    }

    private void jugarRonda() {
        baraja.mezclar();

        Carta cartaJugador = baraja.repartir();
        Carta cartaIA = baraja.repartir();

        System.out.println("Tu carta: " + cartaJugador);
        System.out.println("Carta de la IA: " + cartaIA);

        int valorJugador = cartaJugador.getValorNumerico();
        int valorIA = cartaIA.getValorNumerico();

        if (valorJugador > valorIA) {
            System.out.println("¡Has ganado esta ronda con " + cartaJugador + "!");
            jugador.agregarFichas(apuesta * 2);
        } else if (valorJugador < valorIA) {
            System.out.println("La IA gana esta ronda con " + cartaIA + ".");
        } else {
            System.out.println("¡Empate! Ambas cartas son iguales.");
            jugador.agregarFichas(apuesta); // Recupera la apuesta en caso de empate
        }
    }
}