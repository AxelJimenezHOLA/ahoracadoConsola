import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

public class JuegoAhorcado {
    private final Grupo grupoJugadores;
    private final BancoFrases bancoFrases;
    private String fraseElegida;
    private int metaPuntos;
    private final HashSet<Character> letrasDescubiertas;

    public JuegoAhorcado() {
        grupoJugadores = new Grupo();
        bancoFrases = new BancoFrases();
        letrasDescubiertas = new HashSet<>();
    }

    public void iniciarJuego() {
        int cantidadJugadores = introducirEntero("Ingrese el número de jugadores: ",2,4);
        crearYNombrarJugadores(cantidadJugadores);
        metaPuntos = introducirEntero("Ingrese la meta de puntos: ",1, -1);

         // Elige al primer jugador del grupo como el jugador actual
        while (!juegoTerminado()) {
            inicializarRonda();
            while (!rondaTerminada()) {
                grupoJugadores.cambiarTurno();
                Jugador jugadorActual = grupoJugadores.getJugadorActual();
                System.out.println();
                mostrarPuntajes();
                System.out.println();
                mostrarLetrasDescubiertas();
                System.out.println();
                mostrarFraseDescubierta();
                System.out.println();
                darPuntos(jugadorActual, pedirLetra(jugadorActual));
            }
            System.out.print("La frase era: ");
            mostrarFraseDescubierta();
            grupoJugadores.setGanadorRonda(grupoJugadores.getJugadorActual());
            darPuntosGanadorRonda();
        }
        mostrarPuntajes();
        declararGanadorJuego();
    }

    public void crearYNombrarJugadores(int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            Jugador jugador = new Jugador(obtenerNombreJugador());
            grupoJugadores.agregarJugador(jugador);
        }
    }

    public char pedirLetra(Jugador jugador) {
        Scanner entrada = new Scanner(System.in);
        char letra;
        do {
            System.out.printf("Jugador %s, ingrese una letra: ", jugador);
            letra = entrada.next().toUpperCase().charAt(0);
            if (!Character.isLetter(letra)) {
                System.out.println("Error: elija una letra y no otro carácter.");
            }
        } while (!Character.isLetter(letra));
        return letra;
    }

    public void darPuntos(Jugador jugador, char letra) {
        int puntosObtenidos;
        if (letrasDescubiertas.contains(letra)) {
            puntosObtenidos = -3;
            System.out.printf("El jugador %s ha perdido 3 puntos por elegir una letra ya existente...%n", jugador);
            jugador.agregarPuntos(puntosObtenidos);
        } else if (fraseContieneLetra(letra) && !letrasDescubiertas.contains(letra)) {
            letrasDescubiertas.add(letra);
            puntosObtenidos = 3* contarRepeticiones(letra);
            System.out.printf("¡El jugador %s ha ganado %d puntos por adivinar una letra!%n", jugador, puntosObtenidos);
            jugador.agregarPuntos(puntosObtenidos);
        } else {
            puntosObtenidos = -1;
            letrasDescubiertas.add(letra);
            System.out.printf("El jugador %s ha perdido 1 punto por no adivinar una letra...%n", jugador);
            jugador.agregarPuntos(puntosObtenidos);
        }
    }

    public void darPuntosGanadorRonda() {
        System.out.printf("¡El ganador de la ronda es %s! Se te sumó 5 puntos.%n", grupoJugadores.getGanadorRonda());
        grupoJugadores.getGanadorRonda().agregarPuntos(5);
    }

    public void mostrarFraseDescubierta() {
        for (char c : fraseElegida.toCharArray()) {
            if (letrasDescubiertas.contains(c) && Character.isLetter(c)) {
                System.out.print(c);
            } else if (!Character.isLetter(c)) {
                System.out.print(c);
            } else {
                System.out.print('_');
            }
        }
        System.out.println();
    }

    public void mostrarPuntajes() {
        grupoJugadores.mostrarPuntajes();
    }

    public void mostrarLetrasDescubiertas() {
        System.out.println("Letras jugadas:");
        if (!letrasDescubiertas.isEmpty()) {
            for (Character c : letrasDescubiertas) {
                if (Character.isLetter(c)) {
                    System.out.printf("%s ", c);
                }
            }
        } else {
            System.out.println("ninguna aún...");
        }
    }

    public void inicializarRonda() {
        fraseElegida = bancoFrases.darFraseAleatoria();
        letrasDescubiertas.clear();
    }

    public void declararGanadorJuego() {
        grupoJugadores.setGanadorJuego(grupoJugadores.obtenerJugadorMayorPuntaje());
        System.out.printf("¡El ganador del juego es %s!%n", grupoJugadores.getGanadorJuego());
    }

    public boolean rondaTerminada() {
        for (char c : fraseElegida.toCharArray()) {
            if (!letrasDescubiertas.contains(c) && Character.isLetter(c)) return false;
        }
        return true;
    }

    public boolean juegoTerminado() {
        int[] puntosJugadores = grupoJugadores.obtenerPuntosJugadores();
        for (int i : puntosJugadores) {
            if (i >= metaPuntos) return true;
        }
        return false;
    }

    private int introducirEntero(String mensaje, int minimo, int maximo) {
        Scanner entrada = new Scanner(System.in);
        int valor;
        do {
            System.out.print(mensaje);
            try {
                valor = entrada.nextInt();
                if (valor < minimo || (maximo > 0 && valor > maximo)) {
                    if (maximo != -1) {
                        System.out.printf("Error: ingrese un valor entre %d y %d.\n", minimo, maximo);
                    } else {
                        System.out.printf("Error: ingrese un valor mayor a %d.\n", minimo-1);
                    }
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Error: ingrese solo números.");
                entrada.nextLine();
            }
        } while (true);
        return valor;
    }

    private String obtenerNombreJugador() {
        Scanner entrada = new Scanner(System.in);
        String nombre;
        do {
            System.out.print("Ingrese el nombre: ");
            nombre = entrada.nextLine().trim();
            if (nombre.isEmpty()) System.out.println("Error: ingrese aunque sea un carácter.");
        } while (nombre.isEmpty());
        return nombre;
    }

    private int contarRepeticiones(char letra) {
        int contador = 0;
        for (char c : fraseElegida.toCharArray()) {
            if (c == letra) contador++;
        }
        return contador;
    }

    private boolean fraseContieneLetra(char letra) {
        return fraseElegida.indexOf(letra) >= 0;
    }
}