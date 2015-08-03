package es.uv.bd.sparrow.service.entities;

public class Follows {
	private String miUsuario;
	private String idSeguido;
	
	public String getMiUsuario() {
		return miUsuario;
	}
	
	public void setMiUsuario(String miUsuario) {
		this.miUsuario = miUsuario;
	}
	
	public String getIdSeguido() {
		return idSeguido;
	}
	
	public void setIdSeguido(String idSeguido) {
		this.idSeguido = idSeguido;
	}

	public Follows(String miUsuario, String idSeguido) {
		super();
		this.miUsuario = miUsuario;
		this.idSeguido = idSeguido;
	}
	
	public Follows(){}
	
	
}
