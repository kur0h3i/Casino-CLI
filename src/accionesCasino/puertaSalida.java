package accionesCasino;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import excep.ExcepcionJugadorNoEncontrado;
import excep.ExcepcionJugadorSinDinero;
import excep.ExcepcionJugadorSinFichas;
import personas.Jugador;
import ascii.ASCIIPuerta;
import ascii.ASCIIGeneral;

public class PuertaSalida {
    
    // Atrubtos
    Jugador jugador;
    ASCIIPuerta interfaz;

    // Constructor
    public PuertaSalida(Jugador jugador){
        Scanner input = new Scanner (System.in);
        this.jugador = jugador;
        interfaz = new ASCIIPuerta(jugador);
        inicarPuerta(input);  
    }

    // Metodos
    public void inicarPuerta(Scanner input){
        
        // Guardar Partida
        // Cargar Partida
        // Salir
        int opcion = 0;
        while (opcion != 4) {
            ASCIIGeneral.limpiarPantalla();
            interfaz.titulo();
            interfaz.opcioes();
            try {
                opcion = input.nextInt();
                input.nextLine(); 
                
                // Opciones del menú principal
                switch (opcion) {
                    case 1:
                        guardarPartida();
                        ASCIIGeneral.esperarTecla();
                        break;
                    case 2:
                        cargarPartida();
                        ASCIIGeneral.esperarTecla();
                        break;
                    case 3:
                        salir();
                        break;
                    case 4:
                        System.err.println("Volviendo al casino");
                        ASCIIGeneral.limpiarPantalla();
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Intenta de nuevo.");
                // Limpiar Buffer
                input.nextLine(); 
            }
        }
    }

    public void salir(){
        System.out.println("Saliendo del casino");
        System.exit(0);
    }

    public void guardarPartida() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("saves/" + jugador.getName() +".dat"))) {
            oos.writeObject(jugador);
            System.out.println("Partida guardada exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar la partida: " + e.getMessage());
        }
    }

    public void cargarPartida() {
        Scanner input = new Scanner(System.in);
        System.out.println("Nombre del jugador que quieres cargar: ");
        String nombreJugador = input.nextLine();

        // Archivo que se espera cargar
        File archivo = new File("saves/" + nombreJugador + ".dat");

        try {
            // Validar si el archivo existe
            if (!archivo.exists()) {
                throw new ExcepcionJugadorNoEncontrado("El jugador con nombre '" + nombreJugador + "' no existe.");
            }

            // Intentar leer el archivo y deserializar
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
                jugador = (Jugador) ois.readObject();
                System.out.println("Partida cargada exitosamente: ");
                System.out.println(jugador);
            }
        } catch (ExcepcionJugadorNoEncontrado e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Error al cargar los datos del jugador: " + e.getMessage());
        }
    }
}


