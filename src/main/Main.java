package main;

import content.TarjetaElemento;
import input.ValidadorForgiving;
import navigation.BreadcrumbNavigator;
import state.GestorSesionUI;

public class Main {

    public static void main(String[] args) {
        BreadcrumbNavigator navegador = new BreadcrumbNavigator();
        TarjetaElemento tarjeta = new TarjetaElemento(
                "Servidor Principal", "Activo", "192.168.1.10", 8080, "4GB/16GB");
        ValidadorForgiving validador = new ValidadorForgiving();
        GestorSesionUI gestor = new GestorSesionUI("Teléfono", "Correo de contacto", "Laboratorio");


        System.out.println("--- CASO 1. PATRÓN DE NAVEGACIÓN (BREADCRUMBS + TABS) ---");
        navegador.mostrarRuta();
        navegador.cambiarPestana("Catálogo");
        navegador.mostrarRuta();
        navegador.entrarSubcategoria("Servidor Principal");
        navegador.mostrarRuta();
        navegador.cambiarPestana("Reservas");
        navegador.entrarSubcategoria("Laboratorio de Redes");
        navegador.mostrarRuta();


        System.out.println("\n--- CASO 2. PATRÓN DE CONTENIDO Y ESTRUCTURA (PROGRESSIVE DISCLOSURE) ---");
        tarjeta.setVerAvanzado(false);
        tarjeta.mostrar();
        System.out.println("[Usuario solicita ver detalles avanzados...]");
        tarjeta.setVerAvanzado(true);
        tarjeta.mostrar();


        System.out.println("\n--- CASO 3. PATRÓN DE ENTRADA DE DATOS (FORGIVING FORMAT) ---");
        String entradaTel = "+57 (310) 999-8877";
        System.out.println("Entrada del usuario: \"" + entradaTel + "\"");
        System.out.println("Teléfono normalizado sin error: \""
                + validador.normalizarTelefono(entradaTel) + "\"");
        String entradaFecha = "23 / 10 / 2026";
        System.out.println("Entrada del usuario: \"" + entradaFecha + "\"");
        System.out.println("Fecha normalizada sin error: \""
                + validador.normalizarFecha(entradaFecha) + "\"");


        System.out.println("\n--- CASO 4. PATRÓN DE FEEDBACK Y ESTADO (COMPLETENESS METER) ---");
        gestor.mostrarProgreso();
        gestor.completarCampo("Teléfono", validador.normalizarTelefono(entradaTel));
        gestor.mostrarProgreso();
        gestor.completarCampo("Laboratorio", "Laboratorio de Redes");
        gestor.mostrarProgreso();
        String correo = "estudiante@udistrital.edu.co";
        System.out.println("Usuario ingresa correo: \"" + correo + "\"");
        gestor.completarCampo("Correo de contacto", validador.limpiarTexto(correo));
        gestor.mostrarProgreso();

   
        System.out.println("\n--- CASO 5. LAZY REGISTRATION + TOAST ---");
        System.out.println("Usuario intenta guardar sin haber iniciado sesión (usuarioActivo = false)...");
        gestor.guardarReserva();
        gestor.registroRapido();
        System.out.println("Usuario intenta guardar con sesión activa (usuarioActivo = true)...");
        gestor.guardarReserva();


        System.out.println("\n--- EXTRA. SALIDAS DIRECTAS (CERO DARK PATTERNS) ---");
        System.out.println("Usuario cancela la reserva (un solo paso):");
        gestor.cancelarReserva();
        System.out.println("Usuario cierra sesión (un solo paso):");
        gestor.cerrarSesion();
    }
}
