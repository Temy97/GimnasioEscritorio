package modelo.objetosDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import controlador.objetos.Cliente;
import modelo.Singletone;

public class ClienteDAO {
	
	//METODOS:
	/**
	 * Inserta un nuevo cliente en la tabla de clientes con los datos introducidos.
	 * @param cliente objeto de tipo cliente que referencia a la tupla que va a introducirse en la dbo.
	 * @return boolean que dice true si se ha introducido correctamente la tupla, false en caso opuesto.
	 */
	public static boolean insert_cliente(Cliente cliente) {
		boolean insertado = false;
		
		String instruccion_insert = "INSERT INTO cliente VALUES(?, ?, ?, ?, ?, ?, ?);";
		
		try {
			PreparedStatement st = Singletone.getInstance().prepareStatement(instruccion_insert);
			st.setString(1, cliente.getDni());
			st.setString(2, cliente.getPasword());
			st.setString(3, cliente.getNombre());
			st.setString(4, cliente.getApellidos());
			st.setInt(5, cliente.getEdad());
			st.setString(6, ((cliente.isEstado_empleo())?"S":"N"));
			st.setDouble(7, cliente.getCuota());
			
			if(st.executeUpdate() == 1) {
				insertado = true;
			}
			
		}catch(SQLServerException e) {
			e.getLocalizedMessage();
		
		}catch(SQLException e) {
			e.getLocalizedMessage();
		}
		
		return insertado;
	}
	
	
	/**
	 * Elimina al cliente con el mismo dni que se ha especificado como parametro.
	 * @param dni {@code String} del cliente que se desea eliminar de la base de datos.
	 * @return {@code boolean} que referencia a la eliminacion fructuosa del cliente.
	 */
	public static boolean borrar_cliente_por_dni(String dni) {
		boolean borrado = false;
		
		if(dni.length() == 9) {
			String instruccion_delete = "DELETE FROM cliente WHERE dni LIKE '" + dni + "';";
			
			try {
				PreparedStatement st = Singletone.getInstance().prepareStatement(instruccion_delete);
				
				if(st.executeUpdate() == 1) {
					borrado = true;
				}
			}catch(SQLException e) {
				e.getLocalizedMessage();
			}
		}
		
		return borrado;
	}
	
	
	/**
	 * Devulve el cliente correspondiente a los datos insertados;
	 * metodo recomendado para uso en 'log-in'.
	 * @param dni
	 * @param pasword
	 * @return Objeto de tipo: {@code Cliente}
	 */
	public static Cliente buscar_dni_pasword(String dni, String pasword) {
		Cliente cliente = null;
		
		String consulta = "SELECT * FROM cliente WHERE dni LIKE '" + dni + "' AND pasword LIKE '" + pasword + "';";
		
		try {
			ResultSet rs = Singletone.getInstance().createStatement().executeQuery(consulta);
			rs.next();
			cliente = new Cliente(dni, pasword, rs.getString(3), rs.getString(4), rs.getInt(5), ((rs.getString(6) == "S")? true:false), rs.getDouble(7));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cliente;
	}
	
	
	/**
	 * Modifica los datos del cliente que coincida con el dni especificado.
	 * @param dni
	 * @param dni_nuevo
	 * @param pasword_nuevo
	 * @param nombre_nuevo
	 * @param apellidos_nuevo
	 * @param edad_nueva
	 * @param estado_nuevo
	 * @param cuota_nueva
	 * @return
	 */
	public static boolean editar_datos_cliente(String dni, String dni_nuevo, String pasword_nuevo, String nombre_nuevo, String apellidos_nuevo, int edad_nueva, String estado_nuevo, double cuota_nueva) {
		boolean modificado = false;
		
		String modificacion = "UPDATE cliente SET dni = '" + dni_nuevo + "', pasword = '" + pasword_nuevo + "', nombre = '" + nombre_nuevo + "', apellidos = '" + apellidos_nuevo + "', edad = " + edad_nueva + ", estado_empleo = '" + estado_nuevo + "', cuota = " + cuota_nueva + "' WHERE dni LIKE '" + dni + "';";
		
		try {
			PreparedStatement st = Singletone.getInstance().prepareStatement(modificacion);
			
			if(st.executeUpdate() == 1) {
				modificado = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return modificado;
	}
}