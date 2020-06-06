package savet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Glas {
	@Id
	@GeneratedValue 
	@Column  
	private Long id;
	@Column
	private String predlog;
	@ManyToOne(fetch=FetchType.EAGER)
	private Poruka poruka;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPredlog() {
		return predlog;
	}
	public void setPredlog(String predlog) {
		this.predlog = predlog;
	}
	public Poruka getPoruka() {
		return poruka;
	}
	public void setPoruka(Poruka poruka) {
		this.poruka = poruka;
		if(!poruka.getGlasovi().contains(this)) {
			poruka.getGlasovi().add(this);
		}
	}

	
}
