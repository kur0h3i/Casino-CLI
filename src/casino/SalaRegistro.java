package casino;

import java.util.Scanner;

import personas.*;

public class SalaRegistro {
    
    // Atributos
    public static Jugador jugador;

    // Constructor
    public SalaRegistro() {
        // ASCII ART
        asciiArt();
        // Información sobre el Casino
        informacionCasino();
        // Datos del Jugador
        jugador = crearJugador();
    }

    // ASCII ART
    public void asciiArt() {
        System.out.println(
            "   ______           _                  ____                    __   \n" +
            "  / ____/___ ______(_)___  ____       / __ \\____  __  ______ _/ /__ \n" +
            " / /   / __ `/ ___/ / __ \\/ __ \\     / /_/ / __ \\/ / / / __ `/ / _ \\\n" +
            "/ /___/ /_/ (__  ) / / / / /_/ /    / _, _/ /_/ / /_/ / /_/ / /  __/\n" +
            "\\____/\\__,_/____/_/_/ /_/\\____/    /_/ |_|\\____/\\__, /\\__,_/_/\\___/ \n" +
            "                                               /____/               \n"
        );
    }

    // Información sobre el Casino
    public void informacionCasino() {
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Bienvenidos a Casino Royale, un emocionante juego de casino donde tu objetivo es convertir una pequeña cantidad de 200€ en 1000€ (en el modo normal) a través de una serie de apuestas en varios juegos clásicos de casino. Este desafiante reto pondrá a prueba tu habilidad para gestionar el dinero.");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
    }      

    // Crear al Jugador
    public Jugador crearJugador() {
        // Inicialización de variables
        @SuppressWarnings("resource")
        Scanner input = new Scanner(System.in); // No lo cierres
        String nombre;
        int edad;
        char confirmacion;
        double dinero = 0f;
        int dificultad = 0;
    
        System.out.println("Cuéntame un poco sobre ti:");
        do {
            // Nombre
            System.out.println("¿Cuál es tu nombre?");
            nombre = input.nextLine();
            System.out.println("Con que te llamas " + nombre);
            
            // Edad
            System.out.println(nombre + ", ¿cuántos años tienes?");
            while (!input.hasNextInt()) {
                System.out.println("Por favor, ingresa una edad válida.");
                input.next(); 
            }
            edad = input.nextInt();
            input.nextLine(); 
            
            // Dificultad
            do {
                System.out.println("Selecciona la dificultad:\n\t1. Fácil -> 500€\n\t2. Normal -> 200€\n\t3. Difícil -> 50€");
                while (!input.hasNextInt()) {
                    System.out.println("Por favor, selecciona una opción válida.");
                    input.next(); // Limpiar el buffer
                }
                dificultad = input.nextInt();
                input.nextLine(); // Limpiar el buffer
            } while (dificultad != 1 && dificultad != 2 && dificultad != 3);
            
            // Asignación del dinero según la dificultad
            switch (dificultad) {
                case 1:
                    dinero = 500f;
                    break;
                case 2:
                    dinero = 200f;
                    break;
                case 3:
                    dinero = 50f;
                    break;
                default:
                    System.out.println("Error: Dinero no configurado.");
                    break;
            }
            
            // Confirmación de los datos
            System.out.println("Te llamas " + nombre + " y tienes " + edad + " años, ¿es correcto? S/n");
            confirmacion = input.nextLine().charAt(0);
    
        } while (confirmacion != 'S' && confirmacion != 's');
        
        // input.close(); // Elimina esta línea
    
        // Crear el jugador con los datos confirmados
        Jugador jugador = new Jugador(nombre, edad, dinero);
        
        return jugador;
    }
    

    // Verificar si el jugador es mayor de edad
    public boolean mayorEdad() {
        if (jugador.getEdad() >= 18) {
            return true;
        } else {
            System.out.println("Lo sentimos, pero los menores de edad no pueden entrar al casino.");
            return false;
        }
    }

}