package com.psayago.examentecnico.java_angular_tp.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.psayago.examentecnico.java_angular_tp.JPAUtil;
import com.psayago.examentecnico.java_angular_tp.model.Cliente;
import com.psayago.examentecnico.java_angular_tp.model.Producto;

@RestController
public class ControladorRest {
	
	//final static Logger logger = Logger.getLogger(Logger.class);
	private EntityManager manager = JPAUtil.getEntityManagerFactory().createEntityManager();
	
	@RequestMapping(value = "api/getAllClientes", method = RequestMethod.GET)
	public ResponseEntity<List<Cliente>> getAllClientes() {
		
//		CriteriaBuilder cb = manager.getCriteriaBuilder();
//		
//		CriteriaQuery<Cliente> configConsulta = cb.createQuery(Cliente.class);
//		Root<Cliente> raizClientes = configConsulta.from(Cliente.class);
//		configConsulta.select(raizClientes);
//		
//		List<Cliente> clientes = manager.createQuery(configConsulta).getResultList();
		
		@SuppressWarnings("unchecked")
		List<Cliente> clientes = manager.createNamedQuery("Cliente.findAll").getResultList();

		new ResponseEntity<List<Cliente>>(HttpStatus.OK);
		return ResponseEntity.ok(clientes);

	}
	
	@RequestMapping(value = "api/getAllProductos", method = RequestMethod.GET)
	public ResponseEntity<List<Producto>> getAllProductos() {
		
		@SuppressWarnings("unchecked")
		List<Producto> productos = manager.createNamedQuery("Producto.findAll").getResultList();

		new ResponseEntity<List<Cliente>>(HttpStatus.OK);
		return ResponseEntity.ok(productos);

	}	

}
