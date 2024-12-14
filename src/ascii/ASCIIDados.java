// ASCIIDados.java
package ascii;

import personas.Jugador;

public class ASCIIDados {

    // Atributo para el jugador
    Jugador jugador;

    // Constructor
    public ASCIIDados(Jugador jugador) {
        this.jugador = jugador;
    }

    // Mostrar el título del juego
    public void titulo() {
        System.out.println("  ____            _      ");
        System.out.println(" |  _ \\  __ _  __| | ___ ");
        System.out.println(" | | | |/ _` |/ _` |/ _ \\");
        System.out.println(" | |_| | (_| | (_| |  __/");
        System.out.println(" |____/ \\__,_|\\__,_|\\___|");
    }

    // Mostrar cheatsheet con las reglas y pagos
    public void cheatsheet() {
        System.out.println("___________________________ CHEATSHEET ___________________________");
        System.out.println("| Fase                   | Resultado        | Efecto             |");
        System.out.println("|------------------------|------------------|--------------------|");
        System.out.println("| Come-Out Roll          | 7 o 11           | Ganas x2 tu apuesta|");
        System.out.println("|                        | 2, 3, 12         | Pierdes la apuesta |");
        System.out.println("|                        | 4, 5, 6, 8, 9,10 | Se establece Punto |");
        System.out.println("|------------------------|------------------|--------------------|");
        System.out.println("| Fase del Punto         | Punto logrado    | Ganas x2 tu apuesta|");
        System.out.println("|                        | 7                | Pierdes la apuesta |");
        System.out.println("|________________________|__________________|____________________|");
    }

    // Mostrar opciones para el jugador
    public void opciones() {
        jugador.datosUsuarioEnPartida();
        System.out.println("1. Tirar los dados");
        System.out.println("2. Ver cheatsheet");
        System.out.println("3. Salir");
    }

    // Mostrar resultados del tiro de dados
    public void mostrarResultadoDados(int dado1, int dado2, int suma) {
        System.out.println("___________________________________");
        System.out.println("|              DADOS              |");
        System.out.println("|_________________________________|");
        System.out.println("| Dado 1: " + dado1 + "   Dado 2: " + dado2 + "   Suma: " + suma + " | ");
        System.out.println("|_________________________________|");
    }

}