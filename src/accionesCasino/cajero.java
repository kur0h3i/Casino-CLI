package accionesCasino;


import ascii.ASCIIGeneral;
import excep.ExcepcionJugadorSinDinero;
import excep.ExcepcionJugadorSinFichas;

import java.util.InputMismatchException;
import java.util.Scanner;

import ascii.ASCIICajero;
import personas.Jugador;


public class Cajero {

    // Atributos Cajero
    Jugador jugador;
    ASCIICajero interfaz;

    // Constructor
    public Cajero(Jugador jugador) throws ExcepcionJugadorSinDinero, ExcepcionJugadorSinFichas{
        Scanner input = new Scanner (System.in);
        this.jugador = jugador;
        interfaz = new ASCIICajero(jugador);
        iniciarCajero(input);    
    }

    public void iniciarCajero(Scanner input){
        
        int opcion = 0;
        while (opcion != 3) {
            ASCIIGeneral.limpiarPantalla();
            interfaz.titulo();
            interfaz.opcioes();
            try {
                opcion = input.nextInt();
                input.nextLine(); 
                
                // Opciones del menú principal
                switch (opcion) {
                    case 1:
                        try{
                            dineroFichas(definirValor(input));     
                        } 
                        catch (ExcepcionJugadorSinDinero e){
                            System.out.println("No puedes cambiar porque no tienes dinero.");
                            ASCIIGeneral.esperarTecla();
                        }
                        
                        break;
                    case 2:
                        try{   
                            fichasDinero(definirValor(input));
                        }catch (ExcepcionJugadorSinFichas e){
                            System.out.println("No puedes cambiar porque no tienes fichas.");
                            ASCIIGeneral.esperarTecla();
                        }
                        break;
                    case 3:
                        System.out.println("Saliendo del juego...");
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

    

    public void comprobarfichas() throws ExcepcionJugadorSinFichas{
        if (jugador.getFichas() <= 0) {
            throw new ExcepcionJugadorSinFichas("Jugador sin fichas");
        }
    }

    public void comprobarDinero() throws ExcepcionJugadorSinDinero{
        if (jugador.getDinero() <= 0){
            throw new ExcepcionJugadorSinDinero("Jugador sin dinero");
        }

    }

    public void fichasDinero(int valor) throws ExcepcionJugadorSinFichas {
        comprobarfichas();
        System.out.println("Cambiando " + valor + " fichas por dinero...");
        jugador.restarFichas(valor);
        jugador.agregarDinero(valor);
    }

    public void dineroFichas(int valor) throws ExcepcionJugadorSinDinero {
        comprobarDinero();
        System.out.println("Cambiando " + valor + " dinero por fichas...");
        jugador.restarDinero(valor);
        jugador.agregarFichas(valor);
    }

    public int definirValor(Scanner input) {
        int valor = -1;
        while (valor <= 0) { // Asegurar que el valor sea mayor que cero
            System.out.println("¿Cuál es el valor que quieres intercambiar?");
            try {
                valor = input.nextInt();
                if (valor <= 0) {
                    System.out.println("El valor debe ser mayor que cero. Intenta de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Intenta de nuevo.");
                input.nextLine(); // Limpiar el buffer
            }
        }
        return valor;
    }
}

