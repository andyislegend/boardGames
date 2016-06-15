package com.softserveinc.edu.boardgames.service;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.edu.boardgames.persistence.entity.Exchange;
import com.softserveinc.edu.boardgames.persistence.entity.dto.GameUserDTO;
import com.softserveinc.edu.boardgames.persistence.entity.dto.InfoFromApplierDTO;

public interface ExchangeService {

	Exchange findById(Integer id);
	
	List<Exchange> findAllExchanges();
	
	@Transactional
	void update(Exchange exchange);
	
	@Transactional
	void delete(Exchange exchange);
	
	Exchange getByGameUserId(Integer id);
	
	InfoFromApplierDTO getExchangeDTO(Integer id);
	
	List<InfoFromApplierDTO> getAllBorrowedGames(Integer userId);
	
	boolean checkIfBorrowed(Integer userId, Integer gameUserId);
	
	List<GameUserDTO> selectAllConfirmationsForUser(Integer id);
	
	Integer getHowManyDaysRemains(Date applyingDate, Integer period);
}
