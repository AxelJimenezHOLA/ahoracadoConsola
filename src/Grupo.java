import java.util.ArrayList;

public class Grupo {
    private final ArrayList<Jugador> jugadores;
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

        if (jugadorActual == null) {
            jugadorActual = jugadores.getFirst();
            return;
        }

        if (jugadorActual != jugadores.getLast()) {
            jugadorActual = jugadores.get(jugadores.indexOf(jugadorActual)+1);
        } else {
            jugadorActual = jugadores.getFirst();
        }

    }

    public void agregarJugador(Jugador jugador) {
        jugadores.add(jugador);
    }

    public void mostrarPuntajes() {
        jugadores.forEach(Jugador::mostrarPuntaje);
    }

    public int[] obtenerPuntosJugadores() {
        int[] puntos = new int[jugadores.size()];
        for (int i = 0; i < puntos.length; i++) {
            puntos[i] = jugadores.get(i).getPuntaje();
        }
        return puntos;
    }

    public Jugador obtenerJugadorMayorPuntaje() {
        int puntajeMayor = Integer.MIN_VALUE;
        Jugador jugadorMayor = null;
        for (Jugador jugador : jugadores) {
            if (jugador.getPuntaje() > puntajeMayor) {
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