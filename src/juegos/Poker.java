package juegos;

import recursos.Baraja;
import recursos.Carta;

import java.util.*;

public class Poker extends Juego{

    // Atributos
    private Baraja baraja;
    private List<Carta> cartasComunitarias; // Cartas visibles para todos
    private List<Carta> manoJugador;        // Mano del jugador
    private List<Carta> manoCPU;            // Mano de la CPU (oponente simple)
    private int fichasJugador;              // Fichas del jugador
    private int fichasPot;                 // Total en el "pozo" de apuestas
    private boolean jugadorActivo;         // Estado de si el jugador sigue en la partida

    // Constructor
    public Poker() {
        baraja = new Baraja();
        cartasComunitarias = new ArrayList<>();
        manoJugador = new ArrayList<>();
        manoCPU = new ArrayList<>();
        fichasJugador = 1000; // El jugador comienza con 1000 fichas
        fichasApostadas = 0;
        fichasPot = 0;
        jugadorActivo = true;
    }

    // Método para iniciar una partida de póker
    public void iniciarPartida() {
        Scanner input = new Scanner(System.in);
        
        System.out.println("¡Bienvenido al Poker Texas Hold'em!");
        System.out.println("Comienzas con " + fichasJugador + " fichas.");

        // Reiniciar baraja y repartir cartas iniciales
        baraja.reiniciar();
        repartirManosIniciales();

        // Apuesta inicial
        apuestaInicial(input);

        if (!jugadorActivo) return;

        // Simular las fases del póker
        for (int ronda = 1; ronda <= 3; ronda++) {
            mostrarCartasComunitarias(ronda);
            if (ronda < 3) {
                System.out.println("¿Quieres continuar? (s/n)");
                String respuesta = input.nextLine();
                if (respuesta.equalsIgnoreCase("n")) {
                    System.out.println("Te retiraste de la partida.");
                    jugadorActivo = false;
                    return;
                }
            }
        }

        // Mostrar las manos finales y determinar el ganador
        System.out.println("\nFase final: Revelando cartas...");
        System.out.println("Cartas comunitarias: " + cartasComunitarias);
        System.out.println("Tu mano completa: " + manoJugador);
        System.out.println("Mano de la CPU: " + manoCPU);

        determinarGanador();
    }

    // Repartir cartas iniciales a cada jugador
    private void repartirManosIniciales() {
        manoJugador.add(baraja.repartir());
        manoJugador.add(baraja.repartir());

        manoCPU.add(baraja.repartir());
        manoCPU.add(baraja.repartir());
    }

    // Apuesta inicial del jugador
    private void apuestaInicial(Scanner input) {
        System.out.println("Haz tu apuesta inicial.");
        System.out.print("Cantidad a apostar: ");
        int apuesta = input.nextInt();
        input.nextLine(); // Limpiar el buffer

        if (apuesta > fichasJugador) {
            System.out.println("No tienes suficientes fichas.");
            jugadorActivo = false;
            return;
        }

        fichasApostadas = apuesta;
        fichasJugador -= apuesta;
        fichasPot += apuesta;
        System.out.println("Apostaste " + apuesta + " fichas.");
    }

    // Mostrar cartas comunitarias por fases
    private void mostrarCartasComunitarias(int ronda) {
        if (ronda == 1) {
            // Flop (3 cartas)
            cartasComunitarias.add(baraja.repartir());
            cartasComunitarias.add(baraja.repartir());
            cartasComunitarias.add(baraja.repartir());
        } else if (ronda == 2) {
            // Turn (1 carta adicional)
            cartasComunitarias.add(baraja.repartir());
        } else if (ronda == 3) {
            // River (1 carta adicional)
            cartasComunitarias.add(baraja.repartir());
        }

        System.out.println("Cartas comunitarias actuales: " + cartasComunitarias);
    }

    // Determinar el ganador y repartir el pozo
    private void determinarGanador() {
        // Obtener la mejor mano del jugador y la CPU
        List<Carta> manoJugadorCompleta = new ArrayList<>(manoJugador);
        manoJugadorCompleta.addAll(cartasComunitarias);
        List<Carta> manoCPUCompleta = new ArrayList<>(manoCPU);
        manoCPUCompleta.addAll(cartasComunitarias);

        // Evaluar las manos
        int valorJugador = evaluarMano(manoJugadorCompleta);
        int valorCPU = evaluarMano(manoCPUCompleta);

        System.out.println("Valor de tu mano: " + valorJugador);
        System.out.println("Valor de la mano CPU: " + valorCPU);

        if (valorJugador > valorCPU) {
            System.out.println("¡Ganaste!");
            fichasJugador += fichasPot;
        } else if (valorJugador < valorCPU) {
            System.out.println("La CPU gana.");
        } else {
            System.out.println("Es un empate.");
            fichasJugador += fichasPot / 2; // En caso de empate, se divide el pozo
        }

        System.out.println("Tus fichas actuales: " + fichasJugador);
    }

    // Método para evaluar las manos (ya se explicó en el código anterior)
    private int evaluarMano(List<Carta> mano) {
        // Ordenar las cartas de mayor a menor
        mano.sort((c1, c2) -> obtenerValorCarta(c2).compareTo(obtenerValorCarta(c1)));

        if (esEscaleraReal(mano)) return 10;
        if (esEscaleraDeColor(mano)) return 9;
        if (esPóker(mano)) return 8;
        if (esFullHouse(mano)) return 7;
        if (esColor(mano)) return 6;
        if (esEscalera(mano)) return 5;
        if (esTrio(mano)) return 4;
        if (esDoblePar(mano)) return 3;
        if (esPar(mano)) return 2;
        return 1; // Carta alta
    }

    // Obtener valor de la carta (ya explicado en el código anterior)
    private Integer obtenerValorCarta(Carta carta) {
        String valor = carta.getNumero();
        switch (valor) {
            case "J": return 11;
            case "Q": return 12;
            case "K": return 13;
            case "A": return 14;
            default: return Integer.parseInt(valor);
        }
    }

    // Evaluar si la mano es Escalera Real, Escalera de Color, etc. (ya explicado anteriormente)
    private boolean esEscaleraReal(List<Carta> mano) {
        return esEscaleraDeColor(mano) && obtenerValorCarta(mano.get(0)) == 14; // As es la carta más alta
    }

    private boolean esEscaleraDeColor(List<Carta> mano) {
        return esEscalera(mano) && esMismoPalo(mano);
    }

    private boolean esEscalera(List<Carta> mano) {
        for (int i = 0; i < mano.size() - 1; i++) {
            if (obtenerValorCarta(mano.get(i)) - obtenerValorCarta(mano.get(i + 1)) != 1) {
                return false;
            }
        }
        return true;
    }

    private boolean esColor(List<Carta> mano) {
        return esMismoPalo(mano);
    }

    private boolean esPóker(List<Carta> mano) {
        Map<String, Integer> frecuencia = contarValores(mano);
        return frecuencia.containsValue(4);
    }

    private boolean esFullHouse(List<Carta> mano) {
        Map<String, Integer> frecuencia = contarValores(mano);
        return frecuencia.containsValue(3) && frecuencia.containsValue(2);
    }

    private boolean esTrio(List<Carta> mano) {
        Map<String, Integer> frecuencia = contarValores(mano);
        return frecuencia.containsValue(3);
    }

    private boolean esDoblePar(List<Carta> mano) {
        Map<String, Integer> frecuencia = contarValores(mano);
        return frecuencia.containsValue(2) && frecuencia.size() == 3;
    }

    private boolean esPar(List<Carta> mano) {
        Map<String, Integer> frecuencia = contarValores(mano);
        return frecuencia.containsValue(2);
    }

    private boolean esMismoPalo(List<Carta> mano) {
        String palo = mano.get(0).getTipo();
        for (Carta carta : mano) {
            if (!carta.getTipo().equals(palo)) {
                return false;
            }
        }
        return true;
    }

    private Map<String, Integer> contarValores(List<Carta> mano) {
        Map<String, Integer> frecuencia = new HashMap<>();
        for (Carta carta : mano) {
            frecuencia.put(carta.getNumero(), frecuencia.getOrDefault(carta.getNumero(), 0) + 1);
        }
        return frecuencia;
    }
}