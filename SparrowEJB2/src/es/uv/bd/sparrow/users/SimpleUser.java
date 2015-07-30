package es.uv.bd.sparrow.users;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class SimpleUser
 */
@Stateless
@LocalBean
public class SimpleUser implements SimpleUserRemote {

    private String username;
    private int id;
	
    public SimpleUser() {
    }
    
    public SimpleUser(String username, int id){
    	this.username=username;
    	this.id=id;
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
    
    

}
