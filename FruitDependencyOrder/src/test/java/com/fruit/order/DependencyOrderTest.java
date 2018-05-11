package com.fruit.order;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.fruit.model.FruitNode;

public class DependencyOrderTest {

	@Test
	public void testEmpty() {
		FruitNode[] order = new DependencyOrder().findDependencyOrder(null);
		assertNull(order);
	}
	
	@Test
	public void testSingleValueWithNoChildren() {
		Map<String, List<String>> map = new LinkedHashMap<String, List<String>>();
        map.put("orange", Collections.<String>emptyList());
		FruitNode[] order = new DependencyOrder().findDependencyOrder(map);
		assertEquals(1, order.length);
		assertEquals("orange", order[0].getName());
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testInvalidGraph() {
		Map<String, List<String>> map = new LinkedHashMap<String, List<String>>();
        map.put("apple", Arrays.asList("orange","banana"));
        map.put("banana", Arrays.asList("strawberry"));
        map.put("orange", Collections.<String>emptyList());
        map.put("papaya", Collections.<String>emptyList());
        
        new DependencyOrder().findDependencyOrder(map);
	}
	
	@Test
	public void testValidGraphOrder() {
		Map<String, List<String>> map = new LinkedHashMap<String, List<String>>();
        map.put("apple", Arrays.asList("orange","banana"));
        map.put("banana", Arrays.asList("strawberry"));
        map.put("strawberry", Arrays.asList("orange"));
        map.put("orange", Collections.<String>emptyList());
        map.put("papaya", Collections.<String>emptyList());
        
        FruitNode[] order = new DependencyOrder().findDependencyOrder(map);
        
        assertEquals(5, order.length);
		assertEquals("orange", order[0].getName());
		assertEquals("papaya", order[1].getName());
		assertEquals("strawberry", order[2].getName());
		assertEquals("banana", order[3].getName());
		assertEquals("apple", order[4].getName());
		
	}
	
	
}
