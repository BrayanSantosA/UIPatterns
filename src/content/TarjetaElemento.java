package content;

public class TarjetaElemento {

    private final String nombre;
    private final String estado;
    private final String ip;
    private final int puerto;
    private final String ram;
    private boolean verAvanzado = false;

    public TarjetaElemento(String nombre, String estado, String ip, int puerto, String ram) {
        this.nombre = nombre;
        this.estado = estado;
        this.ip = ip;
        this.puerto = puerto;
        this.ram = ram;
    }

    public void setVerAvanzado(boolean verAvanzado) {
        this.verAvanzado = verAvanzado;
    }

    public boolean isVerAvanzado() {
        return verAvanzado;
    }

    public void alternarDetalles() {
        verAvanzado = !verAvanzado;
    }

    public String renderizar() {
        if (!verAvanzado) {
            return "[Vista Resumen] " + nombre + " · Estado: " + estado;
        }
        return "[Acordeón Desplegado] IP: " + ip + " | Puerto: " + puerto + " | RAM: " + ram;
    }

    public void mostrar() {
        System.out.println(renderizar());
    }
}
