package controlador;

import java.util.regex.Pattern;

public class Utiles {
	
	//METODOS:
	/**
	 * Metodo que recibe una cadena, comprueba y valida
	 * que dicha cadena se un dni valido, tanto en tamaño
	 * y forma como en letra correspondiente al numero.
	 * @param dni
	 * @return
	 */
	public static boolean comprobar_dni(String dni) {
		boolean valido = false;
		
		if(dni.length() == 9 && Pattern.matches("\\d{8}[A-Z]{1}", dni)) {
			int num = Integer.parseInt(dni.substring(0, 8));
			char letra = dni.charAt(dni.length() - 1);
			char[] letras = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 
					'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};
			if(letra == letras[num%23]) {
				valido = true;
			}
		}
		
		return valido;
	}
	
	
	/**
	 * Comprueba que la cadena pueda se un dni,
	 * no se comprueba que la letra corresponda
	 * al numero.
	 * @param dni
	 * @return
	 */
	public static boolean comprobar_dni_sin_coincidencia(String dni) {
		boolean valido = false;
		
		if(dni.length() == 9 && Pattern.matches("\\d{8}[A-Z]{1}", dni)) {
			valido = true;
		}
		
		return valido;
	}
	
	
	/**
	 * devuelve el char correspondiente al estado
	 * del cliente. S = true, N = false.
	 * @param str
	 */
	public static boolean esEstadoChar(String str) {
		if(str.equals("S")) {
			return true;
		}else {
			return false;
		}
	}
	
	
	/**
	 * Comprueba si la cadena de texto es un entero
	 * @param str
	 * @return
	 */
	public static boolean isInt(String str) {
		try {
			Integer.parseInt(str);
			return true;
		}catch(NumberFormatException e) {
			return false;
		}
	}
	
	
	/**
	 * Comprueba si la cadena de texto es un {@code Double}
	 * @param str
	 * @return
	 */
	public static boolean isDouble(String str) {
		try {
			Double.parseDouble(str);
			return true;
		}catch(NumberFormatException e) {
			return false;
		}
	}
	
	
	/**
	 * codificador sencillo.
	 * @param pasword
	 * @return
	 */
	public static String codificador(String pasword) {
		return "$$" + pasword + "$$";
	}
}
