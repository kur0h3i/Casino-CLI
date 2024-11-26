package blackjack;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BlackJackUI extends JFrame {

    private Baraja baraja;
    private Jugador jugador;
    private Jugador crupier;

    private JPanel jugadorPanel;
    private JPanel crupierPanel;
    private JButton pedirCartaButton;
    private JButton plantarseButton;
    private JLabel mensajeLabel;

    public BlackJackUI() {
        // Configuración básica de la ventana
        setTitle("Black Jack");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        baraja = new Baraja();
        jugador = new Jugador("Jugador");
        crupier = new Jugador("Crupier");

        iniciarJuego();

        // Panel superior para las cartas del crupier
        crupierPanel = new JPanel();
        crupierPanel.setLayout(new FlowLayout());
        add(crupierPanel, BorderLayout.NORTH);

        // Panel inferior para las cartas del jugador
        jugadorPanel = new JPanel();
        jugadorPanel.setLayout(new FlowLayout());
        add(jugadorPanel, BorderLayout.CENTER);

        // Panel inferior para botones y mensajes
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BorderLayout());

        pedirCartaButton = new JButton("Pedir Carta");
        plantarseButton = new JButton("Plantarse");
        mensajeLabel = new JLabel("Tu turno", SwingConstants.CENTER);

        controlPanel.add(mensajeLabel, BorderLayout.NORTH);
        controlPanel.add(pedirCartaButton, BorderLayout.WEST);
        controlPanel.add(plantarseButton, BorderLayout.EAST);
        add(controlPanel, BorderLayout.SOUTH);

        // Listeners para botones
        pedirCartaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jugador.recibirCarta(baraja.repartir());
                actualizarPantalla();

                if (!jugador.estaEnJuego()) {
                    mostrarMensaje("¡Te has pasado de 21!");
                    terminarJuego();
                }
            }
        });

        plantarseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                turnoCrupier();
                determinarGanador();
            }
        });

        actualizarPantalla();
    }

    private void iniciarJuego() {
        jugador.recibirCarta(baraja.repartir());
        jugador.recibirCarta(baraja.repartir());
        crupier.recibirCarta(baraja.repartir());
        crupier.recibirCarta(baraja.repartir());
    }

    private void actualizarPantalla() {
        jugadorPanel.removeAll();
        crupierPanel.removeAll();

        // Mostrar cartas del jugador
        for (int i = 0; i < jugador.getMano().length && jugador.getMano()[i] != null; i++) {
            Carta carta = jugador.getMano()[i];
            jugadorPanel.add(crearEtiquetaCarta(carta));
        }

        // Mostrar cartas del crupier
        for (int i = 0; i < crupier.getMano().length && crupier.getMano()[i] != null; i++) {
            Carta carta = crupier.getMano()[i];
            // Muestra la primera carta del crupier, y oculta las demás con un reverso
            if (i == 0 || !pedirCartaButton.isEnabled()) {
                crupierPanel.add(crearEtiquetaCarta(carta));
            } else {
                crupierPanel.add(crearEtiquetaCarta(new Carta("reverso", "reverso")));
            }
        }

        revalidate();
        repaint();
    }

    private JLabel crearEtiquetaCarta(Carta carta) {
        String ruta = "asstes/cartas/" + carta.getNombreArchivo();
        System.out.println(ruta); // Ruta de la imagen
        ImageIcon icono = new ImageIcon(ruta);
        if (icono.getIconWidth() == -1) {
            System.out.println("No se pudo cargar la imagen: " + ruta);
        }
        JLabel label = new JLabel(icono);
        return label;
    }

    private void turnoCrupier() {
        while (crupier.calcularPuntaje() < 17) {
            crupier.recibirCarta(baraja.repartir());
        }
        actualizarPantalla();
    }

    private void determinarGanador() {
        int puntajeJugador = jugador.calcularPuntaje();
        int puntajeCrupier = crupier.calcularPuntaje();

        if (!jugador.estaEnJuego() || (puntajeCrupier <= 21 && puntajeCrupier > puntajeJugador)) {
            mostrarMensaje("El crupier gana.");
        } else if (!crupier.estaEnJuego() || puntajeJugador > puntajeCrupier) {
            mostrarMensaje("¡Has ganado!");
        } else {
            mostrarMensaje("Es un empate.");
        }

        terminarJuego();
    }

    private void mostrarMensaje(String mensaje) {
        mensajeLabel.setText(mensaje);
    }

    private void terminarJuego() {
        pedirCartaButton.setEnabled(false);
        plantarseButton.setEnabled(false);
        actualizarPantalla();
    }
}