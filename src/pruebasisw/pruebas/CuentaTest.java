package pruebasisw.pruebas;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import pruebasisw.Cuenta;

public class CuentaTest {
    @Test
    public void testDepositarValido() {
        // Configuración del escenario
        Cuenta cuenta = new Cuenta(1, 1, new BigDecimal("500.0")); // Saldo inicial: 500.0

        // Acción
        cuenta.depositar(new BigDecimal("200.0")); // Depósito de 200

        // Verificación
        Assert.assertEquals("El saldo debería ser 700.0 después del depósito", new BigDecimal("700.0"), cuenta.getSaldo());
    }

    @Test
    public void testDepositarCero() {
        // Configuración del escenario
        Cuenta cuenta = new Cuenta(1, 1, new BigDecimal("500.0")); // Saldo inicial: 500.0

        // Acción
        cuenta.depositar(new BigDecimal("0.0")); // Depósito de 0

        // Verificación
        Assert.assertEquals("El saldo no debería cambiar con un depósito de 0", new BigDecimal("500.0"), cuenta.getSaldo());
    }

    @Test
    public void testDepositarNegativo() {
        // Configuración del escenario
        Cuenta cuenta = new Cuenta(1, 1, new BigDecimal("500.0")); // Saldo inicial: 500.0

        // Acción
        cuenta.depositar(new BigDecimal("-100.0")); // Depósito de -100

        // Verificación
        Assert.assertEquals("El saldo no debería cambiar con un depósito negativo", new BigDecimal("500.0"), cuenta.getSaldo());
    }

    @Test
    public void testRetirarVerdadero(){
        Cuenta cuenta = new Cuenta(1, 1, new BigDecimal("500"));
        boolean resultado = cuenta.retirar(new BigDecimal("200"));
        System.out.println(cuenta);
        Assert.assertTrue(resultado);
    }

    @Test
    public void testRetirarFalso(){
        Cuenta cuenta = new Cuenta(1, 1, new BigDecimal("500"));
        boolean resultado = cuenta.retirar(new BigDecimal("700"));
        System.out.println(cuenta);
        Assert.assertFalse(resultado);
    }
}
