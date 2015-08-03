package es.uv.sparrow.bo;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import uv.es.bd.sparrow.dao.FollowDao;
import uv.es.bd.sparrow.dao.UserDao;
import uv.es.bd.sparrow.entity.Following;

/**
 * Session Bean implementation class FollowBo
 */
@Stateless
@LocalBean
public class FollowBo implements FollowBoRemote {
	@PersistenceContext(name="sparrowPersistence")
	private EntityManager em;
	private FollowDao fDao;
	private UserDao uDao;
    /**
     * Default constructor. 
     */
    public FollowBo() {
        // TODO Auto-generated constructor stub
    }
    
    @PostConstruct
    public void init(){
    	fDao=new FollowDao(em);
    }
    
    @PreDestroy
    public void finaliza(){
    	em.close();
    }
    
    @Override
    public void seguir(Following follow){
    	fDao.persist(follow);    	
    }
    
    @Override 
    public void noSeguir(Following follow){
    	//fDao.unfollow(unfollowId,myId);
    	
    	Following followBorrar=fDao.buscar(follow.getFollowed(), follow.getFollower());
    	//Borra de la BD pero luego sigue saliendo en la lista ¿?
    	//uDao.findById(follow.getFollowed().getId()).removeFollower(followBorrar);
    	//uDao.findById(follow.getFollower().getId()).removeFollowed(followBorrar);
    	System.out.println("borrar:"+followBorrar.getId());
    	fDao.remove(followBorrar);
    } 
    
   

}
