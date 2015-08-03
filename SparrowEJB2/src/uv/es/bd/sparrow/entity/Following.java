package uv.es.bd.sparrow.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * The persistent class for the followings database table.
 * 
 */
@Entity
@Table(name="followings")
@NamedQueries({
	@NamedQuery(name="Following.findAll", query="SELECT f FROM Following f"),
	@NamedQuery(name="Following.noFollow", query="SELECT f FROM Following f WHERE f.followed.id LIKE :seguido AND f.follower.id LIKE :seguidor")
})
//@NamedQuery(name="User.findByApellidos",query="SELECT e FROM User e WHERE e.apellidos LIKE :apellidos")
@XmlRootElement
public class Following implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FollowingPK id;

	private Timestamp since;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="followed")
	private User followed;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="user")
	private User follower;

	public Following() {
	}

	public FollowingPK getId() {
		return this.id;
	}

	public void setId(FollowingPK id) {
		this.id = id;
	}

	public Timestamp getSince() {
		return this.since;
	}

	public void setSince(Timestamp since) {
		this.since = since;
	}

	public User getFollowed() {
		return this.followed;
	}

	public void setFollowed(User followed) {
		this.followed = followed;
	}

	public User getFollower() {
		return this.follower;
	}

	public void setFollower(User follower) {
		this.follower = follower;
	}

}