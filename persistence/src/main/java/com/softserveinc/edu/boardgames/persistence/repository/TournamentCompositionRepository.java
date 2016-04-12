package com.softserveinc.edu.boardgames.persistence.repository;

import com.softserveinc.edu.boardgames.persistence.entity.TournamentComposition;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Daria Bondar
 * @since 12.04.2016
 */
public interface TournamentCompositionRepository extends JpaRepository<TournamentComposition,Long> {
}
