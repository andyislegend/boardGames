package com.softserveinc.edu.boardgames.web.controller;

import com.softserveinc.edu.boardgames.dto.AllTournamentsDTO;
import com.softserveinc.edu.boardgames.persistence.entity.Tournament;
import com.softserveinc.edu.boardgames.persistence.entity.TournamentComposition;
import com.softserveinc.edu.boardgames.service.TournamentCompositionService;
import com.softserveinc.edu.boardgames.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Dora on 17.04.2016.
 */

@Controller
public class TournamentController {

    @Autowired
    TournamentService tournamentService;
    @Autowired
    TournamentCompositionService tournamentCompositionService;

    @RequestMapping(value = "/tournaments", method = RequestMethod.GET)
    public @ResponseBody List<AllTournamentsDTO> showAllTournaments() {
        List<AllTournamentsDTO> response=new ArrayList<>();
        List<Tournament> tournaments=tournamentService.findAll();
        List<String> userGuests=new ArrayList<>();
        for(Tournament tournament:tournaments){
            userGuests.clear();
            for(TournamentComposition tournamentComposition:tournamentCompositionService.findByTournamentId(tournament.getId())){
                userGuests.add(tournamentComposition.getUserGuest().getFirstName()+ " "+tournamentComposition.getUserGuest().getLastName());
            }
            response.add(new AllTournamentsDTO(tournament.getName(),
                    tournament.getUserCreator().getFirstName()+ " "+ tournament.getUserCreator().getLastName(),
                    userGuests));
        }
        return response;
    }

}
