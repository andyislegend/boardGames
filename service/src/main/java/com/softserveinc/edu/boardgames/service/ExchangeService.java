package com.softserveinc.edu.boardgames.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.edu.boardgames.persistence.entity.Exchange;
import com.softserveinc.edu.boardgames.persistence.entity.dto.EventsDTO;
import com.softserveinc.edu.boardgames.persistence.entity.dto.GameUserDTO;
import com.softserveinc.edu.boardgames.persistence.entity.dto.InfoFromApplierDTO;
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
		eRepo.saveAndFlush(exchange);
	}
	
	@Transactional
	public void delete(Exchange exchange) {
		eRepo.delete(exchange);
	}
	
	public Exchange getByGameUserId(Integer id) {
		return eRepo.findByGameUserId(id);
	}
	
	public InfoFromApplierDTO getExchangeDTO(Integer id) {
		return eRepo.getInfoFromAppliersDTO(id);
	}
	
	public List<InfoFromApplierDTO> getAllBorrowedGames(Integer userId) {
		return eRepo.getAllBorrowedGames(userId);
	}
	
	public boolean checkIfBorrowed(Integer userId, Integer gameUserId) {
		return (eRepo.getBorrowedGameUser(gameUserId, userId) != null);
	}
	
	public List<GameUserDTO> selectAllConfirmationsForUser(Integer id) {
		return eRepo.selectAllConfiramtionsForUser(id);
	}
	
	public List<Date> getAllExchangeDates() {
		List<Date> dates = new ArrayList<>();
    	for (Exchange e: this.findAllExchanges()) {
    		Date date = e.getApplyingDate();
    		if (date != null)
    			dates.add(date);
    	}
    	return dates;
	}
	
	public Integer countExchangesOnDate(Date date) {
		return eRepo.countExchangesForSpecificDate(date);
	}
}
