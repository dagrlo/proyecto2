package uv.es.bd.sparrow.dao;

import javax.persistence.EntityManager;

import uv.es.bd.sparrow.entity.User;
import uv.es.bd.sparrow.entity.UsersGroup;

public class UserGroupDao extends JpaDao<Integer,UsersGroup> {

	public UserGroupDao(EntityManager em) {
		super(em);	
	}
	
	public void setGroup(UsersGroup grupo){		
		em.merge(grupo);
		em.flush();
	}

}
