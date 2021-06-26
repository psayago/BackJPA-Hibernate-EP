/*
 *
 */
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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.psayago.examentecnico.java_angular_tp.JPAUtil;
import com.psayago.examentecnico.java_angular_tp.model.Carrito;
import com.psayago.examentecnico.java_angular_tp.model.Cliente;
import com.psayago.examentecnico.java_angular_tp.model.Factura;
import com.psayago.examentecnico.java_angular_tp.model.Producto;
import com.psayago.examentecnico.java_angular_tp.model.ProductoCompra;
import com.psayago.examentecnico.java_angular_tp.model.Venta;

/**
 * The Class ControladorRest.
 */
@RestController
public class ControladorRest {

	/** The logger. */
	private static Logger logger = Logger.getLogger(ControladorRest.class);

	/** The json mapper. */
	private ObjectMapper jsonMapper = new ObjectMapper();

	/** The manager. */
	private EntityManager manager; //= JPAUtil.getEntityManagerFactory().createEntityManager();

	/**
	 * Gets the all clientes.
	 *
	 * @return the all clientes
	 * @throws SQLException the SQL exception
	 */
	@RequestMapping(value = "api/getAllClientes", method = RequestMethod.GET)
	public ResponseEntity<JsonNode> getAllClientes() throws SQLException {
		manager = JPAUtil.getEntityManagerFactory().createEntityManager();

		logger.info("Obteniendo todos los clientes");
		@SuppressWarnings("unchecked")
		List<Cliente> clientes = manager.createNamedQuery("Cliente.findAll").getResultList();

		return ResponseEntity.ok(jsonMapper.valueToTree(clientes));

	}

	/**
	 * Gets the all productos.
	 *
	 * @return the all productos
	 * @throws SQLException the SQL exception
	 */
	@RequestMapping(value = "api/getAllProductos", method = RequestMethod.GET)
	public ResponseEntity<JsonNode> getAllProductos() throws SQLException {
		manager = JPAUtil.getEntityManagerFactory().createEntityManager();
		logger.info("Obteniendo todos los productos");
		@SuppressWarnings("unchecked")
		List<Producto> productos = manager.createNamedQuery("Producto.findAll").getResultList();

		return ResponseEntity.ok(jsonMapper.valueToTree(productos));

	}

	/**
	 * Save cliente.
	 *
	 * @param cliente the cliente
	 * @return the response entity
	 * @throws SQLException the SQL exception
	 */
	@RequestMapping(value = "api/saveCliente", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> saveCliente(@RequestBody final Cliente cliente) throws SQLException {

		manager = JPAUtil.getEntityManagerFactory().createEntityManager();

		logger.info("Save cliente en BBDD");
		manager.getTransaction().begin();

		manager.persist(cliente);
		manager.getTransaction().commit();

		manager.close();
		new ResponseEntity<List<Cliente>>(HttpStatus.OK);
		return ResponseEntity.ok(jsonMapper.valueToTree(cliente));

	}

	/**
	 * Save producto.
	 *
	 * @param producto the producto
	 * @return the response entity
	 * @throws SQLException the SQL exception
	 */
	@RequestMapping(value = "api/saveProducto", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> saveProducto(@RequestBody final Producto producto) throws SQLException {

		manager = JPAUtil.getEntityManagerFactory().createEntityManager();

		logger.info("Save producto en BBDD");
		manager.getTransaction().begin();

		manager.persist(producto);
		manager.getTransaction().commit();

		manager.close();
		new ResponseEntity<List<Cliente>>(HttpStatus.OK);
		return ResponseEntity.ok(jsonMapper.valueToTree(producto));

	}

	/**
	 * Confirmar venta.
	 *
	 * @param carrito the carrito
	 * @return the response entity
	 * @throws SQLException the SQL exception
	 */
	@RequestMapping(value = "api/confirmarVenta", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> confirmarVenta(@RequestBody final Carrito carrito) throws SQLException {

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
		return ResponseEntity.ok(jsonMapper.valueToTree(carrito));

	}

	/**
	 * Mostrar factura.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@RequestMapping(value = "api/mostrarfactura", method = RequestMethod.GET)
	private ResponseEntity<JsonNode> mostrarFactura(@RequestParam final int id){
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
		return ResponseEntity.ok(jsonMapper.valueToTree(facturaString));

	}

	/**
	 * Guardar venta.
	 *
	 * @param item the item
	 * @param factura the factura
	 * @param manager the manager
	 */
	private void guardarVenta(final ProductoCompra item, final Factura factura, final EntityManager manager) {

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
