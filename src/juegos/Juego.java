package juegos;

import java.util.Scanner;

import excep.ExcepcionjugadorSinFichas;
import personas.Jugador;

public abstract class Juego {
    
    // Atributos 
    private int apuesta;
    private Jugador jugador;

    public Juego(Jugador jugador){
        this.jugador = jugador;
    }

    // Métodos abstractos que se deben implementar en clases hijas
    public abstract void iniciarPartida() throws ExcepcionjugadorSinFichas;
    
    public int definirApuesta() {
        int apuesta = 0;
        Scanner input = new Scanner(System.in);
        System.out.println("Tu apuesta es : ");
        apuesta = input.nextInt();
        input.nextLine();
        return apuesta;
    }

    public void comprobarfichas() throws ExcepcionjugadorSinFichas{
        if (jugador.getFichas() <= 0) {
            throw new ExcepcionjugadorSinFichas("Jugador sin fichas");
        }
    }
}
