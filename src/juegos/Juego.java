package juegos;

import java.util.Scanner;

public abstract class Juego {

    // Métodos abstractos que se deben implementar en clases hijas
    public abstract void iniciarPartida();
    
    public int definirApuesta() {
        int apuesta = 0;
        Scanner input = new Scanner(System.in);
        System.out.println("Tu apuesta es : ");
        apuesta = input.nextInt();
        input.nextLine();
        return apuesta;
    }


}
