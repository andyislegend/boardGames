package com.softserveinc.edu.boardgames.web.controller;

import com.softserveinc.edu.boardgames.persistence.entity.Address;
import com.softserveinc.edu.boardgames.persistence.entity.User;
import com.softserveinc.edu.boardgames.persistence.entity.dto.AddTournamentDTO;
import com.softserveinc.edu.boardgames.persistence.entity.dto.AllTournamentsDTO;
import com.softserveinc.edu.boardgames.service.*;
import com.softserveinc.edu.boardgames.persistence.entity.Tournament;
import com.softserveinc.edu.boardgames.persistence.entity.TournamentComposition;
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
 * @author Daria Bondar
 */

@Controller
public class TournamentController {

    @Autowired
    TournamentService tournamentService;
    @Autowired
    TournamentCompositionService tournamentCompositionService;
    @Autowired
    UserService userService;
    @Autowired
    GameService gameService;
    @Autowired
    AddressService addressService;

    @RequestMapping(value = "/tournaments", method = RequestMethod.GET)
    public
    @ResponseBody
    List<AllTournamentsDTO> showAllTournaments() {
        //List<AllTournamentsDTO> response=tournamentService.
        return null;
    }

    @RequestMapping(value = "/joinTournament", method = RequestMethod.POST)
    public
    @ResponseBody
    List<AllTournamentsDTO> joinTournamnet(@RequestBody Long id) throws Exception {
        User user = userService.findOne(WebUtil.getPrincipalUsername());
        Long count;
        count = tournamentCompositionService.findCountUserGuest(user.getUsername(), id);
        if (count == 0) {
            TournamentComposition tournamentComposition = new TournamentComposition();
            tournamentComposition.setTournament(tournamentService.findById(Long.parseLong(String.valueOf(id))));
            tournamentComposition.setUserGuest(user);
            tournamentCompositionService.save(tournamentComposition);
        }
        return null;
    }

    @RequestMapping(value = "/addTournament", method = RequestMethod.POST)
    public List<AllTournamentsDTO> addTournament(@RequestBody AddTournamentDTO tournamentDTO) {
        Address address = new Address();
        Tournament tournament = new Tournament();

        tournament.setDateOfTournament(tournamentDTO.getDate());
        tournament.setName(tournamentDTO.getTournamentName());
        tournament.setGame(gameService.findByName(tournamentDTO.getGameName()));
        tournament.setRequiredRating(tournamentDTO.getRating());
        tournament.setMaxParticipants(tournamentDTO.getMaxParticipants());
        tournament.setUserCreator(userService.findOne(WebUtil.getPrincipalUsername()));

        tournamentService.save(tournament);
        return null;
    }




}
