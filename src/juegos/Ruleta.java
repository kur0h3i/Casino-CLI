package juegos;

import java.util.Random;
import java.util.Scanner;

import casino.SalaPrincipal;
import personas.Jugador;

public class Ruleta extends Juego {
    
    // Atributo
    int apuesta;
    Jugador jugador;
    
    public Ruleta(Jugador jugador){
        this.jugador = jugador;
    }

    public void iniciarPartida(){
        Scanner input = new Scanner(System.in);
        int opcion = 0;

        // Definir apuesta
        apuesta = definirApuesta();
        while (opcion != 4){
            SalaPrincipal.limpiarPantalla();
            interfazRuleta();
            System.out.println("Elige una opcion\n1.Apostar por una opcion y girar\n2.Girar\n3.Cheetsheet\n4.Salir");
            opcion = input.nextInt();
            input.nextLine();

            // Opciones Menu
            switch (opcion) {
                case 1:
                    System.out.println("Elegir Opcion");
                    break;
                case 2:
                    System.out.println("Girar");
                    break;
                case 3: 
                    cheetsheet();
                    System.out.println("q para salir");
                    input.nextLine();
                    break;
                case 4:
                System.out.println("Saliendo...");
                SalaPrincipal.limpiarPantalla();
                    break;
            }

        }
        
    }

    public  int resultadoFichas(){
        return 1;
    };

    public void interfazRuleta(){
        System.out.println("----------------------------");
        System.out.println("Nombre :  " + jugador.getName());
        System.out.println("Fichas : " + jugador.getFichas());
        System.out.println("----------------------------");
        System.out.println("_________________________________________________________________________");
        System.out.println("|     | 3 | 6 | 9 | 12 | 15 | 18 | 21 | 24 | 27 | 30 | 33 | 36 | 2 to 1 |");
        System.out.println("|     -------------------------------------------------------------------");
        System.out.println("|     | 2 | 5 | 8 | 11 | 14 | 17 | 20 | 23 | 26 | 29 | 32 | 35 | 2 to 1 |");
        System.out.println("|     -------------------------------------------------------------------");
        System.out.println("|  0  | 1 | 4 | 7 | 10 | 13 | 16 | 19 | 22 | 25 | 28 | 31 | 34 | 2 to 1 |");
        System.out.println("|     -------------------------------------------------------------------");
        System.out.println("|     |       1st 12        |        2nd 12     |         3rd 12        |");
        System.out.println("|     -------------------------------------------------------------------");
        System.out.println("|     |  1 to 18  |   PAR   |   RED   |  BLACK  |  IMPAR  |   19 to 36  |");
        System.out.println("-------------------------------------------------------------------------");
    }

    // Mostrar los porcentages de ganancia
    public void cheetsheet() {
        System.out.println("__________________________ CHEET SHEET __________________________");
        System.out.println("| Tipo de Apuesta         | Ejemplo        | Pago               |");
        System.out.println("|-------------------------|----------------|--------------------|");
        System.out.println("| Número Individual       | 7              | 35 a 1             |");
        System.out.println("| Apuesta Split           | 7 y 8          | 17 a 1             |");
        System.out.println("| Apuesta Street          | 7, 8, 9        | 11 a 1             |");
        System.out.println("| Apuesta Corner          | 7, 8, 10, 11   | 8 a 1              |");
        System.out.println("| Docena (1st, 2nd, 3rd)  | 1st 12         | 2 a 1              |");
        System.out.println("| Columna                 | Columna 1      | 2 a 1              |");
        System.out.println("| Mitad (1-18 o 19-36)    | 1-18           | 1 a 1              |");
        System.out.println("| Rojo / Negro            | Rojo           | 1 a 1              |");
        System.out.println("| Par / Impar             | Par            | 1 a 1              |");
        System.out.println("|_______________________________________________________________|");
    }
    
    
}
