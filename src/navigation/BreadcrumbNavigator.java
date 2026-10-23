package navigation;

import java.util.ArrayList;
import java.util.List;


public class BreadcrumbNavigator {

    private static final String RAIZ = "Inicio";
    private static final String SEPARADOR = " > ";

    private final List<String> ruta = new ArrayList<>();

    public BreadcrumbNavigator() {
        ruta.add(RAIZ);
    }


    public void cambiarPestana(String pestana) {
        while (ruta.size() > 1) {
            ruta.remove(ruta.size() - 1);
        }
        ruta.add(pestana);
    }


    public void entrarSubcategoria(String subcategoria) {
        ruta.add(subcategoria);
    }


    public void volver() {
        if (ruta.size() > 1) {
            ruta.remove(ruta.size() - 1);
        }
    }


    public void irAInicio() {
        while (ruta.size() > 1) {
            ruta.remove(ruta.size() - 1);
        }
    }

    public String getRuta() {
        return String.join(SEPARADOR, ruta);
    }

    public void mostrarRuta() {
        System.out.println("Ruta actual: " + getRuta());
    }
}
