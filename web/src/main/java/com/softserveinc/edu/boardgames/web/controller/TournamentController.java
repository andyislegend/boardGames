package com.softserveinc.edu.boardgames.web.controller;

import com.softserveinc.edu.boardgames.dto.AllTournamentsDTO;
import com.softserveinc.edu.boardgames.persistence.entity.Tournament;
import com.softserveinc.edu.boardgames.persistence.entity.TournamentComposition;
import com.softserveinc.edu.boardgames.service.TournamentCompositionService;
import com.softserveinc.edu.boardgames.service.TournamentService;
import com.softserveinc.edu.boardgames.service.UserService;
import com.softserveinc.edu.boardgames.web.util.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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
    @Autowired
    UserService userService;

    @RequestMapping(value = "/tournaments", method = RequestMethod.GET)
    public @ResponseBody List<AllTournamentsDTO> showAllTournaments() {

        return createDTOfromtournamentList();
    }

    /*@RequestMapping(value = "/tournaments", method = RequestMethod.GET)
    public @ResponseBody List<Tournament> showAllTournaments() {
        List<Tournament> tournaments = tournamentService.findAll();

        return tournaments;
    }*/

    @RequestMapping(value = "/joinTournament",method = RequestMethod.POST)
    public @ResponseBody List<AllTournamentsDTO> joinTournamnet(@RequestBody Integer id) throws UnableToJoinTournament{
        TournamentComposition tournamentComposition=new TournamentComposition();
        tournamentComposition.setTournament(tournamentService.findById(Long.parseLong(String.valueOf(id))));
        tournamentComposition.setUserGuest(userService.findOne(WebUtil.getPrincipalUsername()));
        tournamentCompositionService.save(tournamentComposition);
        if(tournamentCompositionService.findByTournamentId(Long.parseLong(String.valueOf(id)))
                .contains(tournamentComposition)){
            throw new UnableToJoinTournament("You have already join this tournament");
        }
        return createDTOfromtournamentList();
    }

    private List<AllTournamentsDTO> createDTOfromtournamentList(){
        List<AllTournamentsDTO> response = new ArrayList<>();
        List<Tournament> tournaments = tournamentService.findAll();

        List<TournamentComposition> compositions;

        for (Tournament tournament : tournaments) {
            List<String> userGuests = new ArrayList<>();

            compositions = tournament.getTournamentComposition();

            for (TournamentComposition tournamentComposition : compositions) {
                userGuests.add(tournamentComposition.getUserGuest().getFirstName() + " " + tournamentComposition.getUserGuest().getLastName());
            }
            response.add(new AllTournamentsDTO(tournament.getId(),tournament.getName(),tournament.getUserCreator().getUsername(),
                    String.valueOf(tournament.getRequiredRating()),userGuests));
        }
        return response;
    }
    private class UnableToJoinTournament extends Exception{

        public UnableToJoinTournament(String message) {
            super(message);
        }
    }
}
