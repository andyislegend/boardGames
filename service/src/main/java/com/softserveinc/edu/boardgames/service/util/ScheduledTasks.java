package com.softserveinc.edu.boardgames.service.util;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.softserveinc.edu.boardgames.persistence.entity.Exchange;
import com.softserveinc.edu.boardgames.service.ExchangeService;
import com.softserveinc.edu.boardgames.service.MailService;
import com.softserveinc.edu.boardgames.service.UserService;

@Component
public class ScheduledTasks {
	
	@Autowired
	MailService mailService;
	
	@Autowired
	ExchangeService exchangeService;
	
	@Autowired
	UserService userService;

    @Scheduled(fixedRate = 86400000)
    public void reportCurrentTime() {
    	for (Exchange exchange: exchangeService.findAllExchanges()) {
    		
    		Date localDate = new Date();
    		Date deadLine = exchange.getApplyingDate();
    		Calendar calendar = Calendar.getInstance();
    		calendar.setTime(deadLine);
    		calendar.add(Calendar.DATE, exchange.getPeriod()); 
    		deadLine = calendar.getTime();
    		Long days = (deadLine.getTime() - localDate.getTime())/ (24 * 60 * 60 * 1000);
    		if (days.intValue() == 1) {
    			mailService.remindAboutGameReturnTomorrow(
    					userService.findById(exchange.getUserApplierId()).getEmail(), 
    					userService.findById(exchange.getUserApplierId()).getUsername(),
    					exchange.getGameUser().getGame().getName(), 
    					exchange.getUser().getUsername());
    		}
    	}
    }
}
