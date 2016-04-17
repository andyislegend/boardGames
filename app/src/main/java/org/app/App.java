package org.app;

import java.nio.channels.GatheringByteChannel;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.softserveinc.edu.boardgames.persistence.entity.Game;
import com.softserveinc.edu.boardgames.persistence.repository.GameRepository;
import com.softserveinc.edu.boardgames.persistence.repository.GameUserRepository;

/**
 * Hello world!
 *
 */
public class App 
{
	@Autowired
	static GameRepository gameUserRepository;
	 
	
    public static void main( String[] args )
    {	
        List<Game> games = gameUserRepository.findAll();
        System.out.println(games);
    }
}
