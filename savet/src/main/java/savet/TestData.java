package savet;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import savet.model.Poruka;
import savet.model.Zgrada;
import savet.service.PorukaService;
import savet.service.ZgradaService;

@Component
public class TestData {
	
	@Autowired
	private PorukaService ps;
	
	@Autowired
	private ZgradaService zs;
	
	@PostConstruct
	public void Innit() {
		
		Zgrada z = new Zgrada();
		
		Poruka p = new Poruka();
		
		z.setAdresa("Novi Sad");
		z.setPredsednik("Babis");
		z.setStanari(10);
		z.setStanovi(25);
		
		Poruka pi = new Poruka();
		
//		pi.setNaslov("random");
//		pi.setOpis("Nikola tesla");
//		pi.setTip("predlog");
//		p.setZgrada(z);
//		ps.save(pi);
		
		
		p.setNaslov("Struja");
		p.setOpis("Popravi struju");
		p.setPrihvacen(false);
		p.setTip("predlog");
		p.setProcenat(50);
		p.setZa(1);
		
		
		
		
		p.setZgrada(z);
		zs.save(z);
		ps.save(p);
		System.out.println(p.getZgrada().getStanari());
	}

}
