package com.softserveinc.edu.boardgames.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.softserveinc.edu.boardgames.persistence.entity.Exchange;
import com.softserveinc.edu.boardgames.persistence.entity.dto.InfoFromApplierDTO;

@Repository
public interface ExchangeRepository extends JpaRepository<Exchange, Integer>{

	@Query("select e from Exchange e where e.gameUser.id = :id")
	public Exchange findByGameUserId(@Param("id")Integer id);
	
	@Query("select new com.softserveinc.edu.boardgames.persistence.entity.dto.InfoFromApplierDTO("
			+ "user.username, exchange.message) "
	        + "from Exchange exchange, User user "
	        + "where exchange.id = :id "
	        + "and user.id = exchange.userApplierId")
	public InfoFromApplierDTO getInfoFromAppliersDTO(@Param("id")Integer id);
}
