package uv.es.bd.sparrow.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQueries({
	@NamedQuery(name="User.findAll", query="SELECT u FROM User u"),
	@NamedQuery(name="User.findByUsername",query="SELECT e FROM User e WHERE e.username LIKE :username"),
	@NamedQuery(name="User.findByApellidos",query="SELECT e FROM User e WHERE e.apellidos LIKE :apellidos")
})

@XmlRootElement
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String apellidos;

	private String eMail;

	private String idioma;

	private String nombre;

	private String password;

	@Column(name="password_string")
	private String passwordString;

	private String sexo;

	private String username;

	//bi-directional many-to-one association to Chip
	@OneToMany(mappedBy="userBean", fetch=FetchType.EAGER)
	private List<Chip> chips;

	//bi-directional many-to-one association to Following
	@OneToMany(mappedBy="followed", fetch=FetchType.EAGER)
	private List<Following> followers;

	//bi-directional many-to-one association to Following
	@OneToMany(mappedBy="follower", fetch=FetchType.EAGER)
	private List<Following> followeds;

	public User() {
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

	@XmlTransient
	public List<Chip> getChips() {
		return this.chips;
	}

	public void setChips(List<Chip> chips) {
		this.chips = chips;
	}

	public Chip addChip(Chip chip) {
		getChips().add(chip);
		chip.setUserBean(this);

		return chip;
	}

	public Chip removeChip(Chip chip) {
		getChips().remove(chip);
		chip.setUserBean(null);

		return chip;
	}

	@XmlTransient
	public List<Following> getFollowers() {
		return this.followers;
	}

	public void setFollowers(List<Following> followers) {
		this.followers = followers;
	}

	public Following addFollower(Following follower) {
		getFollowers().add(follower);
		follower.setFollowed(this);

		return follower;
	}

	public Following removeFollower(Following follower) {
		getFollowers().remove(follower);
		follower.setFollowed(null);

		return follower;
	}

	@XmlTransient
	public List<Following> getFolloweds() {
		return this.followeds;
	}

	public void setFolloweds(List<Following> followeds) {
		this.followeds = followeds;
	}

	public Following addFollowed(Following followed) {
		getFolloweds().add(followed);
		followed.setFollower(this);

		return followed;
	}

	public Following removeFollowed(Following followed) {
		getFolloweds().remove(followed);
		followed.setFollower(null);

		return followed;
	}

}