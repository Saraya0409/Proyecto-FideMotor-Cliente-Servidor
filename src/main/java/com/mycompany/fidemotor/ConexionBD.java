/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fidemotor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *

 */
public class ConexionBD {
    static final String URL = "jdbc:mysql://127.0.0.1:3306/fidemotor?user=root&password=Progra12345@!";
    static final String INSERT_USUARIO = "INSERT INTO usuarios (nombre, apellido, identificacion, telefono, password, direccion, sexo, fecha_nacimiento, correo_electronico, tarjeta_credito) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void cerrarConexion(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean insertarUsuario(Usuario usuario) {
        try (Connection conn = conectar();
             PreparedStatement statement = conn.prepareStatement(INSERT_USUARIO)) {

            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getApellidos());
            statement.setString(3, usuario.getIdentificacion());
            statement.setString(4, usuario.getTelefono());
            statement.setString(5, usuario.getContraseña());
            statement.setString(6, usuario.getDireccion());
            statement.setString(7, String.valueOf(usuario.getSexo()));
            statement.setString(8, usuario.getFechaNacimiento());
            statement.setString(9, usuario.getDireccion());
            statement.setString(10, usuario.getTarjetaCredito());

            int filasInsertadas = statement.executeUpdate();
            return filasInsertadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean agregarVehiculo(Vehiculo vehiculo) {
        String query = "INSERT INTO vehiculos (marca, modelo, precio) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, vehiculo.getMarca());
            stmt.setString(2, vehiculo.getModelo());
            stmt.setDouble(3, vehiculo.getPrecio());

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static boolean editarVehiculo(Vehiculo vehiculo) {
    String query = "UPDATE vehiculos SET marca = ?, modelo = ?, precio = ? WHERE id = ?";
    try (Connection conn = DriverManager.getConnection(URL);
         PreparedStatement stmt = conn.prepareStatement(query)) {
        
        stmt.setString(1, vehiculo.getMarca());
        stmt.setString(2, vehiculo.getModelo());
        stmt.setDouble(3, vehiculo.getPrecio());
        // Asume que Vehiculo tiene un campo id con su getter

        int affectedRows = stmt.executeUpdate();
        return affectedRows > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

public static boolean eliminarVehiculo(int id) {
    String query = "DELETE FROM vehiculos WHERE id = ?";
    try (Connection conn = DriverManager.getConnection(URL);
         PreparedStatement stmt = conn.prepareStatement(query)) {
        
        stmt.setInt(1, id);

        int affectedRows = stmt.executeUpdate();
        return affectedRows > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
}
