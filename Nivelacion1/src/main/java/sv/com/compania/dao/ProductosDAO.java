/*
 * Autor: Davis Vega
 * Fecha: 13/09/2019
 * Descripción: Clase contenedora de los métodos que afectan directamente la entidad producto
 * agregar, actualizar, listar, eliminar	  
 * */

package sv.com.compania.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import sv.com.compania.models.Productos;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductosDAO {
	
	@PersistenceContext
	EntityManager em; // Clase que nos ayuda a persistir las entidades.
	
	
	/*
	 * @Method: Método que retorna la lista de todos los productos
	 * */
	@SuppressWarnings("unchecked")
	public List<Productos> lista(){
		return em.createQuery("SELECT p FROM Productos p").getResultList();
	}
	
	/*
	 * @Method: Método que retorna la un producto especifico por Id(identificador)
	 * */
	public Productos getById(String id) {
		return em.find(Productos.class, id);
	}
	
	
	/*
	 * @Method: Método para agregar un nuevo elemento/entidad a la tabla productos
	 * */
	public Boolean agregar(Productos p) {
		em.persist(p);
		return  true;
	}
	
	
	/*
	 * @Method: Método para actualizar una entidad/Producto
	 * */
	public Boolean editar(Productos p) {
		em.merge(p);
		return true;
	}
	
	
	/*
	 * @Method: Método para eliminar un producto
	 * */
	public Boolean eliminar(String id) {
		Productos p = em.find(Productos.class, id);
		p = em.merge(p);
		em.remove(p);
		return true;
	}
}
