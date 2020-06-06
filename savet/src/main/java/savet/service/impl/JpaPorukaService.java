package savet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import savet.model.Glas;
import savet.model.Poruka;
import savet.model.Zgrada;
import savet.repository.GlasRepository;
import savet.repository.PorukaRepository;
import savet.service.PorukaService;
@Service
public class JpaPorukaService implements PorukaService {
	
	@Autowired
	private GlasRepository gs;
	
	@Autowired
	private PorukaRepository ps;

	@Override
	public Page<Poruka> findAll(int pageNum) {
		// TODO Auto-generated method stub
		return ps.findAll(new PageRequest(pageNum,5));
	}

	@Override
	public Poruka findOne(Long id) {
		// TODO Auto-generated method stub
		return ps.findOne(id);
	}

	@Override
	public Poruka save(Poruka poruka) {
		// TODO Auto-generated method stub
		return ps.save(poruka);
	}

	@Override
	public Poruka delete(Long id) {
		Poruka toDelete = findOne(id);
		if(toDelete != null) {
			ps.delete(toDelete);
		}
		return toDelete;
	}

	@Override
	public Page<Poruka> search(Long zgrada, String naslov, String tip, int pageNum) {
		
		return ps.search(zgrada, naslov, tip, new PageRequest(pageNum,5));
	}

	@Override
	public Glas glasaj(Long id) {
		Poruka p = findOne(id);
		Glas g= null;
		if( p != null) {
			g= null;
		if(p.getZa() < p.getZgrada().getStanari()) {
			g = new Glas();
			p.setZa(p.getZa() + 1);
			g.setPoruka(p);
			ps.save(p);
			gs.save(g);
		if(p.getZa() >= ((p.getProcenat()/100) * p.getZgrada().getStanari()) ) {
		p.setPrihvacen(true);
		
		ps.save(p);
		gs.save(g);
		return g;
		}
		}
		return g;
	}else {
		throw new IllegalStateException("Greka");
	}

}
}