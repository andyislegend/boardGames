package com.softserveinc.edu.boardgames.persistence.repository;

import com.softserveinc.edu.boardgames.persistence.entity.TournamentComposition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Daria Bondar
 * @since 12.04.2016
 */
public interface TournamentCompositionRepository extends JpaRepository<TournamentComposition,Long> {

    List<TournamentComposition> findByTournamentId(Long id);
}
