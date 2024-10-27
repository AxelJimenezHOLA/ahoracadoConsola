public class Jugador {
    private final String nombre;
    private int puntaje;

    public Jugador(String nombre) {
        this.nombre = nombre;
        puntaje = 0;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void agregarPuntos(int puntos) {
        puntaje += puntos;
    }

    public void mostrarPuntaje() {
        System.out.printf("Puntos de %s: %d%n", nombre, puntaje);
    }

    @Override
    public String toString() {
        return nombre;
    }
}