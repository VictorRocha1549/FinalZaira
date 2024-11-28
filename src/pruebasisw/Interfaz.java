package pruebasisw;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interfaz extends JFrame {
    private Banco banco;
    private JTextField campoNumeroCliente;
    private JPasswordField campoContrasena;
    private JTextField campoMonto;
    private JTextArea areaResultados;

    public Interfaz() {
        banco = new Banco();
        
        // Inicializando la interfaz gráfica
        setTitle("Sistema Bancario");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        
        JLabel etiquetaCliente = new JLabel("Número de Cliente:");
        etiquetaCliente.setBounds(30, 30, 150, 25);
        add(etiquetaCliente);
        
        campoNumeroCliente = new JTextField();
        campoNumeroCliente.setBounds(180, 30, 150, 25);
        add(campoNumeroCliente);
        
        JLabel etiquetaContrasena = new JLabel("Contraseña:");
        etiquetaContrasena.setBounds(30, 70, 150, 25);
        add(etiquetaContrasena);
        
        campoContrasena = new JPasswordField();
        campoContrasena.setBounds(180, 70, 150, 25);
        add(campoContrasena);
        
        JButton botonAutenticar = new JButton("Autenticar");
        botonAutenticar.setBounds(30, 110, 150, 25);
        add(botonAutenticar);
        
        JButton botonRegistro = new JButton("Registro");
        botonRegistro.setBounds(180, 110, 150, 25);
        add(botonRegistro);
        
        JButton botonEliminarUsuario = new JButton("Eliminar Usuario");
        botonEliminarUsuario.setBounds(30, 150, 300, 25);
        add(botonEliminarUsuario);
        
        JLabel etiquetaMonto = new JLabel("Monto:");
        etiquetaMonto.setBounds(30, 190, 150, 25);
        add(etiquetaMonto);
        
        campoMonto = new JTextField();
        campoMonto.setBounds(180, 190, 150, 25);
        add(campoMonto);
        
        JButton botonDepositar = new JButton("Depositar");
        botonDepositar.setBounds(30, 230, 150, 25);
        add(botonDepositar);
        
        JButton botonRetirar = new JButton("Retirar");
        botonRetirar.setBounds(180, 230, 150, 25);
        add(botonRetirar);
        
        areaResultados = new JTextArea();
        areaResultados.setBounds(30, 270, 300, 100);
        areaResultados.setEditable(false);
        add(areaResultados);
        
        // Acción para autenticar
        botonAutenticar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                autenticarCliente();
            }
        });
        
        // Acción para registro
        botonRegistro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaRegistro();
            }
        });
        
        // Acción para eliminar usuario
        botonEliminarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaEliminarUsuario();
            }
        });
        
        // Acción para depositar
        botonDepositar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarDeposito();
            }
        });
        
        // Acción para retirar
        botonRetirar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarRetiro();
            }
        });
    }

    private void autenticarCliente() {
        String numeroClienteStr = campoNumeroCliente.getText();
        String contrasena = new String(campoContrasena.getPassword());

        if (numeroClienteStr.isEmpty() || contrasena.isEmpty()) {
            areaResultados.setText("Por favor ingrese el número de cliente y la contraseña.");
            return;
        }

        try {
            int numeroCliente = Integer.parseInt(numeroClienteStr);
            Cliente cliente = banco.autenticar(numeroCliente, contrasena);
            if (cliente != null) {
                areaResultados.setText("Cliente autenticado: " + cliente.getNombre());
            } else {
                areaResultados.setText("Credenciales incorrectas.");
            }
        } catch (NumberFormatException e) {
            areaResultados.setText("Número de cliente inválido.");
        }
    }

    private void realizarDeposito() {
        String numeroClienteStr = campoNumeroCliente.getText();
        String montoStr = campoMonto.getText();

        if (numeroClienteStr.isEmpty() || montoStr.isEmpty()) {
            areaResultados.setText("Por favor ingrese el número de cliente y el monto.");
            return;
        }

        try {
            int numeroCliente = Integer.parseInt(numeroClienteStr);
            double monto = Double.parseDouble(montoStr);

            Cuenta cuenta = banco.obtenerCuenta(numeroCliente);
            if (cuenta != null) {
                cuenta.depositar(monto);
                areaResultados.setText("Depósito exitoso. Nuevo saldo: " + cuenta.getSaldo());
            } else {
                areaResultados.setText("Cuenta no encontrada.");
            }
        } catch (NumberFormatException e) {
            areaResultados.setText("Monto inválido.");
        }
    }

    private void realizarRetiro() {
        String numeroClienteStr = campoNumeroCliente.getText();
        String montoStr = campoMonto.getText();

        if (numeroClienteStr.isEmpty() || montoStr.isEmpty()) {
            areaResultados.setText("Por favor ingrese el número de cliente y el monto.");
            return;
        }

        try {
            int numeroCliente = Integer.parseInt(numeroClienteStr);
            double monto = Double.parseDouble(montoStr);

            Cuenta cuenta = banco.obtenerCuenta(numeroCliente);
            if (cuenta != null) {
                if (cuenta.retirar(monto)) {
                    areaResultados.setText("Retiro exitoso. Nuevo saldo: " + cuenta.getSaldo());
                } else {
                    areaResultados.setText("Saldo insuficiente.");
                }
            } else {
                areaResultados.setText("Cuenta no encontrada.");
            }
        } catch (NumberFormatException e) {
            areaResultados.setText("Monto inválido.");
        }
    }

    // Método para abrir el diálogo de registro
    private void abrirVentanaRegistro() {
        JDialog registroDialog = new JDialog(this, "Registro de Cliente", true);
        registroDialog.setSize(300, 250);
        registroDialog.setLayout(null);

        JLabel etiquetaNombre = new JLabel("Nombre:");
        etiquetaNombre.setBounds(30, 30, 80, 25);
        registroDialog.add(etiquetaNombre);

        JTextField campoNombre = new JTextField();
        campoNombre.setBounds(120, 30, 150, 25);
        registroDialog.add(campoNombre);

        JLabel etiquetaContrasena = new JLabel("Contraseña:");
        etiquetaContrasena.setBounds(30, 70, 80, 25);
        registroDialog.add(etiquetaContrasena);

        JPasswordField campoContrasena = new JPasswordField();
        campoContrasena.setBounds(120, 70, 150, 25);
        registroDialog.add(campoContrasena);

        JTextArea areaResultadoRegistro = new JTextArea();
        areaResultadoRegistro.setBounds(30, 150, 240, 50);
        areaResultadoRegistro.setEditable(false);
        registroDialog.add(areaResultadoRegistro);

        JButton botonRegistrar = new JButton("Registrar");
        botonRegistrar.setBounds(30, 110, 240, 25);
        registroDialog.add(botonRegistrar);

        // Acción del botón de registro
        botonRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = campoNombre.getText();
                String contrasena = new String(campoContrasena.getPassword());

                if (nombre.isEmpty() || contrasena.isEmpty()) {
                    areaResultadoRegistro.setText("Por favor ingrese el nombre y la contraseña.");
                    return;
                }

                int nuevoIdCliente = banco.generarNuevoIdCliente();
                Cliente nuevoCliente = new Cliente(nuevoIdCliente, nombre, contrasena);
                int nuevoIdCuenta = banco.generarNuevoIdCuenta();
                Cuenta nuevaCuenta = new Cuenta(nuevoIdCuenta, nuevoIdCliente, 0.0);

                banco.registrarCliente(nuevoCliente, nuevaCuenta);
                areaResultadoRegistro.setText("Cliente registrado con ID: " + nuevoIdCliente);
            }
        });

        registroDialog.setVisible(true);
    }

    // Método para abrir el diálogo de eliminación de usuario
    private void abrirVentanaEliminarUsuario() {
        JDialog eliminarDialog = new JDialog(this, "Eliminar Usuario", true);
        eliminarDialog.setSize(300, 200);
        eliminarDialog.setLayout(null);

        JLabel etiquetaIDCliente = new JLabel("ID Cliente:");
        etiquetaIDCliente.setBounds(30, 30, 80, 25);
        eliminarDialog.add(etiquetaIDCliente);

        JTextField campoIDCliente = new JTextField();
        campoIDCliente.setBounds(120, 30, 150, 25);
        eliminarDialog.add(campoIDCliente);

        JLabel etiquetaContrasena = new JLabel("Contraseña:");
        etiquetaContrasena.setBounds(30, 70, 80, 25);
        eliminarDialog.add(etiquetaContrasena);

        JPasswordField campoContrasenaEliminar = new JPasswordField();
        campoContrasenaEliminar.setBounds(120, 70, 150, 25);
        eliminarDialog.add(campoContrasenaEliminar);

        JTextArea areaResultadoEliminar = new JTextArea();
        areaResultadoEliminar.setBounds(30, 130, 240, 50);
        areaResultadoEliminar.setEditable(false);
        eliminarDialog.add(areaResultadoEliminar);

        JButton botonEliminar = new JButton("Eliminar");
        botonEliminar.setBounds(30, 100, 240, 25);
        eliminarDialog.add(botonEliminar);

        // Acción del botón de eliminar
        botonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idClienteStr = campoIDCliente.getText();
                String contrasena = new String(campoContrasenaEliminar.getPassword());

                if (idClienteStr.isEmpty() || contrasena.isEmpty()) {
                    areaResultadoEliminar.setText("Por favor ingrese el ID del cliente y la contraseña.");
                    return;
                }

                try {
                    int idCliente = Integer.parseInt(idClienteStr);
                    Cliente cliente = banco.autenticar(idCliente, contrasena);
                    if (cliente != null) {
                        banco.eliminarCliente(idCliente);
                        areaResultadoEliminar.setText("Cliente eliminado con éxito.");
                    } else {
                        areaResultadoEliminar.setText("Credenciales incorrectas.");
                    }
                } catch (NumberFormatException ex) {
                    areaResultadoEliminar.setText("ID de cliente inválido.");
                }
            }
        });

        eliminarDialog.setVisible(true);
    }

    public static void main(String[] args) {
        // Crear el banco y algunos clientes y cuentas de prueba
        Banco banco = new Banco();
        int clienteId1 = banco.generarNuevoIdCliente();
        Cliente cliente1 = new Cliente(clienteId1, "Juan Pérez", "1234");
        int cuentaId1 = banco.generarNuevoIdCuenta();
        Cuenta cuenta1 = new Cuenta(cuentaId1, clienteId1, 500.0);
        banco.registrarCliente(cliente1, cuenta1);

        // Inicializar la interfaz gráfica
        Interfaz interfaz = new Interfaz();
        interfaz.setVisible(true);
    }
}

