package com.psayago.examentecnico.java_angular_tp.controller;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
//import org.hibernate.query.Query;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.psayago.examentecnico.java_angular_tp.JPAUtil;
import com.psayago.examentecnico.java_angular_tp.model.Carrito;
import com.psayago.examentecnico.java_angular_tp.model.Cliente;
import com.psayago.examentecnico.java_angular_tp.model.Factura;
import com.psayago.examentecnico.java_angular_tp.model.Producto;
import com.psayago.examentecnico.java_angular_tp.model.ProductoCompra;
import com.psayago.examentecnico.java_angular_tp.model.Venta;

@RestController
public class ControladorRest {
	
	private static Logger logger = Logger.getLogger(ControladorRest.class);
	private EntityManager manager; //= JPAUtil.getEntityManagerFactory().createEntityManager();
	
	@RequestMapping(value = "api/getAllClientes", method = RequestMethod.GET)
	public ResponseEntity<List<Cliente>> getAllClientes() throws SQLException {
		manager = JPAUtil.getEntityManagerFactory().createEntityManager();
		
		logger.info("Obteniendo todos los clientes");
		@SuppressWarnings("unchecked")
		List<Cliente> clientes = manager.createNamedQuery("Cliente.findAll").getResultList();

		new ResponseEntity<List<Cliente>>(HttpStatus.OK);
		return ResponseEntity.ok(clientes);

	}
	
	@RequestMapping(value = "api/getAllProductos", method = RequestMethod.GET)
	public ResponseEntity<List<Producto>> getAllProductos() throws SQLException {
		manager = JPAUtil.getEntityManagerFactory().createEntityManager();
		logger.info("Obteniendo todos los productos");
		@SuppressWarnings("unchecked")
		List<Producto> productos = manager.createNamedQuery("Producto.findAll").getResultList();
		
		new ResponseEntity<List<Cliente>>(HttpStatus.OK);
		return ResponseEntity.ok(productos);

	}
	
	@RequestMapping(value = "api/saveCliente", method = RequestMethod.POST)
	public ResponseEntity<Cliente> saveCliente(@RequestBody Cliente cliente) throws SQLException {
		
		manager = JPAUtil.getEntityManagerFactory().createEntityManager();
	    
		logger.info("Save cliente en BBDD");
		manager.getTransaction().begin();
	    		
		manager.persist(cliente);
		manager.getTransaction().commit();
		
		manager.close();
		new ResponseEntity<List<Cliente>>(HttpStatus.OK);
		return ResponseEntity.ok(cliente);

	}
	
	@RequestMapping(value = "api/saveProducto", method = RequestMethod.POST)
	public ResponseEntity<Producto> saveProducto(@RequestBody Producto producto) throws SQLException {
		
		manager = JPAUtil.getEntityManagerFactory().createEntityManager();
		
		logger.info("Save producto en BBDD");
		manager.getTransaction().begin();
	    		
		manager.persist(producto);
		manager.getTransaction().commit();
		
		manager.close();
		new ResponseEntity<List<Cliente>>(HttpStatus.OK);
		return ResponseEntity.ok(producto);

	}
	
	@RequestMapping(value = "api/confirmarVenta", method = RequestMethod.POST)
	public ResponseEntity<Carrito> confirmarVenta(@RequestBody Carrito carrito) throws SQLException {
		
		manager= JPAUtil.getEntityManagerFactory().createEntityManager();
		
		logger.info("Recibo la compra");
		manager.getTransaction().begin();
		
		Factura factura = new Factura();
		
		factura.setIdCliente(carrito.getCliente());
		factura.setTotal(carrito.getTotal());
		factura.setFecha(new Date());
		manager.persist(factura);
		
		carrito.getProductos().
		forEach(item -> {
			guardarVenta(item, factura, manager);
		});
		
		manager.persist(factura);
		manager.getTransaction().commit();
		
		manager.close();
		
		System.out.println(carrito.toString());		
		
		new ResponseEntity<List<Cliente>>(HttpStatus.OK);
		return ResponseEntity.ok(carrito);

	}
	
	@RequestMapping(value = "api/mostrarfactura", method = RequestMethod.GET)
	private ResponseEntity<String> mostrarFactura(@RequestParam int id){
		manager = JPAUtil.getEntityManagerFactory().createEntityManager();
		logger.info("Generandoo la factura");
		Factura factura = null;
		try {
			factura = manager.find(Factura.class, id);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		String facturaString = "-- NO EXISTE ESA FACTURA ---";
		if (factura != null) {
			facturaString = factura.toString();
			System.out.println(facturaString);
		}
		new ResponseEntity<String>(HttpStatus.OK);
		return ResponseEntity.ok(facturaString);
		 
	}
	
	private void guardarVenta(ProductoCompra item, Factura factura, EntityManager manager) {
		
		Producto producto = manager.find(Producto.class, item.getId());	
		Venta venta = new Venta();
		
		venta.setIdProducto(producto);
		venta.setCantidad(item.getCantidad());
		venta.setNroFactura(factura);
		System.out.println(venta);
		factura.getVentas().add(venta);
		manager.persist(venta);
	}
	 
	
	

}
