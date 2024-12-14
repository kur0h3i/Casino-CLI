package ascii;

public class ASCIICartaMasAlta {


    // Mostrar el título del juego
    public void titulo() {
        System.err.println("  ____           _           _    _ _        ");
        System.err.println(" / ___|__ _ _ __| |_ __ _   / \\  | | |_ __ _ ");
        System.err.println("| |   / _` | '__| __/ _` | / _ \\ | | __/ _` |");
        System.err.println("| |__| (_| | |  | || (_| |/ ___ \\| | || (_| |");
        System.err.println(" \\____\\__,_|_|   \\__\\__,_/_/   \\_\\_|\\__\\__,_|");
    }

    // Mostrar cheatsheet con reglas y pagos
    public void cheatsheet() {
        System.out.println("____________________________ CHEATSHEET ____________________________");
        System.out.println("| Resultado                  | Pago                                |");
        System.out.println("|----------------------------|--------------------------------------|");
        System.out.println("| Tu carta > Carta IA        | Ganas el doble de tu apuesta         |");
        System.out.println("| Tu carta < Carta IA        | Pierdes tu apuesta                   |");
        System.out.println("| Tu carta == Carta IA       | Recuperas tu apuesta                 |");
        System.out.println("|__________________________________________________________________|");
    }

    // Mostrar opciones para el jugador
    public void opciones() {
        System.out.println("________________________ OPCIONES ________________________");
        System.out.println("| 1. Apostar y jugar                                    |");
        System.out.println("| 2. Ver cheatsheet                                     |");
        System.out.println("| 3. Salir                                              |");
        System.out.println("|_______________________________________________________|");
    }
}

