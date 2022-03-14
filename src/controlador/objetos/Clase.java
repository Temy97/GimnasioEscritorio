package controlador.objetos;

public class Clase {
	
	//ATRIBUTOS:
		private String id;
		private String nombre;
		private String descripcion;
		private String dni_profesor;
		
		
		
		//CONSTRUCTORES:
		public Clase() {};
		
		
		public Clase(String id, String nombre, String descripcion, String dni_profesor) {
			if(id.length() != 3) {
				throw new IllegalArgumentException("Se ha introducido un id invalido, revisar si es de longitud 3.");
			}else {
				this.setId(id);
				this.setNombre(nombre);
				this.setDescripcion(descripcion);
				this.setDni_profesor(dni_profesor);
			}
		}
		
		
		public Clase(String id, String nombre) {
			if(id.length() != 3) {
				throw new IllegalArgumentException("Se ha introducido un id invalido, revisar si es de longitud 3.");
			}else {
				this.setId(id);
				this.setNombre(nombre);
			}
		}
		
		
		
		//METODOS:
		//
		
		
		
		//GET-SET:
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public String getDescripcion() {
			return descripcion;
		}
		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}
		public String getDni_profesor() {
			return dni_profesor;
		}
		public void setDni_profesor(String dni_profesor) {
			this.dni_profesor = dni_profesor;
		}
}
