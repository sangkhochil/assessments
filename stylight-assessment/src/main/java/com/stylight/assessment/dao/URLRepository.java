package com.stylight.assessment.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

@Repository
public class URLRepository {
	
	private static Map<String, String> uglyToPretty = new ConcurrentHashMap<String, String>();
	private static Map<String, String> prettyToUgly = new ConcurrentHashMap<String, String>();
	
	static {
		load();
	}

	private static void load() {
		String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		String iconConfigPath = rootPath + "data.xml";

		Properties urlPros = new Properties();
		try {
			urlPros.loadFromXML(new FileInputStream(iconConfigPath));

			Iterator<Entry<Object, Object>> ir = urlPros.entrySet().iterator();
			while (ir.hasNext()) {
				Entry<Object, Object> entry = ir.next();
				uglyToPretty.put(entry.getKey().toString(), entry.getValue().toString());
				prettyToUgly.put(entry.getValue().toString(), entry.getKey().toString());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Map<String, String> getUglyToPretty() {
		//return new ConcurrentHashMap<String, String>(uglyToPretty);
		return Collections.unmodifiableMap(uglyToPretty);
	}

	public Map<String, String> getPrettyToUgly() {
//		return new ConcurrentHashMap<String, String>(prettyToUgly);
		return Collections.unmodifiableMap(prettyToUgly);
	}
}
