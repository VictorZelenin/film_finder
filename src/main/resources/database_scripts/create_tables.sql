# TODO check foreign keys

#1
CREATE TABLE IF NOT EXISTS `clients` (
  `id`        INTEGER     NOT NULL UNIQUE AUTO_INCREMENT,
  `name`      VARCHAR(45) NOT NULL,
  `gender`    VARCHAR (10),
  `email`     VARCHAR(60) UNIQUE,
  `password`  VARCHAR(60),
  `photo_url` VARCHAR(45),
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB;

#2
CREATE TABLE IF NOT EXISTS `admins` (
  `id`       INTEGER     NOT NULL UNIQUE AUTO_INCREMENT,
  `name`     VARCHAR(45) NOT NULL,
  `email`    VARCHAR(60) UNIQUE,
  `password` VARCHAR(60),
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB;

#3
CREATE TABLE IF NOT EXISTS `messages` (
  `id`        INTEGER NOT NULL UNIQUE AUTO_INCREMENT,
  `message`   VARCHAR(200)            DEFAULT NULL,
  `date`      DATE                    DEFAULT NULL,
  `client_id` INTEGER UNIQUE,
  CONSTRAINT FOREIGN KEY (`client_id`) REFERENCES `clients` (`id`)
    ON UPDATE CASCADE
    ON DELETE CASCADE
)
  ENGINE = InnoDB;

#4  todo check this script
CREATE TABLE IF NOT EXISTS `messages_to_admins` (
  `admin_id`   INTEGER,
  `message_id` INTEGER,
  PRIMARY KEY (`admin_id`, `message_id`),
  CONSTRAINT `fk_messages_to_admins_1` FOREIGN KEY (`admin_id`) REFERENCES `admins` (`id`)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
  CONSTRAINT `fk_messages_to_admins_2` FOREIGN KEY (`message_id`) REFERENCES `messages` (`id`)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);

#5
CREATE TABLE IF NOT EXISTS `acting_people` (
  `id`                  INTEGER NOT NULL UNIQUE AUTO_INCREMENT,
  `name`                VARCHAR(45)             DEFAULT NULL,
  `height`              DOUBLE                  DEFAULT NULL,
  `gender`              VARCHAR (10),
  `country`             VARCHAR(45)             DEFAULT NULL,
  `age`                 INTEGER                 DEFAULT NULL,
  `death_date`          DATE                    DEFAULT NULL,
  `total_movies_number` INTEGER                 DEFAULT NULL,
  `photo_url`           VARCHAR(60),
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB;

#6
CREATE TABLE IF NOT EXISTS `roles_in_movie` (
  `id`   INTEGER NOT NULL UNIQUE AUTO_INCREMENT,
  `role` VARCHAR(45)             DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB;

#7
CREATE TABLE IF NOT EXISTS `genres` (
  `id`    INTEGER NOT NULL UNIQUE AUTO_INCREMENT,
  `genre` VARCHAR(45)             DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB;

#8
CREATE TABLE IF NOT EXISTS `movies` (
  `id`                  INTEGER NOT NULL UNIQUE AUTO_INCREMENT,
  `title`               VARCHAR(45)             DEFAULT NULL,
  `movie_type`          VARCHAR(45)             DEFAULT NULL,
  `release_date`        DATE                    DEFAULT NULL,
  `runtime`             INTEGER                 DEFAULT NULL,
  `plot`                VARCHAR(100)            DEFAULT NULL,
  `country`             VARCHAR(45)             DEFAULT NULL,
  `imdb_rating`         DOUBLE                  DEFAULT NULL,
  `imdb_votes`          DOUBLE                  DEFAULT NULL,
  `average_client_mark` DOUBLE                  DEFAULT NULL,
  `poster_url`          VARCHAR(60)             DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB;

#9
CREATE TABLE IF NOT EXISTS `acting_person_marks` (
  `id`          INTEGER NOT NULL UNIQUE AUTO_INCREMENT,
  `mark`        INTEGER                 DEFAULT NULL,
  `date`        DATE                    DEFAULT NULL,
  `description` VARCHAR(100)            DEFAULT NULL,
  `person_id`   INTEGER,
  `client_id`   INTEGER,
  PRIMARY KEY (`id`),
  CONSTRAINT FOREIGN KEY (`person_id`) REFERENCES acting_people (`id`)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
  CONSTRAINT FOREIGN KEY (`client_id`) REFERENCES clients (`id`)
    ON UPDATE CASCADE
    ON DELETE CASCADE
)
  ENGINE = InnoDB;

#10
CREATE TABLE IF NOT EXISTS `movie_marks` (
  `id`          INTEGER NOT NULL UNIQUE AUTO_INCREMENT,
  `mark`        INTEGER                 DEFAULT NULL,
  `date`        DATE                    DEFAULT NULL,
  `description` VARCHAR(100)            DEFAULT NULL,
  `movie_id`    INTEGER,
  `client_id`   INTEGER,
  PRIMARY KEY (`id`),
  CONSTRAINT FOREIGN KEY (`movie_id`) REFERENCES movies (`id`)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
  CONSTRAINT FOREIGN KEY (`client_id`) REFERENCES clients (`id`)
    ON UPDATE CASCADE
    ON DELETE CASCADE
)
  ENGINE = InnoDB;

#11
CREATE TABLE IF NOT EXISTS `acting_person_genres` (
  `acting_person_id` INTEGER,
  `genre_id`         INTEGER,
  CONSTRAINT FOREIGN KEY (acting_person_id) REFERENCES acting_people (`id`)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
  CONSTRAINT FOREIGN KEY (genre_id) REFERENCES genres (`id`)
    ON UPDATE CASCADE
    ON DELETE CASCADE
)
  ENGINE = InnoDB;

#12
CREATE TABLE IF NOT EXISTS `acting_person_movies` (
  `acting_person_id` INTEGER,
  `movie_id`         INTEGER,
  CONSTRAINT FOREIGN KEY (acting_person_id) REFERENCES acting_people (`id`)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
  CONSTRAINT FOREIGN KEY (movie_id) REFERENCES movies (`id`)
    ON UPDATE CASCADE
    ON DELETE CASCADE
)
  ENGINE = InnoDB;

#13
CREATE TABLE IF NOT EXISTS `acting_person_roles` (
  `acting_person_id` INTEGER,
  `role_id`          INTEGER,
  CONSTRAINT FOREIGN KEY (acting_person_id) REFERENCES acting_people (`id`)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
  CONSTRAINT FOREIGN KEY (role_id) REFERENCES roles_in_movie (`id`)
    ON UPDATE CASCADE
    ON DELETE CASCADE
)
  ENGINE = InnoDB;

#14
CREATE TABLE IF NOT EXISTS `clients_movies` (
  `movie_id`  INTEGER,
  `client_id` INTEGER,
  CONSTRAINT FOREIGN KEY (movie_id) REFERENCES movies (`id`)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
  CONSTRAINT FOREIGN KEY (client_id) REFERENCES clients (`id`)
    ON UPDATE CASCADE
    ON DELETE CASCADE
)
  ENGINE = InnoDB;

#15
CREATE TABLE IF NOT EXISTS `movies_genres` (
  `movie_id` INTEGER,
  `genre_id` INTEGER,
  CONSTRAINT FOREIGN KEY (movie_id) REFERENCES movies (`id`)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
  CONSTRAINT FOREIGN KEY (genre_id) REFERENCES genres (`id`)
    ON UPDATE CASCADE
    ON DELETE CASCADE
)
  ENGINE = InnoDB;


