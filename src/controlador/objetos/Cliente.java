package controlador.objetos;

public class Cliente {
	
	//ATRIBUTOS:
		private String dni;
		private String pasword;
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
		//
		
		
		
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
