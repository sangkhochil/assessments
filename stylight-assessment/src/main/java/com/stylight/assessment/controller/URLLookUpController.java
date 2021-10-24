package com.stylight.assessment.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stylight.assessment.bll.URLService;
import com.stylight.assessment.exception.InvalidRequestException;

@RestController
public class URLLookUpController {

	@Autowired
	URLService urlService;

	private static final Logger logger = LoggerFactory.getLogger(URLLookUpController.class);

	@PostMapping(path = "/uglytopretty")
	public ResponseEntity<Map<String, String>> uglyToPrettyURL(@RequestBody List<String> listUglyUrl) {
		
		logger.debug("Ugly to pretty request.");
		if (listUglyUrl.size() == 0) {
			throw new InvalidRequestException("Request url list empty");
		}

		Map<String, String> mapUglyToPretty = urlService.getuglyToPrettyMap(listUglyUrl);
		return ResponseEntity.ok(mapUglyToPretty);
	}

	@PostMapping(path = "/prettytougly")
	public ResponseEntity<Map<String, String>> prettyToUglyURL(@RequestBody List<String> listPrettyUrl) {

		logger.debug("Pretty to ugly request.");
		if (listPrettyUrl.size() == 0) {
			throw new InvalidRequestException("Request url list empty");
		}

		Map<String, String> mapPrettyToUgly = urlService.getPrettyToUglyMap(listPrettyUrl);
		return ResponseEntity.ok(mapPrettyToUgly);
	}
}
