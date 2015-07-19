package uv.es.beans;

import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement
public class Persona {
	private String nombre;
	private String apellidos;
	private String direccion;
	private String telefono;
	
	public  Persona(){
		
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
	
	public String getDireccion() {
		return direccion;
	}
	
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public String getTelefono() {
		return telefono;
	}
	
	
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public Persona(String nombre, String apellidos, String direccion,
			String telefono) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.telefono = telefono;
	}
	
	
}
