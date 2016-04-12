package com.softserveinc.edu.boardgames.service;

import com.softserveinc.edu.boardgames.persistence.repository.TournamentCompositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Daria Bondar
 * @since 12.04.2016
 */

@Service
public class TournamentCompositionService {

    @Autowired
    TournamentCompositionRepository tournamentCompositionRepository;
}
