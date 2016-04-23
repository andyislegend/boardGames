package com.softserveinc.edu.boardgames.persistence.repository;

import com.softserveinc.edu.boardgames.persistence.entity.Tournament;
import com.softserveinc.edu.boardgames.persistence.entity.User;
import com.softserveinc.edu.boardgames.persistence.entity.dto.AllTournamentsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Daria Bondar
 * @since 12.04.2016
 */
@Repository
public interface TournamentRepository extends JpaRepository<Tournament,Long> {

    Tournament findByName(String name);

    /*@Query("select t.id as tournamentId, t.name as tournamentName, t.userCreator.username as username," +
            " t.game.name as gameName, t.address.country, t.address.city, t.address.street, t.address.houseNumber," +
            "t.address.roomNumber from Tournament t")
    List<AllTournamentsDTO> findAllTournamentsDTO();*/
}
