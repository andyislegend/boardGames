
use boardgames;


-- here inserts for all tables

--  inserts to table roles
INSERT INTO `boardgames`.`role` (`id`, `userRole`) VALUES ('1', 'Admin');
INSERT INTO `boardgames`.`role` (`id`, `userRole`) VALUES ('2', 'User');



--  inserts to table category



--  inserts to table address
INSERT INTO `boardgames`.`address` (`id`, `city`, `country`, `houseNumber`, `postCode`, `roomNumber`, `street`) VALUES ('1', 'Lviv', 'Ukraine', '100', '79002', '23', 'pr. Svobody');
INSERT INTO `boardgames`.`address` (`id`, `city`, `country`, `houseNumber`, `postCode`, `roomNumber`, `street`) VALUES ('2', 'Kyiv', 'Ukraine', '56', '86005', '78', 'Checheta');
INSERT INTO `boardgames`.`address` (`id`, `city`, `country`, `houseNumber`, `postCode`, `roomNumber`, `street`) VALUES ('3', 'Donetsk', 'Ukraine', '12', '77445', '25', 'Ozerna');
INSERT INTO `boardgames`.`address` (`id`, `city`, `country`, `houseNumber`, `postCode`, `roomNumber`, `street`) VALUES ('4', 'Odessa', 'Ukraine', '86', '98765', '49', 'Shyroka');

--  inserts to table user
INSERT INTO `boardgames`.`users` (`id`, `age`, `email`, `firstName`, `lastName`, `password`, `phoneNumber`, `sex`, `addressId`, `role_id`) VALUES ('1', '18', 'stepa@ukr.net', 'Stepa', 'Makar', '1111', '101', 'male', '1', '2');
INSERT INTO `boardgames`.`users` (`id`, `age`, `email`, `firstName`, `lastName`, `password`, `phoneNumber`, `sex`, `addressId`, `role_id`) VALUES ('2', '19', 'body@ukr.net', 'Body', 'Makar', '12345', '102', 'male', '4', '2');
INSERT INTO `boardgames`.`users` (`id`, `age`, `email`, `firstName`, `lastName`, `password`, `phoneNumber`, `sex`, `addressId`, `role_id`) VALUES ('3', '27', 'ulia@ukr.net', 'Ulia', 'Bondar', 'qwerty', '103', 'female', '2', '2');
INSERT INTO `boardgames`.`users` (`id`, `age`, `email`, `firstName`, `lastName`, `password`, `phoneNumber`, `sex`, `addressId`, `role_id`) VALUES ('4', '18', 'andry@ukr.net', 'Andry', 'Bakaim', '112233', '104', 'male', '3', '2');


--  inserts to table game

--  inserts to table status
INSERT INTO `boardgames`.`status` (`id`, `statusOfFriend`) VALUES ('1', 'NOTCONSIDER');
INSERT INTO `boardgames`.`status` (`id`, `statusOfFriend`) VALUES ('2', 'ACCEPTED');
INSERT INTO `boardgames`.`status` (`id`, `statusOfFriend`) VALUES ('3', 'REJECTED');


--  inserts to table friends
INSERT INTO `boardgames`.`friends` (`id`, `pathToAva`, `status`, `user_one`, `user_two`) VALUES ('1', 'qq', '2', '1', '2');
INSERT INTO `boardgames`.`friends` (`id`, `pathToAva`, `status`, `user_one`, `user_two`) VALUES ('2', 'qq', '2', '3', '1');
INSERT INTO `boardgames`.`friends` (`id`, `pathToAva`, `status`, `user_one`, `user_two`) VALUES ('3', 'qq', '1', '4', '2');
INSERT INTO `boardgames`.`friends` (`id`, `pathToAva`, `status`, `user_one`, `user_two`) VALUES ('4', 'qq', '1', '2', '3');
INSERT INTO `boardgames`.`friends` (`id`, `pathToAva`, `status`, `user_one`, `user_two`) VALUES ('5', 'qq', '3', '4', '1');



--  inserts to table user_game