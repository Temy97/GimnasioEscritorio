package modelo.objetosDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import controlador.objetos.Clase;
import modelo.Singletone;

public class ClaseDAO {
	
	//METODOS:
	/**
	 * Metodo que introduce una nueva clase
	 * en la tabla clase de la base de datos.
	 * @param clase
	 * @return {@code true} si se ha insertado la clase, {@code false} en caso contrario.
	 */
	public static boolean insert_clase(Clase clase) {
		boolean insertado = false;
		
		String instruccion_insert = "INSERT INTO clase VALUES(?, ?, ?, ?)";
		
		try {
			PreparedStatement st = Singletone.getInstance().prepareStatement(instruccion_insert);
			st.setString(1, clase.getId());
			st.setString(2, clase.getNombre());
			st.setString(3, clase.getDescripcion());
			st.setString(4, clase.getDni_profesor());
			
			if(st.executeUpdate() == 1) {
				insertado = true;
			}
			
		}catch(SQLException e) {
			e.getLocalizedMessage();
		}
		
		return insertado;
	}
	
	
	/**
	 * Se borra de la base de datos aquella clase
	 * cuyo id coincida con el introducido como
	 * parametro.
	 * @param id
	 * @return
	 */
	public static boolean borrar_profesor(String id) {
		boolean borrado = false;
		
		if(id.length() == 3) {
			String instruccion_delete = "DELETE FROM clase WHERE dni LIKE '" + id + "';";
			
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
	 * Busca en la tabla clase de la base de datos la entrada correspondiente al
	 * id introducido como parametro, despues devuelve un objeto de tipo clase
	 * con los datos de dicha entrada, en caso de no encontrar una coincidencia
	 * se devuelve un objeto inicializado a {@code NULL}.
	 * @param id
	 * @return
	 */
	public static Clase buscar_clase(String id) {
		Clase clase = null;
		
		String consulta = "SELECT * FROM clase WHERE id LIKE '" + id + "';";
		
		try {
			ResultSet rs = Singletone.getInstance().createStatement().executeQuery(consulta);
			rs.next();
			clase = new Clase(id, rs.getString(2), rs.getString(3), rs.getString(4));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return clase;
	}
	
	
	/**
	 * Busca en la tabla clase de la base de datos la entrada correspondiente al
	 * nombre introducido como parametro, despues devuelve un objeto de tipo clase
	 * con los datos de dicha entrada, en caso de no encontrar una coincidencia
	 * se devuelve un objeto inicializado a {@code NULL}.
	 * @param nombre
	 * @return
	 */
	public static Clase buscar_clase_nombre(String nombre) {
		Clase clase = null;
		
		String consulta = "SELECT * FROM clase WHERE nombre LIKE '" + nombre + "';";
		
		try {
			ResultSet rs = Singletone.getInstance().createStatement().executeQuery(consulta);
			rs.next();
			clase = new Clase(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return clase;
	}
	
	
	/**
	 * Devuelve la clase encontrada con el dni del profesor indicado
	 * @param dni_profesor
	 * @return
	 */
	public static Clase buscar_clase_profesor(String dni_profesor) {
		Clase clase = null;
		
		String consulta = "SELECT * FROM clase WHERE dni_profesor LIKE '" + dni_profesor + "';";
		
		try {
			ResultSet rs = Singletone.getInstance().createStatement().executeQuery(consulta);
			rs.next();
			clase = new Clase(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return clase;
	}
	
	
	/**
	 * Se devuelve un {@code ArrayList} con todas
	 * las clases encontradas en la base de datos.
	 * @return
	 */
	public static ArrayList<Clase> todasLasClases() {
		ArrayList<Clase> lista = new ArrayList<Clase>();
		
		String consulta = "SELECT * FROM clase;";
		
		try {
			ResultSet rs = Singletone.getInstance().createStatement().executeQuery(consulta);
			while(rs.next())
				lista.add(new Clase(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lista;
	}
	
	
	/**
	 * Se borra de la base de datos aquel profesor
	 * cuyo dni coincida con el introducido como
	 * parametro.
	 * @param id
	 * @param id_nuevo
	 * @param nombre_nuevo
	 * @param descripcion_nueva
	 * @param dni_profesor_nuevo
	 * @return
	 */
	public static boolean editar_clase(String id, String id_nuevo, String nombre_nuevo, String descripcion_nueva, String dni_profesor_nuevo) {
		boolean modificado = false;
		
		String modificacion = "UPDATE clase SET id = '" + id_nuevo + "', nombre = '" + nombre_nuevo + "', descripcion = '" + descripcion_nueva + "', dni_profesor = '" + dni_profesor_nuevo + "' WHERE id LIKE '" + id + "';";
		
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
