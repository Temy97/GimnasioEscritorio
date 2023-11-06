package modelo.objetosDAO;

import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import controlador.servicios.Singletone;
import modelo.objetos.Administrativo;
import modelo.objetos.Cliente;

public class AdministrativoDAO {
	
	//METODOS:
	/**
	 * Inserta un nuevo administrativo en la tabla de administrativos,
	 * en el caso de que el objeto recibido por parametro careciera de
	 * los atributos nombre y apellidos, se les asignarian los valores
	 * 'default' es pecificados en la creacion de la base de datos. En el
	 * caso de no tener un 'default' asignado se envia un 'NULL'.
	 * @param admin objeto de tipo administrativo que referencia a la tupla que va a introducirse en la dbo.
	 * @return boolean que dice true si se ha introducido correctamente la tupla, false en caso opuesto.
	 */
	public static boolean insert_admin(Administrativo admin) {
		boolean insertado = false;
		
		String instruccion_insert = "INSERT INTO administrativo VALUES(?, ?, ?, ?);";
		
		try {
			PreparedStatement st = Singletone.getInstance().prepareStatement(instruccion_insert);
			st.setString(1, admin.getDni());
			st.setString(2, admin.getPasword());
			
			if(admin.getNombre() == null) {
				DatabaseMetaData dbmd = Singletone.getInstance().getMetaData();
				
				ResultSet columnas = dbmd.getColumns("gimnasio_db", "dbo", "administrativo", null);
				while(columnas.next()) {
					if(columnas.getString("COLUMN_NAME").equals("nombre")) {
						st.setString(3, columnas.getString("COLUMN_DEF").substring(2, (columnas.getString("COLUMN_DEF").length() - 2)));
					}
					
					if(columnas.getString("COLUMN_NAME").equals("apellidos")) {
						st.setString(4, columnas.getString("COLUMN_DEF"));
					}
				}
				
			}else {
				st.setString(3, admin.getNombre());
				st.setString(4, admin.getApellidos());
			}
			
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
	 * Este metodo elimina al administrativo con el mismo dni especificado
	 * por parametro, devolviendo un boolean correspondiente a si se ha
	 * logrado efectuar el borrado o no.
	 * @param dni {@code String} del administrativo que se desea eliminar de la base de datos.
	 * @return {@code boolean} que referencia a la eliminacion fructuosa del administrativo.
	 */
	public static boolean borrar_admin_por_dni(String dni) {
		boolean borrado = false;
		
		if(dni.length() == 9 /*controlador.Utiles.comprobar_dni(dni)*/) {
			String instruccion_delete = "DELETE FROM administrativo WHERE dni LIKE '" + dni + "';";
			
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
	 * Este metodo elimina todos los usuarios con los dni coincidentes con los pasados por parametro,
	 * devolviendo un número correspondiente a las tuplas afectadas, si se pasan valores inv�lidos
	 * no salta excepcion, solo indica las filas afectadas.
	 * @param strings
	 * @return int correspondiente al numero de filas afectadas.
	 * @throws SQLException
	 */
	public static int eliminar_varios_admin_por_dni(String... strings) throws SQLException {
		String instruccion_delete = "DELETE FROM administrativo WHERE dni LIKE '";
		
		for (int i = 0; i < (strings.length - 1); i++) {
			instruccion_delete = instruccion_delete + strings[i] + "' OR dni LIKE '";
		}
		instruccion_delete = instruccion_delete + strings[strings.length-1] + "';";
		
		//la instruccion tambien seria valida de la siguiente forma: DELETE FROM administrativo WHERE dni IN ('...', '...', ...);
		
		PreparedStatement st = Singletone.getInstance().prepareStatement(instruccion_delete);
		
		return st.executeUpdate();
	}
	
	
	/**
	 * Devulve el administrativo correspondiente a los datos insertados;
	 * metodo recomendado para uso en 'log-in'.
	 * @param dni
	 * @param pasword
	 * @return Objeto de tipo: {@code Administrativo}
	 */
	public static Administrativo buscar_dni_pasword(String dni, String pasword) {
		Administrativo admin = null;
		
		String consulta = "SELECT * FROM administrativo WHERE dni LIKE '" + dni + "' AND pasword LIKE '" + pasword + "';";
		
		try {
			ResultSet rs = Singletone.getInstance().createStatement().executeQuery(consulta);
			rs.next();
			admin = new Administrativo(dni, pasword, rs.getString(3), rs.getString(4));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return admin;
	}
	
	
	/**
	 * Se extraen los datos de todos los administrativos
	 * en forma de ArrayList.
	 * @return ArrayList<Administrativo>
	 */
	public static ArrayList<Administrativo> todos_los_admins() {
		ArrayList<Administrativo> admin = new ArrayList<Administrativo>();
		
		String consulta = "SELECT * FROM administrativo;";
		
		try {
			ResultSet rs = Singletone.getInstance().createStatement().executeQuery(consulta);
			while(rs.next())
				admin.add(new Administrativo(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return admin;
	}
	
	
	/**
	 * Se retorna un array con todos los
	 * administrativos encontrados en la base de datos.
	 * @return
	 */
	public static Administrativo[] todos_los_admins_array() {
		ArrayList<Administrativo> adminsList = todos_los_admins();
		Administrativo[] admins = new Administrativo[adminsList.size()];
		
		for (int i = 0; i < admins.length; i++) {
			admins[i] = adminsList.get(i);
		}
		
		return admins;
	}
	
	
	/**
	 * Se modifican todos los datos del administrativo que coincida con el dni y el pasword
	 * especificados en los dos primero parametros.
	 * @param dni
	 * @param pasword
	 * @param dni_nuevo
	 * @param pasword_nuevo
	 * @param nombre_nuevo
	 * @param apellidos_nuevo
	 * @return {@code boolean} correspondiente a la ejecucion del comando.
	 */
	public static boolean editar_datos_administrativo(String dni, String pasword, String dni_nuevo, String pasword_nuevo, String nombre_nuevo, String apellidos_nuevo) {
		boolean modificado = false;
		
		String modificacion = "UPDATE administrativo SET dni = '" + dni_nuevo + "', pasword = '" + pasword_nuevo + "', nombre = '" + nombre_nuevo + "', apellidos = '" + apellidos_nuevo + "' WHERE dni LIKE '" + dni + "' AND pasword LIKE '" + pasword + "';";
		
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
