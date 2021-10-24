package com.stylight.assessment.bll;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stylight.assessment.caching.URLCacheAgent;
import com.stylight.assessment.dao.URLRepository;
import com.stylight.assessment.model.Dummy;

@Service
public class URLService {

	@Autowired
	URLRepository urlRepository;

	@Autowired
	URLCacheAgent cacheAgent;

	public Map<String, String> getuglyToPrettyMap(List<String> listUglyUrl) {
		Map<String, String> map = new HashMap<String, String>();
		ExecutorService executorService = Executors.newCachedThreadPool();
		List<Future<Map<String, String>>> result = new ArrayList<Future<Map<String, String>>>();

		int length = listUglyUrl.size();
		int size = 20;
		int count = length / size;
		int i = 0;

		for (; i < count; i++) {
			List<String> sub = listUglyUrl.subList(i, size);
			result.add(executorService.submit(new Task(sub, urlRepository, cacheAgent)));
			i += size;
		}

		if (i < length) {
			List<String> sub = listUglyUrl.subList(i, length);
			result.add(executorService.submit(new Task(sub, urlRepository, cacheAgent)));
		}

		try {
			for (Future<Map<String, String>> future : result) {
				Map<String, String> tmp = future.get();
				map.putAll(tmp);
			}
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

		return map;
	}

	public Map<String, String> getPrettyToUglyMap(List<String> listPrettyUrl) {
		Map<String, String> map = new HashMap<String, String>();
		for (String url : listPrettyUrl) {
			if (urlRepository.getPrettyToUgly().containsKey(url)) {
				map.put(url, urlRepository.getPrettyToUgly().get(url));
			} else {
				map.put(url, url);
			}
		}
		return map;
	}
}
