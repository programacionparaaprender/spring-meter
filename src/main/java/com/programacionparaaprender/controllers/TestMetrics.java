package com.programacionparaaprender.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/programacion/metrics")
@CrossOrigin
@Controller
public class TestMetrics {
	@GetMapping
	public ResponseEntity<String> get(){
		return new ResponseEntity<String>("@raidentrance", HttpStatus.OK);
	}
}
