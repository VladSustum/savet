package savet.supp;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import savet.dto.GlasDTO;
import savet.model.Glas;

@Component
public class GlasToGlasDTO implements Converter<Glas,GlasDTO> {

	@Override
	public GlasDTO convert(Glas source) {
		GlasDTO dto = new GlasDTO();
		dto.setId(source.getId());
		dto.setPredlog(source.getPredlog());
		return dto;
	}

}
