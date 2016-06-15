package com.softserveinc.edu.boardgames.persistence.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.softserveinc.edu.boardgames.persistence.entity.Exchange;
import com.softserveinc.edu.boardgames.persistence.entity.dto.GameUserDTO;
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
	
	@Query("select new com.softserveinc.edu.boardgames.persistence.entity.dto.InfoFromApplierDTO(gameUser.id, "
			+ "exchange.user.username, exchange.message , gameUser.game.name, gameUser.game.category.name) "
	        + "from Exchange exchange "
	        + "inner join exchange.gameUser gameUser "
	        + "where exchange.userApplierId = :userId "
	        + "and gameUser.status = 'SHARED'")
	public List<InfoFromApplierDTO> getAllBorrowedGames(@Param("userId")Integer userId);
	
	@Query("select e from Exchange e "
			+ "inner join e.gameUser gu "
			+ "where e.userApplierId = :userId "
			+ "and gu.id = :gameUserId "
			+ "and gu.status = 'SHARED'")
	public Exchange getBorrowedGameUser(@Param("gameUserId")Integer gameUserId, @Param("userId")Integer urserId);

	@Query("select new com.softserveinc.edu.boardgames.persistence.entity.dto.GameUserDTO("
			+ "e.id, gu.game.name, gu.edition, gu.yearOfProduction, u.username) "
			+ "from Exchange e "
			+ "inner join e.gameUser gu "
			+ "inner join e.user u "
			+ "where gu.status='SHARED' "
			+ "and e.userApplierId=:userId")
	public List<GameUserDTO> selectAllConfiramtionsForUser(@Param("userId")Integer id);
	
	@Query(value="select datediff(now(), applyingDate) from exchnge where id = 1", nativeQuery=true)
	public Integer getExchangeDateDiffrence(@Param("id")Integer id);
	
	@Query(value="select datediff(DATE_ADD(:applyingDate, INTERVAL :period DAY), now()) from exchnge ", nativeQuery=true)
	public Integer getApplyingDateDiffrence(@Param("applyingDate")Date applyingDate, @Param("period")Integer period);
}
