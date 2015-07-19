package uv.es.bd.sparrow.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import uv.es.bd.sparrow.entity.Chip;
import uv.es.bd.sparrow.entity.User;

public class ChipDao extends JpaDao<Integer,Chip>{

	public ChipDao(EntityManager em) {
		super(em);		
	}
	
	public List<Chip> getAllChips(){
		TypedQuery<Chip> query=em.createNamedQuery("Chip.findAll",Chip.class);
		return query.getResultList();
	}
	
	public List<Chip> getAllThemes(){		
		TypedQuery<Chip> query=em.createNamedQuery("Chip.findThemes",Chip.class);
		System.out.println("dao.lista");
		return query.getResultList();
	}
	
	public List<Chip> getByTag(String tag){
		TypedQuery<Chip> query=em.createNamedQuery("Chip.findByTag",Chip.class);
		query.setParameter("nombreTag", tag);
		
		return query.getResultList();
	}

}
