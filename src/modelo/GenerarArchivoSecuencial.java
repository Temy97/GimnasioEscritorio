package modelo;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/*
 * -Lunes: yoga a las 								8:00, 11:00 y 18:00
 * -Martes: gravity a las 							8:00 y las 16:00
 * -Miercoles:gravity a las 						10:00 y las 17:00
 * -Jueves: yoga a las 								9:00 y las 19:00
 * -Viernes: boxeo a las							8:00, 11:00 y 18:00
 * -Sabados: den este orden {gravity, yoga, boxeo}	8:00, 9:00, 10:00 y 16:00, 18:00 y 19:00
 */

public class GenerarArchivoSecuencial {
	
	//ATRIBUTOS:
	private static String nombre; //varchar(45)
	private static String[] dias = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"};
	private static String[] horas = {"08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "16:00", "17:00", "18:00", "19:00", "20:00"};
	
	private static File archivo = new File("datos\\horarios_secuenciales.dat");
	
	
	//METODOS:
	public static void main(String[] args) {
		try {
			if(archivo.exists()) {
				archivo.delete();
				archivo.createNewFile();
			}else {
				archivo.createNewFile();
			}
			
			RandomAccessFile raf = new RandomAccessFile(archivo, "rw");
			escribir(raf, dias[0], horas[0], "Yoga");
			escribir(raf, dias[0], horas[3], "Yoga");
			escribir(raf, dias[0], horas[8], "Yoga");
			
			escribir(raf, dias[1], horas[0], "Gravity");
			escribir(raf, dias[1], horas[6], "Gravity");
			
			escribir(raf, dias[2], horas[2], "Gravity");
			escribir(raf, dias[2], horas[7], "Gravity");
			
			escribir(raf, dias[3], horas[1], "Yoga");
			escribir(raf, dias[3], horas[9], "Yoga");
			
			escribir(raf, dias[4], horas[0], "Boxeo");
			escribir(raf, dias[4], horas[3], "Boxeo");
			escribir(raf, dias[4], horas[8], "Boxeo");
			
			escribir(raf, dias[5], horas[0], "Gravity");
			escribir(raf, dias[5], horas[1], "Yoga");
			escribir(raf, dias[5], horas[2], "Boxeo");
			escribir(raf, dias[5], horas[6], "Gravity");
			escribir(raf, dias[5], horas[7], "Yoga");
			escribir(raf, dias[5], horas[8], "Boxeo");
			
			int posLec = 0;
			raf.seek(posLec);
			while(raf.getFilePointer() != archivo.length()) {
				System.out.print(raf.readChar());
			}
			
		}catch(IOException e) {
			e.getLocalizedMessage();
		}
	}

	
	/**
	 * Escribe en el fichero los datos introducidos.
	 * @param raf
	 * @param dia
	 * @param hora
	 * @param actividad
	 */
	private static void escribir(RandomAccessFile raf, String dia, String hora, String actividad) {
		StringBuffer bufer_dia = new StringBuffer(dia);
		StringBuffer bufer_hora = new StringBuffer(hora);
		StringBuffer bufer_actividad = new StringBuffer(actividad);
		
		bufer_dia.setLength(10);
		bufer_hora.setLength(5);
		bufer_actividad.setLength(10);
		
		try {
			raf.writeChars(bufer_dia.toString());
			raf.writeChars(bufer_hora.toString());
			raf.writeChars(bufer_actividad.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		//para leer el fichero -> numero de chars * 2
	}

}
