package juegos.blackjack;

import recursos.Baraja;
import recursos.Carta;

public class BlackJack {
    
    // Atributos
    private Baraja baraja;
    private Jugador jugador;
    private Jugador crupier;

    // Constructor
    public BlackJack() {
        baraja = new Baraja();
        jugador = new Jugador("Jugador");
        crupier = new Jugador("Crupier");
        iniciarJuego();
    }

    // Metodos
    // Iniciar Juego
    public void iniciarJuego() {
        jugador.recibirCarta(baraja.repartir());
        jugador.recibirCarta(baraja.repartir());
        crupier.recibirCarta(baraja.repartir());
        crupier.recibirCarta(baraja.repartir());
    }

    // Turno Jugador
    public void turnoJugador() {
        jugador.recibirCarta(baraja.repartir());
    }
    
    // Turno Crupier
    public void turnoCrupier() {
        while (crupier.calcularPuntaje() < 17) {
            crupier.recibirCarta(baraja.repartir());
        }
    }

    // Determinar Jugador
    public String determinarGanador() {
        int puntajeJugador = jugador.calcularPuntaje();
        int puntajeCrupier = crupier.calcularPuntaje();

        if (!jugador.estaEnJuego() || (puntajeCrupier <= 21 && puntajeCrupier > puntajeJugador)) {
            return "Crupier gana";
        } else if (!crupier.estaEnJuego() || puntajeJugador > puntajeCrupier) {
            return "Jugador gana";
        } else {
            return "Empate";
        }
    }

    // Getter jugador
    public Jugador getJugador() {
        return jugador;
    }
    // Getter crupier
    public Jugador getCrupier() {
        return crupier;
    }

    public void pedirCartaJugador() {
        Carta nuevaCarta = baraja.repartir();
        jugador.recibirCarta(nuevaCarta);

        if (!jugador.estaEnJuego()) {
            System.out.println("El jugador se pasó de 21.");
        }
    }
}
