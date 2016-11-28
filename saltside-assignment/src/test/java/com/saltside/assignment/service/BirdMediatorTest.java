package com.saltside.assignment.service;

import static com.google.common.collect.ImmutableSet.of;
import static com.google.common.collect.ImmutableSortedSet.copyOf;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Objects.nonNull;
import static java.util.UUID.randomUUID;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.util.Collection;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.saltside.assignment.data.model.Bird;
import com.saltside.assignment.data.repository.BirdRepository;
import com.saltside.assignment.dto.BirdResponse;
import com.saltside.assignment.dto.BirdsRequest;
import com.saltside.assignment.dto.Item;
import com.saltside.assignment.exception.BirdNotFoundException;

@Test
public class BirdMediatorTest {
	
	private static final String FAMILY = "family";
	private static final String NAME = "name";
	private static final String ADDED = "added";
	private static final boolean VISIBLE = false;
	private static final String ID = randomUUID().toString();
	
	@Mock private BirdsRequest birdRequest;
	@Mock private Item item;
	@Mock private BirdResponse birdResponse;
	@Mock private Bird bird;
	@Mock private com.saltside.assignment.data.model.Item dbItem;
	@Mock private BirdRepository birdRepository;
	
	
	@InjectMocks private BirdMediator target;
	
	@BeforeMethod
	public void setup() throws Exception {
		target = new BirdMediator();
		initMocks(this);
	}
	
	public void test_save_returns_birdResponse() {
		when(birdRequest.getAdded()).thenReturn(ADDED);
		when(birdRequest.getFamily()).thenReturn(FAMILY);
		when(birdRequest.getName()).thenReturn(NAME);
		when(birdRequest.isVisible()).thenReturn(VISIBLE);
		when(item.getItems()).thenReturn("item");
		when(birdRequest.getContinents()).thenReturn(copyOf(of(item)));
		when(bird.getAdded()).thenReturn(ADDED);
		when(bird.getFamily()).thenReturn(FAMILY);
		when(bird.getName()).thenReturn(NAME);
		when(bird.isVisible()).thenReturn(VISIBLE);
		when(dbItem.getItems()).thenReturn("item");
		when(bird.getId()).thenReturn(ID);
		when(bird.getContinents()).thenReturn(copyOf(of(dbItem)));
		when(birdRepository.save(any(Bird.class))).thenReturn(bird);
		
		final BirdResponse result = target.save(birdRequest);
		assertNotNull(nonNull(result));
		
		verify(birdRequest).getFamily();
		verify(birdRequest).getName();
		verify(birdRequest).getContinents();
		verify(birdRequest).isVisible();
		verify(item).getItems();
		verify(bird).getAdded();
		verify(bird).getFamily();
		verify(bird).getName();
		verify(bird).getContinents();
		verify(dbItem).getItems();
		verify(bird).isVisible();
		verify(bird).getId();
		verify(birdRepository).save(any(Bird.class));
		
		verifyNoMoreInteractions(birdRequest, bird, item, dbItem, birdRepository);
	}
	
	public void test_findBird_throws_BirdNotFoundException() {
		when(birdRepository.findOne(ID)).thenReturn(null);
		try {
			target.findBird(ID);
			fail();
		} catch (BirdNotFoundException e) {
		}
		verify(birdRepository).findOne(ID);
		verifyNoMoreInteractions(birdRepository);
		
		verifyZeroInteractions(birdRequest, bird, item, dbItem);
	}
	
	public void test_findBird_returns_success() {
		when(bird.getAdded()).thenReturn(ADDED);
		when(bird.getFamily()).thenReturn(FAMILY);
		when(bird.getName()).thenReturn(NAME);
		when(bird.isVisible()).thenReturn(VISIBLE);
		when(dbItem.getItems()).thenReturn("item");
		when(bird.getId()).thenReturn(ID);
		when(bird.getContinents()).thenReturn(copyOf(of(dbItem)));
		when(birdRepository.findOne(ID)).thenReturn(bird);
		
		final BirdResponse result = target.findBird(ID);
		assertNotNull(result);
		
		verify(bird).getAdded();
		verify(bird).getFamily();
		verify(bird).getName();
		verify(bird).getContinents();
		verify(dbItem).getItems();
		verify(bird).isVisible();
		verify(bird).getId();
		verify(birdRepository).findOne(ID);
		
		verifyNoMoreInteractions(birdRepository, bird);
		
		verifyZeroInteractions(birdRequest, item);
	}
	
	public void test_findAllVisibleBirds_returns_emptyList() {
		when(birdRepository.findByVisibleIsTrue()).thenReturn(emptyList());
		
		final Collection<BirdResponse> result = target.findAllVisibleBirds();
		assertTrue(result.isEmpty());
		
		verify(birdRepository).findByVisibleIsTrue();
		verifyNoMoreInteractions(birdRepository);
		
		verifyZeroInteractions(birdRequest, bird, item, dbItem);
	}
	
	public void test_findAllVisibleBirds_returns_birdResponse() {
		when(bird.getAdded()).thenReturn(ADDED);
		when(bird.getFamily()).thenReturn(FAMILY);
		when(bird.getName()).thenReturn(NAME);
		when(bird.isVisible()).thenReturn(VISIBLE);
		when(dbItem.getItems()).thenReturn("item");
		when(bird.getId()).thenReturn(ID);
		when(bird.getContinents()).thenReturn(copyOf(of(dbItem)));
		when(birdRepository.findByVisibleIsTrue()).thenReturn(asList(bird));
		
		final Collection<BirdResponse> result = target.findAllVisibleBirds();
		assertTrue(!result.isEmpty());
		
		verify(birdRepository).findByVisibleIsTrue();
		verifyNoMoreInteractions(birdRepository, bird);
		
		verifyZeroInteractions(birdRequest, item);
	}
	
	public void test_deleteBird_throws_BirdNotFoundException() {
		when(birdRepository.removeById(ID)).thenReturn(0L);
		try {
			target.deleteBird(ID);
			fail();
		} catch (BirdNotFoundException e) {
		}
		verify(birdRepository).removeById(ID);
		verifyNoMoreInteractions(birdRepository);
		
		verifyZeroInteractions(birdRequest, bird, item, dbItem);
	}
	
	public void test_deleteBird_returns_success() {
		when(birdRepository.removeById(ID)).thenReturn(1L);
		
		target.deleteBird(ID);
		
		verify(birdRepository).removeById(ID);
		verifyNoMoreInteractions(birdRepository);
		
		verifyZeroInteractions(birdRequest, bird, item, dbItem);
	}
}