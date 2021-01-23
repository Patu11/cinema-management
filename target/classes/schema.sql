CREATE TABLE client
(
    client_id  INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL,
    birth_date DATE         NOT NULL
);


CREATE TABLE reservation
(
    reservation_id INT PRIMARY KEY AUTO_INCREMENT,
    hall_id        INT  NOT NULL,
    movie_id       INT  NOT NULL,
    client_id      INT  NOT NULL,
    res_date       DATE NOT NULL,
    seat_number    INT  NOT NULL
);

CREATE TABLE hall
(
    hall_id         INT PRIMARY KEY AUTO_INCREMENT,
    seats_number    INT NOT NULL,
    available_seats INT NOT NULL
);

CREATE TABLE movie
(
    movie_id INT PRIMARY KEY AUTO_INCREMENT,
    title    VARCHAR(255) NOT NULL,
    age_cat  INT          NOT NULL,
    length   INT          NOT NULL,
    genre    VARCHAR(255) NOT NULL,
    price    DOUBLE       NOT NULL
);

ALTER TABLE reservation
    ADD FOREIGN KEY (client_id) REFERENCES client (client_id);

ALTER TABLE reservation
    ADD FOREIGN KEY (hall_id) REFERENCES hall (hall_id);

ALTER TABLE reservation
    ADD FOREIGN KEY (movie_id) REFERENCES movie (movie_id);