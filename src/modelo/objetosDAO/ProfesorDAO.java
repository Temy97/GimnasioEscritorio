package modelo.objetosDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controlador.servicios.Singletone;
import modelo.objetos.Administrativo;
import modelo.objetos.Profesor;

public class ProfesorDAO {
	
	//METODOS:
	/**
	 * Metodo que introduce un nuevo profesor
	 * en la tabla profesor de la base de datos.
	 * @param profesor
	 * @return {@code true} si se ha insertado el profesor, {@code false} en caso contrario.
	 */
	public static boolean insert_profesor(Profesor profesor) {
		boolean insertado = false;
		
		String instruccion_insert = "INSERT INTO profesor VALUES(?, ?, ?)";
		
		try {
			PreparedStatement st = Singletone.getInstance().prepareStatement(instruccion_insert);
			st.setString(1, profesor.getDni());
			st.setString(2, profesor.getNombre());
			st.setDouble(3, profesor.getSalario());
			
			if(st.executeUpdate() == 1) {
				insertado = true;
			}
			
		}catch(SQLException e) {
			e.getLocalizedMessage();
		}
		
		return insertado;
	}
	
	
	/**
	 * Se borra de la base de datos aquel profesor
	 * cuyo dni coincida con el introducido como
	 * parametro.
	 * @param dni
	 * @return
	 */
	public static boolean borrar_profesor(String dni) {
		boolean borrado = false;
		
		if(dni.length() == 9) {
			String instruccion_delete = "DELETE FROM profesor WHERE dni LIKE '" + dni + "';";
			
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
	 * Busca en la tabla profesor de la base de datos la entrada correspondiente al
	 * dni introducido como parametro, despues devuelve un objeto de tipo profesor
	 * con los datos de dicha entrada, en caso de no encontrar una coincidencia
	 * se devuelve un objeto inicializado a {@code NULL}.
	 * @param dni
	 * @return
	 */
	public static Profesor buscar_profesor(String dni) {
		Profesor profesor = null;
		
		String consulta = "SELECT * FROM profesor WHERE dni LIKE '" + dni + "';";
		
		try {
			ResultSet rs = Singletone.getInstance().createStatement().executeQuery(consulta);
			rs.next();
			profesor = new Profesor(dni, rs.getString(2), rs.getDouble(3));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return profesor;
	}
	
	/**
	 * Se devuelve un ArrayList con todos los
	 * profesores de la base de datos.
	 * @return ArrayList<Profesor>
	 */
	public static ArrayList<Profesor> todos_los_profesores(){
		ArrayList<Profesor> profesor = new ArrayList<Profesor>();

		String consulta = "SELECT * FROM profesor;";

		try {
			ResultSet rs = Singletone.getInstance().createStatement().executeQuery(consulta);
			while(rs.next())
				profesor.add(new Profesor(rs.getString(1), rs.getString(2), rs.getDouble(3)));

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return profesor;
	}
	
	
	/**
	 * Se modifican los datos de la base de datos
	 * coincidentes con el primer parametro recibido
	 * por el medoto.
	 * @param dni
	 * @param dni_nuevo
	 * @param nombre_nuevo
	 * @param salario_nuevo
	 * @return
	 */
	public static boolean editar_profesor(String dni, String dni_nuevo, String nombre_nuevo, double salario_nuevo) {
		boolean modificado = false;
		
		String modificacion = "UPDATE profesor SET dni = '" + dni_nuevo + "', nombre = '" + nombre_nuevo + "', salario = '" + salario_nuevo + "' WHERE dni LIKE '" + dni + "';";
		
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
