use bustracker;
########################################################################################################################
########################################				MAYO					########################################
########################################################################################################################

CREATE TABLE Line (
    id_bus TINYINT PRIMARY KEY AUTO_INCREMENT,
    line_name VARCHAR(100),
    place INT(60),
    route VARCHAR(100),
    timetable time
);

CREATE TABLE Stop (
    id_stop TINYINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    location VARCHAR(100),
    time_estimation time
);

ALTER TABLE Stop DROP COLUMN location;
ALTER TABLE Stop DROP COLUMN time_estimation;

CREATE TABLE Line_Stop (
	id_bus TINYINT NOT NULL,
    id_stop TINYINT NOT NULL,
	FOREIGN KEY (id_bus) REFERENCES Line(id_bus),
    FOREIGN KEY (id_stop) REFERENCES Stop(id_stop)
);

CREATE TABLE DepartureTime (
    id_hour TINYINT PRIMARY KEY,
    id_bus TINYINT NOT NULL,
    FOREIGN KEY (id_bus) REFERENCES Line(id_bus)
);

CREATE TABLE Admin (
	id_admin TINYINT PRIMARY KEY AUTO_INCREMENT,
	user VARCHAR(50),
    password VARCHAR(50),
    email VARCHAR(50)
);


########################################################################################################################
########################################				JUNIO					########################################
########################################################################################################################


CREATE TABLE Booking (
    id_booking TINYINT PRIMARY KEY AUTO_INCREMENT,
    id_user TINYINT NOT NULL,
    id_bus TINYINT NOT NULL,
    date date,
    hour TIME,
    price double (10,2),
    availability VARCHAR(50),
    id_StopStart TINYINT,
    id_StopStart TINYINT,
    FOREIGN KEY (id_user) REFERENCES User(id_user),
    FOREIGN KEY (id_bus) REFERENCES Line(id_bus),
    FOREIGN KEY (id_film) REFERENCES Film(id_film)
);

CREATE TABLE User (
	id_user TINYINT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50),
	lastname VARCHAR(100),
    user varchar(50) unique,
	password varchar(50) unique,
	phone VARCHAR(20),
	isAdmin BOOLEAN
);


CREATE TABLE Opinion (
    id_opinion TINYINT PRIMARY KEY AUTO_INCREMENT,
    id_user TINYINT(50),
    score decimal (10,2),
    comments varchar(200),
    date date,
    hour time
);


