package savet.service;

import org.springframework.data.domain.Page;

import savet.model.Glas;
import savet.model.Poruka;

public interface PorukaService {
	
	Page<Poruka> findAll(int pageNum);
	Poruka findOne(Long id);
	Poruka save(Poruka poruka);
	Poruka delete(Long id);
	Page<Poruka> search(Long zgrada, String naslov, String tip, int pageNum);
	
	Glas glasaj(Long id);

}
