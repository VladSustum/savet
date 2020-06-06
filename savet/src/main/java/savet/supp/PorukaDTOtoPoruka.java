package savet.supp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import savet.dto.PorukaDTO;
import savet.model.Poruka;
import savet.model.Zgrada;
import savet.service.PorukaService;
import savet.service.ZgradaService;

@Component
public class PorukaDTOtoPoruka implements Converter<PorukaDTO,Poruka> {
	
	@Autowired
	private PorukaService ps;
	
	@Autowired
	private ZgradaService zs;

	@Override
	public Poruka convert(PorukaDTO source) {
		
		Zgrada z = zs.findOne(source.getZgradaId());
		
		if(z != null) {
			Poruka p = null;
			if(source.getId() != null) {
				p = ps.findOne(source.getId());
			} else {
				p = new Poruka();
			}
			p.setNaslov(source.getNaslov());
			p.setZa(source.getZa());
			p.setOpis(source.getOpis());
			p.setPrihvacen(source.isPrihvacen());
			p.setTip(source.getTip());
			p.setProcenat(source.getProcenat());
			p.setZgrada(z);
			return p;
		} else {
			throw new IllegalStateException("Greska");
		}
		
		
	}
	
	public List<Poruka> convert(List<PorukaDTO> source){
		
		List<Poruka> p = new ArrayList<>();
		
		for(PorukaDTO dto : source) {
			p.add(convert(dto));
		}
		return p;
	}
	
	
	

}
