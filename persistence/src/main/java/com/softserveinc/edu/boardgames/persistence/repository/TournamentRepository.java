package com.softserveinc.edu.boardgames.persistence.repository;

import com.softserveinc.edu.boardgames.persistence.entity.Tournament;
import com.softserveinc.edu.boardgames.persistence.entity.User;
import com.softserveinc.edu.boardgames.persistence.entity.dto.AllTournamentsDTO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Volodymyr Krokhmalyuk
 * 
 */
@Repository
public interface TournamentRepository extends JpaRepository<Tournament,Integer> {

     public Tournament findByName(String name);
    
//    @Query("select new com.softserveinc.edu.boardgames.persistence.entity.dto.AllTournamentsDTO" +
//			"(t.id, t.name, t.userCreator.username, t.country, " +
//			"t.city, t.addition, t.dateOfTournament, t.requiredRating) " +
//    		"from Tournament t where t.userCreator.username =:username")
//	public List<AllTournamentsDTO> getUserTournamentsByUserName(@Param("username")String username);
//
    @Query("select new com.softserveinc.edu.boardgames.persistence.entity.dto.AllTournamentsDTO" +
            "(t.id, t.name,t.countOfParticipants, t.userCreator.id,t.userCreator.username, t.dateOfTournament"
            + ") from Tournament t")
    public List<AllTournamentsDTO> getAllTournaments();
    
    @Query("select new com.softserveinc.edu.boardgames.persistence.entity.dto.AllTournamentsDTO" +
            "(t.id, t.name,t.countOfParticipants, t.userCreator.id,t.userCreator.username, t.dateOfTournament"
            + ") from Tournament t where t.id = :id")
    public AllTournamentsDTO getTournamentsById(@Param("id") Integer id);
//    
//    @Query("select new com.softserveinc.edu.boardgames.persistence.entity.dto.AllTournamentsDTO" +
//            "(t.id, t.name, t.userCreator.username, t.country, " +
//            "t.city, t.addition, t.dateOfTournament, t.requiredRating,t.maxParticipants) " +
//            "from Tournament t where t.name like %:name%")
//    public List<AllTournamentsDTO> findAllTournamentsByWord(@Param("name") String name);
}
