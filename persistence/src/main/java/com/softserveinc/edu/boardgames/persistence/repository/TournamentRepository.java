package com.softserveinc.edu.boardgames.persistence.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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
	
	/**
	 * This methos get all TOurnamentsDTO List
	 * 
	 * @return list of tournaments
	 */
    @Query("select new com.softserveinc.edu.boardgames.persistence.entity.dto.TournamentsDTO" +
            "(t.id, t.name,t.countOfParticipants, t.userCreator.id,t.userCreator.username,t.dateOfTournament,t.country.name, t.city.name, t.isTableGenerated"
            + ") from Tournament t")
    public List<TournamentsDTO> getAllTournaments();
    
    /**
	 * This methos get all date of tournament
	 * 
	 * @return date of tournaments
	 */
    @Query("select new com.softserveinc.edu.boardgames.persistence.entity.dto.TournamentsDTO" +
            "(t.dateOfTournament"
            + ") from Tournament t")
    public List<TournamentsDTO> getTournamentDate();
    
    /**
	 * This methos get  TournamentsDTO by Id
	 * 
	 * @return tournament
	 */
    @Query("select new com.softserveinc.edu.boardgames.persistence.entity.dto.TournamentsDTO" +
            "(t.id, t.name,t.countOfParticipants, t.userCreator.id,t.userCreator.username, t.dateOfTournament,t.country.name, t.city.name, t.isTableGenerated"
            + ") from Tournament t where t.id = :id")
    public TournamentsDTO getTournamentsById(@Param("id") Integer id);
    
    /**
     * This methos get all tournament by word
     * 
     * @param key word
     * @return list of tournamnet
     */
    @Query("select new com.softserveinc.edu.boardgames.persistence.entity.dto.TournamentsDTO" +
            "(t.id, t.name,t.countOfParticipants, t.userCreator.id,t.userCreator.username, t.dateOfTournament,t.country.name, t.city.name, t.isTableGenerated"
            + ") from Tournament t where t.name like %:name%")
    public List<TournamentsDTO> findAllTournamentsByWord(@Param("name") String name);
    
    /**
     * This method get all Tournament by username
     * 
     * @param username
     * @return list of tournament
     */
    @Query("select new com.softserveinc.edu.boardgames.persistence.entity.dto.TournamentsDTO" +
            "(t.id, t.name,t.countOfParticipants, t.userCreator.id,t.userCreator.username, t.dateOfTournament,t.country.name, t.city.name, t.isTableGenerated"
            + ") from Tournament t where t.userCreator.username = :username")
    public List<TournamentsDTO> getAllTournamentsByUserCreator(@Param("username")String username);
    
    /**
     * This method delete Tournament by id
     * 
     * @param id
     */
    @Modifying
    @Query("Delete from Tournament t where t.id = :id")
    public void deleteTournament(@Param("id")Integer id);
    
    /**
     * @author Vasyl Bervetskyy
     */
    @Query(value = "SELECT * FROM tournament where dateOfTournament between (CURDATE() + INTERVAL 1 DAY) and (CURDATE() + INTERVAL 2 DAY)", nativeQuery = true)
    public List<Tournament> getAllTommorowTournament();
    
    @Query("select COUNT(t) from Tournament t where t.dateOfTournament = :date")
	public Integer countTournamentForSpecificDate(@Param("date")Date date);
}
