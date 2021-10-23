package com.stylight.assessment.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class URLLookUpController {
	
	@GetMapping(path = "/hello-world")
	public String getHello() {
		
		return "Hello World.....";
//		return sb.toString();
	}
	
	@PostMapping(path = "/get-list")
	public List<String> getList(){
		return List.of("item1","item2", "item3");
	}

	@PostMapping(path = "/uglytopretty")
	public ResponseEntity<Map<String, String>> uglyToPrettyURL(@RequestBody List<String> listUglyUrl) {
		
		Map<String, String> mapUglyToPretty = new HashMap<String, String>();
		mapUglyToPretty.put("ugly1", "pretty1");
		mapUglyToPretty.put("ugly2", "pretty2");
		mapUglyToPretty.put("ugly3", "pretty3");
		
		return ResponseEntity.ok(mapUglyToPretty);	
	}
	
	@PostMapping(path = "/prettytougly")
	public ResponseEntity<Map<String, String>> prettyToUglyURL(@RequestBody List<String> listPrettyUrl) {
		
		Map<String, String> mapPrettyToUgly = new HashMap<String, String>();
		mapPrettyToUgly.put("pretty1", "ugly1");
		mapPrettyToUgly.put("pretty2", "ugly2");
		mapPrettyToUgly.put("pretty3", "ugly3");
		
		return ResponseEntity.ok(mapPrettyToUgly);	
	}
}
