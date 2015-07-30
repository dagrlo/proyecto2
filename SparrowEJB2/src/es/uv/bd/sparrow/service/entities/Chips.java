package es.uv.bd.sparrow.service.entities;

public class Chips {
	private String texto;
	private String autor;
	private int id;
	
	public String getTexto() {
		return texto;
	}
	
	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	public String getAutor() {
		return autor;
	}
	
	public void setAutor(String autor) {
		this.autor = autor;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public Chips(String texto, String autor, int id) {
		super();
		this.texto = texto;
		this.autor = autor;
		this.id = id;
	}
	
	public Chips(){}
}
