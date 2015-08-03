package es.uv.sparrow.bo;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import uv.es.bd.sparrow.dao.UserGroupDao;
import uv.es.bd.sparrow.entity.UsersGroup;


@Stateless
@LocalBean
public class UsersGroupBo implements UsersGroupBoRemote {

	@PersistenceContext(name="sparrowPersistence")
	private EntityManager em;
	private UserGroupDao uGDao;
	
    public UsersGroupBo() {
    }

    @PostConstruct
    public void init(){
    	uGDao=new UserGroupDao(em);
    }
    
    @PreDestroy
    public void finaliza(){
    	em.close();
    }
    
    public void ponEnGrupo(String usuario){
    	UsersGroup grupo=new UsersGroup();
    	grupo.setGroupid("USERS");
    	grupo.setId(0);
    	grupo.setUsername(usuario);
    	uGDao.persist(grupo);
    }
    
    
}
