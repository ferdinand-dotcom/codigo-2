import java.util.Random;

public class Tablero {
    private static final int FILAS = 10;
    private static final int COLUMNAS = 10;
    private static final int MINAS = 10;
    private Casilla[][] casillas;

    public Tablero() {
        casillas = new Casilla[FILAS][COLUMNAS];
        inicializarTablero();
    }

    private void inicializarTablero() {
        // Inicializamos todas las casillas
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                casillas[i][j] = new Casilla();
            }
        }
        colocarMinas();
        calcularMinasAdyacentes();
    }

    private void colocarMinas() {
        Random random = new Random();
        int minasColocadas = 0;

        while (minasColocadas < MINAS) {
            int fila = random.nextInt(FILAS);
            int columna = random.nextInt(COLUMNAS);

            if (!casillas[fila][columna].tieneMina()) {
                casillas[fila][columna].colocarMina();
                minasColocadas++;
            }
        }
    }

    private void calcularMinasAdyacentes() {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                if (casillas[i][j].tieneMina()) {
                    for (int filaAdyacente = -1; filaAdyacente <= 1; filaAdyacente++) {
                        for (int colAdyacente = -1; colAdyacente <= 1; colAdyacente++) {
                            int fila = i + filaAdyacente;
                            int columna = j + colAdyacente;

                            if (fila >= 0 && fila < FILAS && columna >= 0 && columna < COLUMNAS) {
                                casillas[fila][columna].setNumeroMinasAdyacentes(
                                        casillas[fila][columna].getNumeroMinasAdyacentes() + 1);
                            }
                        }
                    }
                }
            }
        }
    }

    public Casilla getCasilla(int fila, int columna) {
        return casillas[fila][columna];
    }

    public boolean verificarVictoria() {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                if (!casillas[i][j].tieneMina() && !casillas[i][j].estaDescubierta()) {
                    return false; // Si hay alguna casilla no descubierta que no sea mina
                }
            }
        }
        return true;
    }

    public void reiniciarTablero() {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                casillas[i][j].resetear();
            }
        }
        colocarMinas();
        calcularMinasAdyacentes();
    }

    public int getFilas() {
        return FILAS;
    }

    public int getColumnas() {
        return COLUMNAS;
    }
}
