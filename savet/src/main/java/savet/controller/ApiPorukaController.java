package savet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import savet.dto.GlasDTO;
import savet.dto.PorukaDTO;
import savet.model.Glas;
import savet.model.Poruka;
import savet.service.PorukaService;
import savet.supp.GlasToGlasDTO;
import savet.supp.PorukaDTOtoPoruka;
import savet.supp.PorukaToPorukaDTO;

@RestController
@RequestMapping(value="/api/poruke")
public class ApiPorukaController {
	
	@Autowired
	private PorukaService ps;
	
	@Autowired
	private PorukaToPorukaDTO toDTO;
	
	@Autowired
	private PorukaDTOtoPoruka toPor;
	
	@Autowired
	private GlasToGlasDTO toGlas;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<PorukaDTO>> getPoruke(@RequestParam(required= false) Long zgrada, @RequestParam(required = false) String naslov, @RequestParam(required= false) String tip
			, @RequestParam(defaultValue="0") int pageNum){
		Page<Poruka> poruka = null;
		
		if(zgrada != null || naslov != null || tip != null) {
			poruka = ps.search(zgrada, naslov, tip, pageNum);
		} else {
			poruka = ps.findAll(pageNum);
		}
		HttpHeaders headers = new HttpHeaders();
		
		headers.add("totalPages", Integer.toString(poruka.getTotalPages()));
		
		return new ResponseEntity<>(toDTO.convert(poruka.getContent()), headers, HttpStatus.OK);
	}
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public ResponseEntity<PorukaDTO> getPoruka(@PathVariable Long id){
		Poruka poruka = ps.findOne(id);
		if(poruka != null) {
			return new ResponseEntity<>(toDTO.convert(poruka), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<PorukaDTO> save(@Validated @RequestBody PorukaDTO porukaDTO){
		
		Poruka toSave = ps.save(toPor.convert(porukaDTO));
		
		return new ResponseEntity<>(toDTO.convert(toSave), HttpStatus.CREATED);
	}
	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	public ResponseEntity<PorukaDTO> delete(@PathVariable Long id){
		Poruka toDelete = ps.delete(id);
		
		if(toDelete == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(toDTO.convert(toDelete), HttpStatus.OK);
	}
	@RequestMapping(method=RequestMethod.PUT, value="/{id}", consumes="application/json")
	public ResponseEntity<PorukaDTO> edit(@PathVariable Long id, @RequestBody PorukaDTO dto){
		if(!id.equals(dto.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	Poruka save =ps.save(toPor.convert(dto));
	
		return new ResponseEntity<>(toDTO.convert(save), HttpStatus.OK);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Void> handler(){
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	@RequestMapping(method=RequestMethod.POST, value="/{id}")
	public ResponseEntity<GlasDTO> glasaj(@PathVariable Long id){
		Glas glasaj = ps.glasaj(id);
		if(glasaj != null) {
			return new ResponseEntity<>(toGlas.convert(glasaj), HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
