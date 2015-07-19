package es.uv.sparrow.bo;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;

import uv.es.bd.sparrow.entity.Chip;

@Remote
public interface ChipBoRemote {
	public List<Chip> listaChips();
	public void addChip(Chip nuevo);
	public List<Chip> listaTemas();
	public List<Chip> listaPorTag(String tag);
	public Chip damePorId(String id);
}
