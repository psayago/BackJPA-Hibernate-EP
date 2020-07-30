package com.psayago.examentecnico.java_angular_tp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the facturas database table.
 * 
 */
@Entity
@Table(name="facturas")
@NamedQuery(name="Factura.findAll", query="SELECT f FROM Factura f")
public class Factura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="NRO_FACTURA")
	private int nroFactura;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CLIENTE")
	private Cliente idCliente;

	@Column
	private double total;
	
	@Column(name="FECHA")
	private Date fecha;
	
	@OneToMany(mappedBy = "nroFactura")	
	private List<Venta> ventas= new ArrayList<Venta>();

	public Factura() {
	}

	public Factura(int nroFactura, Cliente idCliente, double total, Date fecha) {
		this.nroFactura = nroFactura;
		this.idCliente = idCliente;
		this.total = total;
		this.fecha = fecha;
	}

	public int getNroFactura() {
		return this.nroFactura;
	}

	public void setNroFactura(int nroFactura) {
		this.nroFactura = nroFactura;
	}

	public Cliente getIdCliente() {
		return this.idCliente;
	}

	public void setIdCliente(Cliente idCliente) {
		this.idCliente = idCliente;
	}

	public double getTotal() {
		return this.total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public List<Venta> getVentas() {
		return ventas;
	}

	public void setVentas(List<Venta> ventas) {
		this.ventas = ventas;
	}
	
	public Date getFecha() {
		return fecha;
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "Factura [nroFactura=" + nroFactura 
				+ ", idCliente=" + idCliente 
				+ ", total=" + total 
				+ ", fecha=" + fecha.toString() 
				+ ", ventas=" + ventas + "]";
	}

	

}