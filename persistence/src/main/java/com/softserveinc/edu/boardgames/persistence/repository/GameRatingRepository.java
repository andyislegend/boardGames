package com.softserveinc.edu.boardgames.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.softserveinc.edu.boardgames.persistence.entity.GameRating;
import com.softserveinc.edu.boardgames.persistence.entity.dto.AllGamesDto;
import com.softserveinc.edu.boardgames.persistence.entity.dto.GamesChartDTO;

@Repository
public interface GameRatingRepository extends JpaRepository<GameRating, Integer>{

	@Query("select gameRateNum.rating "
			+ "from GameRating gameRateNum "
			+ "where gameRateNum.game.id = :gameId "
			+ "and gameRateNum.user.id = :userId")
	Integer getGameRated(@Param("gameId")Integer gameId, @Param("userId")Integer userId);
	
	@Query("delete from GameRating grn "
			+ "where grn.game.id = :gameId "
			+ "and grn.user.id = :userId")
	@Modifying
	void deleteCustom(@Param("gameId")Integer gameId, @Param("userId")Integer userId);
	
	@Query("select AVG(gr.rating) from GameRating gr "
			+ "where gr.game.id = :gameId and gr.user.id = :userId")
	public Double getForGameAndUser(@Param("gameId")Integer gameId, @Param("userId")Integer userId);
	
	@Query("select AVG(gr.rating) from GameRating gr where gr.game.id = :id")
	public Double getAverageRatingForGame(@Param("id")Integer gameId);
	
	@Query("select gr from GameRating gr where gr.game.id = :gameId and gr.user.id = :userId")
	public List<GameRating> checkIfUserRated(@Param("gameId")Integer gameId, @Param("userId")Integer userId);

	@Query("select COUNT(gr) from GameRating gr where gr.game.id = :id")
	public Integer getCountOfRatingsForGame(@Param("id") Integer id);
	
	@Query("select new com.softserveinc.edu.boardgames.persistence.entity.dto.GamesChartDTO"
			+"(gr.game.name, AVG(gr.rating), COUNT(gr)) "
			+ "from GameRating gr "
			+ "group by gr.game.name")
	public List<GamesChartDTO> getAllRatings();
}
