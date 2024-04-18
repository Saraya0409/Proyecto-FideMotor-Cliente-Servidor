/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fidemotor;



import java.time.LocalDate;

/**
 *
 * @author armi8
 */
public class Compra {
    private int id;
    private String clienteId; 
    private int vehiculoId; 
    private LocalDate fechaCompra;
    private double montoTotal;
    private String estado; 

   
    public Compra(int id, String clienteId, int vehiculoId, LocalDate fechaCompra, double montoTotal, String estado) {
        this.id = id;
        this.clienteId = clienteId;
        this.vehiculoId = vehiculoId;
        this.fechaCompra = fechaCompra;
        this.montoTotal = montoTotal;
        this.estado = estado;
    }

public class Compra {
    private int id;
    private String clienteId; 
    private int vehiculoId; 
    private LocalDate fechaCompra;
    private double montoTotal;
    private String estado; 

   
    public Compra(int id, String clienteId, int vehiculoId, LocalDate fechaCompra, double montoTotal, String estado) {
        this.id = id;
        this.clienteId = clienteId;
        this.vehiculoId = vehiculoId;
        this.fechaCompra = fechaCompra;
        this.montoTotal = montoTotal;
        this.estado = estado;
    }

   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    public int getVehiculoId() {
        return vehiculoId;
    }

    public void setVehiculoId(int vehiculoId) {
        this.vehiculoId = vehiculoId;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
