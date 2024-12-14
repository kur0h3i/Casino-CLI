package accionesCasino;

import java.io.*;
import java.util.Scanner;
import excep.ExcepcionJugadorNoEncontrado;
import personas.Jugador;
import ascii.ASCIICajero;
import ascii.ASCIIPuerta;

public class PuertaSalida {
    
    // Atrubtos
    Jugador jugador;
    ASCIICajero interfaz;

    // Constructor
    public PuertaSalida(Jugador jugador){
        Scanner input = new Scanner (System.in);
        this.jugador = jugador;
        interfaz = new ASCIICajero(jugador);
        inicarPuerta(input);  
    }

    // Metodos
    public void inicarPuerta(Scanner inpout){
        
        // Guardar Partida
        // Cargar Partida
        // Salir
    }

    public void salir(){
        System.out.println("Saliendo del casino");
        System.exit(0);
    }

    public void guardarPartida() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("saves/" + jugador.getNombre() +".dat"))) {
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


