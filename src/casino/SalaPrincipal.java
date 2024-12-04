package casino;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import juegos.*;
import personas.Jugador;
import recursos.Mesa;

public class SalaPrincipal {

    char[][] mapa = MapaCasino.mapa;
    int posX = MapaCasino.posX;
    int posY = MapaCasino.posY;

    public SalaPrincipal(Jugador jugador) throws IOException {
        // Construir Terminal
        Terminal terminal = TerminalBuilder.terminal();
        
        jugador.setFichas(100); // Fichas iniciales del jugador
        
        // Mesas disponibles (agregar las mesas a la lista)
        List<Mesa> mesas = new ArrayList<>();
        mesas.add(new Mesa(new Ruleta(jugador), "Ruleta", 1, new int[][]{{9, 4}})); // Asegúrate de que la posición es correcta
        
            boolean running = true;
            while (running) {
                limpiarPantalla();
                interfazPrincipal(jugador, mesas);
                salirPuerta();
                entradaTermianal(terminal, jugador, mesas);
            }
               
    }
    

    // Limpiar pantalla (función para terminal)
    public static void limpiarPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void interfazPrincipal(Jugador jugador, List<Mesa> mesas){
        // Interfaz del jugador
        System.out.println("----------------------------");
        System.out.println("Nombre :  " + jugador.getName());
        System.out.println("Dinero : " + jugador.getDinero());
        System.out.println("Fichas : " + jugador.getFichas());
        System.out.println("POSX : " + posX);
        System.out.println("POSY : " + posY);
        System.out.println("----------------------------");

        // Mostrar el mapa del casino
        mostrarMapa();

        // Instrucciones de control
        System.out.println("Usa WASD para moverte, E para unirte a la mesa, Q para salir:");
        
        // Interacción con mesas
        for (Mesa mesa : mesas) {
            if (mesa.getPosicionInteractuar()[0][0] == posX && mesa.getPosicionInteractuar()[0][1] == posY) {
                System.out.println("Usa E para unirte a la mesa de " + mesa.getNombreMesa());
            }
        }
    }

    // Mostrar mapa del casino
    public void mostrarMapa() {
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                if (i == posY && j == posX) {
                    System.out.print("P ");  // Representar jugador con "P"
                } else {
                    System.out.print(mapa[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    // Mover jugador en el mapa
    public void moverJugador(int dx, int dy) {
        int nuevaPosX = posX + dx;
        int nuevaPosY = posY + dy;

        // Comprobar que la nueva posición esté dentro de los límites y no sea un muro (#)
        if (nuevaPosX >= 0 && nuevaPosX < mapa[0].length && nuevaPosY >= 0 && nuevaPosY < mapa.length
                && mapa[nuevaPosY][nuevaPosX] != '#') {
            posX = nuevaPosX;
            posY = nuevaPosY;
        }
    }

    public void salirPuerta(){
        // Puerta de salida
        if (posX == 4 && posY == 0) {
            System.out.println("Usa E para guardar partida ");
            System.out.println("Usa C para cargar partida ");
            System.out.println("Usa Q para Salir");
        }
    }

    public void entradaTermianal(Terminal terminal, Jugador jugador, List<Mesa> mesas) throws IOException{
         // Leer entrada del teclado
        int key = terminal.reader().read();
    
        switch (key) {
             case 'w':
                 moverJugador(0, -1);
                 break;
             case 's':
                 moverJugador(0, 1);
                 break;
             case 'a':
                 moverJugador(-1, 0);
                 break;
             case 'd':
                 moverJugador(1, 0);
                 break;
             case 'e':
                 // Verificar si estamos en la posición de la mesa y unirse
                 for (Mesa mesa : mesas) {
                     if (mesa.getPosicionInteractuar()[0][0] == posX && mesa.getPosicionInteractuar()[0][1] == posY) {
                        mesa.jugar();
                     }
                 }
                 break;
             case 'q':
                 break;
        }
    }
}
