package com.devsuperior.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.demo.dto.EventDTO;
import com.devsuperior.demo.entities.City;
import com.devsuperior.demo.entities.Event;
import com.devsuperior.demo.exceptions.ResourceNotFoundException;
import com.devsuperior.demo.repositories.EventRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EventService {
	@Autowired
	private EventRepository repository;
	
	@Transactional
	public EventDTO update(Long id,EventDTO dto) {
		try {
		Event event=repository.getReferenceById(id);
		event.setName(dto.getName());
		event.setDate(dto.getDate());
		event.setUrl(dto.getUrl());
		City city=new City(dto.getCityId(),null);
		event.setCity(city);
		event=repository.save(event);
		return new EventDTO(event);}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id "+id+" Not Found");
		}
	}
}
