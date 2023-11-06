package controlador;

import modelo.objetos.Administrativo;
import modelo.objetos.Clase;
import modelo.objetos.Cliente;
import modelo.objetos.SuperClienteAdministrativo;
import modelo.objetosDAO.AdministrativoDAO;
import modelo.objetosDAO.ClaseDAO;
import modelo.objetosDAO.ClienteDAO;
import modelo.objetosDAO.NeodatisDAO;
import vista.vista_administrativo.VentanaAdministrativo;
import vista.vista_cliente.VentanaCliente;
import vista.vista_login.VistaLogIn;

import java.io.File;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;

public class MainClass {
	
	public static void main(String[] args) {
		actualizarDBO();
		new VistaLogIn();
	}
	
	
	/**
	 * Actualiza la base de datos embebida orientada a objetos
	 * con los datos extraidos de la base de datos SQL.
	 */
	private static void actualizarDBO() {
		File ficheroDB = new File("datos\\db_login.DB");//si el archivo que creamos usamos la extension '.db' en minusculas en vez de mayusculas ('.DB') se generan archivos adicionales
		ficheroDB.delete();
		ODB dbo = NeodatisDAO.BASE_DATOS;
		
		Cliente[] clientes = ClienteDAO.todos_los_clientes_array();
		Administrativo[] administrativos = AdministrativoDAO.todos_los_admins_array();
		
		for (int i = 0; i < clientes.length; i++) {
			dbo.store(clientes[i]);
		}
		for (int i = 0; i < administrativos.length; i++) {
			dbo.store(administrativos[i]);
		}
		
		//comprobarDBO(dbo);
		//dbo.close();
	}
	
	
	/**
	 * Se muestran los clientes y administrativos
	 * que hay almacenados en la base de datos
	 * orientada a objetos por consola.
	 * @param dbo
	 */
	private static void comprobarDBO(ODB dbo) {
		Objects<Cliente> clientes = dbo.getObjects(Cliente.class);
		Objects<Administrativo> admins = dbo.getObjects(Administrativo.class);
		
		while(clientes.hasNext()) {
			Cliente cliente = clientes.next();
			System.out.println(cliente.toString());
		}
		while(admins.hasNext()) {
			Administrativo admin = admins.next();
			System.out.println(admin.toString());
		}
	}
	
	
	/**
	 * Se llama a la clase con la interfaz grafica
	 * referida al Log-In, para que se loguee el clilente
	 * o administrativo, se retorna un booleano equivalente
	 * a: -> {@code true} == cliente // {@code false} == administrativo.
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
