public class Casilla {
    private boolean tieneMina;
    private int numeroMinasAdyacentes;
    private boolean descubierta;
    private boolean marcada;

    // Constructor
    public Casilla() {
        this.tieneMina = false;
        this.numeroMinasAdyacentes = 0;
        this.descubierta = false;
        this.marcada = false;
    }

    public boolean tieneMina() {
        return tieneMina;
    }

    public void colocarMina() {
        this.tieneMina = true;
    }

    public int getNumeroMinasAdyacentes() {
        return numeroMinasAdyacentes;
    }

    public void setNumeroMinasAdyacentes(int numeroMinasAdyacentes) {
        this.numeroMinasAdyacentes = numeroMinasAdyacentes;
    }

    public boolean estaDescubierta() {
        return descubierta;
    }

    public void descubrir() {
        this.descubierta = true;
    }

    public boolean estaMarcada() {
        return marcada;
    }

    public void marcar() {
        this.marcada = true;
    }

    public void desmarcar() {
        this.marcada = false;
    }

    public void resetear() {
        this.tieneMina = false;
        this.numeroMinasAdyacentes = 0;
        this.descubierta = false;
        this.marcada = false;
    }
}
