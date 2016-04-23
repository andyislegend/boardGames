package com.softserveinc.edu.boardgames.persistence.repository;

import com.softserveinc.edu.boardgames.persistence.entity.TournamentComposition;
import com.softserveinc.edu.boardgames.persistence.entity.User;

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
    
    @Query("Select t FROM TournamentComposition t WHERE t.userGuest.username =:username")
	public List<TournamentComposition> findUserByUsername(@Param("username") String username);
}
