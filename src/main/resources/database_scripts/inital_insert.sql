# acting people
INSERT INTO `acting_people` (`name`, `gender`, `height`, `country`, `age`, `total_movies_number`,
                             `photo_url`)
VALUES ('Brad Pitt', 'male', 180, 'USA', 52, 263,
        '/home/victor/images/acting_person_images/actors/Brad_Pitt.jpg');

INSERT INTO `acting_people` (`name`, `gender`, `height`, `country`, `age`, `total_movies_number`, `photo_url`)
VALUES ('Jim Carrey','male', 188, 'USA', 54, 207,
        '/home/victor/images/acting_person_images/actors/Jim_Carrey.jpg');

INSERT INTO `acting_people` (`name`, `gender`, `height`, `country`, `age`, `total_movies_number`, `photo_url`)
VALUES ('Kit Harington', 'male', 173, 'UK', 29, 39,
        '/home/victor/images/acting_person_images/actors/Kit_Harington.jpg');

INSERT INTO `acting_people` (`name`, `gender`, `height`, `country`, `age`, `total_movies_number`, `photo_url`)
VALUES ('Rachel McAdams', 'female', 163, 'Canada', 37, 79,
        '/home/victor/images/acting_person_images/actors/Rachel_McAdams.jpg');

INSERT INTO `acting_people` (`name`, `gender`, `height`, `country`, `age`, `total_movies_number`, `photo_url`)
VALUES ('Emilia Clarke', 'female', 157, 'UK', 29, 50,
        '/home/victor/images/acting_person_images/actors/Emilia_Clarke.jpg');

INSERT INTO `acting_people` (`name`, `gender`, `height`, `country`, `age`, `death_date`, `total_movies_number`,
                             `photo_url`)
VALUES ('Stanley Kubrick', 'male', 169, 'USA', 70, '1999-03-07', 40,
        '/home/victor/images/acting_person_images/directors/Stanley_Kubrick.jpg');

INSERT INTO `acting_people` (`name`, `gender`, `height`, `country`, `age`, `total_movies_number`,
                             `photo_url`)
VALUES ('Quentin Tarantino', 'male', 185, 'USA', 53, 189,
        '/home/victor/images/acting_person_images/directors/Quentin_Tarantino.jpg');

INSERT INTO `acting_people` (`name`, `gender`, `height`, `country`, `age`, `total_movies_number`,
                             `photo_url`)
VALUES ('Martin Scorsese', 'male', 163, 'USA', 73, 300,
        '/home/victor/images/acting_person_images/directors/Martin_Scorsese.jpg');

# genres
INSERT INTO genres (genre) VALUES ('action');
INSERT INTO genres (genre) VALUES ('adventure');
INSERT INTO genres (genre) VALUES ('animation');
INSERT INTO genres (genre) VALUES ('biography');
INSERT INTO genres (genre) VALUES ('comedy');
INSERT INTO genres (genre) VALUES ('crime');
INSERT INTO genres (genre) VALUES ('documentary');
INSERT INTO genres (genre) VALUES ('drama');
INSERT INTO genres (genre) VALUES ('family');
INSERT INTO genres (genre) VALUES ('fantasy');
INSERT INTO genres (genre) VALUES ('film_noir');
INSERT INTO genres (genre) VALUES ('game_show');
INSERT INTO genres (genre) VALUES ('history');
INSERT INTO genres (genre) VALUES ('horror');
INSERT INTO genres (genre) VALUES ('music');
INSERT INTO genres (genre) VALUES ('musical');
INSERT INTO genres (genre) VALUES ('mystery');
INSERT INTO genres (genre) VALUES ('news');
INSERT INTO genres (genre) VALUES ('reality');
INSERT INTO genres (genre) VALUES ('romance');
INSERT INTO genres (genre) VALUES ('sci_fi');
INSERT INTO genres (genre) VALUES ('sport');
INSERT INTO genres (genre) VALUES ('talk_show');
INSERT INTO genres (genre) VALUES ('thriller');
INSERT INTO genres (genre) VALUES ('war');
INSERT INTO genres (genre) VALUES ('western');
INSERT INTO genres (genre) VALUES ('melodrama');
INSERT INTO genres (genre) VALUES ('detective');

#acting peson genres
INSERT INTO acting_person_genres VALUES (3, 9);
INSERT INTO acting_person_genres VALUES (3, 6);
INSERT INTO acting_person_genres VALUES (3, 25);
INSERT INTO acting_person_genres VALUES (4, 6);
INSERT INTO acting_person_genres VALUES (4, 9);
INSERT INTO acting_person_genres VALUES (4, 11);
INSERT INTO acting_person_genres VALUES (5, 9);
INSERT INTO acting_person_genres VALUES (5, 3);
INSERT INTO acting_person_genres VALUES (5, 11);
INSERT INTO acting_person_genres VALUES (6, 9);
INSERT INTO acting_person_genres VALUES (6, 28);
INSERT INTO acting_person_genres VALUES (6, 6);
INSERT INTO acting_person_genres VALUES (7, 9);
INSERT INTO acting_person_genres VALUES (7, 8);
INSERT INTO acting_person_genres VALUES (7, 25);
INSERT INTO acting_person_genres VALUES (8, 25);
INSERT INTO acting_person_genres VALUES (8, 9);
INSERT INTO acting_person_genres VALUES (8, 26);
INSERT INTO acting_person_genres VALUES (9, 6);
INSERT INTO acting_person_genres VALUES (9, 9);
INSERT INTO acting_person_genres VALUES (9, 26);
INSERT INTO acting_person_genres VALUES (10, 9);
INSERT INTO acting_person_genres VALUES (10, 8);
INSERT INTO acting_person_genres VALUES (10, 7);

#clients
INSERT INTO clients VALUES (1, 'Tamara Kuznichenko', 'female', 'tamara_kuz@mail.ru', '1q2w3e4r5t', NULL);
INSERT INTO clients VALUES (2, 'Victor Zelenin', 'male', 'zelenin.victor95@gmail.com', '1q2w3e4r5t', NULL);
INSERT INTO clients VALUES (3, 'vic', 'male', 'vitjazelenin@rambler.ru', '1q2w3e4r5t', NULL);

#acting person marks
INSERT INTO acting_person_marks VALUES (1, 10, '2016-08-02', 'Great actor, loves his films', 4, 2);
INSERT INTO acting_person_marks VALUES (2, 9, '2016-08-02', 'Great director, loves his films', 10, 2);
INSERT INTO acting_person_marks VALUES (3, 5, '2016-08-02', NULL, 7, 1);
INSERT INTO acting_person_marks VALUES (4, 10, '2016-08-02', 'Тарантино - лучший', 9, 1);

#movies
INSERT INTO movies
VALUES (1, 'The Departed', 'movie', '2006-10-06', 151,
           'An undercover cop and a mole in the police attempt to identify each other while infiltrating
           an Irish gang in South Boston.', 'USA', 8.5, 860.751, NULL,
           '/home/victor/images/movies/The_Departed.jpg');
INSERT INTO movies
VALUES (2, 'Sherlock Holmes', 'movie', '2009-12-25', 128,
           'Detective Sherlock Holmes and his stalwart partner Watson engage in a battle of wits and
           brawn with a nemesis whose plot is a threat to all of England.', 'USA', 7.6, 473.745, NULL,
           '/home/victor/images/movies/Sherlock_Holmes.jpg');
INSERT INTO movies
VALUES (3, 'Breaking Bad', 'series', '2008-01-20', 49,
           'A high school chemistry teacher diagnosed with inoperable lung cancer turns to manufacturing
           and selling methamphetamine, with his former student, in order to secure his family''s
           financial future.', 'USA', 9.5, 855.152, NULL,
           '/home/victor/images/movies/Breaking_Bad.jpg');

INSERT INTO movies
VALUES (4, 'Fight Club', 'movie', '1999-10-15', 139,
           'An insomniac office worker, looking for a way to change his life, crosses paths with a
           devil-may-care soap maker, forming an underground fight club that evolves into something much,
           much more...', 'USA', 8.9, 1335.613, NULL,
           '/home/victor/images/movies/Fight_Club.jpg');
INSERT INTO movies
VALUES (5, 'Kill Bill: Vol. 1', 'movie', '2003-10-10', 111,
           'The Bride wakens from a four-year coma. The child she carried in her womb is gone. Now she
           must wreak vengeance on the team of assassins who betrayed her - a team she was once part of.',
           'USA', 8.1, 733.413, NULL,
           '/home/victor/images/movies/Kill_bill_1.jpg');
INSERT INTO movies
VALUES (6, 'Москва слезам не верит', 'movie', '1980-02-11', 150,
           'Москва пятидесятых годов. Три молодые провинциалки приезжают в Москву в поисках того, что ищут люди во
           всех столицах мира — любви, счастья и достатка. Их судьбы складываются именно так, как предполагает
           характер каждой из девушек. ', 'СССР', 8.37, 775.4, NULL,
           '/home/victor/images/movies/M_S_N_V.jpg');

#acting person movies
INSERT INTO acting_person_movies VALUES (3, 4);
INSERT INTO acting_person_movies VALUES (9, 5);
INSERT INTO acting_person_movies VALUES (6, 2);
INSERT INTO acting_person_movies VALUES (10, 1);

#roles in movie
INSERT INTO roles_in_movie (role) VALUE ('actor');
INSERT INTO roles_in_movie (role) VALUE ('director');
INSERT INTO roles_in_movie (role) VALUE ('producer');
INSERT INTO roles_in_movie (role) VALUE ('screen_writer');

#acting person roles
INSERT INTO acting_person_roles VALUES (3, 1);
INSERT INTO acting_person_roles VALUES (3, 3);
INSERT INTO acting_person_roles VALUES (4, 1);
INSERT INTO acting_person_roles VALUES (4, 4);
INSERT INTO acting_person_roles VALUES (4, 3);
INSERT INTO acting_person_roles VALUES (5, 1);
INSERT INTO acting_person_roles VALUES (6, 1);
INSERT INTO acting_person_roles VALUES (7, 1);
INSERT INTO acting_person_roles VALUES (8, 2);
INSERT INTO acting_person_roles VALUES (8, 3);
INSERT INTO acting_person_roles VALUES (8, 4);
INSERT INTO acting_person_roles VALUES (9, 1);
INSERT INTO acting_person_roles VALUES (9, 2);
INSERT INTO acting_person_roles VALUES (9, 3);
INSERT INTO acting_person_roles VALUES (9, 4);
INSERT INTO acting_person_roles VALUES (10, 2);
INSERT INTO acting_person_roles VALUES (10, 3);
INSERT INTO acting_person_roles VALUES (10, 1);
INSERT INTO acting_person_roles VALUES (10, 4);

#admins
INSERT INTO admins (name, email, password) VALUES ('main_admin', 'zelenin.victor95@gmail.com', '12345');
INSERT INTO admins (name, email, password) VALUES ('victor', 'vitjazelenin@rambler.ru', '1q2w3e4r5t');

#chosen movies by client
INSERT INTO clients_movies VALUES (3, 2);
INSERT INTO clients_movies VALUES (1, 2);
INSERT INTO clients_movies VALUES (6, 3);
INSERT INTO clients_movies VALUES (1, 3);
INSERT INTO clients_movies VALUES (3, 1);
INSERT INTO clients_movies VALUES (4, 1);
INSERT INTO clients_movies VALUES (5, 2);

#messages
INSERT INTO messages VALUES (1, 'great service', '2016-08-02', 3);
INSERT INTO messages VALUES (2, 'it is better than kinopoisk', '2016-08-02', 2);
INSERT INTO messages VALUES (3, 'crappy site', '2016-08-02', 1);

# messeges to admins
INSERT INTO messages_to_admins VALUES (4, 1);
INSERT INTO messages_to_admins VALUES (4, 2);
INSERT INTO messages_to_admins VALUES (4, 3);
INSERT INTO messages_to_admins VALUES (5, 1);
INSERT INTO messages_to_admins VALUES (5, 2);
INSERT INTO messages_to_admins VALUES (5, 3);

#movie marks
INSERT INTO movie_marks VALUES (1, 10, '2016-08-02', 'Ohh .. Di Caprio', 1, 1);
INSERT INTO movie_marks VALUES (2, 8, '2016-08-02', 'Fabulous series!!', 3, 2);
INSERT INTO movie_marks VALUES (3, 2, '2016-08-02', NULL, 6, 3);
INSERT INTO movie_marks VALUES (4, 10, '2016-08-02', 'Любимый фильм', 6, 1);

# movie genres
INSERT INTO movies_genres VALUES (1, 25);
INSERT INTO movies_genres VALUES (1, 9);
INSERT INTO movies_genres VALUES (1, 7);
INSERT INTO movies_genres VALUES (2, 2);
INSERT INTO movies_genres VALUES (2, 25);
INSERT INTO movies_genres VALUES (2, 9);
INSERT INTO movies_genres VALUES (2, 6);
INSERT INTO movies_genres VALUES (2, 7);
INSERT INTO movies_genres VALUES (2, 3);
INSERT INTO movies_genres VALUES (2, 30);
INSERT INTO movies_genres VALUES (3, 25);
INSERT INTO movies_genres VALUES (3, 9);
INSERT INTO movies_genres VALUES (3, 7);
INSERT INTO movies_genres VALUES (4, 7);
INSERT INTO movies_genres VALUES (4, 25);
INSERT INTO movies_genres VALUES (4, 9);
INSERT INTO movies_genres VALUES (5, 2);
INSERT INTO movies_genres VALUES (6, 9);
INSERT INTO movies_genres VALUES (6, 28);
INSERT INTO movies_genres VALUES (6, 6);
