package com.stylight.assessment.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stylight.assessment.bll.URLService;

@RestController
public class URLLookUpController {

	@Autowired
	URLService urlService;

	@GetMapping(path = "/hello-world")
	public String getHello() {
		return "Hello World.....";
	}

	@PostMapping(path = "/get-list")
	public List<String> getList() {
		return List.of("item1", "item2", "item3");
	}

	@PostMapping(path = "/uglytopretty")
	public ResponseEntity<Map<String, String>> uglyToPrettyURL(@RequestBody List<String> listUglyUrl) {

		Map<String, String> mapUglyToPretty = urlService.getuglyToPrettyMap(listUglyUrl);
		return ResponseEntity.ok(mapUglyToPretty);
	}

	@PostMapping(path = "/prettytougly")
	public ResponseEntity<Map<String, String>> prettyToUglyURL(@RequestBody List<String> listPrettyUrl) {

		Map<String, String> mapPrettyToUgly = urlService.getPrettyToUglyMap(listPrettyUrl);
		return ResponseEntity.ok(mapPrettyToUgly);
	}
}
