package uv.es.beans;

import java.util.ArrayList;

import javax.ejb.Remote;

@Remote
public interface HolaBeanRemote {
	public String dameHola();
	public void creaPersona();
	public Persona damePersona();
	public ArrayList<Persona> dameLista();
}
