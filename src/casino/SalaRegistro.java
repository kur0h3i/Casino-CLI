package casino;

import java.util.Scanner;
import jugadores.*;

public class SalaRegistro {
    
    // Atributos
    Jugador jugador;

    public SalaRegistro(){
        //ASCII ART
        asciiArt();
        // Informacion Casino
        informacionCasino();
        // Datos Jugador
        jugador = crearJugador();
    }

    public void asciiArt(){
        System.out.println(
            "   ______           _                  ____                    __   \n" +
            "  / ____/___ ______(_)___  ____       / __ \\____  __  ______ _/ /__ \n" +
            " / /   / __ `/ ___/ / __ \\/ __ \\     / /_/ / __ \\/ / / / __ `/ / _ \\\n" +
            "/ /___/ /_/ (__  ) / / / / /_/ /    / _, _/ /_/ / /_/ / /_/ / /  __/\n" +
            "\\____/\\__,_/____/_/_/ /_/\\____/    /_/ |_|\\____/\\__, /\\__,_/_/\\___/ \n" +
            "                                               /____/               \n"
            );
    }

    public void informacionCasino(){
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Bienvenidos a Casino Royale, un emocionante juego de casino donde tu objetivo es convertir una pequeña cantidad de 200€ en 1000€ a través de una serie de apuestas en varios juegos clásicos de casino. Este desafiante reto pondrá a prueba tu habilidad para gestionar el dinero.");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
    }   

    public Jugador crearJugador(){
        // Inicializacion de variables
        Scanner input = new Scanner( System.in);
        Jugador jugador;
        String nombre;
        int edad;
        char opcion = 'x';

        System.out.println("Cuentame un poco sobre ti : ");
        do {
            // Nombre
            System.out.println("Tu nombre es? ");
            nombre = input.nextLine();
            System.out.println("Con que te llamas " + nombre);
            // Edad
            System.out.println(nombre + " y cuantos añso tines? ");
            edad = input.nextInt();
            input.nextLine();
            // Comprobacion de campos
            System.out.println("Con que te llamas "+ nombre + "y tienes " + edad + "correcto ? S/n");
            opcion = input.nextLine().charAt(0);
        } while (opcion != 'S');
        input.close();
        
        jugador = new Jugador(nombre, edad, 200f);
        
        return jugador;
    }

    public boolean mayorEdad(){
        if (jugador.getEdad() >= 18){
            
            return true;
        }
        else {
            System.out.println("Lo sentimos pero menores de edad no pueden entrar al casino");
            return false;
        }
    }
}
