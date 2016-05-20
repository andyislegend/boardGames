use boardgames;

--  inserts to table country
INSERT INTO country (name) VALUES ('Ukraine');
INSERT INTO country (name) VALUES ('Poland');

--  inserts to table city
INSERT INTO city (name, countryId) VALUES ('Cherkasy', 1);
INSERT INTO city (name, countryId) VALUES ('Chernihiv', 1);
INSERT INTO city (name, countryId) VALUES ('Chernivtsi', 1);
INSERT INTO city (name, countryId) VALUES ('Dnipropetrovsk', 1);
INSERT INTO city (name, countryId) VALUES ('Donetsk', 1);
INSERT INTO city (name, countryId) VALUES ('Ivano-Frankivsk', 1);
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


insert into users (age, email, firstName, lastName, password, phoneNumber, userRating, rating, gender, state, username, countryId, cityId) values
(99, 'root@root.com', 'Super', 'Admin', '$2a$10$e2qEa0wunoicRAGky9Kd7O..A5YpXbUy3TDMhtrQ3FG3tkYsOpPmW', '+380000000000',  0,'GODLIKE', 'female', 'ACTIVE', 'root', 1, 1);

insert into users (age, email, firstName, lastName, password, phoneNumber, userRating,rating, gender, state, username, countryId, cityId) values
(25, 'kravets@gmail.com', 'Artem', 'Kravets', '$2a$10$e2qEa0wunoicRAGky9Kd7O..A5YpXbUy3TDMhtrQ3FG3tkYsOpPmW', '+380957987654', 0,'NOOB', 'male', 'ACTIVE', 'kravets', 1, 2);

INSERT INTO `boardgames`.`users` (`id`, `age`, `email`, `firstName`, `gender`, `lastName`, `password`, `phoneNumber`, `rating`, `state`, `userRating`, `username`, `countryId`, `cityId`) 
VALUES (NULL, '45', 'prosinecki', 'vladislav', 'male', 'prosinecki', '$2a$10$e2qEa0wunoicRAGky9Kd7O..A5YpXbUy3TDMhtrQ3FG3tkYsOpPmW', '+37986574545', 'NOOB', 'ACTIVE', '0', 'prosinecki', '2', '33');

insert into users (age, email, firstName, lastName, password, phoneNumber,userRating, rating, gender, state, username, countryId, cityId) values
(37, 'bondar@gmail.com', 'Ivan', 'Bondar', '$2a$10$e2qEa0wunoicRAGky9Kd7O..A5YpXbUy3TDMhtrQ3FG3tkYsOpPmW', '+380957987654', 0,'NOOB', 'male', 'ACTIVE', 'bondar', 1, 13);

insert into users (age, email, firstName, lastName, password, phoneNumber, userRating,rating, gender, state, username, countryId, cityId) values
(37, 'khariv@gmail.com', 'Body', 'Khariv', '$2a$10$e2qEa0wunoicRAGky9Kd7O..A5YpXbUy3TDMhtrQ3FG3tkYsOpPmW', '+380957987654', 0, 'NOOB', 'male', 'ACTIVE', 'khariv', 2, 33);

insert into users (age, email, firstName, lastName, password, phoneNumber, userRating,rating, gender, state, username, countryId, cityId) values
(23, 'winston@root.com', 'Taras', 'Winston', '$2a$10$e2qEa0wernoicRAGky9Kd7O..A5YpXbUy3TDMhtrQ3FG3tkYsOpPmW', '+380000700000',  0,'NOOB', 'male', 'ACTIVE', 'winston', 2, 32);

insert into users (age, email, firstName, lastName, password, phoneNumber, userRating,rating, gender, state, username, countryId, cityId) values
(25, 'borodin@gmail.com', 'Vitalik', 'Borodin', '$2a$10$e2qEa0wusdfoicRAGky9Kd7O..A5YpXbUy3TDMhtrQ3FG3tkYsOpPmW', '+380957987454', 0,'NOOB', 'male', 'ACTIVE', 'borodin', 1, 13);

insert into users (age, email, firstName, lastName, password, phoneNumber,userRating, rating, gender, state, username, countryId, cityId) values
(38, 'nasty@gmail.com', 'Nasty', 'Ptashnyk', '$2a$10$e2qEadffwunoicRAGky9Kd7O..A5YpXbUy3TDMhtrQ3FG3tkYsOpPmW', '+380957987614', 0,'NOOB', 'female', 'ACTIVE', 'ptashnyk', 2, 34);

insert into users (age, email, firstName, lastName, password, phoneNumber, userRating,rating, gender, state, username, countryId, cityId) values
(47, 'lida@gmail.com', 'Lida', 'Berkyt', '$2a$10$e2qEa0rtoicRAGky9Kd7O..A5YpXbUy3TDMhtrQ3FG3tkYsOpPmW', '+380957957654', 0, 'NOOB', 'female', 'ACTIVE', 'berkyt', 1, 11);

insert into users (age, email, firstName, lastName, password, phoneNumber, userRating,rating, gender, state, username, countryId, cityId) values
(26, 'zencarych@gmail.com', 'Oleg', 'Zencarych', '$2a$10$e2qEa0wuno23cRAGky9Kd7O..A5YpXbUy3TDMhtrQ3FG3tkYsOpPmW', '+380957937654', 0,'NOOB', 'male', 'ACTIVE', 'zencarych', 2, 35);

insert into users (age, email, firstName, lastName, password, phoneNumber,userRating, rating, gender, state, username, countryId, cityId) values
(47, 'lama@gmail.com', 'Roman', 'Lama', '$2a$10$e2qEa0wunoicRAGky9Kd7O..A5YpXbUy3TD0htrQ3FG3tkYsOpPmW', '+380907987654', 0,'NOOB', 'male', 'ACTIVE', 'lama', 1, 11);

insert into users (age, email, firstName, lastName, password, phoneNumber, userRating,rating, gender, state, username, countryId, cityId) values
(57, 'vytryk@gmail.com', 'Katy', 'Vytryk', '$2a$10$e2qEa0wunoicRAGky9Kd7O..A5YpXb12y3TDMhtrQ3FG3tkYsOpPmW', '+380951987654', 0, 'NOOB', 'female', 'ACTIVE', 'vytryk', 2, 29);

insert into users (age, email, firstName, lastName, password, phoneNumber, userRating,rating, gender, state, username, countryId, cityId) values
(26, 'plotnikov@root.com', 'Volody', 'Plotnikov', '$2a$10$e2qEa0wernoicRAGky9Kd7O..A5YpXbUy3TDMhtrQ3F83tkYsOpPmW', '+380000780000',  0,'NOOB', 'male', 'ACTIVE', 'plotnikov', 1, 14);

insert into users (age, email, firstName, lastName, password, phoneNumber, userRating,rating, gender, state, username, countryId, cityId) values
(27, 'kaban@gmail.com', 'Sasha', 'Kaban', '$2a$10$e2qEa0wusdfoicRAGky9Kd7O..A5YpXbUy3TDMht673FG3tkYsOpPmW', '+380957917454', 0,'NOOB', 'male', 'ACTIVE', 'kaban', 1, 9);

insert into users (age, email, firstName, lastName, password, phoneNumber,userRating, rating, gender, state, username, countryId, cityId) values
(39, 'konovalchyk@gmail.com', 'Valeriy', 'Konovalchyk', '$2a$10$e2qEadffwunoicRAGky9Kd7O..A5YpXbUy3TDMhtrQ3FG3tkYsOphjW', '+380957987604', 0,'NOOB', 'female', 'ACTIVE', 'konovalchyk', 2, 35);

insert into users (age, email, firstName, lastName, password, phoneNumber, userRating,rating, gender, state, username, countryId, cityId) values
(17, 'trysh@gmail.com', 'Khrustuna', 'Trysh', '$2a$10$e2qEa0rtoicRAGky9Kd7O..A5YsdbUy3TDMhtrQ3FG3tkYsOpPmW', '+388957957654', 0, 'NOOB', 'female', 'ACTIVE', 'trysh', 1, 7);

insert into users (age, email, firstName, lastName, password, phoneNumber, userRating,rating, gender, state, username, countryId, cityId) values
(55, 'aslamov@gmail.com', 'Igor', 'Aslamov', '$2a$10$e2qEa0wunoicRAGky9Kd7O..A5YpXb12y3TDMhtrQ34G3tkYsOpPmW', '+380941987654', 0, 'NOOB', 'male', 'ACTIVE', 'aslamov', 2, 31);

insert into users (age, email, firstName, lastName, password, phoneNumber, userRating,rating, gender, state, username, countryId, cityId) values
(16, 'gorbach@root.com', 'Ivan', 'Gorbach', '$2a$10$e2qEa0wernoicRAGky9Kd7O..A5YpXbUy3TDMhtrQ3F83tkYsj5PmW', '+380000780600',  0,'NOOB', 'male', 'ACTIVE', 'gorbach', 1, 12);

insert into users (age, email, firstName, lastName, password, phoneNumber, userRating,rating, gender, state, username, countryId, cityId) values
(37, 'andreeva@gmail.com', 'Olya', 'Andreeva', '$2a$10$e2qEa0wusdfoicRAGky9Kd7O..A5YpXbUy3TDMht67b3G3tkYsOpPmW', '+380957927454', 0,'NOOB', 'female', 'ACTIVE', 'andreeva', 1, 12);

insert into users (age, email, firstName, lastName, password, phoneNumber,userRating, rating, gender, state, username, countryId, cityId) values
(19, 'konotopska@gmail.com', 'Ulia', 'Konotopska', '$2a$10$e2qEadffwunoicRAGky9Kd7O..A5YpXbUy3TDMhtrQ334G3tkYsOphjW', '+380957487604', 0,'NOOB', 'female', 'ACTIVE', 'konotopska', 2, 36);

insert into users (age, email, firstName, lastName, password, phoneNumber, userRating,rating, gender, state, username, countryId, cityId) values
(87, 'morison@gmail.com', 'Jim', 'Morison', '$2a$10$e2qEa0rtoicRAGky9Kd7O..A5YsdbUy3T22htrQ3FG3tkYsOpPmW', '+388957227654', 0, 'NOOB', 'male', 'ACTIVE', 'morison', 1, 14);

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

-- inserts to table of users
INSERT INTO `boardgames`.`gameuser` (`countOfComments`, `description`, `edition`, `maxPlayers`, `minPlayers`, `rules`, `status`, `yearOfProduction`, `game_id`, `user_id`) VALUES ('0', 'bla bla', '2', '4', '2', 'no', 'PRIVATE', '1900', '1', '1');
INSERT INTO `boardgames`.`gameuser` (`countOfComments`, `description`, `edition`, `maxPlayers`, `minPlayers`, `rules`, `status`, `yearOfProduction`, `game_id`, `user_id`) VALUES ('0', 'bla bla', '2', '4', '2', 'no', 'PRIVATE', '1900', '2', '2');
INSERT INTO `boardgames`.`gameuser` (`countOfComments`, `description`, `edition`, `maxPlayers`, `minPlayers`, `rules`, `status`, `yearOfProduction`, `game_id`, `user_id`) VALUES ('0', 'bla bla', '2', '4', '2', 'no', 'PRIVATE', '1900', '3', '3');
INSERT INTO `boardgames`.`gameuser` (`countOfComments`, `description`, `edition`, `maxPlayers`, `minPlayers`, `rules`, `status`, `yearOfProduction`, `game_id`, `user_id`) VALUES ('0', 'bla bla', '2', '4', '2', 'no', 'PRIVATE', '1900', '4', '4');
INSERT INTO `boardgames`.`gameuser` (`countOfComments`, `description`, `edition`, `maxPlayers`, `minPlayers`, `rules`, `status`, `yearOfProduction`, `game_id`, `user_id`) VALUES ('0', 'bla bla', '2', '4', '2', 'no', 'PRIVATE', '1900', '5', '5');


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

-- inserts to table tournaments
INSERT INTO `boardgames`.`tournament` (`city`, `countOfParticipants`, `country`, `dateOfTournament`, `name`, `game_id`, `userCreator_id`) VALUES ('Lviv', '8', 'Ukraine', '2016-05-17 00:00:00', 'ChessTournament', '2', '8');
INSERT INTO `boardgames`.`tournament` (`city`, `countOfParticipants`, `country`, `dateOfTournament`, `name`, `game_id`, `userCreator_id`) VALUES ('Lviv', '4', 'Ukraine', '2016-05-27 00:00:00', 'Monopoly Tornament', '1', '1');


--  inserts to table events
INSERT INTO `boardgames`.`events` (`name`, `description`, `imgSrc`, `date`, `place`, `userId`, `gameId`) VALUES ('Everybody sleeps but mafia members wake up', 'Mafia event', 'resources/images/mafiaImg.jpg', '2016-06-06', 'Lviv', '1', '3');
INSERT INTO `boardgames`.`events` (`name`, `description`, `imgSrc`, `date`, `place`, `userId`, `gameId`) VALUES ('Game of kings', 'Chess event', 'resources/images/chessImg.jpg', '2016-06-18', 'Lviv', '2', '4');
INSERT INTO `boardgames`.`events` (`name`, `description`, `imgSrc`, `date`, `place`, `userId`, `gameId`) VALUES ('One of the best games of the Western world', 'Skrabble event', 'resources/images/skrabbleImg.jpg', '2016-06-20', 'Lviv', '3', '5');
INSERT INTO `boardgames`.`events` (`name`, `description`, `imgSrc`, `date`, `place`, `userId`, `gameId`) VALUES ('Come with your kids: fun for everybody', 'Tic Tac Toe event', 'resources/images/tic-tac-toeImg.jpg', '2016-06-28', 'Lviv', '4', '6');
INSERT INTO `boardgames`.`events` (`name`, `description`, `imgSrc`, `date`, `place`, `userId`, `gameId`) VALUES ('If America was a game of Monopoly', 'Monopoly event', 'resources/images/monopolyImg.jpg', '2016-07-02', 'Lviv', '2', '1');
INSERT INTO `boardgames`.`events` (`name`, `description`, `imgSrc`, `date`, `place`, `userId`, `gameId`) VALUES ('Loving and Linkin', 'Dominos event', 'resources/images/dominosImg.jpg', '2016-07-13', 'Lviv', '3', '9');
INSERT INTO `boardgames`.`events` (`name`, `description`, `imgSrc`, `date`, `place`, `userId`, `gameId`) VALUES ('When your body is the canvas', 'Pictionary event', 'resources/images/pictionaryImg.jpg', '2016-07-22', 'Lviv', '2', '7');
INSERT INTO `boardgames`.`events` (`name`, `description`, `imgSrc`, `date`, `place`, `userId`, `gameId`) VALUES ('This is simply a wonderful visual feast', 'Utopia event', 'resources/images/utopiaImg.jpg', '2016-08-20', 'Lviv', '1', '8');
