package es.uv.sparrow.bo;

import javax.ejb.Remote;

@Remote
public interface UsersGroupBoRemote {
	public void ponEnGrupo(String usuario);
}
