

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

    public SalaPrincipal(Jugador juador) throws IOException{
        
        // Construir Terminal
        Terminal terminal = TerminalBuilder.terminal();
        
        // Mesas
        List<Mesa> mesas = new ArrayList<>();
        mesas.add(new Mesa(new Ruleta(), "Ruleta", 1, new int[][]{{9, 4}}));
        mesas.add(new Mesa(new Poker(), "Poker", 4, new int[][]{{14, 11}}));
        
        try {

            boolean running = true;
            while (running) {

                limpiarPantalla();

                // Interfaz del juego
                // Informacion Usuario
                System.out.println("----------------------------");
                System.out.println("Nombre :  " + juador.getName());
                System.out.println("Dinero : " + juador.getDinero());
                System.out.println("Fichas : " + juador.getFichas());
                System.out.println("POSX : " + posX);
                System.out.println("POSY : " + posY);
                System.out.println("---------------------------");
                //Mapa
                mostrarMapa();
                // Instrucciones
                System.out.println("Usa WASD para moverte, Q para salir:");
                for (Mesa mesa : mesas) {
                    if (mesa.getPosicionInteractuar()[0][0] == posX && mesa.getPosicionInteractuar()[0][1] == posY) {
                        System.out.println("Usa E para unirse a la mesa de " + mesa.getNombreMesa());
                    }
                }

                // Puerta Salida
                if (posX == 4 && posY == 0){
                    System.out.println("Usa E para guardar partida ");
                    System.out.println("Usa C para cargar partida ");
                    System.out.println("Usa Q para Salir");
                }
                
                

                int key = terminal.reader().read();

                switch (key) {
                    case 'w': moverJugador(0, -1); break;
                    case 's': moverJugador(0, 1); break;
                    case 'a': moverJugador(-1, 0); break;
                    case 'd': moverJugador(1, 0); break;
                    case 'e': System.out.println("Iniciar Juego");
                    case 'q': running = false; break;
                }
            }
        } finally {
            terminal.close();
        }
    }

    public void limpiarPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void mostrarMapa() {
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                if (i == posY && j == posX) {
                    System.out.print("P ");
                } else {
                    System.out.print(mapa[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public void moverJugador(int dx, int dy) {
        int nuevaPosX = posX + dx;
        int nuevaPosY = posY + dy;

        if (nuevaPosX >= 0 && nuevaPosX < mapa[0].length && nuevaPosY >= 0 && nuevaPosY < mapa.length
                && mapa[nuevaPosY][nuevaPosX] != '#') {
            posX = nuevaPosX;
            posY = nuevaPosY;
        }
    }
}

