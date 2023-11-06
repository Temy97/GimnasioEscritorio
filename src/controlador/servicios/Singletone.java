package controlador.servicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Singletone {
	
private static Connection conector;
	
	private Singletone() {}
	
	@SuppressWarnings("finally")
	public synchronized static Connection getInstance() {
		try {
			if(conector == null) {
				conector = DriverManager.getConnection("jdbc:sqlserver://;database=gimnasio_db;", "sa", "dam2t");
			}else {
				throw new IllegalArgumentException("Error en la creacion de la clase singletone.");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			return conector;
		}
	}
	
	public synchronized static void cierre() {
		if(conector != null) {
			try {
				conector.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
