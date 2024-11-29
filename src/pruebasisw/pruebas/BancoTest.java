package pruebasisw.pruebas;
import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import pruebasisw.Banco;
import pruebasisw.Cliente;
import pruebasisw.Cuenta;

public class BancoTest {
    @Test
    public void testRegistrarCliente() {
        // Configuración del escenario
        Banco banco = new Banco();
        Cliente cliente = new Cliente(1, "Juan Pérez", "12345");
        Cuenta cuenta = new Cuenta(101, 1, new BigDecimal("5000.0"));

        // Ejecución del método
        banco.registrarCliente(cliente, cuenta);
    }

    @Test
    public void testGenerarNuevoIdCliente() {
        // Configuración del escenario
        Banco banco = new Banco();

        // Ejecución del método
        int primerId = banco.generarNuevoIdCliente();
        int segundoId = banco.generarNuevoIdCliente();

        // Verificaciones
        Assert.assertEquals("El primer ID debería ser 1", 1, primerId);
        Assert.assertEquals("El segundo ID debería ser 2", 2, segundoId);

        // Verificar que el siguiente ID sea único e incremental
        int tercerId = banco.generarNuevoIdCliente();
        Assert.assertEquals("El tercer ID debería ser 3", 3, tercerId);
    }

    @Test
    public void testGenerarNuevoIdCuenta() {
        // Configuración del escenario
        Banco banco = new Banco();

        // Ejecución del método
        int primerIdCuenta = banco.generarNuevoIdCuenta();
        int segundoIdCuenta = banco.generarNuevoIdCuenta();

        // Verificaciones
        Assert.assertEquals("El primer ID de cuenta debería ser 1", 1, primerIdCuenta);
        Assert.assertEquals("El segundo ID de cuenta debería ser 2", 2, segundoIdCuenta);

        // Verificar que el siguiente ID de cuenta sea único e incremental
        int tercerIdCuenta = banco.generarNuevoIdCuenta();
        Assert.assertEquals("El tercer ID de cuenta debería ser 3", 3, tercerIdCuenta);
    }

    @Test
    public void testEliminarCliente() {
        // Configuración del escenario
        Banco banco = new Banco();
        Cliente cliente = new Cliente(1, "Juan Perez", "password123");
        Cuenta cuenta = new Cuenta(1, 1, new BigDecimal("1000.0"));
        banco.registrarCliente(cliente, cuenta);

        // Caso 1: Cliente eliminado exitosamente con contraseña correcta
        boolean resultadoEliminacion = banco.eliminarCliente(1, "password123");
        Assert.assertTrue("El cliente debería eliminarse correctamente", resultadoEliminacion);

        // Caso 2: No se elimina porque la contraseña es incorrecta
        boolean resultadoContraseñaIncorrecta = banco.eliminarCliente(1, "wrongpassword");
        Assert.assertFalse("El cliente no debería eliminarse con una contraseña incorrecta", resultadoContraseñaIncorrecta);

        // Caso 3: No se elimina porque el cliente no existe
        boolean resultadoClienteNoExistente = banco.eliminarCliente(99, "password123");
        Assert.assertFalse("No se debería eliminar un cliente que no existe", resultadoClienteNoExistente);
    }
}

    
