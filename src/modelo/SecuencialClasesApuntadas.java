package modelo;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;


/**
 * Esta clase guarda las clases a las que cada usuario
 * se ha apuntado, se realiza en ficheros secuenciales.<br>
 * El orden en que se guardan los datos es:<br>
 * ·fecha (dd/mm/aa) [10 chars]<br>
 * ·hora (hh:mm) [5chars]<br>
 * ·nombre de la clase [10 chars]<br>
 * {@code total chars: 20 + 10 + 20 = 50}<br>
 * <br>
 * Dicho fichero se crea bajo el nombre:
 * "ClasesApuntadas" + [dni del usuario] + ".dat".
 * @author david
 *
 */
public class SecuencialClasesApuntadas {
	
	//Metodos:
	/**
	 * Se añade una nueva entrada al fichero donde
	 * se almacenan las clases apuntadas de cada usuario.
	 * @param clase
	 * @param dni
	 * @param hora
	 */
	public static void guardarClase(String clase, String dni, String hora) {
		String fecha = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		
		File fichero = new File("datos\\ClasesApuntadas" + dni + ".dat");
		try {
			fichero.createNewFile();
			
			RandomAccessFile raf = new RandomAccessFile(fichero, "rw");
			raf.seek(fichero.length());
			escribir(raf, fecha, hora, clase);
			
		} catch(IOException e) {
			JOptionPane.showMessageDialog(null, "No se pudieron guardar los datos solicitados.", "", JOptionPane.WARNING_MESSAGE, null);
		}
	}
	
	
	/**
	 * Añade la clase apuntada junto con la fecha y hora
	 * al archivo donde se almacenan los datos del historial
	 * de clases apuntadas del usuario.
	 * @param raf
	 * @param dia
	 * @param hora
	 * @param actividad
	 */
	private static void escribir(RandomAccessFile raf, String fecha, String hora, String actividad) {
		StringBuffer bufer_fecha = new StringBuffer(fecha);
		StringBuffer bufer_hora = new StringBuffer(hora);
		StringBuffer bufer_actividad = new StringBuffer(actividad);
		
		bufer_fecha.setLength(10);
		bufer_hora.setLength(5);
		bufer_actividad.setLength(10);
		
		try {
			raf.writeChars(bufer_fecha.toString());
			raf.writeChars(bufer_hora.toString());
			raf.writeChars(bufer_actividad.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
