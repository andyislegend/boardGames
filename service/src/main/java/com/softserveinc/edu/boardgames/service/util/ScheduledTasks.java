package com.softserveinc.edu.boardgames.service.util;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.softserveinc.edu.boardgames.persistence.entity.Exchange;
import com.softserveinc.edu.boardgames.persistence.entity.User;
import com.softserveinc.edu.boardgames.persistence.entity.VerificationToken;
import com.softserveinc.edu.boardgames.service.ExchangeService;
import com.softserveinc.edu.boardgames.service.MailService;
import com.softserveinc.edu.boardgames.service.UserService;

/**
 * @author Taras Varvariuk
 * Java util component class performing scheduled tasks
 */
@Component
public class ScheduledTasks {
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private ExchangeService exchangeService;
	
	@Autowired
	private UserService userService;
	
    @Scheduled(cron = "0 33 16 * * *")
    public void checkIfOneDayRemains() {
    	for (Exchange exchange: exchangeService.findAllExchanges()) {
    		
    		if (exchangeService.getExchangeDateDiffrence(exchange.getId()).equals(-1)) {
    			mailService.remindAboutGameReturnTomorrow(
    					userService.findById(exchange.getUserApplierId()).getEmail(), 
    					userService.findById(exchange.getUserApplierId()).getUsername(),
    					exchange.getGameUser().getGame().getName(), 
    					exchange.getUser().getUsername());
    		}
    	}
    }
    
    @Scheduled(cron = "0 0 12 ? * WED")
    public void remindThatNeedsToGiveBack() {
    	for (Exchange exchange: exchangeService.findAllExchanges()) {

    		Integer daysDiff = exchangeService.getExchangeDateDiffrence(exchange.getId());
    		if ( daysDiff > -1) {
    			mailService.remindThatYouAreLate(
    					userService.findById(exchange.getUserApplierId()).getEmail(), 
    					userService.findById(exchange.getUserApplierId()).getUsername(),
    					daysDiff,
    					exchange.getGameUser().getGame().getName(), 
    					exchange.getUser().getUsername());
    		}
    	}
    }
	
	@Scheduled(cron = "0 0 12 ? * WED")
	public void deleteExpiredTokensAndUsers() {
		for (VerificationToken token : userService.findAllTokens()) {

			final Calendar calendar = Calendar.getInstance();

			if ((token.getExpiryDate().getTime() - calendar.getTime().getTime()) <= 0) {
				User userToDelete = userService.getUserByToken(token.getToken());
				userService.removeToken(token);
				userService.deleteUser(userToDelete);
			}
		}
	}

}
