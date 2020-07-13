package com.psayago.examentecnico.java_angular_tp.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.psayago.examentecnico.java_angular_tp.model.Cliente;
import com.psayago.examentecnico.java_angular_tp.model.Factura;
import com.psayago.examentecnico.java_angular_tp.model.Producto;
import com.psayago.examentecnico.java_angular_tp.model.Venta;

public class PruebaConsola {
	
	private static EntityManager manager;
	
	private static EntityManagerFactory emf;
	
	public static void main(String[] args) {
		emf = Persistence.createEntityManagerFactory("Persistencia");
		manager = emf.createEntityManager();
		
		Factura fact = manager.find(Factura.class, 2);
		System.out.println(fact);
		
//		Producto producto = new Producto();
//		producto.setNombre("Monitor Sentey 24'");
//		producto.setDescripcion("Monitor gamer 144hz");
//		producto.setPrecio(18999.99);
		
//		Cliente cliente = new Cliente();
//		cliente.setNombre("Claudia");
//		cliente.setApellido("Roman");
//		cliente.setEmail("croman@gmail.com");
//		cliente.setEsVip(false);
//		
		
		//manager.getTransaction().begin();
		
		
		
//		
//		Producto prod= manager.find(Producto.class, 3);
//		
//		System.out.println(prod);
//		Venta venta = new Venta();
//		venta.setIdProducto(prod);
//		venta.setCantidad(2);
//		venta.setNroFactura(null);
//		venta.setNroFactura(fact);
//		System.out.println(venta);
//		
//		fact.getVentas().add(venta);
		
		
//		
//        Producto prod2= manager.find(Producto.class, 4);
//		
//		System.out.println(prod2);
//		Venta venta2 = new Venta();
//		venta2.setIdProducto(prod);
//		System.out.println(venta2);
		
//		Cliente cliente = manager.find(Cliente.class, 8);
//		
//		Factura factura = new Factura();
//		factura.setIdCliente(cliente);
//		factura.setTotal(0);
//		
		
//		
//		
		
		
//		manager.persist(venta);
//		//manager.persist(venta2);
//		manager.persist(fact);
		//manager.getTransaction().commit();
		
		
		//manager.close();

	}

}
