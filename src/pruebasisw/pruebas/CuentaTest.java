package pruebasisw.pruebas;

import org.junit.Assert;
import org.junit.Test;
import pruebasisw.Cuenta;
import java.math.BigDecimal;

public class CuentaTest {

    @Test
    public void testDepositar() {
        Cuenta cuenta = new Cuenta(1, 1, new BigDecimal("1000.0"));
        cuenta.depositar(500);
        Assert.assertEquals("El saldo después del depósito debería ser 1500", new BigDecimal("1500.0"), cuenta.getSaldo());
    }

    @Test
    public void testRetirar() {
        Cuenta cuenta = new Cuenta(1, 1, new BigDecimal("1000.0"));
        boolean resultado = cuenta.retirar(new BigDecimal("500")); 
        Assert.assertTrue("El retiro debería ser exitoso", resultado);
        Assert.assertEquals("El saldo después del retiro debería ser 500", new BigDecimal("500.0"), cuenta.getSaldo());
    }
    

    @Test
    public void testRetirarConSaldoInsuficiente() {
        Cuenta cuenta = new Cuenta(1, 1, new BigDecimal("1000.0"));
        boolean resultado = cuenta.retirar(new BigDecimal(1500));
        Assert.assertFalse("El retiro debería fallar por saldo insuficiente", resultado);
        Assert.assertEquals("El saldo no debería cambiar", new BigDecimal("1000.0"), cuenta.getSaldo());
    }

    @Test
    public void testDepositarNegativo() {
        Cuenta cuenta = new Cuenta(1, 1, new BigDecimal("1000.0"));
        cuenta.depositar(-500);
        Assert.assertEquals("El saldo no debería cambiar con un depósito negativo", new BigDecimal("1000.0"), cuenta.getSaldo());
    }

    @Test
    public void testSaldoInicial() {
        Cuenta cuenta = new Cuenta(1, 1, new BigDecimal("1000.0"));
        Assert.assertEquals("El saldo inicial debería ser 1000", new BigDecimal("1000.0"), cuenta.getSaldo());
    }
}
