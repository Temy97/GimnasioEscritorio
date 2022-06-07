package controlador.objetos;

public class Administrativo extends SuperClienteAdministrativo{
	
	//ATRIBUTOS:
	//private String dni;
	private String pasword;
	//private String nombre;
	//private String apellidos;
	
	
	
	//CONSTRUCTORES:
	public Administrativo() {};
	
	
	public Administrativo(String dni, String pasword, String nombre, String apellidos) {
		super.setDni(dni);
		this.pasword = pasword;
		super.setNombre(nombre);
		super.setApellidos(apellidos);
	}
	
	
	/*public Administrativo(String dni, String pasword) {
		this.dni = dni;
		this.pasword = pasword;
	}*/
	
	
	
	//METODOS:
	@Override
	public String toString() {
		return "Nombre: " + getNombre() + ", apellidos: " + getApellidos() + ", DNI: " + getDni() + ", pasword: " + getPasword();
	}
	
	
	
	//GET-SET:
	public String getDni() {
		return super.getDni();
	}
	public void setDni(String dni) {
		super.setDni(dni);
	}
	public String getPasword() {
		return pasword;
	}
	public void setPasword(String pasword) {
		this.pasword = pasword;
	}
	public String getNombre() {
		return super.getNombre();
	}
	public void setNombre(String nombre) {
		super.setNombre(nombre);
	}
	public String getApellidos() {
		return super.getApellidos();
	}
	public void setApellidos(String apellidos) {
		super.setApellidos(apellidos);
	}
}
