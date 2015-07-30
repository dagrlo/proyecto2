package uv.es.sparrow.entities;

public class Password {
	private String email;
	private String nombre;
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	

	public Password(String email, String nombre) {
		super();
		this.email = email;
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Password(){};
	
	
}
