INSERT INTO regiones (id, name) VALUES (1, 'Sudamérica');
INSERT INTO regiones (id, name) VALUES (2, 'Centroamérica');
INSERT INTO regiones (id, name) VALUES (3, 'Norteamérica');
INSERT INTO regiones (id, name) VALUES (4, 'Europa');
INSERT INTO regiones (id, name) VALUES (5, 'Asia');
INSERT INTO regiones (id, name) VALUES (6, 'Africa');
INSERT INTO regiones (id, name) VALUES (7, 'Oceanía');
INSERT INTO regiones (id, name) VALUES (8, 'Antártida');

insert into customer (id, region_id, name, lastname, email, created_at) values (1, 1, 'Willy', 'Fernandez', 'willy@gmail.com', '2019-09-07');
insert into customer (id, region_id, name, lastname, email, created_at) values (2, 2, 'Agustina', 'Fernandez Girones', 'agus@gmail.com', '2019-09-06');
insert into customer (id, region_id, name, lastname, email, created_at) values (3, 3, 'Joaquin', 'Fernandez Girones', 'joacko@gmail.com', '2019-09-05');
insert into customer (id, region_id, name, lastname, email, created_at) values (4, 4, 'Karina', 'Girones', 'kari@gmail.com', '2019-09-04');
INSERT INTO customer (id, region_id, name, lastname, email, created_at) VALUES(5, 5, 'Andres', 'Guzman', 'profesor@bolsadeideas.com', '2017-08-01');
INSERT INTO customer (id, region_id, name, lastname, email, created_at) VALUES(6, 6, 'John', 'Doe', 'john.doe@gmail.com', '2017-08-02');
INSERT INTO customer (id, region_id, name, lastname, email, created_at) VALUES(7, 7, 'Linus', 'Torvalds', 'linus.torvalds@gmail.com', '2017-08-03');
INSERT INTO customer (id, region_id, name, lastname, email, created_at) VALUES(8, 8, 'Jane', 'Doe', 'jane.doe@gmail.com', '2017-08-04');
INSERT INTO customer (id, region_id, name, lastname, email, created_at) VALUES(9, 1, 'Rasmus', 'Lerdorf', 'rasmus.lerdorf@gmail.com', '2017-08-05');
INSERT INTO customer (id, region_id, name, lastname, email, created_at) VALUES(10, 2,'Erich', 'Gamma', 'erich.gamma@gmail.com', '2017-08-06');
INSERT INTO customer (id, region_id, name, lastname, email, created_at) VALUES(11, 3, 'Richard', 'Helm', 'richard.helm@gmail.com', '2017-08-07');
INSERT INTO customer (id, region_id, name, lastname, email, created_at) VALUES(12, 4, 'Ralph', 'Johnson', 'ralph.johnson@gmail.com', '2017-08-08');
INSERT INTO customer (id, region_id, name, lastname, email, created_at) VALUES(13, 5, 'John', 'Vlissides', 'john.vlissides@gmail.com', '2017-08-09');
INSERT INTO customer (id, region_id, name, lastname, email, created_at) VALUES(14, 6, 'James', 'Gosling', 'james.gosling@gmail.com', '2017-08-010');
INSERT INTO customer (id, region_id, name, lastname, email, created_at) VALUES(15, 7, 'Bruce', 'Lee', 'bruce.lee@gmail.com', '2017-08-11');
INSERT INTO customer (id, region_id, name, lastname, email, created_at) VALUES(16, 1, 'Johnny', 'Doe', 'johnny.doe@gmail.com', '2017-08-12');
INSERT INTO customer (id, region_id, name, lastname, email, created_at) VALUES(17, 2, 'John', 'Roe', 'john.roe@gmail.com', '2017-08-13');
INSERT INTO customer (id, region_id, name, lastname, email, created_at) VALUES(18, 3, 'Jane', 'Roe', 'jane.roe@gmail.com', '2017-08-14');
INSERT INTO customer (id, region_id, name, lastname, email, created_at) VALUES(19, 4, 'Richard', 'Doe', 'richard.doe@gmail.com', '2017-08-15');
INSERT INTO customer (id, region_id, name, lastname, email, created_at) VALUES(20, 5, 'Janie', 'Doe', 'janie.doe@gmail.com', '2017-08-16');
INSERT INTO customer (id, region_id, name, lastname, email, created_at) VALUES(21, 6, 'Phillip', 'Webb', 'phillip.webb@gmail.com', '2017-08-17');
INSERT INTO customer (id, region_id, name, lastname, email, created_at) VALUES(22, 7, 'Stephane', 'Nicoll', 'stephane.nicoll@gmail.com', '2017-08-18');
INSERT INTO customer (id, region_id, name, lastname, email, created_at) VALUES(23, 1, 'Sam', 'Brannen', 'sam.brannen@gmail.com', '2017-08-19');  
INSERT INTO customer (id, region_id, name, lastname, email, created_at) VALUES(24, 2, 'Juergen', 'Hoeller', 'juergen.Hoeller@gmail.com', '2017-08-20'); 
INSERT INTO customer (id, region_id, name, lastname, email, created_at) VALUES(25, 3, 'Janie', 'Roe', 'janie.roe@gmail.com', '2017-08-21');
INSERT INTO customer (id, region_id, name, lastname, email, created_at) VALUES(26, 4, 'John', 'Smith', 'john.smith@gmail.com', '2017-08-22');
INSERT INTO customer (id, region_id, name, lastname, email, created_at) VALUES(27, 5, 'Joe', 'Bloggs', 'joe.bloggs@gmail.com', '2017-08-23');
INSERT INTO customer (id, region_id, name, lastname, email, created_at) VALUES(28, 6, 'John', 'Stiles', 'john.stiles@gmail.com', '2017-08-24');
INSERT INTO customer (id, region_id, name, lastname, email, created_at) VALUES(29, 7, 'Richard', 'Roe', 'stiles.roe@gmail.com', '2017-08-25');

INSERT INTO users (id, username, password, enabled) VALUES (1, 'willy','$2a$10$mKywzjoSlLHpYxKzXwF6qe3/902a2BObZCrmBV7FD87T36czlfEhO',1);
INSERT INTO users (id, username, password, enabled) VALUES (2, 'admin','$2a$10$sFwvkRqgTjGzEwXnxcNoYeNo.efNA4WSmIAZbEX1QEDqbhvDVpSuG',1);

INSERT INTO roles (id, name) VALUES (1, 'ROLE_USER');
INSERT INTO roles (id, name) VALUES (2,'ROLE_ADMIN');

INSERT INTO users_authorities (user_id, role_id) VALUES (1, 1);
INSERT INTO users_authorities (user_id, role_id) VALUES (2, 2);
INSERT INTO users_authorities (user_id, role_id) VALUES (2, 1);

