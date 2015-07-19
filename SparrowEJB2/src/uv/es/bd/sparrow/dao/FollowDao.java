package uv.es.bd.sparrow.dao;

import javax.persistence.EntityManager;
import javax.persistence.PostPersist;
import javax.persistence.TypedQuery;

import uv.es.bd.sparrow.entity.Following;
import uv.es.bd.sparrow.entity.User;

public class FollowDao extends JpaDao<Integer,Following>{

	public FollowDao(EntityManager em) {
		super(em);
		// TODO Auto-generated constructor stub
	}
	
	public void update(Following follow){
		em.merge(follow);	
		em.flush();
	}
	




}
