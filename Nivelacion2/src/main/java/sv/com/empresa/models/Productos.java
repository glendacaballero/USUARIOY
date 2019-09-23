package sv.com.empresa.models;

/*
 * Autor: Davis Vega
 * Fecha: 23/09/2019
 * Descripción: Clase Productos, que se convierte en la entidad a persistir con la base de datos.	  
 * */
import java.util.Date;

public class Productos {
	
	private String codigo;
	private String nombre;
	private int cantidad;
	private double precio;
	private Date vencimiento;
	private String categoria;
	
	
	/* 
	 * Métodos GET y SET de los tributos
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
