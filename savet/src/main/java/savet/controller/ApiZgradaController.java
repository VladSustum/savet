package savet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import savet.dto.ZgradaDTO;
import savet.model.Zgrada;
import savet.service.ZgradaService;
import savet.supp.ZgradaToZgradaDTO;

@RestController
@RequestMapping(value="/api/zgrade")
public class ApiZgradaController {
	@Autowired
	private ZgradaService zs;
	
	@Autowired
	private ZgradaToZgradaDTO toDTO;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ZgradaDTO>> getZgrade(){
		
		List<Zgrada> zgrade= zs.findAll();
		if(zgrade == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(toDTO.convert(zgrade), HttpStatus.OK);
	}

}
