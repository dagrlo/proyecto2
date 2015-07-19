package es.uv.bd.sparrow.users;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;

import uv.es.bd.sparrow.entity.User;

/**
 * Session Bean implementation class UsersBean
 */
@Stateful
@LocalBean
public class UsersBean implements UsersBeanRemote {

	private String name;
	private String password;
	private boolean logged;
	private User user;
    /**
     * Default constructor. 
     */
    public UsersBean() {
        this.logged=false;
    }
	
	public void setLogged(boolean logged){
		this.logged=logged;
	}
	
	public boolean getLogged(){
		return this.logged;
	}
	
	public void setUser(User usuario){
		this.user=usuario; 
		System.out.println("Bean. usuario:"+usuario.getUsername());
		System.out.println("Bean. followers/eds:"+usuario.getFollowers().size()+"/"+usuario.getFolloweds().size());
	}
	
	public User getUser(){
		return this.user;
	}

}
