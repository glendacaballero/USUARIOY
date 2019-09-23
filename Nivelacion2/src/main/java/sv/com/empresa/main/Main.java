package sv.com.empresa.main;

/*
 * Autor: Davis Vega
 * Fecha: 23/09/2019
 * Descripción: Clase encargada de consumir Api Rest y crear un mantenimiento de la clase Productos	  
 * */

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import org.springframework.web.client.RestTemplate;
import sv.com.empresa.models.Productos;

public class Main {
	private static Scanner sc = new Scanner(System.in);
	private static RestTemplate rt = new RestTemplate(); 
	private static String url = "http://localhost:8080/PruebaJavaEjercicio1";
	
	public static void main(String[] args) {
		int op = 0;
		
		// Proceso para seleccionar que operación desean realizar a Tabla Productos
		do {
			op = getMenu();
			
			//Evaluación de la Opción
			switch(op) {
				//Listar Productos
				case 1: 
					consultar();
					break;
				
				//Agregar Producto
				case 2:
					
					if(!agregar().equals(""))
						System.out.println("*** Producto Agregado Exitosamente");
					else
						System.out.println("*** Producto No se pudo agregar !!!");
					
					break;
					
				//Eliminar Producto
				case 3:
					
					if(eliminar())
						System.out.println("*** Producto Eliminado Exitosamente");
					else
						System.out.println("*** Producto No se pudo Eliminar !!!");
					
					break;
					
				//Actualizar Productos	
				case 4:
					
					if(actualizar())
						System.out.println("*** Producto Actualizado Exitosamente");
					else
						System.out.println("*** Producto No se pudo Actualizar !!!");
					
					break;
				case 0:
					System.out.println("*** FIN de la ejecución ***");		
					break;	
				default: 
					System.out.println("*** Opción no valida !!! ***");			
					break;
			}
			
		}while(op != 0);
	}
	
	
	/*
	 * Method: getMenu()
	 * Description: Método encargado de generar el menú de opciones
	 * */
	private static int getMenu() {
		int op = 0;
		
		System.out.println("");
		System.out.println("*** Seleccione la opción que desea realizar ***");
		System.out.println("1. Consultar ");
		System.out.println("2. Agregar ");
		System.out.println("3. Eliminar ");
		System.out.println("4. Actualizar ");
		System.out.println("0. Salir ");
		System.out.print("Opción: ");
		op = sc.nextInt();
		
		return op;
	}
	
	/*
	 * Method: consultar()
	 * Description: Método encargado de listar todos los productos
	 * */
	private static void consultar() {
		Productos[] productos = rt.getForObject(url, Productos[].class);
		
		sc.nextLine();
		for(Productos prod: productos) {
			System.out.println("");
			System.out.println("Código: "+prod.getCodigo());
			System.out.println("Nombre: "+prod.getNombre());
			System.out.println("Cantidad: "+prod.getCantidad());
			System.out.println("Precio: "+prod.getPrecio());
			System.out.println("Fecha de vencimiento: "+prod.getVencimiento());
			System.out.println("Categoría: "+prod.getCategoria());
			System.out.println("----------------------------------------------");
		}
	}
	
	/*
	 * Method: agregar()
	 * Description: Método encargado de agregar un producto
	 * */
	private static String agregar() {
		Productos p = new Productos();
		sc = new Scanner(System.in);
		
		System.out.println("Ingrese código del producto");
		p.setCodigo(sc.nextLine());
		
		System.out.println("Ingrese Nombre del producto");
		p.setNombre(sc.nextLine());
		
		System.out.println("Ingrese Cantidad del producto");
		p.setCantidad(sc.nextInt());
		
		System.out.println("Ingrese Precio del producto");
		p.setPrecio(sc.nextDouble());
		
		sc.nextLine();
		// new SimpleDateFormat("yyyy-MM-dd").format(new Date())
		// System.out.println("Ingrese Fecha de Vencimiento del producto (Y-M-D)");
		p.setVencimiento(new Date());
		
		System.out.println("Ingrese la Categoría del producto ( Alimentos, Limpieza, Cosmetico )");
		p.setCategoria(sc.nextLine());
		
		String s = rt.postForObject(url+"/Productos", p, String.class);
		return s;
	}
	
	/*
	 * Method: eliminar()
	 * Description: Método encargado de eliminar un producto
	 * */
	public static Boolean eliminar() {
		consultar();
		sc = new Scanner(System.in);
		
		try {
			System.out.println("\nIngrese código del producto que desea eliminar");
			String codigo = sc.nextLine();
			
			rt.delete(url+"/"+codigo);
			return true;
			
		}catch(Exception e) {
			return false;
		}
	}
	
	
	/*
	 * Method: actualizar()
	 * Description: Método encargado de actualizar un producto
	 * */
	private static Boolean actualizar() {
		consultar();
		
		Productos p = new Productos();
		sc = new Scanner(System.in);
		
		try {
			System.out.println("Ingrese código del producto que desea Actualizar");
			p.setCodigo(sc.nextLine());
			
			System.out.println("Ingrese Nombre del producto");
			p.setNombre(sc.nextLine());
			
			System.out.println("Ingrese Cantidad del producto");
			p.setCantidad(sc.nextInt());
			
			System.out.println("Ingrese Precio del producto");
			p.setPrecio(sc.nextDouble());
			
			sc.nextLine();
			p.setVencimiento(new Date());
			
			System.out.println("Ingrese la Categoría del producto ( Alimentos, Limpieza, Cosmetico )");
			p.setCategoria(sc.nextLine());
			
			rt.put(url, p);
			return true;
		}catch(Exception e) {
			return false;	
		}
	}

}
