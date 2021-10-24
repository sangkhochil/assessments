package com.stylight.assessment.bll;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stylight.assessment.caching.URLCacheAgent;
import com.stylight.assessment.controller.URLLookUpController;
import com.stylight.assessment.dao.URLRepository;

@Service
public class URLService {

	@Autowired
	URLRepository urlRepository;

	@Autowired
	URLCacheAgent cacheAgent;
	
	private static final Logger logger = LoggerFactory.getLogger(URLLookUpController.class);

	public Map<String, String> getuglyToPrettyMap(List<String> listUglyUrl) {
		
		Map<String, String> map = new HashMap<String, String>();
		ExecutorService executorService = Executors.newCachedThreadPool();
		List<Future<Map<String, String>>> result = new ArrayList<Future<Map<String, String>>>();

		int length = listUglyUrl.size();
		int size = 20;
		int count = length / size;
		int i = 0;
		
		logger.debug("Making task list...");

		try {
			for (; i < count; i++) {
				List<String> sub = listUglyUrl.subList(i, size);
				result.add(executorService.submit(new Task(sub, urlRepository, cacheAgent)));
				i += size;
			}

			if (i < length) {
				List<String> sub = listUglyUrl.subList(i, length);
				result.add(executorService.submit(new Task(sub, urlRepository, cacheAgent)));
			}

		} catch (Exception e) {
			throw e;
		}
		
		logger.debug("Merging result maps...");
		try {
			for (Future<Map<String, String>> future : result) {
				Map<String, String> tmp = future.get();
				map.putAll(tmp);
			}
		} catch (InterruptedException | ExecutionException ex) {
			ex.printStackTrace();
		}

		return map;
	}

	public Map<String, String> getPrettyToUglyMap(List<String> listPrettyUrl) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			for (String url : listPrettyUrl) {
				if (urlRepository.getPrettyToUgly().containsKey(url)) {
					map.put(url, urlRepository.getPrettyToUgly().get(url));
				} 
//				else if(cacheAgent.getFromCache(url) != null){
//					map.put(url, cacheAgent.getFromCache(url));
//				}
				else {
					map.put(url, url);
				}
			}
		} catch (Exception ex) {
			throw ex;
		}

		return map;
	}
}
