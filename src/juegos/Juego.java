package juegos;

import java.util.Scanner;

import excep.ExcepcionJugadorSinFichas;
import personas.Jugador;

public abstract class Juego {
    
    // Atributos 
    private Jugador jugador;

    public Juego(Jugador jugador){
        this.jugador = jugador;
    }

    // Métodos abstractos que se deben implementar en clases hijas
    public abstract void iniciarPartida() throws ExcepcionJugadorSinFichas;
    
    public int definirApuesta() {
        int apuesta = 0;
        Scanner input = new Scanner(System.in);
        System.out.println("Tu apuesta es : ");
        apuesta = input.nextInt();
        input.nextLine();
        input.close();
        return apuesta;
    }

    public void comprobarfichas() throws ExcepcionJugadorSinFichas{
        if (jugador.getFichas() <= 0) {
            throw new ExcepcionJugadorSinFichas("Jugador sin fichas");
        }
    }
}
