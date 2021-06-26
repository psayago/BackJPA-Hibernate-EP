package com.psayago.examentecnico.java_angular_tp.model;

import java.util.List;

public class Carrito {
	
	private int id;
    private Cliente cliente;
    private List<ProductoCompra> productos;
    private double total;
    private String fechaCreacion;
    private int tipoCarrito;
    
	public Carrito() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Carrito(int id, Cliente cliente, List<ProductoCompra> productos, double total, String fechaCreacion,
			int tipoCarrito) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.productos = productos;
		this.total = total;
		this.fechaCreacion = fechaCreacion;
		this.tipoCarrito = tipoCarrito;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public List<ProductoCompra> getProductos() {
		return productos;
	}
	public void setProductos(List<ProductoCompra> productos) {
		this.productos = productos;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public int getTipoCarrito() {
		return tipoCarrito;
	}
	public void setTipoCarrito(int tipoCarrito) {
		this.tipoCarrito = tipoCarrito;
	}
	
	@Override
	public String toString() {
		return "Carrito [id=" + id + ", cliente=" + cliente + ", productos=" + productos + ", total=" + total
				+ ", fechaCreacion=" + fechaCreacion + ", tipoCarrito=" + tipoCarrito + "]";
	}
    
	
    

}
