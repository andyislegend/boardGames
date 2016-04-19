
use boardgames;

--  inserts to table address
INSERT INTO `boardgames`.`address` (`city`, `country`, `houseNumber`, `postCode`, `roomNumber`, `street`) VALUES ('Lviv', 'Ukraine', '100', '79002', '23', 'pr. Svobody');
INSERT INTO `boardgames`.`address` (`city`, `country`, `houseNumber`, `postCode`, `roomNumber`, `street`) VALUES ('Kyiv', 'Ukraine', '56', '86005', '78', 'Checheta');
INSERT INTO `boardgames`.`address` (`city`, `country`, `houseNumber`, `postCode`, `roomNumber`, `street`) VALUES ('Donetsk', 'Ukraine', '12', '77445', '25', 'Ozerna');
INSERT INTO `boardgames`.`address` (`city`, `country`, `houseNumber`, `postCode`, `roomNumber`, `street`) VALUES ('Odessa', 'Ukraine', '86', '98765', '49', 'Shyroka');


--  inserts to table user
insert into users (age, email, firstName, lastName, password, phoneNumber, userRating,rating, sex, state, username, addressId) values
(99, 'root@root.com', 'Super', 'Admin', '$2a$10$e2qEa0wunoicRAGky9Kd7O..A5YpXbUy3TDMhtrQ3FG3tkYsOpPmW', '+380000000000',  0,'GODLIKE', 'none', 'ACTIVE', 'root', 1);

insert into users (age, email, firstName, lastName, password, phoneNumber, userRating,rating, sex, state, username, addressId) values
(25, 'kravets@gmail.com', 'Artem', 'Kravets', '$2a$10$e2qEa0wunoicRAGky9Kd7O..A5YpXbUy3TDMhtrQ3FG3tkYsOpPmW', '+380957987654', 0,'NOOB', 'male', 'ACTIVE', 'kravets', 2);

insert into users (age, email, firstName, lastName, password, phoneNumber,userRating, rating, sex, state, username, addressId) values
(37, 'bondar@gmail.com', 'Ivan', 'Bondar', '$2a$10$e2qEa0wunoicRAGky9Kd7O..A5YpXbUy3TDMhtrQ3FG3tkYsOpPmW', '+380957987654', 0,'NOOB', 'male', 'ACTIVE', 'bondar', 3);

insert into users (age, email, firstName, lastName, password, phoneNumber, userRating,rating, sex, state, username, addressId) values
(37, 'khariv@gmail.com', 'Body', 'Khariv', '$2a$10$e2qEa0wunoicRAGky9Kd7O..A5YpXbUy3TDMhtrQ3FG3tkYsOpPmW', '+380957987654', 0, 'NOOB', 'male', 'ACTIVE', 'khariv', 3);

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


--  inserts to table game
insert into game (name,description,minPlayers,maxPlayers,categoryId, gameRating) values ('Monopoly','Bla la description',  2,4,1,0.0);
insert into game (name,description,minPlayers,maxPlayers,categoryId, gameRating) values ('Memo','Bla la description',  2,4,2,0.0);
insert into game (name,description,minPlayers,maxPlayers,categoryId, gameRating) values ('Mafia','Bla la description',  2,4,1,0.0); 
insert into game (name,description,minPlayers,maxPlayers,categoryId, gameRating) values ('Chess','Bla la description',  2,4,3,0.0);
insert into game (name,description,minPlayers,maxPlayers,categoryId, gameRating) values ('Skrabble','Bla la description',  2,4,3,0.0);
insert into game (name,description,minPlayers,maxPlayers,categoryId, gameRating) values ('Tic Tac Toe','Bla la description',  2,4,2,0.0);
insert into game (name,description,minPlayers,maxPlayers,categoryId, gameRating) values ('Pictionary','Bla la description',  2,4,3,0.0);
insert into game (name,description,minPlayers,maxPlayers,categoryId, gameRating) values ('Utopia','Bla la description',  2,4,1,0.0);
insert into game (name,description,minPlayers,maxPlayers,categoryId, gameRating) values ('Dominos','Bla la description',  2,4,1,0.0);


--  inserts to table status
INSERT INTO `boardgames`.`status` (`statusOfFriend`) VALUES ('NOTCONSIDER');
INSERT INTO `boardgames`.`status` (`statusOfFriend`) VALUES ('ACCEPTED');
INSERT INTO `boardgames`.`status` (`statusOfFriend`) VALUES ('REJECTED');


--  inserts to table friends
INSERT INTO `boardgames`.`friends` (`status`, `user_one`, `user_two`) VALUES ('2', '1', '2');
INSERT INTO `boardgames`.`friends` (`status`, `user_one`, `user_two`) VALUES ('2', '3', '1');
INSERT INTO `boardgames`.`friends` (`status`, `user_one`, `user_two`) VALUES ('1', '4', '2');
INSERT INTO `boardgames`.`friends` (`status`, `user_one`, `user_two`) VALUES ('1', '2', '3');
INSERT INTO `boardgames`.`friends` (`status`, `user_one`, `user_two`) VALUES ('3', '4', '1');


--  inserts to table tournament
insert into tournament (name,idUserCreator,gameid) values ('MonopolyTournament',1,1);
insert into tournament (name,idUserCreator,gameid) values ('MEMOTournament',2,2);

--  inserts to table tournament_composition
insert into tournament_composition (tournamentId,userGuestId) values (1,2);
insert into tournament_composition (tournamentId,userGuestId) values (1,3);
insert into tournament_composition (tournamentId,userGuestId) values (2,4);

--  inserts to table events
INSERT INTO `boardgames`.`events` (`name`, `description`, `imgSrc`, `date`, `place`, `userId`, `gameId`) VALUES ('Everybody sleeps but mafia members wake up', 'Mafia event', 'resources/images/mafiaImg.jpg', '2016-06-06', 'Lviv', '1', '3');
INSERT INTO `boardgames`.`events` (`name`, `description`, `imgSrc`, `date`, `place`, `userId`, `gameId`) VALUES ('Game of kings', 'Chess event', 'resources/images/chessImg.jpg', '2016-06-18', 'Lviv', '2', '4');
INSERT INTO `boardgames`.`events` (`name`, `description`, `imgSrc`, `date`, `place`, `userId`, `gameId`) VALUES ('One of the best games of the Western world', 'Skrabble event', 'resources/images/skrabbleImg.jpg', '2016-06-20', 'Lviv', '3', '5');
INSERT INTO `boardgames`.`events` (`name`, `description`, `imgSrc`, `date`, `place`, `userId`, `gameId`) VALUES ('Come with your kids: fun for everybody', 'Tic Tac Toe event', 'resources/images/tic-tac-toeImg.jpg', '2016-06-28', 'Lviv', '4', '6');
INSERT INTO `boardgames`.`events` (`name`, `description`, `imgSrc`, `date`, `place`, `userId`, `gameId`) VALUES ('If America was a game of Monopoly', 'Monopoly event', 'resources/images/monopolyImg.jpg', '2016-07-02', 'Lviv', '2', '1');
INSERT INTO `boardgames`.`events` (`name`, `description`, `imgSrc`, `date`, `place`, `userId`, `gameId`) VALUES ('Loving and Linkin', 'Dominos event', 'resources/images/dominosImg.jpg', '2016-07-13', 'Lviv', '3', '9');
INSERT INTO `boardgames`.`events` (`name`, `description`, `imgSrc`, `date`, `place`, `userId`, `gameId`) VALUES ('When your body is the canvas', 'Pictionary event', 'resources/images/pictionaryImg.jpg', '2016-07-22', 'Lviv', '2', '7');
INSERT INTO `boardgames`.`events` (`name`, `description`, `imgSrc`, `date`, `place`, `userId`, `gameId`) VALUES ('This is simply a wonderful visual feast', 'Utopia event', 'resources/images/utopiaImg.jpg', '2016-08-20', 'Lviv', '1', '8');

