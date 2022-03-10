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
}
