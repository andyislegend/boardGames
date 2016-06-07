use boardgames;

--  inserts to table country
INSERT INTO country (name) VALUES ('Ukraine');
INSERT INTO country (name) VALUES ('Poland');

--  inserts to table city
INSERT INTO city (name, countryId) VALUES ('Cherkasy', 1);
INSERT INTO city (name, countryId) VALUES ('Chernihiv', 1);
INSERT INTO city (name, countryId) VALUES ('Chernivtsi', 1);
INSERT INTO city (name, countryId) VALUES ('Dnipro', 1);
INSERT INTO city (name, countryId) VALUES ('Donetsk', 1);
INSERT INTO city (name, countryId) VALUES ('Ivano_Frankivsk', 1);
INSERT INTO city (name, countryId) VALUES ('Kharkiv', 1);
INSERT INTO city (name, countryId) VALUES ('Kherson', 1);
INSERT INTO city (name, countryId) VALUES ('Khmelnytskyi', 1);
INSERT INTO city (name, countryId) VALUES ('Kyiv', 1);
INSERT INTO city (name, countryId) VALUES ('Kirovohrad', 1);
INSERT INTO city (name, countryId) VALUES ('Luhansk', 1);
INSERT INTO city (name, countryId) VALUES ('Lviv', 1);
INSERT INTO city (name, countryId) VALUES ('Mykolaiv', 1);
INSERT INTO city (name, countryId) VALUES ('Odessa', 1);
INSERT INTO city (name, countryId) VALUES ('Poltava', 1);
INSERT INTO city (name, countryId) VALUES ('Rivne', 1);
INSERT INTO city (name, countryId) VALUES ('Sumy', 1);
INSERT INTO city (name, countryId) VALUES ('Ternopil', 1);
INSERT INTO city (name, countryId) VALUES ('Vinnytsia', 1);
INSERT INTO city (name, countryId) VALUES ('Lutsk', 1);
INSERT INTO city (name, countryId) VALUES ('Uzhhorod', 1);
INSERT INTO city (name, countryId) VALUES ('Zaporizhia', 1);
INSERT INTO city (name, countryId) VALUES ('Zhytomyr', 1);
INSERT INTO city (name, countryId) VALUES ('Warsaw', 2);
INSERT INTO city (name, countryId) VALUES ('Wroclaw', 2);
INSERT INTO city (name, countryId) VALUES ('Cracow', 2);
INSERT INTO city (name, countryId) VALUES ('Wloclawek', 2);
INSERT INTO city (name, countryId) VALUES ('Gdansk', 2);
INSERT INTO city (name, countryId) VALUES ('Sopot', 2);
INSERT INTO city (name, countryId) VALUES ('Gdynia', 2);
INSERT INTO city (name, countryId) VALUES ('Lublin', 2);
INSERT INTO city (name, countryId) VALUES ('Zabrze', 2);
INSERT INTO city (name, countryId) VALUES ('Olsztyn', 2);
INSERT INTO city (name, countryId) VALUES ('Tychy', 2);
INSERT INTO city (name, countryId) VALUES ('Rzeszow', 2);
INSERT INTO city (name, countryId) VALUES ('Bydgoszcz', 2);
INSERT INTO city (name, countryId) VALUES ('Rybnik', 2);
INSERT INTO city (name, countryId) VALUES ('Szczecin', 2);
INSERT INTO city (name, countryId) VALUES ('Katowice', 2);
INSERT INTO city (name, countryId) VALUES ('Bialystok', 2);
INSERT INTO city (name, countryId) VALUES ('Sosnowiec', 2);
INSERT INTO city (name, countryId) VALUES ('Bytom', 2);


--  inserts to table user


insert into users (age, email, firstName, lastName, password, phoneNumber, userRating, rating, gender, state, username, countryId, cityId,tournamentRatingStatus, is_notificated) values
(99, 'root@root.com', 'Super', 'Admin', '$2a$10$e2qEa0wunoicRAGky9Kd7O..A5YpXbUy3TDMhtrQ3FG3tkYsOpPmW', '+380000000000',  63,'EXTRATERESTRIAL', 'female', 'ACTIVE', 'root', 1, 1,FALSE, FALSE);

insert into users (age, email, firstName, lastName, password, phoneNumber, userRating,rating, gender, state, username, countryId, cityId,tournamentRatingStatus, is_notificated) values
(25, 'kravets@gmail.com', 'Artem', 'Kravets', '$2a$10$e2qEa0wunoicRAGky9Kd7O..A5YpXbUy3TDMhtrQ3FG3tkYsOpPmW', '+380957987654', 0,'NOOB', 'male', 'ACTIVE', 'kravets', 1, 2,FALSE, FALSE);

INSERT INTO `boardgames`.`users` (`id`, `age`, `email`, `firstName`, `gender`, `lastName`, `password`, `phoneNumber`, `rating`, `state`, `userRating`, `username`, `countryId`, `cityId`,tournamentRatingStatus, is_notificated) 
VALUES (NULL, '45', 'prosinecki@pl.net', 'Vladislav', 'male', 'Prosinecki', '$2a$10$e2qEa0wunoicRAGky9Kd7O..A5YpXbUy3TDMhtrQ3FG3tkYsOpPmW', '+37986574545', 'NOOB', 'ACTIVE', '0', 'prosinecki', '2', '33',FALSE, FALSE);

insert into users (age, email, firstName, lastName, password, phoneNumber,userRating, rating, gender, state, username, countryId, cityId,tournamentRatingStatus, is_notificated) values
(28, 'vterlyha@gmail.com', 'Volodymyr', 'Terlyha', '$2a$10$e2qEa0wunoicRAGky9Kd7O..A5YpXbUy3TDMhtrQ3FG3tkYsOpPmW', '+380935787496', 23,'PRO', 'male', 'ACTIVE', 'vterlyha', 1, 13,FALSE, FALSE);

insert into users (age, email, firstName, lastName, password, phoneNumber, userRating,rating, gender, state, username, countryId, cityId,tournamentRatingStatus, is_notificated) values
(37, 'volodymyr_terlyha@ukr.net', 'Body', 'Khariv', '$2a$10$e2qEa0wunoicRAGky9Kd7O..A5YpXbUy3TDMhtrQ3FG3tkYsOpPmW', '+380957987654', -4, 'NOOB', 'male', 'ACTIVE', 'khariv', 2, 33,FALSE, FALSE);

insert into users (age, email, firstName, lastName, password, phoneNumber, userRating,rating, gender, state, username, countryId, cityId,tournamentRatingStatus, is_notificated) values
(23, 'winston@root.com', 'Taras', 'Winston', '$2a$10$e2qEa0wunoicRAGky9Kd7O..A5YpXbUy3TDMhtrQ3FG3tkYsOpPmW', '+380000700000',  55,'WICKED_SICK', 'male', 'BANNED', 'winston', 2, 32,FALSE, FALSE);

insert into users (age, email, firstName, lastName, password, phoneNumber, userRating,rating, gender, state, username, countryId, cityId,tournamentRatingStatus, is_notificated) values
(25, 'borodin@gmail.com', 'Vitalik', 'Borodin', '$2a$10$e2qEa0wusdfoicRAGky9Kd7O..A5YpXbUy3TDMhtrQ3FG3tkYsOpPmW', '+380957987454', 0,'NOOB', 'male', 'ACTIVE', 'borodin', 1, 13,FALSE, FALSE);

insert into users (age, email, firstName, lastName, password, phoneNumber,userRating, rating, gender, state, username, countryId, cityId,tournamentRatingStatus, is_notificated) values
(38, 'nasty@gmail.com', 'Nasty', 'Ptashnyk', '$2a$10$e2qEadffwunoicRAGky9Kd7O..A5YpXbUy3TDMhtrQ3FG3tkYsOpPmW', '+380957987614', 0,'NOOB', 'female', 'ACTIVE', 'ptashnyk', 2, 34,FALSE, FALSE);

insert into users (age, email, firstName, lastName, password, phoneNumber, userRating,rating, gender, state, username, countryId, cityId,tournamentRatingStatus, is_notificated) values
(47, 'lida@gmail.com', 'Lida', 'Berkyt', '$2a$10$e2qEa0rtoicRAGky9Kd7O..A5YpXbUy3TDMhtrQ3FG3tkYsOpPmW', '+380957957654', 0, 'NOOB', 'female', 'ACTIVE', 'berkyt', 1, 11,FALSE, FALSE);

insert into users (age, email, firstName, lastName, password, phoneNumber, userRating,rating, gender, state, username, countryId, cityId,tournamentRatingStatus, is_notificated) values
(26, 'zencarych@gmail.com', 'Oleg', 'Zencarych', '$2a$10$e2qEa0wuno23cRAGky9Kd7O..A5YpXbUy3TDMhtrQ3FG3tkYsOpPmW', '+380957937654', 0,'NOOB', 'male', 'ACTIVE', 'zencarych', 2, 35,FALSE, FALSE);

insert into users (age, email, firstName, lastName, password, phoneNumber,userRating, rating, gender, state, username, countryId, cityId,tournamentRatingStatus, is_notificated) values
(47, 'lama@gmail.com', 'Roman', 'Lama', '$2a$10$e2qEa0wunoicRAGky9Kd7O..A5YpXbUy3TD0htrQ3FG3tkYsOpPmW', '+380907987654', 0,'NOOB', 'male', 'ACTIVE', 'lama', 1, 11,FALSE, FALSE);

insert into users (age, email, firstName, lastName, password, phoneNumber, userRating,rating, gender, state, username, countryId, cityId,tournamentRatingStatus, is_notificated) values
(57, 'vytryk@gmail.com', 'Katy', 'Vytryk', '$2a$10$e2qEa0wunoicRAGky9Kd7O..A5YpXb12y3TDMhtrQ3FG3tkYsOpPmW', '+380951987654', 0, 'NOOB', 'female', 'ACTIVE', 'vytryk', 2, 29,FALSE, FALSE);

insert into users (age, email, firstName, lastName, password, phoneNumber, userRating,rating, gender, state, username, countryId, cityId,tournamentRatingStatus, is_notificated) values
(26, 'plotnikov@root.com', 'Volody', 'Plotnikov', '$2a$10$e2qEa0wernoicRAGky9Kd7O..A5YpXbUy3TDMhtrQ3F83tkYsOpPmW', '+380000780000',  0,'NOOB', 'male', 'ACTIVE', 'plotnikov', 1, 14,FALSE, FALSE);

insert into users (age, email, firstName, lastName, password, phoneNumber, userRating,rating, gender, state, username, countryId, cityId,tournamentRatingStatus, is_notificated) values
(27, 'kaban@gmail.com', 'Sasha', 'Kaban', '$2a$10$e2qEa0wusdfoicRAGky9Kd7O..A5YpXbUy3TDMht673FG3tkYsOpPmW', '+380957917454', 0,'NOOB', 'male', 'ACTIVE', 'kaban', 1, 9,FALSE, FALSE);

insert into users (age, email, firstName, lastName, password, phoneNumber,userRating, rating, gender, state, username, countryId, cityId,tournamentRatingStatus, is_notificated) values
(39, 'konovalchyk@gmail.com', 'Valeriy', 'Konovalchyk', '$2a$10$e2qEadffwunoicRAGky9Kd7O..A5YpXbUy3TDMhtrQ3FG3tkYsOphjW', '+380957987604', 0,'NOOB', 'female', 'ACTIVE', 'konovalchyk', 2, 35,FALSE, FALSE);

insert into users (age, email, firstName, lastName, password, phoneNumber, userRating,rating, gender, state, username, countryId, cityId,tournamentRatingStatus, is_notificated) values
(17, 'trysh@gmail.com', 'Khrustuna', 'Trysh', '$2a$10$e2qEa0rtoicRAGky9Kd7O..A5YsdbUy3TDMhtrQ3FG3tkYsOpPmW', '+388957957654', 0, 'NOOB', 'female', 'ACTIVE', 'trysh', 1, 7,FALSE, FALSE);

insert into users (age, email, firstName, lastName, password, phoneNumber, userRating,rating, gender, state, username, countryId, cityId,tournamentRatingStatus, is_notificated) values
(55, 'aslamov@gmail.com', 'Igor', 'Aslamov', '$2a$10$e2qEa0wunoicRAGky9Kd7O..A5YpXb12y3TDMhtrQ34G3tkYsOpPmW', '+380941987654', 0, 'NOOB', 'male', 'ACTIVE', 'aslamov', 2, 31,FALSE, FALSE);

insert into users (age, email, firstName, lastName, password, phoneNumber, userRating,rating, gender, state, username, countryId, cityId,tournamentRatingStatus, is_notificated) values
(16, 'gorbach@root.com', 'Ivan', 'Gorbach', '$2a$10$e2qEa0wernoicRAGky9Kd7O..A5YpXbUy3TDMhtrQ3F83tkYsj5PmW', '+380000780600',  0,'NOOB', 'male', 'ACTIVE', 'gorbach', 1, 12,FALSE, FALSE);

insert into users (age, email, firstName, lastName, password, phoneNumber, userRating,rating, gender, state, username, countryId, cityId, tournamentRatingStatus, is_notificated) values
(37, 'andreeva@gmail.com', 'Olya', 'Andreeva', '$2a$10$e2qEa0wusdfoicRAGky9Kd7O..A5YpXbUy3TDMht67b3G3tkYsOpPmW', '+380957927454', 0,'NOOB', 'female', 'ACTIVE', 'andreeva', 1, 12,FALSE, FALSE);

insert into users (age, email, firstName, lastName, password, phoneNumber,userRating, rating, gender, state, username, countryId, cityId, tournamentRatingStatus, is_notificated) values
(19, 'konotopska@gmail.com', 'Ulia', 'Konotopska', '$2a$10$e2qEadffwunoicRAGky9Kd7O..A5YpXbUy3TDMhtrQ334G3tkYsOphjW', '+380957487604', 0,'NOOB', 'female', 'ACTIVE', 'konotopska', 2, 36,FALSE, FALSE);

insert into users (age, email, firstName, lastName, password, phoneNumber, userRating,rating, gender, state, username, countryId, cityId, tournamentRatingStatus, is_notificated) values
(87, 'morison@gmail.com', 'Jim', 'Morison', '$2a$10$e2qEa0rtoicRAGky9Kd7O..A5YsdbUy3T22htrQ3FG3tkYsOpPmW', '+388957227654', 0, 'NOOB', 'male', 'ACTIVE', 'morison', 1, 14,FALSE, FALSE);

-- inserts into user_role table
insert into user_role (username, value) values
(1, 'ADMIN'),
(2, 'USER'),
(1, 'SUPERADMIN'),
(1, 'USER');

--  inserts to table category
insert into category (name) values ('strategy');
insert into category (name) values ('children games');
insert into category (name) values ('logical');
insert into category (name) values ('gambling');

--  inserts to table game
insert into game (name,categoryId) values ('Monopoly',1);
insert into game (name,categoryId) values ('Memo',2);
insert into game (name,categoryId) values ('Mafia',1); 
insert into game (name,categoryId) values ('Chess',3);
insert into game (name,categoryId) values ('Skrabble',3);
insert into game (name,categoryId) values ('Tic Tac Toe',2);
insert into game (name,categoryId) values ('Pictionary',3);
insert into game (name,categoryId) values ('Utopia',1);
insert into game (name,categoryId) values ('Dominos',1);
insert into game (name,categoryId) values ('Uno',4);
insert into game (name,categoryId) values ('Bridge',4);
insert into game (name,categoryId) values ('Poker',4);

INSERT INTO `boardgames`.`gamerating` (`rating`, `game_id`, `user_id`) VALUES ('10', '1', '2');
INSERT INTO `boardgames`.`gamerating` (`rating`, `game_id`, `user_id`) VALUES ('9', '2', '1');
INSERT INTO `boardgames`.`gamerating` (`rating`, `game_id`, `user_id`) VALUES ('8', '3', '2');
INSERT INTO `boardgames`.`gamerating` (`rating`, `game_id`, `user_id`) VALUES ('7', '4', '1');
INSERT INTO `boardgames`.`gamerating` (`rating`, `game_id`, `user_id`) VALUES ('6', '5', '3');
INSERT INTO `boardgames`.`gamerating` (`rating`, `game_id`, `user_id`) VALUES ('7', '6', '4');
INSERT INTO `boardgames`.`gamerating` (`rating`, `game_id`, `user_id`) VALUES ('8', '7', '1');
INSERT INTO `boardgames`.`gamerating` (`rating`, `game_id`, `user_id`) VALUES ('9', '1', '1');
INSERT INTO `boardgames`.`gamerating` (`rating`, `game_id`, `user_id`) VALUES ('10', '2', '2');

-- inserts to table of users
INSERT INTO `boardgames`.`gameuser` (`countOfComments`, `description`, `edition`, `maxPlayers`, `minPlayers`, `rules`, `status`, `yearOfProduction`, `game_id`, `user_id`) VALUES ('0', 'Monopoly is a board game that originated in the United States in 1903 
as a way to demonstrate that an economy which rewards wealth creation is better than one in which monopolists work under few constraints and to promote the economic theories of Henry George and in particular his ideas about taxation.
The current version was first published by Parker Brothers in 1935. Subtitled "The Fast-Dealing Property Trading Game", the game is named after the economic concept of monopoly—the domination of a market by a single entity. 
It is now produced by the United States game and toy company Hasbro. Players move around the game-board buying or trading properties, developing their properties with houses and hotels, and collecting rent from their opponents, 
with the goal being to drive them all into bankruptcy leaving one monopolist in control of the entire economy. Since the board game was first commercially sold in the 1930s, it has become a part of popular world culture, 
having been locally licensed in more than 103 countries and printed in more than thirty-seven languages.'
, '2', '4', '2', 'Players take turns in order, with the initial player determined by chance before the game. 
A typical turn begins with the rolling of the dice and advancing their piece clockwise around the board the 
corresponding number of squares. If a player rolls doubles, they roll again after completing their turn. 
If a player rolls three consecutive sets of doubles on one turn, the player has been "caught speeding", 
and the player is immediately sent to jail instead of moving the amount shown on the dice for the third roll, 
ending the players turn.
A player who lands on or passes the Go space collects $200 from the bank. However, 
when the optional Speed Die is used, a player who rolls a triple and chooses to move to "Go to Jail" 
does not collect if the move would normally take them past Go.
Players who land on either Income Tax or Luxury Tax pay the indicated amount to the bank. 
In older editions of the game, two options were given for Income Tax: either pay a flat fee of $200 or 10% 
of the players total worth (including the current values of all the properties and buildings owned). 
Players must choose which option before calculating their total worth, and cannot change their mind if 
it turns out that the $200 was actually less; in 2008, the 10% option was removed. Luxury Tax was originally 
$75; in 2008, it was increased to $100. No reward or penalty is given for landing on Free Parking.', 'PRIVATE', '1900', '1', '1');
INSERT INTO `boardgames`.`gameuser` (`countOfComments`, `description`, `edition`, `maxPlayers`, `minPlayers`, `rules`, `status`, `yearOfProduction`, `game_id`, `user_id`) VALUES ('0', 'Today’s gamers aren’t always perched in front of a video console. 
They are increasingly sitting around a table rolling dice and moving pieces. 
And these modern games aren’t just the classic games like Monopoly and Yahtzee, 
they are innovative new games like the Settlers of Canaan and Qwirkle.
In case you missed the memo, board games are on the comeback, with sales increasing at a rate of 25 percent 
or higher annually the past four years. And this weekend, board game enthusiasts from around the globe will 
converge on the Hyatt Regency in San Francisco for the four-day KublaCon Game Convention. 
Many gamers will compete in the Optimal Board Game Tournament.'
, '2', '4', '2', 'Each player in the tournament will rank their preferences to play the 40 available games .
Factors will be taken into consideration, such as knowledge of the rules, player preferences, and the number of players each game can handle.
Once the pairings are made the games will begin. Each round will add additional constraints. Players that have won all their matches will be paired with each other. 
Players cannot play the same game or the same opponents during the tournament.
By the final round, there will be a single table with all of the undefeated players. 
If there are less than 3 finalists, the top runner-ups will be invited to the championship table.', 'PRIVATE', '1900', '2', '2');
INSERT INTO `boardgames`.`gameuser` (`countOfComments`, `description`, `edition`, `maxPlayers`, `minPlayers`, `rules`, `status`, `yearOfProduction`, `game_id`, `user_id`) VALUES ('0', 'bla bla', '2', '4', '2', 'no', 'PRIVATE', '1900', '3', '3');
INSERT INTO `boardgames`.`gameuser` (`countOfComments`, `description`, `edition`, `maxPlayers`, `minPlayers`, `rules`, `status`, `yearOfProduction`, `game_id`, `user_id`) VALUES ('0', 'bla bla', '2', '4', '2', 'no', 'PRIVATE', '1900', '4', '4');
INSERT INTO `boardgames`.`gameuser` (`countOfComments`, `description`, `edition`, `maxPlayers`, `minPlayers`, `rules`, `status`, `yearOfProduction`, `game_id`, `user_id`) VALUES ('0', 'bla bla', '2', '4', '2', 'no', 'PRIVATE', '1900', '5', '5');
INSERT INTO `boardgames`.`gameuser` (`countOfComments`, `description`, `edition`, `maxPlayers`, `minPlayers`, `rules`, `status`, `yearOfProduction`, `game_id`, `user_id`) VALUES ('0', 'some desc', '5', '6', '2', 'some rule', 'PRIVATE', '2010', '6', '1');
INSERT INTO `boardgames`.`gameuser` (`countOfComments`, `description`, `edition`, `maxPlayers`, `minPlayers`, `rules`, `status`, `yearOfProduction`, `game_id`, `user_id`) VALUES ('0', 'some desc', '6', '8', '2', 'some rule', 'PRIVATE', '2010', '7', '2');
INSERT INTO `boardgames`.`gameuser` (`countOfComments`, `description`, `edition`, `maxPlayers`, `minPlayers`, `rules`, `status`, `yearOfProduction`, `game_id`, `user_id`) VALUES ('0', 'some desc', '7', '8', '4', 'some rule', 'PRIVATE', '2010', '8', '3');
INSERT INTO `boardgames`.`gameuser` (`countOfComments`, `description`, `edition`, `maxPlayers`, `minPlayers`, `rules`, `status`, `yearOfProduction`, `game_id`, `user_id`) VALUES ('0', 'some desc', '3', '6', '4', 'some rule', 'PRIVATE', '2010', '9', '4');
INSERT INTO `boardgames`.`gameuser` (`countOfComments`, `description`, `edition`, `maxPlayers`, `minPlayers`, `rules`, `status`, `yearOfProduction`, `game_id`, `user_id`) VALUES ('0', 'some desc', '8', '8', '4', 'some rule', 'PRIVATE', '2010', '1', '1');
INSERT INTO `boardgames`.`gameuser` (`countOfComments`, `description`, `edition`, `maxPlayers`, `minPlayers`, `rules`, `status`, `yearOfProduction`, `game_id`, `user_id`) VALUES ('0', 'bla bla', '2', '6', '2', 'no', 'PRIVATE', '2010', '7', '2');
INSERT INTO `boardgames`.`gameuser` (`countOfComments`, `description`, `edition`, `maxPlayers`, `minPlayers`, `rules`, `status`, `yearOfProduction`, `game_id`, `user_id`) VALUES ('0', 'bla bla', '3', '4', '2', 'no', 'PRIVATE', '2012', '3', '3');
INSERT INTO `boardgames`.`gameuser` (`countOfComments`, `description`, `edition`, `maxPlayers`, `minPlayers`, `rules`, `status`, `yearOfProduction`, `game_id`, `user_id`) VALUES ('0', 'bla bla', '4', '8', '2', 'no', 'PRIVATE', '2012', '4', '4');
INSERT INTO `boardgames`.`gameuser` (`countOfComments`, `description`, `edition`, `maxPlayers`, `minPlayers`, `rules`, `status`, `yearOfProduction`, `game_id`, `user_id`) VALUES ('0', 'bla bla', '5', '3', '2', 'no', 'PRIVATE', '2012', '5', '5');
INSERT INTO `boardgames`.`gameuser` (`countOfComments`, `description`, `edition`, `maxPlayers`, `minPlayers`, `rules`, `status`, `yearOfProduction`, `game_id`, `user_id`) VALUES ('0', 'bla bla', '2', '6', '2', 'no', 'PRIVATE', '2010', '6', '2');
INSERT INTO `boardgames`.`gameuser` (`countOfComments`, `description`, `edition`, `maxPlayers`, `minPlayers`, `rules`, `status`, `yearOfProduction`, `game_id`, `user_id`) VALUES ('0', 'bla bla', '3', '4', '2', 'no', 'PRIVATE', '2012', '3', '2');
INSERT INTO `boardgames`.`gameuser` (`countOfComments`, `description`, `edition`, `maxPlayers`, `minPlayers`, `rules`, `status`, `yearOfProduction`, `game_id`, `user_id`) VALUES ('0', 'bla bla', '4', '8', '2', 'no', 'PRIVATE', '2012', '4', '2');
INSERT INTO `boardgames`.`gameuser` (`countOfComments`, `description`, `edition`, `maxPlayers`, `minPlayers`, `rules`, `status`, `yearOfProduction`, `game_id`, `user_id`) VALUES ('0', 'bla bla', '5', '3', '2', 'no', 'PRIVATE', '2012', '5', '2');

--  inserts to table status
INSERT INTO `boardgames`.`status` (`statusOfFriend`) VALUES ('NOTCONSIDER');
INSERT INTO `boardgames`.`status` (`statusOfFriend`) VALUES ('ACCEPTED');
INSERT INTO `boardgames`.`status` (`statusOfFriend`) VALUES ('REJECTED');

--  inserts to table friends
INSERT INTO `boardgames`.`friends` (`status`, `user`, `userId`) VALUES ('2', '1', '2');
INSERT INTO `boardgames`.`friends` (`status`, `user`, `userId`) VALUES ('2', '2', '1');
INSERT INTO `boardgames`.`friends` (`status`, `user`, `userId`) VALUES ('2', '1', '3');
INSERT INTO `boardgames`.`friends` (`status`, `user`, `userId`) VALUES ('2', '3', '1');
INSERT INTO `boardgames`.`friends` (`status`, `user`, `userId`) VALUES ('2', '1', '7');
INSERT INTO `boardgames`.`friends` (`status`, `user`, `userId`) VALUES ('2', '7', '1');
INSERT INTO `boardgames`.`friends` (`status`, `user`, `userId`) VALUES ('2', '1', '11');
INSERT INTO `boardgames`.`friends` (`status`, `user`, `userId`) VALUES ('2', '11', '1');
INSERT INTO `boardgames`.`friends` (`status`, `user`, `userId`) VALUES ('2', '1', '5');
INSERT INTO `boardgames`.`friends` (`status`, `user`, `userId`) VALUES ('2', '5', '1');
INSERT INTO `boardgames`.`friends` (`status`, `user`, `userId`) VALUES ('1', '10', '1');
INSERT INTO `boardgames`.`friends` (`status`, `user`, `userId`) VALUES ('1', '12', '1');
INSERT INTO `boardgames`.`friends` (`status`, `user`, `userId`) VALUES ('1', '13', '1');
INSERT INTO `boardgames`.`friends` (`status`, `user`, `userId`) VALUES ('1', '15', '1');
INSERT INTO `boardgames`.`friends` (`status`, `user`, `userId`) VALUES ('1', '16', '1');
INSERT INTO `boardgames`.`friends` (`status`, `user`, `userId`) VALUES ('2', '4', '2');
INSERT INTO `boardgames`.`friends` (`status`, `user`, `userId`) VALUES ('2', '4', '3');
INSERT INTO `boardgames`.`friends` (`status`, `user`, `userId`) VALUES ('1', '4', '4');
INSERT INTO `boardgames`.`friends` (`status`, `user`, `userId`) VALUES ('1', '4', '5');
INSERT INTO `boardgames`.`friends` (`status`, `user`, `userId`) VALUES ('2', '4', '6');

-- inserts to table tournaments

INSERT INTO `boardgames`.`tournament` (`city`, `countOfParticipants`, `country`, `dateOfTournament`, `name`, `game_id`, `userCreator_id`,`isTableGenerated` ) VALUES ('Lviv', '4', 'Ukraine', '2016-05-17 00:00:00', 'Chess Tournamnet', '4', '1',FALSE);
INSERT INTO `boardgames`.`tournament` (`city`, `countOfParticipants`, `country`, `dateOfTournament`, `name`, `game_id`, `userCreator_id`,`isTableGenerated`) VALUES ('Lviv', '6', 'Ukraine', '2016-05-27 00:00:00', 'Monopoly Championship', '1', '3',FALSE);
INSERT INTO `boardgames`.`tournament` (`city`, `countOfParticipants`, `country`, `dateOfTournament`, `name`, `game_id`, `userCreator_id`,`isTableGenerated`) VALUES ('Lviv', '4', 'Ukraine', '2016-05-26 00:00:00', 'Scrable Cup', '1', '3',FALSE);
INSERT INTO `boardgames`.`tournament` (`city`, `countOfParticipants`, `country`, `dateOfTournament`, `name`, `game_id`, `userCreator_id`, `isTableGenerated`) VALUES ('Lviv', '4', 'Ukraine', '2016-05-27 00:00:00', 'Mafia tournament', '3', '3', FALSE);
INSERT INTO `boardgames`.`tournament` (`city`, `countOfParticipants`, `country`, `dateOfTournament`, `name`, `game_id`, `userCreator_id`, `isTableGenerated`) VALUES ('Lviv', '8', 'Ukraine', '2016-05-27 00:00:00', 'Cup of MONOPOLY', '1', '1', FALSE);
INSERT INTO `boardgames`.`tournament` (`city`, `countOfParticipants`, `country`, `dateOfTournament`, `name`, `game_id`, `userCreator_id`, `isTableGenerated`) VALUES ('Lviv', '4', 'Ukraine', '2016-05-25 00:00:00', 'Memo Cup', '2', '1', FALSE);

INSERT INTO `boardgames`.`tournament_users` (`Tournament_id`, `users_id`) VALUES ('4', '2');
INSERT INTO `boardgames`.`tournament_users` (`Tournament_id`, `users_id`) VALUES ('4', '3');
INSERT INTO `boardgames`.`tournament_users` (`Tournament_id`, `users_id`) VALUES ('5', '1');
INSERT INTO `boardgames`.`tournament_users` (`Tournament_id`, `users_id`) VALUES ('5', '2');
INSERT INTO `boardgames`.`tournament_users` (`Tournament_id`, `users_id`) VALUES ('5', '3');
INSERT INTO `boardgames`.`tournament_users` (`Tournament_id`, `users_id`) VALUES ('5', '4');
INSERT INTO `boardgames`.`tournament_users` (`Tournament_id`, `users_id`) VALUES ('5', '5');
INSERT INTO `boardgames`.`tournament_users` (`Tournament_id`, `users_id`) VALUES ('5', '6');
INSERT INTO `boardgames`.`tournament_users` (`Tournament_id`, `users_id`) VALUES ('2', '7');
INSERT INTO `boardgames`.`tournament_users` (`Tournament_id`, `users_id`) VALUES ('2', '8');
INSERT INTO `boardgames`.`tournament_users` (`Tournament_id`, `users_id`) VALUES ('3', '2');
INSERT INTO `boardgames`.`tournament_users` (`Tournament_id`, `users_id`) VALUES ('3', '3');
INSERT INTO `boardgames`.`tournament_users` (`Tournament_id`, `users_id`) VALUES ('4', '1');


--  inserts to table events
INSERT INTO `boardgames`.`events` (`date`, `name`, `description`, `location`) VALUES ('2016-05-27', 'Garage Party', 'We are happy to announce our first Garage party', 'Lviv, UA');
INSERT INTO `boardgames`.`events` (`date`, `name`, `description`, `location`) VALUES ('2016-05-30', 'New Deck for Magic!', 'Kravets just released a new Deck on full elemental monsters', 'Lviv, UA');
INSERT INTO `boardgames`.`events` (`date`, `name`, `description`, `location`) VALUES ('2016-05-30', 'Our first official Tournament', 'Find our First official tournament on Monoploy on our site', 'Lviv, UA');
INSERT INTO `boardgames`.`events` (`date`, `name`, `description`, `location`) VALUES ('2016-05-10', 'Release of new version of Hasbro Uno', 'Hasbro has announced a realese of New version of UNO', 'San-Jose, USA');
INSERT INTO `boardgames`.`events` (`date`, `name`, `description`, `location`) VALUES ('2016-06-15', 'Server shutdown planned', 'Site would be unavialble from 12 CET till 15 CET', 'Stockholm, SWE');
INSERT INTO `boardgames`.`events` (`date`, `name`, `description`, `location`) VALUES ('2016-06-15', 'Release of Monopoly v.5', 'Hasbro has announced a realese of New Monopoly series of Version 5', 'Oakland, USA');
INSERT INTO `boardgames`.`events` (`date`, `name`, `description`, `location`) VALUES ('2016-06-28', 'Server shutdown planned', 'Site would be unavialble from 12 CET till 15 CET', 'Frankfurt, GER');
INSERT INTO `boardgames`.`events` (`date`, `name`, `description`, `location`) VALUES ('2016-06-15', 'Auction', 'Legendary edition of Monopoly will be put at auction', 'Boston, USA');
INSERT INTO `boardgames`.`events` (`date`, `name`, `description`, `location`) VALUES ('2016-05-30', 'Party in da club', 'Selebrating Maria Muzychuk win in world chess series', 'Lviv, UA');
INSERT INTO `boardgames`.`events` (`date`, `name`, `description`, `location`) VALUES ('2016-06-15', 'Opening', 'Opening great board games mall', 'Kyiv, UA');


INSERT INTO `boardgames`.`subscribed_users` (`isNew`, `event_id`, `user_id`) VALUES (TRUE, '1', '1');
INSERT INTO `boardgames`.`subscribed_users` (`isNew`, `event_id`, `user_id`) VALUES (TRUE, '3', '1');
INSERT INTO `boardgames`.`subscribed_users` (`isNew`, `event_id`, `user_id`) VALUES (TRUE, '2', '1');
INSERT INTO `boardgames`.`subscribed_users` (`isNew`, `event_id`, `user_id`) VALUES (TRUE, '1', '2');
INSERT INTO `boardgames`.`subscribed_users` (`isNew`, `event_id`, `user_id`) VALUES (TRUE, '4', '1');

INSERT INTO `boardgames`.`notification` (`id`, `date`, `message`, `status`, `status_of_reading`, `type`, `userId`, `user_sender`, `is_notificated`) VALUES ('1', '2016-05-25 01:12:35', 'Hello', 'UNCHECKED', '1', 'MESSAGE', '2', '1', FALSE);
INSERT INTO `boardgames`.`notification` (`id`, `date`, `message`, `status`, `status_of_reading`, `type`, `userId`, `user_sender`, `is_notificated`) VALUES ('2', '2016-05-25 01:12:37', 'привіт', 'UNCHECKED', '0', 'MESSAGE', '2', '3', FALSE);
INSERT INTO `boardgames`.`notification` (`id`, `date`, `message`, `status`, `status_of_reading`, `type`, `userId`, `is_notificated`) VALUES ('3', '2016-05-28 00:00:00', 'Dear user, you have tournament tomorrow, pleas do not miss it. Name of tournament is \"Monopoly\" it will be in Lviv, Ukraine in ', 'UNCHECKED', '0', 'NOTIFICATION', '1', FALSE);
INSERT INTO `boardgames`.`notification` (`id`, `date`, `message`, `status`, `status_of_reading`, `type`, `userId`, `is_notificated`) VALUES ('4', '2016-06-20 00:00:00', 'Dear user, you have already sent request on \"Garage Party\" event, we hope you will spend time with pleasure. Feel free, enjoy the game and be happy! Event will be in Lviv, UA on ', 'UNCHECKED', '0', 'EVENT', '1', FALSE);
INSERT INTO `boardgames`.`notification` (`id`, `date`, `message`, `status`, `status_of_reading`, `type`, `userId`, `is_notificated`) VALUES ('5', '2016-05-30 00:00:00', 'Dear user, you have already sent request on \"New Deck for Magic!\" event, we hope you will spend time with pleasure. Feel free, enjoy the game and be happy! Event will be in Lviv, UA on ', 'UNCHECKED', '0', 'EVENT', '1', FALSE);
INSERT INTO `boardgames`.`notification` (`id`, `date`, `message`, `status`, `status_of_reading`, `type`, `userId`, `is_notificated`) VALUES ('6', '2016-05-28 00:00:00', 'Dear user, you have tournament tomorrow, pleas do not miss it. Name of tournament is \"Monopoly\" it will be in Lviv, Ukraine in ', 'UNCHECKED', '0', 'NOTIFICATION', '1', FALSE);
INSERT INTO `boardgames`.`notification` (`id`, `date`, `message`, `status`, `status_of_reading`, `type`, `userId`, `is_notificated`) VALUES ('7', '2016-05-28 00:00:00', 'Dear user, you have tournament tomorrow, pleas do not miss it. Name of tournament is \"Monopoly\" it will be in Lviv, Ukraine in ', 'UNCHECKED', '0', 'NOTIFICATION', '1', FALSE);
INSERT INTO `boardgames`.`notification` (`id`, `date`, `message`, `status`, `status_of_reading`, `type`, `userId`, `is_notificated`) VALUES ('8', '2016-05-28 00:00:00', 'Dear user, you have tournament tomorrow, pleas do not miss it. Name of tournament is \"Monopoly\" it will be in Lviv, Ukraine in ', 'UNCHECKED', '0', 'NOTIFICATION', '1', FALSE);
INSERT INTO `boardgames`.`notification` (`id`, `date`, `message`, `status`, `status_of_reading`, `type`, `userId`, `is_notificated`) VALUES ('9', '2016-05-28 00:00:00', 'Dear user, you have tournament tomorrow, pleas do not miss it. Name of tournament is \"Monopoly\" it will be in Lviv, Ukraine in ', 'UNCHECKED', '0', 'NOTIFICATION', '1', FALSE);
INSERT INTO `boardgames`.`notification` (`id`, `date`, `message`, `status`, `status_of_reading`, `type`, `userId`, `is_notificated`) VALUES ('10', '2016-05-28 00:00:00', 'Dear user, you have tournament tomorrow, pleas do not miss it. Name of tournament is \"Monopoly\" it will be in Lviv, Ukraine in ', 'UNCHECKED', '0', 'NOTIFICATION', '1', FALSE);
INSERT INTO `boardgames`.`notification` (`id`, `date`, `message`, `status`, `status_of_reading`, `type`, `userId`, `user_sender`, `is_notificated`) VALUES ('11', '2016-05-25 01:43:27', 'Hi', 'UNCHECKED', '0', 'MESSAGE', '2', '1', FALSE);
INSERT INTO `boardgames`.`notification` (`id`, `date`, `message`, `status`, `status_of_reading`, `type`, `userId`, `user_sender`, `is_notificated`) VALUES ('12', '2016-05-25 01:43:30', 'How are you?', 'UNCHECKED', '0', 'MESSAGE', '2', '1', FALSE);

