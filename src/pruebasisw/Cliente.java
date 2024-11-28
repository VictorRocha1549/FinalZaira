package pruebasisw;

public class Cliente {
    private int numeroCliente;
    private String nombre;
    private String contrasena;

    public Cliente(int numeroCliente, String nombre, String contrasena) {
        this.numeroCliente = numeroCliente;
        this.nombre = nombre;
        this.contrasena = contrasena;
    }

    public int getNumeroCliente() {
        return numeroCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean verificarContrasena(String contrasena) {
        return this.contrasena.equals(contrasena);
    }
}
