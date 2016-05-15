package com.softserveinc.edu.boardgames.web.controller;

import com.softserveinc.edu.boardgames.persistence.entity.dto.TournamentsDTO;
import com.softserveinc.edu.boardgames.persistence.entity.mapper.TournamentMapper;
import com.softserveinc.edu.boardgames.service.*;
import com.softserveinc.edu.boardgames.persistence.entity.Tournament;
import com.softserveinc.edu.boardgames.web.util.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Volodymyr Krokhmaliuk
 */

@Controller
public class TournamentController {

    @Autowired
    private TournamentService tournamentService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private GameUserService gameUserService;
   
//    @RequestMapping(value = "/tournaments", method = RequestMethod.GET)
//    public
//    @ResponseBody
//    List<AllTournamentsDTO> showAllTournaments() {
//        return getAllTournaments();
//    }

//    @RequestMapping(value = "/joinTournament", method = RequestMethod.POST)
//    public
//    @ResponseBody
//    List<AllTournamentsDTO> joinTournamnet(@RequestBody Long id) {
//        User user = userService.findOne(WebUtil.getPrincipalUsername());
//        Long count = (long) 1;
//        List<TournamentComposition> tournamentCompositions = tournamentCompositionService.findByTournamentId(id);
//        for (TournamentComposition tournamentComposition : tournamentCompositions) {
//
//            count = tournamentCompositionService.findCountUserGuest(user.getUsername(), tournamentComposition.getId());
//            if (count != 0) {
//                break;
//            }
//        }
//        if (count == 0) {
//            TournamentComposition tournamentComposition = new TournamentComposition();
//            tournamentComposition.setTournament(tournamentService.findById(Long.parseLong(String.valueOf(id))));
//            tournamentComposition.setUserGuest(user);
//            tournamentCompositionService.save(tournamentComposition);
//        }
//        return getAllTournaments();
//    }

    @RequestMapping(value = "/addTournament", method = RequestMethod.POST)
    public void addTournament(@RequestBody TournamentsDTO tournamentDTO) {
        Tournament tournament = TournamentMapper.toEntity(tournamentDTO);
        tournament.setUserCreator(userService.getUser(WebUtil.getPrincipalUsername()));
        tournament.setGame(gameUserService.getUserGamesById(tournamentDTO.getGameUserId()));
        tournamentService.save(tournament);
    }

//    private List<AllTournamentsDTO> getAllTournaments() {
//        List<AllTournamentsDTO> response = tournamentService.findAllTournamentsDTO();
//        for (AllTournamentsDTO tournamentsDTO : response) {
//            tournamentsDTO.setUserGuests(tournamentCompositionService.findAllUserGuestsByTournament(tournamentsDTO.getTournamentId()));
//            tournamentsDTO.setCountParticipants(tournamentCompositionService.findAllUserGuestsByTournament
//                    (tournamentsDTO.getTournamentId()).size());
//            tournamentsDTO.setIsCanJoin(
//                    (tournamentsDTO.getCountParticipants() == tournamentsDTO.getMaxParticipants() ||
//                            tournamentsDTO.getUsername().equals(WebUtil.getPrincipalUsername()) ||
//                            tournamentsDTO.getUserGuests().contains(WebUtil.getPrincipalUsername())) ? true : false);
//        }
//        return response;
//    }

}
