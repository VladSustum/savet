package savet.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import savet.model.Zgrada;
import savet.repository.ZgradaRepository;
import savet.service.ZgradaService;
@Service
public class JpaZgradaService implements ZgradaService {
	
	@Autowired
	private ZgradaRepository zs;

	@Override
	public List<Zgrada> findAll() {
		// TODO Auto-generated method stub
		return zs.findAll();
	}

	@Override
	public Zgrada findOne(Long id) {
		// TODO Auto-generated method stub
		return zs.findOne(id);
	}

	@Override
	public Zgrada save(Zgrada zgrada) {
		// TODO Auto-generated method stub
		return zs.save(zgrada);
	}

}
