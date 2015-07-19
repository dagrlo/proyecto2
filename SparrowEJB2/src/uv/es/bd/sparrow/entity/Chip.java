package uv.es.bd.sparrow.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


/**
 * The persistent class for the chips database table.
 * 
 */
@Entity
@Table(name="chips")
@NamedQueries({
	@NamedQuery(name="Chip.findAll", query="SELECT c FROM Chip c"),
	@NamedQuery(name="Chip.findThemes",query="SELECT c FROM Chip c WHERE c.chip=NULL"),
	@NamedQuery(name="Chip.findByTag",query="SELECT c FROM Chip c WHERE c.tag LIKE :nombreTag")
	
})
@XmlRootElement
public class Chip implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String tag;

	private String text;

	//bi-directional many-to-one association to Chip
	@ManyToOne
	@JoinColumn(name="thread")
	private Chip chip;

	//bi-directional many-to-one association to Chip
	@OneToMany(mappedBy="chip", fetch=FetchType.EAGER)
	private List<Chip> chips;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="user")
	private User userBean;

	public Chip() {
	}

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

	public Chip getChip() {
		return this.chip;
	}

	public void setChip(Chip chip) {
		this.chip = chip;
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
		chip.setChip(this);

		return chip;
	}

	public Chip removeChip(Chip chip) {
		getChips().remove(chip);
		chip.setChip(null);

		return chip;
	}

	public User getUserBean() {
		return this.userBean;
	}

	public void setUserBean(User userBean) {
		this.userBean = userBean;
	}

}