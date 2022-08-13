package modelo.objetosDAO;

import java.util.ArrayList;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;

import controlador.objetos.Administrativo;
import controlador.objetos.Cliente;

public class NeodatisDAO {
	
	//METODOS:
	/**
	 * Se genera y devuelve un ArrayList con
	 * los clientes encontrados en la base de datos.
	 * @return
	 */
	public static ArrayList<Cliente> listaClientes(){
		ArrayList<Cliente> array_clientes = new ArrayList<Cliente>();
		
		ODB base_datos = ODBFactory.open("datos\\dn_login.DB");
		Objects<Cliente> clientes = base_datos.getObjects(Cliente.class);
		
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
		
		ODB base_datos = ODBFactory.open("datos\\dn_login.DB");
		Objects<Administrativo> administrativo = base_datos.getObjects(Administrativo.class);
		
		while(administrativo.hasNext()) {
			array_administrativos.add((Administrativo) administrativo.next());
		}
		
		return array_administrativos;
	}
}
