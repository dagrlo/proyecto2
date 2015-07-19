package uv.es.beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class HolaBean
 */
@Stateless
@LocalBean
public class HolaBean implements HolaBeanRemote {

	private Persona persona;
	private ArrayList<Persona> personas;
    /**
     * Default constructor. 
     */
    public HolaBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public String dameHola() {
		return "Hola!!!";
	}

	@Override
	public void creaPersona() {
		persona=new Persona("Nombre","Apellidos","Direccion","Telefono");
		
	}

	@Override
	public Persona damePersona() { 
		return persona;
	}

	@Override
	public ArrayList<Persona> dameLista() {
		ArrayList<Persona> personas=new ArrayList<Persona>();
		personas.add(new Persona("Nombre1","Apellidos","Direccion","Telefono"));
		personas.add(new Persona("Nombre2","Apellidos","Direccion","Telefono"));
		personas.add(new Persona("Nombre3","Apellidos","Direccion","Telefono"));
		personas.add(new Persona("Nombre4","Apellidos","Direccion","Telefono"));
		personas.add(new Persona("Nombre5","Apellidos","Direccion","Telefono"));
		personas.add(new Persona("Nombre6","Apellidos","Direccion","Telefono"));
		personas.add(new Persona("Nombre7","Apellidos","Direccion","Telefono"));
		personas.add(new Persona("Nombre8","Apellidos","Direccion","Telefono"));
		return personas;
	}

}
