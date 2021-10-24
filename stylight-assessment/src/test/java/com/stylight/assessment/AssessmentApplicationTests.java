package com.stylight.assessment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.stylight.assessment.bll.URLService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class AssessmentApplicationTests {

	@Autowired
	URLService service;

	@Test
	@Order(1)
	void uglyToPrettyTest_001() {
		List<String> listUglyUrl = new ArrayList<String>();
		listUglyUrl.add("/products?gender=female&tag=123&tag=1234");
		listUglyUrl.add("/products?gender=female&tag=123&tag=1234&tag=5678");
		listUglyUrl.add("/products?brand=123");
		listUglyUrl.add("/products?gender=female");
		listUglyUrl.add("/products?tag=5678");
		listUglyUrl.add("/products");

		Map<String, String> expected = new HashMap<String, String>();
		expected.put("/products?gender=female&tag=123&tag=1234", "/Women/Shoes/");
		expected.put("/products?gender=female&tag=123&tag=1234&tag=5678", "/Women/Shoes/?tag=5678");
		expected.put("/products?brand=123", "/Adidas/");
		expected.put("/products?gender=female", "/Women/");
		expected.put("/products?tag=5678", "/Boat--Shoes/");
		expected.put("/products", "/Fashion/");

		Map<String, String> actual = service.getuglyToPrettyMap(listUglyUrl);
		assertEquals(expected, actual);
	}

	@Test
	@Order(2)
	void uglyToPrettyTest_002() {
		List<String> listUglyUrl = new ArrayList<String>();
		listUglyUrl.add("/products?gender=female&tag=123&tag=1234");
		listUglyUrl.add("/products?gender=female&tag=123&tag=1234");
		listUglyUrl.add("/products?gender=female&tag=123&tag=1234");
		listUglyUrl.add("/products?gender=female&tag=123&tag=1234");
		listUglyUrl.add("/products?gender=female&tag=123&tag=1234");
		listUglyUrl.add("/products?gender=female&tag=123&tag=1234&tag=5678");
		listUglyUrl.add("/products?gender=female&tag=123&tag=1234&tag=5678/1");
		listUglyUrl.add("/products?gender=female&tag=123&tag=1234&tag=5678/2");
		listUglyUrl.add("/products?gender=female&tag=123&tag=1234&tag=5678/3");
		listUglyUrl.add("/products?gender=female&tag=123&tag=1234&tag=5678/4");
		listUglyUrl.add("/products?gender=female&tag=123&tag=1234&tag=5678/5");
		listUglyUrl.add("/products?gender=female&tag=123&tag=1234&tag=5678/6");
		listUglyUrl.add("/products?gender=female&tag=123&tag=1234&tag=5678/7");
		listUglyUrl.add("/products?gender=female&tag=123&tag=1234&tag=5678/8");
		listUglyUrl.add("/products?gender=female&tag=123&tag=1234&tag=5678/9");
		listUglyUrl.add("/products?gender=female&tag=123&tag=1234&tag=5678/10");
		listUglyUrl.add("/products?gender=female&tag=123&tag=1234&tag=5678/11");
		listUglyUrl.add("/products?gender=female&tag=123&tag=1234&tag=5678/12");
		listUglyUrl.add("/products?gender=female&tag=123&tag=1234&tag=5678/13");
		listUglyUrl.add("/products?gender=female&tag=123&tag=1234&tag=5678/14");
		listUglyUrl.add("/products?gender=female&tag=123&tag=1234&tag=5678/15");
		listUglyUrl.add("/products?gender=female&tag=123&tag=1234&tag=5678/15");
		listUglyUrl.add("/products?gender=female&tag=123&tag=1234&tag=5678/15");
		listUglyUrl.add("/products?gender=female&tag=123&tag=1234&tag=5678/15");
		listUglyUrl.add("/products?gender=female&tag=123&tag=1234&tag=5678/15");
		listUglyUrl.add("/products?gender=female&tag=123&tag=1234&tag=5678/15");
		listUglyUrl.add("/products?gender=female&tag=123&tag=1234&tag=5678/15");

		Map<String, String> expected = new HashMap<String, String>();
		expected.put("/products?gender=female&tag=123&tag=1234", "/Women/Shoes/");
		expected.put("/products?gender=female&tag=123&tag=1234&tag=5678", "/Women/Shoes/?tag=5678");
		expected.put("/products?gender=female&tag=123&tag=1234&tag=5678/1", "/Women/Shoes/?tag=5678/1");
		expected.put("/products?gender=female&tag=123&tag=1234&tag=5678/2", "/Women/Shoes/?tag=5678/2");
		expected.put("/products?gender=female&tag=123&tag=1234&tag=5678/3", "/Women/Shoes/?tag=5678/3");
		expected.put("/products?gender=female&tag=123&tag=1234&tag=5678/4", "/Women/Shoes/?tag=5678/4");
		expected.put("/products?gender=female&tag=123&tag=1234&tag=5678/5", "/Women/Shoes/?tag=5678/5");
		expected.put("/products?gender=female&tag=123&tag=1234&tag=5678/6", "/Women/Shoes/?tag=5678/6");
		expected.put("/products?gender=female&tag=123&tag=1234&tag=5678/7", "/Women/Shoes/?tag=5678/7");
		expected.put("/products?gender=female&tag=123&tag=1234&tag=5678/8", "/Women/Shoes/?tag=5678/8");
		expected.put("/products?gender=female&tag=123&tag=1234&tag=5678/9", "/Women/Shoes/?tag=5678/9");
		expected.put("/products?gender=female&tag=123&tag=1234&tag=5678/10", "/Women/Shoes/?tag=5678/10");
		expected.put("/products?gender=female&tag=123&tag=1234&tag=5678/11", "/Women/Shoes/?tag=5678/11");
		expected.put("/products?gender=female&tag=123&tag=1234&tag=5678/12", "/Women/Shoes/?tag=5678/12");
		expected.put("/products?gender=female&tag=123&tag=1234&tag=5678/13", "/Women/Shoes/?tag=5678/13");
		expected.put("/products?gender=female&tag=123&tag=1234&tag=5678/14", "/Women/Shoes/?tag=5678/14");
		expected.put("/products?gender=female&tag=123&tag=1234&tag=5678/15", "/Women/Shoes/?tag=5678/15");

		Map<String, String> actual = service.getuglyToPrettyMap(listUglyUrl);
		assertEquals(expected, actual);
	}

	@Test
	@Order(3)
	void prettyToUglyTest_001() {
		List<String> listUglyUrl = new ArrayList<String>();

		listUglyUrl.add("/Fashion/");
		listUglyUrl.add("/Boat--Shoes/");
		listUglyUrl.add("/Women/");
		listUglyUrl.add("/Boat--Shoes/123");
		listUglyUrl.add("/Women/Shoes/?tag=5678");

		Map<String, String> expected = new HashMap<String, String>();
		expected.put("/Fashion/", "/products");
		expected.put("/Boat--Shoes/", "/products?tag=5678");
		expected.put("/Women/", "/products?gender=female");
		expected.put("/Boat--Shoes/123", "/Boat--Shoes/123");
		expected.put("/Women/Shoes/?tag=5678", "/Women/Shoes/?tag=5678");

		Map<String, String> actual = service.getPrettyToUglyMap(listUglyUrl);
		assertEquals(expected, actual);
	}

}
