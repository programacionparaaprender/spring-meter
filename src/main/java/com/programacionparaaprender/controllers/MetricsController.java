package com.programacionparaaprender.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Timer;
import io.micrometer.core.instrument.composite.CompositeMeterRegistry;


@RestController
@RequestMapping("/api/metrics")
@CrossOrigin
@Controller
public class MetricsController {
	@Autowired
	private MeterRegistry registry;
	 private static final Logger log = LoggerFactory.getLogger(MetricsController.class);
	
	@GetMapping("/")
	public ResponseEntity<String> get(){
		log.info("MeterRegistry used {}", registry.getClass().getName());
		Timer timer = registry.timer("devs4j.timer");
		timer.record(()-> {
			
		});
		return new ResponseEntity<String>("@raidentrance", HttpStatus.OK);
	}
	
	@GetMapping("/hola")
	@Timed("devs4j.timer")
	public ResponseEntity<String> hola(){
		log.info("MeterRegistry used {}", registry.getClass().getName());
		return new ResponseEntity<String>("@raidentrance", HttpStatus.OK);
	}
}
