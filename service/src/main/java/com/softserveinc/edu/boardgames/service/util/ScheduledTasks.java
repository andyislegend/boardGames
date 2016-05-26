package com.softserveinc.edu.boardgames.service.util;

import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.softserveinc.edu.boardgames.persistence.entity.User;
import com.softserveinc.edu.boardgames.persistence.entity.VerificationToken;
import com.softserveinc.edu.boardgames.persistence.enumeration.TimeEnum;
import com.softserveinc.edu.boardgames.service.ExchangeService;
import com.softserveinc.edu.boardgames.service.MailService;
import com.softserveinc.edu.boardgames.service.UserService;
import com.softserveinc.edu.boardgames.persistence.entity.Exchange;

/**
 * @author Taras Varvariuk
 * Java util component class performing scheduled tasks
 */
@Component
public class ScheduledTasks {
	
	@Autowired
	MailService mailService;
	
	@Autowired
	ExchangeService exchangeService;
	
	@Autowired
	UserService userService;
	
	private final Logger logger = Logger.getLogger(MailService.class);

    @Scheduled(cron = "0 33 16 * * *")
    public void checkIfOneDayRemains() {
    	for (Exchange exchange: exchangeService.findAllExchanges()) {
    		
    		Date localDate = new Date();
    		Date deadLine = exchange.getApplyingDate();
    		Calendar calendar = Calendar.getInstance();
    		calendar.setTime(deadLine);
    		calendar.add(Calendar.DATE, exchange.getPeriod()); 
    		deadLine = calendar.getTime();
    		Long days = (deadLine.getTime() - localDate.getTime())
    				/ (TimeEnum.HOURS.getValue() * TimeEnum.MINUTES.getValue() 
    				* TimeEnum.SECONDS.getValue() * TimeEnum.MILISECONDS.getValue());
    		if (days.intValue() == 1) {
    			mailService.remindAboutGameReturnTomorrow(
    					userService.findById(exchange.getUserApplierId()).getEmail(), 
    					userService.findById(exchange.getUserApplierId()).getUsername(),
    					exchange.getGameUser().getGame().getName(), 
    					exchange.getUser().getUsername());
    			logger.info("Message reminding about exchanges term is out for user " 
    					+ userService.findById(exchange.getUserApplierId()).getUsername() 
    					+ " was successfully sent");
    		}
    	}
    	logger.info("16:30 - Checking for users that have one day more to give the game back");
    }
    
    @Scheduled(cron = "0 0 12 ? * WED")
    public void remindThatNeedsToGiveBack() {
    	for (Exchange exchange: exchangeService.findAllExchanges()) {
    		Date localDate = new Date();
    		Date deadLine = exchange.getApplyingDate();
    		Calendar calendar = Calendar.getInstance();
    		calendar.setTime(deadLine);
    		calendar.add(Calendar.DATE, exchange.getPeriod()); 
    		deadLine = calendar.getTime();
    		Long days = (deadLine.getTime() - localDate.getTime())
    				/ (TimeEnum.HOURS.getValue() * TimeEnum.MINUTES.getValue() 
    				* TimeEnum.SECONDS.getValue() * TimeEnum.MILISECONDS.getValue());
    		if (days.intValue() < 1) {
    			mailService.remindThatYouAreLate(
    					userService.findById(exchange.getUserApplierId()).getEmail(), 
    					userService.findById(exchange.getUserApplierId()).getUsername(),
    					(-days.intValue()),
    					exchange.getGameUser().getGame().getName(), 
    					exchange.getUser().getUsername());
    			logger.info("Message reminding that a deadline for giving gam back for " 
    					+ userService.findById(exchange.getUserApplierId()).getUsername() 
    					+ " has run out");
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
