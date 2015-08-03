package es.uv.bd.sparrow.service.entities;





public class Topics {
	
	private int id;

	private String tag;

	private String text;
	
	private String user;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTag() {
		return this.tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Topics(int id, String tag, String text, String user) {
		super();
		this.id = id;
		this.tag = tag;
		this.text = text;
		this.user = user;
	}
	
	public Topics(){}

	
}
