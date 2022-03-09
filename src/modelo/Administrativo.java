package modelo;

public class Administrativo {
	
	//ATRIBUTOS:
	private String dni;
	private String pasword;
	private String nombre;
	private String apellidos;
	
	
	
	//CONSTRUCTORES:
	public Administrativo() {};
	
	
	public Administrativo(String dni, String pasword, String nombre, String apellidos) {
		this.dni = dni;
		this.pasword = pasword;
		this.nombre = nombre;
		this.apellidos = apellidos;
	}
	
	
	public Administrativo(String dni, String pasword) {
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
}
