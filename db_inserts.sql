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


insert into users (age, email, firstName, lastName, password, phoneNumber, userRating, rating, gender, state, username, countryId, cityId,tournamentRatingStatus) values
(99, 'root@root.com', 'Super', 'Admin', '$2a$10$e2qEa0wunoicRAGky9Kd7O..A5YpXbUy3TDMhtrQ3FG3tkYsOpPmW', '+380000000000',  63,'EXTRATERESTRIAL', 'female', 'ACTIVE', 'root', 1, 1,FALSE);

insert into users (age, email, firstName, lastName, password, phoneNumber, userRating,rating, gender, state, username, countryId, cityId,tournamentRatingStatus) values
(25, 'kravets@gmail.com', 'Artem', 'Kravets', '$2a$10$e2qEa0wunoicRAGky9Kd7O..A5YpXbUy3TDMhtrQ3FG3tkYsOpPmW', '+380957987654', 0,'NOOB', 'male', 'ACTIVE', 'kravets', 1, 2,FALSE);

INSERT INTO `boardgames`.`users` (`id`, `age`, `email`, `firstName`, `gender`, `lastName`, `password`, `phoneNumber`, `rating`, `state`, `userRating`, `username`, `countryId`, `cityId`,tournamentRatingStatus) 
VALUES (NULL, '45', 'prosinecki@pl.net', 'Vladislav', 'male', 'Prosinecki', '$2a$10$e2qEa0wunoicRAGky9Kd7O..A5YpXbUy3TDMhtrQ3FG3tkYsOpPmW', '+37986574545', 'NOOB', 'ACTIVE', '0', 'prosinecki', '2', '33',FALSE);

insert into users (age, email, firstName, lastName, password, phoneNumber,userRating, rating, gender, state, username, countryId, cityId,tournamentRatingStatus) values
(28, 'vterlyha@gmail.com', 'Volodymyr', 'Terlyha', '$2a$10$e2qEa0wunoicRAGky9Kd7O..A5YpXbUy3TDMhtrQ3FG3tkYsOpPmW', '+380935787496', 23,'PRO', 'male', 'ACTIVE', 'vterlyha', 1, 13,FALSE);

insert into users (age, email, firstName, lastName, password, phoneNumber, userRating,rating, gender, state, username, countryId, cityId,tournamentRatingStatus) values
(37, 'volodymyr_terlyha@ukr.net', 'Body', 'Khariv', '$2a$10$e2qEa0wunoicRAGky9Kd7O..A5YpXbUy3TDMhtrQ3FG3tkYsOpPmW', '+380957987654', -4, 'NOOB', 'male', 'ACTIVE', 'khariv', 2, 33,FALSE);

insert into users (age, email, firstName, lastName, password, phoneNumber, userRating,rating, gender, state, username, countryId, cityId,tournamentRatingStatus) values
(23, 'winston@root.com', 'Taras', 'Winston', '$2a$10$e2qEa0wunoicRAGky9Kd7O..A5YpXbUy3TDMhtrQ3FG3tkYsOpPmW', '+380000700000',  55,'WICKED_SICK', 'male', 'BANNED', 'winston', 2, 32,FALSE);

insert into users (age, email, firstName, lastName, password, phoneNumber, userRating,rating, gender, state, username, countryId, cityId,tournamentRatingStatus) values
(25, 'borodin@gmail.com', 'Vitalik', 'Borodin', '$2a$10$e2qEa0wusdfoicRAGky9Kd7O..A5YpXbUy3TDMhtrQ3FG3tkYsOpPmW', '+380957987454', 0,'NOOB', 'male', 'ACTIVE', 'borodin', 1, 13,FALSE);

insert into users (age, email, firstName, lastName, password, phoneNumber,userRating, rating, gender, state, username, countryId, cityId,tournamentRatingStatus) values
(38, 'nasty@gmail.com', 'Nasty', 'Ptashnyk', '$2a$10$e2qEadffwunoicRAGky9Kd7O..A5YpXbUy3TDMhtrQ3FG3tkYsOpPmW', '+380957987614', 0,'NOOB', 'female', 'ACTIVE', 'ptashnyk', 2, 34,FALSE);

insert into users (age, email, firstName, lastName, password, phoneNumber, userRating,rating, gender, state, username, countryId, cityId,tournamentRatingStatus) values
(47, 'lida@gmail.com', 'Lida', 'Berkyt', '$2a$10$e2qEa0rtoicRAGky9Kd7O..A5YpXbUy3TDMhtrQ3FG3tkYsOpPmW', '+380957957654', 0, 'NOOB', 'female', 'ACTIVE', 'berkyt', 1, 11,FALSE);

insert into users (age, email, firstName, lastName, password, phoneNumber, userRating,rating, gender, state, username, countryId, cityId,tournamentRatingStatus) values
(26, 'zencarych@gmail.com', 'Oleg', 'Zencarych', '$2a$10$e2qEa0wuno23cRAGky9Kd7O..A5YpXbUy3TDMhtrQ3FG3tkYsOpPmW', '+380957937654', 0,'NOOB', 'male', 'ACTIVE', 'zencarych', 2, 35,FALSE);

insert into users (age, email, firstName, lastName, password, phoneNumber,userRating, rating, gender, state, username, countryId, cityId,tournamentRatingStatus) values
(47, 'lama@gmail.com', 'Roman', 'Lama', '$2a$10$e2qEa0wunoicRAGky9Kd7O..A5YpXbUy3TD0htrQ3FG3tkYsOpPmW', '+380907987654', 0,'NOOB', 'male', 'ACTIVE', 'lama', 1, 11,FALSE);

insert into users (age, email, firstName, lastName, password, phoneNumber, userRating,rating, gender, state, username, countryId, cityId,tournamentRatingStatus) values
(57, 'vytryk@gmail.com', 'Katy', 'Vytryk', '$2a$10$e2qEa0wunoicRAGky9Kd7O..A5YpXb12y3TDMhtrQ3FG3tkYsOpPmW', '+380951987654', 0, 'NOOB', 'female', 'ACTIVE', 'vytryk', 2, 29,FALSE);

insert into users (age, email, firstName, lastName, password, phoneNumber, userRating,rating, gender, state, username, countryId, cityId,tournamentRatingStatus) values
(26, 'plotnikov@root.com', 'Volody', 'Plotnikov', '$2a$10$e2qEa0wernoicRAGky9Kd7O..A5YpXbUy3TDMhtrQ3F83tkYsOpPmW', '+380000780000',  0,'NOOB', 'male', 'ACTIVE', 'plotnikov', 1, 14,FALSE);

insert into users (age, email, firstName, lastName, password, phoneNumber, userRating,rating, gender, state, username, countryId, cityId,tournamentRatingStatus) values
(27, 'kaban@gmail.com', 'Sasha', 'Kaban', '$2a$10$e2qEa0wusdfoicRAGky9Kd7O..A5YpXbUy3TDMht673FG3tkYsOpPmW', '+380957917454', 0,'NOOB', 'male', 'ACTIVE', 'kaban', 1, 9,FALSE);

insert into users (age, email, firstName, lastName, password, phoneNumber,userRating, rating, gender, state, username, countryId, cityId,tournamentRatingStatus) values
(39, 'konovalchyk@gmail.com', 'Valeriy', 'Konovalchyk', '$2a$10$e2qEadffwunoicRAGky9Kd7O..A5YpXbUy3TDMhtrQ3FG3tkYsOphjW', '+380957987604', 0,'NOOB', 'female', 'ACTIVE', 'konovalchyk', 2, 35,FALSE);

insert into users (age, email, firstName, lastName, password, phoneNumber, userRating,rating, gender, state, username, countryId, cityId,tournamentRatingStatus) values
(17, 'trysh@gmail.com', 'Khrustuna', 'Trysh', '$2a$10$e2qEa0rtoicRAGky9Kd7O..A5YsdbUy3TDMhtrQ3FG3tkYsOpPmW', '+388957957654', 0, 'NOOB', 'female', 'ACTIVE', 'trysh', 1, 7,FALSE);

insert into users (age, email, firstName, lastName, password, phoneNumber, userRating,rating, gender, state, username, countryId, cityId,tournamentRatingStatus) values
(55, 'aslamov@gmail.com', 'Igor', 'Aslamov', '$2a$10$e2qEa0wunoicRAGky9Kd7O..A5YpXb12y3TDMhtrQ34G3tkYsOpPmW', '+380941987654', 0, 'NOOB', 'male', 'ACTIVE', 'aslamov', 2, 31,FALSE);

insert into users (age, email, firstName, lastName, password, phoneNumber, userRating,rating, gender, state, username, countryId, cityId,tournamentRatingStatus) values
(16, 'gorbach@root.com', 'Ivan', 'Gorbach', '$2a$10$e2qEa0wernoicRAGky9Kd7O..A5YpXbUy3TDMhtrQ3F83tkYsj5PmW', '+380000780600',  0,'NOOB', 'male', 'ACTIVE', 'gorbach', 1, 12,FALSE);

insert into users (age, email, firstName, lastName, password, phoneNumber, userRating,rating, gender, state, username, countryId, cityId, tournamentRatingStatus) values
(37, 'andreeva@gmail.com', 'Olya', 'Andreeva', '$2a$10$e2qEa0wusdfoicRAGky9Kd7O..A5YpXbUy3TDMht67b3G3tkYsOpPmW', '+380957927454', 0,'NOOB', 'female', 'ACTIVE', 'andreeva', 1, 12,FALSE);

insert into users (age, email, firstName, lastName, password, phoneNumber,userRating, rating, gender, state, username, countryId, cityId, tournamentRatingStatus) values
(19, 'konotopska@gmail.com', 'Ulia', 'Konotopska', '$2a$10$e2qEadffwunoicRAGky9Kd7O..A5YpXbUy3TDMhtrQ334G3tkYsOphjW', '+380957487604', 0,'NOOB', 'female', 'ACTIVE', 'konotopska', 2, 36,FALSE);

insert into users (age, email, firstName, lastName, password, phoneNumber, userRating,rating, gender, state, username, countryId, cityId, tournamentRatingStatus) values
(87, 'morison@gmail.com', 'Jim', 'Morison', '$2a$10$e2qEa0rtoicRAGky9Kd7O..A5YsdbUy3T22htrQ3FG3tkYsOpPmW', '+388957227654', 0, 'NOOB', 'male', 'ACTIVE', 'morison', 1, 14,FALSE);

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

INSERT INTO `boardgames`.`gamerating` (`rating`, `gameId`, `user_id`) VALUES ('10', '1', '2');
INSERT INTO `boardgames`.`gamerating` (`rating`, `gameId`, `user_id`) VALUES ('9', '2', '1');
INSERT INTO `boardgames`.`gamerating` (`rating`, `gameId`, `user_id`) VALUES ('8', '3', '2');
INSERT INTO `boardgames`.`gamerating` (`rating`, `gameId`, `user_id`) VALUES ('7', '4', '1');
INSERT INTO `boardgames`.`gamerating` (`rating`, `gameId`, `user_id`) VALUES ('6', '5', '3');
INSERT INTO `boardgames`.`gamerating` (`rating`, `gameId`, `user_id`) VALUES ('7', '6', '4');
INSERT INTO `boardgames`.`gamerating` (`rating`, `gameId`, `user_id`) VALUES ('8', '7', '1');
INSERT INTO `boardgames`.`gamerating` (`rating`, `gameId`, `user_id`) VALUES ('9', '1', '1');
INSERT INTO `boardgames`.`gamerating` (`rating`, `gameId`, `user_id`) VALUES ('10', '2', '2');

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
INSERT INTO `boardgames`.`friends` (`status`, `user`, `userId`) VALUES ('2', '4', '2');
INSERT INTO `boardgames`.`friends` (`status`, `user`, `userId`) VALUES ('2', '4', '3');
INSERT INTO `boardgames`.`friends` (`status`, `user`, `userId`) VALUES ('1', '4', '4');
INSERT INTO `boardgames`.`friends` (`status`, `user`, `userId`) VALUES ('1', '4', '5');
INSERT INTO `boardgames`.`friends` (`status`, `user`, `userId`) VALUES ('2', '4', '6');

-- inserts to table tournaments
INSERT INTO `boardgames`.`tournament` (`city`, `countOfParticipants`, `country`, `dateOfTournament`, `name`, `game_id`, `userCreator_id` ) VALUES ('Lviv', '4', 'Ukraine', '2016-05-17 00:00:00', 'Monopoly', '2', '8');
INSERT INTO `boardgames`.`tournament` (`city`, `countOfParticipants`, `country`, `dateOfTournament`, `name`, `game_id`, `userCreator_id`) VALUES ('Lviv', '6', 'Ukraine', '2016-05-27 00:00:00', 'Monopoly', '1', '1');
INSERT INTO `boardgames`.`tournament` (`city`, `countOfParticipants`, `country`, `dateOfTournament`, `name`, `game_id`, `userCreator_id`) VALUES ('Lviv', '4', 'Ukraine', '2016-05-26 00:00:00', 'Monopoly', '1', '3');


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


INSERT INTO `boardgames`.`notification` (`id`, `date`, `message`, `status`, `status_of_reading`, `type`, `userInvokerId`, `userId`, `user_sender`) VALUES ('1', '2016-05-25 01:12:35', 'неро', 'UNCHECKED', '1', 'MESSAGE', '0', '2', '1');
INSERT INTO `boardgames`.`notification` (`id`, `date`, `message`, `status`, `status_of_reading`, `type`, `userInvokerId`, `userId`, `user_sender`) VALUES ('2', '2016-05-25 01:12:37', '?', 'UNCHECKED', '0', 'MESSAGE', '0', '2', '3');
INSERT INTO `boardgames`.`notification` (`id`, `date`, `message`, `status`, `status_of_reading`, `type`, `userInvokerId`, `userId`) VALUES ('3', '2016-05-27 00:00:00', 'Dear user, you have tournament tomorrow, pleas do not miss it. Name of tournament is \"Monopoly\" it will be in Lviv, Ukraine in ', 'UNCHECKED', '0', 'NOTIFICATION', '0', '1');
INSERT INTO `boardgames`.`notification` (`id`, `date`, `message`, `status`, `status_of_reading`, `type`, `userInvokerId`, `userId`) VALUES ('4', '2016-05-27 00:00:00', 'Dear user, you have already sent request on \"Garage Party\" event, we hope you will spend time with pleasure. Feel free, enjoy the game and be happy! Event will be in Lviv, UA on ', 'UNCHECKED', '0', 'EVENT', '0', '1');
INSERT INTO `boardgames`.`notification` (`id`, `date`, `message`, `status`, `status_of_reading`, `type`, `userInvokerId`, `userId`) VALUES ('5', '2016-05-30 00:00:00', 'Dear user, you have already sent request on \"New Deck for Magic!\" event, we hope you will spend time with pleasure. Feel free, enjoy the game and be happy! Event will be in Lviv, UA on ', 'UNCHECKED', '0', 'EVENT', '0', '1');
INSERT INTO `boardgames`.`notification` (`id`, `date`, `message`, `status`, `status_of_reading`, `type`, `userInvokerId`, `userId`) VALUES ('6', '2016-05-27 00:00:00', 'Dear user, you have tournament tomorrow, pleas do not miss it. Name of tournament is \"Monopoly\" it will be in Lviv, Ukraine in ', 'UNCHECKED', '0', 'NOTIFICATION', '0', '1');
INSERT INTO `boardgames`.`notification` (`id`, `date`, `message`, `status`, `status_of_reading`, `type`, `userInvokerId`, `userId`) VALUES ('7', '2016-05-27 00:00:00', 'Dear user, you have tournament tomorrow, pleas do not miss it. Name of tournament is \"Monopoly\" it will be in Lviv, Ukraine in ', 'UNCHECKED', '0', 'NOTIFICATION', '0', '1');
INSERT INTO `boardgames`.`notification` (`id`, `date`, `message`, `status`, `status_of_reading`, `type`, `userInvokerId`, `userId`) VALUES ('8', '2016-05-27 00:00:00', 'Dear user, you have tournament tomorrow, pleas do not miss it. Name of tournament is \"Monopoly\" it will be in Lviv, Ukraine in ', 'UNCHECKED', '0', 'NOTIFICATION', '0', '1');
INSERT INTO `boardgames`.`notification` (`id`, `date`, `message`, `status`, `status_of_reading`, `type`, `userInvokerId`, `userId`) VALUES ('9', '2016-05-27 00:00:00', 'Dear user, you have tournament tomorrow, pleas do not miss it. Name of tournament is \"Monopoly\" it will be in Lviv, Ukraine in ', 'UNCHECKED', '0', 'NOTIFICATION', '0', '1');
INSERT INTO `boardgames`.`notification` (`id`, `date`, `message`, `status`, `status_of_reading`, `type`, `userInvokerId`, `userId`) VALUES ('10', '2016-05-27 00:00:00', 'Dear user, you have tournament tomorrow, pleas do not miss it. Name of tournament is \"Monopoly\" it will be in Lviv, Ukraine in ', 'UNCHECKED', '0', 'NOTIFICATION', '0', '1');
INSERT INTO `boardgames`.`notification` (`id`, `date`, `message`, `status`, `status_of_reading`, `type`, `userInvokerId`, `userId`, `user_sender`) VALUES ('11', '2016-05-25 01:43:27', '&', 'UNCHECKED', '0', 'MESSAGE', '0', '2', '1');
INSERT INTO `boardgames`.`notification` (`id`, `date`, `message`, `status`, `status_of_reading`, `type`, `userInvokerId`, `userId`, `user_sender`) VALUES ('12', '2016-05-25 01:43:30', ',', 'UNCHECKED', '0', 'MESSAGE', '0', '2', '1');

