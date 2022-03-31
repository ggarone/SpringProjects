package com.garone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.garone.uploadfile.StorageService;

@Controller
@RequestMapping("")
public class UploadCtrl {
	
	private final StorageService service;
	
	@Autowired
	public UploadCtrl(StorageService service) {
		this.service = service;
	}
	
	@GetMapping
	public String benvenuto() {
		return "index";
	}
	
	@PostMapping
	public String caricaFile(@RequestParam("file") MultipartFile file,
			RedirectAttributes redirect) {
		service.store(file);
		redirect.addFlashAttribute("messaggio","hai caricato con successo! " + file.getOriginalFilename());
		return "redirect:/";
		
	}
	
}
