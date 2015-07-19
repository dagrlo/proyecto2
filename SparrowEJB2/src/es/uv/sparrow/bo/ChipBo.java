package es.uv.sparrow.bo;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import uv.es.bd.sparrow.dao.ChipDao;
import uv.es.bd.sparrow.entity.Chip;

/**
 * Session Bean implementation class ChipBo
 */
@Stateless
@LocalBean
public class ChipBo implements ChipBoRemote {

	@PersistenceContext(name="sparrowPersistence")
	private EntityManager em;
	private ChipDao cdao;
    /**
     * Default constructor. 
     */
    public ChipBo() {
        // TODO Auto-generated constructor stub
    }
    
    @PostConstruct
    public void init(){
    	cdao=new ChipDao(em);
    }
    
    @PreDestroy
    public void finaliza(){
    	em.close();
    }
    
    public List<Chip> listaChips(){
    	return cdao.getAllChips();
    }

	@Override
	public void addChip(Chip nuevo) {
		cdao.persist(nuevo);		
	}

	@Override
	public List<Chip> listaTemas(){
		System.out.println("bo.lista");
		List<Chip> lista = null;
		
		try{
			lista=cdao.getAllThemes();
		} catch (Exception e){
			
		}
		
		return lista;
	}
	
	@Override
	public List<Chip>  listaPorTag(String tag){
		List<Chip> lista=null;
		
		try{
			lista=cdao.getByTag(tag);
		} catch (Exception e){
			
		}
		return lista;
	}
	
	@Override
	public Chip damePorId(String id){
		Chip chip=cdao.findById(Integer.parseInt(id));
		return chip;
	}

}
