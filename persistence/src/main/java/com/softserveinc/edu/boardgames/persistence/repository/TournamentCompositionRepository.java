package com.softserveinc.edu.boardgames.persistence.repository;

import com.softserveinc.edu.boardgames.persistence.entity.TournamentComposition;
import com.softserveinc.edu.boardgames.persistence.entity.dto.AllTournamentsDTO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Daria Bondar
 * @since 12.04.2016
 */
@Repository
public interface TournamentCompositionRepository extends JpaRepository<TournamentComposition, Long> {


    List<TournamentComposition> findByTournamentId(Long id);

    @Query(value = "select count(t) from TournamentComposition t where t.userGuest.username = :username or" +
            " t.tournament.userCreator.username = :username and t.id=:id")
    Long findCountUserGuest(@Param("username")String username,@Param("id")Long idComposition);
    
    @Query("Select t FROM Tournament t WHERE t.userCreator.username =:username")
	public List<TournamentComposition> findUserByUsername(@Param("username") String username);
    
    @Query("select new com.softserveinc.edu.boardgames.persistence.entity.dto.AllTournamentsDTO" +
			"(t.id, t.name, t.userCreator.username, t.country, " +
			"t.city, t.addition," +
			" t.dateOfTournament, t.requiredRating) " +
    		"from Tournament t where t.userCreator.id =:id")
	public List<AllTournamentsDTO> getUserTournamentsByUserId(@Param("id")Long id);

    @Query("select t.userGuest.username" +
            " from TournamentComposition t where t.tournament.id=:tournamentId")
    public List<String> findAllUserGuestsByTournament(@Param("tournamentId") Long id);
    
    
}
