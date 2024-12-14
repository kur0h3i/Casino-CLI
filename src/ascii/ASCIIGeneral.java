package ascii;

import java.util.Scanner;

public class ASCIIGeneral {
    
    public static void esperarTecla() {
            Scanner input = new Scanner(System.in);
            System.out.println("Presiona Enter para continuar...");
            input.nextLine();
        }
    
        public static void limpiarPantalla() {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
}
