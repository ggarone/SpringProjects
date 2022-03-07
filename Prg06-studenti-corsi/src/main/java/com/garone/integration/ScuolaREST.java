package com.garone.integration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.garone.entities.Esame;
import com.garone.entities.Studente;
import com.garone.entities.StudentiEsami;
import com.garone.services.ScuolaService;

@RestController
@RequestMapping("api")
public class ScuolaREST {

	@Autowired
	private ScuolaService service;
	
	@GetMapping("studenti")
	public List<Studente> getStudenti() {
		return service.getStudenti();
	}
	
	@GetMapping("esami")
	public List<Esame> getEsami() {
		return service.getEsami();
	}
	@GetMapping("studentiesami")
	public List<StudentiEsami> getStudentiEsami() {
		return service.getStudentiEsami();
	}
	
	@GetMapping("esamibystudente/{id}")
	public List<Esame> getEsamiByStudente(@PathVariable int id){
		return service.getEsameByStudente(service.getStudenteById(id));
	}
	@GetMapping("studentebyesame/{id}")
	public List<Studente> getStudenteByEsame(@PathVariable int id){
		return service.getStudenteByEsame(service.getEsameById(id));
	}
	
}
