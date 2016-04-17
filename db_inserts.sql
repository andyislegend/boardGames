
use boardgames;


-- here inserts for all tables


--  inserts to table category



--  inserts to table address
INSERT INTO `boardgames`.`address` (`city`, `country`, `houseNumber`, `postCode`, `roomNumber`, `street`) VALUES ('Lviv', 'Ukraine', '100', '79002', '23', 'pr. Svobody');
INSERT INTO `boardgames`.`address` (`city`, `country`, `houseNumber`, `postCode`, `roomNumber`, `street`) VALUES ('Kyiv', 'Ukraine', '56', '86005', '78', 'Checheta');
INSERT INTO `boardgames`.`address` (`city`, `country`, `houseNumber`, `postCode`, `roomNumber`, `street`) VALUES ('Donetsk', 'Ukraine', '12', '77445', '25', 'Ozerna');
INSERT INTO `boardgames`.`address` (`city`, `country`, `houseNumber`, `postCode`, `roomNumber`, `street`) VALUES ('Odessa', 'Ukraine', '86', '98765', '49', 'Shyroka');

--  inserts to table user
insert into users (age, email, firstName, lastName, password, phoneNumber, rating, sex, state, username, addressId) values
(99, 'root@root.com', 'Super', 'Admin', '$2a$10$e2qEa0wunoicRAGky9Kd7O..A5YpXbUy3TDMhtrQ3FG3tkYsOpPmW', '+380000000000', 'GODLIKE', 'none', 'ACTIVE', 'root', '1');

insert into users (age, email, firstName, lastName, password, phoneNumber, rating, sex, state, username, addressId) values
(25, 'kravets@gmail.com', 'Artem', 'Kravets', '$2a$10$e2qEa0wunoicRAGky9Kd7O..A5YpXbUy3TDMhtrQ3FG3tkYsOpPmW', '+380957987654', 'NOOB', 'male', 'ACTIVE', 'kravets', '2');

insert into users (age, email, firstName, lastName, password, phoneNumber, rating, sex, state, username, addressId) values
(37, 'bondar@gmail.com', 'Ivan', 'Bondar', '$2a$10$e2qEa0wunoicRAGky9Kd7O..A5YpXbUy3TDMhtrQ3FG3tkYsOpPmW', '+380957987654', 'NOOB', 'male', 'ACTIVE', 'bondar', '3');

insert into users (age, email, firstName, lastName, password, phoneNumber, rating, sex, state, username, addressId) values
(37, 'khariv@gmail.com', 'Body', 'Khariv', '$2a$10$e2qEa0wunoicRAGky9Kd7O..A5YpXbUy3TDMhtrQ3FG3tkYsOpPmW', '+380957987654', 'NOOB', 'male', 'ACTIVE', 'khariv', '3');
-- inserts into user_role table
insert into user_role (username, value) values
(1, 'ADMIN'),
(2, 'USER'),
(1, 'SUPERADMIN'),
(1, 'USER');


--  inserts to table game

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



--  inserts to table user_game