package uv.es.sparrow.entities;

public class Users {
	private int id;

	private String apellidos;

	private String eMail;

	private String idioma;

	private String nombre;

	private String password;
	
	private String passwordString;

	private String sexo;

	private String username;

	public Users() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEMail() {
		return this.eMail;
	}

	public void setEMail(String eMail) {
		this.eMail = eMail;
	}

	public String getIdioma() {
		return this.idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordString() {
		return this.passwordString;
	}

	public void setPasswordString(String passwordString) {
		this.passwordString = passwordString;
	}

	public String getSexo() {
		return this.sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Users(int id, String apellidos, String eMail, String idioma,
			String nombre, String password, String passwordString, String sexo,
			String username) {
		super();
		this.id = id;
		this.apellidos = apellidos;
		this.eMail = eMail;
		this.idioma = idioma;
		this.nombre = nombre;
		this.password = password;
		this.passwordString = passwordString;
		this.sexo = sexo;
		this.username = username;
	}
	
	
	
	
}
