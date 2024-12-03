import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Buscaminas {
    private JFrame frame;
    private JPanel panelTablero;
    private JTextArea textArea;
    private Tablero tablero;

    public Buscaminas() {
        tablero = new Tablero();
        inicializarGUI();
    }

    private void inicializarGUI() {
        frame = new JFrame("Buscaminas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Crear el panel para el tablero
        panelTablero = new JPanel();
        panelTablero.setLayout(new GridLayout(tablero.getFilas(), tablero.getColumnas()));
        frame.add(panelTablero, BorderLayout.CENTER);

        // Crear los botones para las casillas
        for (int i = 0; i < tablero.getFilas(); i++) {
            for (int j = 0; j < tablero.getColumnas(); j++) {
                JButton boton = new JButton();
                boton.setPreferredSize(new Dimension(40, 40));
                boton.setFont(new Font("Arial", Font.PLAIN, 20));
                boton.setFocusPainted(false);
                boton.setBackground(Color.LIGHT_GRAY);

                int fila = i;
                int columna = j;

                boton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        manejarAccion(fila, columna, boton);
                    }
                });

                panelTablero.add(boton);
            }
        }

        // Crear el área de texto para los mensajes
        textArea = new JTextArea(3, 30);
        textArea.setEditable(false);
        frame.add(new JScrollPane(textArea), BorderLayout.SOUTH);

        // Finalizar configuración de la ventana
        frame.pack();
        frame.setLocationRelativeTo(null); // Centrar la ventana
        frame.setVisible(true);
    }

    private void manejarAccion(int fila, int columna, JButton boton) {
        Casilla casilla = tablero.getCasilla(fila, columna);

        if (casilla.estaDescubierta()) {
            return; // Si la casilla ya está descubierta, no hacemos nada
        }

        if (casilla.tieneMina()) {
            boton.setText("*");
            boton.setBackground(Color.RED);
            textArea.setText("¡Perdiste! Has descubierto una mina.");
            mostrarTableroCompleto();
        } else {
            casilla.descubrir();
            boton.setText(String.valueOf(casilla.getNumeroMinasAdyacentes()));
            boton.setEnabled(false);

            if (tablero.verificarVictoria()) {
                textArea.setText("¡Ganaste! Has descubierto todas las casillas sin minas.");
            }
        }
    }

    private void mostrarTableroCompleto() {
        // Mostrar el tablero completo (con minas)
        for (int i = 0; i < tablero.getFilas(); i++) {
            for (int j = 0; j < tablero.getColumnas(); j++) {
                Casilla casilla = tablero.getCasilla(i, j);
                JButton boton = (JButton) panelTablero.getComponent(i * tablero.getColumnas() + j); // Obtener el botón
                                                                                                    // correcto
                if (casilla.tieneMina()) {
                    boton.setText("*");
                    boton.setBackground(Color.RED);
                } else {
                    boton.setText(String.valueOf(casilla.getNumeroMinasAdyacentes()));
                    boton.setBackground(Color.GRAY);
                }
                boton.setEnabled(false); // Deshabilitar el botón al final
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Buscaminas(); // Crear una instancia del juego
        });
    }
}
