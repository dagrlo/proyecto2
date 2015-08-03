package es.uv.bd.sparrow.service.entities;

public class MiniUser {
	private String username;
	private String id;
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public MiniUser(){}

	public MiniUser(String username, String id) {
		super();
		this.username = username;
		this.id = id;
	}
	
	
	
	
}
