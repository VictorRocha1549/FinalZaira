package pruebasisw;

public class Cuenta {
    private int numeroCuenta;
    private int numeroCliente;
    private double saldo;

    public Cuenta(int numeroCuenta, int numeroCliente, double saldoInicial) {
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

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double cantidad) {
        if (cantidad > 0) {
            saldo += cantidad;
        }
    }

    public boolean retirar(double cantidad) {
        if (cantidad > 0 && cantidad <= saldo) {
            saldo -= cantidad;
            return true;
        }
        return false;
    }
}
