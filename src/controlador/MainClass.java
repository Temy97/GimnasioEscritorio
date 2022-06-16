package controlador;

import controlador.objetos.SuperClienteAdministrativo;
import controlador.objetos.Cliente;
import controlador.objetos.Administrativo;
import vista_administrativo.VentanaAdministrativo;
import vista_cliente.VentanaCliente;

public class MainClass {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	/**
	 * Actualiza la base de datos embebido orientada a objetos
	 * con los datos extraidos de la base de datos SQL.
	 */
	private static void actualizarDBO() {
		
	}
	
	
	/**
	 * Se llama a la clase con la interfaz grafica
	 * referida al Log-In, para que se loguee el clilente
	 * o administrativo, se retorna un booleano equivalente
	 * a -> {@code true} == cliente // {@code false} == administrativo.
	 * @return
	 */
	private static boolean llamarLogIn() {
		return false;
	}
	
	
	/**
	 * Si se recibe como parametro {@code true}
	 * se muestra la aplicacion correspondiente
	 * al cliente y en caso contrario se
	 * muestra la aplicacion del administrativo.
	 * @param vista
	 */
	private static void llamarVista(boolean vista, SuperClienteAdministrativo super_clase_datos) {
		if(vista == true) {
			new VentanaCliente((Cliente) super_clase_datos);
		}else {
			new VentanaAdministrativo((Administrativo) super_clase_datos);
		}
	}
}
