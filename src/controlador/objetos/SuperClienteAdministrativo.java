package controlador.objetos;

public class SuperClienteAdministrativo {
	
	//TRIBUTOS:
	private String dni;
	private String nombre;
	private String apellidos;
	
	
	//CONSTRUCTOR:
	public SuperClienteAdministrativo() {}
	
	public SuperClienteAdministrativo(String dni, String nombre, String apellidos) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
	}
	
	
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
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
}
