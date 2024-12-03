package juegos;

import java.util.Scanner;

public abstract class Juego {

    // Atributos
    int fichasApostadas;
    int fichasActuales;

    public Juego(int fichasActuales) {
        this.fichasActuales = fichasActuales;
    }

    // Métodos abstractos que se deben implementar en clases hijas
    public abstract int resultadoFichas();
    public abstract void iniciarPartida();

    // Método para realizar la apuesta
}
