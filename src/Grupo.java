import java.util.ArrayList;
import java.util.Iterator;

public class Grupo {
    private final ArrayList<Jugador> jugadores;
    private Iterator<Jugador> iterador;
    private Jugador jugadorActual;
    private Jugador ganadorRonda;
    private Jugador ganadorJuego;

    public Grupo() {
        jugadores = new ArrayList<>();
        jugadorActual = null;
        ganadorRonda = null;
        ganadorJuego = null;
    }

    public void cambiarTurno() {
        if (jugadores.isEmpty()) {
            return;
        }

        if (jugadorActual == null || !iterador.hasNext()) {
            iterador = jugadores.iterator();
        }

        jugadorActual = iterador.next();
    }

    public void agregarJugador(Jugador jugador) {
        jugadores.add(jugador);
    }

    public void mostrarPuntajes() {
        jugadores.forEach(Jugador::mostrarPuntaje);
    }

    public int[] obtenerPuntosJugadores() {
        int[] puntos = new int[jugadores.size()];
        int i = 0;
        for (Jugador jugador : jugadores) {
            puntos[i++] = jugador.getPuntaje();
        }
        return puntos;
    }

    public Jugador obtenerJugadorMayorPuntaje() {
        int puntajeMayor = Integer.MIN_VALUE;
        Jugador jugadorMayor = null;
        for (Jugador jugador : jugadores) {
            if (jugador.getPuntaje() >= puntajeMayor) {
                jugadorMayor = jugador;
                puntajeMayor = jugador.getPuntaje();
            }
        }
        return jugadorMayor;
    }

    public Jugador getJugadorActual() {
        return jugadorActual;
    }

    public Jugador getGanadorRonda() {
        return ganadorRonda;
    }

    public void setGanadorRonda(Jugador ganadorRonda) {
        this.ganadorRonda = ganadorRonda;
    }

    public Jugador getGanadorJuego() {
        return ganadorJuego;
    }

    public void setGanadorJuego(Jugador ganadorJuego) {
        this.ganadorJuego = ganadorJuego;
    }
}