/*
 * Autor: Davis Vega
 * Fecha: 13/09/2019
 * Descripci�n: Clase Productos, que se convierte en la entidad a persistir con la base de datos.	  
 * */
package sv.com.compania.models;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Productos {
	
	//Atributos de la clase entidad productos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String codigo;
	private String nombre;
	private int cantidad;
	private double precio;
	private Date vencimiento;
	private String categoria;
	
	
	/* 
	 * M�todos GET y SET de los tributos
	 * Get, nos retonan los valores de los atributos
	 * Set, asignamos nuevos valores a los atributos
	 */
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public Date getVencimiento() {
		return vencimiento;
	}
	public void setVencimiento(Date vencimiento) {
		this.vencimiento = vencimiento;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
}
