// ASCIISlot.java
package ascii;

import personas.Jugador;

public class ASCIISlot {
    
    // Atributos
    Jugador jugador;

    // constructor
    public ASCIISlot(Jugador jugador){
        this.jugador = jugador;
    }

    // Mostrar el título del juego
    public void titulo() {
        System.out.println(" ____  _       _   ");
        System.out.println("/ ___|| | ___ | |_ ");
        System.out.println("\\___ \\| |/ _ \\| __|");
        System.out.println(" ___) | | (_) | |_ ");
        System.out.println("|____/|_|\\___/ \\__|");
    }

    // Mostrar una cheatsheet con combinaciones y premios
    public void cheetsheet() {
        System.out.println("=========================================");
        System.out.println("           TABLA DE PREMIOS SLOT          ");
        System.out.println("=========================================");
        System.out.println(" X X X - Jackpot! x10 tu apuesta");
        System.out.println(" ! ! ! - Mega premio! x7 tu apuesta");
        System.out.println(" ? ? ? - Buen premio! x5 tu apuesta");
        System.out.println(" * * * - Premio base! x3 tu apuesta");
        System.out.println(" Dos símbolos iguales - Recuperas tu apuesta");
        System.out.println("=========================================");
    }

    public void opcioes(){
        jugador.datosUsuarioEnPartida();
        System.out.println("1. Realizar Apuesta");
        System.out.println("2. CheetSheet");
        System.out.println("3. Salir");
    }

    // Mostrar resultados de los rodillos
    public void mostrarResultados(String simbolo1, String simbolo2, String simbolo3) {
        System.out.println("=========================================");
        System.out.println("           RESULTADOS SLOT               ");
        System.out.println("=========================================");
        System.out.println("           " + simbolo1 + " | " + simbolo2 + " | " + simbolo3);
        System.out.println("=========================================");
    }
}
