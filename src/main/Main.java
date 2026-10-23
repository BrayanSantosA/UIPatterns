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


        System.out.println(" Caso 1: patrón de navegación ");
        navegador.mostrarRuta();
        navegador.cambiarPestana("Catálogo");
        navegador.mostrarRuta();
        navegador.entrarSubcategoria("Servidor Principal");
        navegador.mostrarRuta();
        navegador.cambiarPestana("Reservas");
        navegador.entrarSubcategoria("Laboratorio de Redes");
        navegador.mostrarRuta();


        System.out.println("\n Caso 2: contenido y estructura");
        tarjeta.setVerAvanzado(false);
        tarjeta.mostrar();
        System.out.println("[Usuario solicita ver detalles avanzados...]");
        tarjeta.setVerAvanzado(true);
        tarjeta.mostrar();


        System.out.println("\nCaso 3: Patron de entrada de datos");
        String entradaTel = "+57 (310) 999-8877";
        System.out.println("Entrada del usuario: \"" + entradaTel + "\"");
        System.out.println("Teléfono normalizado sin error: \""
                + validador.normalizarTelefono(entradaTel) + "\"");
        String entradaFecha = "23 / 10 / 2026";
        System.out.println("Entrada del usuario: \"" + entradaFecha + "\"");
        System.out.println("Fecha normalizada sin error: \""
                + validador.normalizarFecha(entradaFecha) + "\"");


        System.out.println("\n Caso 4: Patron de Sesión y Progreso");
        gestor.mostrarProgreso();
        gestor.completarCampo("Teléfono", validador.normalizarTelefono(entradaTel));
        gestor.mostrarProgreso();
        gestor.completarCampo("Laboratorio", "Laboratorio de Redes");
        gestor.mostrarProgreso();
        String correo = "estudiante@udistrital.edu.co";
        System.out.println("Usuario ingresa correo: \"" + correo + "\"");
        gestor.completarCampo("Correo de contacto", validador.limpiarTexto(correo));
        gestor.mostrarProgreso();

   
        System.out.println("\n Caso 5: Patron de Sesión y Progreso con borrador");
        System.out.println("Usuario intenta guardar sin haber iniciado sesión (usuarioActivo = false)...");
        gestor.guardarReserva();
        gestor.registroRapido();
        System.out.println("Usuario intenta guardar con sesión activa (usuarioActivo = true)...");
        gestor.guardarReserva();


        System.out.println("\n Salidas directas");
        System.out.println("Usuario cancela la reserva (un solo paso):");
        gestor.cancelarReserva();
        System.out.println("Usuario cierra sesión (un solo paso):");
        gestor.cerrarSesion();
    }
}
