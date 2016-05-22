package com.softserveinc.edu.boardgames.persistence.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.softserveinc.edu.boardgames.persistence.entity.Tournament;
import com.softserveinc.edu.boardgames.persistence.entity.dto.TournamentsDTO;

/**
 * @author Volodymyr Krokhmalyuk
 * 
 */
@Repository
public interface TournamentRepository extends JpaRepository<Tournament,Integer> {

    public Tournament findById(Integer id); 
	public Tournament findByName(String name);
    
	@Query(value ="INSERT INTO tournament_users(Tournament_id, users_id) VALUES (?1,?2)", nativeQuery = true)
	public void addParticipantToTournament(Integer tournament, Integer userId);
	
    @Query("select new com.softserveinc.edu.boardgames.persistence.entity.dto.TournamentsDTO" +
            "(t.id, t.name,t.countOfParticipants, t.userCreator.id,t.userCreator.username, t.dateOfTournament"
            + ") from Tournament t")
    public List<TournamentsDTO> getAllTournaments();
    
    @Query("select new com.softserveinc.edu.boardgames.persistence.entity.dto.TournamentsDTO" +
            "(t.id, t.name,t.countOfParticipants, t.userCreator.id,t.userCreator.username, t.dateOfTournament"
            + ") from Tournament t where t.id = :id")
    public TournamentsDTO getTournamentsById(@Param("id") Integer id);
//    
//    @Query("select new com.softserveinc.edu.boardgames.persistence.entity.dto.AllTournamentsDTO" +
//            "(t.id, t.name, t.userCreator.username, t.country, " +
//            "t.city, t.addition, t.dateOfTournament, t.requiredRating,t.maxParticipants) " +
//            "from Tournament t where t.name like %:name%")
//    public List<AllTournamentsDTO> findAllTournamentsByWord(@Param("name") String name);
    
    /**
     * @author Vasyl Bervetskyy
     */
    @Query(value = "SELECT " +
            "id, name, dateOfTournament, countOfParticipants, country, city"
            + " FROM tournament WHERE id IN "
            + "( SELECT tournament_id FROM tournament_users  WHERE users_id = "
            + "( SELECT id FROM users WHERE username = ?1 ))", nativeQuery = true )
    public List<Object[]> getAllTournamentByUserName(String currentUserName);
}
