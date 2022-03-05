package com.garone.canzoni.integration;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.garone.canzoni.entities.Canzone;
import com.garone.canzoni.service.CanzoneService;

@RestController
@RequestMapping("api")
public class CanzoniREST {
	@Autowired
	private CanzoneService service;
	
	@GetMapping
	public List<Canzone> getAll() {
		return this.service.getCanzoni();
	}
	
	@GetMapping("genere/{gen}")
	public List<Canzone> getByGenere(@PathVariable String gen) {
		return this.service.getCanzoniByGenere(gen);
	}
	
	@GetMapping("genere/{tit}")
	public List<Canzone> getByTitolo(@PathVariable String tit) {
		return this.service.getCazoniByTitolo(tit);
	}
	
	@GetMapping("canzone/{id}")
	public Canzone getCanzone(@PathVariable int id) {
		if(this.service.getCanzoneById(id).isPresent())
			return this.service.getCanzoneById(id).get();
		else
			return new Canzone();
	}
	
	@GetMapping("paroleChiave")
	List<String> getParole(){
		List<String> parole = this.service.getCanzoni().stream()
				.map(c->c.getTitolo())
				.collect(Collectors.toList());
		
		Map<String,Integer> singoleParole = new TreeMap<>();
		for (String titolo : parole) {
			String[] parola = titolo.split(" ");
			for(String parolina: parola) {
				if(singoleParole.get(parolina)==null)
					singoleParole.put(parolina,1);
				else{
					Integer i = singoleParole.get(parolina);
					singoleParole.put(parolina,++i);
				}
			}
		}
		return parole;
	}
}
