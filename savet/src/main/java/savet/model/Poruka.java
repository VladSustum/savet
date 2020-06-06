package savet.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Poruka {
	@Id
	@GeneratedValue
	@Column  
	private Long id;
	@Column 
	private String naslov;
	@Column 
	private String tip;
	@Column 
	private double procenat=0;
	@Column 
	private boolean prihvacen=false;
	@Column 
	private String opis;
	@ManyToOne(fetch=FetchType.EAGER)
	Zgrada zgrada;
	@OneToMany(mappedBy="poruka", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Glas> glasovi = new ArrayList<>(); 
	@Column
	private int za=0;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNaslov() {
		return naslov;
	}
	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	public double getProcenat() {
		return procenat;
	}
	public void setProcenat(double procenat) {
		this.procenat = procenat;
	}
	public boolean isPrihvacen() {
		return prihvacen;
	}
	public void setPrihvacen(boolean prihvacen) {
		this.prihvacen = prihvacen;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public Zgrada getZgrada() {
		return zgrada;
	}
	public void setZgrada(Zgrada zgrada) {
		this.zgrada = zgrada;
		if(!zgrada.getPoruke().contains(this)) {
			zgrada.getPoruke().add(this);
		}
	}
	public List<Glas> getGlasovi() {
		return glasovi;
	}
	public void setGlasovi(List<Glas> glasovi) {
		this.glasovi = glasovi;
	}
	public void addGlas(Glas glas) {
		if(glas.getPoruka() != this) {
			glas.setPoruka(this);
		}
		glasovi.add(glas);
	}
	public int getZa() {
		return za;
	}
	public void setZa(int za) {
		this.za = za;
	}

	
}
