package com.softserveinc.edu.boardgames.web.controller;

import com.softserveinc.edu.boardgames.persistence.entity.Address;
import com.softserveinc.edu.boardgames.persistence.entity.User;
import com.softserveinc.edu.boardgames.persistence.entity.dto.AllTournamentsDTO;
import com.softserveinc.edu.boardgames.service.*;
import com.softserveinc.edu.boardgames.persistence.entity.dto.AddTournamentDTO;
import com.softserveinc.edu.boardgames.persistence.entity.Tournament;
import com.softserveinc.edu.boardgames.persistence.entity.TournamentComposition;
import com.softserveinc.edu.boardgames.web.util.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    @Autowired
    GameService gameService;
    @Autowired
    AddressService addressService;

    @RequestMapping(value = "/tournaments", method = RequestMethod.GET)
    public
    @ResponseBody
    List<AllTournamentsDTO> showAllTournaments() {
        return createDTOfromtournamentList();
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
        return createDTOfromtournamentList();
        /*else {
            return null;
        }*/
    }

    @RequestMapping(value = "/addTournament", method = RequestMethod.POST)
    public List<AllTournamentsDTO> addTournament(@RequestBody AddTournamentDTO tournamentDTO) {
        Address address = new Address();
        Tournament tournament = new Tournament();
        if (tournamentDTO.getCountry() != null || tournamentDTO.getCity() != null ||
                tournamentDTO.getStreet() != null || tournamentDTO.getHouseNumber() != null ||
                tournamentDTO.getRoomNumber() != null) {
            if (tournamentDTO.getCountry() != null)
                address.setCountry(tournamentDTO.getCountry());
            if (tournamentDTO.getCity() != null)
                address.setCity(tournamentDTO.getCity());
            if (tournamentDTO.getStreet() != null)
                address.setStreet(tournamentDTO.getStreet());
            if (tournamentDTO.getHouseNumber() != null)
                address.setHouseNumber(Integer.parseInt(tournamentDTO.getHouseNumber()));
            if (tournamentDTO.getRoomNumber() != null)
                address.setRoomNumber(Integer.parseInt(tournamentDTO.getRoomNumber()));

            address = addressService.save(address);
            tournament.setAddress(addressService.findById(address.getId()));
        }
        tournament.setDateOfTournament(tournamentDTO.getDate());
        tournament.setName(tournamentDTO.getTournamentName());
        tournament.setGame(gameService.findByName(tournamentDTO.getGameName()));
        tournament.setRequiredRating(tournamentDTO.getRating());
        tournament.setMaxParticipants(tournamentDTO.getMaxParticipants());
        tournament.setUserCreator(userService.findOne(WebUtil.getPrincipalUsername()));

        tournamentService.save(tournament);
        return createDTOfromtournamentList();
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
                        userGuests, tournament.getMaxParticipants()
                ));
            } else {
                response.add(new AllTournamentsDTO(
                        tournament.getId(),
                        tournament.getName(),
                        tournament.getUserCreator().getUsername(),
                        tournament.getRequiredRating(),
                        tournament.getDateOfTournament().toString(),
                        userGuests,tournament.getMaxParticipants()
                ));
            }
        }
        return response;
    }


}
