package uv.es.bd.sparrow.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import uv.es.bd.sparrow.entity.User;

public class UserDao extends JpaDao<Integer,User>{

	public UserDao(EntityManager em) {
		super(em);		
	}
	
	public List<User> getAllUsers(){
		TypedQuery<User> query=em.createNamedQuery("User.findAll",User.class);
		return query.getResultList();
	}
	
	public User findUserByUsername(String username){
		TypedQuery<User> query=em.createNamedQuery("User.findByUsername",User.class);
		query.setParameter("username", username);
		return query.getSingleResult();
	}
	
	public List<User> findByApellidos(String apellidos){
		TypedQuery<User> query=em.createNamedQuery("User.findByApellidos",User.class);
		query.setParameter("apellidos", apellidos);
		
		return query.getResultList();
	}
	
	public void update(User usr){
		em.merge(usr);
		em.flush();
	}
	

}
