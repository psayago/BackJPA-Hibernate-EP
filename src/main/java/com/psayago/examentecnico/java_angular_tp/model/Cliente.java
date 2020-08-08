package com.psayago.examentecnico.java_angular_tp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
/**
Este es el modelo que se asocia a la DB	
**/
@Entity
@Table(name = "Clientes")
@NamedQuery(name="Cliente.findAll", query="SELECT f FROM Cliente f")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CLIENTE")
	private int id;
	@Column
	private String nombre;
	@Column
	private String apellido;
	@Column
	private String email;
	@Column(name = "ESVIP")
	private Boolean esVip;
	
	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Cliente(int id, String nombre, String apeliido, String email, Boolean esVip) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apeliido;
		this.email = email;
		this.esVip = esVip;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Boolean getEsVip() {
		return esVip;
	}
	public void setEsVip(Boolean esVip) {
		this.esVip = esVip;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email + ", esVip="
				+ esVip + "]";
	}
	
	
}
