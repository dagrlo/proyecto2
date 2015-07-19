package es.uv.bd.sparrow.users;

import javax.ejb.Remote;

import uv.es.bd.sparrow.entity.User;

@Remote
public interface UsersBeanRemote {
	public void setUser(User usuario);
	public User getUser();
	public void setLogged(boolean logged);
	public boolean getLogged();
}
