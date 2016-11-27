package com.saltside.assignment.dto;

//import com.google.common.testing.EqualsTester;
import static com.google.common.collect.ImmutableSortedSet.copyOf;
import static org.testng.Assert.assertEquals;

import java.util.Collection;
import java.util.Set;

import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableSet;

@Test
public class BirdsRequestTest {
	
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	private static final String FAMILY = "family";
	private static final String NAME = "name";
	private static final String ADDED = "added";
	private static final boolean VISIBLE = false;
	private static final Item ITEM = new Item("item");
	private static final Collection<Item> ITEMS = ImmutableSet.of(ITEM); 
	private static final Set<Item> CONTINENTS = copyOf(ITEMS);
	
	
	private BirdsRequest target;
	
	@BeforeMethod
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
		target = new BirdsRequest(NAME, FAMILY, CONTINENTS, ADDED, VISIBLE);
		
		OBJECT_MAPPER.enable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY);
	}
	
	public void test_getters() throws Exception {
		assertEquals(target.getName(), NAME);
		assertEquals(target.getFamily(), FAMILY);
		assertEquals(target.getAdded(), ADDED);
		assertEquals(target.isVisible(), VISIBLE);
		assertEquals(target.getContinents(), CONTINENTS);
	}
	
	public void test_marshaling() throws Exception {
		assertEquals(
				OBJECT_MAPPER.writeValueAsString(target),
				"{\"added\":\"added\",\"continents\":[{\"items\":\"item\"}],\"family\":\"family\",\"name\":\"name\",\"visible\":false}");
	}
	
	/*public void test_unMarshaling() throws Exception {
		final BirdsRequest result = OBJECT_MAPPER
				.readValue("{\"added\":\"added\",\"continents\":[{\"items\":\"item\"}],\"family\":\"family\",\"name\":\"name\",\"visible\":false}", BirdsRequest.class);
		assertEquals(result, target);
	}*/
	
	
}