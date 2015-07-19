package es.uv.sparrow.bo;

import javax.ejb.Remote;

import uv.es.bd.sparrow.entity.Following;

@Remote
public interface FollowBoRemote {
	public void seguir(Following follow);
	
	public void noSeguir(Following follow);
}
