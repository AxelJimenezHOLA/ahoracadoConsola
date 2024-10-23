import java.util.ArrayList;
import java.util.Random;

public class BancoFrases {
    ArrayList<String> frases;

    public BancoFrases() {
        frases = new ArrayList<>();
        agregarFrases();
    }

    private void agregarFrases() {
        frases.add("LA MESA TIENE JUMPERS ENCIMA");
        frases.add("EL LIBRO ESTÁ SOBRE LA SILLA");
        frases.add("LA COMPUTADORA ESTÁ EN EL ESCRITORIO");
        frases.add("EL TELÉFONO SUENA EN LA HABITACIÓN");
        frases.add("LA LÁMPARA ILUMINA EL CUARTO");
        frases.add("LA BOTELLA ESTÁ EN LA MESA");
        frases.add("EL RELOJ CUELGA EN LA PARED");
        frases.add("LA VENTANA ESTÁ ABIERTA");
        frases.add("LA PUERTA SE CIERRA LENTAMENTE");
        frases.add("LA TELEVISIÓN ESTÁ ENCENDIDA");
        frases.add("BRILLAN LAS ESTRELLAS ESTA NOCHE");
        frases.add("DE REPENTE, SE ESCUCHÓ UN GRITO");
        frases.add("EL CAFÉ, AÚN HUMEANTE, SE DERRAMÓ");
        frases.add("SIN AVISO, LA PUERTA SE ABRIÓ");
        frases.add("SU RISA RESONÓ EN EL SILENCIO");
        frases.add("CAÍAN LAS HOJAS COMO SI FUERA OTOÑO");
        frases.add("EN EL AIRE, UN AROMA DULCE SE DESLIZABA");
        frases.add("NADIE SABÍA POR QUÉ ESTABA VACÍO");
        frases.add("A LO LEJOS, UNA LUZ PARPADEABA");
        frases.add("UNA PALABRA, Y TODO CAMBIÓ");
        frases.add("SÓLO QUEDABAN CENIZAS");
        frases.add("VOLVÍO, PERO YA ERA TARDE");
        frases.add("A VECES, LA VERDAD DUELE MÁS");
        frases.add("AL DESPERTAR, TODO HABÍA DESAPARECIDO");
        frases.add("LA TORMENTA SE ACERCABA LENTAMENTE");
    }

    public String darFraseAleatoria() {
        Random rng = new Random(System.currentTimeMillis());
        int indiceAleatorio = rng.nextInt(frases.size());
        return frases.get(indiceAleatorio);
    }
}