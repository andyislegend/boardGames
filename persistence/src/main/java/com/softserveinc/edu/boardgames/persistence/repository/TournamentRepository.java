package com.softserveinc.edu.boardgames.persistence.repository;

import com.softserveinc.edu.boardgames.persistence.entity.Tournament;
import com.softserveinc.edu.boardgames.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Daria Bondar
 * @since 12.04.2016
 */
public interface TournamentRepository extends JpaRepository<Tournament,Long> {

    Tournament findByName(String name);
}
