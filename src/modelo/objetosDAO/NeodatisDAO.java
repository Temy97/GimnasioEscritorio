package modelo.objetosDAO;

import java.util.ArrayList;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;

import controlador.objetos.Administrativo;
import controlador.objetos.Cliente;

public class NeodatisDAO {
	//ATRIBUTOS:
	public static final ODB BASE_DATOS = ODBFactory.open("datos\\db_login.DB");
	
	//METODOS:
	/**
	 * Se genera y devuelve un ArrayList con
	 * los clientes encontrados en la base de datos.
	 * @return
	 */
	public static ArrayList<Cliente> listaClientes(){
		ArrayList<Cliente> array_clientes = new ArrayList<Cliente>();
		
		Objects<Cliente> clientes = BASE_DATOS.getObjects(Cliente.class);
		
		while(clientes.hasNext()) {
			array_clientes.add((Cliente) clientes.next());
		}
		
		return array_clientes;
	}
	
	
	/**
	 * Se genera y devuelve un ArrayList con
	 * los administrativos encontrados en la base de datos.
	 * @return
	 */
	public static ArrayList<Administrativo> listaAdministrativos(){
		ArrayList<Administrativo> array_administrativos = new ArrayList<Administrativo>();
		
		Objects<Administrativo> administrativo = BASE_DATOS.getObjects(Administrativo.class);
		
		while(administrativo.hasNext()) {
			array_administrativos.add((Administrativo) administrativo.next());
		}
		
		return array_administrativos;
	}
}
