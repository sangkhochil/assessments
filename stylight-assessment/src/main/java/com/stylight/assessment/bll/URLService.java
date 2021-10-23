package com.stylight.assessment.bll;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

		String[] split = url.split("\\?");
		if (urlRepository.getUglyToPretty().containsKey(split[0])) {
			prettyUrl = urlRepository.getUglyToPretty().get(split[0]);
		} else {
			return url;
		}
		
		if(split.length == 1) return prettyUrl;
		
		String[] secondSplit = split[1].split("&");
		
		int i;
		LinkedList<Dummy> dummyList = new LinkedList<Dummy>();		
		StringBuffer sb = new StringBuffer(split[0]);
		sb.append("?");
		
		for (i = 0; i < secondSplit.length; i++) {
			sb.append(secondSplit[i]);
			if(urlRepository.getUglyToPretty().containsKey(sb.toString())) {
				prettyUrl = urlRepository.getUglyToPretty().get(sb.toString());
				dummyList.addFirst(new Dummy(secondSplit[i], true));
			} else {
				dummyList.addFirst(new Dummy(secondSplit[i], false));
			}
			
			if(i < secondSplit.length - 1)
				sb.append("&");
		}
		
		sb = new StringBuffer(prettyUrl);
		sb.append("?");
//		for(; i < secondSplit.length; i++) {
//			sb.append(secondSplit[i]);
//			if(i < secondSplit.length - 1)
//				sb.append("&");
//		}
		StringBuffer params = new StringBuffer("");
		while(!dummyList.isEmpty()) {
			Dummy dummy = dummyList.poll();
			if(!dummy.isFind()) {
				params.insert(0, dummy.getParam()+"&");
			} else {
				break;
			}
		}
		sb.append(params.substring(0, params.length()-1));
		
		//caching
		cacheAgent.setToCache(url, sb.toString());

		return sb.toString();
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
