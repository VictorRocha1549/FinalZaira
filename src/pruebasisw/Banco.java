package pruebasisw;

import java.util.HashMap;


public class Banco {
    private HashMap<Integer, Cliente> clientes;
    private HashMap<Integer, Cuenta> cuentas;
    private int idClienteCounter = 1; // Contador para IDs de clientes
    private int idCuentaCounter = 1;  // Contador para IDs de cuentas

    public Banco() {
        clientes = new HashMap<>();
        cuentas = new HashMap<>();
    }

    // Método para registrar un cliente y su cuenta
    public void registrarCliente(Cliente cliente, Cuenta cuenta) {
        clientes.put(cliente.getNumeroCliente(), cliente);
        cuentas.put(cuenta.getNumeroCuenta(), cuenta);
    }

    // Método para autenticar cliente por su número de cliente y contraseña
    public Cliente autenticar(int numeroCliente, String contrasena) {
        Cliente cliente = clientes.get(numeroCliente);
        if (cliente != null && cliente.verificarContrasena(contrasena)) {
            return cliente;
        }
        return null;
    }

    public boolean eliminarCliente(int idCliente, String contrasena) {
        Cliente cliente = obtenerCliente(idCliente);
        if (cliente != null && cliente.getContrasena().equals(contrasena)) {
            // Lógica para eliminar el cliente
            return true;  // Indica que la eliminación fue exitosa
        }
        return false;  // Indica que la eliminación falló
    }
    
    

    // Método para obtener la cuenta de un cliente específico
    public Cuenta obtenerCuenta(int numeroCliente) {
        for (Cuenta cuenta : cuentas.values()) {
            if (cuenta.getNumeroCliente() == numeroCliente) {
                return cuenta;
            }
        }
        return null;
    }

    // Genera un nuevo ID único para un cliente
    public int generarNuevoIdCliente() {
        return idClienteCounter++;
    }

    // Genera un nuevo ID único para una cuenta
    public int generarNuevoIdCuenta() {
        return idCuentaCounter++;
    }
}
