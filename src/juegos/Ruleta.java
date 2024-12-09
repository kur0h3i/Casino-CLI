package juegos;

import java.util.Random;
import java.util.Scanner;

import casino.SalaPrincipal;
import personas.Jugador;

public class Ruleta extends Juego {

    // Atributos
    private int apuesta; // Cantidad de fichas apostadas en esta partida
    private Jugador jugador; // Jugador participando en la partida

    public Ruleta(Jugador jugador) {
        this.jugador = jugador;
    }

    public void iniciarPartida() {
        try (Scanner input = new Scanner(System.in)) {
            int opcion = 0;

            // Verificar que el jugador tiene fichas suficientes para jugar
            if (jugador.getFichas() <= 0) {
                System.out.println("No tienes suficientes fichas para jugar. Por favor, recarga fichas.");
                return;
            }

            while (opcion != 4) {
                SalaPrincipal.limpiarPantalla();
                interfazRuleta();
                System.out.println("Elige una opción:");
                System.out.println("1. Apostar por una opción y girar");
                System.out.println("2. Girar (sin apostar)");
                System.out.println("3. Ver Cheetsheet");
                System.out.println("4. Salir");
                try {
                    opcion = input.nextInt();
                    input.nextLine(); // Limpiar el buffer

                    // Opciones del menú principal
                    switch (opcion) {
                        case 1:
                            if (!definirApuesta(input)) {
                                break;
                            }
                            opcionesDeApuesta(input);
                            break;
                        case 2:
                            System.out.println("Girar la ruleta (sin apostar).");
                            tirarRuleta();
                            esperarTecla(input);
                            break;
                        case 3:
                            cheetsheet();
                            esperarTecla(input);
                            break;
                        case 4:
                            System.out.println("Saliendo del juego...");
                            SalaPrincipal.limpiarPantalla();
                            break;
                        default:
                            System.out.println("Opción no válida. Intenta de nuevo.");
                    }
                } catch (Exception e) {
                    System.out.println("Entrada no válida. Intenta de nuevo.");
                    input.nextLine(); // Limpiar el buffer
                }
            }
        }
    }

    private void esperarTecla(Scanner input) {
        System.out.println("Presiona Enter para continuar...");
        input.nextLine();
    }


    public void interfazRuleta() {
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
        int resultado = random.nextInt(37);
        System.out.println("La ruleta gira... El número es: " + resultado);
        return resultado;
    }

    public boolean definirApuesta(Scanner input) {
        System.out.println("Tienes " + jugador.getFichas() + " fichas.");
        System.out.print("¿Cuántas fichas deseas apostar? ");
        try {
            apuesta = input.nextInt();
            input.nextLine();

            if (apuesta <= 0) {
                System.out.println("La apuesta debe ser mayor que 0.");
                return false;
            } else if (apuesta > jugador.getFichas()) {
                System.out.println("No tienes suficientes fichas para realizar esta apuesta.");
                return false;
            }

            jugador.restarFichas(apuesta);
            System.out.println("Apuesta realizada con éxito. ¡Buena suerte!");
            return true;
        } catch (Exception e) {
            System.out.println("Entrada inválida. Intenta de nuevo.");
            input.nextLine();
            return false;
        }
    }

    public void opcionesDeApuesta(Scanner input) {
        int opcion = 0;
        SalaPrincipal.limpiarPantalla();
        while (opcion != 6) {
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

            try {
                opcion = input.nextInt();

                switch (opcion) {
                    case 1:
                        apostarPorColor(input);
                        break;
                    case 2:
                        apostarParImpar(input);
                        break;
                    case 3:
                        apostarPorNumero(input);
                        break;
                    case 4:
                        apostarPorDocena(input);
                        break;
                    case 5:
                        apostarMitad(input);
                        break;
                    case 6:
                        System.out.println("Saliendo de las opciones de apuesta...");
                        break;
                    default:
                        System.out.println("Opción no válida. Intenta de nuevo.");
                }
            } catch (Exception e) {
                System.out.println("Entrada no válida. Intenta de nuevo.");
                input.nextLine();
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

    public void apostarPorColor(Scanner input) {
        System.out.println("Elige un color: 1 para Rojo, 2 para Negro:");
        int color = input.nextInt();
    
        int resultado = tirarRuleta();
        boolean esRojo = esNumeroRojo(resultado);
    
        if ((color == 1 && esRojo) || (color == 2 && !esRojo)) {
            System.out.println("¡Felicidades! Ganaste x1 tu apuesta.");
            jugador.agregarFichas(apuesta * 2); // Ganancia: la apuesta inicial más el premio
        } else {
            System.out.println("Lo siento, perdiste.");
            jugador.restarFichas(apuesta);
        }
    }
    
    public void apostarParImpar(Scanner input) {
        System.out.println("Elige: 1 para Par, 2 para Impar:");
        int eleccion = input.nextInt();
    
        int resultado = tirarRuleta();
    
        boolean esPar = (resultado != 0 && resultado % 2 == 0);
        if ((eleccion == 1 && esPar) || (eleccion == 2 && !esPar)) {
            System.out.println("¡Felicidades! Ganaste x1 tu apuesta.");
            jugador.agregarFichas(apuesta * 2);
        } else {
            System.out.println("Lo siento, perdiste.");
            jugador.restarFichas(apuesta);
        }
    }
    
    public void apostarPorNumero(Scanner input) {
        System.out.println("Elige un número entre 0 y 36:");
        int numeroApostado = input.nextInt();
    
        if (numeroApostado < 0 || numeroApostado > 36) {
            System.out.println("Número inválido. Intenta de nuevo.");
            return;
        }
    
        int resultado = tirarRuleta();
    
        if (numeroApostado == resultado) {
            System.out.println("¡Felicidades! Ganaste x35 tu apuesta!");
            jugador.agregarFichas(apuesta * 36);
        } else {
            System.out.println("Lo siento, perdiste.");
            jugador.restarFichas(apuesta);
        }
    }

    public void apostarPorDocena(Scanner input) {
    
        System.out.println("Elige una docena: 1 (1-12), 2 (13-24), o 3 (25-36):");
        int docena = input.nextInt();
    
        if (docena < 1 || docena > 3) {
            System.out.println("Docena inválida. Intenta de nuevo.");
            return;
        }
    
        int resultado = tirarRuleta();
    
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
    
    
    public void apostarMitad(Scanner input) {
    
        System.out.println("Elige una mitad: 1 (1-18) o 2 (19-36):");
        int mitad = input.nextInt();
    
        if (mitad != 1 && mitad != 2) {
            System.out.println("Mitad inválida. Intenta de nuevo.");
            return;
        }
    
        int resultado = tirarRuleta();
    
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
