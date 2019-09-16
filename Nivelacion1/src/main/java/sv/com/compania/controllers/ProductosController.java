/*
 * Autor: Davis Vega
 * Fecha: 13/09/2019
 * Descripción: Clase para controlar las peticiones del cliente 
 * */

package sv.com.compania.controllers;

import java.util.List;

import sv.com.compania.dao.ProductosDAO;
import sv.com.compania.models.Productos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sun.faces.action.RequestMapping;

@RestController
@RequestMapping("/Productos")
public class ProductosController {
	
	@Autowired
	ProductosDAO pDao;
	
	/*
	 * @Method: Método que retorna la lista de todos los productos
	 * */
	@GetMapping
	public List<Productos> index() {
		return pDao.lista();
	}

	/*
	 * @Method: Método que retorna la un producto especifico por Id(identificador)
	 * */
	@GetMapping("/{id}")
	public Productos index(@PathVariable String id) {
		return pDao.getById(id);
	}

	/*
	 * @Method: Método para agregar un nuevo elemento/entidad a la tabla productos
	 * */
	@PostMapping
	public Boolean index(@RequestBody Productos p) {
		return pDao.agregar(p);
	}
	
	/*
	 * @Method: Método para actualizar una entidad/Producto
	 * */
	@PutMapping
	public Boolean actualizar(@RequestBody Productos p) {
		return pDao.editar(p);
	}
	
	/*
	 * @Method: Método para eliminar un producto
	 * */
	@DeleteMapping("/{id}")
	public Boolean eliminar(@PathVariable String id) {
		return pDao.eliminar(id);
	}
}
