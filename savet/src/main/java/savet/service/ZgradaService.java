package savet.service;

import java.util.List;

import savet.model.Zgrada;

public interface ZgradaService {
	
	List<Zgrada> findAll();
	Zgrada findOne(Long id);
	Zgrada save(Zgrada zgrada);

}
