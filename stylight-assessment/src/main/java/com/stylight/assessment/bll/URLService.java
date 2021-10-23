package com.stylight.assessment.bll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stylight.assessment.dao.URLRepository;

@Service
public class URLService {
	@Autowired
	URLRepository urlRepository;
	
}
