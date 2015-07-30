package uv.es.sparrow.publico;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;

/**
 * Session Bean implementation class UserBean
 */
@Stateful
@LocalBean
public class UserBean {

	private String nombre;
	private String pass;
	private String b64;
	
	public UserBean(){
		
	}
	
    public UserBean(String nombre,String pass,String b64) {
    	super();
		this.nombre = nombre;
		this.pass = pass;
		this.b64 = b64;
    }
    
    public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getPass() {
		return pass;
	}
	
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public String getB64() {
		return b64;
	}
	
	public void setB64(String b64) {
		this.b64 = b64;
	}


}
