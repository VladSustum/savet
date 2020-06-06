package savet.supp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import savet.dto.ZgradaDTO;
import savet.model.Zgrada;

@Component
public class ZgradaToZgradaDTO implements Converter<Zgrada,ZgradaDTO> {

	@Override
	public ZgradaDTO convert(Zgrada source) {
		ZgradaDTO dto = new ZgradaDTO();
		dto.setAdresa(source.getAdresa());
		dto.setId(source.getId());
		dto.setPredsednik(source.getPredsednik());
		dto.setStanari(source.getStanari());
		dto.setStanovi(source.getStanovi());
		return dto;
	}
	
	public List<ZgradaDTO> convert(List<Zgrada> source){
		List<ZgradaDTO> dto = new ArrayList<>();
		
		for(Zgrada z : source) {
			dto.add(convert(z));
		}
		return dto;
	}
	

}
