package com.softserveinc.edu.boardgames.service.Impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.edu.boardgames.persistence.entity.Exchange;
import com.softserveinc.edu.boardgames.persistence.entity.dto.GameUserDTO;
import com.softserveinc.edu.boardgames.persistence.entity.dto.InfoFromApplierDTO;
import com.softserveinc.edu.boardgames.persistence.repository.ExchangeRepository;
import com.softserveinc.edu.boardgames.service.ExchangeService;

/**
 * @author Taras Varvariuk
 */
@Service
@Transactional
public class ExchangeServiceImpl implements ExchangeService{

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

	public Integer getExchangeDateDiffrence(Integer id) {
		return eRepo.getExchangeDateDiffrence(id);
	}

	public Integer getApplyingDateDiffrence(Date date, Integer period) {
		return eRepo.getApplyingDateDiffrence(date, period);
	}
}
