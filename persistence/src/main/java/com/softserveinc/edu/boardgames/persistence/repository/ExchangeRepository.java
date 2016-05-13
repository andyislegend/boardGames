package com.softserveinc.edu.boardgames.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.softserveinc.edu.boardgames.persistence.entity.Exchange;

@Repository
public interface ExchangeRepository extends JpaRepository<Exchange, Integer>{

	@Query("select e from Exchange e where e.gameUser.id = :id")
	public Exchange findByGameUserId(@Param("id")Integer id);
}
