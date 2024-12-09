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
                    opcionesDeApuesta();
                    System.out.println("q para salir");
                    input.nextLine();
                    break;
                case 2:
                    System.out.println("Girar");
                    tirarRuleta();
                    System.out.println("q para salir");
                    input.nextLine();
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


    public void interfazRuleta(){
        jugador.datosUsuarioEnPartida();
        System.out.println("_________________________________________________________________________");
        System.out.println("|     | 3 | 6 | 9 | 12 | 15 | 18 | 21 | 24 | 27 | 30 | 33 | 36 | 2 to 1 |");
        System.out.println("|     |-----------------------------------------------------------------|");
        System.out.println("|     | 2 | 5 | 8 | 11 | 14 | 17 | 20 | 23 | 26 | 29 | 32 | 35 | 2 to 1 |");
        System.out.println("|     |-----------------------------------------------------------------|");
        System.out.println("|  0  | 1 | 4 | 7 | 10 | 13 | 16 | 19 | 22 | 25 | 28 | 31 | 34 | 2 to 1 |");
        System.out.println("|     |-----------------------------------------------------------------|");
        System.out.println("|     |       1st 12        |       2nd 12      |         3rd 12        |");
        System.out.println("|     |-----------------------------------------------------------------|");
        System.out.println("|     |  1 to 18  |   PAR   |   RED   |  BLACK  |  IMPAR  |   19 to 36  |");
        System.out.println("|_____|_________________________________________________________________|");
    }

    // Mostrar los porcentages de ganancia
    public void cheetsheet() {
        System.out.println("__________________________ CHEET SHEET __________________________");
        System.out.println("| Tipo de Apuesta         | Ejemplo        | Pago               |");
        System.out.println("|-------------------------|----------------|--------------------|");
        System.out.println("| Número Individual       | 7              | x35                |");
        System.out.println("| Docena (1st, 2nd, 3rd)  | 1st 12         | x2                 |");
        System.out.println("| Mitad (1-18 o 19-36)    | 1-18           | x1                 |");
        System.out.println("| Rojo / Negro            | Rojo           | x1                 |");
        System.out.println("| Par / Impar             | Par            | x1                 |");
        System.out.println("|_______________________________________________________________|");
    }
    
    public int tirarRuleta() {
        Random random = new Random();
        // Genera un número aleatorio entre 0 y 36
        int resultado = random.nextInt(37);
        return resultado;
    }

    public void opcionesDeApuesta() {
        Scanner input = new Scanner(System.in);
        int opcion = 0;
        
        // Bucle para mostrar opciones hasta que el usuario elija salir
        while (opcion != 6) { // Supongamos que el 8 es la opción de salir
            SalaPrincipal.limpiarPantalla();
            jugador.datosUsuarioEnPartida();
            System.out.println("_______________________");
            System.out.println("| Opciones de Apuesta   |");
            System.out.println("| 1. Color (Rojo/Negro) |");
            System.out.println("| 2. Par / Impar        |");
            System.out.println("| 3. Número Individual  |");
            System.out.println("| 4. Docena             |");
            System.out.println("| 5. Mitad              |");
            System.out.println("| 6. Salir              |");
            System.out.println("|_______________________|");
            System.out.print("Elige una opción: ");
    
            // Leer la opción del usuario
            try {
                opcion = input.nextInt();
            } catch (Exception e) {
                System.out.println("Entrada no válida. Intenta de nuevo.");
                input.nextLine(); // Limpiar el buffer
                continue;
            }
    
            // Lógica para cada opción de apuesta
            switch (opcion) {
                case 1:
                    System.out.println("Has elegido apostar por el color.");
                    apostarPorColor();
                    break;
                case 2:
                    System.out.println("Has elegido apostar por Par / Impar.");
                    apostarParImpar();
                    break;
                case 3:
                    System.out.println("Has elegido apostar a un número individual.");
                    apostarPorNumero();
                    break;
                case 4:
                    System.out.println("Has elegido apostar por una docena.");
                    apostarPorDocena();
                    break;
                case 5:
                    System.out.println("Has elegido apostar por mitad (1-18 o 19-36).");
                    apostarMitad();
                    break;
                case 6:
                    System.out.println("Saliendo de las opciones de apuesta...");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }
    }

    // Determina si un número es rojo o negro
    private boolean esNumeroRojo(int numero) {
        // Números rojos en la ruleta
        int[] rojos = {1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36};
        for (int rojo : rojos) {
            if (numero == rojo) {
                return true;
            }
        }
        return false;
    }

    public void apostarPorColor() {
        Scanner input = new Scanner(System.in);
        System.out.println("Elige un color: 1 para Rojo, 2 para Negro:");
        int color = input.nextInt();
    
        int resultado = tirarRuleta();
        boolean esRojo = esNumeroRojo(resultado);
    
        System.out.println("La ruleta gira... El número es: " + resultado);
        if ((color == 1 && esRojo) || (color == 2 && !esRojo)) {
            System.out.println("¡Felicidades! Ganaste x1 tu apuesta.");
            jugador.agregarFichas(apuesta * 2); // Ganancia: la apuesta inicial más el premio
        } else {
            System.out.println("Lo siento, perdiste.");
            jugador.restarFichas(apuesta);
        }
    }
    
    public void apostarParImpar() {
        Scanner input = new Scanner(System.in);
        System.out.println("Elige: 1 para Par, 2 para Impar:");
        int eleccion = input.nextInt();
    
        int resultado = tirarRuleta();
        System.out.println("La ruleta gira... El número es: " + resultado);
    
        boolean esPar = (resultado != 0 && resultado % 2 == 0);
        if ((eleccion == 1 && esPar) || (eleccion == 2 && !esPar)) {
            System.out.println("¡Felicidades! Ganaste x1 tu apuesta.");
            jugador.agregarFichas(apuesta * 2);
        } else {
            System.out.println("Lo siento, perdiste.");
            jugador.restarFichas(apuesta);
        }
    }
    
    public void apostarPorNumero() {
        Scanner input = new Scanner(System.in);
        System.out.println("Elige un número entre 0 y 36:");
        int numeroApostado = input.nextInt();
    
        if (numeroApostado < 0 || numeroApostado > 36) {
            System.out.println("Número inválido. Intenta de nuevo.");
            return;
        }
    
        int resultado = tirarRuleta();
        System.out.println("La ruleta gira... El número es: " + resultado);
    
        if (numeroApostado == resultado) {
            System.out.println("¡Felicidades! Ganaste x35 tu apuesta!");
            jugador.agregarFichas(apuesta * 36);
        } else {
            System.out.println("Lo siento, perdiste.");
            jugador.restarFichas(apuesta);
        }
    }

    public void apostarPorDocena() {
        Scanner input = new Scanner(System.in);
    
        System.out.println("Elige una docena: 1 (1-12), 2 (13-24), o 3 (25-36):");
        int docena = input.nextInt();
    
        if (docena < 1 || docena > 3) {
            System.out.println("Docena inválida. Intenta de nuevo.");
            return;
        }
    
        int resultado = tirarRuleta();
        System.out.println("La ruleta gira... El número es: " + resultado);
    
        if ((docena == 1 && resultado >= 1 && resultado <= 12) ||
            (docena == 2 && resultado >= 13 && resultado <= 24) ||
            (docena == 3 && resultado >= 25 && resultado <= 36)) {
            System.out.println("¡Felicidades! Ganaste x2 tu apuesta.");
            jugador.agregarFichas(apuesta * 3); // Ganancia: apuesta inicial más premio
        } else {
            System.out.println("Lo siento, perdiste.");
            jugador.restarFichas(apuesta);
        }
    }
    
    
    public void apostarMitad() {
        Scanner input = new Scanner(System.in);
    
        System.out.println("Elige una mitad: 1 (1-18) o 2 (19-36):");
        int mitad = input.nextInt();
    
        if (mitad != 1 && mitad != 2) {
            System.out.println("Mitad inválida. Intenta de nuevo.");
            return;
        }
    
        int resultado = tirarRuleta();
        System.out.println("La ruleta gira... El número es: " + resultado);
    
        if ((mitad == 1 && resultado >= 1 && resultado <= 18) ||
            (mitad == 2 && resultado >= 19 && resultado <= 36)) {
            System.out.println("¡Felicidades! Ganaste x1 tu apuesta.");
            jugador.agregarFichas(apuesta * 2); // Ganancia: apuesta inicial más premio
        } else {
            System.out.println("Lo siento, perdiste.");
            jugador.restarFichas(apuesta);
        }
    }
    
    
    
    
}
