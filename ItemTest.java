package com.saltside.assignment.dto;

import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Test
public class ItemTest {
	
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	private static final String ITEM = "items";
	
	
	private Item target;
	
	@BeforeMethod
	public void setup() throws Exception {
		target = new Item(ITEM);
		initMocks(this);
		
		OBJECT_MAPPER.enable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY);
	}
	
	public void test_getters() throws Exception {
		assertEquals(target.getItems(), ITEM);
	}
	
	public void test_marshaling() throws Exception {
		assertEquals(
				OBJECT_MAPPER.writeValueAsString(target),
				"{\"items\":\"items\"}");
	}
	
	public void test_unMarshaling() throws Exception {
		final Item result = OBJECT_MAPPER
				.readValue("{\"items\":\"items\"}", 
						Item.class);
		assertEquals(result, target);
	}
}