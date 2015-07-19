package uv.es.bd.sparrow.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the followings database table.
 * 
 */
@Embeddable
public class FollowingPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int user;

	@Column(insertable=false, updatable=false)
	private int followed;

	public FollowingPK() {
	}
	public int getUser() {
		return this.user;
	}
	public void setUser(int user) {
		this.user = user;
	}
	public int getFollowed() {
		return this.followed;
	}
	public void setFollowed(int followed) {
		this.followed = followed;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FollowingPK)) {
			return false;
		}
		FollowingPK castOther = (FollowingPK)other;
		return 
			(this.user == castOther.user)
			&& (this.followed == castOther.followed);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.user;
		hash = hash * prime + this.followed;
		
		return hash;
	}
}