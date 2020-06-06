package savet.dto;

public class ZgradaDTO {
	
	private Long id;
	
	private String adresa;
	
	private String predsednik;
	
	private Integer stanovi;
	
	private int stanari;

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

	public Integer getStanari() {
		return stanari;
	}

	public void setStanari(Integer stanari) {
		this.stanari = stanari;
	}
	
	

}
