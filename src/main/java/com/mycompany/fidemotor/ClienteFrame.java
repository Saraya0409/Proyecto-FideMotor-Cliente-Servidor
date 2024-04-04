/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fidemotor;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class ClienteFrame extends JFrame{
    private List<Vehiculo> vehiculos;
    private JPanel panelInicio;
    private JButton btnAgregarUsuario;
    private JButton btnIniciarSesion;
    

    private Socket socket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    public ClienteFrame() {
        setTitle("FideMotor App");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
        conectarAServidor();
    }

    private void initComponents() {
        panelInicio = new JPanel();
        btnAgregarUsuario = new JButton("Agregar usuario");
        btnIniciarSesion = new JButton("Iniciar sesión");

        panelInicio.setLayout(new GridLayout(2, 1));
        panelInicio.add(btnAgregarUsuario);
        panelInicio.add(btnIniciarSesion);

        btnAgregarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaAgregarUsuario();
            }
        });

        btnIniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaInicioSesion();
            }
        });

        add(panelInicio);
    }

    private void abrirVentanaAgregarUsuario() {
        JFrame frameAgregarUsuario = new JFrame("Agregar Usuario");
        frameAgregarUsuario.setSize(400, 300);
        frameAgregarUsuario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelAgregarUsuario = new JPanel();
        panelAgregarUsuario.setLayout(new GridLayout(9, 2));

        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField();
        JLabel lblApellidos = new JLabel("Apellidos:");
        JTextField txtApellidos = new JTextField();
        JLabel lblIdentificacion = new JLabel("Identificación:");
        JTextField txtIdentificacion = new JTextField();
        JLabel lblTelefono = new JLabel("Teléfono:");
        JTextField txtTelefono = new JTextField();
        JLabel lblContraseña = new JLabel("Contraseña:");
        JPasswordField txtContraseña = new JPasswordField();
        JLabel lblDireccion = new JLabel("Dirección:");
        JTextField txtDireccion = new JTextField();
        JLabel lblSexo = new JLabel("Sexo:");
        JTextField txtSexo = new JTextField();
        JLabel lblFechaNacimiento = new JLabel("Fecha de Nacimiento:");
        JTextField txtFechaNacimiento = new JTextField();
        JLabel lblTarjetaCredito = new JLabel("TarjetaCredito:");
        JTextField txtTarjetaCredito = new JTextField();
        JButton btnEnviar = new JButton("Enviar");

        panelAgregarUsuario.add(lblNombre);
        panelAgregarUsuario.add(txtNombre);
        panelAgregarUsuario.add(lblApellidos);
        panelAgregarUsuario.add(txtApellidos);
        panelAgregarUsuario.add(lblIdentificacion);
        panelAgregarUsuario.add(txtIdentificacion);
        panelAgregarUsuario.add(lblTelefono);
        panelAgregarUsuario.add(txtTelefono);
        panelAgregarUsuario.add(lblContraseña);
        panelAgregarUsuario.add(txtContraseña);
        panelAgregarUsuario.add(lblDireccion);
        panelAgregarUsuario.add(txtDireccion);
        panelAgregarUsuario.add(lblSexo);
        panelAgregarUsuario.add(txtSexo);
        panelAgregarUsuario.add(lblFechaNacimiento);
        panelAgregarUsuario.add(txtFechaNacimiento);
        panelAgregarUsuario.add(lblTarjetaCredito);
        panelAgregarUsuario.add(txtTarjetaCredito);
        panelAgregarUsuario.add(btnEnviar);

        frameAgregarUsuario.add(panelAgregarUsuario);
        frameAgregarUsuario.setVisible(true);

        btnEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Usuario usuario = new Usuario(
                        txtNombre.getText(),
                        txtApellidos.getText(),
                        txtIdentificacion.getText(),
                        txtTelefono.getText(),
                        new String(txtContraseña.getPassword()),
                        txtDireccion.getText(),
                        txtSexo.getText(),
                        txtFechaNacimiento.getText(),
                        txtTarjetaCredito.getText()
                );
                enviarUsuarioAlServidor(usuario);
                frameAgregarUsuario.dispose();
            }
        });
    }

    private void abrirVentanaInicioSesion() {
        JFrame frameInicioSesion = new JFrame("Inicio de Sesión");
        frameInicioSesion.setSize(300, 200);
        frameInicioSesion.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelInicioSesion = new JPanel();
        panelInicioSesion.setLayout(new GridLayout(3, 2));

        JLabel lblIdentificacion = new JLabel("Identificación:");
        JTextField txtIdentificacion = new JTextField();
        JLabel lblContraseña = new JLabel("Contraseña:");
        JPasswordField txtContraseña = new JPasswordField();
        JButton btnIniciar = new JButton("Iniciar sesión");

        panelInicioSesion.add(lblIdentificacion);
        panelInicioSesion.add(txtIdentificacion);
        panelInicioSesion.add(lblContraseña);
        panelInicioSesion.add(txtContraseña);
        panelInicioSesion.add(btnIniciar);

        frameInicioSesion.add(panelInicioSesion);
        frameInicioSesion.setVisible(true);

        btnIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String identificacion = txtIdentificacion.getText();
                String contraseña = new String(txtContraseña.getPassword());
                iniciarSesion(identificacion, contraseña);
                frameInicioSesion.dispose();
            }
        });
    }

    private void conectarAServidor() {
        try {
            socket = new Socket("localhost", 12345); 
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void enviarUsuarioAlServidor(Usuario usuario) {
        try {
            outputStream.writeObject("AGREGAR_USUARIO");
            outputStream.writeObject(usuario);
            outputStream.flush();

            String respuesta = (String) inputStream.readObject();
            if (respuesta.equals("OK")) {
                JOptionPane.showMessageDialog(this, "Usuario agregado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Error al agregar usuario", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void iniciarSesion(String identificacion, String contraseña) {
        try {
            outputStream.writeObject("INICIAR_SESION");
            outputStream.writeObject(identificacion);
            outputStream.writeObject(contraseña);
            outputStream.flush();

            String respuesta = (String) inputStream.readObject();
            if (respuesta.equals("OK")) {
                mostrarOpcionesDisponibles();
            } else {
                JOptionPane.showMessageDialog(this, "Identificación o contraseña incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void mostrarOpcionesDisponibles() {
        JFrame frameOpciones = new JFrame("Opciones disponibles");
        frameOpciones.setSize(400, 300);
        frameOpciones.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelOpciones = new JPanel();
        panelOpciones.setLayout(new GridLayout(4, 1));

        JButton btnSalirSesion = new JButton("Salir de la sesión");
        JButton btnVisualizarVehiculos = new JButton("Visualizar vehículos");
        JButton btnEditarDatos = new JButton("Editar datos");
        JButton btnVisualizarCompras = new JButton("Visualizar compras");

        panelOpciones.add(btnSalirSesion);
        panelOpciones.add(btnVisualizarVehiculos);
        panelOpciones.add(btnEditarDatos);
        panelOpciones.add(btnVisualizarCompras);

        frameOpciones.add(panelOpciones);
        frameOpciones.setVisible(true);

        // ActionListener para salir de la sesión
        btnSalirSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrarSesion();
                frameOpciones.dispose();
            }
        });

        // ActionListener para visualizar vehículos
        btnVisualizarVehiculos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
                JOptionPane.showMessageDialog(frameOpciones, "Mostrando vehículos disponibles");
            }
        });

        
        btnEditarDatos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaEditarDatos();
                frameOpciones.dispose();
            }
        });

        // ActionListener para visualizar compras
        btnVisualizarCompras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implementar lógica para visualizar compras realizadas
                JOptionPane.showMessageDialog(frameOpciones, "Mostrando compras realizadas");
            }
        });
    }

    private void cerrarSesion() {
        try {
            outputStream.writeObject("CERRAR_SESION");
            outputStream.flush();
            JOptionPane.showMessageDialog(this, "Sesión cerrada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void abrirVentanaEditarDatos() {
        JFrame frameEditarDatos = new JFrame("Editar Datos");
        frameEditarDatos.setSize(400, 300);
        frameEditarDatos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelEditarDatos = new JPanel();
        panelEditarDatos.setLayout(new GridLayout(4, 2));

        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField();
        JLabel lblApellidos = new JLabel("Apellidos:");
        JTextField txtApellidos = new JTextField();
        JLabel lblTelefono = new JLabel("Teléfono:");
        JTextField txtTelefono = new JTextField();
        JLabel lblDireccion = new JLabel("Dirección:");
        JTextField txtDireccion = new JTextField();

        JButton btnGuardar = new JButton("Guardar");

        panelEditarDatos.add(lblNombre);
        panelEditarDatos.add(txtNombre);
        panelEditarDatos.add(lblApellidos);
        panelEditarDatos.add(txtApellidos);
        panelEditarDatos.add(lblTelefono);
        panelEditarDatos.add(txtTelefono);
        panelEditarDatos.add(lblDireccion);
        panelEditarDatos.add(txtDireccion);


        panelEditarDatos.add(btnGuardar);

        frameEditarDatos.add(panelEditarDatos);
        frameEditarDatos.setVisible(true);

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implementar lógica para guardar los datos editados
                JOptionPane.showMessageDialog(frameEditarDatos, "Datos guardados correctamente");
                frameEditarDatos.dispose();
            }
        });
    }

    private void mostrarVehiculosDisponibles() {
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("Vehículos disponibles:\n\n");

        for (Vehiculo vehiculo : vehiculos) {
            mensaje.append("Marca: ").append(vehiculo.getMarca()).append("\n");
            mensaje.append("Modelo: ").append(vehiculo.getModelo()).append("\n");
            mensaje.append("Año: ").append(vehiculo.getAnio()).append("\n");
            mensaje.append("Precio: ").append(vehiculo.getPrecio()).append("\n");
            mensaje.append("\n");
        }

        JOptionPane.showMessageDialog(this, mensaje.toString(), "Vehículos Disponibles", JOptionPane.INFORMATION_MESSAGE);
    }
       
    

    private void abrirVentanaCompra() {
        JFrame frameCompra = new JFrame("Realizar Compra");
        frameCompra.setSize(400, 200);
        frameCompra.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelCompra = new JPanel();
        panelCompra.setLayout(new GridLayout(3, 2));

        JLabel lblFormaPago = new JLabel("Forma de Pago:");
        JComboBox<String> cmbFormaPago = new JComboBox<>(new String[]{"Efectivo", "Tarjeta de Crédito"});
        JLabel lblNumeroTarjeta = new JLabel("Número de Tarjeta:");
        JTextField txtNumeroTarjeta = new JTextField();
        JButton btnComprar = new JButton("Realizar Compra");

        panelCompra.add(lblFormaPago);
        panelCompra.add(cmbFormaPago);
        panelCompra.add(lblNumeroTarjeta);
        panelCompra.add(txtNumeroTarjeta);
        panelCompra.add(btnComprar);

        frameCompra.add(panelCompra);
        frameCompra.setVisible(true);

        btnComprar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String formaPago = (String) cmbFormaPago.getSelectedItem();
                String numeroTarjeta = txtNumeroTarjeta.getText();
                realizarCompra(formaPago, numeroTarjeta);
                frameCompra.dispose();
            }
        });
    }

    private void realizarCompra(String formaPago, String numeroTarjeta) {
        try {
            outputStream.writeObject("REALIZAR_COMPRA");
            outputStream.writeObject(formaPago);
            outputStream.writeObject(numeroTarjeta);
            outputStream.flush();

            String respuesta = (String) inputStream.readObject();
            if (respuesta.equals("OK")) {
                JOptionPane.showMessageDialog(this, "Compra realizada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Error al realizar la compra", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ClienteFrame().setVisible(true);
            }
        });
    }
    
}


