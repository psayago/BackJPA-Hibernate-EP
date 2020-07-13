package com.psayago.examentecnico.java_angular_tp.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ventas database table.
 * 
 */
@Entity
@Table(name="ventas")
@NamedQuery(name="Venta.findAll", query="SELECT v FROM Venta v")
public class Venta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_VENTA")
	private int idVenta;

	@Column
	private int cantidad;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ID_PRODUCTO")
	private Producto idProducto;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "NRO_FACTURA")
	private Factura nroFactura;

	public Venta() {
	}
	

	public Venta(int idVenta, int cantidad, Producto idProducto, Factura nroFactura) {
		this.idVenta = idVenta;
		this.cantidad = cantidad;
		this.idProducto = idProducto;
		this.nroFactura = nroFactura;
	}

	public int getIdVenta() {
		return this.idVenta;
	}

	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Producto getIdProducto() {
		return this.idProducto;
	}

	public void setIdProducto(Producto idProducto) {
		this.idProducto = idProducto;
	}

	public Factura getNroFactura() {
		return this.nroFactura;
	}

	public void setNroFactura(Factura nroFactura) {
		this.nroFactura = nroFactura;
	}
	
	@Override
	public String toString() {
		return "Venta [idVenta=" + idVenta + ", cantidad=" + cantidad + ", idProducto=" + idProducto + ", nroFactura="
				+ nroFactura.getNroFactura() + "]";
	}

}