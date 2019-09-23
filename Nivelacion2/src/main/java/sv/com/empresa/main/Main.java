package sv.com.empresa.main;

/*
 * Autor: Davis Vega
 * Fecha: 23/09/2019
 * Descripci�n: Clase encargada de consumir Api Rest y crear un mantenimiento de la clase Productos	  
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
		
		// Proceso para seleccionar que operaci�n desean realizar a Tabla Productos
		do {
			op = getMenu();
			
			//Evaluaci�n de la Opci�n
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
					System.out.println("*** FIN de la ejecuci�n ***");		
					break;	
				default: 
					System.out.println("*** Opci�n no valida !!! ***");			
					break;
			}
			
		}while(op != 0);
	}
	
	
	/*
	 * Method: getMenu()
	 * Description: M�todo encargado de generar el men� de opciones
	 * */
	private static int getMenu() {
		int op = 0;
		
		System.out.println("");
		System.out.println("*** Seleccione la opci�n que desea realizar ***");
		System.out.println("1. Consultar ");
		System.out.println("2. Agregar ");
		System.out.println("3. Eliminar ");
		System.out.println("4. Actualizar ");
		System.out.println("0. Salir ");
		System.out.print("Opci�n: ");
		op = sc.nextInt();
		
		return op;
	}
	
	/*
	 * Method: consultar()
	 * Description: M�todo encargado de listar todos los productos
	 * */
	private static void consultar() {
		Productos[] productos = rt.getForObject(url, Productos[].class);
		
		sc.nextLine();
		for(Productos prod: productos) {
			System.out.println("");
			System.out.println("C�digo: "+prod.getCodigo());
			System.out.println("Nombre: "+prod.getNombre());
			System.out.println("Cantidad: "+prod.getCantidad());
			System.out.println("Precio: "+prod.getPrecio());
			System.out.println("Fecha de vencimiento: "+prod.getVencimiento());
			System.out.println("Categor�a: "+prod.getCategoria());
			System.out.println("----------------------------------------------");
		}
	}
	
	/*
	 * Method: agregar()
	 * Description: M�todo encargado de agregar un producto
	 * */
	private static String agregar() {
		Productos p = new Productos();
		sc = new Scanner(System.in);
		
		System.out.println("Ingrese c�digo del producto");
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
		
		System.out.println("Ingrese la Categor�a del producto ( Alimentos, Limpieza, Cosmetico )");
		p.setCategoria(sc.nextLine());
		
		String s = rt.postForObject(url+"/Productos", p, String.class);
		return s;
	}
	
	/*
	 * Method: eliminar()
	 * Description: M�todo encargado de eliminar un producto
	 * */
	public static Boolean eliminar() {
		consultar();
		sc = new Scanner(System.in);
		
		try {
			System.out.println("\nIngrese c�digo del producto que desea eliminar");
			String codigo = sc.nextLine();
			
			rt.delete(url+"/"+codigo);
			return true;
			
		}catch(Exception e) {
			return false;
		}
	}
	
	
	/*
	 * Method: actualizar()
	 * Description: M�todo encargado de actualizar un producto
	 * */
	private static Boolean actualizar() {
		consultar();
		
		Productos p = new Productos();
		sc = new Scanner(System.in);
		
		try {
			System.out.println("Ingrese c�digo del producto que desea Actualizar");
			p.setCodigo(sc.nextLine());
			
			System.out.println("Ingrese Nombre del producto");
			p.setNombre(sc.nextLine());
			
			System.out.println("Ingrese Cantidad del producto");
			p.setCantidad(sc.nextInt());
			
			System.out.println("Ingrese Precio del producto");
			p.setPrecio(sc.nextDouble());
			
			sc.nextLine();
			p.setVencimiento(new Date());
			
			System.out.println("Ingrese la Categor�a del producto ( Alimentos, Limpieza, Cosmetico )");
			p.setCategoria(sc.nextLine());
			
			rt.put(url, p);
			return true;
		}catch(Exception e) {
			return false;	
		}
	}

}
