package com.stylight.assessment.bll;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stylight.assessment.caching.URLCacheAgent;
import com.stylight.assessment.dao.URLRepository;

@Service
public class URLService {

	@Autowired
	URLRepository urlRepository;

	@Autowired
	URLCacheAgent cacheAgent;

	public Map<String, String> getuglyToPrettyMap(List<String> listUglyUrl) {
		Map<String, String> map = new HashMap<String, String>();
		for (String url : listUglyUrl) {

			if (urlRepository.getUglyToPretty().containsKey(url)) {
				map.put(url, urlRepository.getUglyToPretty().get(url));
				continue;
			}

			String cache = cacheAgent.getFromCache(url);
			if (cache != null) {
				map.put(url, cache);
				continue;
			}

			map.put(url, makeURL(url));

		}
		return map;
	}

	private String makeURL(String url) {
		String prettyUrl = "";

		String[] split = url.split("?");
		if (urlRepository.getUglyToPretty().containsKey(split[0])) {
			prettyUrl = urlRepository.getUglyToPretty().get(split[0]);
		} else {
			return url;
		}
		
		if(split.length == 1) return prettyUrl;
		
		String[] secondSplit = split[1].split("&");
		
		for (String string : secondSplit) {
			
		}

		return prettyUrl;
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
