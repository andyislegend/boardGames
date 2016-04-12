package com.softserveinc.edu.boardgames.service;

import com.softserveinc.edu.boardgames.persistence.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Daria Bondar
 * @since 12.04.2016
 */
@Service
public class TournamentService {

    @Autowired
    TournamentRepository tournamentRepository;
}
