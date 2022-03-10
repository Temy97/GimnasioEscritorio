package modelo.objetosDAO;

import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import controlador.objetos.Administrativo;
import modelo.Singletone;

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
	
	
	public static void eliminar_varios_admin_por_dni(String... strings) {
		
	}
}
