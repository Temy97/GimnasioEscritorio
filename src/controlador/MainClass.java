package controlador;

import controlador.objetos.SuperClienteAdministrativo;
import modelo.objetosDAO.AdministrativoDAO;
import modelo.objetosDAO.ClienteDAO;
import controlador.objetos.Cliente;

import java.io.File;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;

import controlador.objetos.Administrativo;
import vista_administrativo.VentanaAdministrativo;
import vista_cliente.VentanaCliente;

public class MainClass {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		actualizarDBO();
	}
	
	
	/**
	 * Actualiza la base de datos embebido orientada a objetos
	 * con los datos extraidos de la base de datos SQL.
	 */
	private static void actualizarDBO() {
		File ficheroDB = new File("datos\\db_login.db");
		ficheroDB.delete();
		ODB dbo = ODBFactory.open(ficheroDB.getPath());
		
		Cliente[] clientes = ClienteDAO.todos_los_clientes_array();
		Administrativo[] administrativos = AdministrativoDAO.todos_los_admins_array();
		
		for (int i = 0; i < clientes.length; i++) {
			dbo.store(clientes[i]);
		}
		for (int i = 0; i < administrativos.length; i++) {
			dbo.store(administrativos[i]);
		}
		
		comprobarDBO(dbo);
		dbo.close();
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
