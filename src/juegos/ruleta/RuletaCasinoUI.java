package juegos.ruleta;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RuletaCasinoUI extends JFrame {
    
    private static final String[] NUMEROS = {
        "0", "32", "15", "19", "4", "21", "2", "25", "17", "34", "6", "27", "13", "36", "11", "30", "8", "23", "10", "5", 
        "24", "16", "33", "1", "20", "14", "31", "9", "22", "18", "29", "7", "28", "12", "35", "3", "26", "0"
    };
    
    private static final String[] COLORES = {
        "verde", "rojo", "rojo", "negro", "rojo", "negro", "rojo", "negro", "rojo", "negro", "rojo", "negro", "rojo", 
        "negro", "rojo", "negro", "rojo", "negro", "rojo", "negro", "rojo", "negro", "rojo", "negro", "rojo", "negro", 
        "rojo", "negro", "rojo", "negro", "rojo", "negro", "rojo", "negro", "rojo"
    };

    private JTextField apuestaField;
    private JButton girarButton;
    private JLabel resultadoLabel;
    private JLabel numeroGiradaLabel;
    private JLabel colorGiradaLabel;
    private JLabel mensajeLabel;
    
    // Panel donde dibujaremos la ruleta
    private JPanel ruletaPanel;
    private Timer timer;
    private double anguloActual = 0;
    
    // Constructor de la ventana
    public RuletaCasinoUI() {
        setTitle("Ruleta de Casino");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel para mostrar la ruleta girando
        ruletaPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Rotar la imagen de la ruleta para simular el giro
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Cargar y redimensionar la imagen de la ruleta
                ImageIcon ruletaIcon = new ImageIcon("assets/ruleta.png");
                Image ruletaImage = ruletaIcon.getImage();
                int width = 250; // Nuevo tamaño para la ruleta
                int height = 250; // Nuevo tamaño para la ruleta
                ruletaImage = ruletaImage.getScaledInstance(width, height, Image.SCALE_DEFAULT);

                // Trasladar el punto de origen al centro de la ruleta
                g2d.translate(getWidth() / 2 - width / 2, getHeight() / 4);
                g2d.rotate(Math.toRadians(anguloActual), width / 2, height / 2);
                g2d.drawImage(ruletaImage, 0, 0, this);
            }
        };
    
        add(ruletaPanel, BorderLayout.CENTER);

        // Panel para los controles de la UI (apuesta y botones)
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());

        apuestaField = new JTextField(10);
        girarButton = new JButton("Girar Ruleta");
        resultadoLabel = new JLabel("Resultado de la apuesta: ", SwingConstants.CENTER);

        bottomPanel.add(new JLabel("Apuesta (número 0-36, rojo, negro, par, impar): "));
        bottomPanel.add(apuestaField);
        bottomPanel.add(girarButton);
        bottomPanel.add(resultadoLabel);

        add(bottomPanel, BorderLayout.SOUTH);

        // Panel superior para mostrar resultados
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(3, 1));

        numeroGiradaLabel = new JLabel("Número girado: ", SwingConstants.CENTER);
        colorGiradaLabel = new JLabel("Color: ", SwingConstants.CENTER);
        mensajeLabel = new JLabel("¡Haz tu apuesta!", SwingConstants.CENTER);
        
        topPanel.add(numeroGiradaLabel);
        topPanel.add(colorGiradaLabel);
        topPanel.add(mensajeLabel);
        
        add(topPanel, BorderLayout.NORTH);

        // Acción del botón para girar la ruleta
        girarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                girarRuleta();
            }
        });

        // Hacer la ventana visible
        setVisible(true);
    }

    // Iniciar la animación del giro de la ruleta
    private void girarRuleta() {
        // Crear un nuevo Timer para simular el giro de la ruleta
        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aumentar el ángulo de rotación
                anguloActual += 10; 
                if (anguloActual >= 360) {
                    anguloActual = 0; // Resetear la rotación si pasa de 360 grados
                }
                ruletaPanel.repaint();
            }
        });
        
        // Iniciar el giro durante 3 segundos
        timer.start();
        
        // Después de 3 segundos, detener la animación y mostrar el número ganador
        new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop(); // Detener la animación
                mostrarResultado();
            }
        }).start();
    }

    // Mostrar el resultado después del giro
    private void mostrarResultado() {
        Random random = new Random();
        int resultadoRuleta = random.nextInt(37); // Número aleatorio entre 0 y 36
        String numeroGirada = NUMEROS[resultadoRuleta];
        String colorGirada = COLORES[resultadoRuleta];
        
        numeroGiradaLabel.setText("Número girado: " + numeroGirada);
        colorGiradaLabel.setText("Color: " + colorGirada);

        // Obtener la apuesta del jugador
        String apuesta = apuestaField.getText().toLowerCase();

        // Verificar si la apuesta es correcta
        if (apuesta.equals(numeroGirada)) {
            mensajeLabel.setText("¡Felicidades! Has acertado el número.");
            resultadoLabel.setText("Resultado: ¡Has ganado!");
        } else if (apuesta.equals(colorGirada)) {
            mensajeLabel.setText("¡Felicidades! Has acertado el color.");
            resultadoLabel.setText("Resultado: ¡Has ganado!");
        } else if (apuesta.equals("par") && Integer.parseInt(numeroGirada) % 2 == 0) {
            mensajeLabel.setText("¡Felicidades! Has acertado al apostar por un número par.");
            resultadoLabel.setText("Resultado: ¡Has ganado!");
        } else if (apuesta.equals("impar") && Integer.parseInt(numeroGirada) % 2 != 0) {
            mensajeLabel.setText("¡Felicidades! Has acertado al apostar por un número impar.");
            resultadoLabel.setText("Resultado: ¡Has ganado!");
        } else {
            mensajeLabel.setText("Lo siento, has perdido.");
            resultadoLabel.setText("Resultado: ¡Perdiste!");
        }
    }

    // Método principal para ejecutar la interfaz gráfica
    public static void main(String[] args) {
        new RuletaCasinoUI();
    }
}
