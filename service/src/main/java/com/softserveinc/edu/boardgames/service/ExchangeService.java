package com.softserveinc.edu.boardgames.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.edu.boardgames.persistence.entity.Exchange;
import com.softserveinc.edu.boardgames.persistence.repository.ExchangeRepository;

@Service
@Transactional
public class ExchangeService {

	@Autowired
	private ExchangeRepository eRepo;
	
	public Exchange findById(Integer id) {
		return eRepo.findOne(id);
	}
	
	public List<Exchange> findAllExchanges() {
		return eRepo.findAll();
	}
	
	@Transactional
	public void update(Exchange exchange) {
		eRepo.save(exchange);
	}
}
