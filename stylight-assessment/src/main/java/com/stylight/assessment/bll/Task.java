package com.stylight.assessment.bll;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stylight.assessment.caching.URLCacheAgent;
import com.stylight.assessment.dao.URLRepository;
import com.stylight.assessment.model.Dummy;

public class Task implements Callable<Map<String, String>> {

	URLRepository urlRepository;
	URLCacheAgent cacheAgent;
	private List<String> listUglyUrl;
	private static final Logger logger = LoggerFactory.getLogger(Task.class);

	public Task(List<String> listUglyUrl, URLRepository urlRepository, URLCacheAgent cacheAgent) {
		this.listUglyUrl = listUglyUrl;
		this.urlRepository = urlRepository;
		this.cacheAgent = cacheAgent;
	}

	@Override
	public Map<String, String> call() throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		try {
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
		} catch (Exception ex) {
			throw ex;
		}

		return map;
	}

	private String makeURL(String url) {
		String prettyUrl = "";
		logger.debug("Making url...");
		
		String[] split = url.split("\\?");
		if (urlRepository.getUglyToPretty().containsKey(split[0])) {
			prettyUrl = urlRepository.getUglyToPretty().get(split[0]);
		} else {
			return url;
		}

		if (split.length == 1)
			return prettyUrl;

		String[] secondSplit = split[1].split("&");

		int i;
		LinkedList<Dummy> dummyList = new LinkedList<Dummy>();
		StringBuffer sb = new StringBuffer(split[0]);
		sb.append("?");

		for (i = 0; i < secondSplit.length; i++) {
			sb.append(secondSplit[i]);
			if (urlRepository.getUglyToPretty().containsKey(sb.toString())) {
				prettyUrl = urlRepository.getUglyToPretty().get(sb.toString());
				dummyList.addFirst(new Dummy(secondSplit[i], true));
			} else {
				dummyList.addFirst(new Dummy(secondSplit[i], false));
			}

			if (i < secondSplit.length - 1)
				sb.append("&");
		}

		sb = new StringBuffer(prettyUrl);
		sb.append("?");

		StringBuffer params = new StringBuffer("");
		while (!dummyList.isEmpty()) {
			Dummy dummy = dummyList.poll();
			if (!dummy.isFind()) {
				params.insert(0, dummy.getParam() + "&");
			} else {
				break;
			}
		}
		sb.append(params.substring(0, params.length() - 1));

		// caching
		cacheAgent.setToCache(url, sb.toString());

		return sb.toString();
	}
}
