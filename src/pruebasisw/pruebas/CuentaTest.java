package pruebasisw.pruebas;

import org.junit.Assert;
import org.junit.Test;
import pruebasisw.Cuenta;

public class CuentaTest {
    @Test
    public void testDepositarValido() {
        // Configuración del escenario
        Cuenta cuenta = new Cuenta(1, 1, 500.0); // Saldo inicial: 500.0

        // Acción
        cuenta.depositar(200.0); // Depósito de 200

        // Verificación
        Assert.assertEquals("El saldo debería ser 700.0 después del depósito", 700.0, cuenta.getSaldo(), 0.001);
    }

    @Test
    public void testDepositarCero() {
        // Configuración del escenario
        Cuenta cuenta = new Cuenta(1, 1, 500.0); // Saldo inicial: 500.0

        // Acción
        cuenta.depositar(0.0); // Depósito de 0

        // Verificación
        Assert.assertEquals("El saldo no debería cambiar con un depósito de 0", 500.0, cuenta.getSaldo(), 0.001);
    }

    @Test
    public void testDepositarNegativo() {
        // Configuración del escenario
        Cuenta cuenta = new Cuenta(1, 1, 500.0); // Saldo inicial: 500.0

        // Acción
        cuenta.depositar(-100.0); // Depósito de -100

        // Verificación
        Assert.assertEquals("El saldo no debería cambiar con un depósito negativo", 500.0, cuenta.getSaldo(), 0.001);
    }
}
