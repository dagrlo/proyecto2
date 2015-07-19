package es.uv.sparrow.bo;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import uv.es.bd.sparrow.dao.FollowDao;
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
    	fDao.remove(follow);
    }
    
   

}
