package modelo;

public class Profesor {

	//ATRIBUTOS:
		private String dni;
		private String nombre;
		private double salario;
		
		
		
		//CONSTRUCTORES:
		public Profesor() {};
		
		
		public Profesor(String dni, String nombre, double salario) {
			this.setDni(dni);
			this.setNombre(nombre);
			this.setSalario(salario);
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
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public double getSalario() {
			return salario;
		}
		public void setSalario(double salario) {
			this.salario = salario;
		}
}
