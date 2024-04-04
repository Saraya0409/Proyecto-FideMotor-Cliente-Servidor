/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fidemotor;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author armi8
 */
public class FideMotorBusinessFrame extends JFrame {
     private JButton btnGestionarVehiculos;
    private JButton btnVisualizarCompras;
    private JButton btnGestionarEstadosCompras;
    

    public FideMotorBusinessFrame() {
        setTitle("FideMotor Business");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JPanel panelPrincipal = new JPanel(new GridLayout(3, 1, 10, 10));

        btnGestionarVehiculos = new JButton("Gestionar Vehículos");
        btnVisualizarCompras = new JButton("Visualizar Compras");
        btnGestionarEstadosCompras = new JButton("Gestionar Estados de Compras");

        panelPrincipal.add(btnGestionarVehiculos);
        panelPrincipal.add(btnVisualizarCompras);
        panelPrincipal.add(btnGestionarEstadosCompras);

        btnGestionarVehiculos.addActionListener(this::abrirVentanaGestionVehiculos);
        btnVisualizarCompras.addActionListener(this::abrirVentanaVisualizarCompras);
        btnGestionarEstadosCompras.addActionListener(this::abrirVentanaGestionarEstadosCompras);

        add(panelPrincipal);
    }

    private void abrirVentanaGestionVehiculos(ActionEvent e) {
        JFrame frameGestionVehiculos = new JFrame("Gestionar Vehículos");
    frameGestionVehiculos.setSize(500, 400);
    frameGestionVehiculos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frameGestionVehiculos.setLayout(new BorderLayout());

    // Panel para botones
    JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JButton btnAgregar = new JButton("Agregar");
    JButton btnEditar = new JButton("Editar");
    JButton btnEliminar = new JButton("Eliminar");
    panelBotones.add(btnAgregar);
    panelBotones.add(btnEditar);
    panelBotones.add(btnEliminar);

    // Tabla para mostrar los vehículos
    String[] columnas = {"ID", "Marca", "Modelo", "Precio"};
    // Asumiendo que tienes una función que te devuelva los datos de los vehículos en formato Object[][]
    Object[][] datosVehiculos = obtenerDatosVehiculos();
    JTable tablaVehiculos = new JTable(datosVehiculos, columnas);
    JScrollPane scrollPane = new JScrollPane(tablaVehiculos);

    frameGestionVehiculos.add(panelBotones, BorderLayout.NORTH);
    frameGestionVehiculos.add(scrollPane, BorderLayout.CENTER);

    // Listeners para los botones (Estos necesitarán implementación)
    btnAgregar.addActionListener(event -> agregarVehiculo());
    btnEditar.addActionListener(event -> editarVehiculo());
    btnEliminar.addActionListener(event -> eliminarVehiculo());

    frameGestionVehiculos.setLocationRelativeTo(null); // Centrar ventana
    frameGestionVehiculos.setVisible(true);
    
    
    }
    
    private void agregarVehiculo() {
    JDialog dialog = new JDialog(this, "Agregar Vehículo", true);
    dialog.setLayout(new GridLayout(4, 2));

    JTextField marcaField = new JTextField();
    JTextField modeloField = new JTextField();
    JTextField precioField = new JTextField();
    JButton agregarBtn = new JButton("Agregar");

    dialog.add(new JLabel("Marca:"));
    dialog.add(marcaField);
    dialog.add(new JLabel("Modelo:"));
    dialog.add(modeloField);
    dialog.add(new JLabel("Precio:"));
    dialog.add(precioField);
    dialog.add(agregarBtn);

    agregarBtn.addActionListener(e -> {
        Vehiculo vehiculo = new Vehiculo(marcaField.getText(), modeloField.getText(), new BigDecimal(precioField.getText()));
        boolean exito = ConexionBD.agregarVehiculo(vehiculo);
        if (exito) {
            JOptionPane.showMessageDialog(dialog, "Vehículo agregado correctamente");
            dialog.dispose();
          
        } else {
            JOptionPane.showMessageDialog(dialog, "Error al agregar el vehículo", "Error", JOptionPane.ERROR_MESSAGE);
        }
    });

    dialog.pack();
    dialog.setLocationRelativeTo(this);
    dialog.setVisible(true);
}
    
    private void accionEditarVehiculo(ActionEvent e) {
    
    int selectedRow = tablaVehiculos.getSelectedRow();
    if (selectedRow >= 0) {
        int vehiculoId = Integer.parseInt(tablaVehiculos.getValueAt(selectedRow, 0).toString()); 
        

        Vehiculo vehiculo = obtenerVehiculoPorId(vehiculoId); // Este método necesita ser implementado
        
        JDialog dialog = new JDialog(this, "Editar Vehículo", true);
        dialog.setLayout(new GridLayout(4, 2));

        JTextField marcaField = new JTextField(vehiculo.getMarca());
        JTextField modeloField = new JTextField(vehiculo.getModelo());
        JTextField precioField = new JTextField(vehiculo.getPrecio().toString());
        JButton editarBtn = new JButton("Editar");

        // Agregar componentes al diálogo...
        
        editarBtn.addActionListener(event -> {
            // Actualizar vehículo con nuevos valores
            vehiculo.setMarca(marcaField.getText());
            vehiculo.setModelo(modeloField.getText());
            vehiculo.setPrecio(new BigDecimal(precioField.getText()));

            boolean exito = ConexionBD.editarVehiculo(vehiculo);
            if (exito) {
                JOptionPane.showMessageDialog(dialog, "Vehículo editado correctamente");
                dialog.dispose();
                // Actualizar tabla de vehículos aquí
            } else {
                JOptionPane.showMessageDialog(dialog, "Error al editar el vehículo", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    } else {
        JOptionPane.showMessageDialog(this, "Por favor, seleccione un vehículo para editar", "Advertencia", JOptionPane.WARNING_MESSAGE);
    }
    }

    private void abrirVentanaVisualizarCompras(ActionEvent e) {
    
    }

    private void abrirVentanaGestionarEstadosCompras(ActionEvent e) {
        // Aquí abrirías una ventana para gestionar los estados de las compras
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FideMotorBusinessFrame().setVisible(true));
    }

   
    
}
