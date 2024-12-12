package opcionesSala;

import juegos.Juego;
import juegos.Poker;
import juegos.Ruleta;

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

    public void jugar(){
        juego.iniciarPartida();
    }


}
