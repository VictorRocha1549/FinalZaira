package pruebasisw;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

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

    // Método para autenticar a un cliente por número de cliente y contraseña
    public Cliente autenticar(int numeroCliente, String contrasena) {
        for (Map.Entry<Integer, Cliente> entry : clientes.entrySet()) {
            Cliente cliente = entry.getValue();
            if (cliente.getNumeroCliente() == numeroCliente && cliente.verificarContrasena(contrasena)) {
                return cliente; // Si se encuentra el cliente, lo devuelves
            }
        }
        return null; // Si no se encuentra, retornas null
    }

    // Método de eliminar cliente: elimina cliente y sus cuentas asociadas
    public boolean eliminarCliente(int idCliente, String contrasena) {
        Cliente cliente = clientes.get(idCliente);
        if (cliente != null && cliente.verificarContrasena(contrasena)) {
            // Elimina la cuenta asociada al cliente
            cuentas.values().removeIf(cuenta -> cuenta.getNumeroCliente() == idCliente);
            // Elimina el cliente
            clientes.remove(idCliente);
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
        return null;  // Si no encuentra la cuenta, retorna null
    }

    // Genera un nuevo ID único para un cliente
    public int generarNuevoIdCliente() {
        return idClienteCounter++;
    }

    // Genera un nuevo ID único para una cuenta
    public int generarNuevoIdCuenta() {
        return idCuentaCounter++;
    }

    public boolean realizarDeposito(int numeroCliente, double monto) {
        Cliente cliente = clientes.get(numeroCliente); // Obtener el cliente
        if (cliente != null) {
            Cuenta cuenta = obtenerCuenta(numeroCliente); // Obtener la cuenta asociada
            if (cuenta != null && monto > 0) {
                cuenta.depositar(monto); // Llamar al método depositar de la cuenta
                return true; // Depósito exitoso
            }
        }
        return false; // Si no se encuentra el cliente o la cuenta, o el monto es inválido
    }

    // Método para realizar un retiro
        public boolean realizarRetiro(int numeroCliente, double monto) {
            Cliente cliente = clientes.get(numeroCliente);
                if (cliente != null) {
            Cuenta cuenta = obtenerCuenta(numeroCliente);
                if (cuenta != null && monto > 0) {
            BigDecimal montoBD = BigDecimal.valueOf(monto);
            if (cuenta.getSaldo().compareTo(montoBD) >= 0) {
                cuenta.retirar(montoBD); 
                return true; 
            }
        }
    }
    return false; // Si no se encuentra la cuenta o el saldo es insuficiente
}

}
