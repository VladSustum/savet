package savet.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Zgrada {
	@Id
	@GeneratedValue
	@Column
	private Long id;
	@Column(nullable = false, unique=true)
	private String adresa;
	@Column(nullable = false)
	private String predsednik;
	@Column
	private Integer stanovi;
	@Column(nullable = false)
	private int stanari;
	
	@OneToMany(mappedBy="zgrada", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	List<Poruka> poruke = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getPredsednik() {
		return predsednik;
	}

	public void setPredsednik(String predsednik) {
		this.predsednik = predsednik;
	}

	public Integer getStanovi() {
		return stanovi;
	}

	public void setStanovi(Integer stanovi) {
		this.stanovi = stanovi;
	}

	public int getStanari() {
		return stanari;
	}

	public void setStanari(int stanari) {
		this.stanari = stanari;
	}

	public List<Poruka> getPoruke() {
		return poruke;
	}

	public void setPoruke(List<Poruka> poruke) {
		this.poruke = poruke;
	}
	
	
	public void addPoruka(Poruka poruka) {
		if(poruka.getZgrada() != this) {
			poruka.setZgrada(this);
		}
		poruke.add(poruka);
	}

}
