package com.saltside.assignment.service;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.UUID.randomUUID;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.util.Collection;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.saltside.assignment.dto.BirdResponse;
import com.saltside.assignment.dto.BirdsRequest;

@Test
public class BirdServiceTest {
	
	private static final String ID = randomUUID().toString();
	
	@Mock private BirdsRequest birdRequest;
	@Mock private BirdResponse birdResponse;
	@Mock private BirdMediator birdMediator;
	
	
	@InjectMocks private BirdService target;
	
	@BeforeMethod
	public void setup() throws Exception {
		target = new BirdService();
		initMocks(this);
	}
	
	public void test_postBirds_return_HTTP_201() {
		when(birdMediator.save(birdRequest)).thenReturn(birdResponse);
		
		final ResponseEntity<BirdResponse> response = target.postBirds(birdRequest);
		assertNotNull(response);
		assertEquals(response.getStatusCode(), CREATED);
		
		verify(birdMediator).save(birdRequest);
		verifyNoMoreInteractions(birdRequest, birdMediator);
	}
	
	public void test_getBirdById_returns_HTTP_200() {
		when(birdMediator.findBird(ID)).thenReturn(birdResponse);
		
		final ResponseEntity<BirdResponse> response = target.getBirdById(ID);
		assertNotNull(response);
		assertEquals(response.getStatusCode(), OK);
		
		verify(birdMediator).findBird(ID);
		verifyNoMoreInteractions(birdMediator);
		
		verifyZeroInteractions(birdRequest);
	}
	
	public void test_getAllBirds_returns_HTTP_204() {
		when(birdMediator.findAllVisibleBirds()).thenReturn(emptyList());
		
		final ResponseEntity<Collection<BirdResponse>> response = target.getAllBirds();
		assertEquals(response.getStatusCode(), NO_CONTENT);
		
		verify(birdMediator).findAllVisibleBirds();
		verifyNoMoreInteractions(birdMediator);
		
		verifyZeroInteractions(birdRequest, birdResponse);
	}
	
	public void test_getAllBirds_returns_HTTP_200() {
		when(birdMediator.findAllVisibleBirds()).thenReturn(asList(birdResponse));
		
		final ResponseEntity<Collection<BirdResponse>> response = target.getAllBirds();
		assertNotNull(response);
		assertEquals(response.getStatusCode(), OK);
		
		verify(birdMediator).findAllVisibleBirds();
		verifyNoMoreInteractions(birdMediator);
		
		verifyZeroInteractions(birdRequest);
	}
	
	public void test_deleteBird_returns_HTTP_200() {
		target.deleteBird(ID);
		
		verify(birdMediator).deleteBird(ID);
		verifyNoMoreInteractions(birdMediator);
		
		verifyZeroInteractions(birdRequest, birdResponse);
	}
}