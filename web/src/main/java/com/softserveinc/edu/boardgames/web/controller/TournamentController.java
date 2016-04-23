package com.softserveinc.edu.boardgames.web.controller;

import com.softserveinc.edu.boardgames.service.dto.AllTournamentsDTO;
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
import java.util.List;

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
    public
    @ResponseBody
    List<AllTournamentsDTO> showAllTournaments() {
        return createDTOfromtournamentList();
    }

    @RequestMapping(value = "/joinTournament", method = RequestMethod.POST)
    public
    @ResponseBody
    List<AllTournamentsDTO> joinTournamnet(@RequestBody Integer id) {
        TournamentComposition tournamentComposition = new TournamentComposition();
        tournamentComposition.setTournament(tournamentService.findById(Long.parseLong(String.valueOf(id))));
        tournamentComposition.setUserGuest(userService.findOne(WebUtil.getPrincipalUsername()));
        tournamentCompositionService.save(tournamentComposition);
        return createDTOfromtournamentList();
    }

    @RequestMapping(value = "/addTournament", method = RequestMethod.PUT)
    public void addTournament(@RequestBody Tournament tournament) {
        tournamentService.save(tournament);
    }

    private List<AllTournamentsDTO> createDTOfromtournamentList() {
        List<AllTournamentsDTO> response = new ArrayList<>();
        List<Tournament> tournaments = tournamentService.findAll();
        List<TournamentComposition> compositions;
        Long countUser;
        for (Tournament tournament : tournaments) {
            List<String> userGuests = new ArrayList<>();

            compositions = tournament.getTournamentComposition();
            countUser = (long) 0;
            for (TournamentComposition tournamentComposition : compositions) {
                userGuests.add(tournamentComposition.getUserGuest().getUsername());
                countUser = tournamentCompositionService.findCountUserGuest(WebUtil.getPrincipalUsername(), tournamentComposition.getId());
            }

            if (tournament.getAddress() != null) {
                response.add(new AllTournamentsDTO(
                        tournament.getId(),
                        tournament.getName(),
                        tournament.getUserCreator().getUsername(),
                        tournament.getAddress().getCountry(),
                        tournament.getAddress().getCity(),
                        tournament.getAddress().getStreet(),
                        tournament.getAddress().getHouseNumber(),
                        tournament.getAddress().getRoomNumber(),
                        tournament.getRequiredRating(),
                        tournament.getDateOfTournament().toString(),
                        userGuests
                ));
            } else {
                response.add(new AllTournamentsDTO(
                        tournament.getId(),
                        tournament.getName(),
                        tournament.getUserCreator().getUsername(),
                        tournament.getRequiredRating(),
                        tournament.getDateOfTournament().toString(),
                        userGuests
                ));
            }
        }
        return response;
    }


}
