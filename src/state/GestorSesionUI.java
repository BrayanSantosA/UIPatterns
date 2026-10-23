package state;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;


public class GestorSesionUI {

    private final Map<String, String> campos = new LinkedHashMap<>();
    private Map<String, String> borrador = null;
    private boolean sesionActiva = false;

    public GestorSesionUI(String... nombresCampos) {
        for (String nombre : nombresCampos) {
            campos.put(nombre, "");
        }
    }



    public void completarCampo(String nombre, String valor) {
        if (campos.containsKey(nombre)) {
            campos.put(nombre, valor == null ? "" : valor);
        }
    }

    private long camposListos() {
        return campos.values().stream().filter(v -> !v.isBlank()).count();
    }


    public int calcularProgreso() {
        if (campos.isEmpty()) {
            return 0;
        }
        return (int) Math.round(100.0 * camposListos() / campos.size());
    }

    public void mostrarProgreso() {
        int pct = calcularProgreso();
        if (pct == 100) {
            System.out.println("[Progreso: 100%] ¡Formulario completo!");
            return;
        }
        String faltantes = campos.entrySet().stream()
                .filter(e -> e.getValue().isBlank())
                .map(Map.Entry::getKey)
                .collect(Collectors.joining(", "));
        System.out.println("[Progreso: " + pct + "%] Campos listos: " + camposListos()
                + " de " + campos.size() + " (Falta: " + faltantes + ")");
    }


    public boolean isSesionActiva() {
        return sesionActiva;
    }

    public boolean tieneBorrador() {
        return borrador != null;
    }


    public boolean guardarReserva() {
        if (!sesionActiva) {
            borrador = new LinkedHashMap<>(campos);
            System.out.println("[Lazy Registration] Avance guardado en borrador temporal. "
                    + "Por favor cree su cuenta para finalizar.");
            return false;
        }
        borrador = null;
        mostrarToast("Reserva guardada exitosamente.");
        return true;
    }


    public void registroRapido() {
        sesionActiva = true;
        if (borrador != null) {
            campos.putAll(borrador);
        }
        System.out.println("Usuario completa registro rápido -> Sesión activa = true");
    }



    public void cancelarReserva() {
        campos.replaceAll((k, v) -> "");
        borrador = null;
        mostrarToast("Reserva cancelada.");
    }


    public void cerrarSesion() {
        sesionActiva = false;
        mostrarToast("Sesión cerrada.");
    }


    public void mostrarToast(String mensaje) {
        System.out.println("[Toast de Estado] " + mensaje);
    }
}
