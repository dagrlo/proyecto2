package es.uv.bd.sparrow.users;

import javax.ejb.Remote;

@Remote
public interface SimpleUserRemote {
	public String getUsername();
	public void setUsername(String username);
	public int getId();
	public void setId(int id);
	
}
