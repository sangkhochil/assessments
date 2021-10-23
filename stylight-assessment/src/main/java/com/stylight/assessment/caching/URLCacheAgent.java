package com.stylight.assessment.caching;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

@Component
public class URLCacheAgent {
	private Map<String, String> cacheMap = new ConcurrentHashMap<String, String>();

	public String getFromCache(String key) {
		return cacheMap.get(key);
	}

	public void setToCache(String key, String value) {
		this.cacheMap.put(key, value);
	}
}
