package pruebasisw;

import java.math.BigDecimal;

public class Cuenta {
    private int numeroCuenta;
    private int numeroCliente;
    private BigDecimal saldo;

    public Cuenta(int numeroCuenta, int numeroCliente, BigDecimal saldoInicial) {
        this.numeroCuenta = numeroCuenta;
        this.numeroCliente = numeroCliente;
        this.saldo = saldoInicial;
    }

    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    public int getNumeroCliente() {
        return numeroCliente;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void depositar(BigDecimal cantidad) {
        if (cantidad.compareTo(BigDecimal.ZERO) > 0) {
            saldo = saldo.add(cantidad);
        }
    }

    public boolean retirar(BigDecimal cantidad) {
        if (cantidad.compareTo(BigDecimal.ZERO) > 0 && cantidad.compareTo(saldo) <= 0) {
            saldo = saldo.subtract(cantidad);
            return true;
        }
        return false;
    }
}