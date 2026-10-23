package input;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ValidadorForgiving {

    private static final Pattern NO_DIGITOS = Pattern.compile("\\D");
    private static final Pattern FECHA_DMA =
            Pattern.compile("^(\\d{1,2})[\\s/.\\-]+(\\d{1,2})[\\s/.\\-]+(\\d{4})$");
    private static final Pattern FECHA_AMD =
            Pattern.compile("^(\\d{4})[\\s/.\\-]+(\\d{1,2})[\\s/.\\-]+(\\d{1,2})$");

 
    public String normalizarTelefono(String entrada) {
        if (entrada == null) {
            return "";
        }
        String digitos = NO_DIGITOS.matcher(entrada).replaceAll("");
        if (digitos.length() == 12 && digitos.startsWith("57")) {
            digitos = digitos.substring(2);
        }
        return digitos;
    }

    public String normalizarFecha(String entrada) {
        if (entrada == null) {
            return "";
        }
        String limpia = entrada.trim();

        Matcher dma = FECHA_DMA.matcher(limpia);
        if (dma.matches()) {
            return formatear(dma.group(3), dma.group(2), dma.group(1));
        }
        Matcher amd = FECHA_AMD.matcher(limpia);
        if (amd.matches()) {
            return formatear(amd.group(1), amd.group(2), amd.group(3));
        }
        String digitos = NO_DIGITOS.matcher(limpia).replaceAll("");
        if (digitos.length() == 8) {
            return formatear(digitos.substring(4), digitos.substring(2, 4), digitos.substring(0, 2));
        }
        return limpia;
    }


    public String limpiarTexto(String entrada) {
        return entrada == null ? "" : entrada.trim();
    }

    private String formatear(String anio, String mes, String dia) {
        return String.format("%s-%02d-%02d", anio, Integer.parseInt(mes), Integer.parseInt(dia));
    }
}
