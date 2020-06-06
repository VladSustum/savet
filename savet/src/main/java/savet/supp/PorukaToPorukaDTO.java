package savet.supp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import savet.dto.PorukaDTO;
import savet.model.Poruka;

@Component
public class PorukaToPorukaDTO implements Converter<Poruka,PorukaDTO> {

	@Override
	public PorukaDTO convert(Poruka source) {
		PorukaDTO dto = new PorukaDTO();
		dto.setId(source.getId());
		dto.setNaslov(source.getNaslov());
		dto.setOpis(source.getOpis());
		dto.setPrihvacen(source.isPrihvacen());
		dto.setProcenat(source.getProcenat());
		dto.setZa(source.getZa());
		dto.setTip(source.getTip());
		dto.setZgradaId(source.getZgrada().getId());
		dto.setZgradaAdresa(source.getZgrada().getAdresa());
		
		return dto;
	}
	
	public List<PorukaDTO> convert(List<Poruka> source){
		List<PorukaDTO> dto = new ArrayList<>();
		
		for(Poruka p : source) {
			dto.add(convert(p));
		}
		return dto;
	}

}
