package accionesCasino;

import excep.ExcepcionJugadorSinFichas;
import juegos.Juego;
import ascii.ASCIIGeneral;

public class Mesa {
    
    // Atributos de la mesa
    Juego juego;
    String nombreMesa;
    int numPartcipantes;
    int [][] posicionInteractuar;

    // Constructor
    public Mesa(Juego juego, String nombreMesa, int numPartcipantes, int [][] posicionInteractuar){
        this.juego = juego;
        this.nombreMesa = nombreMesa;
        this.numPartcipantes = numPartcipantes;
        this.posicionInteractuar = posicionInteractuar;
    }



    // Getters
    public Juego getJuego() {
        return juego;
    }
    public String getNombreMesa() {
        return nombreMesa;
    }
    public int getNumPartcipantes() {
        return numPartcipantes;
    }
    public int[][] getPosicionInteractuar() {
        return posicionInteractuar;
    }

    public void jugar() throws ExcepcionJugadorSinFichas{
        try {
            juego.iniciarPartida(); // Intenta iniciar la partida
        } catch (ExcepcionJugadorSinFichas e) {
            // Captura la excepción y muestra un mensaje sin salir del programa
            System.out.println("No puedes jugar porque no tienes fichas.");
            ASCIIGeneral.esperarTecla();
        }
    }


}
