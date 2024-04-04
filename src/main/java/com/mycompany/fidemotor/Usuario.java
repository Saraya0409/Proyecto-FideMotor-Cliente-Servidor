/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fidemotor;

/**
 *
 * @author yagoa
 */
public class Usuario {

    private String nombre;
    private String apellidos;
    private String identificacion;
    private String telefono;
    private String contraseña;
    private String direccion;
    private String sexo;
    private String fechaNacimiento;
    private String tarjetaCredito;

    public Usuario(String nombre, String apellidos, String identificacion, String telefono, String contraseña,
            String direccion, String sexo, String fechaNacimiento, String tarjetaCredito) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.identificacion = identificacion;
        this.telefono = telefono;
        this.contraseña = contraseña;
        this.direccion = direccion;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.tarjetaCredito = tarjetaCredito;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTarjetaCredito() {
        return tarjetaCredito;
    }

    public void setTarjetaCredito(String tarjetaCredito) {
        this.tarjetaCredito = tarjetaCredito;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}

