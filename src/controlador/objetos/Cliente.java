package controlador.objetos;

public class Cliente {
	
	//ATRIBUTOS:
		private String dni;
		private String pasword; //tiene un default 'BIENVENIDO'
		private String nombre;
		private String apellidos;
		private int edad;
		private boolean estado_empleo; //para la dbo es un char 'S'/'N'
		private double cuota;
		
		
		
		//CONSTRUCTORES:
		public Cliente() {};
		
		
		public Cliente(String dni, String pasword, String nombre, String apellidos, int edad, boolean estado_empleo, double cuota) {
			this.dni = dni;
			this.pasword = pasword;
			this.nombre = nombre;
			this.apellidos = apellidos;
			this.edad = edad;
			this.estado_empleo = estado_empleo;
			this.cuota = cuota;
		}
		
		
		public Cliente(String dni, String pasword) {
			this.dni = dni;
			this.pasword = pasword;
		}
		
		
		
		//METODOS:
		@Override
		public String toString() {
			return "Nombre: " + nombre + ", apellidos: " + apellidos + ", dni: " + dni + ", pasword: " + pasword + ", edad: " + edad + ", es trabajador/desempleado -> " + estado_empleo + ", cuota: " + cuota + "€.";
		}
		
		
		/**
		 * Devuelve el atributo estado_empleo en forma de
		 * {@code String}, siento "S" para true y "N" para false.
		 * @return
		 */
		public String isEstadoChar() {
			if(estado_empleo == true) {
				return "S";
			}else {
				return "N";
			}
		}
		
		
		/**
		 * Da el valor true o false al atributo estado_empleo,
		 * siendo true el equivalente de "S" y siendo, a su vez,
		 * false el equivalente a "N", en el resto de casos
		 * salta una excepcion.
		 * @param str
		 * @throws IllegalArgumentException
		 */
		public void setEstadoChar(String str) {
			if(str.equals("S")) {
				this.estado_empleo = true;
			}else if(str.equals("N")){
				this.estado_empleo = false;
			}else {
				throw new IllegalArgumentException();
			}
		}
		
		
		/**
		 * Se introduce un char y lo convierte a boolean. <br>
		 * 'S' -> TRUE ---- 'N' -> FALSE, otro = ERROR (IllegalArgumentException).
		 * @param str
		 * @return
		 */
		public static boolean generarEstado(String str) {
			if(str.equals("S")) {
				return true;
			}else if(str.equals("N")){
				return false;
			}else {
				throw new IllegalArgumentException();
			}
		}
		
		
		
		//GET-SET:
		public String getDni() {
			return dni;
		}
		public void setDni(String dni) {
			this.dni = dni;
		}
		public String getPasword() {
			return pasword;
		}
		public void setPasword(String pasword) {
			this.pasword = pasword;
		}
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public String getApellidos() {
			return apellidos;
		}
		public void setApellidos(String apellidos) {
			this.apellidos = apellidos;
		}
		public int getEdad() {
			return edad;
		}
		public void setEdad(int edad) {
			this.edad = edad;
		}
		public boolean isEstado_empleo() {
			return estado_empleo;
		}
		public void setEstado_empleo(boolean estado_empleo) {
			this.estado_empleo = estado_empleo;
		}
		public double getCuota() {
			return cuota;
		}
		public void setCuota(double cuota) {
			this.cuota = cuota;
		}
}
