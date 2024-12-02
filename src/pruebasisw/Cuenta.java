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

// Método para depositar una cantidad en la cuenta
public void depositar(double monto) {
    if (monto > 0) { // Verificamos que el monto sea mayor que cero
        saldo = saldo.add(BigDecimal.valueOf(monto)); // Suma la cantidad al saldo actual
    }
}



    // Método para retirar una cantidad de la cuenta
    public boolean retirar(BigDecimal cantidad) {
        if (cantidad.compareTo(BigDecimal.ZERO) > 0 && cantidad.compareTo(saldo) <= 0) {
            saldo = saldo.subtract(cantidad); // Resta la cantidad al saldo actual
            return true; // Retiro exitoso
        }
        return false; // Retiro fallido si no hay suficiente saldo o la cantidad es inválida
    }
}
